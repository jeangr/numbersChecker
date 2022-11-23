package it.gianni.numberschecker.controller;

import it.gianni.numberschecker.exception.StorageException;
import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import it.gianni.numberschecker.service.ICSVService;
import it.gianni.numberschecker.service.IStorageService;

import it.gianni.numberschecker.service.IValidateNumberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Controller
public class MainController {

	private final IStorageService storageService;
	private final ICSVService csvService;

	private final String messageUpload = "messageUpload";

	private final String messageCheckNumber = "messageCheckNumber";

	private final String outputNumbers = "numbers";

	private final IValidateNumberService validateNumberService;

	public MainController(IStorageService storageService, ICSVService csvService, IValidateNumberService validateNumberService) {
		this.storageService = storageService;
		this.csvService = csvService;
		this.validateNumberService = validateNumberService;
	}

	@GetMapping("/")
	public String showResults(Model model) {
		return "mainForm";
	}

	@PostMapping("/upload")
	public String handleFileUpload(Model model, @RequestParam("file") MultipartFile file) throws IOException {

		storageService.deleteFiles();
		try {
			storageService.store(file);
			csvService.saveInDb(file);
			model.addAttribute(outputNumbers, csvService.getData());
			model.addAttribute(messageUpload, file.getOriginalFilename() + " uploaded!");
		}catch (StorageException se){
			model.addAttribute(messageUpload, "Please choose a correct file: " + se.getMessage());
		}

		return "mainForm";
	}

	@PostMapping("/checkNumber")
	public String checkNumber(Model model, @RequestParam("numberToCheck") String numberToCheck) {

		if(StringUtils.isBlank(numberToCheck)){
			model.addAttribute(messageCheckNumber, "Please insert a number");
		} else {
			final SouthAfricanMobileNumberEntity validatedNumber = validateNumberService.validateNumber(null, numberToCheck);//TODO usare oggettino con mapper
			model.addAttribute(outputNumbers, Collections.singletonList(validatedNumber));
		}
		return "mainForm";
	}

}
