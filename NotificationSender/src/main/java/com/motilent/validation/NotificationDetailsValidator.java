package com.motilent.validation;

import com.motilent.model.NotificationDetails;
import org.springframework.stereotype.Component;

@Component
public class NotificationDetailsValidator {

  public boolean notificationDetailsAreValid(NotificationDetails details) {

    if (details == null ||
        details.getNotificationUrl() == null ||
        details.getNotificationUrl().isBlank() ||
        details.getNotificationContent() == null ||
        details.getNotificationContent().getReportUID() == null ||
        details.getNotificationContent().getReportUID().isBlank() ||
        details.getNotificationContent().getStudyInstanceUID() == null ||
        details.getNotificationContent().getStudyInstanceUID().isBlank()
       ) {
      return false;
       }

    return true;

  }

}
