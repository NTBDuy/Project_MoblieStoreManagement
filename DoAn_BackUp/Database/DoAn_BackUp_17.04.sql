-- TẠO DATABASE
GO
CREATE DATABASE DoAn_PRO1041

-- SỬ DỤNG DATABASE DoAn_PRO1041
GO
USE DoAn_PRO1041

-- TẠO BẢNG NHÂN VIÊN
GO
CREATE TABLE NHANVIEN
(
	MaNV		NCHAR(50)			PRIMARY KEY,
	TenNV		NVARCHAR(50)	NOT NULL,
	GioiTinh	BIT				null,
	NgaySinh	DATE			NULL,
	DiaChi      NVARCHAR(100)   NULL,
	Email		NVARCHAR(100)	NULL,
	SoDT        NVARCHAR(10)    NULL,
	CMND		NVARCHAR(20)	NULL,
	ChucVu		NVARCHAR(20)	NULL,
	TrangThai   NVARCHAR(50)    NULL,
	Hinh		NVARCHAR(50)	NULL
)

-- TẠO BẢNG TÀI KHOẢN
GO
CREATE TABLE TAIKHOAN
(
	TenTK		NCHAR(50)			PRIMARY KEY,
	MatKhau		NCHAR(50)		NOT NULL,
	Quyen		NCHAR(50)		NOT NULL,
	MaNV		NCHAR(50)		NULL FOREIGN KEY REFERENCES NHANVIEN(MaNV)
)

-- TẠO BẢNG KHÁCH HÀNG 
GO
CREATE TABLE KHACHHANG
(
	MaKH		NCHAR(50)			PRIMARY KEY,
	TenKH		NVARCHAR(50)	NOT NULL,
	GioiTinh	BIT				NULL,
	NgaySinh	DATE			NULL,
	CMND		NVARCHAR(20)	NULL,
	SoDT        NVARCHAR(10)    NULL,
	DiaChi      NVARCHAR(100)   NULL,
	Email		NVARCHAR(100)	NULL,
	GhiChu      NVARCHAR(200)   NULL
)

-- TẠO BẢNG SẢN PHẨM
GO
CREATE TABLE SANPHAM
(
	MaSP		NCHAR(50)			PRIMARY KEY,
	TenSP		NVARCHAR(50)	NOT NULL,
	NhaSX	    NVARCHAR(50)    NULL,
	DacDiem     NVARCHAR(50)    NULL,
	SoLuong     INT             NULL,
	GiaNhap     DECIMAL(15,0)   NULL,
	GiaBan      DECIMAL(15,0)	NULL,
	NgayNhap	DATE			NULL,
	HinhAnh     NVARCHAR(50)    NULL
)

-- TẠO BẢNG HOÁ ĐƠN
GO
CREATE TABLE HoaDon
(
	MaHD			NCHAR(50)			PRIMARY KEY,
	MaKH			NCHAR(50)		NULL FOREIGN KEY REFERENCES KHACHHANG(MaKH),
	MaNV			NCHAR(50)		NULL FOREIGN KEY REFERENCES NHANVIEN(MaNV),
	NgayLap			DATE			NULL,
	TongTien		DECIMAL(15,3)	NULL,
	GhiChu			NVARCHAR(100)	NULL
)

-- TẠO BẢNG CHI TIẾT HOÁ ĐƠN
GO
CREATE TABLE CTHoaDon
(
	MaHD			NCHAR(50)		NOT NULL FOREIGN KEY REFERENCES HoaDon(MaHD),
	MaSP			NCHAR(50)		NOT NULL FOREIGN KEY REFERENCES SANPHAM(MaSP),
	TenSP			NVARCHAR(50)	NULL,
	SoLuong			INT				NULL,
	DonGia			DECIMAL(15,0)	NULL,
	GiamGia			DECIMAL(2,0)	NULL,
	ThanhTien		DECIMAL(15,0)	NULL,
	CONSTRAINT PK_CTHoaDon PRIMARY KEY (MaHD,MaSP)
)

-- HÀM CHUYỂN DATA TỪ DATABASE NÀY SANG DATABASE KHÁC NẾU CÓ CHỈNH SỬA
INSERT INTO DoAn_PRO1041.dbo.TAIKHOAN(TenTK, MatKhau, Quyen, MaNV)
SELECT TenTK, MatKhau, Quyen, MaNV FROM DoAn_PRO1041_1.dbo.TAIKHOAN

-- HÀM THÊM DỮ LIỆU VÀO BẢNG TÀI KHOẢN
INSERT INTO TaiKhoan(TenTK, MatKhau, Quyen, MaNV)
VALUES	(N'admin', N'admin', N'QuanLy', null)

