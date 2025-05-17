-- V1 --
CREATE TABLE roles(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    RoleName VARCHAR(50) not null unique
);

CREATE TABLE users(
	ID VARCHAR(30) PRIMARY KEY,
    Password VARCHAR(255) not null,
    Email VARCHAR(50) not null unique,
    HoTen VARCHAR(100) not null,
    NgaySinh DATE not null,
    GioiTinh VARCHAR(20) not null,
    RoleId INT not null,
    FOREIGN KEY (RoleId) REFERENCES roles(Id)
);

CREATE TABLE khoa(
	MaKhoa VARCHAR(30) PRIMARY KEY,
    TenKhoa VARCHAR(100) not null unique
);

CREATE TABLE admin(
	Id VARCHAR(30) PRIMARY KEY,
    FOREIGN KEY (Id) REFERENCES users(Id)
);

CREATE TABLE giangvien(
	MSGV VARCHAR(30) not null unique,
	Id VARCHAR(30) PRIMARY KEY,
    FOREIGN KEY (Id) REFERENCES users(Id),
    MaKhoa VARCHAR(30) not null,
    foreign key (MaKhoa) references khoa(MaKhoa)
);

CREATE TABLE chuyennganh(
	MaNganh VARCHAR(30) PRIMARY KEY,
    TenNganh VARCHAR(100) not null unique,
    MaKhoa VARCHAR(30) not null,
    foreign key (MaKhoa) references khoa(MaKhoa)
);

CREATE TABLE sinhvien(
	MSSV VARCHAR(30) not null unique,
	Id VARCHAR(30) PRIMARY KEY,
    FOREIGN KEY (Id) REFERENCES users(Id),
    MaNganh VARCHAR(30) not null,
    FOREIGN KEY (MaNganh) REFERENCES chuyennganh(MaNganh)
);

CREATE TABLE phieudangky(
	MaPDK VARCHAR(30) PRIMARY KEY,
    HocKi INT not null,
    NamHoc VARCHAR(30) not null,
    TongTinChi INT not null,
    MSSV VARCHAR(30) not null,
    foreign key (MSSV) references sinhvien(Id)
);

CREATE TABLE monhoc(
	MaMH VARCHAR(30) PRIMARY KEY,
    TenMH VARCHAR(100) not null unique,
    SoTinChi INT not null,
    MaKhoa VARCHAR(30) not null,
    foreign key (MaKhoa) references khoa(MaKhoa)
);

-- MaGV là ID của GiangVien
CREATE TABLE lophoc(
	MaLH VARCHAR(30) PRIMARY KEY,
    NgayBatDau DATE not null,
    NgayKetThuc DATE not null,
    HocKi INT not null,
    NamHoc VARCHAR(30) not null,
    SoLuongSinhVien INT not null,
    SoLuongSinhVienToiDa INT not null,
    MaGV VARCHAR(30) not null,
    foreign key (MaGV) references giangvien(Id),
    MaMH VARCHAR(30) not null,
    foreign key (MaMH) references monhoc(MaMH),
    TietBatDau INT not null,
    TietKetThuc INT not null,
    Thu INT not null
);

CREATE TABLE mondadat(
	MaMH VARCHAR(30) PRIMARY KEY,
    NamHoc VARCHAR(30) not null,
    HocKi INT not null,
    FOREIGN KEY (MaMH) references monhoc(MaMH)
);

CREATE TABLE montienquyet(
	MaMH VARCHAR(30) PRIMARY KEY,
    foreign key (MaMH) references monhoc(MaMH)
);

-- MSSV là id của sinhvien, không phải là mssv trong bảng SinhVien
CREATE TABLE sinhvienmondadat(
	Id int AUTO_INCREMENT primary key,
	MSSV VARCHAR(30) not null,
    MaMH VARCHAR(30) not null,
    FOREIGN KEY (MSSV) REFERENCES sinhvien(Id),
    foreign key (MaMH) references mondadat(MaMH),
    unique key unique_sv_mh (MSSV,MaMH)
);

