package com.spnd.data.repository;

import com.spnd.data.entity.LogDetailsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class LogAnalyzerDaoImplJunitTest {
    @Mock
    private SessionFactory sessionFactory;

    @InjectMocks
    private LogAnalyzerDao logAnalyzerDao = new LogAnalyzerDaoImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testInserRecord() {
        Session session = mock(Session.class);
        when(session.save(any(LogDetailsEntity.class))).thenReturn(42L);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        LogDetailsEntity logDetailsEntity = new LogDetailsEntity();
        logAnalyzerDao.inserRecord(logDetailsEntity);
        //Verifying call on mock object
        verify(session, atLeastOnce()).save(logDetailsEntity);
    }

    @Test
    public void testInserRecords() {
        Session session = mock(Session.class);
        when(session.save(any(LogDetailsEntity.class))).thenReturn(42L);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        List<LogDetailsEntity> logDetailsEntities = new ArrayList<>();
        LogDetailsEntity logDetailsEntity = new LogDetailsEntity();
        logDetailsEntities.add(logDetailsEntity);
        logDetailsEntities.add(logDetailsEntity);
        logAnalyzerDao.inserRecords(logDetailsEntities);
        //Verifying call on mock object
        verify(session, times(2)).save(logDetailsEntity);
    }

}
