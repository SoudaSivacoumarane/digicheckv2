
CREATE TABLE BANCO
( 
	BAN_ID               integer IDENTITY ( 1,1 ) ,
	BAN_CODIGO           varchar(4)  NOT NULL ,
	BAN_NOMBRE           varchar(100)  NOT NULL ,
	BAN_CUENTA           varchar(100)  NOT NULL 
)
go



ALTER TABLE BANCO
	ADD CONSTRAINT IDX_PK_BAN PRIMARY KEY  CLUSTERED (BAN_ID ASC)
go



CREATE TABLE DIVISA
( 
	DIV_ID               integer IDENTITY ( 1,1 ) ,
	DIV_IMAGEN           varbinary  NULL ,
	DIV_NOMBRE           varchar(100)  NOT NULL ,
	DIV_CODIGO           varchar(3)  NOT NULL 
)
go



ALTER TABLE DIVISA
	ADD CONSTRAINT IDX_PK_DIV PRIMARY KEY  CLUSTERED (DIV_ID ASC)
go



CREATE TABLE DOCUMENTO
( 
	DOC_ID               integer IDENTITY ( 1,1 ) ,
	DOC_NUMERO           integer  NOT NULL ,
	DOC_ARCHIVO          varbinary  NOT NULL ,
	LOT_ID               integer  NOT NULL ,
	DOT_ID               char(1)  NULL 
)
go



ALTER TABLE DOCUMENTO
	ADD CONSTRAINT IDX_PK_DOC PRIMARY KEY  CLUSTERED (DOC_ID ASC)
go



CREATE TABLE ESTADO
( 
	EST_NOMBRE           varchar(100)  NOT NULL ,
	EST_CODIGO           varchar(4)  NOT NULL 
)
go



ALTER TABLE ESTADO
	ADD CONSTRAINT IDX_PK_EST PRIMARY KEY  CLUSTERED (EST_CODIGO ASC)
go



CREATE TABLE LOTE
( 
	LOT_REFERENCIA       varchar(100)  NOT NULL ,
	LOT_IMPORTE          decimal(18,2)  NOT NULL ,
	USU_LOGIN            varchar(20)  NOT NULL ,
	SUC_ID               integer  NOT NULL ,
	BAN_ID               integer  NOT NULL ,
	DIV_ID               integer  NOT NULL ,
	LOT_ID               integer IDENTITY ( 1,1 ) ,
	LOT_DOCUMENTOS       integer  NOT NULL ,
	LOT_FECHA            datetime  NOT NULL ,
	LOT_FECHA_ALTA       datetime  NOT NULL 
)
go



ALTER TABLE LOTE
	ADD CONSTRAINT IDX_PK_LOT PRIMARY KEY  CLUSTERED (LOT_ID ASC)
go



CREATE TABLE PERMISO
( 
	PER_ID               integer  NOT NULL ,
	PER_DESCRIPCION      varchar(100)  NOT NULL ,
	PET_CODE             char(3)  NOT NULL 
)
go



ALTER TABLE PERMISO
	ADD CONSTRAINT IDX_PK_PER PRIMARY KEY  CLUSTERED (PER_ID ASC)
go



CREATE TABLE SUCURSAL
( 
	SUC_ID               integer IDENTITY ( 1,1 ) ,
	SUC_NOMBRE           varchar(100)  NOT NULL ,
	SUC_DIRECCION        varchar(100)  NULL ,
	SUC_COLONIA          varchar(20)  NULL ,
	SUC_CODIGO_POSTAL    varchar(5)  NULL ,
	SUC_CIUDAD           varchar(100)  NULL ,
	EST_CODIGO           varchar(4)  NULL 
)
go



ALTER TABLE SUCURSAL
	ADD CONSTRAINT IDX_FK_SUC PRIMARY KEY  CLUSTERED (SUC_ID ASC)
go



CREATE TABLE TIPO_DOCUMENTO
( 
	DOT_ID               char(1)  NOT NULL ,
	DOT_DESCRIPCION      varchar(30)  NOT NULL 
)
go



ALTER TABLE TIPO_DOCUMENTO
	ADD CONSTRAINT IDX_PK_DOT PRIMARY KEY  CLUSTERED (DOT_ID ASC)
go



CREATE TABLE TIPO_PERMISO
( 
	PET_CODE             char(3)  NOT NULL ,
	PET_DESCRIPCION      varchar(20)  NULL 
)
go



ALTER TABLE TIPO_PERMISO
	ADD CONSTRAINT IDX_PK_PET PRIMARY KEY  CLUSTERED (PET_CODE ASC)
