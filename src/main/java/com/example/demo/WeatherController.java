package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
class WeatherController {

    private final Logger log = LoggerFactory.getLogger(WeatherController.class);
    private final JmsTemplate jmsTemplate;

    WeatherController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/read")
    String readAlerts() {
        String message = (String) jmsTemplate.receiveAndConvert("test-jms-queue");
        log.info("Received message=" + message);
        return "redirect:/";
    }

    @PostMapping("/publish")
    @Secured("ROLE_admin")
    String publishAlert(@ModelAttribute WeatherAlert weatherAlert) {
        log.info("Sending message: " + weatherAlert);
        jmsTemplate.convertAndSend("test-jms-queue", weatherAlert.getMessage());
        return "redirect:/";
    }
}
