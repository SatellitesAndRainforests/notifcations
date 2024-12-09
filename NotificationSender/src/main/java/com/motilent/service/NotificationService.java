package com.motilent.service;

import com.motilent.model.NotificationDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class NotificationService {

  private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
  private final RestTemplate restTemplate;

  public NotificationService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public void sendNotification(NotificationDetails details) {

    String url = details.getNotificationUrl();
    NotificationDetails.NotificationContent content = details.getNotificationContent();

    try {

      logger.info(" - - sending notification - - - - - - - - - - - - - - ");

      Instant startTime = Instant.now();
      var response = restTemplate.postForEntity(url, content, String.class);
      Instant endTime = Instant.now();

      long responseTime = Duration.between(startTime, endTime).toMillis();

      logger.info("Notification URL: {}", url);
      logger.info("Request Body: {}", content);
      logger.info("Response Code: {}", response.getStatusCode());
      logger.info("Response Body: {}", response.getBody());
      logger.info("Response Time: {} ms", responseTime );

    } catch (HttpClientErrorException e) {
      logger.error("Client Error: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
      throw e;
    } catch (HttpServerErrorException e) {
      logger.error("Server Error: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
      throw e;
    } catch (Exception e) {
      logger.error("Sending Notification Service Error : {}", e.getMessage(), e);
      throw new RuntimeException(e);
    }

  }

}

