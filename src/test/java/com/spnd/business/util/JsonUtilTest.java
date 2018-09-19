package com.spnd.business.util;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.spnd.business.dto.LogDetails;
import com.spnd.helper.LogAnalyzerJunitHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.spnd.business.util.*")
public class JsonUtilTest {
    @Test
    public void testGetFieldValue() {
        String jsonString = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\"host\":\"12345\", \"timestamp\":1491377495212}";
        String output = JsonUtil.getTextFieldValue("id", jsonString);

        Assert.assertEquals("scsmbstgra", output);
    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void testParseToObject() throws IOException {
        String jsonString = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\",\"host\":\"12345\",\"testattr\":\"attrval\", \"timestamp\":1491377495212}";
        LogDetails output = JsonUtil.parseToObject(jsonString);
    }

    @Test
    public void testCalculateDuration() {
        List<String> jsonRecordList = LogAnalyzerJunitHelper.getDummyJsonRecordList();
        LogAnalyzerJunitHelper.mockedJsonUtil();
        Long duration = JsonUtil.calculateDuration(jsonRecordList);
        Long expected = 8L;
        Assert.assertEquals(expected, duration);


    }

    @Test
    public void testGetTextFieldValue() {
        String jsonRecord = LogAnalyzerJunitHelper.getDummyJsonRecordStarted();
        String fieldValue = JsonUtil.getTextFieldValue("id", jsonRecord);
        Assert.assertEquals("scsmbstgra", fieldValue);
    }

    @Test
    public void testGetLongFieldValue() {
        String jsonRecord = LogAnalyzerJunitHelper.getDummyJsonRecordStarted();
        Long fieldValue = JsonUtil.getLongFieldValue("timestamp", jsonRecord);
        Long expected = 1491377495212L;
        Assert.assertEquals(expected, fieldValue);
    }


}
