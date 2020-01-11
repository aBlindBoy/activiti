package com.chuji;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;


/**
 * ----------------------------------------------
 *  流程定义部署
 * --------------------------------------------
 */

public class Activiti2DeployTest {


    /**
     * 影响的表
     *  act_re_deployment
     *  act_re_procdef
     *  act_ge_bytearray  存储bpmn和png文件
     */
    @Test
    public void test(){
        //1.获得ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        //2.获得RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService ();

        Deployment deployment = repositoryService.createDeployment ()
                .addClasspathResource ( "bpmn/holiday.bpmn" )
                .addClasspathResource ( "bpmn/holiday.png" )
                .name ( "请假流程" )
                .deploy ();

        System.out.println (deployment.getCategory ());
        System.out.println (deployment.getDeploymentTime ());
        System.out.println (deployment.getId ());
        System.out.println (deployment.getKey ());
        System.out.println (deployment.getName ());
    }


}
