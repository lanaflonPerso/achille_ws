package achille.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="Consultant")
public class Consultant {
	
	@Id
	@GeneratedValue
	private int id;
	private String nom;
	private String prenom;
	private String nomJeuneFille;
	private Sexe sexe;
	@ManyToOne(targetEntity=Adresse.class, fetch=FetchType.EAGER)
	private Adresse Adresse;
	private String portableNumeros;
	private String fixeNumeros;
	private double securiteSocial;
	private Date dateNaissance;
	private String lieuNaissance;
	private Nationalite nationalite;
	private String pays;
	private double titreSejourNumeros;
	private double visaNumeros;
	private Date dateValidite;
	private StatutFamilal situationFamilial;
	private int nombrePersonnesCharges;
	private Boolean mutuelle;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc attestationMutuelle;
	private Date dateVisiteMedicale;
	private Boolean allocatairePoleEmploi;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc rib;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc carteGrise;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc prevoyance;
	@ManyToOne(targetEntity=Doc.class, fetch=FetchType.EAGER)
	private Doc conventionAdhesion;
	
	public enum Sexe { m, f; }
	public enum StatutFamilal { maried, pacs, single; }
	public enum Nationalite { FR, EEE, HORS_EEE; }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public Adresse getAdresse() {
		return Adresse;
	}
	public void setAdresse(Adresse adresse) {
		Adresse = adresse;
	}
	public String getPortableNumeros() {
		return portableNumeros;
	}
	public void setPortableNumeros(String portableNumeros) {
		this.portableNumeros = portableNumeros;
	}
	public String getFixeNumeros() {
		return fixeNumeros;
	}
	public void setFixeNumeros(String fixeNumeros) {
		this.fixeNumeros = fixeNumeros;
	}
	public double getSecuriteSocial() {
		return securiteSocial;
	}
	public void setSecuriteSocial(double securiteSocial) {
		this.securiteSocial = securiteSocial;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
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
	public double getTitreSejourNumeros() {
		return titreSejourNumeros;
	}
	public void setTitreSejourNumeros(double titreSejourNumeros) {
		this.titreSejourNumeros = titreSejourNumeros;
	}
	public double getVisaNumeros() {
		return visaNumeros;
	}
	public void setVisaNumeros(double visaNumeros) {
		this.visaNumeros = visaNumeros;
	}
	public Date getDateValidite() {
		return dateValidite;
	}
	public void setDateValidite(Date dateValidite) {
		this.dateValidite = dateValidite;
	}
	public StatutFamilal getSituationFamilial() {
		return situationFamilial;
	}
	public void setSituationFamilial(StatutFamilal situationFamilial) {
		this.situationFamilial = situationFamilial;
	}
	public int getNombrePersonnesCharges() {
		return nombrePersonnesCharges;
	}
	public void setNombrePersonnesCharges(int nombrePersonnesCharges) {
		this.nombrePersonnesCharges = nombrePersonnesCharges;
	}
	public Boolean getMutuelle() {
		return mutuelle;
	}
	public void setMutuelle(Boolean mutuelle) {
		this.mutuelle = mutuelle;
	}
	@ManyToOne
	public Doc getAttestationMutuelle() {
		return attestationMutuelle;
	}
	public void setAttestationMutuelle(Doc attestationMutuelle) {
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
	@ManyToOne
	public Doc getRib() {
		return rib;
	}
	public void setRib(Doc rib) {
		this.rib = rib;
	}
	@ManyToOne
	public Doc getCarteGrise() {
		return carteGrise;
	}
	public void setCarteGrise(Doc carteGrise) {
		this.carteGrise = carteGrise;
	}
	@ManyToOne
	public Doc getPrevoyance() {
		return prevoyance;
	}
	public void setPrevoyance(Doc prevoyance) {
		this.prevoyance = prevoyance;
	}
	@ManyToOne
	public Doc getConventionAdhesion() {
		return conventionAdhesion;
	}
	public void setConventionAdhesion(Doc conventionAdhesion) {
		this.conventionAdhesion = conventionAdhesion;
	}
	
}
