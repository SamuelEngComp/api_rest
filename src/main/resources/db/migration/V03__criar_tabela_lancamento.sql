create table lancamento(
	codigo bigint(20) primary key auto_increment,
	descricao varchar(50) not null,
	data_vencimento date not null,
	data_pagamento date,
	valor decimal(10,2) not null,
	observacao varchar(100),
	tipo varchar(20) not null,
	codigo_categoria bigint(20) not null,
	codigo_pessoa bigint(20) not null,
	foreign key (codigo_categoria) references categoria(codigo),
	foreign key (codigo_pessoa) references pessoa(codigo)
	
)engine=InnoDB default charset=utf8;


insert into lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) 
			values ('Salario mensal', '2017-02-02', '2017-02-01', 4000.00,'Distribuição de lucros','RECEITA',9,1);
			
insert into lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) 
			values ('Netflix', '2017-03-01', '2017-02-27', 59.00,'Cotidiano','DESPESA',1,1);
			
insert into lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) 
			values ('Alimentacao', '2017-07-09', '2017-07-02', 360.00,'alimentos','DESPESA',2,7);
			
insert into lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) 
			values ('Estudos', '2017-09-19', '2017-09-10', 260.00,'livros','DESPESA',8,7);