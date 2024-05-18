-- SQLBook: Code
DROP DATABASE der_grupo_5;
CREATE DATABASE der_grupo_5;

DROP USER 'client'@'%';
FLUSH PRIVILEGES;

CREATE USER 'client'@'%' identified by 'Client123$';
GRANT SELECT, UPDATE, DELETE, INSERT ON der_grupo_5.* TO 'client';
flush privileges;

USE der_grupo_5;

CREATE TABLE plano (
    idPlano INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    descricao VARCHAR(200) NOT NULL
);

CREATE TABLE empresa (
    idEmpresa INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    cnpj CHAR(18),
    fkPlano INT,
    constraint fkPlano foreign key (fkPlano) references plano(idPlano)
);

CREATE TABLE endereco (
    idEndereco INT PRIMARY KEY AUTO_INCREMENT,
    cep CHAR(9) NOT NULL,
    logradouro VARCHAR(60) NOT NULL,
    numero VARCHAR(4) NOT NULL,
    bairro VARCHAR(40),
    estado VARCHAR(30),
    complemento VARCHAR(45),
    fkEmpresa INT NOT NULL,
        CONSTRAINT fkEmpresaEndereco FOREIGN KEY (fkEmpresa) REFERENCES empresa(idEmpresa)
);

CREATE TABLE usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    senha VARCHAR(45) NOT NULL,
    cargo varchar(45),
    fkEmpresa INT NOT NULL, 
        CONSTRAINT fkEmpresaUsuario FOREIGN KEY (fkEmpresa) REFERENCES empresa(idEmpresa)
);

CREATE TABLE contato (
    idContato INT AUTO_INCREMENT,
    telefone CHAR(12) NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    fkUsuario INT NOT NULL,
        CONSTRAINT fkUsuarioContato FOREIGN KEY (fkUsuario) REFERENCES usuario(idUsuario),
    PRIMARY KEY (idContato, fkUsuario)
);

CREATE TABLE maquina (
    idMaquina INT AUTO_INCREMENT,
    numeroIdentificacao VARCHAR(25),
    modelo VARCHAR(45),
    marca VARCHAR (45),
    username VARCHAR(45),
    hostname VARCHAR(45),
    mac VARCHAR(20),
    fkUsuario INT NOT NULL,
        CONSTRAINT fkUsuarioMaquina FOREIGN KEY (fkUsuario) REFERENCES usuario(idUsuario),
    PRIMARY KEY (idMaquina, fkUsuario)
);

CREATE TABLE rede (
    idRede INT PRIMARY KEY AUTO_INCREMENT,
    nomeRede VARCHAR(45) NOT NULL,
    interfaceRede VARCHAR(45) NOT NULL,
    sinalRede INT NOT NULL,
    transmissaoRede DOUBLE NOT NULL,
    bssidRede VARCHAR(20) NOT NULL
);

CREATE TABLE ipv4(
    idIpv4 INT AUTO_INCREMENT,
    numeroIP VARCHAR(18),
    nomeLocal VARCHAR(45),
    fkMaquina INT NOT NULL,
        CONSTRAINT fkMaquinaIPV4 FOREIGN KEY (fkMaquina) REFERENCES maquina(idMaquina),
    fkUsuario INT NOT NULL,
        CONSTRAINT fkUsuarioMaquinaIPV4 FOREIGN KEY (fkUsuario) REFERENCES maquina(fkUsuario),
    PRIMARY KEY (idIpv4, fkMaquina, fkUsuario)
);

