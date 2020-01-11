package com.chuji;


import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.junit.Test;

import java.util.List;

public class Activiti5HistoryServiceTest {




    @Test
    public void testA(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine ();

        HistoryService historyService = processEngine.getHistoryService ();

        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery ();

        List<HistoricActivityInstance> list =   instanceQuery.processInstanceId ( "25001" ).orderByHistoricActivityInstanceStartTime ().asc ().list ();

        for (HistoricActivityInstance historicActivityInstance:list) {

            System.out.println(historicActivityInstance.getActivityId());
            System.out.println (historicActivityInstance.getActivityName ());
            System.out.println("===================================================");
        }

    }






}
