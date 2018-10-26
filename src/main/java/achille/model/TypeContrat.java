package achille.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="TypeContrat")
public class TypeContrat {
	
	public TypeContrat(String typeContrat) {
		super();
		this.typeContrat = typeContrat;
	}

	public TypeContrat() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private String typeContrat;

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

}
