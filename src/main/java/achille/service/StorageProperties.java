package achille.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("storage")
@Component
public class StorageProperties {

    /**
     * Folder location for storing files
     */
	@Value("${achille.path.storage}")
    private String location;

    public String getLocation(String repertoireUser, int idCampagne) {
        return location+"\\"+repertoireUser+"\\"+idCampagne;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
