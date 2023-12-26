package com.linkmart.services;

import com.linkmart.forms.LogisticCompanyForm;
import com.linkmart.models.LogisticCompany;
import com.linkmart.repositories.LogisticCompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticCompanyService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LogisticCompanyRepository logisticCompanyRepository;

    @Autowired
    ProviderService providerService;

    public Integer uploadLogisticCompany (LogisticCompanyForm companyForm, String userId) throws Exception {
        try {
            String providerId = providerService.checkIfProvider(userId);
            logger.info("providerId: " + providerId);
            logger.info("companyName: " + companyForm.getCompanyName());
            LogisticCompany logisticCompany = new LogisticCompany();
            logisticCompany.setProviderId(providerId);
            logisticCompany.setCompanyName(companyForm.getCompanyName());
            logisticCompany.setCompanyUrl(companyForm.getCompanyUrl());
            var result = logisticCompanyRepository.saveAndFlush(logisticCompany);
            return result.getLogisticCompanyId();
        } catch (Exception e) {
            throw new Exception("Cannot upload logistic company via service");
        }
    }
}
