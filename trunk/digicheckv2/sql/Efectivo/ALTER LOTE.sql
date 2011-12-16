--Revisar nombre de la base
use digicheck

--Revisar el nombre del constraint
alter table cheque drop constraint DF__CHEQUE__CHQ_TIPO__797309D9
go
alter table cheque drop column chq_tipo
go

alter table lote add LOT_TIPO int not null default 0
go
