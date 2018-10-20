package achille.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="Doc")
public class Doc implements Serializable{
 
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(targetEntity=TypeDoc.class, fetch=FetchType.EAGER)
	private TypeDoc typeDoc;
	private StatutDoc statut;
	private Date date;
	private String path;

	/** constructor */
	public Doc() {
		super();
	}

	/** get & set */
	public TypeDoc getTypeDoc() {
		return typeDoc;
	}
	public void setTypeDoc(TypeDoc type) {
		this.typeDoc = type;
	}
	public StatutDoc getStatut() {
		return statut;
	}
	public void setStatut(StatutDoc statut) {
		this.statut = statut;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public enum StatutDoc {
		fill,
		missing,
		require,
		consultant_require;
	}

}
