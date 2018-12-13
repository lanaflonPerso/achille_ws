package achille.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import java.nio.file.Path;

import achille.exception.StorageFileNotFoundException;
import achille.service.StorageService;

@CrossOrigin(origins = "*")
@RestController
public class FileUploadController {



	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/file")
	public List<Path> listUploadedFiles(Model model) throws IOException {

		Stream<Path> sp = storageService.loadAll();
		List<Path> list = new ArrayList<Path>();
		sp.forEach(x->list.add(x.getFileName()));
		return list;
	}


	@RequestMapping(value="/file/{filename}", method=RequestMethod.GET)	
	ResponseEntity<Resource> get(@PathVariable String filename){
		Resource f = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + f.getFilename())
				.body(f);

	}
	
	

	@PostMapping("/file")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		storageService.store(file);		
		return "You successfully uploaded " + file.getOriginalFilename() + "!";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}


}
