package com.linkmart.services;

import com.linkmart.models.ReportCase;
import com.linkmart.repositories.ReportCaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReportCaseRepository reportCaseRepository;

    public void createReportCase(String orderId, String content, String subject)
            throws Exception {
        try{
            ReportCase reportCase = new ReportCase();
            reportCase.MakeReportCase();
            reportCase.setOrdersId(orderId);
            reportCase.setStatusId(1);
            reportCase.setReportSubject(subject);
            reportCase.setReportDescription(content);
            reportCaseRepository.save(reportCase);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("Error while creating report case");
        }
    }
}
