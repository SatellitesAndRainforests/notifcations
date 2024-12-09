package com.motilent.validation;

import com.motilent.model.NotificationDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationDetailsValidatorTest {

    private final NotificationDetailsValidator validator = new NotificationDetailsValidator();

    @Test
    void Validator_NullNotificationDetails_ReturnsFalse() {

        assertFalse(validator.notificationDetailsAreValid(null));

    }

    @Test
    void Validator_ValidNotificationDetails_RetrunsTrue() {

        // Arrange
        NotificationDetails details = new NotificationDetails();
        details.setNotificationUrl("https://example.com/notification");
        NotificationDetails.NotificationContent content = new NotificationDetails.NotificationContent();
        content.setReportUID("valid-report-uid");
        content.setStudyInstanceUID("valid-study-instance-uid");
        details.setNotificationContent(content);

        // Act + Assert
        assertTrue(validator.notificationDetailsAreValid(details));

    }

    @Test
    void Validator_InvalidNotificationDetails_NullUrl_ReturnsFalse() {

        // Arrange
        NotificationDetails details = new NotificationDetails();
        // details.setNotificationUrl("https://example.com/notification");
        NotificationDetails.NotificationContent content = new NotificationDetails.NotificationContent();
        content.setReportUID("valid-report-uid");
        content.setStudyInstanceUID("valid-study-instance-uid");
        details.setNotificationContent(content);

        // Act + Assert
        assertFalse(validator.notificationDetailsAreValid(details));

    }

    @Test
    void Validator_InvalidNotificationDetails_EmptyUrl_ReturnsFalse() {

        // Arrange
        NotificationDetails details = new NotificationDetails();
        details.setNotificationUrl("");
        NotificationDetails.NotificationContent content = new NotificationDetails.NotificationContent();
        content.setReportUID("valid-report-uid");
        content.setStudyInstanceUID("valid-study-instance-uid");
        details.setNotificationContent(content);

        // Act + Assert
        assertFalse(validator.notificationDetailsAreValid(details));

    }

    // . . .

}

