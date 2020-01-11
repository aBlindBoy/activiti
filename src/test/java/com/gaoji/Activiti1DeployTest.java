package com.gaoji;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;


public class Activiti1DeployTest {


    /**
     * 启动一个流程实例并关联自己的业务逻辑buinessKey
     */
    @Test
    public void testA(){

        //获得ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        //获得RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService ();

        //创建流程实例,通过流程应以的key(bpmn里id) holiday_1
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey ( "holiday_1","1001" );

        System.out.println (processInstance.getBusinessKey ());
    }



}
