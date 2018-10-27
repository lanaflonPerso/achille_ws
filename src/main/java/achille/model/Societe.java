package achille.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Societe")
public class Societe {
	
	public Societe(String value) {
		super();
		this.value = value;
	}

	public Societe() {
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
