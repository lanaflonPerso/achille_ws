package achille.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import achille.dao.UserDAO;
import achille.model.User;

@Service
public class UserService implements UserDetailsService {

	private final UserDAO userRepository;

	@Autowired
	public UserService(UserDAO userRepository) {
		this.userRepository = userRepository;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Objects.requireNonNull(username);
		Optional<User> user = userRepository.findUserWithName(username);
		if (!user.isPresent())
			throw new UsernameNotFoundException("User not found");
		return user.get();
	}

}
