create DATABASE QLNV
GO
use  QLNV
GO

create table Deparment(
					   ID int identity primary key,
					   Depname nvarchar(50) not null)
GO
create table chucvu(
				   ID int identity primary key,
				   Chucvu nvarchar(50) NOT NULL,
				   PhongBanID INT FOREIGN KEY REFERENCES dbo.Deparment(ID))
GO

create table Employees(
						ID int identity primary key,	
						FullName nvarchar(50) not null,
						Ngay_Sinh DATE ,
						age INT not null ,
						sex smallint not null , --0 là Nữ , 1 là Nam
						Email nvarchar(100) not null,
						[address] nvarchar(100)not null,
						phone varchar(15) not null,

						DepID int foreign key references Deparment(ID),
						ChucvuID int foreign key references chucvu(ID),
						TrangThai SMALLINT) --1 là còn đang làm,0 là đã nghỉ
GO


create table Acc(
				ID int IDENTITY primary KEY,
				idnv INT references dbo.Employees(ID)UNIQUE,
				acc nvarchar(50) UNIQUE not null ,
				pass nvarchar(50),
				type nvarchar(50) DEFAULT 0)
GO

create table TypeContract(
						TypeID int identity primary key,
						NameType nvarchar(50) not null)
GO

create table Contracts(
						ContractID int identity primary key,
						Salary double precision	not null,
						DayStar date not null,	
						DayEnd date not null,
						ID int foreign key references Employees(ID),
						TypeID int foreign key references TypeContract(TypeID))
GO


create table DieuDong(	
						ID int IDENTITY PRIMARY KEY ,
						idnv INT references dbo.Employees(ID),
						Ngay_Dieu_Dong date,
						PhongBanCu nvarchar(100), 
						PhongBanmoi nvarchar(100),
						ChucVuCu nvarchar(100),
						ChucVuMoi nvarchar(100),
						LyDo NVARCHAR(100))


create table attendance(
						idnv INT references dbo.Employees(ID),
						attendance_date date,
						first_time NVARCHAR(20),--0 là chưa điểm danh
						second_time NVARCHAR(20),
						note nvarchar(100))

GO


create table DKNghi(	
						ID int identity primary key,	
						IDNV INT REFERENCES Employees(ID),	
						NgaySinh DATE ,
						Phone varchar(15),	
						LoaiNghi nvarchar(50),
						ThoiHan nvarchar(50) NOT NULL,
						NgayNop DATE,
						NgayDuyet DATE,
						DepID NVARCHAR(50) ,						
						TrangThai SMALLINT )-- 0 là chưa duyệt,1 là đã duyệt
						
GO 

create table LichNghi(	
						STT INT identity PRIMARY KEY ,
						EmpID INT REFERENCES Employees(ID),
						DayStarOff date not null,
						DayEndOff date not null,
						LyDo nvarchar(500) not null,
						ThoiHan nvarchar(50) not null,
						LoaiNghi nvarchar(50) not null,
						DepID INT REFERENCES Deparment(ID),
						)
GO 


insert into Deparment values (N'ETown')
insert into Deparment values (N'Bitexco')
insert into Deparment values (N'Phòng Nhân Sự')
insert into Deparment values (N'Phòng Kế Toán')
insert into Deparment values (N'Phòng Bảo Vệ')
GO

INSERT INTO chucvu(Chucvu,PhongBanID)VALUES(N'Thư Ký',1)
INSERT INTO chucvu(Chucvu,PhongBanID)VALUES(N'Nhân Viên',3)
INSERT INTO chucvu(Chucvu,PhongBanID)VALUES(N'Trưởng Phòng Nhân Sự',3)
INSERT INTO chucvu(Chucvu,PhongBanID)VALUES(N'Trưởng Phòng Kế Toán',4)
INSERT INTO chucvu(Chucvu,PhongBanID)VALUES(N'Trưởng Phòng An Ninh',5)
GO

INSERT INTO TypeContract(NameType)VALUES(N'Hợp Đồng Thử Việc')
INSERT INTO TypeContract(NameType)VALUES(N'Hợp Đồng 1 Năm')
INSERT INTO TypeContract(NameType)VALUES(N'Hợp Đồng 5 Năm')
GO 