CREATE TABLE dieukientienquyet(
	Id int AUTO_INCREMENT primary key,
	MaTQ VARCHAR(30),
    MaMH VARCHAR(30),
    foreign key (MaTQ) references montienquyet(MaMH),
    foreign key (MaMH) references monhoc(MaMH),
    unique key unique_mhtq_mh (MaTQ,MaMH)
);

CREATE TABLE phieudangkylophoc(
	Id int AUTO_INCREMENT primary key,
	MaPDK VARCHAR(30) not null,
    MaLH VARCHAR(30) not null,
    foreign key (MaPDK) references phieudangky(MaPDK),
    foreign key (MaLH) references lophoc(MaLH),
    unique key unique_pdk_lh (MaPDK,MaLH)
);

-- V2 --
-- fake data roles
insert into roles(`Id`, `RoleName`) values
 (1, "SINHVIEN"),
 (2, "GIANGVIEN"),
 (3, "ADMIN");

-- fake data users
insert into users(`ID`, `Password`, `Email`, `HoTen`, `NgaySinh`, `GioiTinh`, `RoleId`) values
 ("testsinhvienid", "$2a$12$gd7KsVdGEJcNpCfJkp80LuIx5aEzYAWM1zYWz/MOrBFGKTw8yOTyW", "testsinhvien@gmail.com", "TestSinhVien", "2005-4-13", "Nam", 1),
 ("testgiangvienid", "$2a$12$qy5RUOSWvlKUWsfRx3um3uCY4bs1YUhkdpMY3.xqqORZi45tge5Ke", "testgiangvien@gmail.com", "TestGiangVien", "2006-4-13", "Nu", 2),
 ("testadminid", "$2a$12$xv4.GmxuJeUUs54wJNwPdODdcvnHs7ikvpCuLeVVMy4tki5hZLq/m", "testadmin@gmail.com", "TestAdmin", "2004-4-13", "Nam", 3);
 
 -- fake data khoa
 insert into khoa (MaKhoa, TenKhoa) values
    ("CS", "Khoa hoc May tinh"),
    ("SE", "Cong nghe Phan mem"),
    ("CE", "Ky thuat May tinh"),
    ("IS", "He thong Thong tin"),
    ("MTTT", "Mang May tinh va Truyen thong"),
    ("ISE", "Khoa hoc va Ky thuat Thong tin");

-- fake data chuyennganh
insert into chuyennganh (MaNganh, TenNganh, MaKhoa) values
    ("KHMT", "Khoa hoc May tinh", "CS"),
    ("TTNT", "Tri tue Nhan tao", "CS"),
    ("KTPM", "Ky thuat Phan mem", "SE"),
    ("KTMT", "Ky thuat May tinh", "CE"),
    ("HTTT", "He thong Thong tin", "IS"),
    ("MTTT", "Mang May tinh va Truyen thong Du lieu", "MTTT"),
    ("ATTT", "An toan Thong tin", "MTTT"),
    ("TMDT", "Thuong mai Dien tu", "IS"),
    ("KHDL", "Khoa hoc Du lieu", "ISE"),
    ("CNTT", "Cong nghe Thong tin", "ISE"),
    ("TKVM", "Thiet ke Vi mach", "ISE");

-- fake data sinhvien
insert into sinhvien(Id, MSSV, MaNganh) values
 ("testsinhvienid", "23520593", "CNTT");

-- fake data giangvien
insert into giangvien(Id, MSGV, MaKhoa) values
 ("testgiangvienid", "12345678", "ISE");

-- fake data admin
insert into admin(Id) values ("testadminid");


