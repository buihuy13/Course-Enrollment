package CNTTK18.JobBE.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import CNTTK18.JobBE.Models.ChuyenNganh;

@Repository
public interface ChuyenNganhRepo extends JpaRepository<ChuyenNganh, String> {
    ChuyenNganh findChuyenNganhByMaNganh(String manganh);

}
