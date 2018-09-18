package com.spnd.helper;

import com.spnd.business.util.JsonUtil;
import com.spnd.data.entity.LogDetailsEntity;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static org.powermock.api.mockito.PowerMockito.*;

public class LogAnalyzerJunitHelper {
    public static LogDetailsEntity getDummyLogDetailsEntity() {
        LogDetailsEntity logDetailsEntity = new LogDetailsEntity();
        logDetailsEntity.setDuration(8L);
        logDetailsEntity.setEventId("test");
        logDetailsEntity.setAlert(true);
        return logDetailsEntity;
    }

    public static String getDummyJsonRecord() {
        String jsonRecord = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\"host\":\"12345\", \"timestamp\":1491377495212}";
        return jsonRecord;
    }

    public static List<String> getDummyJsonRecordList() {
        String jsonRecord1 = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\"host\":\"12345\", \"timestamp\":1491377495212}";
        String jsonRecord2 = "{\"id\":\"scsmbstgra\", \"state\":\"FINISHED\", \"type\":\"APPLICATION_LOG\",\"host\":\"12345\", \"timestamp\":1491377495220}";
        List<String> jsonRecordList = new ArrayList<>();
        jsonRecordList.add(jsonRecord1);
        jsonRecordList.add(jsonRecord2);
        return jsonRecordList;
    }

    public static void mockedJsonUtil() {
        //JsonUtil jsonUtil = mock(JsonUtil.class);
        String jsonRecord1 = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\"host\":\"12345\", \"timestamp\":1491377495212}";
        String jsonRecord2 = "{\"id\":\"scsmbstgra\", \"state\":\"FINISHED\", \"type\":\"APPLICATION_LOG\",\"host\":\"12345\", \"timestamp\":1491377495220}";
       // mockStatic(JsonUtil.class);
        spy(JsonUtil.class);
        when(JsonUtil.getTimestamp(jsonRecord1)).thenReturn(1491377495212L);
        when(JsonUtil.getTimestamp(jsonRecord2)).thenReturn(1491377495220L);
    }

    public static Stream<String> getMockedStream() throws IOException {
        String jsonRecord1 = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\"host\":\"12345\", \"timestamp\":1491377495212}";
        String jsonRecord2 = "{\"id\":\"scsmbstgra\", \"state\":\"FINISHED\", \"type\":\"APPLICATION_LOG\",\"host\":\"12345\", \"timestamp\":1491377495220}";
        String[] strArr = {jsonRecord1, jsonRecord2};
        return Stream.of(strArr);
    }

    public static Map<String, List<String>> getDummyGrouppedData() {
        Map<String, List<String>> groupedData = new HashMap();
        groupedData.put("scsmbstgra", getDummyJsonRecordList());

        return groupedData;
    }
}
