package com.motilent;

import com.motilent.model.NotificationDetails;
import com.motilent.service.NotificationService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NotificationServiceTest {

  @Test
  void SendNotification_ValidNotification_MakesPost() {

    // Arrange
    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    NotificationService service = new NotificationService(restTemplate);

    ResponseEntity<String> mockResponse = new ResponseEntity<>("Success", HttpStatus.OK);
    when(restTemplate.postForEntity(any(String.class), any(), eq(String.class))).thenReturn(mockResponse);

    NotificationDetails details = new NotificationDetails();
    details.setNotificationUrl("https://webhook.site/test-url");
    NotificationDetails.NotificationContent content = new NotificationDetails.NotificationContent();
    content.setReportUID("20fb8e02-9c55-410a-93a9-489c6f1d7598");
    content.setStudyInstanceUID("9998e02-9c55-410a-93a9-489c6f789798");
    details.setNotificationContent(content);

    // Act 
    service.sendNotification(details);

    // Assert
    verify(restTemplate, times(1)).postForEntity(any(String.class), any(), eq(String.class));

  }

  @Test
  void testSendNotificationInvalidUrl() {

    // Arrange
    RestTemplate restTemplate = mock(RestTemplate.class);
    NotificationService service = new NotificationService(restTemplate);

    when(restTemplate.postForEntity(any(String.class), any(), eq(String.class)))
      .thenThrow(new RuntimeException("Invalid URL"));

    NotificationDetails details = new NotificationDetails();
    details.setNotificationUrl("https://invalid-url.example.com");
    NotificationDetails.NotificationContent content = new NotificationDetails.NotificationContent();
    content.setReportUID("invalid-reportUID");
    content.setStudyInstanceUID("invalid-studyInstanceUID");
    details.setNotificationContent(content);

    // Act + Assert
    RuntimeException exception = assertThrows(RuntimeException.class, () ->  service.sendNotification(details) );

    verify(restTemplate, times(1)).postForEntity(any(String.class), any(), eq(String.class));

  }

    @Test
    void SendNotification_404NotFound_ThrowsHttpClientErrorException() {

        // Arrange
        RestTemplate restTemplate = mock(RestTemplate.class);
        NotificationService service = new NotificationService(restTemplate);

        when(restTemplate.postForEntity(any(String.class), any(), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found"));

        NotificationDetails details = new NotificationDetails();
        details.setNotificationUrl("https://webhook.site/invalid-url");
        NotificationDetails.NotificationContent content = new NotificationDetails.NotificationContent();
        content.setReportUID("invalid-reportUID");
        content.setStudyInstanceUID("invalid-studyInstanceUID");
        details.setNotificationContent(content);

        // Act + Assert
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> service.sendNotification(details));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Not Found", exception.getStatusText());
        verify(restTemplate, times(1)).postForEntity(any(String.class), any(), eq(String.class));

    }

}
