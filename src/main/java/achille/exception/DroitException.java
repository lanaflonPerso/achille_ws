package achille.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class DroitException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DroitException() {
		super("Vous n'avez pas les droits pour effectuer cette opération");
	}

}