-- HÀM TẠO MÃ SẢN PHẨM MỚI TRÁNH BỊ TRÙNG MÃ 
GO
CREATE FUNCTION AutoID_SP()
RETURNS NCHAR(50)
AS
BEGIN
	DECLARE @ID NCHAR(50)
	IF (SELECT COUNT(MaSP) FROM dbo.SANPHAM) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(RTRIM(MaSP),2)) FROM SANPHAM
		SELECT @ID = CASE
				WHEN @ID >= 0 AND @ID < 9 THEN 'SP0000' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 9 AND @ID < 99 THEN 'SP000' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 99 AND @ID < 999 THEN 'SP00' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 999 AND @ID < 9999 THEN 'SP0' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 9999 THEN 'SP' + CONVERT(CHAR,CONVERT(INT,@ID)+1)
		             END
	RETURN UPPER(@ID)	
END

-- HÀM TẠO MÃ NHÂN VIÊN MỚI TRÁNH BỊ TRÙNG MÃ 
GO
CREATE FUNCTION AutoID_NV()
RETURNS NCHAR(50)
AS
BEGIN
	DECLARE @ID NCHAR(50)
	IF (SELECT COUNT(MaNV) FROM dbo.NHANVIEN) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(RTRIM(MaNV),2)) FROM NHANVIEN
		SELECT @ID = CASE
				WHEN @ID >= 0 AND @ID < 9 THEN 'NV0000' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 9 AND @ID < 99 THEN 'NV000' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 99 AND @ID < 999 THEN 'NV00' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 999 AND @ID < 9999 THEN 'NV0' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 9999 THEN 'NV' + CONVERT(CHAR,CONVERT(INT,@ID)+1)
		             END
	RETURN UPPER(@ID)	
END

-- HÀM TẠO MÃ KHÁCH HÀNG MỚI TRÁNH BỊ TRÙNG MÃ 
GO
CREATE FUNCTION AutoID_KH()
RETURNS NCHAR(50)
AS
BEGIN
	DECLARE @ID NCHAR(50)
	IF (SELECT COUNT(MaKH) FROM dbo.KHACHHANG) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(RTRIM(MaKH),2)) FROM KHACHHANG
		SELECT @ID = CASE
				WHEN @ID >= 0 AND @ID < 9 THEN 'KH0000' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 9 AND @ID < 99 THEN 'KH000' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 99 AND @ID < 999 THEN 'KH00' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 999 AND @ID < 9999 THEN 'KH0' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 9999 THEN 'KH' + CONVERT(CHAR,CONVERT(INT,@ID)+1)
		             END
	RETURN UPPER(@ID)	
END

-- HÀM TẠO MÃ HOÁ ĐƠN MỚI TRÁNH BỊ TRÙNG MÃ 
GO
CREATE FUNCTION AutoID_HD()
RETURNS NCHAR(50)
AS
BEGIN
	DECLARE @ID NCHAR(50)
	IF (SELECT COUNT(MaHD) FROM dbo.HoaDon) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(RTRIM(MaHD),2)) FROM HoaDon
		SELECT @ID = CASE
				WHEN @ID >= 0 AND @ID < 9 THEN 'HD0000' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 9 AND @ID < 99 THEN 'HD000' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 99 AND @ID < 999 THEN 'HD00' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 999 AND @ID < 9999 THEN 'HD0' + CONVERT(CHAR,CONVERT(INT,@ID) + 1)
				WHEN @ID >= 9999 THEN 'HD' + CONVERT(CHAR,CONVERT(INT,@ID)+1)
		             END
	RETURN UPPER(@ID)	
END

-- Trigger cập nhật số lượng mặt hàng khi thêm, sửa, xóa chi tiết hóa đơn
GO
CREATE TRIGGER Before_XoaCTHoaDon ON CTHOADON
FOR DELETE
AS
	UPDATE dbo.SANPHAM SET SoLuong = dbo.SANPHAM.SoLuong + Deleted.SoLuong FROM DELETED
	WHERE dbo.SANPHAM.MaSP = DELETED.MaSP

GO
CREATE TRIGGER Before_SuaCTHoaDon ON dbo.CTHOADON
FOR UPDATE
AS
	UPDATE dbo.SANPHAM SET SoLuong = a.SoLuong + b.SoLuong - c.SoLuong
	FROM dbo.SANPHAM a, DELETED b, INSERTED c
	WHERE a.MaSP = b.MaSP AND a.MaSP = c.MaSP

