package com.greg.gmall.config;

import javax.annotation.PostConstruct;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.config.MethodInvokingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({ "com.greg.gmall" })
@Configuration
public class SpringRootConfig {
	
	/*@PostConstruct
	public void startDBManager() {
		
		//hsqldb
		//DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

		//derby
		//DatabaseManagerSwing.main(new String[] { "--url", "jdbc:derby:memory:testdb", "--user", "", "--password", "" });

		//h2
		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE;TRACE_LEVEL_FILE=3", "--user", "sa", "--password", "" });

	}
	*/
	//MethodInvokingBean example
	
/*	@PostConstruct
	public void startDBM() {
		MethodInvokingBean mBean = new MethodInvokingBean();
		mBean.setTargetClass(DatabaseManagerSwing.class);
		mBean.setTargetMethod("main");
		String[] args = new String[] { "--url", "jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE;TRACE_LEVEL_FILE=3", "--user", "sa", "--password", "" };
		mBean.setArguments(args);
		try {
			mBean.prepare();
			mBean.invoke();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/

}
