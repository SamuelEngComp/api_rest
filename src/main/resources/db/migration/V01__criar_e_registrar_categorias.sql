create table categoria(
	codigo bigint(20) primary key auto_increment,
    nome varchar(50) not null

)engine=InnoDB default charset=utf8;

insert into categoria (nome) values ('lazer');
insert into categoria (nome) values ('alimentação');
insert into categoria (nome) values ('supermercado');
insert into categoria (nome) values ('farmacia');
insert into categoria (nome) values ('outros');