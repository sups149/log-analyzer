package com.spnd.data.repository;

import com.spnd.data.entity.LogDetailsEntity;

import java.io.Serializable;
import java.util.List;

public interface LogAnalyzerDao {
    void inserRecords(List<LogDetailsEntity> logDetailsList);
    Serializable inserRecord(LogDetailsEntity logDetails);
}
