package com.spnd.business.processsor;

import com.spnd.business.processor.LogAnalyzerProcessor;
import com.spnd.business.processor.LogAnalyzerProcessorImpl;
import com.spnd.data.entity.LogDetailsEntity;
import com.spnd.helper.LogAnalyzerJunitHelper;
import org.junit.Assert;
import org.junit.Test;


public class LogAnalyzerProcessorTest {
    @Test
    public void testPopulateLogDetailsParam() {
        LogAnalyzerProcessor logAnalyzerProcessor = new LogAnalyzerProcessorImpl();
        LogDetailsEntity logDetailsEntity = logAnalyzerProcessor.populateLogDetailsParam("scsmbstgra", LogAnalyzerJunitHelper.getDummyJsonRecord(), 8L);
        Assert.assertEquals("12345", logDetailsEntity.getHost());
    }

}
