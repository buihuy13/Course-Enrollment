package CNTTK18.JobBE.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import CNTTK18.JobBE.Models.LopHoc;
import CNTTK18.JobBE.Repositories.ClassRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassService {
    private final ClassRepo classRepo;

    public ClassService(ClassRepo classRepo) {
        this.classRepo = classRepo;
    }

    public List<LopHoc> getAllClasses() {
        return classRepo.findAll();        
    }

    public LopHoc getClassById(String id) {
        LopHoc lophoc = classRepo.findClassById(id);
        if (lophoc == null) {
            throw new EntityNotFoundException("Class not found with id: " + id);
        }

        return lophoc;
    }
}