-- V3 -- 
INSERT INTO monhoc(MaMH, TenMH, SoTinChi, MaKhoa) VALUES
('IT001', 'Nhap mon lap trinh', 4, 'CS'),
('IT002', 'Lap trinh huong doi tuong', 4, 'SE'),
('IT003', 'Cau truc du lieu va giai thuat', 4, 'CS'),
('IT004', 'Co so du lieu', 4, 'IS'),
('IT005', 'Nhap mon mang may tinh', 4, 'MTTT'),
('IT006', 'Kien truc may tính', 3, 'CE'),
('IT007', 'He dieu hanh', 4, 'CE'),
('IT010', 'To chu va cau truc may tinh', 2, 'CE'),
('IT012', 'To chu va cau truc may tinh II', 4, 'CE'),
('IE101', 'Co so ha tang cong nghe thong tin', 3, 'ISE'),
('IE103', 'Quan ly thong tin', 4, 'ISE'),
('IE104', 'Internet va cong nghe Web', 4, 'ISE'),
('IE105', 'Nhap mon bao dam va an ninh thong tin', 4, 'ISE'),
('IE106', 'Thiet ke giao dien nguoi dung', 4, 'ISE'),
('IE108', 'Phan tich thiet ke phan mem', 4, 'ISE'),
('IS402', 'Dien toan dam may', 4, 'ISE'),
('IE102', 'Cac cong nghe nen', 2, 'ISE'),
('IE212', 'Cong nghe Du lieu lon', 3, 'ISE'),
('IE303', 'Cong nghe Java', 3, 'ISE'),
('IE207', 'Do an', 3, 'ISE'),
('DS204', 'Do an khoa hoc du lieu va ung dung', 3, 'ISE'),
('IE402', 'He thong thong tin dia ly 3 chieu', 3, 'ISE'),
('DS102', 'Hoc may thong ke', 3, 'ISE'),
('IE403', 'Khai thac du lieu truyen thong xa hoi', 3, 'ISE'),
('DS505', 'Khoa luan tot nghiep', 10, 'ISE'),
('IE213', 'Ky thuat phat trien he thong Web', 3, 'ISE'),
('DS200', 'Phan tich du lieu lon', 3, 'ISE'),
('DS304', 'Thiet ke va phan tich thuc nghiem', 3, 'ISE'),
('DS309', 'Thuc tap doanh nghiep', 2, 'ISE'),
('IE204', 'Toi uu hoa cong cu tim kiem', 3, 'ISE'),
('DS107', 'Tu duy tinh toan cho Khoa hoc du lieu', 3, 'ISE'),
('DS312', 'Xu ly anh y khoa', 3, 'ISE'),
('DS313', 'Xu ly thong tin giong noi', 3, 'ISE'),
('EC335', 'An toan va bao mat thuong mai dien tu', 3, 'IS'),
('IS211', 'Co so du lieu phan tan', 3, 'IS'),
('IS405', 'Du lieu lon', 3, 'IS'),
('IS210', 'He quan tri co so du lieu', 3, 'IS'),
('IS349', 'He thong y te', 3, 'IS'),
('IS336', 'Hoach dinh nguon luc doanh nghiep', 3, 'IS'),
('EC001', 'Kinh te hoc dai cuong', 4, 'IS'),
('IS216', 'Lap trinh Java', 3, 'IS'),
('IS353', 'Mang xa hoi', 3, 'IS'),
('EC101', 'Marketing can ban', 3, 'IS'),
('EC204', 'Marketing dien tu', 3, 'IS'),
('EC214', 'Nhap mon Quan tri chuoi cung ung', 3, 'IS'),
('IS403', 'Phan tich du lieu kinh doanh', 3, 'IS'),
('IS201', 'Phan tich thiet ke he thong thong tin', 3, 'IS'),
('EC201', 'Phan tich thiet ke quy trinh nghiep vu doanh nghiep', 3, 'IS'),
('EC229', 'Phap luat trong thuong mai dien tu', 3, 'IS'),
('EC402', 'Phat trien ung dung thuong mai di dong', 3, 'IS'),
('IS207', 'Phat trien ung dung web', 3, 'IS'),
('IS208', 'Quan ly du an cong nghe thong tin', 3, 'IS'),
('EC208', 'Quan ly du an TMDT', 3, 'IS'),
('EC312', 'Thiet ke he thong thuong mai dien tu', 3, 'IS'),
('EC403', 'Thuong mai xa hoi', 3, 'IS');


-- V4 -- 

