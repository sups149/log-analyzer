package com.spnd.business.processor;

import com.spnd.business.util.JsonUtil;
import com.spnd.data.entity.LogDetailsEntity;
import com.spnd.data.repository.LogAnalyzerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public abstract class AbstractLogAnalyzerProcessor implements LogAnalyzerProcessor{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogAnalyzerDao logAnalyzerDao;

    public void processGrouppedData(Map<String, List<String>> logMap) {
        List<LogDetailsEntity> logDetailsList = new LinkedList<>();

        logMap.forEach((eventId, jsonRecordList) -> {
            Long duration = JsonUtil.calculateDuration(jsonRecordList);
            if(duration > 4) {
                logger.info("Event duration is beyond permissible limit");
                LogDetailsEntity logDetails = populateLogDetailsParam(eventId, jsonRecordList.get(0), duration);

                logAnalyzerDao.inserRecord(logDetails);
                //logDetailsList.add(logDetails);
            }

        });

        logAnalyzerDao.inserRecords(logDetailsList);
    }
}
