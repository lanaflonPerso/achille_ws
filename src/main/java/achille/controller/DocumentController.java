package achille.controller;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import achille.exception.StorageFileNotFoundException;
import achille.service.DocumentService;

@CrossOrigin(origins = "*")
@Controller
public class DocumentController {

	@Autowired
	DocumentService documentService;
		
	//Retourne le document demandé
	@RequestMapping(value="/file/{filename}")	
	ResponseEntity<Resource> get(@PathVariable String filename) throws StorageFileNotFoundException, MalformedURLException{
		Resource f = documentService.loadDocument(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + f.getFilename())
				.body(f);

	}
	
	//Retourne le document demandé
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	ResponseEntity<Resource> loadDocument(@RequestBody String fileName) throws StorageFileNotFoundException, MalformedURLException {
		Resource f = documentService.loadDocument(fileName);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + f.getFilename())
				.body(f);

	}



}
