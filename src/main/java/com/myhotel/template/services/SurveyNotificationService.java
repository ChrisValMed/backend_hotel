package com.myhotel.template.services;
import com.myhotel.template.models.MessageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyNotificationService {

	@Autowired
    private EmailSenderService emailSenderService;
	
	@Autowired
    private EmailTemplateService emailTemplateService;


    public MessageResult notifyGuest(Long surveyResponseId) {
    	emailTemplateService.getTemplateData(surveyResponseId);
        String from = emailTemplateService.getSenderName();
        String to = emailTemplateService.getRecipientEmail();
        String subject = "Thank you for your feedback!";
        String body = emailTemplateService.buildMailMessage();
        return emailSenderService.sendEmail(from, to, subject, body);
    }
}
