package com.sterling.common.mysql;

import java.sql.Types;

import org.hibernate.Hibernate;

public class MSSqlDialect extends org.hibernate.dialect.SQLServerDialect {

	public MSSqlDialect() {
		super();
		/*registerColumnType(Types.CHAR, "char($l)" );
		registerColumnType( Types.VARBINARY, 32000, "varbyte" ); 
		registerHibernateType( Types.CHAR, Hibernate.STRING.getName() );*/
	}
}
