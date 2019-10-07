package achille;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AchilleApplication {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress ip;
		ip = InetAddress.getLocalHost();
		System.out.println("Current IP address : " + ip.getHostAddress());
		SpringApplication.run(AchilleApplication.class, args);
	}
}

