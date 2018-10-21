package achille.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import achille.auth.Authority;

@Entity
@Table(name = "USER")
public class User implements Serializable , UserDetails {

 
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;   
    private String username;
    private String password;
    
    @ManyToMany(fetch=FetchType.EAGER)
    private List<Authority> authority = new ArrayList<Authority>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
    	return  authority;
    }
    
    
   

    public boolean isAccountNonExpired() {return true;}
    public boolean isAccountNonLocked() { return true;}
    public boolean isCredentialsNonExpired() {return true;}
    public boolean isEnabled() {return true;}

     
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


}