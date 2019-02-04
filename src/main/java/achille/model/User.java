package achille.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import achille.auth.Authority;
import achille.password.PasswordGenerator;
import achille.password.PasswordUtils;

@Entity
@Table(name = "USER")
public class User implements Serializable , UserDetails {

 
	private static final long serialVersionUID = 1L;
	
	@Id
    private Integer userId;  
    private String username;
    private String password;
    private String salt;
    
    @ManyToMany(fetch=FetchType.EAGER)
    private List<Authority> authority = new ArrayList<Authority>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
    	return  authority;
    }

    public boolean isAccountNonExpired() {return true;}
    public boolean isAccountNonLocked() { return true;}
    
    public User(Integer userId, String username, String password, String salt, List<Authority> authority) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.authority = authority;
	}

    public User( int consultantId, String username, String password, String salt) {
		super();
		this.userId = consultantId;
		this.username = username;
		this.password = password;
		this.salt = salt;
	}
    
	public boolean isCredentialsNonExpired() {return true;}
    public boolean isEnabled() {return true;}
    
    public User() {}
    
    public User(String username) {
    	
    	super();
    	
    	this.username = username;
    	
    	this.salt = PasswordUtils.getSalt(30);
    	PasswordGenerator pwdGen = new PasswordGenerator(4, 12);
    	this.password = PasswordUtils.generateSecurePassword(pwdGen.generatePassword().toString(), salt);
    	
    }
     
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}


}