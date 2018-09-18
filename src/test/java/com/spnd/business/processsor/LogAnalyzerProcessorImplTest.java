package com.spnd.business.processsor;

import com.spnd.business.processor.LogAnalyzerProcessor;
import com.spnd.business.processor.LogAnalyzerProcessorImpl;
import com.spnd.helper.LogAnalyzerJunitHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LogAnalyzerProcessorImplTest {


    @Test
    public void testMet() throws IOException {
        Stream<String> contentStream = LogAnalyzerJunitHelper.getMockedStream();
        LogAnalyzerProcessor logAnalyzerProcessor = new LogAnalyzerProcessorImpl();
        Map<String, List<String>> logMap = logAnalyzerProcessor.getGroupedDataFromFile(contentStream);
        if(logMap != null && logMap.isEmpty()) {
            Map.Entry<String, List<String>> entry = logMap.entrySet().iterator().next();
            Assert.assertEquals("scsmbstgra", entry.getKey());
        }






        //System.out.println(location.getPath());
    }
}
