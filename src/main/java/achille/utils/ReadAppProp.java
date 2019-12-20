package achille.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReadAppProp
{	

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String stateDb;
	@Value("${databaseName}")
	private String nameDb;
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.username}")
	private String usrName;
	@Value("${spring.datasource.password}")
	private String usrPswd;
	@Value("${siteAdminPwd}")
	private String siteAdminPwd;
	@Value("${siteAdminUserName}")
	private String siteAdminUserName;
	@Value("${siteAdminPwd_2}")
	private String siteAdminPwd_2;
	@Value("${siteAdminUserName_2}")
	private String siteAdminUserName_2;
	public String getSiteAdminPwd_2() {
		return siteAdminPwd_2;
	}
	public void setSiteAdminPwd_2(String siteAdminPwd_2) {
		this.siteAdminPwd_2 = siteAdminPwd_2;
	}
	public String getSiteAdminUserName_2() {
		return siteAdminUserName_2;
	}
	public void setSiteAdminUserName_2(String siteAdminUserName_2) {
		this.siteAdminUserName_2 = siteAdminUserName_2;
	}
	@Value("${emailAdress}")
	private String emailAdress;
	@Value("${emailPwd}")
	private String emailPwd;
	
	private Properties appProps;
	private Map<String, String> displayName2propName;
	
	public ReadAppProp(String Env) throws IOException {
		
		String configFileName = "application.properties";
		
		InputStream is = getClass().getClassLoader().getResourceAsStream(configFileName);
		this.appProps = new Properties();
		this.appProps.load(is);
		
		this.displayName2propName = new HashMap<String, String>();
		
		this.displayName2propName.put("stateDb","spring.jpa.hibernate.ddl-auto");
		this.displayName2propName.put("nameDb","databaseName");
		this.displayName2propName.put("dbUrl","spring.datasource.url");
		this.displayName2propName.put("usrName","spring.datasource.username");
		this.displayName2propName.put("usrPswd","spring.datasource.password");
		this.displayName2propName.put("siteAdminPwd","siteAdminPwd");
		this.displayName2propName.put("siteAdminUserName","siteAdminUserName");
		this.displayName2propName.put("emailAdress","emailAdress");
		this.displayName2propName.put("emailPwd","emailPwd");

	}
	public ReadAppProp() throws IOException {
		
		String configFileName = "application.properties";
		
		InputStream is = getClass().getClassLoader().getResourceAsStream(configFileName);
		this.appProps = new Properties();
		this.appProps.load(is);
		
		this.displayName2propName = new HashMap<String, String>();
		
		this.displayName2propName.put("stateDb","spring.jpa.hibernate.ddl-auto");
		this.displayName2propName.put("nameDb","databaseName");
		this.displayName2propName.put("dbUrl","spring.datasource.url");
		this.displayName2propName.put("usrName","spring.datasource.username");
		this.displayName2propName.put("usrPswd","spring.datasource.password");
		this.displayName2propName.put("siteAdminPwd","siteAdminPwd");
		this.displayName2propName.put("siteAdminUserName","siteAdminUserName");
		this.displayName2propName.put("emailAdress","emailAdress");
		this.displayName2propName.put("emailPwd","emailPwd");
	
	}
	public String getProperty(String name) {
		String propName = name;
		if (this.displayName2propName.containsKey(name))
			propName = this.displayName2propName.get(name);
		return this.appProps.getProperty(propName);
	}
	public String getStateDb() {
		return stateDb;
	}
	public void setStateDb(String stateDb) {
		this.stateDb = stateDb;
	}
	public String getNameDb() {
		return nameDb;
	}
	public void setNameDb(String nameDb) {
		this.nameDb = nameDb;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public String getUsrPswd() {
		return usrPswd;
	}
	public void setUsrPswd(String usrPswd) {
		this.usrPswd = usrPswd;
	}
	public String getSiteAdminPwd() {
		return siteAdminPwd;
	}
	public void setSiteAdminPwd(String siteAdminPwd) {
		this.siteAdminPwd = siteAdminPwd;
	}
	public String getSiteAdminUserName() {
		return siteAdminUserName;
	}
	public void setSiteAdminUserName(String siteAdminUserName) {
		this.siteAdminUserName = siteAdminUserName;
	}
	public Map<String, String> getDisplayName2propName() {
		return displayName2propName;
	}
	public void setDisplayName2propName(Map<String, String> displayName2propName) {
		this.displayName2propName = displayName2propName;
	}
	public String getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}
	public String getEmailPwd() {
		return emailPwd;
	}
	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}

}
