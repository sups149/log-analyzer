package com.spnd.business.service;

import com.spnd.business.processor.LogAnalyzerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class LogAnalyzerService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogAnalyzerProcessor logAnalyzerProcessor;

    @Value("${file.path}")
    private String filePath;

    public void manageLogContent() {
        try(Stream<String> stream = Files.lines(Paths.get(filePath))) {

            Map<String, List<String>> logMap = logAnalyzerProcessor.getGroupedDataFromFile(stream);
            logAnalyzerProcessor.processGrouppedData(logMap);

        } catch (IOException e) {
            logger.error("Exception occured while processing file", e);
        }
    }



}
