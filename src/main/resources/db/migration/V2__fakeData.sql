
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