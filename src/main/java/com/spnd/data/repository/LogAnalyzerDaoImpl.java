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
    //private HibernateConfiguation configuation = new HibernateConfiguation()
    @Override
    public void inserRecords(List<LogDetailsEntity> logDetailsList) {
        //Session session = HibernateConfiguation.getSession();

        Session session = sessionFactory.getCurrentSession();
        logDetailsList.forEach(logDetails -> {
            session.save(logDetails);
        });
    }

    @Override
    public Serializable inserRecord(LogDetailsEntity logDetails) {
        Session session = sessionFactory.getCurrentSession();
        Serializable ab = session.save(logDetails);
        return ab;
    }
}
