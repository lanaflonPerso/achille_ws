package achille.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="document_campagne")
public class DocCampagne {


	@Id
	@GeneratedValue
	private int id;
	private int idConsultantCampagne;
	@ManyToOne(targetEntity=TypeDoc.class, fetch=FetchType.EAGER)
	private TypeDoc typeDoc;
	private Date date;
	private String path;
	public DocCampagne(int id, int idConsultantCampagne, TypeDoc typeDoc, Date date, String path) {
		super();
		this.id = id;
		this.idConsultantCampagne = idConsultantCampagne;
		this.typeDoc = typeDoc;
		this.date = date;
		this.path = path;
	}
	public DocCampagne() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdConsultantCampagne() {
		return idConsultantCampagne;
	}
	public void setIdConsultantCampagne(int idConsultantCampagne) {
		this.idConsultantCampagne = idConsultantCampagne;
	}
	public TypeDoc getTypeDoc() {
		return typeDoc;
	}
	public void setTypeDoc(TypeDoc typeDoc) {
		this.typeDoc = typeDoc;
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


}
