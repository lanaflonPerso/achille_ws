package achille.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import achille.dao.UserDAO;
import achille.model.User;
import achille.password.PasswordUtils;
import achille.service.UserService;

public class AppAuthProvider extends DaoAuthenticationProvider {
	
	@Autowired
	UserService userDetailsService;
	@Autowired
	UserDAO userDAO;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		String name = auth.getName();
		String password = auth.getCredentials().toString();
		
		User user = userDAO.findByUsername(name);
		
		if (user == null) {
			throw new BadCredentialsException("l/p does not match for " + auth.getPrincipal());
		}
		if (name.equals("ADMIN"))
		{
			if (!user.getPassword().equals(password)) {
				throw new BadCredentialsException("l/p does not match for " + auth.getPrincipal());
			}
		} else {
			if (!PasswordUtils.verifyUserPassword(password, user.getPassword(), user.getSalt())) {
				throw new BadCredentialsException("l/p does not match for " + auth.getPrincipal());
			}
		}

		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return true;
	}
}