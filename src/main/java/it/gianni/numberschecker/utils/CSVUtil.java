package it.gianni.numberschecker.utils;

import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
  public static String TYPE = "text/csv";

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

}
