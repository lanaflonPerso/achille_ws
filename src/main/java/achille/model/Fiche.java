package achille.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="Fiche")
public class Fiche {
	
	@Id
	@GeneratedValue
	private int id;
    private String nomJeuneFille;
    private Sexe sexe;
    private String numeroSecuriteSocial;
    private String telephone;
    @ManyToOne(targetEntity=Adresse.class, fetch=FetchType.EAGER)
    private Adresse adresse;
    private Date dateNaissance;
    private String villeNaissance;
    private Nationalite nationalite;
    private String pays;
    private String numeroTitreSejour;
    private String numeroVisa;
    private Date dateValidite;
    private StatutFamilal situationFamiliale;
    private int personnesACharge;
    private Boolean mutuelle;
    private Boolean attestationMutuelle;
    private Date dateVisiteMedicale;
    private Boolean allocatairePoleEmploi;
    private Date insertionDate;
    
    @ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
    private Doc titreSejour;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc rib;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc carteGrise;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc prevoyance;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc conventionAdhesion;
	
	
	public Fiche() {
		
	}
	
	public enum Sexe { Homme, Femme; }
	public enum StatutFamilal { Marie, PACS, Celibataire; }
	public enum Nationalite { FR, EEE, HORS_EEE; }
	
	public String getNomJeuneFille() {
		return nomJeuneFille;
	}
	public void setNomJeuneFille(String nomJeuneFille) {
		this.nomJeuneFille = nomJeuneFille;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	public String getNumeroSecuriteSocial() {
		return numeroSecuriteSocial;
	}
	public void setNumeroSecuriteSocial(String numeroSecuriteSocial) {
		this.numeroSecuriteSocial = numeroSecuriteSocial;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getVilleNaissance() {
		return villeNaissance;
	}
	public void setVilleNaissance(String villeNaissance) {
		this.villeNaissance = villeNaissance;
	}
	public Nationalite getNationalite() {
		return nationalite;
	}
	public void setNationalite(Nationalite nationalite) {
		this.nationalite = nationalite;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getNumeroTitreSejour() {
		return numeroTitreSejour;
	}
	public void setNumeroTitreSejour(String numeroTitreSejour) {
		this.numeroTitreSejour = numeroTitreSejour;
	}
	public String getNumeroVisa() {
		return numeroVisa;
	}
	public void setNumeroVisa(String numeroVisa) {
		this.numeroVisa = numeroVisa;
	}
	public Date getDateValidite() {
		return dateValidite;
	}
	public void setDateValidite(Date dateValidite) {
		this.dateValidite = dateValidite;
	}
	public StatutFamilal getSituationFamiliale() {
		return situationFamiliale;
	}
	public void setSituationFamiliale(StatutFamilal situationFamiliale) {
		this.situationFamiliale = situationFamiliale;
	}
	public int getPersonnesACharge() {
		return personnesACharge;
	}
	public void setPersonnesACharge(int personnesACharge) {
		this.personnesACharge = personnesACharge;
	}
	public Boolean getMutuelle() {
		return mutuelle;
	}
	public void setMutuelle(Boolean mutuelle) {
		this.mutuelle = mutuelle;
	}
	public Boolean getAttestationMutuelle() {
		return attestationMutuelle;
	}
	public void setAttestationMutuelle(Boolean attestationMutuelle) {
		this.attestationMutuelle = attestationMutuelle;
	}
	public Date getDateVisiteMedicale() {
		return dateVisiteMedicale;
	}
	public void setDateVisiteMedicale(Date dateVisiteMedicale) {
		this.dateVisiteMedicale = dateVisiteMedicale;
	}
	public Boolean getAllocatairePoleEmploi() {
		return allocatairePoleEmploi;
	}
	public void setAllocatairePoleEmploi(Boolean allocatairePoleEmploi) {
		this.allocatairePoleEmploi = allocatairePoleEmploi;
	}
	public Doc getTitreSejour() {
		return titreSejour;
	}
	public void setTitreSejour(Doc titreSejour) {
		this.titreSejour = titreSejour;
	}
	public Date getInsertionDate() {
		return insertionDate;
	}
	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}
	
}
