package achille.model;

import java.io.File;
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
public class Doc {
 
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(targetEntity=TypeDoc.class, fetch=FetchType.EAGER)
	private TypeDoc typeDoc;
	private StatutDoc statut;
	private Date date;
	private String path;
	private Date insertionDate;
	
	/** constructor */
	public Doc(File file, TypeDoc typeDoc, String path) {
		this.statut = StatutDoc.missing;
		this.typeDoc = typeDoc;
		this.path = path;
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
	public Date getInsertionDate() {
		return insertionDate;
	}
	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}
	public enum StatutDoc {
		fill,
		missing,
		require,
		consultant_require;
	}

}
