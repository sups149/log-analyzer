package com.spnd.business.processsor;

import com.spnd.business.processor.LogAnalyzerProcessor;
import com.spnd.business.processor.LogAnalyzerProcessorImpl;
import com.spnd.data.entity.LogDetailsEntity;
import com.spnd.helper.LogAnalyzerJunitHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
public class LogAnalyzerProcessorTest {
    @Test
    public void testPopulateLogDetailsParam() {
        LogAnalyzerProcessor logAnalyzerProcessor = new LogAnalyzerProcessorImpl();
        LogDetailsEntity logDetailsEntity = logAnalyzerProcessor.populateLogDetailsParam("scsmbstgra", LogAnalyzerJunitHelper.getDummyJsonRecordStarted(), 8L);
        Assert.assertEquals("12345", logDetailsEntity.getHost());
    }

    //It is not yet possible to test interface default method using Mocks
    public void testPopulateLogDetailsParam2() {
        LogAnalyzerProcessor logAnalyzerProcessor = mock(LogAnalyzerProcessor.class, Mockito.CALLS_REAL_METHODS);
        LogDetailsEntity logDetailsEntity = logAnalyzerProcessor.populateLogDetailsParam("scsmbstgra", LogAnalyzerJunitHelper.getDummyJsonRecordStarted(), 8L);
        Assert.assertEquals("12345", logDetailsEntity.getHost());
    }

}
