package com.chuji;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;


/**
 * ----------------------------------------------
 *  完整的一套流程 启动流程实例->添加请假单->部门经理审批->总经理审批
 * --------------------------------------------
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Activiti3RuntimeService {





    /**启动一个流程实例
     * 受影响的表
     *  act_hi_actinst          已完成的活动
     *  act_hi_identitylink     参与者信息
     *  act_hi_procinst         流程实例
     *  act_hi_taskinst         任务实例
     *
     *  act_ru_execution        执行者
     *  act_ru_identitylink     参与者信息
     *  act_ru_task             任务
     */
    @Test
    public void testA() {
        //获得ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        //获得RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService ();

        //创建流程实例,通过流程应以的key(bpmn里id) holiday_1
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey ( "holiday_1" );

        //打印信息
        System.out.println ("流程部署ID"+processInstance.getDeploymentId ());
        System.out.println ("流程定义ID"+processInstance.getProcessDefinitionId ());
        System.out.println ("流程实例ID"+processInstance.getId ());
        System.out.println ("活动ID"+processInstance.getActivityId ());

    }

    /**
     * 查询当前用户的任务列表并处理
     */
    @Test
    public void testB(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        TaskService taskService = processEngine.getTaskService ();

        List<Task> tasks = taskService.createTaskQuery ()
                .processDefinitionKey ( "holiday_1" )
                .taskAssignee ( "zhangsan" )
                .list ();

        for (Task task:tasks) {
            taskService.complete ( task.getId () );
        }

    }


    /**
     * 查询部门经理的任务列表并处理
     */
    @Test
    public void testC(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        TaskService taskService = processEngine.getTaskService ();

        List<Task> tasks = taskService.createTaskQuery ()
                .processDefinitionKey ( "holiday_1" )
                .taskAssignee ( "lisi" )
                .list ();

        for (Task task:tasks) {
            taskService.complete ( task.getId () );
        }

    }
    /**
     * 查询经理的任务列表并处理
     */
    @Test
    @Ignore
    public void testD(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        TaskService taskService = processEngine.getTaskService ();

        List<Task> tasks = taskService.createTaskQuery ()
                .processDefinitionKey ( "holiday_1" )
                .taskAssignee ( "wangwu" )
                .list ();

        for (Task task:tasks) {
            taskService.complete ( task.getId () );
        }

    }



    /**
     * 查询当前用户的任务列表
     */
    @Test
    @Ignore
    public void test(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        TaskService taskService = processEngine.getTaskService ();

        List<Task> tasks = taskService.createTaskQuery ()
                .processDefinitionKey ( "holiday_1" )
                .taskAssignee ( "zhangsan" )
                .list ();

        for (Task task:tasks) {
            System.out.println ("任务实例Id:"+task.getProcessInstanceId ());
            System.out.println ("任务ID:"+task.getId ());
            System.out.println ("任务负责人:"+task.getAssignee ());
            System.out.println ("任务名称:"+task.getName ());

        }

    }
}
