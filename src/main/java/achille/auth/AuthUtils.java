package achille.auth;

import org.springframework.security.core.Authentication;

import achille.exception.DroitException;
import achille.model.User;

public class AuthUtils {

	public static void consultantAutorise(int id, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		String role = user.getAuthority().get(0).getAuthority();
		if (role.equals("CONSULTANT")) {
			if (id != user.getUserId()) {
				throw new DroitException();
			}
		}
	}
}
