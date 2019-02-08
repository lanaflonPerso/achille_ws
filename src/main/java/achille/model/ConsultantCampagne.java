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
	private Etat etat;
	// Documents
	 @ManyToMany(fetch=FetchType.EAGER)
	private List<Doc> documentsCampagne = new ArrayList<Doc>();
	// Information
	private double nbJourOuvree;
	private double astreinte;

	//Getter et setter
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
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public List<Doc> getDocumentsCampagne() {
		return documentsCampagne;
	}
	public void setDocumentsCampagne(List<Doc> documentsCampagne) {
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
	public double getAstreinte() {
		return astreinte;
	}
	public void setAstreinte(double astreinte) {
		this.astreinte = astreinte;
	}
	
	//Constructeur
	public ConsultantCampagne() {
		super();
	}
	public ConsultantCampagne(int id, Consultant consultant, Campagne campagne, Date date, Etat etat,
			List<Doc> documentsCampagne, double nbJourOuvree, double astreinte) {
		super();
		this.id = id;
		this.consultant = consultant;
		this.campagne = campagne;
		this.date = date;
		this.etat = etat;
		this.documentsCampagne = documentsCampagne;
		this.nbJourOuvree = nbJourOuvree;
		this.astreinte = astreinte;
	}



}
