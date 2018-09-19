package com.spnd.data.repository;

import com.spnd.config.LogAnalyzerConfig;
import com.spnd.helper.LogAnalyzerJunitHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


/*@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { LogAnalyzerConfig.class })
@ActiveProfiles("stream")*/
public class LogAnalyzerDaoImplIntegrationTest {
    //@Autowired
    public LogAnalyzerDao logAnalyzerDao;

    //@Test
    public void inserRecords() {
        logAnalyzerDao.inserRecord(LogAnalyzerJunitHelper.getDummyLogDetailsEntity());
    }


}
