
INSERT INTO PERMISO (PER_ID,PER_DESCRIPCION,PET_CODE) VALUES (12, 'Datos Usuarios','002')
go
INSERT INTO PERMISO (PER_ID,PER_DESCRIPCION,PET_CODE) VALUES (13, 'Datos Bancos/Divisas','002')
go

update tipo_permiso set pet_descripcion = 'Documentos' where pet_code = '003'
go
update tipo_permiso set pet_descripcion = 'Catalogos' where pet_code = '002'
go

INSERT INTO PERMISO (PER_ID,PER_DESCRIPCION,PET_CODE) VALUES (14, 'Borrar Documentos','003')
go

insert into USUARIO_PERMISO (USU_LOGIN, PER_ID, USP_FECHA) values ('admin', 12, getDate())
go
insert into USUARIO_PERMISO (USU_LOGIN, PER_ID, USP_FECHA) values ('admin', 13, getDate())
go
insert into USUARIO_PERMISO (USU_LOGIN, PER_ID, USP_FECHA) values ('admin', 14, getDate())
go
