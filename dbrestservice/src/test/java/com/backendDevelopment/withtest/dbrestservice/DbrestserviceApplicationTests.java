package com.backendDevelopment.withtest.dbrestservice;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({"com.backendDevelopment.withtest.dbrestservice.unittest","com.backendDevelopment.withtest.dbrestservice.integrationtest"})
public class DbrestserviceApplicationTests{
}
