package com.chuji;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * ----------------------------------------------
 *  创建Activiti所需的25张表
 * --------------------------------------------
 */

public class Activiti1TableTest {


    /**
     * 方式一
     */
    @Test
    public void test(){

        //创建ProcessEngineConfiguration
        ProcessEngineConfiguration configuration =
                ProcessEngineConfiguration
                        .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    //通过ProcessEngineConfiguration创建ProcessEngine，此时会创建数据库
        ProcessEngine processEngine =
                configuration.buildProcessEngine();
        System.out.println(processEngine);
    }


    /**
     * 方式二
     *  条件:1.配置文件名称必须为activiti.cfg.xml
     *      2.bean的id="processEngineConfiguration"
     */
    @Test
    public void test1(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine ();
        System.out.println (defaultProcessEngine);
    }


}
