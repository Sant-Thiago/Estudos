CREATE DATABASE databaseinfo;

CREATE TABLE usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    senha VARCHAR(45) NOT NULL,
    cargo varchar(45),
    fkEmpresa INT NOT NULL, 
        CONSTRAINT fkEmpresaUsuario FOREIGN KEY (fkEmpresa) REFERENCES empresa(idEmpresa)
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
    id INT AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    interface VARCHAR(45) NOT NULL,
    sinal INT NOT NULL,
    transmissao FLOAT NOT NULL,
    bssid VARCHAR NOT NULL,
    fkIpv4 INT NOT NULL,
        CONSTRAINT fkIpv4Rede FOREIGN KEY (fkIpv4) REFERENCES ipv4(idIpv4),
    PRIMARY KEY (id, fkIpv4) 
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
