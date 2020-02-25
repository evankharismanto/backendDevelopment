package com.backendDevelopment.withtest.dbrestservice;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;

@RunWith(JUnitPlatform.class)
//@Suite.SuiteClasses({OrderRestUnitTest.class, OrderServiceIntegrationTest.class})
@SelectPackages({"com.backendDevelopment.withtest.dbrestservice.unittest","com.backendDevelopment.withtest.dbrestservice.integrationtest"})
/*@SelectClasses({OrderServiceIntegrationTest.class,
        OrderRestUnitTest.class})*/
public class DbrestserviceApplicationTests{
	//@Test
	//void contextLoads() {
	//}
}
