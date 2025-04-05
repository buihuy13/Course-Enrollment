package CNTTK18.JobBE.Services;

import java.util.ArrayList;
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

    // public void processExcelFile(MultipartFile file) throws IOException {
    //     try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
    //         Sheet sheet = workbook.getSheetAt(0);
            
    //         // Bỏ qua dòng header
    //         Iterator<Row> rows = sheet.iterator();
    //         if (rows.hasNext()) {
    //             rows.next(); // Skip header row
    //         }
            
    //         List<LopHoc> classes = new ArrayList<>();
            
    //         while (rows.hasNext()) {
    //             Row currentRow = rows.next();
                
    //             // Giả sử Entity có các trường id, name, description
    //             LopHoc lophoc = new LopHoc();
                
    //             // Đọc dữ liệu từ các ô trong hàng hiện tại
    //             if (currentRow.getCell(0) != null) {
    //                 entity.setId((long) currentRow.getCell(0).getNumericCellValue());
    //             }
                
    //             if (currentRow.getCell(1) != null) {
    //                 entity.setName(currentRow.getCell(1).getStringCellValue());
    //             }
                
    //             if (currentRow.getCell(2) != null) {
    //                 entity.setDescription(currentRow.getCell(2).getStringCellValue());
    //             }
                
    //             entities.add(entity);
    //         }
            
    //         // Lưu danh sách entities vào database
    //         entityRepository.saveAll(entities);
    //     }
    // }
}
