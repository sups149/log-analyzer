package com.spnd;


import com.spnd.business.service.LogAnalyzerService;
import com.spnd.config.LogAnalyzerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LogAnalyzerApp {
    private static Logger logger = LoggerFactory.getLogger(LogAnalyzerApp.class);

    public static void main(String[] args) {
        logger.info("Starting log analyzer App");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LogAnalyzerConfig.class);
        LogAnalyzerService logAnalyzerService = context.getBean(LogAnalyzerService.class);
        logAnalyzerService.manageLogContent();
    }
}
