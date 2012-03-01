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


create table PERFIL(
	PRF_ID INT NOT NULL PRIMARY KEY identity(1,1),
	PRF_DESCRIPCION VARCHAR(50)
)
GO

create table PERFIL_PERMISO(
	PRF_ID INT NOT NULL REFERENCES PERFIL(PRF_ID),
	PER_ID INT NOT NULL REFERENCES PERMISO(PER_ID),
	PEP_FECHA DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(PRF_ID,PER_ID)
)
GO

INSERT INTO PERFIL (PRF_DESCRIPCION) VALUES ('Administrador')
GO
INSERT INTO PERFIL (PRF_DESCRIPCION) VALUES ('Auditor')
GO
INSERT INTO PERFIL (PRF_DESCRIPCION) VALUES ('Supervisor')
GO
INSERT INTO PERFIL (PRF_DESCRIPCION) VALUES ('Usuario')
GO

INSERT INTO PERFIL_PERMISO (PRF_ID,PER_ID)
SELECT 1, PER_ID FROM PERMISO
go

alter table usuario add PRF_ID INT REFERENCES PERFIL(PRF_ID)
GO

UPDATE USUARIO SET PRF_ID = 1 WHERE USU_LOGIN = 'admin'
go