package achille;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import achille.auth.Authority;
import achille.dao.UserDAO;
import achille.model.User;
import achille.password.PasswordUtils;


@SpringBootApplication
public class AchilleApplication {


	private static ReadAppProp readAppProp;
	private static UserDAO userDAO;

	@Autowired
	public void setReadAppProp(ReadAppProp readAppProp){
		AchilleApplication.readAppProp = readAppProp;
	}
	@Autowired
	public void setUserDAO(UserDAO userDAO){
		AchilleApplication.userDAO = userDAO;
	}

	public static void main(String[] args)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, SQLException {

		SpringApplication.run(AchilleApplication.class, args);

		if (readAppProp.stateDb.equals("create")) {
			AchilleApplication.initDataBase();
		}
	}

	private static void initDataBase() throws SQLException {

		String url = readAppProp.dbUrl;
		String databaseName = readAppProp.nameDb;
		String user = readAppProp.usrName;
		String password = readAppProp.usrPswd;
		String passwordGenerated = readAppProp.siteAdminPwd;
		String userName = readAppProp.siteAdminUserName;

		int userid = 99999;
		String authority = "ADMIN";
		String salt = PasswordUtils.getSalt(20);
		String securedPassword = PasswordUtils.generateSecurePassword(passwordGenerated, salt);

		Connection conn = DriverManager.getConnection(url, user, password);

		Statement st = conn.createStatement();
			
		//System.out.println("INSERT INTO " + databaseName + ".authority (authority) VALUES ('ADMIN')");
		
		st.executeUpdate("INSERT INTO " + databaseName + ".authority (authority) VALUES ('ADMIN')");
		st.executeUpdate("INSERT INTO " + databaseName + ".authority (authority) VALUES ('CONSULTANT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".societe (value) VALUES ('INTERVIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".societe (value) VALUES ('B2M')");
		st.executeUpdate("INSERT INTO " + databaseName + ".societe (value) VALUES ('INTERMISSION')");
		st.executeUpdate("INSERT INTO " + databaseName + ".societe (value) VALUES ('SINOPIE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".societe (value) VALUES ('RONDOUDOU')");
		st.executeUpdate("INSERT INTO " + databaseName + ".type_contrat (value) VALUES ('CDD')");
		st.executeUpdate("INSERT INTO " + databaseName + ".type_contrat (value) VALUES ('CDI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".type_contrat (value) VALUES ('CDIC/CDI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".type_contrat (value) VALUES ('retraité')");
		st.executeUpdate("INSERT INTO " + databaseName + ".type_contrat (value) VALUES ('CDI/Retraité')");
		st.executeUpdate("INSERT INTO " + databaseName + ".type_contrat (value) VALUES ('CDI/Retraité?')");
		st.executeUpdate("INSERT INTO " + databaseName + ".type_contrat (value) VALUES ('CDD/Retraité?')");
		st.executeUpdate("INSERT INTO " + databaseName + ".type_contrat (value) VALUES ('CDI INTERNE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('2B CONSULTING')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('8POURCENT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ABC SYSTEMES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ACENSI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ADMV')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ADNEOM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ADYTON')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AEZAN')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AFD.TECH')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AGILICIO')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AGT INNOVATION')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AKKA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ALADIUM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ALEYSIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ALLEGIS GROUP')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ALLIACOM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ALMAVIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ALTER SOLUTIONS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ALTEZIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AMEG')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AMESSI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AMETIX')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ANTARES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('APRILE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('APSIDE TOP')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('APTERYX FORMATIONS SARL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ARCOSYS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ARKEMA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ARTHUR HUNT TRANSITION')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AS DELIVERY')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ASAP CONSEIL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ASENSIEL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ASTAN INFORMATIQUE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ATTENTION TRAVAIL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AUDENSIEL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AUDENSIEL DIGITAL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AUDENSIEL TECHNOLOGIES SUD OUEST')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AURA MAY')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AUSY')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AVA2I - LITTLE BIG CONNECTION')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AVECVOO/MAYDAY')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AVENIR CONSEIL FORMATION')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('AVENTURE PEUGEOT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BAI SA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BEMORE MONACO')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BHI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BIP DIFFUSION')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BISCUIT HOLDING')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BONNE PIOCHE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BPCE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BPCE IT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BRITTANY FERRIES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BROCELIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BUREAU VERITAS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('Business & Engineering Solutions')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BVBA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('BVBA ATELIER HAIDER ACKBRMANN BVBA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CARREFOUR')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CARREFOUR (TJM 499,20€)')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CE GEMALTO')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CELAD')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CHLOE SAS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CMF CONSULTING')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('COMITEM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('COMPUTER FUTURS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CONSORT GROUP')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CROSS KNOWLEDGE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES (\"CS SYSTEMES d'INFORMATION\")");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CTS NORD')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('CTY')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVEO')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON AQUITAINE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON ENERGIE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON EST')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON GRENOBLE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON INDUSTRIE LYON')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON INNOVATION & MOBILITE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON MIDI PYRENEES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON NORD')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON OUEST')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON PACA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON PARIS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON RESEAUX ET MULTIMEDIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON SI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON SI NORD')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON SWITZERLAND')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON TECHNOLOGIES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON TELECOM ET SI LYON')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON TELECOME ET SI LYON')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON TRANSPORTS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DAVIDSON TTA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DBV TECHNOLOGIES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DCS EASYWARE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DEGETEL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DELVILLE MANAGEMENT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('DEVOTEAM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('E3M MANAGEMENT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ECONOCOM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ELSYS Design')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('EMAGINE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ENTREPRENEURS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('EOLEN')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ESI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ESI DIGITAL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ETIENNE LACROIX')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('EULIDIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('EUROSTAFF')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('EXPORT ENTREPRISE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('EXTEAM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('Fédération Française du Sport Automobile')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('FFSA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('FHM INGENIERIE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('FIME')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('FINAXYS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('FNMF')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('FORMATEUR')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('FOTOFEVER')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('FREE TEAM IT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('FULL ERP PERFORMANCE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('GALAXY SOFTWARE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('GEMALTO')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('GEOPHYSICS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('GFI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('HARVEY NASH IT CONSLTING N.V')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('HAYS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('HEEM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('HOXTON PARTNERS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('HR EXPERTISE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('HR TEAM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('HUBSAN')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('HUXLEY')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('IBP')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('IDEMIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('IDEX SERVICES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('IFPASS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('INFODIS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('INFOGENE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('INFOTEL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('INGEROP')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('INTERVIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ISABEY GESTION')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('IT LINK')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('IT&M SOLUTIONS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('KEY CONSULTING')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('KEY4CAST')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('KEYWER')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('LAIINTERNATIONAL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('LE GALEC')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('LEAP 29')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('LINA GORA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('LINEON')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('LOGTEAM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('LOGWARE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('LUTECEO')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('LYNX RH')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MAGELLAN')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MARGIELA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MATIS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MBA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MCG')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MEGA SERVICES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MICHELIN')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MISYS France')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MOHAMED SOUIAI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MONTREAL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MOOSE MILANO')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MPA SERVICE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MPEARL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MPI PARTNERS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('MYRIAD')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('NEXTEDIA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('NEXTER MUNITIONS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('NIGEL FRANCK INTERNATIONAL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('NIGEL FRANCK INTERNATIONAL LIMITED')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('NIJI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('OBERTHUR')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('OCSIGROUP')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('OLIGOS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ONEPOINT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('OPENMINDED')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('OPTEAMIS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('OWENTIS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PACO RABANNE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PANDA SERVICES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PARAGRAPH')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PAYLIB SERVICES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PI SERVICES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PICKMEUP')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PREMIUM PEERS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PRIME ENGINEERING')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PROCADRES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PROCADRES SUISSE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PROGRESSIVE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('PROXIAD IDF SAS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('QUINTEN')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('RATP')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('RCH CONSULTING')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('RED SAP SOLUTIONS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ROBONOTIC')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('ROIK')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('RSB FRANCE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SAMSUNG')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SAPSTARS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SCORE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SEMOFI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SGS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SHIFT CONSULTING')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SIKHOM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SISMOCEAN')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SNCF')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SOAT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SOFTCOMPANY')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SOM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SOM CALCUL')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SPIE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('STA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('STREIT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('STS MANAGEMENT')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('STX France')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SUNLOG TECHNOLOGIE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('SYSTEMIS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('TALAN')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('TALAN CORPORATE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('TEK KER')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('TELITEM PARIS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('TEXELIS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('TLTI')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('TLTI INFORMATIQUE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('TREVES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('TRSB')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('UBS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('UCC EXPERTS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('UNIWARE GLOBAL SERVICES')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('V&A FASHION')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('VALLOUREC')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('VALTUS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('VENEDIM')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('VIVREA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('XPM TRANSITION PARTNERS')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('YELE')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('YOMEVA')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('YOSEMITE INVEST')");
		st.executeUpdate("INSERT INTO " + databaseName + ".partenaire (value) VALUES ('YVES SAINT LAURENT BEAUTE')");

		st.executeUpdate("INSERT INTO " + databaseName + ".campagne "
				+ "(id_campagne, annee_campagne, date_ouverture, etat, mois_campagne) "
				+ "VALUES (201901, 2019, '2019-01-01', 'O', 1)");

		st.executeUpdate("INSERT INTO " + databaseName + ".user "
				+ "(user_id, username, password, salt) "
				+ "VALUES (" + Integer.toString(userid) + ", '" + userName + "', '" + securedPassword + "', '" + salt + "')");
		
		st.executeUpdate("INSERT INTO " + databaseName + ".user_authority "
				+ "(user_user_id, authority_authority) "
				+ "VALUES ('" + Integer.toString(userid) + "', '" + authority + "')");

		conn.close();

		/*List<Authority> la = new ArrayList<Authority>();
		la.add(new Authority(authority));
		User u = new User(userid, userName, securedPassword, salt, la);
		userDAO.save(u);*/

	}
}

@Component
class ReadAppProp
{

	@Value("${spring.jpa.hibernate.ddl-auto}")
	public String stateDb;
	@Value("${databaseName}")
	public String nameDb;
	@Value("${spring.datasource.url}")
	public String dbUrl;
	@Value("${spring.datasource.username}")
	public String usrName;
	@Value("${spring.datasource.password}")
	public String usrPswd;
	@Value("${siteAdminPwd}")
	public String siteAdminPwd;
	@Value("${siteAdminUserName}")
	public String siteAdminUserName;

}


