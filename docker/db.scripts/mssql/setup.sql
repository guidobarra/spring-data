IF NOT EXISTS(
                SELECT *
                FROM sys.databases
                WHERE name = 'library'
             )
BEGIN
    CREATE DATABASE [library]
END

GO
    USE [library]
GO

IF NOT EXISTS (
                SELECT *
                FROM sys.tables
                WHERE name='book' and type='U'
              )
BEGIN
CREATE TABLE book (
    id INT PRIMARY KEY IDENTITY (1, 1),
    name VARCHAR(100),
)
END