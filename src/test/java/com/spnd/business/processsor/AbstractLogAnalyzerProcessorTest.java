package com.spnd.business.processsor;

import com.spnd.business.processor.LogAnalyzerProcessor;
import com.spnd.business.processor.LogAnalyzerProcessorImpl;
import com.spnd.business.util.JsonUtil;
import com.spnd.data.entity.LogDetailsEntity;
import com.spnd.data.repository.LogAnalyzerDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.spnd.helper.LogAnalyzerJunitHelper;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
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
    @PrepareForTest(JsonUtil.class)
    public void testProcessGrouppedData() {
        Mockito.when(logAnalyzerDao.inserRecord(any(LogDetailsEntity.class))).thenReturn("42");
        Map<String, List<String>> grouppedData = LogAnalyzerJunitHelper.getDummyGrouppedData();
        spy(JsonUtil.class);
        List<String> jsonRecordList = LogAnalyzerJunitHelper.getDummyJsonRecordList();
        when(JsonUtil.calculateDuration(jsonRecordList)).thenReturn(10L);
        logAnalyzerProcessor.processGrouppedData(grouppedData);
        verify(logAnalyzerDao, atLeastOnce()).inserRecord(any(LogDetailsEntity.class));


    }
}
