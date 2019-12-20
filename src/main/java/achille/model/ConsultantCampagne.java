package achille.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="consultant_campagne")
public class ConsultantCampagne {

	@Id
	@GeneratedValue
	private int id;
	@OneToOne
	private Consultant consultant;
	@OneToOne
	private Campagne campagne;
	private Date date;
	private int etat;
	// Documents
	 @ManyToMany(fetch=FetchType.EAGER)
	private List<Document> documentsCampagne = new ArrayList<Document>();
	// Information
	private double nbJourOuvree;
	private Boolean astreinte;
	private String commentaires;

	//Getter et setter

	
	public String getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(String commentaire) {
		this.commentaires = commentaire;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Consultant getConsultant() {
		return consultant;
	}
	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}
	public Campagne getCampagne() {
		return campagne;
	}
	public void setCampagne(Campagne campagne) {
		this.campagne = campagne;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}

	public List<Document> getDocumentsCampagne() {
		return documentsCampagne;
	}
	public void setDocumentsCampagne(List<Document> documentsCampagne) {
		this.documentsCampagne = documentsCampagne;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getNbJourOuvree() {
		return nbJourOuvree;
	}
	public void setNbJourOuvree(double nbJourOuvree) {
		this.nbJourOuvree = nbJourOuvree;
	}
	public boolean getAstreinte() {
		return astreinte;
	}
	public void setAstreinte(boolean astreinte) {
		this.astreinte = astreinte;
	}
	
	//Constructeur
	public ConsultantCampagne() {
		super();
	}
	public ConsultantCampagne(int id, Consultant consultant, Campagne campagne, Date date, int etat,
			List<Document> documentsCampagne, double nbJourOuvree, boolean astreinte, String commentaires) {
		super();
		this.id = id;
		this.consultant = consultant;
		this.campagne = campagne;
		this.date = date;
		this.etat = etat;
		this.documentsCampagne = documentsCampagne;
		this.nbJourOuvree = nbJourOuvree;
		this.astreinte = astreinte;
		this.commentaires=commentaires;
	}
	
	
	public void setDocument(Document doc) {
		this.documentsCampagne.add(doc);
	
		
	}



}
