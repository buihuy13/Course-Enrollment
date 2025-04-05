package CNTTK18.JobBE.Services;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import CNTTK18.JobBE.DTO.Class.ClassDTO;
import CNTTK18.JobBE.Models.GiangVien;
import CNTTK18.JobBE.Models.LopHoc;
import CNTTK18.JobBE.Models.MonHoc;
import CNTTK18.JobBE.Repositories.ClassRepo;
import CNTTK18.JobBE.Repositories.GiangVienRepo;
import CNTTK18.JobBE.Repositories.MonHocRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassService {
    private final ClassRepo classRepo;
    private final GiangVienRepo giangVienRepo;
    private final MonHocRepo monHocRepo;

    public ClassService(ClassRepo classRepo, GiangVienRepo giangVienRepo, MonHocRepo monHocRepo) {
        this.classRepo = classRepo;
        this.giangVienRepo = giangVienRepo;
        this.monHocRepo = monHocRepo;
    }

    public List<LopHoc> getAllClasses() {
        return classRepo.findAll();        
    }

    public LopHoc getClassById(String id) {
        LopHoc lophoc = classRepo.findLopHocByMaLH(id);
        if (lophoc == null) {
            throw new EntityNotFoundException("Class not found with id: " + id);
        }

        return lophoc;
    }

    public void updateClassById(String id, ClassDTO updatedClass) {
        LopHoc existingClass = classRepo.findLopHocByMaLH(id);
        if (existingClass == null) {
            throw new EntityNotFoundException("Class not found with id: " + id);
        }
        GiangVien giangVien = giangVienRepo.findGiangVienById(updatedClass.getMagv());
        if (giangVien == null) {
            throw new EntityNotFoundException("GiangVien not found with id: " + updatedClass.getMagv());
        }
        MonHoc monHoc = monHocRepo.findMonHocByMaMH(updatedClass.getMamh());
        if (monHoc == null) {
            throw new EntityNotFoundException("MonHoc not found with id: " + updatedClass.getMamh());
        }

        existingClass.setMonHoc(monHoc);
        existingClass.setGiangVien(giangVien);
        existingClass.setNgayBatDau(updatedClass.getNgayBatDau());
        existingClass.setNgayKetThuc(updatedClass.getNgayKetThuc());

        classRepo.save(existingClass);
    }

    public void deleteClassById(String id) {
        LopHoc existingClass = classRepo.findLopHocByMaLH(id);
        if (existingClass == null) {
            throw new EntityNotFoundException("Class not found with id: " + id);
        }
        classRepo.delete(existingClass);
    }

    public void processExcelFile(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            
            // Bỏ qua dòng header
            Iterator<Row> rows = sheet.iterator();
            if (rows.hasNext()) {
                rows.next(); // Skip header row
            }
            
            List<LopHoc> classes = new ArrayList<>();
            
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                
                // Giả sử Entity có các trường id, name, description
                LopHoc lophoc = new LopHoc();
                
                // Đọc dữ liệu từ các ô trong hàng hiện tại
                if (currentRow.getCell(1) != null) {
                    MonHoc monHoc = monHocRepo.findMonHocByMaMH(currentRow.getCell(1).getStringCellValue());
                    if (monHoc == null) {
                        throw new EntityNotFoundException("MonHoc not found with id: " + currentRow.getCell(1).getStringCellValue());
                    }
                    lophoc.setMonHoc(monHoc);
                }
                
                if (currentRow.getCell(2) != null) {
                    lophoc.setMaLH(currentRow.getCell(2).getStringCellValue());
                }
                
                if (currentRow.getCell(4) != null) {
                    GiangVien giangVien = giangVienRepo.findGiangVienById(currentRow.getCell(4).getStringCellValue());
                    if (giangVien == null) {
                        throw new EntityNotFoundException("GiangVien not found with id: " + currentRow.getCell(4).getStringCellValue());
                    }
                    lophoc.setGiangVien(giangVien);
                }

                if (currentRow.getCell(6) != null) {
                    lophoc.setSoLuongSinhVien((int) currentRow.getCell(6).getNumericCellValue());
                }

                //Tong so tin chi cua lop do
                // if (currentRow.getCell(7) != null) {
                //     lophoc.set(currentRow.getCell(7).getStringCellValue());
                // }

                //hoc vao thu may
                // if (currentRow.getCell(8) != null) {
                //     lophoc.setNgayBatDau(currentRow.getCell(8).getDateCellValue());
                // }

                //so tiet hoc 
                // if (currentRow.getCell(9) != null) {
                //     lophoc.setNgayKetThuc(currentRow.getCell(9).getDateCellValue());
                // }

                //hoc ky
                if (currentRow.getCell(10) != null) {
                    lophoc.setHocKi((int) currentRow.getCell(10).getNumericCellValue());
                }

                //nam hoc
                // if (currentRow.getCell(11) != null) {
                //     lophoc.setNamHoc(currentRow.getCell(11).getStringCellValue());
                // }
                
                //ngay bat dau
                if (currentRow.getCell(12) != null) {
                    lophoc.setNgayBatDau((Date) currentRow.getCell(12).getDateCellValue());
                }

                //ngay ket thuc
                if (currentRow.getCell(13) != null) {
                    lophoc.setNgayKetThuc((Date) currentRow.getCell(13).getDateCellValue());
                }

                classes.add(lophoc);
            }
            
            // Lưu danh sách entities vào database
            classRepo.saveAll(classes);
        }
    }
}
