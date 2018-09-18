package com.spnd.business.processor;

import com.spnd.data.entity.LogDetailsEntity;
import com.spnd.business.util.JsonUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface LogAnalyzerProcessor {

    Map<String,List<String>> getGroupedDataFromFile(Stream<String> contentStream) throws IOException;
    void processGrouppedData(Map<String, List<String>> logMap);

    default LogDetailsEntity populateLogDetailsParam(String eventId, String jsonRecord, Long duration) {
        LogDetailsEntity logDetails = new LogDetailsEntity();
        logDetails.setDuration(duration);
        logDetails.setEventId(eventId);
        logDetails.setType(JsonUtil.getTextFieldValue("type", jsonRecord));
        logDetails.setHost(JsonUtil.getTextFieldValue("host", jsonRecord));
        logDetails.setAlert(true);
        return logDetails;
    }
}
