
-- Kiểm tra các môn được đăng ký đã thỏa mãn các môn tiên quyết chưa
DELIMITER //
CREATE TRIGGER before_add_lophoc_check BEFORE INSERT ON phieudangkylophoc
FOR EACH ROW
BEGIN
 DECLARE count_montq INT;
 DECLARE total_montq INT;
 DECLARE mamh_lophoc VARCHAR(30);
 DECLARE mssv VARCHAR(30);
 DECLARE soluongsinhvien INT;
 DECLARE soluongsinhvientoida INT;
 
 -- Lấy Id của sinhvien
 SELECT pdk.MSSV INTO mssv 
 FROM phieudangky pdk
 WHERE pdk.MaPDK = NEW.MaPDK;
 
 -- Lấy mã môn học của lớp học được thêm vào
 SELECT MaMH INTO mamh_lophoc
 FROM lophoc lh 
 WHERE lh.MaLH = NEW.MaLH;
 
 -- Số các môn tiên quyết cần để học môn vừa đăng ký
 SELECT COUNT(*) INTO total_montq
 FROM dieukientienquyet AS dk
 WHERE dk.MaMH = mamh_lophoc;
 
 -- Đếm số lịch bị trùng với lịch vừa đăng ký
 SELECT COUNT(*) INTO @trung
 FROM lophoc lh JOIN phieudangkylophoc pdklh ON pdklh.MaLH = lh.MaLH
 WHERE pdklh.MaPDK = NEW.MaPDK AND EXISTS (
  SELECT 1 FROM lophoc lhtmp 
  WHERE lhtmp.MaLH = NEW.MaLH 
  AND lhtmp.Thu = lh.Thu
  AND ((lhtmp.TietKetThuc > lh.TietBatDau AND lhtmp.TietBatDau < lh.TietKetThuc)
  OR (lh.TietKetThuc > lhtmp.TietBatDau AND lh.TietBatDau < lhtmp.TietKetThuc)
  OR (lh.TietBatDau < lhtmp.TietBatDau AND lh.TietKetThuc > lhtmp.TietKetThuc)
  OR (lhtmp.TietBatDau < lh.TietBatDau AND lhtmp.TietKetThuc > lh.TietKetThuc)
  ));
  
  -- Nếu số lịch bị trùng lớn hơn 0 thì báo lỗi
  IF @trung > 0 THEN 
  SIGNAL SQLSTATE '45000'
  SET MESSAGE_TEXT = 'Lịch học bị trùng';
  END IF;
 
 -- Lấy số lượng sinhvien tối đa của lớp học
 SELECT SoLuongSinhVienToiDa INTO soluongsinhvientoida
 FROM lophoc as lh
 WHERE lh.MaLH = NEW.MaLH;
 
 -- Lấy số lượng sinhvien của lớp học
 SELECT SoLuongSinhVien INTO soluongsinhvien
 FROM lophoc as lh
 WHERE lh.MaLH = NEW.MaLH;
 
 -- Kiểm tra số lượng sinhvien theo lớp đó đã quá mức hay chưa
 IF soluongsinhvien >= soluongsinhvientoida THEN
 SIGNAL SQLSTATE '45000'
 SET MESSAGE_TEXT = 'Đã đủ sinh viên cho lớp học';
 END IF;
 
 -- Tăng số lượng sinhvien của lớp học đó sau khi đăng ký thành công
 UPDATE lophoc AS lh
 SET SoLuongSinhVien = SoLuongSinhVien + 1
 WHERE lh.MaLH = NEW.MaLH;
 
  -- Tăng số tín chỉ của phiếu đăng ký sau khi đăng ký
 UPDATE phieudangky AS pdk
 SET TongTinChi = TongTinChi + (SELECT SoTinChi FROM monhoc AS mh 
								WHERE mh.MaMH = mamh_lophoc)
 WHERE pdk.MaPDK = NEW.MaPDK;
 
 -- Nếu môn vừa đăng ký tồn tại môn tiên quyết
 IF total_montq > 0 THEN
     -- Đếm số môn học tiên quyết mà sinhvien đó đã pass
	 SELECT COUNT(*) INTO count_montq
	 FROM sinhvienmondadat svmdd JOIN dieukientienquyet dktq ON svmdd.MaMH = dktq.MaTQ
	 WHERE svmdd.MSSV = mssv
	 AND dktq.MaMH = mamh_lophoc;
	 
	 IF count_montq < total_montq THEN 
	 SIGNAL SQLSTATE '45000'
	 SET MESSAGE_TEXT = 'Chua hoan thanh tat ca cac mon tien quyet';
	 END IF;
 END IF;
END; //
DELIMITER ;


-- Xóa các môn vào thì tự động cập nhật số tín chỉ trong phieudangky
DELIMITER //
CREATE TRIGGER cal_tongtinchi_after_update BEFORE DELETE ON phieudangkylophoc
FOR EACH ROW
BEGIN
 DECLARE mamh_lophoc VARCHAR(30);
 -- Lấy mã môn học của lớp học được thêm vào
 SELECT MaMH INTO mamh_lophoc
 FROM lophoc lh 
 WHERE lh.MaLH = OLD.MaLH;
 
 UPDATE phieudangky AS pdk
 SET TongTinChi = TongTinChi - (SELECT SoTinChi FROM monhoc AS mh 
								WHERE mh.MaMH = mamh_lophoc)
 WHERE pdk.MaPDK = OLD.MaPDK;
 
END; //
DELIMITER ;
