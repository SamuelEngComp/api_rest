create table pessoa(
	codigo bigint(20) primary key auto_increment,
    nome varchar(50) not null,
    ativo boolean,
    logradouro varchar(50),
    numero varchar(50),
    complemento varchar(50),
    bairro varchar(50),
    cep varchar(50),
    cidade varchar(50),
    estado varchar(50)

)engine=InnoDB default charset=utf8;

insert into pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
			values ('Samuel lima', true, 'rua boa vista', '313','rua principal','boa vista','59550-000','joao camara','RN');
insert into pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
			values ('Samuel lima_1', true, 'rua boa vista_1', '313_1','rua principal_1','boa vista_1','59550-000_1','joao camara_1','RN_1');
insert into pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
			values ('Samuel lima_2', true, 'rua boa vista_2', '313_2','rua principal_2','boa vista_2','59550-000_2','joao camara_2','RN_2');
insert into pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
			values ('Samuel lima_3', true, 'rua boa vista_3', '313_3','rua principal_3','boa vista_3','59550-000_3','joao camara_3','RN_3');
insert into pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
			values ('Samuel lima_4', true, 'rua boa vista_4', '313_4','rua principal_4','boa vista_4','59550-000_4','joao camara_4','RN_4');
insert into pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
			values ('Samuel lima_5', true, 'rua boa vista_5', '313_5','rua principal_5','boa vista_5','59550-000_5','joao camara','RN_5');