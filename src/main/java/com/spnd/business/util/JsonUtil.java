package com.spnd.business.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spnd.business.dto.LogDetails;
import static com.spnd.constants.LogAnalyzerConstants.*;

import java.io.IOException;
import java.util.List;

public class JsonUtil {
    private static JsonFactory jsonFactory = new JsonFactory();

    public static LogDetails parseToObject(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        LogDetails logDetails = mapper.readValue(jsonStr, LogDetails.class);
        return logDetails;
    }



    public static Long calculateDuration(List<String> jsonRecordList) {
        Long startTime = 0L;
        Long endTime = 0L;

        for(String jsonRecord : jsonRecordList) {
            if(STARTED.equals(getTextFieldValue("state", jsonRecord))) {
                startTime = getTimestamp(jsonRecord);
            }else {
                endTime = getTimestamp(jsonRecord);
            }
        }

        return  endTime-startTime;
    }
    public static String getId(String json) {
        return getTextFieldValue("id", json);
    }

    public static Long getTimestamp(String json) {
        return getLongFieldValue("timestamp", json);
    }

    public static String getTextFieldValue(String fieldName, String json) {
        String returnVal=null;
        try(JsonParser jsonParser = jsonFactory.createParser(json);) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String jsonFieldName = jsonParser.currentName();

                if (fieldName.equals(jsonFieldName)) {
                    //jsonParser.nextToken();
                    returnVal = jsonParser.nextTextValue();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnVal;
    }

    public static Long getLongFieldValue(String fieldName, String json) {
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
            e.printStackTrace();
        }
        return returnVal;
    }



}