CREATE TABLE relRedeIpv4 (
    fkRede INT NOT NULL,
		CONSTRAINT fkRedeRRI FOREIGN KEY (fkRede) REFERENCES rede(idRede),
	fkIpv4 INT NOT NULL,
		CONSTRAINT fkIpv4RRI FOREIGN KEY (fkIpv4) REFERENCES ipv4(idIpv4),
	PRIMARY KEY (fkRede, fkIpv4),
	dataConexao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

# alter table relRedeIpv4 add primary key (fkRede, fkIpv4);

CREATE TABLE alertas(
    idAlertas INT PRIMARY KEY AUTO_INCREMENT,
    tipoAlertas VARCHAR(45) NOT NULL,
    mensagem VARCHAR(60) NOT NULL
);

CREATE TABLE componente (
    idComponente INT AUTO_INCREMENT,
    componente VARCHAR(200) NOT NULL,
    modelo VARCHAR(200) NOT NULL,
    fabricante VARCHAR(200) NOT NULL,
    fkMaquina INT NOT NULL,
        CONSTRAINT fkMaquinaComponente FOREIGN KEY (fkMaquina) REFERENCES maquina(idMaquina),
    fkUsuario INT NOT NULL,
        CONSTRAINT fkMaquinaUsuarioComponente FOREIGN KEY (fkUsuario) REFERENCES maquina(fkUsuario),
    PRIMARY KEY (idComponente, fkMaquina, fkUsuario)
);


CREATE TABLE captura(
    idCaptura INT AUTO_INCREMENT,
    dadoCaptura VARCHAR(45) NOT NULL,
    unidadeMedida VARCHAR(5) NOT NULL,
    dataCaptura DATETIME NOT NULL,
    fkComponente INT NOT NULL,
        CONSTRAINT fkComponenteCaptura FOREIGN KEY (fkComponente) REFERENCES componente(idComponente),
    PRIMARY KEY (idCaptura, fkComponente)
);

CREATE TABLE registroAlertas(
    idRegistroAlertas INT AUTO_INCREMENT,
    horario TIMESTAMP,
    fkAlertas INT NOT NULL,
    constraint fkAlertas foreign key (fkAlertas) references alertas(idAlertas),
    fkCaptura INT NOT NULL,
        CONSTRAINT fkRegistroAlertasCaptura FOREIGN KEY (fkCaptura) REFERENCES captura(idCaptura),
    fkComponente INT NOT NULL,
        CONSTRAINT fkRegistroAlertasCapturaComponente FOREIGN KEY (fkComponente) REFERENCES captura(fkComponente),
    PRIMARY KEY (idRegistroAlertas, fkCaptura, fkComponente)
);


insert into plano values
(null, 'Plano Freelancer', 'Foco: Freelancers (monitora uma máquina).
Monitoramento de Hardware: Processador, RAM, disco, conexão USB, placa gráfica'),
(null, 'Plano Empresarial', 'Foco: Pequenas e Médias Empresas (monitora até 100 máquinas).
Monitoramento de Hardware: Processador, RAM, disco, conexão USB, placa gráfica.'),
(null, 'Plano Corporativo', 'Foco: Grandes Empresas.
Monitoramento de Hardware: Processador, RAM, disco, conexão USB, placa gráfica.');

-- Inserções para a tabela 'empresa'
INSERT INTO empresa (nome, cnpj, fkPlano) VALUES
('Empresa A', '12345678901234', 1),
('Empresa B', '98765432109876', 2),
('Empresa C', '56789012345678', 3);

-- Inserções para a tabela 'endereco'
INSERT INTO endereco (cep, logradouro, numero, fkEmpresa) VALUES
('12345-678', 'Rua A', '123', 1),
('98765-432', 'Rua B', '456', 2),
('56789-012', 'Rua C', '789', 3);

-- Inserções para a tabela 'usuario'
INSERT INTO usuario (nome, email, senha, fkEmpresa) VALUES
('Usuário 1', 'usuario1@empresa.com', 'senha123', 1),
('Usuário 2', 'usuario2@empresa.com', 'senha456', 1),
('Usuário 3', 'usuario3@empresa.com', 'senha789', 2);


INSERT INTO maquina (fkUsuario) VALUES 
	(1),
    (2),
    (3);


insert into ipv4(numeroIP, nomeLocal, fkMaquina, fkUsuario) values 
	('192.168.15.5', 'Home', 1, 1);


SELECT * FROM maquina JOIN usuario on maquina.fkUsuario = usuario.idUsuario JOIN ipv4 ON ipv4.fkMaquina = maquina.idMaquina WHERE idUsuario = 2;

select * from ipv4 join usuario on fkUsuario = idUsuario where fkEmpresa = 1;
select * from rede;
SELECT * FROM relredeipv4;
select * from maquina join ipv4 on idMaquina = fkMaquina;
select * from ipv4;

SELECT * FROM ipv4 WHERE numeroIP = '192.168.15.6' AND fkMaquina = '1';
SELECT * FROM relredeipv4;

select * from relredeIpv4 join ipv4 on fkIpv4 = idIpv4 join rede on fkRede = idRede where fkRede = 1;

insert into relRedeIpv4 value 
	(1, 2, default);

SELECT * FROM rede WHERE bssidRede = 'e6:54:20:c3:b5:2e';

select * from maquina;
