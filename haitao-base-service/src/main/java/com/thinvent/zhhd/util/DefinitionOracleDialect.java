package com.thinvent.zhhd.util;

import org.hibernate.dialect.MySQL5Dialect;

/**   
* @author tanshumei
* @Description: 自定义 OracleDialect 扩展方言库
* 解决数据类型不匹配问题
*/	
public class DefinitionOracleDialect extends MySQL5Dialect{
	public DefinitionOracleDialect(){
		super();   
        registerHibernateType(1, "string");   
        registerHibernateType(-9, "string");
        registerHibernateType(-16, "string");   
        registerHibernateType(3, "double");  
	}
}
