package achille;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class AchilleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AchilleApplication.class, args);
	}
}
