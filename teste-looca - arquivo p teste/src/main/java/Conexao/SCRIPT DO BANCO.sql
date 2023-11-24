IF OBJECT_ID('prj_sprint', 'U') IS NOT NULL
    DROP DATABASE prj_sprint;
GO

CREATE DATABASE prj_sprint;
GO

USE prj_sprint;
GO

CREATE TABLE tbEmpresa (
                           idEmpresa INT IDENTITY(1,1) PRIMARY KEY,
                           nomeFantasia NVARCHAR(150) NOT NULL,
                           razaoSocial NVARCHAR(150) NOT NULL,
                           cnpj NVARCHAR(18) NOT NULL,
                           telefone NVARCHAR(14) NOT NULL,
                           limiteAlerta INT DEFAULT 60
);

CREATE TABLE tbUsuario (
                           idUsuario INT IDENTITY(1,1) PRIMARY KEY,
                           nome NVARCHAR(100) NOT NULL,
                           sobrenome NVARCHAR(100) NOT NULL,
                           email NVARCHAR(100) UNIQUE NOT NULL,
                           senha NVARCHAR(200) NOT NULL,
                           tipoUsuario NCHAR(5),
                           fkEmpresa INT,
                           FOREIGN KEY (fkEmpresa) REFERENCES tbEmpresa (idEmpresa)
);

CREATE TABLE tbArena (
                         idArena INT IDENTITY(1,1) PRIMARY KEY,
                         nomeArena NVARCHAR(150) NOT NULL,
                         cep NCHAR(9) NOT NULL,
                         logradouro NVARCHAR(100) NOT NULL,
                         numero INT NOT NULL,
                         bairro NVARCHAR(100) NOT NULL,
                         cidade NVARCHAR(100) NOT NULL,
                         uf NCHAR(2),
                         fkEmpresa INT,
                         FOREIGN KEY (fkEmpresa) REFERENCES tbEmpresa (idEmpresa)
);

CREATE TABLE tbComputador (
                              idComputador NVARCHAR(100) PRIMARY KEY,
                              apelidoPc NVARCHAR(100),
                              sistemaOperacional NVARCHAR(30),
                              processador NVARCHAR(100),
                              discoTotal BIGINT,
                              memoriaTotal BIGINT,
                              qtdDiscos INT,
                              fkArena INT,
                              FOREIGN KEY (fkArena) REFERENCES tbArena (idArena)
);

CREATE TABLE status_pc (
                           idCaptura INT IDENTITY(1,1) PRIMARY KEY,
                           memoriaUso BIGINT,
                           processadorUso FLOAT,
                           descricao NVARCHAR(200),
                           dtHoraAlerta DATETIME DEFAULT GETDATE(),
                           caminhoArquivo NVARCHAR(200),
                           tipoAlerta NVARCHAR(150),
                           fkComputador NVARCHAR(100),
                           FOREIGN KEY (fkComputador) REFERENCES tbComputador(idComputador)
);
