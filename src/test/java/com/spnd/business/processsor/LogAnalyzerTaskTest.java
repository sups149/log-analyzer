package com.spnd.business.processsor;

import com.spnd.business.processor.LogAnalyzerTask;
import com.spnd.helper.LogAnalyzerJunitHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class LogAnalyzerTaskTest {
    @Test
    public void testRun() {
        ConcurrentMap<String, List<String>> logMap = new ConcurrentHashMap<>();
        Runnable logAnalyzerTask1 = new LogAnalyzerTask(logMap, LogAnalyzerJunitHelper.getDummyJsonRecordStarted());
        Runnable logAnalyzerTask2 = new LogAnalyzerTask(logMap, LogAnalyzerJunitHelper.getDummyJsonRecordFinished());
        logAnalyzerTask1.run();
        logAnalyzerTask2.run();
        if(logMap != null && logMap.isEmpty()) {
            Map.Entry<String, List<String>> entry = logMap.entrySet().iterator().next();
            Assert.assertEquals("scsmbstgra", entry.getKey());
        }

    }
}
