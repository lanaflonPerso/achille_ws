package achille.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import achille.model.Campagne;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class CampagneNotFound extends Exception {

	private static final long serialVersionUID = 1L;

	public CampagneNotFound(Campagne c) {
		super("La campagne de "+c.getMoisCampagne() + " "+ c.getAnneeCampagne() + " n'existe pas.");
	}
}
