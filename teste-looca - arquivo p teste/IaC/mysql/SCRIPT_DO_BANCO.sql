DROP DATABASE IF EXISTS prj_sprint;
CREATE DATABASE IF NOT EXISTS prj_sprint;
USE prj_sprint;

CREATE TABLE tbEmpresa(
	idEmpresa INT PRIMARY KEY AUTO_INCREMENT,
    nomeFantasia VARCHAR(150) NOT NULL,
    razaoSocial VARCHAR(150) NOT NULL,
    cnpj CHAR(18) unique NOT NULL,
    telefone char(14) NOT NULL,
    limiteAlerta INT DEFAULT 60
);

CREATE TABLE tbUsuario(
	idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    email varchar(100) unique NOT NULL,
    senha VARCHAR(200) NOT NULL,
    tipoUsuario CHAR(5),
	fkEmpresa INT,
    FOREIGN KEY (fkEmpresa) REFERENCES tbEmpresa (idEmpresa)
);

CREATE TABLE tbArena(
	idArena INT PRIMARY KEY AUTO_INCREMENT,
    nomeArena VARCHAR(150) NOT NULL,
    cep CHAR(9) NOT NULL NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero INT NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf char(2),
    fkEmpresa INT,
    FOREIGN KEY (fkEmpresa) REFERENCES tbEmpresa (idEmpresa)
);

CREATE TABLE tbComputador(
	idComputador INT PRIMARY KEY AUTO_INCREMENT,
    sistemaOperacional VARCHAR(30),
    processador VARCHAR(100),
    discoTotal LONG,
    memoriaTotal LONG,
    qtdDiscos INT,
    fkArena INT,
    FOREIGN KEY (fkArena) REFERENCES tbArena (idArena)
);

CREATE TABLE status_pc(
	idCaptura INT PRIMARY KEY AUTO_INCREMENT,
    memoriaUso LONG,
    processadorUso DOUBLE,
    discoDisponivel LONG,
    dtHoraCaptura DATETIME DEFAULT now(),
	fkComputador INT,
    FOREIGN KEY (fkComputador) REFERENCES tbComputador (idComputador)
);

CREATE TABLE Alerta(
    idAlerta INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(200),
    dtHoraAlerta DATETIME DEFAULT now(),
    caminhoArquivo VARCHAR(200),
    fkComputador INT,
    FOREIGN KEY (fkComputador) REFERENCES tbComputador(idComputador));

    -- Função para buscar o ID da empresa de acordo com o CNPJ
DELIMITER $$
CREATE FUNCTION fn_empresa(fnCnpj char(18))
RETURNS int
deterministic
BEGIN
    DECLARE vId int;
    set vId = (select idEmpresa from tbEmpresa where CNPJ = fnCnpj);
    return(vId);
END$$;
DELIMITER ;

-- INSERT INTO tbusuario (nome, sobrenome, email, senha)VALUES ('MC', 'Lovin','gui@gmail.com', '123');

/*insert into tbComputador values (null, 'Windows 11','Intel Core i7 7700k','1000','16', 1, 1),
								(null, 'Windows 11','Intel Core i7 7700k','1000','16', 2, 1),
                                (null, 'Windows 11','Intel Core i7 7700k','1000','16', 1, 1),
                                (null, 'Windows 11','Intel Core i7 7700k','1000','16', 1 ,1),
                                (null, 'Windows 11','Intel Core i7 7700k','1000','16',1 ,1);
                                

insert into status_pc (memoriaUso, processadorUso, discoDisponivel, fkComputador)
					  values (77, 62, 58.1, 1),
							 (71, 60, 58.1, 1),
                             (69, 59, 58.2, 1),
                             (65, 50, 58.2, 1),
                             (60, 45, 58.1, 1),
                             
                             (98, 90, 80.2, 2),
                             (99, 90, 80.3, 2),
                             (97, 90, 80.3, 2),
                             (100, 95, 80.4, 2),
                             (98, 94, 79.1, 2),
                             
                             (12, 20, 10.2, 3),
                             (16, 25, 10.2, 3),
                             (18, 25, 10.3, 3),
                             (17, 23, 10.4, 3),
                             (15, 22, 10.4, 3),
                             
                             (70, 66, 90.2, 4),
                             (76, 66, 90.3, 4),
                             (77, 69, 90.2, 4),
                             (81, 68, 90.2, 4),
                             (80, 70, 90.2, 4),
                             
                             (07, 11, 37.2, 5),
                             (05, 10, 37.3, 5),
                             (05, 10, 37.3, 5),
                             (02, 09, 37.3, 5),
                             (08, 12, 37.2, 5); */