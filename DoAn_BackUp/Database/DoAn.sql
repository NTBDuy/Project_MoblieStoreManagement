USE [DoAn_PRO1041]
GO
/****** Object:  UserDefinedFunction [dbo].[AutoID_HD]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AutoID_HD]()
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
GO
/****** Object:  UserDefinedFunction [dbo].[AutoID_KH]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AutoID_KH]()
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

GO
/****** Object:  UserDefinedFunction [dbo].[AutoID_NV]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AutoID_NV]()
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


GO
/****** Object:  UserDefinedFunction [dbo].[AutoID_SP]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[AutoID_SP]()
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

GO
/****** Object:  UserDefinedFunction [dbo].[TienBanHang]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[TienBanHang](@Time DATE, @MaNV NCHAR(50))
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

GO
/****** Object:  UserDefinedFunction [dbo].[TinhLoiNhuanDaytoday]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[TinhLoiNhuanDaytoday](@TimeBD DATE, @TimeKT DATE)
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
GO
/****** Object:  UserDefinedFunction [dbo].[TinhLoiNhuanDaytodayANDMaNV]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[TinhLoiNhuanDaytodayANDMaNV](@TimeBD DATE, @TimeKT DATE, @MaNV NCHAR(50))
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
GO
/****** Object:  UserDefinedFunction [dbo].[TinhLoiNhuanNgay]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[TinhLoiNhuanNgay](@Time DATE,  @MaNV NCHAR(50))
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
GO
/****** Object:  UserDefinedFunction [dbo].[TinhTongSL]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[TinhTongSL](@Time DATE, @MaNV NCHAR(50))
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

GO
/****** Object:  Table [dbo].[CTHoaDon]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHoaDon](
	[MaHD] [nchar](50) NOT NULL,
	[MaSP] [nchar](50) NOT NULL,
	[TenSP] [nvarchar](50) NULL,
	[SoLuong] [int] NULL,
	[DonGia] [decimal](15, 0) NULL,
	[GiamGia] [decimal](2, 0) NULL,
	[ThanhTien] [decimal](15, 0) NULL,
 CONSTRAINT [PK_CTHoaDon] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC,
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHD] [nchar](50) NOT NULL,
	[MaKH] [nchar](50) NULL,
	[MaNV] [nchar](50) NULL,
	[NgayLap] [date] NULL,
	[TongTien] [decimal](15, 3) NULL,
	[GhiChu] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[MaKH] [nchar](50) NOT NULL,
	[TenKH] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NULL,
	[NgaySinh] [date] NULL,
	[CMND] [nvarchar](20) NULL,
	[SoDT] [nvarchar](10) NULL,
	[DiaChi] [nvarchar](100) NULL,
	[Email] [nvarchar](100) NULL,
	[GhiChu] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MaNV] [nchar](50) NOT NULL,
	[TenNV] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NULL,
	[NgaySinh] [date] NULL,
	[DiaChi] [nvarchar](100) NULL,
	[Email] [nvarchar](100) NULL,
	[SoDT] [nvarchar](10) NULL,
	[CMND] [nvarchar](20) NULL,
	[ChucVu] [nvarchar](20) NULL,
	[TrangThai] [nvarchar](50) NULL,
	[Hinh] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAM](
	[MaSP] [nchar](50) NOT NULL,
	[TenSP] [nvarchar](50) NOT NULL,
	[NhaSX] [nvarchar](50) NULL,
	[DacDiem] [nvarchar](50) NULL,
	[SoLuong] [int] NULL,
	[GiaNhap] [decimal](15, 0) NULL,
	[GiaBan] [decimal](15, 0) NULL,
	[NgayNhap] [date] NULL,
	[HinhAnh] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TAIKHOAN]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TAIKHOAN](
	[TenTK] [nchar](50) NOT NULL,
	[MatKhau] [nchar](50) NOT NULL,
	[Quyen] [nchar](50) NOT NULL,
	[MaNV] [nchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[TenTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CTHoaDon]  WITH CHECK ADD FOREIGN KEY([MaHD])
REFERENCES [dbo].[HoaDon] ([MaHD])
GO
ALTER TABLE [dbo].[CTHoaDon]  WITH CHECK ADD FOREIGN KEY([MaSP])
REFERENCES [dbo].[SANPHAM] ([MaSP])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([MaKH])
REFERENCES [dbo].[KHACHHANG] ([MaKH])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
ALTER TABLE [dbo].[TAIKHOAN]  WITH CHECK ADD FOREIGN KEY([MaNV])
REFERENCES [dbo].[NHANVIEN] ([MaNV])
GO
/****** Object:  StoredProcedure [dbo].[DoanhSoBanHangg]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[DoanhSoBanHangg]
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
GO
/****** Object:  StoredProcedure [dbo].[HoaDon_XuatThongTin]    Script Date: 12/23/2021 9:17:43 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[HoaDon_XuatThongTin]
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
GO
