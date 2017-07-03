package KTAFW;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class Config {
	
	public String filenameLocal = "config.local.properties";
    public String filenameJenkins = System.getProperty("configJenkins");
    public Properties config;

    {
    	Properties fallback = new Properties();
    
        /* Default Project Setup global configuration values */
        fallback.put("driversPath", "C:\\sdrivers\\");
        fallback.put("sourcePath", new File("src\\.").getAbsolutePath());
        fallback.put("testSuite", "0");
        fallback.put("downloads", new File("").getAbsolutePath() + "\\Downloads\\");

        /* Selenium Grid Configurations */
        fallback.put("gridExecution", "0");
        fallback.put("gridHubAddress", "http://10.20.0.26:4444/wd/hub");

        /* Default Browser Credentials */
        fallback.put("appUrl", "https://develop.kinnser.net/");
        fallback.put("browserType", "chrome");

        /* AM Default Browser and Application Credentials */
        fallback.put("appUserName", "uber.user.bill2");
        fallback.put("appPassword", "mma4uber");
        
        /* AM Default Browser and Application Credentials For BM1 AM */
        fallback.put("appUserNameBM1", "uber.user");
        fallback.put("appPasswordBM1", "mma4uber");

        /* Default PB Clinic Application Credentials */
        fallback.put("appUserNamePB", "uber.user.pb");
        fallback.put("appPasswordPB", "mma4uber");
        
        /* Default TM Application Credentials */
        fallback.put("appUserNameTM", "uber.user.tm");
        fallback.put("appPasswordTM", "mma4uber");
        
        /* Default PB Clinic Application Credentials */
        fallback.put("appUserNamePA", "dr.feelgood.qa@gmail.com");
        fallback.put("appPasswordPA", "mma4uber");
        
        /* Default KH Application Credentials */
        fallback.put("appUserNameKH", "uber.user.hm");
        fallback.put("appPasswordKH", "mma4uber");
        
        /* Default KH Application Credentials */
        fallback.put("appUserNamePTG", "ptg.trainer");
        fallback.put("appPasswordPTG", "mma4uber");
        
        /* Default Calltrack Application Credentials */
        fallback.put("appUserNameCalltrack", "KQA.Auto");
        fallback.put("appPasswordCalltrack", "Aut0m@teIt");

        /* Default reports file name configuration */
        fallback.put("testCycle", "REG");
        fallback.put("testEnvironment", "Develop");

        /* Database connection info */ 
        fallback.put("dbserver", "AUS-DEV-DB001");
        fallback.put("dbuser", "cfUserAuto");
        fallback.put("dbpassword", "1b2h3s4D");
    	fallback.put("dbname", "Develop");
    	fallback.put("reportdbname", "DevelopReport");

    	/* Default KH Application User Credentials */
        fallback.put("appUserNameKHUser", "uber.user.hm");
        fallback.put("appPasswordKHUser", "mma4user"); 
        
        /* Default Load Timeout */
        fallback.put("pageLoadTimeout", "60");
    	
    	config = new Properties(fallback);
    	 	
    	File configFile =  new File( filenameLocal );
		if( configFile.exists() && configFile.isFile() ){
	    	loadConfig( filenameLocal );
		}else { 
			loadConfig( filenameJenkins );
		}

		Enumeration<?> e = config.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = config.getProperty(key);
		}
    }
    
    
    public void loadConfig ( String filename ){
    	try {
    		// load default configuration
    		InputStream stream = new FileInputStream( filename );
    		try {

    			config.load(stream);
    	    	//System.out.println("Configuration loaded from  " + filename);
    		}
        	catch (Exception ex) {
        		/* Handle exception. */
        		ex.printStackTrace();
        	}
    		finally {
    			stream.close();
    		}
    	}
    	catch (IOException ex) {
    		/* Handle exception. */
    		System.out.println("Unable to find default configuration file " + new File("").getAbsolutePath() + "\\" + filename);
    	}
    }

	/*private static String filenameLocal = "config.local.properties";
	private static String filenameJenkins = System.getProperty("configJenkins");
	private static final Properties config;

	static {
		Properties fallback = new Properties();

		 Default Project Setup global configuration values 
		fallback.put("driversPath", "C:\\sdrivers\\");
		fallback.put("sourcePath", new File("src\\.").getAbsolutePath());
		fallback.put("testSuite", "0");
		fallback.put("downloads", new File("").getAbsolutePath() + "\\Downloads\\");

		 Selenium Grid Configurations 
		fallback.put("gridExecution", "0");
		fallback.put("gridHubAddress", "http://10.20.0.26:4444/wd/hub");

		 Default Browser Credentials 
		fallback.put("appUrl", "https://develop.kinnser.net/");
		fallback.put("browserType", "chrome");

		 AM Default Browser and Application Credentials 
		fallback.put("appUserName", "uber.user.bill2");
		fallback.put("appPassword", "mma4uber");

		 AM Default Browser and Application Credentials For BM1 AM 
		fallback.put("appUserNameBM1", "uber.user");
		fallback.put("appPasswordBM1", "mma4uber");

		 Default PB Clinic Application Credentials 
		fallback.put("appUserNamePB", "uber.user.pb");
		fallback.put("appPasswordPB", "mma4uber");

		 Default TM Application Credentials 
		fallback.put("appUserNameTM", "uber.user.tm");
		fallback.put("appPasswordTM", "mma4uber");

		 Default PB Clinic Application Credentials 
		fallback.put("appUserNamePA", "dr.feelgood.qa@gmail.com");
		fallback.put("appPasswordPA", "mma4uber");

		 Default KH Application Credentials 
		fallback.put("appUserNameKH", "uber.user.hm");
		fallback.put("appPasswordKH", "mma4uber");

		 Default KH Application Credentials 
		fallback.put("appUserNamePTG", "ptg.trainer");
		fallback.put("appPasswordPTG", "mma4uber");

		 Default Calltrack Application Credentials 
		fallback.put("appUserNameCalltrack", "KQA.Auto");
		fallback.put("appPasswordCalltrack", "Aut0m@teIt");

		 Default reports file name configuration 
		fallback.put("testCycle", "REG");
		fallback.put("testEnvironment", "Develop");

		 Database connection info  
		fallback.put("dbserver", "AUS-DEV-DB001");
		fallback.put("dbuser", "cfUserAuto");
		fallback.put("dbpassword", "1b2h3s4D");
		fallback.put("dbname", "Develop");
		fallback.put("reportdbname", "DevelopReport");

		 Default KH Application User Credentials 
		fallback.put("appUserNameKHUser", "uber.user.hm");
		fallback.put("appPasswordKHUser", "mma4user"); 

		config = new Properties(fallback);

		File configFile =  new File( filenameLocal );
		if( configFile.exists() && configFile.isFile() ){
			loadConfig( filenameLocal );
		}else { 
			loadConfig( filenameJenkins );
		}

		Enumeration<?> e = config.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = config.getProperty(key);
		}
	}

	private static void loadConfig ( String filename ){
		try {
			// load default configuration
			InputStream stream = new FileInputStream( filename );
			try {

				config.load(stream);
				System.out.println("Configuration loaded from  " + filename);
			}
			catch (Exception ex) {
				 Handle exception. 
				ex.printStackTrace();
			}
			finally {
				stream.close();
			}
		}
		catch (IOException ex) {
			 Handle exception. 
			System.out.println("Unable to find default configuration file " + new File("").getAbsolutePath() + "\\" + filename);
		}
	}*/


	//@ Getters & Setters
	public String getReportsPath() {
		return config.getProperty("reportsPath");
	}

	public void setReportsPath(String reportsPath) {
		config.put("reportsPath", reportsPath);
	}

	public String getScreenShotsPath() {
		return config.getProperty("screenShotsPath");
	}

	public void setScreenShotsPath(String screenShotsPath) {
		config.put("screenShotsPath", screenShotsPath);
	}

	public String getSourcePath() {
		return config.getProperty("sourcePath");
	}

	public void setSourcePath(String sourcePath) {
		config.put("sourcePath", sourcePath);
	}

	public boolean isTestSuite() {
		return config.getProperty("testSuite").equalsIgnoreCase("1");
	}

	public void setTestSuite(boolean testSuite) {
		config.put("testSuite", testSuite ? "1" : "0");
	}

	public String getAppUserName() {
		return config.getProperty("appUserName");
	}

	public void setAppUserName(String appUserName) {
		config.put("appUserName", appUserName);
	}

	public String getAppUserNameBM1() {
		return config.getProperty("appUserNameBM1");
	}

	public void setAppUserNameBM1(String appUserNameBM1) {
		config.put("appUserNameBM1", appUserNameBM1);
	}

	public String getAppPassword() {
		return config.getProperty("appPassword");
	}

	public String getAppUserKey() {
		return config.getProperty("appUserKey");
	}

	public void setAppPassword(String appPassword) {
		config.put("appPassword", appPassword);
	}

	public String getAppPasswordBM1() {
		return config.getProperty("appPasswordBM1");
	}

	public void setAppPasswordBM1(String appPasswordBM1) {
		config.put("appPasswordBM1", appPasswordBM1);
	}

	public String getAppUrl() {
		return config.getProperty("appUrl");
	}

	public void setAppUrl(String appUrl) {
		config.put("appUrl", appUrl);
	}

	public String getAppUrlIP() {
		return config.getProperty("appUrlIP");
	}

	public void setAppUrlIP(String appUrlIP) {
		config.put("appUrlIP", appUrlIP);
	}

	public String getBrowserType() {
		return config.getProperty("browserType");
	}

	public void setBrowserType(String browserType) {
		config.put("browserType", browserType);
	}

	public String getDriversPath() {
		return config.getProperty("driversPath");
	}

	public void setDriversPath(String driversPath) {
		config.put("driversPath", driversPath);
	}

	public boolean isGridExecution() {
		return config.getProperty("gridExecution").equalsIgnoreCase("1");
	}

	public void setGridExecution(boolean gridExecution) {
		config.put("gridExecution", gridExecution ? "1" : "0" );
	}

	public String getGridHubAddress() {
		return config.getProperty("gridHubAddress");
	}

	public void setGridHubAddress(String gridHubAddress) {
		config.put("gridHubAddress", gridHubAddress);
	}

	public String getTestCycle() {
		return config.getProperty("testCycle");
	}

	public void setTestCycle(String testCycle) {
		config.put("testCycle", testCycle);
	}

	public String getTestEnvironment() {
		return config.getProperty("testEnvironment");
	}

	public void setTestEnvironment(String testEnvironment) {
		config.put("testEnvironment", testEnvironment);
	}

	public String getDBServer() {
		return config.getProperty("dbserver");
	}

	public String getDBUser() {
		return config.getProperty("dbuser");
	}

	public String getDBPassword() {
		return config.getProperty("dbpassword");
	}

	public String getDBName() {
		return config.getProperty("dbname");
	}

	public String getReportDBName() {
		return config.getProperty("reportdbname");
	}

	public String getAppPasswordKH() {
		return config.getProperty("appPasswordKH");
	}

	public void setAppPasswordKH(String appPasswordKH) {
		config.put("appPasswordKH", appPasswordKH);
	}

	public String getAppUserNameKH() {
		return config.getProperty("appUserNameKH");
	}

	public void setAppUserNameKH(String appUserNameKH) {
		config.put("appUserNameKH", appUserNameKH);
	}

	public String getAppUserNameCalltrack() {
		return config.getProperty("appUserNameCalltrack");
	}

	public void setAppUserNameCalltrack(String appUserNameCalltrack) {
		config.put("appUserNameCalltrack", appUserNameCalltrack);
	}

	public String getAppPasswordCalltrack() {
		return config.getProperty("appPasswordCalltrack");
	}

	public void setAppPasswordCalltrack(String appPasswordCalltrack) {
		config.put("appPasswordCalltrack", appPasswordCalltrack);
	}

	public String getAppUserNameAM() {
		return config.getProperty("appUserNameAM");
	}

	public void setAppUserNameAM(String appUserNamePA) {
		config.put("appUserNameAM", appUserNamePA);
	}

	public String getAppPasswordAM(){
		return config.getProperty("appPasswordAM");
	}

	public void setAppPasswordAM(String appPasswordAM){
		config.put("appPasswordAM", appPasswordAM);
	}

	public String getAppUserNameTM() {
		return config.getProperty("appUserNameTM");
	}

	public void setAppUserNameTM(String appUserNameTM) {
		config.put("appUserNameTM", appUserNameTM);
	}

	public String getAppPasswordTM(){
		return config.getProperty("appPasswordTM");
	}

	public void setAppPasswordTM(String appPasswordTM){
		config.put("appPasswordTM", appPasswordTM);
	}

	public String getAppUserNamePA() {
		return config.getProperty("appUserNamePA");
	}

	public void setAppUserNamePA(String appUserNamePA) {
		config.put("appUserNamePA", appUserNamePA);
	}

	public String getAppPasswordPA(){
		return config.getProperty("appPasswordPA");
	}

	public void setAppPasswordPA(String appPasswordPA){
		config.put("appPasswordPA", appPasswordPA);
	}

	public String getAppUserNamePTG(){
		return config.getProperty("appUserNamePTG");
	}

	public String getAppPasswordPTG(){
		return config.getProperty("appPasswordPTG");
	}

	public void setAppUserNamePTG(String appUserNamePTG){
		config.put("appUserNamePTG", appUserNamePTG);
	}

	public void setAppPasswordPTG(String appPasswordPTG){
		config.put("appPasswordPTG", appPasswordPTG);
	}

	public String getEdiRestagingPath() {
		String ediRestagingPath = config.getProperty("ediRestagingPath");
		// If the config value is not an absolute path, prepend the vsSharePath
		if (ediRestagingPath.substring(0, 1) != "\\" && !ediRestagingPath.contains(":")){
			ediRestagingPath = config.getProperty("vsSharePath") + ediRestagingPath;
		}
		return ediRestagingPath;
	}

	public void setEdiRestagingPath(String ediRestagingPath) {
		config.put("ediRestagingPath", ediRestagingPath);
	}

	public String getDownloads() {
		return config.getProperty("downloads");
	}

	public void setDownloads(String downloads) {
		config.put("downloads", downloads);
	}

	public String getVsharePath() {
		return config.getProperty("vsSharePath");
	}
	public String getAppUserNamePB() {
		return config.getProperty("appUserNamePB");
	}

	public String getAppPasswordPB() {
		return config.getProperty("appPasswordPB");
	}

	public String getPdfGeneratorUrl() {
		return config.getProperty("pdfGeneratorUrl");
	}

	public void setPdfGeneratorUrl(String pdfGeneratorUrl) {
		config.put("pdfGeneratorUrl", pdfGeneratorUrl);
	}

	public long getImplicitWait() {
		return Long.parseLong(config.getProperty("implicitWait"));
	}

	public void setImplicitWait(long waitTime) {
		config.put("implicitWait", waitTime);
	}

	public long getExplicitWait() {
		return Long.parseLong(config.getProperty("explicitWait"));
	}

	public void setExplicitWait(int waitTime) {
		config.put("explicitWait", waitTime);
	}

	public long getRetryCount() {
		return Long.parseLong(config.getProperty("retryCount"));
	}

	public void setRetryCount(int count) {
		config.put("retryCount", count);
	}

	public String getCnaUserNameKH() {
		return config.getProperty("cnaUserNameKH");
	}

	public void setCnaUserNameKH(String cnaUserNameKH) {
		config.put("cnaUserNameKH", cnaUserNameKH);
	}

	public String getCnaPasswordKH() {
		return config.getProperty("cnaPasswordKH");
	}

	public void setCnaPasswordKH(String cnaPasswordKH) {
		config.put("cnaPasswordKH", cnaPasswordKH);
	}

	public String getRnUserNameKH() {
		return config.getProperty("rnUserNameKH");
	}

	public void setRnUserNameKH(String rnUserNameKH) {
		config.put("rnUserNameKH", rnUserNameKH);
	}

	public String getRnPasswordKH() {
		return config.getProperty("rnPasswordKH");
	}

	public void setRnPasswordKH(String rnPasswordKH) {
		config.put("rnPasswordKH", rnPasswordKH);
	}

	public String getCnaUserNameHH() {
		return config.getProperty("cnaUserNameHH");
	}

	public void setCnaUserNameHH(String cnaUserNameHH) {
		config.put("cnaUserNameHH", cnaUserNameHH);
	}

	public String getCnaPasswordHH() {
		return config.getProperty("cnaPasswordHH");
	}

	public void setCnaPasswordHH(String cnaPasswordHH) {
		config.put("cnaPasswordKH", cnaPasswordHH);
	}

	public String getRnUserNameHH() {
		return config.getProperty("rnUserNameHH");
	}

	public void setRnUserNameHH(String rnUserNameHH) {
		config.put("rnUserNameHH", rnUserNameHH);
	}

	public String getRnPasswordHH() {
		return config.getProperty("rnPasswordHH");
	}

	public void setRnPasswordHH(String rnPasswordHH) {
		config.put("rnPasswordHH", rnPasswordHH);
	}
	
	public String getAdlUserName() {
		return config.getProperty("adlUserName");
	}

	public void setAdlUserName(String adlUserName) {
		config.put("adlUserName", adlUserName);
	}
	
	public String getAdlPassword() {
		return config.getProperty("adlPassword");
	}

	public void setAdlPassword(String adlPassword) {
		config.put("adlPassword", adlPassword);
	}
	
	public String getAdlAccountId() {
		return config.getProperty("adlAccountId");
	}

	public void setAdlAccountId(String adlAccountId) {
		config.put("adlAccountId", adlAccountId);
	}

	// Property to set the amount of time(In Seconds) to notify WebDriver instance to wait for specific time, 
	// if any element is readily not available
	// By default, implicit wait time is 10 seconds
	public int getImplicitWaitTime()
	{
		String propertyValue = config.getProperty("implicitlyWaitTimeout");

		if (propertyValue == null)
		{
			propertyValue = "30";
		}

		return Integer.parseInt(propertyValue);
	}		      

	public void setImplicitWaitTime(int implicitWaitTime)
	{
		if(config.containsKey("implicitlyWaitTimeout")){
			config.put("implicitlyWaitTimeout","implicitWaitTime");
		}
	}


	// Property to set the amount of time to wait for a page load to complete
	// By default, pageload wait time is 60 seconds
	public int getpageLoadTimeout()
	{
		String propertyValue = config.getProperty("pageLoadTimeout");

		if (propertyValue == null)
		{
			propertyValue = "180";
		}


		return Integer.parseInt(propertyValue);
	}

	public void setpageLoadTimeout(int pageLoadTimeout)
	{
		config.put("pageLoadTimeout",pageLoadTimeout);
		/*if(config.containsKey(pageLoadTimeout)){
			config.put("pageLoadTimeout",pageLoadTimeout);
		}*/
	}
}
