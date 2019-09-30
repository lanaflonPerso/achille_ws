package achille.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import achille.dao.DocumentDAO;
import achille.exception.StorageFileNotFoundException;
import achille.model.Document;

@Service
public class DocumentService {
	
	@Autowired
	DocumentDAO documentDAO;
	@Autowired 
	FileSystemStorageService fileSystemStorageService ;
	@Autowired
	StorageProperties properties;
	

	public Document saveFileAndBdd(MultipartFile file, String userName, int idCampagne) {
		Document doc = new Document();
		doc.setDate(new Date());
		String name=file.getOriginalFilename();
		String typeDoc = name.substring(0, name.indexOf("_"));
		doc.setTypeDoc(typeDoc);
		String originalName = name.substring(name.indexOf("_")+1,name.length());
		doc.setOriginalName(originalName);
		StringBuilder nomDoc =  new StringBuilder() ;
		Format formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String s = formatter.format(doc.getDate());
		nomDoc.append(typeDoc).append("_").append(s).append("_").append(originalName) ;  
		doc.setName(nomDoc.toString());
		Path rootLocation = Paths.get(properties.getLocation(userName, idCampagne));
		doc.setPath(rootLocation.toString());
		fileSystemStorageService.store(file, doc.getName(), rootLocation);
		return documentDAO.save(doc);
	}
	

	public Resource loadDocument(String filename) throws StorageFileNotFoundException, MalformedURLException {
		// On récupère le document en BDD pour avoir l'username et l'idCampagne correspondant
		Document doc = documentDAO.findByName(filename);
		return fileSystemStorageService.load(doc);
		
	
	}

}
