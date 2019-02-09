package achille.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class CampagneException extends Exception {

	private static final long serialVersionUID = 1L;

	public CampagneException(String message ) {
		super(message);
	}
}
