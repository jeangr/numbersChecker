package it.gianni.numberschecker.service;

import it.gianni.numberschecker.mapper.EntityOmMapper;
import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import it.gianni.numberschecker.exception.CSVException;
import it.gianni.numberschecker.entity.SouthAfricanMobileNumberEntity;
import it.gianni.numberschecker.repository.SouthAfricanMobileNumberRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CSVSouthAfricaService implements CSVService {

    private final SouthAfricanMobileNumberRepository repository;
    private final ValidatorNumberService validateNumberService;

    private final EntityOmMapper mapper;

    public CSVSouthAfricaService(SouthAfricanMobileNumberRepository repository, ValidatorNumberService validateNumberService, EntityOmMapper mapper) {
        this.repository = repository;
        this.validateNumberService = validateNumberService;
        this.mapper = mapper;
    }

    @Override
    public void saveData(@NonNull InputStream is) {
        final List<SouthAfricanMobileNumberOM> oms = this.csvToNumbers(is);
        repository.saveAll(mapper.omsToEntities(oms));
    }

    @Override
    @NonNull
    public Page<SouthAfricanMobileNumberOM> getDataPage(@NonNull Pageable pageable) {
        final List<SouthAfricanMobileNumberEntity> entities = repository.findAll();
        final List<SouthAfricanMobileNumberOM> oms = mapper.entitiesToOms(entities);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<SouthAfricanMobileNumberOM> list;

        if (oms.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, oms.size());
            list = oms.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), oms.size());
    }

    @Override
    public void deleteData() {
        repository.deleteAll();
    }

    @NonNull
    private List<SouthAfricanMobileNumberOM> csvToNumbers(@NonNull InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<SouthAfricanMobileNumberOM> numbers = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                SouthAfricanMobileNumberOM number = validateNumberService.validateNumber(csvRecord.get(1));
                number.setId(Long.parseLong(csvRecord.get(0)));
                numbers.add(number);
            }

            return numbers;
        } catch (Exception e) {
            throw new CSVException("Fail to parse CSV file - " + e.getMessage());
        }
    }

}
