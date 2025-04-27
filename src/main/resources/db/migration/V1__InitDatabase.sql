create database if not exists dkhp;
use dkhp;

CREATE TABLE roles(
	Id INT PRIMARY KEY,
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
    FOREIGN KEY (MaNganh) REFERENCES chuyennganh(MaNganh),
);

CREATE TABLE PHIEUDANGKY(
	MaPDK VARCHAR(30) PRIMARY KEY,
    HocKi INT not null,
    NamHoc VARCHAR(30) not null,
    TongTinChi INT not null
    MSSV VARCHAR(30) not null,
    foreign key (MSSV) references sinhvien(Id),
);

CREATE TABLE monhoc(
	MaMH VARCHAR(30) PRIMARY KEY,
    TenMH VARCHAR(100) not null unique,
    SoTinChi INT not null,
    MaKhoa VARCHAR(30) not null,
    SoLuongSinhVienToiDa int not null,
    foreign key (MaKhoa) references khoa(MaKhoa)
);

-- MaGV là ID của GiangVien
CREATE TABLE LOPHOC(
	MaLH VARCHAR(30) PRIMARY KEY,
    NgayBatDau DATE not null,
    NgayKetThuc DATE not null,
    HocKi INT not null,
    NamHoc VARCHAR(30) not null,
    SoLuongSinhVien INT not null,
    MaGV VARCHAR(30) not null,
    foreign key (MaGV) references giangvien(Id),
    MaMH VARCHAR(30) not null,
    foreign key (MaMH) references monhoc(MaMH),
    TietBatDau INT not null,
    TietKetThuc INT not null,
    Thu INT not null,
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
	Id int primary key,
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
	Id int primary key,
	MaPDK VARCHAR(30) not null,
    MaLH VARCHAR(30) not null,
    foreign key (MaPDK) references phieudangky(MaPDK),
    foreign key (MaLH) references lophoc(MaLH),
    unique key unique_pdk_lh (MaPDK,MaLH)
);