GO
CREATE TRIGGER Before_ThemCTHoaDon ON dbo.CTHOADON
FOR INSERT
AS
	UPDATE dbo.SANPHAM SET SoLuong = dbo.SANPHAM.SoLuong - INSERTED.SoLuong FROM INSERTED
	WHERE dbo.SANPHAM.MaSP = INSERTED.MaSP


-- HÀM TẠO FUNCTION TỔNG TIỀN BÁN HÀNG
GO
CREATE FUNCTION TienBanHang(@Time DATE, @MaNV NCHAR(50))
RETURNS MONEY
AS
BEGIN
	DECLARE @TienBanHang MONEY = 0
	IF (@MaNV IS NULL)
		SELECT @TienBanHang = SUM(TongTien) FROM dbo.HoaDon WHERE CONVERT(DATE,dbo.HoaDon.NgayLap,103) = @Time
	ELSE
		SELECT @TienBanHang = SUM(TongTien) FROM dbo.HoaDon WHERE CONVERT(DATE,dbo.HoaDon.NgayLap,103) = @Time AND MaNV = @MaNV
	RETURN @TienBanHang
END

-- HÀM TẠO FUNCTION TỔNG SỐ LƯỢNG SẢN PHẨM BÁN RA
GO
CREATE FUNCTION TinhTongSL(@Time DATE, @MaNV NCHAR(50))
RETURNS MONEY
AS
BEGIN
	DECLARE @TongSL INT = 0
	IF (@MaNV IS NULL)
		SELECT @TongSL = SUM(SoLuong) FROM dbo.HoaDon JOIN dbo.CTHoaDon ON CTHoaDon.MaHD = HoaDon.MaHD WHERE CONVERT(DATE,dbo.HoaDon.NgayLap,103) = @Time
	ELSE
		SELECT @TongSL = SUM(SoLuong) FROM dbo.HoaDon JOIN dbo.CTHoaDon ON CTHoaDon.MaHD = HoaDon.MaHD WHERE CONVERT(DATE,dbo.HoaDon.NgayLap,103) = @Time AND MaNV = @MaNV
	RETURN @TongSL
END

 -- Thủ tục lấy thông tin Hóa Đơn
GO
CREATE PROC HoaDon_XuatThongTin
	@MaHD	NCHAR(50)
AS
BEGIN
	SELECT ROW_NUMBER() OVER(ORDER BY CTHD.MASP) AS STT, KH.TenKH, KH.SoDT, KH.DiaChi, HD.NgayLap, HD.MaNV, CTHD.MaHD, SP.TenSP, CTHD.SoLuong, HD.TongTien,
		   CTHD.DonGia, CTHD.GiamGia, KH.MaKH, CTHD.ThanhTien
	FROM HoaDon HD JOIN KhachHang KH
		ON HD.MaKH = KH.MaKH JOIN CTHoaDon CTHD
		ON HD.MaHD = CTHD.MaHD JOIN SANPHAM SP
		ON CTHD.MaSP = SP.MaSP JOIN NhanVien NV
		ON NV.MaNV = HD.MaNV
	WHERE HD.MaHD = @MaHD
END

-- Thủ tục tính lợi nhuận trong ngày
GO
CREATE FUNCTION TinhLoiNhuanNgay(@Time DATE,  @MaNV NCHAR(50))
RETURNS MONEY
AS
BEGIN
	DECLARE @TongLN MONEY
	IF (@MaNV IS NULL)
		SELECT @TongLN = SUM(((CTHD.ThanhTien/CTHD.SoLuong)-SP.GiaNhap)*CTHD.SoLuong) FROM HoaDon HD JOIN KhachHang KH
		ON HD.MaKH = KH.MaKH JOIN CTHoaDon CTHD
		ON HD.MaHD = CTHD.MaHD JOIN SANPHAM SP
		ON CTHD.MaSP = SP.MaSP JOIN NhanVien NV
		ON NV.MaNV = HD.MaNV
		WHERE CONVERT(DATE,HD.NgayLap,103) = @Time
	ELSE
		SELECT @TongLN = SUM(((CTHD.ThanhTien/CTHD.SoLuong)-SP.GiaNhap)*CTHD.SoLuong) FROM HoaDon HD JOIN KhachHang KH
		ON HD.MaKH = KH.MaKH JOIN CTHoaDon CTHD
		ON HD.MaHD = CTHD.MaHD JOIN SANPHAM SP
		ON CTHD.MaSP = SP.MaSP JOIN NhanVien NV
		ON NV.MaNV = HD.MaNV
		WHERE CONVERT(DATE,HD.NgayLap,103) = @Time AND NV.MaNV = @MaNV
	RETURN @TongLN
