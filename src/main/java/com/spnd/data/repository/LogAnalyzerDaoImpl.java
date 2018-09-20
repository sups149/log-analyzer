package com.spnd.data.repository;

import com.spnd.data.entity.LogDetailsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public class LogAnalyzerDaoImpl implements LogAnalyzerDao {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void inserRecords(List<LogDetailsEntity> logDetailsList) {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering inserRecords(List<LogDetailsEntity>)");
        }
        Session session = sessionFactory.getCurrentSession();
        logDetailsList.forEach(logDetails -> {
            if(logger.isDebugEnabled()) {
                logger.debug("Inserting record: {}", logDetails);
            }
            session.save(logDetails);
        });
        if(logger.isDebugEnabled()) {
            logger.debug("Entering inserRecords()");
        }
    }

    @Override
    public Serializable inserRecord(LogDetailsEntity logDetails) {
        if(logger.isDebugEnabled()) {
            logger.debug("Entering inserRecord(logDetails = {})", logDetails);
        }
        Session session = sessionFactory.getCurrentSession();
        Serializable ab = session.save(logDetails);
        if(logger.isDebugEnabled()) {
            logger.debug("Leaving inserRecord(): {}", ab);
        }
        return ab;
    }
}
