package com.spnd.business.processsor;

import com.spnd.business.processor.LogAnalyzerProcessor;
import com.spnd.business.processor.LogAnalyzerProcessorImpl;
import com.spnd.business.processor.LogAnalyzerProcessorImplV2;
import com.spnd.business.processor.LogAnalyzerTask;
import com.spnd.helper.LogAnalyzerJunitHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class LogAnalyzerProcessorImplV2Test {

    @Mock
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @InjectMocks
    private LogAnalyzerProcessor logAnalyzerProcessor = new LogAnalyzerProcessorImplV2();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGroupedDataFromFile() throws IOException {
        when(threadPoolTaskExecutor.getActiveCount()).thenReturn(0);
        doNothing().when(threadPoolTaskExecutor).execute(any(LogAnalyzerTask.class));
        doNothing().when(threadPoolTaskExecutor).shutdown();

        Stream<String> contentStream = LogAnalyzerJunitHelper.getMockedStream();
        Map<String, List<String>> logMap = logAnalyzerProcessor.getGroupedDataFromFile(contentStream);

        verify(threadPoolTaskExecutor, atLeastOnce()).getActiveCount();
        verify(threadPoolTaskExecutor, atLeastOnce()).execute(any(LogAnalyzerTask.class));
        verify(threadPoolTaskExecutor, times(1)).shutdown();

    }

}