INSERT INTO users (Id, `Password`, Email, HoTen, NgaySinh, GioiTinh, RoleId) VALUES
('U001', '$2a$12$wIb5RWMZplRmWVuHjN2uZeCMt1rk4WssDRHHV3eiPv.n67CCwj0YC', 'ngocle@example.com', 'Ngoc Le', '1990-01-01', 'Female', 2),
('U002', '$2a$12$BCYetnf68YinYC69Qs0Cd.qKonudBS8ixfIw.XHzfVtIhqE5utQ3i', 'minhtran@example.com', 'Minh Tran', '1992-03-15', 'Male', 2),
('U003', '$2a$12$qcyxUTloxOA03OssD8gka.66RhwjPC28RpfsFYrOeuSuJEL9/pcHO', 'hoapham@example.com', 'Hoa Pham', '1995-07-25', 'Female', 2),
('U004', '$2a$12$qpKviH7UBQ010omRSPiVleB/L/Dw5jTKfNjb4YPtx/zkpjOw0Q9QO', 'huyvo@example.com', 'Huy Vo', '1988-05-14', 'Male', 2),
('U005', '$2a$12$PEBsvGW.eSbXW7JZspwWjOZQS6H1zjOUcpEX/zrz9L87Lywzs/l.K', 'thunguyen@example.com', 'Thu Nguyen', '1993-09-09', 'Female', 2),
('U006', '$2a$12$LpRE746XtobgcePninRDXe3OICkWCae58.PB5rTEiFDosLrXVk2DG', 'namdo@example.com', 'Nam Do', '1989-11-30', 'Male', 2),
('U007', '$2a$12$q/Icb1GLMwv/aK11ecHBFenPt2raJcRTMuaU6tif76UZechIDMH6i', 'anhho@example.com', 'Anh Ho', '1991-02-22', 'Female', 2),
('U008', '$2a$12$BPUjMWlm7LH1RB5B0ESS8u/SIcxbXGC1AvtaoQp/6GNHnZpZA1HDC', 'khanhle@example.com', 'Khanh Le', '1994-04-18', 'Male', 2),
('U009', '$2a$12$6VMVKt.S6ij.X0cj7Lt1HeKEvIaeKuOEBXxabyA/DkoqHetnrNazu', 'tuyetvo@example.com', 'Tuyet Vo', '1997-06-12', 'Female', 2),
('U010', '$2a$12$oQ4KFgv4/zqhovCmhCkJ.upsjJOLQyDzPcgWgtsPKB9apK7CP8226', 'linhnguyen@example.com', 'Linh Nguyen', '1990-08-20', 'Female', 2),
('U011', '$2a$12$ptdWt.Ptjw9uZrXlyAAg4edc/DUGYT5O/NnE0StfOt9lBKx3vIDaG', 'quangtran@example.com', 'Quang Tran', '1987-01-01', 'Male', 1),
('U012', '$2a$12$KiVu1Xb6t6hnK57dkiHDX.kT2s7Ac6IlXy1masutqk.YVWKWl31km', 'maipham@example.com', 'Mai Pham', '1996-05-10', 'Female', 1),
('U013', '$2a$12$rPLSOu4f/vKrCJtiBz21ZuwIdRG.gIxmCSDyFIjpHDGSVywi4.O6G', 'thienvo@example.com', 'Thien Vo', '1993-03-21', 'Male', 1),
('U014', '$2a$12$bIp5YnHVzoKy5k7jSZPzC.nBf7pspD0HDLajC6eU.dGD5Sj2308fG', 'yendo@example.com', 'Yen Do', '1989-07-19', 'Female', 1),
('U015', '$2a$12$AB0ftqsyucUAwc7h0w65ROK91Vsvfus7JYv1j/e3AvDe0oVIvws7q', 'vinhho@example.com', 'Vinh Ho', '1992-09-17', 'Male', 1),
('U016', '$2a$12$o5M55G61XQycjAHNKaTzb.OV5ICyENh5/pQyrJldSGBFVA7ZfKZ7u', 'ngale@example.com', 'Nga Le', '1994-12-25', 'Female', 1),
('U017', '$2a$12$JE2RN7VYzjyLeEncxz.h6Of6zjbsUj6ebVGyzwAgjdR/K2CXdTix6', 'phucnguyen@example.com', 'Phuc Nguyen', '1997-02-14', 'Male', 1),
('U018', '$2a$12$4lGeXl3Kk2d9f9wdC4eO9.lHOmFjOXZiMwgy2OwVzh/WhWk64ZC7.', 'trangvo@example.com', 'Trang Vo', '1985-04-12', 'Female', 1),
('U019', '$2a$12$XOyvIc1DakHGymJsXSmdPehfyoZ9fQJ0x1c5zbViXRp/TD/vWm3wq', 'baotran@example.com', 'Bao Tran', '1988-06-28', 'Male', 1),
('U020', '$2a$12$lLlKwCFHt0n0ZUDLOC2AXektMfZVfwFhHuCu3pXam.EpGwubH93rm', 'nganpham@example.com', 'Ngan Pham', '1991-08-03', 'Female', 1);

