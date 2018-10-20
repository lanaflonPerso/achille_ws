package achille.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="type_document")
public class TypeDoc {
	
	@Id
	@GeneratedValue
	private int id;
	private String libelle;
	
	public TypeDoc() {
		super();
	}
	
	/** get & set */
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}	

}

