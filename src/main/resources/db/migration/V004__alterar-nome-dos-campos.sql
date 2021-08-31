alter table entrega change taxa taxa_de_entrega decimal(10,2) not null;

alter table entrega modify status_pedido varchar(20) not null;