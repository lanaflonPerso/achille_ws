package achille.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Partenaire")
public class Partenaire {
	
	public Partenaire(String value) {
		super();
		this.value = value;
	}

	public Partenaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
