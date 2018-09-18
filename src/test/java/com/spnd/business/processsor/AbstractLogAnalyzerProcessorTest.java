package com.spnd.business.processsor;

import com.spnd.business.processor.LogAnalyzerProcessor;
import com.spnd.business.processor.LogAnalyzerProcessorImpl;
import com.spnd.business.util.JsonUtil;
import com.spnd.data.entity.LogDetailsEntity;
import com.spnd.data.repository.LogAnalyzerDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.spnd.helper.LogAnalyzerJunitHelper;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

public class AbstractLogAnalyzerProcessorTest {

    @InjectMocks
    private LogAnalyzerProcessor logAnalyzerProcessor = new LogAnalyzerProcessorImpl();

    @Mock
    private LogAnalyzerDao logAnalyzerDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessGrouppedData() {
        Mockito.when(logAnalyzerDao.inserRecord(any(LogDetailsEntity.class))).thenReturn("42");
        Map<String, List<String>> grouppedData = LogAnalyzerJunitHelper.getDummyGrouppedData();
        spy(JsonUtil.class);
        when(JsonUtil.calculateDuration(any(List.class))).thenReturn(10L);
        System.out.println(JsonUtil.calculateDuration(new ArrayList<String>()));
        //logAnalyzerProcessor.processGrouppedData(grouppedData);

    }
}
