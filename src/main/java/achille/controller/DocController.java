package achille.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import achille.dao.DocDAO;

@CrossOrigin(origins = "*")
@RestController
public class DocController {
	

	@Autowired
	private DocDAO doc;


	@PostMapping("/import")
	public ResponseEntity<String> handleFileUpload(@RequestParam("document") MultipartFile input) throws IOException {
		
		return ResponseEntity.ok("Document sauvegardé");
	}
	

}
