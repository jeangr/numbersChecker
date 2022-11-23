package it.gianni.numberschecker.utils;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


public class CSVUtil {

  public CSVUtil(){}

  private static final String TYPE = "text/csv";

  public static boolean hasCSVFormat(@NonNull MultipartFile file) {
    Assert.notNull(file, "file is null");

    if(file.getContentType() != null) {
      return file.getContentType().equals(TYPE);
    } else {
      return false;
    }
  }

}
