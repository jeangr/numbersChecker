package it.gianni.numberschecker.utils;

import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "id", "sms_phone" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<SouthAfricanMobileNumberEntity> csvToNumbers(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<SouthAfricanMobileNumberEntity> numbers = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        SouthAfricanMobileNumberEntity number = new SouthAfricanMobileNumberEntity(
              Long.parseLong(csvRecord.get(0)),
              csvRecord.get(1)
            );

        numbers.add(number);
      }

      return numbers;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

}
