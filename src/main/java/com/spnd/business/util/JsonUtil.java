package com.spnd.business.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spnd.business.dto.LogDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.spnd.constants.LogAnalyzerConstants.*;

import java.io.IOException;
import java.util.List;

public class JsonUtil {
    private static JsonFactory jsonFactory = new JsonFactory();
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static LogDetails parseToObject(String jsonStr) throws IOException {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering parseToObject(jsonStr={})", jsonStr);
        }
        ObjectMapper mapper = new ObjectMapper();
        LogDetails logDetails = mapper.readValue(jsonStr, LogDetails.class);
        if(logger.isDebugEnabled()) {
            logger.debug("Leaving parseToObject(): {}", logDetails);
        }
        return logDetails;
    }



    public static Long calculateDuration(List<String> jsonRecordList) {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering calculateDuration(jsonRecordList={})", jsonRecordList);
        }
        Long startTime = 0L;
        Long endTime = 0L;

        for(String jsonRecord : jsonRecordList) {
            if(STARTED.equals(getTextFieldValue("state", jsonRecord))) {
                startTime = getTimestamp(jsonRecord);
            }else {
                endTime = getTimestamp(jsonRecord);
            }
        }
        if(logger.isDebugEnabled()) {
            logger.debug("Entering calculateDuration(): {}", endTime-startTime);
        }

        return  endTime-startTime;
    }
    public static String getId(String json) {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering getId(json={})", json);
        }
        String id = getTextFieldValue("id", json);
        if(logger.isDebugEnabled()) {
            logger.debug("Leaving getId(): {}", id);
        }
        return id;
    }

    public static Long getTimestamp(String json) {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering getTimestamp(json={})", json);
        }
        Long timestamp = getLongFieldValue("timestamp", json);
        if(logger.isDebugEnabled()) {
            logger.debug("Leaving getTimestamp(): {}", timestamp);
        }
        return timestamp;
    }

    public static String getTextFieldValue(String fieldName, String json) {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering getTextFieldValue(fieldName={}, json={})", fieldName, json);
        }
        String returnVal=null;
        try(JsonParser jsonParser = jsonFactory.createParser(json);) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String jsonFieldName = jsonParser.currentName();

                if (fieldName.equals(jsonFieldName)) {
                    returnVal = jsonParser.nextTextValue();
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Exception occurder while extracting JSON test value", e);
        }
        if(logger.isDebugEnabled()) {
            logger.debug("Leaving getTextFieldValue(): {}", returnVal);
        }
        return returnVal;
    }

    public static Long getLongFieldValue(String fieldName, String json) {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering getLongFieldValue(fieldName={}, json={})", fieldName, json);
        }
        Long returnVal=null;
        try(JsonParser jsonParser = jsonFactory.createParser(json);) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String jsonFieldName = jsonParser.currentName();

                if (fieldName.equals(jsonFieldName)) {
                    //jsonParser.nextToken();
                    returnVal = jsonParser.nextLongValue(0L);
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("Exception occurder while extracting JSON Long value", e);
        }
        if(logger.isDebugEnabled()) {
            logger.debug("Leaving getLongFieldValue(): {}", returnVal);
        }
        return returnVal;
    }



}