go



CREATE TABLE USUARIO
( 
	USU_LOGIN            varchar(20)  NOT NULL ,
	USU_NOMBRE           varchar(100)  NOT NULL ,
	USU_PASSWORD         varchar(30)  NOT NULL ,
	SUC_ID               integer  NULL 
)
go



ALTER TABLE USUARIO
	ADD CONSTRAINT IDX_PK_USU PRIMARY KEY  CLUSTERED (USU_LOGIN ASC)
go



CREATE TABLE USUARIO_PERMISO
( 
	USU_LOGIN            varchar(20)  NOT NULL ,
	PER_ID               integer  NOT NULL ,
	USP_FECHA            datetime  NOT NULL 
)
go



ALTER TABLE USUARIO_PERMISO
	ADD CONSTRAINT IDX_PK_USP PRIMARY KEY  CLUSTERED (USU_LOGIN ASC,PER_ID ASC)
go




ALTER TABLE DOCUMENTO
	ADD CONSTRAINT FK_LOT_DOC FOREIGN KEY (LOT_ID) REFERENCES LOTE(LOT_ID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE DOCUMENTO
	ADD CONSTRAINT FK_DOT_DOC FOREIGN KEY (DOT_ID) REFERENCES TIPO_DOCUMENTO(DOT_ID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE LOTE
	ADD CONSTRAINT FK_SUC_LOT FOREIGN KEY (SUC_ID) REFERENCES SUCURSAL(SUC_ID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE LOTE
	ADD CONSTRAINT FK_USU_LOT FOREIGN KEY (USU_LOGIN) REFERENCES USUARIO(USU_LOGIN)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE LOTE
	ADD CONSTRAINT FK_BAN_LOT FOREIGN KEY (BAN_ID) REFERENCES BANCO(BAN_ID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE LOTE
	ADD CONSTRAINT FK_DIV_LOT FOREIGN KEY (DIV_ID) REFERENCES DIVISA(DIV_ID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE PERMISO
	ADD CONSTRAINT FK_PET_PER FOREIGN KEY (PET_CODE) REFERENCES TIPO_PERMISO(PET_CODE)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE SUCURSAL
	ADD CONSTRAINT FK_EST_SUC FOREIGN KEY (EST_CODIGO) REFERENCES ESTADO(EST_CODIGO)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE USUARIO
	ADD CONSTRAINT FK_SUC_USU FOREIGN KEY (SUC_ID) REFERENCES SUCURSAL(SUC_ID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE USUARIO_PERMISO
	ADD CONSTRAINT FK_USU_USP FOREIGN KEY (USU_LOGIN) REFERENCES USUARIO(USU_LOGIN)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




ALTER TABLE USUARIO_PERMISO
	ADD CONSTRAINT FK_PER_USP FOREIGN KEY (PER_ID) REFERENCES PERMISO(PER_ID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go




CREATE TRIGGER tD_BANCO ON BANCO FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on BANCO */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* BANCO  LOTE on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="0000e7f4", PARENT_OWNER="", PARENT_TABLE="BANCO"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_BAN_LOT", FK_COLUMNS="BAN_ID" */
    IF EXISTS (
      SELECT * FROM deleted,LOTE
      WHERE
        /*  %JoinFKPK(LOTE,deleted," = "," AND") */
        LOTE.BAN_ID = deleted.BAN_ID
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete BANCO because LOTE exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_BANCO ON BANCO FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on BANCO */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insBAN_ID integer,
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* BANCO  LOTE on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="00010460", PARENT_OWNER="", PARENT_TABLE="BANCO"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_BAN_LOT", FK_COLUMNS="BAN_ID" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(BAN_ID)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,LOTE
      WHERE
        /*  %JoinFKPK(LOTE,deleted," = "," AND") */
        LOTE.BAN_ID = deleted.BAN_ID
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update BANCO because LOTE exists.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_DIVISA ON DIVISA FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on DIVISA */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* DIVISA  LOTE on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="0000e7ce", PARENT_OWNER="", PARENT_TABLE="DIVISA"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_DIV_LOT", FK_COLUMNS="DIV_ID" */
    IF EXISTS (
      SELECT * FROM deleted,LOTE
      WHERE
        /*  %JoinFKPK(LOTE,deleted," = "," AND") */
        LOTE.DIV_ID = deleted.DIV_ID
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete DIVISA because LOTE exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_DIVISA ON DIVISA FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on DIVISA */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insDIV_ID integer,
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* DIVISA  LOTE on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="0000f976", PARENT_OWNER="", PARENT_TABLE="DIVISA"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_DIV_LOT", FK_COLUMNS="DIV_ID" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(DIV_ID)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,LOTE
      WHERE
        /*  %JoinFKPK(LOTE,deleted," = "," AND") */
        LOTE.DIV_ID = deleted.DIV_ID
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update DIVISA because LOTE exists.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_DOCUMENTO ON DOCUMENTO FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on DOCUMENTO */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* LOTE  DOCUMENTO on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="000251a3", PARENT_OWNER="", PARENT_TABLE="LOTE"
    CHILD_OWNER="", CHILD_TABLE="DOCUMENTO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_LOT_DOC", FK_COLUMNS="LOT_ID" */
    IF EXISTS (SELECT * FROM deleted,LOTE
      WHERE
        /* %JoinFKPK(deleted,LOTE," = "," AND") */
        deleted.LOT_ID = LOTE.LOT_ID AND
        NOT EXISTS (
          SELECT * FROM DOCUMENTO
          WHERE
            /* %JoinFKPK(DOCUMENTO,LOTE," = "," AND") */
            DOCUMENTO.LOT_ID = LOTE.LOT_ID
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last DOCUMENTO because LOTE exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* TIPO_DOCUMENTO  DOCUMENTO on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="TIPO_DOCUMENTO"
    CHILD_OWNER="", CHILD_TABLE="DOCUMENTO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_DOT_DOC", FK_COLUMNS="DOT_ID" */
    IF EXISTS (SELECT * FROM deleted,TIPO_DOCUMENTO
      WHERE
        /* %JoinFKPK(deleted,TIPO_DOCUMENTO," = "," AND") */
        deleted.DOT_ID = TIPO_DOCUMENTO.DOT_ID AND
        NOT EXISTS (
          SELECT * FROM DOCUMENTO
          WHERE
            /* %JoinFKPK(DOCUMENTO,TIPO_DOCUMENTO," = "," AND") */
            DOCUMENTO.DOT_ID = TIPO_DOCUMENTO.DOT_ID
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last DOCUMENTO because TIPO_DOCUMENTO exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_DOCUMENTO ON DOCUMENTO FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on DOCUMENTO */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insDOC_ID integer,
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* LOTE  DOCUMENTO on child update no action */
  /* ERWIN_RELATION:CHECKSUM="0002bea3", PARENT_OWNER="", PARENT_TABLE="LOTE"
    CHILD_OWNER="", CHILD_TABLE="DOCUMENTO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_LOT_DOC", FK_COLUMNS="LOT_ID" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(LOT_ID)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,LOTE
        WHERE
          /* %JoinFKPK(inserted,LOTE) */
          inserted.LOT_ID = LOTE.LOT_ID
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    select @nullcnt = count(*) from inserted where
      inserted.LOT_ID IS NULL
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update DOCUMENTO because LOTE does not exist.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* TIPO_DOCUMENTO  DOCUMENTO on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="TIPO_DOCUMENTO"
    CHILD_OWNER="", CHILD_TABLE="DOCUMENTO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_DOT_DOC", FK_COLUMNS="DOT_ID" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(DOT_ID)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,TIPO_DOCUMENTO
        WHERE
          /* %JoinFKPK(inserted,TIPO_DOCUMENTO) */
          inserted.DOT_ID = TIPO_DOCUMENTO.DOT_ID
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    select @nullcnt = count(*) from inserted where
      inserted.DOT_ID IS NULL
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update DOCUMENTO because TIPO_DOCUMENTO does not exist.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_ESTADO ON ESTADO FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on ESTADO */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* ESTADO  SUCURSAL on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="0000ef0c", PARENT_OWNER="", PARENT_TABLE="ESTADO"
    CHILD_OWNER="", CHILD_TABLE="SUCURSAL"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_EST_SUC", FK_COLUMNS="EST_CODIGO" */
    IF EXISTS (
      SELECT * FROM deleted,SUCURSAL
      WHERE
        /*  %JoinFKPK(SUCURSAL,deleted," = "," AND") */
        SUCURSAL.EST_CODIGO = deleted.EST_CODIGO
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete ESTADO because SUCURSAL exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_ESTADO ON ESTADO FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on ESTADO */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insEST_CODIGO varchar(4),
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* ESTADO  SUCURSAL on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="00011905", PARENT_OWNER="", PARENT_TABLE="ESTADO"
    CHILD_OWNER="", CHILD_TABLE="SUCURSAL"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_EST_SUC", FK_COLUMNS="EST_CODIGO" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(EST_CODIGO)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,SUCURSAL
      WHERE
        /*  %JoinFKPK(SUCURSAL,deleted," = "," AND") */
        SUCURSAL.EST_CODIGO = deleted.EST_CODIGO
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update ESTADO because SUCURSAL exists.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_LOTE ON LOTE FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on LOTE */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* LOTE  DOCUMENTO on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="00051b3a", PARENT_OWNER="", PARENT_TABLE="LOTE"
    CHILD_OWNER="", CHILD_TABLE="DOCUMENTO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_LOT_DOC", FK_COLUMNS="LOT_ID" */
    IF EXISTS (
      SELECT * FROM deleted,DOCUMENTO
      WHERE
        /*  %JoinFKPK(DOCUMENTO,deleted," = "," AND") */
        DOCUMENTO.LOT_ID = deleted.LOT_ID
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete LOTE because DOCUMENTO exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* SUCURSAL  LOTE on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="SUCURSAL"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_SUC_LOT", FK_COLUMNS="SUC_ID" */
    IF EXISTS (SELECT * FROM deleted,SUCURSAL
      WHERE
        /* %JoinFKPK(deleted,SUCURSAL," = "," AND") */
        deleted.SUC_ID = SUCURSAL.SUC_ID AND
        NOT EXISTS (
          SELECT * FROM LOTE
          WHERE
            /* %JoinFKPK(LOTE,SUCURSAL," = "," AND") */
            LOTE.SUC_ID = SUCURSAL.SUC_ID
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last LOTE because SUCURSAL exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* USUARIO  LOTE on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="USUARIO"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_USU_LOT", FK_COLUMNS="USU_LOGIN" */
    IF EXISTS (SELECT * FROM deleted,USUARIO
      WHERE
        /* %JoinFKPK(deleted,USUARIO," = "," AND") */
        deleted.USU_LOGIN = USUARIO.USU_LOGIN AND
        NOT EXISTS (
          SELECT * FROM LOTE
          WHERE
            /* %JoinFKPK(LOTE,USUARIO," = "," AND") */
            LOTE.USU_LOGIN = USUARIO.USU_LOGIN
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last LOTE because USUARIO exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* BANCO  LOTE on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="BANCO"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_BAN_LOT", FK_COLUMNS="BAN_ID" */
    IF EXISTS (SELECT * FROM deleted,BANCO
      WHERE
        /* %JoinFKPK(deleted,BANCO," = "," AND") */
        deleted.BAN_ID = BANCO.BAN_ID AND
        NOT EXISTS (
          SELECT * FROM LOTE
          WHERE
            /* %JoinFKPK(LOTE,BANCO," = "," AND") */
            LOTE.BAN_ID = BANCO.BAN_ID
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last LOTE because BANCO exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* DIVISA  LOTE on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="DIVISA"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_DIV_LOT", FK_COLUMNS="DIV_ID" */
    IF EXISTS (SELECT * FROM deleted,DIVISA
      WHERE
        /* %JoinFKPK(deleted,DIVISA," = "," AND") */
        deleted.DIV_ID = DIVISA.DIV_ID AND
        NOT EXISTS (
          SELECT * FROM LOTE
          WHERE
            /* %JoinFKPK(LOTE,DIVISA," = "," AND") */
            LOTE.DIV_ID = DIVISA.DIV_ID
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last LOTE because DIVISA exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_LOTE ON LOTE FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on LOTE */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insLOT_ID integer,
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* LOTE  DOCUMENTO on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="000665e2", PARENT_OWNER="", PARENT_TABLE="LOTE"
    CHILD_OWNER="", CHILD_TABLE="DOCUMENTO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_LOT_DOC", FK_COLUMNS="LOT_ID" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(LOT_ID)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,DOCUMENTO
      WHERE
        /*  %JoinFKPK(DOCUMENTO,deleted," = "," AND") */
        DOCUMENTO.LOT_ID = deleted.LOT_ID
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update LOTE because DOCUMENTO exists.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* SUCURSAL  LOTE on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="SUCURSAL"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_SUC_LOT", FK_COLUMNS="SUC_ID" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(SUC_ID)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,SUCURSAL
        WHERE
          /* %JoinFKPK(inserted,SUCURSAL) */
          inserted.SUC_ID = SUCURSAL.SUC_ID
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    select @nullcnt = count(*) from inserted where
      inserted.SUC_ID IS NULL
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update LOTE because SUCURSAL does not exist.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* USUARIO  LOTE on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="USUARIO"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_USU_LOT", FK_COLUMNS="USU_LOGIN" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(USU_LOGIN)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,USUARIO
        WHERE
          /* %JoinFKPK(inserted,USUARIO) */
          inserted.USU_LOGIN = USUARIO.USU_LOGIN
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    select @nullcnt = count(*) from inserted where
      inserted.USU_LOGIN IS NULL
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update LOTE because USUARIO does not exist.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* BANCO  LOTE on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="BANCO"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_BAN_LOT", FK_COLUMNS="BAN_ID" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(BAN_ID)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,BANCO
        WHERE
          /* %JoinFKPK(inserted,BANCO) */
          inserted.BAN_ID = BANCO.BAN_ID
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    select @nullcnt = count(*) from inserted where
      inserted.BAN_ID IS NULL
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update LOTE because BANCO does not exist.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* DIVISA  LOTE on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="DIVISA"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_DIV_LOT", FK_COLUMNS="DIV_ID" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(DIV_ID)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,DIVISA
        WHERE
          /* %JoinFKPK(inserted,DIVISA) */
          inserted.DIV_ID = DIVISA.DIV_ID
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    select @nullcnt = count(*) from inserted where
      inserted.DIV_ID IS NULL
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update LOTE because DIVISA does not exist.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_PERMISO ON PERMISO FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on PERMISO */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* PERMISO  USUARIO_PERMISO on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="000234f2", PARENT_OWNER="", PARENT_TABLE="PERMISO"
    CHILD_OWNER="", CHILD_TABLE="USUARIO_PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_PER_USP", FK_COLUMNS="PER_ID" */
    IF EXISTS (
      SELECT * FROM deleted,USUARIO_PERMISO
      WHERE
        /*  %JoinFKPK(USUARIO_PERMISO,deleted," = "," AND") */
        USUARIO_PERMISO.PER_ID = deleted.PER_ID
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete PERMISO because USUARIO_PERMISO exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* TIPO_PERMISO  PERMISO on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="TIPO_PERMISO"
    CHILD_OWNER="", CHILD_TABLE="PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_PET_PER", FK_COLUMNS="PET_CODE" */
    IF EXISTS (SELECT * FROM deleted,TIPO_PERMISO
      WHERE
        /* %JoinFKPK(deleted,TIPO_PERMISO," = "," AND") */
        deleted.PET_CODE = TIPO_PERMISO.PET_CODE AND
        NOT EXISTS (
          SELECT * FROM PERMISO
          WHERE
            /* %JoinFKPK(PERMISO,TIPO_PERMISO," = "," AND") */
            PERMISO.PET_CODE = TIPO_PERMISO.PET_CODE
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last PERMISO because TIPO_PERMISO exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_PERMISO ON PERMISO FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on PERMISO */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insPER_ID integer,
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* PERMISO  USUARIO_PERMISO on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="00028c74", PARENT_OWNER="", PARENT_TABLE="PERMISO"
    CHILD_OWNER="", CHILD_TABLE="USUARIO_PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_PER_USP", FK_COLUMNS="PER_ID" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(PER_ID)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,USUARIO_PERMISO
      WHERE
        /*  %JoinFKPK(USUARIO_PERMISO,deleted," = "," AND") */
        USUARIO_PERMISO.PER_ID = deleted.PER_ID
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update PERMISO because USUARIO_PERMISO exists.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* TIPO_PERMISO  PERMISO on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="TIPO_PERMISO"
    CHILD_OWNER="", CHILD_TABLE="PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_PET_PER", FK_COLUMNS="PET_CODE" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(PET_CODE)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,TIPO_PERMISO
        WHERE
          /* %JoinFKPK(inserted,TIPO_PERMISO) */
          inserted.PET_CODE = TIPO_PERMISO.PET_CODE
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    select @nullcnt = count(*) from inserted where
      inserted.PET_CODE IS NULL
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update PERMISO because TIPO_PERMISO does not exist.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_SUCURSAL ON SUCURSAL FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on SUCURSAL */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* SUCURSAL  USUARIO on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="0002ef1e", PARENT_OWNER="", PARENT_TABLE="SUCURSAL"
    CHILD_OWNER="", CHILD_TABLE="USUARIO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_SUC_USU", FK_COLUMNS="SUC_ID" */
    IF EXISTS (
      SELECT * FROM deleted,USUARIO
      WHERE
        /*  %JoinFKPK(USUARIO,deleted," = "," AND") */
        USUARIO.SUC_ID = deleted.SUC_ID
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete SUCURSAL because USUARIO exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* SUCURSAL  LOTE on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="SUCURSAL"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_SUC_LOT", FK_COLUMNS="SUC_ID" */
    IF EXISTS (
      SELECT * FROM deleted,LOTE
      WHERE
        /*  %JoinFKPK(LOTE,deleted," = "," AND") */
        LOTE.SUC_ID = deleted.SUC_ID
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete SUCURSAL because LOTE exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* ESTADO  SUCURSAL on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="ESTADO"
    CHILD_OWNER="", CHILD_TABLE="SUCURSAL"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_EST_SUC", FK_COLUMNS="EST_CODIGO" */
    IF EXISTS (SELECT * FROM deleted,ESTADO
      WHERE
        /* %JoinFKPK(deleted,ESTADO," = "," AND") */
        deleted.EST_CODIGO = ESTADO.EST_CODIGO AND
        NOT EXISTS (
          SELECT * FROM SUCURSAL
          WHERE
            /* %JoinFKPK(SUCURSAL,ESTADO," = "," AND") */
            SUCURSAL.EST_CODIGO = ESTADO.EST_CODIGO
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last SUCURSAL because ESTADO exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_SUCURSAL ON SUCURSAL FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on SUCURSAL */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insSUC_ID integer,
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* SUCURSAL  USUARIO on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="0003556b", PARENT_OWNER="", PARENT_TABLE="SUCURSAL"
    CHILD_OWNER="", CHILD_TABLE="USUARIO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_SUC_USU", FK_COLUMNS="SUC_ID" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(SUC_ID)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,USUARIO
      WHERE
        /*  %JoinFKPK(USUARIO,deleted," = "," AND") */
        USUARIO.SUC_ID = deleted.SUC_ID
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update SUCURSAL because USUARIO exists.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* SUCURSAL  LOTE on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="SUCURSAL"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_SUC_LOT", FK_COLUMNS="SUC_ID" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(SUC_ID)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,LOTE
      WHERE
        /*  %JoinFKPK(LOTE,deleted," = "," AND") */
        LOTE.SUC_ID = deleted.SUC_ID
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update SUCURSAL because LOTE exists.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* ESTADO  SUCURSAL on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="ESTADO"
    CHILD_OWNER="", CHILD_TABLE="SUCURSAL"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_EST_SUC", FK_COLUMNS="EST_CODIGO" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(EST_CODIGO)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,ESTADO
        WHERE
          /* %JoinFKPK(inserted,ESTADO) */
          inserted.EST_CODIGO = ESTADO.EST_CODIGO
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    select @nullcnt = count(*) from inserted where
      inserted.EST_CODIGO IS NULL
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update SUCURSAL because ESTADO does not exist.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_TIPO_DOCUMENTO ON TIPO_DOCUMENTO FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on TIPO_DOCUMENTO */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* TIPO_DOCUMENTO  DOCUMENTO on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="0000efef", PARENT_OWNER="", PARENT_TABLE="TIPO_DOCUMENTO"
    CHILD_OWNER="", CHILD_TABLE="DOCUMENTO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_DOT_DOC", FK_COLUMNS="DOT_ID" */
    IF EXISTS (
      SELECT * FROM deleted,DOCUMENTO
      WHERE
        /*  %JoinFKPK(DOCUMENTO,deleted," = "," AND") */
        DOCUMENTO.DOT_ID = deleted.DOT_ID
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete TIPO_DOCUMENTO because DOCUMENTO exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_TIPO_DOCUMENTO ON TIPO_DOCUMENTO FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on TIPO_DOCUMENTO */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insDOT_ID char(1),
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* TIPO_DOCUMENTO  DOCUMENTO on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="00010cdb", PARENT_OWNER="", PARENT_TABLE="TIPO_DOCUMENTO"
    CHILD_OWNER="", CHILD_TABLE="DOCUMENTO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_DOT_DOC", FK_COLUMNS="DOT_ID" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(DOT_ID)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,DOCUMENTO
      WHERE
        /*  %JoinFKPK(DOCUMENTO,deleted," = "," AND") */
        DOCUMENTO.DOT_ID = deleted.DOT_ID
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update TIPO_DOCUMENTO because DOCUMENTO exists.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_TIPO_PERMISO ON TIPO_PERMISO FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on TIPO_PERMISO */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* TIPO_PERMISO  PERMISO on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="0000ed72", PARENT_OWNER="", PARENT_TABLE="TIPO_PERMISO"
    CHILD_OWNER="", CHILD_TABLE="PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_PET_PER", FK_COLUMNS="PET_CODE" */
    IF EXISTS (
      SELECT * FROM deleted,PERMISO
      WHERE
        /*  %JoinFKPK(PERMISO,deleted," = "," AND") */
        PERMISO.PET_CODE = deleted.PET_CODE
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete TIPO_PERMISO because PERMISO exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_TIPO_PERMISO ON TIPO_PERMISO FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on TIPO_PERMISO */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insPET_CODE char(3),
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* TIPO_PERMISO  PERMISO on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="0001011e", PARENT_OWNER="", PARENT_TABLE="TIPO_PERMISO"
    CHILD_OWNER="", CHILD_TABLE="PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_PET_PER", FK_COLUMNS="PET_CODE" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(PET_CODE)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,PERMISO
      WHERE
        /*  %JoinFKPK(PERMISO,deleted," = "," AND") */
        PERMISO.PET_CODE = deleted.PET_CODE
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update TIPO_PERMISO because PERMISO exists.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_USUARIO ON USUARIO FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on USUARIO */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* USUARIO  USUARIO_PERMISO on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="0002fcfd", PARENT_OWNER="", PARENT_TABLE="USUARIO"
    CHILD_OWNER="", CHILD_TABLE="USUARIO_PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_USU_USP", FK_COLUMNS="USU_LOGIN" */
    IF EXISTS (
      SELECT * FROM deleted,USUARIO_PERMISO
      WHERE
        /*  %JoinFKPK(USUARIO_PERMISO,deleted," = "," AND") */
        USUARIO_PERMISO.USU_LOGIN = deleted.USU_LOGIN
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete USUARIO because USUARIO_PERMISO exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* USUARIO  LOTE on parent delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="USUARIO"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_USU_LOT", FK_COLUMNS="USU_LOGIN" */
    IF EXISTS (
      SELECT * FROM deleted,LOTE
      WHERE
        /*  %JoinFKPK(LOTE,deleted," = "," AND") */
        LOTE.USU_LOGIN = deleted.USU_LOGIN
    )
    BEGIN
      SELECT @errno  = 30001,
             @errmsg = 'Cannot delete USUARIO because LOTE exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* SUCURSAL  USUARIO on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="SUCURSAL"
    CHILD_OWNER="", CHILD_TABLE="USUARIO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_SUC_USU", FK_COLUMNS="SUC_ID" */
    IF EXISTS (SELECT * FROM deleted,SUCURSAL
      WHERE
        /* %JoinFKPK(deleted,SUCURSAL," = "," AND") */
        deleted.SUC_ID = SUCURSAL.SUC_ID AND
        NOT EXISTS (
          SELECT * FROM USUARIO
          WHERE
            /* %JoinFKPK(USUARIO,SUCURSAL," = "," AND") */
            USUARIO.SUC_ID = SUCURSAL.SUC_ID
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last USUARIO because SUCURSAL exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_USUARIO ON USUARIO FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on USUARIO */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insUSU_LOGIN varchar(20),
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* USUARIO  USUARIO_PERMISO on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="00036b06", PARENT_OWNER="", PARENT_TABLE="USUARIO"
    CHILD_OWNER="", CHILD_TABLE="USUARIO_PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_USU_USP", FK_COLUMNS="USU_LOGIN" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(USU_LOGIN)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,USUARIO_PERMISO
      WHERE
        /*  %JoinFKPK(USUARIO_PERMISO,deleted," = "," AND") */
        USUARIO_PERMISO.USU_LOGIN = deleted.USU_LOGIN
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update USUARIO because USUARIO_PERMISO exists.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* USUARIO  LOTE on parent update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="USUARIO"
    CHILD_OWNER="", CHILD_TABLE="LOTE"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_USU_LOT", FK_COLUMNS="USU_LOGIN" */
  IF
    /* %ParentPK(" OR",UPDATE) */
    UPDATE(USU_LOGIN)
  BEGIN
    IF EXISTS (
      SELECT * FROM deleted,LOTE
      WHERE
        /*  %JoinFKPK(LOTE,deleted," = "," AND") */
        LOTE.USU_LOGIN = deleted.USU_LOGIN
    )
    BEGIN
      SELECT @errno  = 30005,
             @errmsg = 'Cannot update USUARIO because LOTE exists.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* SUCURSAL  USUARIO on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="SUCURSAL"
    CHILD_OWNER="", CHILD_TABLE="USUARIO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_SUC_USU", FK_COLUMNS="SUC_ID" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(SUC_ID)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,SUCURSAL
        WHERE
          /* %JoinFKPK(inserted,SUCURSAL) */
          inserted.SUC_ID = SUCURSAL.SUC_ID
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    select @nullcnt = count(*) from inserted where
      inserted.SUC_ID IS NULL
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update USUARIO because SUCURSAL does not exist.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go




CREATE TRIGGER tD_USUARIO_PERMISO ON USUARIO_PERMISO FOR DELETE AS
/* ERwin Builtin Trigger */
/* DELETE trigger on USUARIO_PERMISO */
BEGIN
  DECLARE  @errno   int,
           @errmsg  varchar(255)
    /* ERwin Builtin Trigger */
    /* USUARIO  USUARIO_PERMISO on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00027ec4", PARENT_OWNER="", PARENT_TABLE="USUARIO"
    CHILD_OWNER="", CHILD_TABLE="USUARIO_PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_USU_USP", FK_COLUMNS="USU_LOGIN" */
    IF EXISTS (SELECT * FROM deleted,USUARIO
      WHERE
        /* %JoinFKPK(deleted,USUARIO," = "," AND") */
        deleted.USU_LOGIN = USUARIO.USU_LOGIN AND
        NOT EXISTS (
          SELECT * FROM USUARIO_PERMISO
          WHERE
            /* %JoinFKPK(USUARIO_PERMISO,USUARIO," = "," AND") */
            USUARIO_PERMISO.USU_LOGIN = USUARIO.USU_LOGIN
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last USUARIO_PERMISO because USUARIO exists.'
      GOTO ERROR
    END

    /* ERwin Builtin Trigger */
    /* PERMISO  USUARIO_PERMISO on child delete no action */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="PERMISO"
    CHILD_OWNER="", CHILD_TABLE="USUARIO_PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_PER_USP", FK_COLUMNS="PER_ID" */
    IF EXISTS (SELECT * FROM deleted,PERMISO
      WHERE
        /* %JoinFKPK(deleted,PERMISO," = "," AND") */
        deleted.PER_ID = PERMISO.PER_ID AND
        NOT EXISTS (
          SELECT * FROM USUARIO_PERMISO
          WHERE
            /* %JoinFKPK(USUARIO_PERMISO,PERMISO," = "," AND") */
            USUARIO_PERMISO.PER_ID = PERMISO.PER_ID
        )
    )
    BEGIN
      SELECT @errno  = 30010,
             @errmsg = 'Cannot delete last USUARIO_PERMISO because PERMISO exists.'
      GOTO ERROR
    END


    /* ERwin Builtin Trigger */
    RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


CREATE TRIGGER tU_USUARIO_PERMISO ON USUARIO_PERMISO FOR UPDATE AS
/* ERwin Builtin Trigger */
/* UPDATE trigger on USUARIO_PERMISO */
BEGIN
  DECLARE  @NUMROWS int,
           @nullcnt int,
           @validcnt int,
           @insUSU_LOGIN varchar(20), 
           @insPER_ID integer,
           @errno   int,
           @errmsg  varchar(255)

  SELECT @NUMROWS = @@rowcount
  /* ERwin Builtin Trigger */
  /* USUARIO  USUARIO_PERMISO on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00028679", PARENT_OWNER="", PARENT_TABLE="USUARIO"
    CHILD_OWNER="", CHILD_TABLE="USUARIO_PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_USU_USP", FK_COLUMNS="USU_LOGIN" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(USU_LOGIN)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,USUARIO
        WHERE
          /* %JoinFKPK(inserted,USUARIO) */
          inserted.USU_LOGIN = USUARIO.USU_LOGIN
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update USUARIO_PERMISO because USUARIO does not exist.'
      GOTO ERROR
    END
  END

  /* ERwin Builtin Trigger */
  /* PERMISO  USUARIO_PERMISO on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="PERMISO"
    CHILD_OWNER="", CHILD_TABLE="USUARIO_PERMISO"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="FK_PER_USP", FK_COLUMNS="PER_ID" */
  IF
    /* %ChildFK(" OR",UPDATE) */
    UPDATE(PER_ID)
  BEGIN
    SELECT @nullcnt = 0
    SELECT @validcnt = count(*)
      FROM inserted,PERMISO
        WHERE
          /* %JoinFKPK(inserted,PERMISO) */
          inserted.PER_ID = PERMISO.PER_ID
    /* %NotnullFK(inserted," IS NULL","select @nullcnt = count(*) from inserted where"," AND") */
    
    IF @validcnt + @nullcnt != @NUMROWS
    BEGIN
      SELECT @errno  = 30007,
             @errmsg = 'Cannot update USUARIO_PERMISO because PERMISO does not exist.'
      GOTO ERROR
    END
  END


  /* ERwin Builtin Trigger */
  RETURN
ERROR:
    raiserror @errno @errmsg
    rollback transaction
END

go


