package com.motilent;

import com.motilent.model.NotificationDetails;
import com.motilent.service.FileReaderService;
import com.motilent.service.NotificationService;
import com.motilent.validation.NotificationDetailsValidator;
import com.motilent.validation.FileValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NotificationSenderApplication implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(NotificationSenderApplication.class);

  private final NotificationService notificationService;
  private final FileReaderService fileReaderService;
  private final NotificationDetailsValidator validator;

  public NotificationSenderApplication(NotificationService notificationService, 
      ObjectMapper objectMapper,
      NotificationDetailsValidator validator) {
    this.notificationService = notificationService;
    this.fileReaderService = new FileReaderService(objectMapper, new FileValidator());
    this.validator = validator;
  }

  public static void main(String[] args) {
    SpringApplication.run(NotificationSenderApplication.class, args);
  }

  @Override
  public void run(String... args) {

    if (args.length != 1) {
      logger.error(" usage error : must pass notificationDetails.json file as 1st argument");
      logger.error(" eg :  java -jar target/NotificationSender-1.0-SNAPSHOT.jar ./notificationDetails.json");
      System.exit(1);
    }

    try {

      NotificationDetails details = fileReaderService.readNotificationDetails(args[0]);

      if (validator.notificationDetailsAreValid(details)) {
        logger.info("Successfully read notification details from file: {}", args[0]);
        notificationService.sendNotification(details);
      } else {
        logger.error("Validation failed : notificationDetails.json does not contrain all the valid fields.");
        System.exit(2);
      }

    } catch (Exception e) {
      logger.error("Application Error: {}", e.getMessage(), e);
      System.exit(3);
    }

  }

}

