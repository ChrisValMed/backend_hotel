package com.myhotel.template.services;

import com.myhotel.template.projections.EmailTemplateDataProjection;
import com.myhotel.template.repositories.SurveyResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService {
	
	@Autowired
    private SurveyResultRepository surveyResultRepository;
	
	private EmailTemplateDataProjection template;

    public void getTemplateData(Long surveyResponseId) {
    	template = surveyResultRepository.findEmailTemplateDataBySurveyResultId(surveyResponseId);
    }

    public String buildMailMessage() {
        return String.format("Thanks %s for answering our survey. Kind regards, %s!",
        		template.getGuestName(),
        		template.getHotelName());
    }
    
    public String buildCustomMailMessage() {
        return String.format("Thanks %s for answering our survey. Kind regards, %s!",
        		template.getGuestName(),
        		template.getHotelName());
    }

    public String getRecipientEmail() {
        return template.getGuestEmail();
    }

    public String getSenderName() {
        return template.getHotelName();
    }
}
