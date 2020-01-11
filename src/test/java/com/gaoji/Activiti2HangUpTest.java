package com.gaoji;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * 流程的挂起与激活
 */
public class Activiti2HangUpTest {


    /**
     * 全部流程的挂起与激活
     */
    @Test
    public void testA(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        RepositoryService repositoryService = processEngine.getRepositoryService ();

        //根据流程的key查询流程信息
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery ();

        ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionKey ( "holiday_1" ).singleResult ();

        String id = processDefinition.getId ();

        boolean suspended = processDefinition.isSuspended ();

        if (suspended){
            repositoryService.activateProcessDefinitionById (id,true,null  );
            System.out.println ("流程定义:"+id+"激活");
        }else{
            repositoryService.suspendProcessDefinitionById ( id,true,null );
            System.out.println ("流程定义:"+id+"挂起");

        }

    }



    /**
     * 单个流程的挂起与激活
     */
    @Test
    public void testB(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        RuntimeService runtimeService = processEngine.getRuntimeService ();

        //根据流程的key查询流程信息
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery ().processDefinitionKey ( "holiday_1" ).processDefinitionId ( "holiday_1:1:22504" ).singleResult ();

        String id = processInstance.getId ();

        boolean suspended = processInstance.isSuspended ();

        if (suspended){
            runtimeService.activateProcessInstanceById (id );
            System.out.println ("流程定义:"+id+"激活");
        }else{
            runtimeService.suspendProcessInstanceById ( id);
            System.out.println ("流程定义:"+id+"挂起");

        }

    }




}
