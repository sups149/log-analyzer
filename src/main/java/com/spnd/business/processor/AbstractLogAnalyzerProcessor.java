package com.spnd.business.processor;

import com.spnd.business.util.JsonUtil;
import com.spnd.constants.LogAnalyzerConstants;
import com.spnd.data.entity.LogDetailsEntity;
import com.spnd.data.repository.LogAnalyzerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public abstract class AbstractLogAnalyzerProcessor implements LogAnalyzerProcessor{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogAnalyzerDao logAnalyzerDao;
    @Autowired
    private Environment env;

    public void processGrouppedData(Map<String, List<String>> logMap) {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering processGrouppedData(Map<String, List<String>>()");
        }
        logMap.forEach((eventId, jsonRecordList) -> {
            Long duration = JsonUtil.calculateDuration(jsonRecordList);
            int maxDuration = Integer.parseInt(env.getProperty(LogAnalyzerConstants.MAX_DURATION));
            if(duration > maxDuration) {
                if(logger.isDebugEnabled()) {
                    logger.debug("Event duration is beyond permissible limit: {}", duration);
                }
                LogDetailsEntity logDetails = populateLogDetailsParam(eventId, jsonRecordList.get(0), duration);
                try {
                    logAnalyzerDao.inserRecord(logDetails);
                } catch (Exception e) {
                    logger.error("Exception occured while persisting into DB : "+eventId, e);
                }
            }

        });
    }
}