END

-- Thủ tục tính lợi nhuận từ ngày tới ngày
GO
CREATE FUNCTION TinhLoiNhuanDaytoday(@TimeBD DATE, @TimeKT DATE)
RETURNS MONEY
AS
BEGIN
	DECLARE @TongLN MONEY
		SELECT @TongLN = SUM(((CTHD.ThanhTien/CTHD.SoLuong)-SP.GiaNhap)*CTHD.SoLuong) FROM HoaDon HD JOIN KhachHang KH
		ON HD.MaKH = KH.MaKH JOIN CTHoaDon CTHD
		ON HD.MaHD = CTHD.MaHD JOIN SANPHAM SP
		ON CTHD.MaSP = SP.MaSP 
		WHERE CONVERT(DATE,HD.NgayLap,103) BETWEEN @TimeBD AND @TimeKT
	RETURN @TongLN
END

SELECT dbo.TinhLoiNhuanDaytoday('04/01/2021','04/17/2021')

-- Thủ tục tính lợi nhuận từ ngày tới ngày theo mã nhân viên
GO
CREATE FUNCTION TinhLoiNhuanDaytodayANDMaNV(@TimeBD DATE, @TimeKT DATE, @MaNV NCHAR(50))
RETURNS MONEY
AS
BEGIN
	DECLARE @TongLN MONEY
		SELECT @TongLN = SUM(((CTHD.ThanhTien/CTHD.SoLuong)-SP.GiaNhap)*CTHD.SoLuong) FROM HoaDon HD JOIN KhachHang KH
		ON HD.MaKH = KH.MaKH JOIN CTHoaDon CTHD
		ON HD.MaHD = CTHD.MaHD JOIN SANPHAM SP
		ON CTHD.MaSP = SP.MaSP JOIN NhanVien NV
		ON NV.MaNV = HD.MaNV
		WHERE CONVERT(DATE,HD.NgayLap,103) BETWEEN @TimeBD AND @TimeKT AND NV.MaNV = @MaNV 
	RETURN @TongLN
END

SELECT dbo.TinhLoiNhuanDaytodayANDMaNV('04/01/2021','04/17/2021',N'NV00001')


-- Thủ tục xuất doanh số bán hàng 
GO
CREATE PROC DoanhSoBanHangg
	@NgayBD DATE,
	@NgayKT DATE,
	@MaNV	NCHAR(50) = NULL
AS
BEGIN
	SET NOCOUNT ON
	/*Khai báo hai biến ngày bắt đầu và ngày kết thúc*/
	DECLARE @StartDate DATETIME,
			@EndDate DATETIME
	/*Chuyển dữ liệu tham số kiểu chuỗi định dạng dd/MM/yyyy thành ngày bắt đầu và ngày kết thúc*/
	SELECT  @StartDate = CONVERT(DATETIME,@NgayBD,103),
			@EndDate = CONVERT(DATETIME,@NgayKT,103)
	/*Tạo bảng tạm lưu các ngày từ ngày tăng dần ngày bắt đầu đến ngày kết thúc*/
	DECLARE @RunDate DATETIME
	SELECT  @RunDate = @EndDate

	DECLARE @TEMP TABLE(
        Ngay DATE,
		TongSL INT,
		TongLN MONEY,
		TongTien MONEY
	)

	WHILE @RunDate >= @StartDate
	BEGIN
		INSERT INTO @TEMP (Ngay,TongSL,TongLN,TongTien) SELECT CONVERT(DATE,@RunDate,103) AS Ngay, ISNULL(dbo.TinhTongSL(CONVERT(DATE,@RunDate,103), @MaNV),0) AS TongSL,
		ISNULL(dbo.TinhLoiNhuanNgay(CONVERT(DATE,@RunDate,103), @MaNV),0) AS TongLN,ISNULL(dbo.TienBanHang(CONVERT(DATE,@RunDate,103), @MaNV),0) AS TongTien
													FROM dbo.HoaDon HD							
		SET @RunDate = @RunDate - 1
	END

	/*Lấy kết quả từ SQL doanh số bán hàng theo từng ngày*/

	DECLARE @TEMP1 TABLE(
        Ngay DATE,
		TongSL INT,
		TongLN MONEY,
		TongTien MONEY
	)

	INSERT INTO @TEMP1 (Ngay,TongSL,TongLN,TongTien) SELECT DISTINCT * FROM @TEMP

	SELECT * FROM @TEMP1
	ORDER BY CONVERT(DATETIME,Ngay,103) DESC
END
