package achille.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class ConsultantNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConsultantNotFound(int id) {
		super("Le consultant " + id + " n'a pas été trouvé.");
	}

	public ConsultantNotFound(String username) {
		super("Le consultant " + username + " n'a pas été trouvé.");
	}

}
