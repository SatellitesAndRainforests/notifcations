package com.motilent.model;

public class NotificationDetails {

  private String notificationUrl;
  private NotificationContent notificationContent;

  // getters / setters

  public String getNotificationUrl() {
    return notificationUrl;
  }

  public void setNotificationUrl(String notificationUrl) {
    this.notificationUrl = notificationUrl;
  }

  public NotificationContent getNotificationContent() {
    return notificationContent;
  }

  public void setNotificationContent(NotificationContent notificationContent) {
    this.notificationContent = notificationContent;
  }



  public static class NotificationContent {

    private String reportUID;
    private String studyInstanceUID;

    // getters / setters

    public String getReportUID() {
      return reportUID;
    }

    public void setReportUID(String reportUID) {
      this.reportUID = reportUID;
    }

    public String getStudyInstanceUID() {
      return studyInstanceUID;
    }

    public void setStudyInstanceUID(String studyInstanceUID) {
      this.studyInstanceUID = studyInstanceUID;
    }

    @Override
    public String toString() {
      return "NotificationContent{" +
        "reportUID='" + reportUID + '\'' +
        ", studyInstanceUID='" + studyInstanceUID + '\'' +
        '}';
    }

  }

}

