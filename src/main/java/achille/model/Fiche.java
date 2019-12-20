package achille.model;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
    
    @ManyToOne(targetEntity=Document.class, fetch=FetchType.EAGER)
    private Document titreSejour;
	@ManyToOne(targetEntity=Document.class, fetch=FetchType.EAGER)
	private Document rib;
	@ManyToOne(targetEntity=Document.class, fetch=FetchType.EAGER)
	private Document carteGrise;
	@ManyToOne(targetEntity=Document.class, fetch=FetchType.EAGER)
	private Document prevoyance;
	@ManyToOne(targetEntity=Document.class, fetch=FetchType.EAGER)
	private Document conventionAdhesion;
	
	
	public Fiche() {
		
	}
	
	public void setFields(String fieldsName, String fieldsValue, String toCast, Map<String,String> correctNationalite, Map<String,String> correctSexe)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ParseException {

		Field field = Fiche.class.getDeclaredField(fieldsName);

		switch (toCast) {

		case "string": field.set(this, fieldsValue); break;
		case "double": field.set(this, Double.parseDouble(fieldsValue)); break;
		case "int": field.set(this, Integer.parseInt(fieldsValue)); break;
		case "date": field.set(this, new SimpleDateFormat("dd/MM/yyyy").parse(fieldsValue)); break;
		case "boolean": field.set(this, fieldsValue.equalsIgnoreCase("vrai")); break;
		case "nationalite": 
			String enumNationalite = correctNationalite.get(fieldsValue);
			if (enumNationalite == null)
				field.set(this, Nationalite.valueOf("Unknown"));
			else
				field.set(this, Nationalite.valueOf(enumNationalite));			
			break;
		case "sexe":
			String enumSexe = correctSexe.get(fieldsValue);
			if (enumSexe == null)
				field.set(this, Sexe.valueOf("Unknown"));
			else
				field.set(this, Sexe.valueOf(enumSexe));			
			break;
		}

	}
	
	
	public enum Sexe { Homme, Femme, Unknown;}
	public enum StatutFamilal { Marié, PACS, Celibataire, Unknown; }
	public enum Nationalite { FR, EEE, HORS_EEE, Unknown;}
	
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
	public Document getTitreSejour() {
		return titreSejour;
	}
	public void setTitreSejour(Document titreSejour) {
		this.titreSejour = titreSejour;
	}
	public Date getInsertionDate() {
		return insertionDate;
	}
	public void setInsertionDate(Date insertionDate) {
		this.insertionDate = insertionDate;
	}
	 public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Document getRib() {
			return rib;
		}

		public void setRib(Document rib) {
			this.rib = rib;
		}

		public Document getCarteGrise() {
			return carteGrise;
		}

		public void setCarteGrise(Document carteGrise) {
			this.carteGrise = carteGrise;
		}

		public Document getPrevoyance() {
			return prevoyance;
		}

		public void setPrevoyance(Document prevoyance) {
			this.prevoyance = prevoyance;
		}

		public Document getConventionAdhesion() {
			return conventionAdhesion;
		}

		public void setConventionAdhesion(Document conventionAdhesion) {
			this.conventionAdhesion = conventionAdhesion;
		}
}
