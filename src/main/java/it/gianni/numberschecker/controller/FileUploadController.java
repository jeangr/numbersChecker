package it.gianni.numberschecker.controller;

import it.gianni.numberschecker.exception.StorageFileNotFoundException;
import it.gianni.numberschecker.model.SouthAfricanMobileNumberEntity;
import it.gianni.numberschecker.service.CSVService;
import it.gianni.numberschecker.service.ICSVService;
import it.gianni.numberschecker.service.IStorageService;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

	private final IStorageService storageService;
	private final ICSVService csvService;

	public FileUploadController(IStorageService storageService, ICSVService csvService) {
		this.storageService = storageService;
		this.csvService = csvService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

//		model.addAttribute("files", storageService.loadAll().map(
//				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//						"serveFile", path.getFileName().toString()).build().toUri().toString())
//				.collect(Collectors.toList()));

		model.addAttribute("files", csvService.getData());

		return "uploadForm";
	}

	@GetMapping("/numbers")
	@ResponseBody
	public ResponseEntity<List<SouthAfricanMobileNumberEntity>> serveFile(@PathVariable String filename) {

//		Resource file = storageService.loadAsResource(filename);
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//				"attachment; filename=\"" + file.getFilename() + "\"").body(file);

		return ResponseEntity.ok().body(csvService.getData());
	}

	@PostMapping("/files")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {

//		storageService.store(file);
		csvService.saveInDb(file);
		final List<SouthAfricanMobileNumberEntity> allNumbers = csvService.getData();

		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}