INSERT INTO Acc(acc,pass,[type])VALUES('Kizzz','202cb962ac59075b964b07152d234b70',1) -- Pass là 123
GO

--DELETE FROM Contracts 
--DELETE FROM dbo.Acc
--DELETE FROM dbo.chucvu 
--DELETE FROM dbo.Deparment
--DELETE FROM dbo.Employees
--DELETE FROM dbo.Project
--DELETE FROM Acc WHERE FullName = 'qwe'
--DELETE FROM	attendance
--DELETE FROM LichNghi

---------------------------------------------------
--select * FROM Contracts 
--select * FROM dbo.Acc 
--select * FROM dbo.Employees
--select * FROM dbo.chucvu 
--select * FROM dbo.Deparment
--select * FROM dbo.Project
--select * from dbo.TypeContract
--SELECT	* FROM	attendance AS a
--SELECT * FROM	DieuDong AS dd
--SELECT * FROM	DKNghi 

----------------------------------------------------------------------------------------






 
CREATE PROC USP_DieuDong 
@chuVuMoi NVARCHAR(100), @phongBanMoi NVARCHAR(100) , @idnv INT , @Ngay_Dieu_Dong DATE  ,@lydo NVARCHAR(100)
AS
BEGIN
	DECLARE @IDchuVu INT
	DECLARE @IDDep INT
	DECLARE @IDchuVuMoi INT
	DECLARE @IDphongBanMoi INT

	SELECT @IDchuVu = e.ChucvuID ,@IDDep =e.DepID FROM Employees AS e WHERE e.ID = @idnv
	
	SELECT @IDchuVuMoi = ID FROM chucvu WHERE Chucvu =@chuVuMoi
	SELECT @IDphongBanMoi = ID FROM Deparment WHERE Depname = @phongBanMoi
	
	DECLARE @ThanhCong INT 
	SET @ThanhCong = 1
	----Lay ten ChucVu Vs Phong Ban Cu
	DECLARE @phongbanCu NVARCHAR(100)
	DECLARE @chuVuCu NVARCHAR(100)
		
	SELECT @chuVuCu = Chucvu FROM chucvu WHERE ID =@IDchuVu
	SELECT @phongbanCu = Depname  FROM Deparment WHERE ID = @IDDep
	-------
---------Chuc Vu ------------------------------------	
	IF(@IDchuVuMoi != @IDchuVu  )
	BEGIN
		SET @ThanhCong = 11
		
		
		UPDATE Employees  SET ChucvuID = @IDchuVuMoi WHERE ID = @idnv
		
		INSERT INTO DieuDong(idnv,Ngay_Dieu_Dong,ChucVuCu,ChucVuMoi,PhongBanCu,LyDo)VALUES(@idnv,@Ngay_Dieu_Dong,@chuVuCu,@chuVuMoi,@phongbanCu,@lydo)	
		
		
	END
-----------Phòng Ban ------------------------------------	

	IF( @IDphongBanMoi != @IDDep)
	BEGIN
	
		
		IF(@ThanhCong != 11)
		BEGIN
			
			UPDATE Employees SET DepID = @IDphongBanMoi	WHERE ID = @idnv
			
			
			INSERT INTO DieuDong(idnv,Ngay_Dieu_Dong, ChucVuCu,PhongBanCu,PhongBanmoi,LyDo)VALUES	(@idnv,	@Ngay_Dieu_Dong,@chuVuCu,@phongbanCu,@phongBanMoi,@lydo)
		
		END
	
		IF(@ThanhCong = 11)
		BEGIN
			DECLARE @IDLast INT 
			SELECT TOP 1 @IDLast = ID FROM DieuDong WHERE idnv = @idnv ORDER BY ID DESC 
			
			UPDATE Employees SET DepID = @IDphongBanMoi	WHERE ID = @idnv
			
			UPDATE DieuDong	SET	Ngay_Dieu_Dong = @Ngay_Dieu_Dong, ChucVuCu =@chuVuCu ,PhongBanCu = @phongbanCu,	PhongBanmoi = @phongBanMoi WHERE idnv = @idnv AND Ngay_Dieu_Dong = @Ngay_Dieu_Dong AND ID = @IDLast
			
		END
		
	END
	
END
GO
 ----------------------------------------------------------------------------------------
