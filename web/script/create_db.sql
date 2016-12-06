create database pedidofacil;

create user 'pedidofacil'@'localhost' identified by 'pedidofacil';

grant all privileges on * . * to 'pedidofacil'@'localhost';

use pedidofacil;

create table categoria(
	cod int not null primary key auto_increment,
    nome varchar(50) not null
);

create table produtos(
	cod int not null primary key auto_increment,
    nome varchar(50) not null,
    tipo varchar(25) not null,
    preco numeric(8,2) not null,
    quantidade numeric(8,2) not null,
    cod_categ int not null,
    constraint fk_categ foreign key (cod_categ) references categoria(cod)
);

create table item_prod(
	cod int not null primary key auto_increment,
    cod_prod int not null,
    quantidade numeric(8,2),
    constraint fk_prod foreign key (cod_prod) references produtos(cod)
);
    
create table pessoa (
	cod int not null primary key auto_increment,
    nome varchar(50) not null,
    tipo varchar(25) not null,
    senha varchar(25),
    email varchar(250),
    fone varchar(25)
);

create table pedido(
	cod int not null primary key auto_increment,
    hora_ent datetime not null,
    status_ped varchar(25) not null,
    hora_sai datetime,
    cod_mesa int not null,
    cod_func int not null,
    cod_cli int,
	constraint fk_func foreign key (cod_func) references pessoa(cod),
    constraint fk_cli foreign key (cod_cli) references pessoa(cod)
);
    
create table item_pedido(
	cod int not null primary key auto_increment,
    cod_ped int not null,
    quantidade numeric(8,2) not null,
    status_item varchar(25) not null,
    hora_processado datetime,
    constraint fk_pedido foreign key (cod_ped) references pedido(cod)
);
    