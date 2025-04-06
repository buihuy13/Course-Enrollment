
INSERT INTO Users (id, password, email, hoten, ngaysinh, gioitinh, roleid) VALUES
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
INSERT INTO giangvien (msgv, id, makhoa) VALUES
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

INSERT INTO sinhvien (mssv, id, manganh) VALUES
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