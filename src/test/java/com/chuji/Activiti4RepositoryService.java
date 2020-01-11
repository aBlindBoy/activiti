package com.chuji;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class Activiti4RepositoryService {


    /**
     * 流程定义信息查询
     */
    @Test
    public void testA() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        RepositoryService repositoryService = processEngine.getRepositoryService ();

        //创建一个查询器
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery ();

        //根据key值进行查询
        List<ProcessDefinition> lists = processDefinitionQuery
                .processDefinitionKey ( "holiday_1" )
                .orderByProcessDefinitionName ().desc ().list ();

        for (ProcessDefinition processDefinition : lists) {
            System.out.println ( "流程ID:" + processDefinition.getId () );
            System.out.println ( "流程名称:" + processDefinition.getName () );
            System.out.println ( "流程KEY:" + processDefinition.getKey () );
            System.out.println ( "流程版本:" + processDefinition.getVersion () );
        }

    }

    /**
     * 流程定义信息删除
     */
    @Test
    public void testB() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        RepositoryService repositoryService = processEngine.getRepositoryService ();

        //创建一个查询器
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery ();

        //根据key值进行查询
        ProcessDefinition processDefinition= processDefinitionQuery
                .processDefinitionKey ( "holiday-var" )
                .latestVersion ().singleResult ();

        //删除最后一个版本的流程定义,设置为true,表示级联删除(先删除未走完的流程,在删除流程)
        repositoryService.deleteDeployment ( processDefinition.getDeploymentId (),true );

    }


    /**
     * 获取流程定义的图片文件和bpmn文件
     */
    @Test
    public void testC(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        RepositoryService repositoryService = processEngine.getRepositoryService ();


        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery ();


        ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionKey ( "holiday_1" ).singleResult ();

        //等到部署id
        String deploymentId = processDefinition.getDeploymentId ();


        //等到图片文件,第一个参数部署id,第二个参数图资源的名字
        InputStream imageIs = repositoryService.getResourceAsStream ( deploymentId, processDefinition.getDiagramResourceName () );


        //得到bpmn文件
        InputStream bpmnIs = repositoryService.getResourceAsStream ( deploymentId, processDefinition.getResourceName () );


        //保存图片和bpmn图片
        try(OutputStream imageOs=new FileOutputStream ( "F:/"+processDefinition.getDiagramResourceName ());
            OutputStream bpmnOs=new FileOutputStream ( "F:/"+processDefinition.getResourceName ())){
            IOUtils.copy (imageIs ,imageOs );
            IOUtils.copy (bpmnIs ,bpmnOs );
        }catch(Exception e){
            e.printStackTrace ();
        }


    }


}

