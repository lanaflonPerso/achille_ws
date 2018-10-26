package achille.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Partenaire")
public class Partenaire {
	
	public Partenaire(String partenaire) {
		super();
		this.partenaire = partenaire;
	}

	public Partenaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private String partenaire;

	public String getPartenaire() {
		return partenaire;
	}

	public void setPartenaire(String partenaire) {
		this.partenaire = partenaire;
	}
	
}