-- insert giangvien
INSERT INTO giangvien (MSGV, Id, MaKhoa) VALUES
('23412', 'U001', 'SE'),
('546435', 'U002', 'IS'),
('645376', 'U003', 'CS'),
('64564', 'U004', 'ISE'),
('3423', 'U005', 'CE'),
('78657', 'U006', 'MTTT'),
('76533', 'U007', 'SE'),
('54636', 'U008', 'IS'),
('25345', 'U009', 'CS'),
('56436', 'U010', 'ISE');

INSERT INTO sinhvien (MSSV, Id, MaNganh) VALUES
('645432', 'U011', 'KHMT'),
('2436543', 'U012', 'TTNT'),
('523452', 'U013', 'KTPM'),
('76354', 'U014', 'KTMT'),
('65236', 'U015', 'HTTT'),
('5246234', 'U016', 'MTTT'),
('43255', 'U017', 'ATTT'),
('423523', 'U018', 'TMDT'),
('4253487', 'U019', 'KHDL'),
('13221', 'U020', 'CNTT');

-- V5 --

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

 -- Kiểm tra sinh viên có tồn tại hay không
 IF mssv IS NULL THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'MSSV không tồn tại';
 END IF;
 
 -- Lấy mã môn học của lớp học được thêm vào
 SELECT MaMH INTO mamh_lophoc
 FROM lophoc lh 
 WHERE lh.MaLH = NEW.MaLH;
 
    -- Kiểm tra môn học có tồn tại hay không
 IF mamh_lophoc IS NULL THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Mã lớp học không tồn tại';
 END IF;
 
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
 SELECT lh.SoLuongSinhVienToiDa INTO soluongsinhvientoida
 FROM lophoc as lh
 WHERE lh.MaLH = NEW.MaLH;
 
 -- Lấy số lượng sinhvien của lớp học
 SELECT lh.SoLuongSinhVien INTO soluongsinhvien
 FROM lophoc as lh
 WHERE lh.MaLH = NEW.MaLH;
 
 -- Kiểm tra số lượng sinhvien theo lớp đó đã quá mức hay chưa
 IF soluongsinhvien >= soluongsinhvientoida THEN
 SIGNAL SQLSTATE '45000'
 SET MESSAGE_TEXT = 'Đã đủ sinh viên cho lớp học';
 END IF;
 
 -- Tăng số lượng sinhvien của lớp học đó sau khi đăng ký thành công
 UPDATE lophoc AS lh
 SET lh.SoLuongSinhVien = IFNULL(lh.SoLuongSinhVien, 0) + 1
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
 
 -- Trừ tín chỉ của pdk
 UPDATE phieudangky AS pdk
 SET TongTinChi = TongTinChi - (SELECT SoTinChi FROM monhoc AS mh 
								WHERE mh.MaMH = mamh_lophoc)
 WHERE pdk.MaPDK = OLD.MaPDK;

 -- Trừ số lượng sinh viên của lớp học
 UPDATE lophoc AS lh
 SET lh.SoLuongSinhVien = lh.SoLuongSinhVien - 1
 WHERE lh.MaLH = OLD.MaLH;
 
END; //
DELIMITER ;
