package achille.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="Consultant")
public class Consultant {
	
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(targetEntity=Societe.class, fetch=FetchType.EAGER)
	private Societe societe;
	private String nom;
	private String prenom;
	private String matricule;
	private String email;
	private Date dateEntree;
	private Date dateFinContratCom;
    private Date dateSortiePaie;
    @ManyToOne(targetEntity=Partenaire.class, fetch=FetchType.EAGER)
    private Partenaire partenaire;
    private String contact;
    private double TJM;
    private double forfaitFrais;
    private Boolean DUE;
    @ManyToOne(targetEntity=TypeContrat.class, fetch=FetchType.EAGER)
    private TypeContrat typeContrat;
    private Boolean entreePaie;
    private Boolean stcPaie;
    private String observation;
    private double plvtIntervia;
    private String lieuActiviteInsee;
    private Boolean davidsonCommission;
    private Boolean campagnePaie;
    @OneToOne
    private Fiche fiche;
	private Date insertionDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Societe getSociete() {
		return societe;
	}
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}
	public Date getDateFinContratCom() {
		return dateFinContratCom;
	}
	public void setDateFinContratCom(Date dateFinContratCom) {
		this.dateFinContratCom = dateFinContratCom;
	}
	public Date getDateSortiePaie() {
		return dateSortiePaie;
	}
	public void setDateSortiePaie(Date dateSortiePaie) {
		this.dateSortiePaie = dateSortiePaie;
	}
	public Partenaire getPartenaire() {
		return partenaire;
	}
	public void setPartenaire(Partenaire partenaire) {
		this.partenaire = partenaire;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public double getTJM() {
		return TJM;
	}
	public void setTJM(double tJM) {
		TJM = tJM;
	}
	public double getForfaitFrais() {
		return forfaitFrais;
	}
	public void setForfaitFrais(double forfaitFrais) {
		this.forfaitFrais = forfaitFrais;
	}
	public Boolean getDUE() {
		return DUE;
	}
	public void setDUE(Boolean dUE) {
		DUE = dUE;
	}
	public TypeContrat getTypeContrat() {
		return typeContrat;
	}
	public void setTypeContrat(TypeContrat typeContrat) {
		this.typeContrat = typeContrat;
	}
	public Boolean getEntreePaie() {
		return entreePaie;
	}
	public void setEntreePaie(Boolean entreePaie) {
		this.entreePaie = entreePaie;
	}
	public Boolean getStcPaie() {
		return stcPaie;
	}
	public void setStcPaie(Boolean stcPaie) {
		this.stcPaie = stcPaie;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public double getPlvtIntervia() {
		return plvtIntervia;
	}
	public void setPlvtIntervia(double plvtIntervia) {
		this.plvtIntervia = plvtIntervia;
	}
	public String getLieuActiviteInsee() {
		return lieuActiviteInsee;
	}
	public void setLieuActiviteInsee(String lieuActiviteInsee) {
		this.lieuActiviteInsee = lieuActiviteInsee;
	}
	public Boolean getDavidsonCommission() {
		return davidsonCommission;
	}
	public void setDavidsonCommission(Boolean davidsonCommission) {
		this.davidsonCommission = davidsonCommission;
	}
	public Boolean getCampagnePaie() {
		return campagnePaie;
	}
	public void setCampagnePaie(Boolean campagnePaie) {
		this.campagnePaie = campagnePaie;
	}
	public Fiche getFiche() {
		return fiche;
	}
	public void setFiche(Fiche fiche) {
		this.fiche = fiche;
	}
	public Date getInsertionDate() {
		return insertionDate;
	}
	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}

}
