package achille.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length=100)
	private String authority;
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}


}
