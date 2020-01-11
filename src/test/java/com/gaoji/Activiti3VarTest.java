package com.gaoji;

import com.itheima.pojo.Holiday;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程变量测试
 * 建立bpmn -> 流程部署 -> 流程启动(设置流程变量)
 *
 */
public class Activiti3VarTest {


    /**
     * 流程部署
     */
    @Test
    public void testA(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();


        RepositoryService repositoryService = processEngine.getRepositoryService ();

        Deployment deployment = repositoryService.createDeployment ()
                .addClasspathResource ( "bpmn/holiday2.bpmn" )
                .addClasspathResource ( "bpmn/holiday2.png" )
                .deploy ();

        System.out.println (deployment.getKey ());
        System.out.println (deployment.getId ());
        System.out.println (deployment.getDeploymentTime ());
        System.out.println (deployment.getCategory ());
    }


    /**
     * 启动一个流程实例,
     *  设置流程变量  方式一   runtimeService.startProcessInstanceByKey( "holiday-var", map );
     *              方式二   taskService.complete ( task.getId (), map);
     */
    @Test
    public void testB(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();
        RuntimeService runtimeService = processEngine.getRuntimeService ();
        Map<String,Object> map=new HashMap<> (  );
        //设置流程变量
        Holiday holiday=new Holiday ();
        holiday.setNum ( 3D );
        map.put ( "holiday",holiday);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey ( "holiday-var", map );

        System.out.println (processInstance.getBusinessKey ());
        System.out.println (processInstance.getDeploymentId ());
        System.out.println (processInstance.getName ());
    }


    /**
     * 测试 处理任务
     */
    @Test
    public void testC(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();
        TaskService taskService = processEngine.getTaskService ();
        Task task = taskService.createTaskQuery ().processDefinitionKey ( "holiday-var" ).taskAssignee ( "zhaoliu" ).singleResult ();

        if (task!=null){
            taskService.complete ( task.getId ());
            System.out.println (task.getAssignee ()+"了处理任务");
        }


    }










}
