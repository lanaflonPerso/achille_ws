package achille.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Societe")
public class Societe {
	
	public Societe(String societe) {
		super();
		this.societe = societe;
	}

	public Societe() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private String societe;

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}
	
}
