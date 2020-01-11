package com.gaoji;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 组任务(查询组任务,拾取组任务,归还组任务,交接任务,办理任务)
 */
public class Archive4GroupTest {


    /**
     * 部署流程
     */
    @Test
    public void testA(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        RepositoryService repositoryService = processEngine.getRepositoryService ();

        Deployment deploy = repositoryService.createDeployment ().addClasspathResource ( "bpmn/archive.bpmn" )
                .name ( "档案销毁流程" ).deploy ();

        System.out.println (deploy.getId ());
        System.out.println (deploy.getDeploymentTime ());
    }


    /**
     * 启动流程实例
     */
    @Test
    public void testB(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        ProcessInstance processInstance = processEngine.getRuntimeService ().startProcessInstanceByKey ( "archive" );

        System.out.println (processInstance.getId ());
        System.out.println (processInstance.getName ());

    }

    /**
     * 查询当前用户组任务(拾取任务,归还任务,交接任务)
     */
    @Test
    public void testC(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        TaskService taskService = processEngine.getTaskService ();

        Task task = taskService.createTaskQuery ( ).processDefinitionKey (  "archive" ).taskCandidateOrAssigned ("小红" ).singleResult ();


        if (task!=null){
            //拾取任务
            taskService.claim ( task.getId (),"小红" );

            //归还任务
            //taskService.claim ( task.getId (),null);

            //交接任务
            //taskService.setAssignee ( task.getId (),"小红" );

            //处理任务
            //taskService.complete ( task.getId () );


            Map<String,Object> map=new HashMap<> (  );
            map.put ( "flag",true );
            taskService.complete ( task.getId () ,map);

            System.out.println ("操作成功");
        }
    }






}
