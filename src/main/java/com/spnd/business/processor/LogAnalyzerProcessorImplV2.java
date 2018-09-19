package com.spnd.business.processor;

import com.spnd.data.repository.LogAnalyzerDao;
import com.spnd.data.entity.LogDetailsEntity;
import com.spnd.business.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

@Component
@Profile("taskexecutor")
public class LogAnalyzerProcessorImplV2 extends AbstractLogAnalyzerProcessor {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    public Map<String,List<String>> getGroupedDataFromFile(Stream<String> contentStream) throws IOException {
        logger.info("Starting getGroupedDataFromFile method of LogAnalyzerProcessorImplV2");
        ConcurrentMap<String, List<String>> logMap = new ConcurrentHashMap<>();
        contentStream.forEach(jsonRecord -> {
            try{
                threadPoolTaskExecutor.execute(new LogAnalyzerTask(logMap, jsonRecord));
            } catch (Exception e) {
                logger.error("Exception occured while processing record: "+jsonRecord, e);
            }
        });
        while (true) {
            if(threadPoolTaskExecutor.getActiveCount() == 0) {
                logger.info("Shutting down Executor");
                threadPoolTaskExecutor.shutdown();
                break;
            }
        }
        return logMap;
    }
}
