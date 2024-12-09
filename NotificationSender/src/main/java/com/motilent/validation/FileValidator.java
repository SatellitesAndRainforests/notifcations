package com.motilent.validation;

import com.motilent.model.NotificationDetails;

import java.io.File;

public class FileValidator {

  public boolean isValidFile(File file) {

    if (!file.exists() || !file.canRead() || file.length() == 0) {
      return false;
    }

    return true;

  }

}
