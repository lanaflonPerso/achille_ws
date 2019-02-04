package achille.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Campagne")
public class Campagne {
	
	@Id
	@GeneratedValue
	private int idCampagne;
	private int moisCampagne;
	private int anneeCampagne;
	private Date dateOuverture; 
	private EtatCampagne etat;		
	

	//Constructeurs
	public Campagne() {
		super();
	}
	
	public Campagne(int idCampagne, int moisCampagne, int anneeCampagne, Date dateOuverture, EtatCampagne etat) {
		super();
		this.idCampagne = idCampagne;
		this.moisCampagne = moisCampagne;
		this.anneeCampagne = anneeCampagne;
		this.dateOuverture = dateOuverture;
		this.etat = etat;
	}
	
	//Getter et setter	
	public int getIdCampagne() {
		return idCampagne;
	}

	public void setIdCampagne(int idCampagne) {
		this.idCampagne = idCampagne;
	}

	public int getMoisCampagne() {
		return moisCampagne;
	}

	public void setMoisCampagne(int moisCampagne) {
		this.moisCampagne = moisCampagne;
	}

	public int getAnneeCampagne() {
		return anneeCampagne;
	}

	public void setAnneeCampagne(int anneeCampagne) {
		this.anneeCampagne = anneeCampagne;
	}

	public Date getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public EtatCampagne getEtat() {
		return etat;
	}

	public void setEtat(EtatCampagne etat) {
		this.etat = etat;
	}


	public enum EtatCampagne { O, F; }

}
