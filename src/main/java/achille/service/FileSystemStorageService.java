package achille.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import achille.exception.StorageException;
import achille.exception.StorageFileNotFoundException;
import achille.model.Document;

@Service
public class FileSystemStorageService  {

   @Autowired
   StorageProperties properties;


    public void store(MultipartFile file, String nomFichier, Path rootLocation) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Impossible de stocker le fichier vide " + filename);
            }
            if (nomFichier.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Impossible de stocker un fichier avec un chemin relatif dans le répertoire courant "
                                + nomFichier);
            }
            try (InputStream inputStream = file.getInputStream()) {
            	if (!Files.exists(rootLocation)) {
                	Files.createDirectories(rootLocation);
            	}
                Files.copy(inputStream, rootLocation.resolve(nomFichier),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("L'enregistrement du fichier " + nomFichier + " a échoué", e);
        }
    }

    public Stream<Path> loadAll( String userName, int idCampagne) {
        try {
        	Path rootLocation = Paths.get(properties.getLocation(userName, idCampagne));
            return Files.walk(rootLocation, 1)
                .filter(path -> !path.equals(rootLocation))
                .map(rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Impossible de lire les fichiers stockés", e);
        }

    }
    

    public Path load(String filename, String userName, int idCampagne) {
    	Path rootLocation = Paths.get(properties.getLocation(userName, idCampagne));
        return rootLocation.resolve(filename);
    }


    public Resource loadAsResource(String filename, String userName, int idCampagne) {
        try {
            Path file = load(filename, userName, idCampagne);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Impossible de lire le fichier: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Impossible de lire le fichier: " + filename, e);
        }
    }

    public Resource load(Document doc) throws MalformedURLException, StorageFileNotFoundException {
    
    		Path path = Paths.get(doc.getPath()).resolve(doc.getName());
    		Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Impossible de charger le fichier: " + doc.getName());

            }
    	}
    
	


    public void deleteAll(String userName, int idCampagne) {
    	Path rootLocation = Paths.get(properties.getLocation(userName, idCampagne));
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

	

	

}
