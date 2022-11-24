package it.gianni.numberschecker.controller;

import it.gianni.numberschecker.om.SouthAfricanMobileNumberOM;
import it.gianni.numberschecker.exception.CSVException;
import it.gianni.numberschecker.exception.StorageException;
import it.gianni.numberschecker.service.CSVService;
import it.gianni.numberschecker.service.StorageService;

import it.gianni.numberschecker.service.ValidatorNumberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    private final StorageService storageService;
    private final CSVService csvService;
    private static final String MAIN_FORM_NAME = "mainForm";
    private static final String DATA_PAGE = "dataPage";
    private final ValidatorNumberService validateNumberService;

    public MainController(StorageService storageService, CSVService csvService, ValidatorNumberService validateNumberService) {
        this.storageService = storageService;
        this.csvService = csvService;
        this.validateNumberService = validateNumberService;
    }

    @GetMapping("/")
    @NonNull
    public String showResults(@NonNull Model model) {
        model.addAttribute(DATA_PAGE, null);

        setEmptyModel(model);

        return MAIN_FORM_NAME;
    }


    @PostMapping("/upload")
    @NonNull
    public String handleFileUpload(@NonNull Model model, @NonNull @RequestParam("file") MultipartFile file) throws IOException {
        String messageUpload = "messageUpload";

        storageService.deleteFiles();
        csvService.deleteData();

        final Path store;

        try {
            store = storageService.store(file);
        } catch (StorageException se) {
            setEmptyModel(model);
            model.addAttribute(messageUpload, "Please choose a correct file: " + se.getMessage());
            return MAIN_FORM_NAME;
        }

        InputStream is;
        try {
            is = Files.newInputStream(Paths.get(store.toString()));//load from db
        }catch(IOException e){
            setEmptyModel(model);
            model.addAttribute(messageUpload, "Error to retrieve the csv stored: " + e.getMessage());
            return MAIN_FORM_NAME;
        }

        try {
            csvService.saveData(is);
        } catch (CSVException e) {
            setEmptyModel(model);
            model.addAttribute(messageUpload, e.getMessage());
            return MAIN_FORM_NAME;
        }

        model.addAttribute(messageUpload, file.getOriginalFilename() + " uploaded!");

        final int currentPage = 1;
        final int pageSize = 25;

        final Page<SouthAfricanMobileNumberOM> dataPage = csvService.getDataPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute(DATA_PAGE, dataPage);

        manageModelPagination(model, dataPage);

        return MAIN_FORM_NAME;
    }

    @GetMapping("/numbersPaginated")
    @NonNull
    public String getData(@NonNull Model model,
                          @NonNull @RequestParam("page") Optional<Integer> page,
                          @NonNull @RequestParam("size") Optional<Integer> size) {


        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(25);

        final Page<SouthAfricanMobileNumberOM> dataPage = csvService.getDataPage(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute(DATA_PAGE, dataPage);

        manageModelPagination(model, dataPage);

        return MAIN_FORM_NAME;
    }

    @PostMapping("/checkNumber")
    @NonNull
    public String checkNumber(@NonNull Model model, @NonNull @RequestParam("numberToCheck") String numberToCheck) {
        SouthAfricanMobileNumberOM validatedNumber;

        if (StringUtils.isBlank(numberToCheck)) {
            setEmptyModel(model);
            String messageCheckNumber = "messageCheckNumber";
            model.addAttribute(messageCheckNumber, "Please insert a number");
        } else {
            validatedNumber = validateNumberService.validateNumber(numberToCheck);
            List<SouthAfricanMobileNumberOM> oms = new ArrayList<>();
            oms.add(validatedNumber);

            final Page<SouthAfricanMobileNumberOM> dataPage =
                    new PageImpl<>(oms, PageRequest.of(1, 5), 0);

            model.addAttribute(DATA_PAGE, dataPage);
        }
        return MAIN_FORM_NAME;
    }

    private static void manageModelPagination(@NonNull Model model, @NonNull Page<SouthAfricanMobileNumberOM> dataPage) {
        int totalPages = dataPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }

    private static void setEmptyModel(@NonNull Model model) {
        final Page<SouthAfricanMobileNumberOM> dataPage =
                new PageImpl<>(new ArrayList<>(), PageRequest.of(1, 5), 0);

        model.addAttribute(DATA_PAGE, dataPage);
    }

}
