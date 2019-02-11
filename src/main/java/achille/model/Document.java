package achille.model;

import java.io.File;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Doc")
public class Document {
 
	@Id
	@GeneratedValue
	private int id;
	private String typeDoc;
	private String statut;
	private Date date;
	private String path;
	private String name;
	private String originalName;
	
	/** constructor */
	public Document(File file, String typeDoc, String path) {
		this.statut = "missing";
		this.typeDoc = typeDoc;
		this.path = path;
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** get & set */
	public String getTypeDoc() {
		return typeDoc;
	}
	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public void setTypeDoc(String type) {
		this.typeDoc = type;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
