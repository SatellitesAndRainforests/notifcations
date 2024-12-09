package com.motilent.service;

import com.motilent.model.NotificationDetails;
import com.motilent.validation.FileValidator;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class FileReaderService {

  private final ObjectMapper objectMapper;
  private final FileValidator validator;

  public FileReaderService(ObjectMapper objectMapper, FileValidator validator) {
    this.objectMapper = objectMapper;
    this.validator = validator;
  }

  public NotificationDetails readNotificationDetails(String filePath) throws IOException {

    if (filePath == null || filePath.isBlank()) {
      throw new IllegalArgumentException("File path cannot be null or empty.");
    }

    File jsonFile = new File(filePath);

    if (!validator.isValidFile(jsonFile)) {
      throw new IOException("Invalid File");
    }

    return objectMapper.readValue(jsonFile, NotificationDetails.class);

  }

}


