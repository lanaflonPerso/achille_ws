package achille.wrapper;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import achille.model.Consultant;
import achille.model.Fiche;

public class ConsultantWrapper {

	private Map<String, String> header2consultantFields;
	private Map<String, String> header2ficheFields;
	private Map<String, String> field2type;
	private Map<String, String> nationalite2enumNationalite;

	public ConsultantWrapper() {

		this.initMaps();

	}

	public Consultant createConsultant(String[] header, String[] data)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ParseException {

		Consultant c = new Consultant();
		Fiche f = new Fiche();

		for (int i = 0; i < data.length; i++) {

			String value = data[i];
			String fields = header[i];

			if (value.equals(""))
				continue;

			if (this.header2consultantFields.containsKey(fields)) {
				String fieldsName = this.header2consultantFields.get(fields);
				String toCast = this.field2type.get(fieldsName);
				c.setFields(fieldsName, value, toCast);
			}

			if (this.header2ficheFields.containsKey(fields)) {
				String fieldsName = this.header2ficheFields.get(fields);
				String toCast = this.field2type.get(fieldsName);
				f.setFields(fieldsName, value, toCast, this.nationalite2enumNationalite);
			}

		}

		c.setFiche(f);
		c.setSendMail(false);



		return c;

	}

	private void initMaps() {

		this.header2consultantFields = new HashMap<String, String>();
		this.header2ficheFields = new HashMap<String, String>();
		this.field2type = new HashMap<String, String>();
		this.nationalite2enumNationalite = new HashMap<String, String>();

		this.header2consultantFields.put("SOCIETE","societe");
		this.header2consultantFields.put("MATRI","matricule");
		this.header2consultantFields.put("NOM","nom");
		this.header2consultantFields.put("PRENOM","prenom");
		this.header2consultantFields.put("ADRESSE-E-MAIL","email");
		this.header2consultantFields.put("DATE-ENTREE","dateEntree");
		this.header2consultantFields.put("DATE-SORTIE-PAIE","dateSortiePaie");
		this.header2consultantFields.put("PARTENAIRE","partenaire");
		this.header2consultantFields.put("CONTACT","contact");
		this.header2consultantFields.put("TJM","TJM");
		this.header2consultantFields.put("FORFAIT- FRAIS","forfaitFrais");
		this.header2consultantFields.put("DUE","DUE");
		this.header2consultantFields.put("TYPE-CONTRAT","typeContrat");
		this.header2consultantFields.put("ENTREE-PAIE","entreePaie");
		this.header2consultantFields.put("STC-PAIE","stcPaie");
		this.header2consultantFields.put("OBSERVATION","observation");
		this.header2consultantFields.put("PLVT-INTERVIA","plvtIntervia");
		this.header2consultantFields.put("LIEU ACTVITE - INSEE","lieuActiviteInsee");
		this.header2consultantFields.put("DAVIDSON","davidsonCommission");
		this.header2consultantFields.put("CAMPAGNE-PAIE","campagnePaie");

		this.header2ficheFields.put("MUTUELLE","mutuelle");
		this.header2ficheFields.put("NATIONALITE","nationalite");
		this.header2ficheFields.put("TITRE-SEJOUR-NUMERO","numeroTitreSejour");
		this.header2ficheFields.put("DATE-FIN-TITRE-SEJOUR","dateValidite");
		this.header2ficheFields.put("SEXE","sexe");

		this.field2type.put("societe", "societe");
		this.field2type.put("matricule", "string");
		this.field2type.put("nom", "string");
		this.field2type.put("prenom", "string");
		this.field2type.put("email", "string");
		this.field2type.put("dateEntree", "date");
		this.field2type.put("dateSortiePaie", "date");
		this.field2type.put("partenaire", "partenaire");
		this.field2type.put("contact", "string");
		this.field2type.put("TJM", "double");
		this.field2type.put("forfaitFrais", "double");
		this.field2type.put("DUE", "boolean");
		this.field2type.put("typeContrat", "typecontrat");
		this.field2type.put("entreePaie", "boolean");
		this.field2type.put("stcPaie", "boolean");
		this.field2type.put("observation", "string");
		this.field2type.put("plvtIntervia", "double");
		this.field2type.put("lieuActiviteInsee", "string");
		this.field2type.put("davidsonCommission", "boolean");
		this.field2type.put("campagnePaie", "boolean");
		this.field2type.put("mutuelle", "boolean");
		this.field2type.put("nationalite", "nationalite");
		this.field2type.put("numeroTitreSejour", "string");
		this.field2type.put("dateValidite", "date");
		this.field2type.put("sexe", "sexe");

		this.nationalite2enumNationalite.put("FR","FR");
		this.nationalite2enumNationalite.put("fr","FR");
		this.nationalite2enumNationalite.put("Fr","FR");
		this.nationalite2enumNationalite.put("ITALIENNE","EEE");
		this.nationalite2enumNationalite.put("ALGERIENNE","HORS_EEE");
		this.nationalite2enumNationalite.put("SENEGALAISE","HORS_EEE");
		this.nationalite2enumNationalite.put("INDONESIENNE","HORS_EEE");
		this.nationalite2enumNationalite.put("MAROCAINE","HORS_EEE");
		this.nationalite2enumNationalite.put("Marocaine","HORS_EEE");
		this.nationalite2enumNationalite.put("BRITANNIQUE","EEE");
		this.nationalite2enumNationalite.put("TUNISIENNE","HORS_EEE");
		this.nationalite2enumNationalite.put("Japonaise","HORS_EEE");
		this.nationalite2enumNationalite.put("ALLEMAND","EEE");
		this.nationalite2enumNationalite.put("Française","FR");
		this.nationalite2enumNationalite.put("ALGERIEN","HORS_EEE");
		this.nationalite2enumNationalite.put("COREEN DU SUD","HORS_EEE");
		this.nationalite2enumNationalite.put("ROYAUME UNI","EEE");
		this.nationalite2enumNationalite.put("CAMEROUNAISE","HORS_EEE");
		this.nationalite2enumNationalite.put("camerounaise","HORS_EEE");
		this.nationalite2enumNationalite.put("MALGACHE","HORS_EEE");
		this.nationalite2enumNationalite.put("PAYS BAS","EEE");
		this.nationalite2enumNationalite.put("FINLANDAISE","EEE");
		this.nationalite2enumNationalite.put("KIGHIZISTAN","HORS_EEE");
		this.nationalite2enumNationalite.put("CANADIENNE","HORS_EEE");
		this.nationalite2enumNationalite.put("IVOIRIENNE","HORS_EEE");
		this.nationalite2enumNationalite.put("Belge","EEE");
		this.nationalite2enumNationalite.put("BENINOISE","HORS_EEE");
		this.nationalite2enumNationalite.put("haitienne","HORS_EEE");
		this.nationalite2enumNationalite.put("malienne","HORS_EEE");
		this.nationalite2enumNationalite.put("INDIEN","HORS_EEE");
		this.nationalite2enumNationalite.put("ALLEMANDE","EEE");
		this.nationalite2enumNationalite.put("BENINOIS","HORS_EEE");
		this.nationalite2enumNationalite.put("ITALIEN","EEE");
		this.nationalite2enumNationalite.put("Marocain","HORS_EEE");
		this.nationalite2enumNationalite.put("JAPONAISE","HORS_EEE");
		this.nationalite2enumNationalite.put("?","Unknown");

	}

	public boolean controleHeader(String[] header) {
		List<String> listHeader=Arrays.asList(header);

		boolean societe=  listHeader.contains("SOCIETE") ;
		boolean matri=  listHeader.contains("MATRI") ;
		boolean nom = listHeader.contains("NOM") ;
		boolean prenom =  listHeader.contains("PRENOM");
		boolean campagnePaie=  listHeader.contains("CAMPAGNE-PAIE");
		return matri && societe && nom && prenom && campagnePaie;
	}

}
