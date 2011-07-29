package com.sterling.common.mysql;


public class MSSqlDialect extends org.hibernate.dialect.SQLServerDialect {

	public MSSqlDialect() {
		super();
		/*registerColumnType(Types.CHAR, "char($l)" );
		registerColumnType( Types.VARBINARY, 32000, "varbyte" ); 
		registerHibernateType( Types.CHAR, Hibernate.STRING.getName() );*/
	}
}
