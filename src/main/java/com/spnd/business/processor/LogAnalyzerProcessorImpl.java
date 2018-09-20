package com.spnd.business.processor;

import com.spnd.data.repository.LogAnalyzerDao;
import com.spnd.data.entity.LogDetailsEntity;
import com.spnd.business.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Profile("stream")
public class LogAnalyzerProcessorImpl extends AbstractLogAnalyzerProcessor{
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /*
    This method leverage parallel processing of Streams.
     */
    public Map<String,List<String>> getGroupedDataFromFile(Stream<String> contentStream) throws IOException {
        logger.info("Entering getGroupedDataFromFile(Stream<String>)");
        Map<String, List<String>> logMap =  contentStream.parallel().collect(Collectors.groupingBy(JsonUtil::getId));
        logger.info("Leaving getGroupedDataFromFile()");
        return logMap;
    }
}
