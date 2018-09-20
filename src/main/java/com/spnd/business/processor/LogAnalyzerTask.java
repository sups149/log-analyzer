package com.spnd.business.processor;

import com.spnd.business.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LogAnalyzerTask implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, List<String>> groupedLogMap;
    private String jsonData;

    public LogAnalyzerTask(Map<String, List<String>> groupedLogMap, String jsonData) {
        this.groupedLogMap = groupedLogMap;
        this.jsonData = jsonData;
    }

    @Override
    public void run() {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering run() with jsonData {}", jsonData);
        }
        List<String> jsonList = groupedLogMap.putIfAbsent(JsonUtil.getId(jsonData), (new LinkedList<>(Arrays.asList(jsonData))));
        if (jsonList != null) {
            jsonList.add(jsonData);
        }
        if(logger.isDebugEnabled()) {
            logger.debug("Leaving run()");
        }
    }
}
