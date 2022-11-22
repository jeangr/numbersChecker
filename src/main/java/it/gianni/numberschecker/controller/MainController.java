package it.gianni.numberschecker.controller;

import it.gianni.numberschecker.exception.StorageException;
import it.gianni.numberschecker.exception.StorageFileNotFoundException;
import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import it.gianni.numberschecker.service.ICSVService;
import it.gianni.numberschecker.service.IStorageService;

import it.gianni.numberschecker.service.IValidateNumberService;
import it.gianni.numberschecker.utils.CSVUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

	private final IStorageService storageService;
	private final ICSVService csvService;

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
			model.addAttribute("numbers", csvService.getData());
			model.addAttribute("message", file.getOriginalFilename() + " uploaded!");
		}catch (StorageException se){
			model.addAttribute("message", "Please choose a file");
		}

		return "mainForm";
	}

	@PostMapping("/checkNumber")
	public String checkNumber(Model model, @RequestParam("numberToCheck") String numberToCheck) {

		if(StringUtils.isEmpty(numberToCheck)){
			model.addAttribute("message", "Please insert a number");
		} else {
			final SouthAfricanMobileNumberEntity validatedNumber = validateNumberService.validateNumber(null, numberToCheck);//TODO usare oggettino con mapper
			model.addAttribute("numbers", Collections.singletonList(validatedNumber));
		}
		return "mainForm";
	}

}
