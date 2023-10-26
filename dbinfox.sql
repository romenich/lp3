-- a linha abaixo cria um banco de dados
create database dbinfox;
-- a linha abaixo escolhe o banco de dados a ser utilizado
use dbinfox;
-- o bloco de instruções abaixo cria uma tabela
create table tabusuarios(
iduser int primary key,
usuario varchar(50) not null,
telefone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null
);

-- o comando abaixo descreve a tabela 
describe tabusuarios;
-- a linha abaixo insere dados na tabela (CRUD)
-- create > insert
insert into tabusuarios (iduser, usuario, telefone, login, senha)
values(1,'José de Assis', '22-9903-0404', 'josedeassis', '12345');
-- a linha abaixo exibe os dados da tabela (CRUD)
-- read > select
select * from tabusuarios;
insert into tabusuarios (iduser, usuario, telefone, login, senha)
values(2,'Josias Macarrao', '22-9132-0404', 'josiasmaca', '54321');
insert into tabusuarios (iduser, usuario, telefone, login, senha)
values(3,'Juca Limao', '22-9434-0404', 'juquinha', '12345');
insert into tabusuarios (iduser, usuario, telefone, login, senha)
values(4,'Administrador', '22-9990-0399', 'admin', 'admin');

-- a linha abaixo modifica dados na tabela (CRUD)
-- update > update
update tabusuarios set telefone='8888-8888' where iduser=1;

-- a linhaabaixo apaga um registro da tabela (CRUD)
-- delete > delete
delete from tabusuarios where iduser=3;

-- criando cadastro de clientes
create table tabclientes(
idcliente int primary key auto_increment,
nomecliente varchar (50) not null, 
enderecocliente varchar(100),
telefonecliente varchar(50) not null,
emailcliente varchar(50)
);

-- descrevendo a tabela clientes
describe tabclientes;

-- inserindo dados na tabela clientes
insert into tabclientes (nomecliente, enderecocliente, telefonecliente, emailcliente)
values ('Linus Torvaldas', 'Rua Tux 2023', '22-9999-9999', 'torvalds@linux.kernel');

-- criando a tabela ordem de serviços
create table tabos(
os int primary key auto_increment,
data_os timestamp default current_timestamp,
equipamento varchar(50) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar (30),
valor decimal(10,2),
idcliente int not null,
foreign key (idcliente) references tabclientes(idcliente)
);

-- descrevendo a tabela
describe tabos;


-- inserindo dados na tabela os
insert into tabos (equipamento, defeito, servico, tecnico, valor, idcliente)
values ('notebook', 'nao liga', 'Troca da fonte', 'Zé', 87.50, 1);

-- verificando os dados inseridos na tabela
select * from tabos;

-- o código abaixo traz informações de duas tabelas
select 
O.os,equipamento,defeito,servico,valor,
C.nomecliente,telefonecliente
from tabos as O
inner join tabclientes as C
on (O.idcliente=C.idcliente);
