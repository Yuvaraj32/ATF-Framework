package DataSource;



public class GlobalData {

	/* Default Project Setup global data values */
	private static String patientFirstName = "";
	private static String patientLastName = "";
	private static String physicianFirstName = "";
	private static String physicianLastName = "";
	private static String physicianFullName = "";
	private static String patientFullName = "";
	private static String patientMRNumber = "";
	private static String claimNumber = "";
	private static String patientMiddleInitial = "";
	private static String remittanceNumber = "";
	private static int currentDataRow = 1;
	private static String authorizationNumber = "";
	private static String insuranceName = "";
	private static String insuranceAbbreviation = "";
	private static String lastName = "";
	private static String firstName = "";
	private static String userName = "";
	private static String temporaryPwd = "";
	private static String emailAddress = "";
	private static String loginCredentials = "";
	private static String addressOne = "";
	private static String addressTwo = "";
	private static String zipCode = "";
	private static String payerId = "";
	private static String physicianUpin = "";
	private static String physicianNpi = "";
	private static String physicianLicense = "";
	private static String physicianExpiration = "";
	private static String communityCare = "";
	private static String facilityName = "";
	private static String facilityNpi = "";
	private static String trainerName = "";
	private static String trainerPwd = "";
	private static String date = "";
	private static String zipCode2 = "";
	private static String taskName = "";
	private static String caseManager = "";
	private static String INS_EDI_ISA05 = "";
	private static String INS_EDI_ISA06 = "";
	private static String INS_EDI_ISA07 = "";
	private static String INS_EDI_ISA08 = "";
	private static String INS_EDI_ISA15 = "";
	private static String INS_EDI_GS02 = "";
	private static String INS_EDI_GS03 = "";
	private static String INS_EDI_PRV03 = "";
	private static String INS_EDI_RB_2010BB_NM109 = "";
	private static String benefitPeriodCount = "";
	private static String idgTeam = "";
			
	/**
	 * Method to reset Global Data
	 */
	public static void resetGlobalData() {
		setPatientFirstName("");
		setPatientLastName("");
		setPhysicianLastName("");
		setPhysicianFirstName("");
		setPhysicianFullName("");
		setPatientFullName("");
		setPatientMRNumber("");
		setClaimNumber("");
		setPatientMiddleInitial("");
		setRemittanceNumber("");
		setCurrentDataRow(1);
		setAuthorizationNumber("");
		setInsuranceName("");
		setInsuranceAbbreviation("");
		setLastName("");
		setFirstName("");
		setUserName("");
		setTemporaryPwd("");
		setEmailAddress("");
		setLoginCredentials("");
		setAddressOne("");
		setAddressTwo("");
		setZipCode("");
		setPayerId("");
		setPhysicianUpin("");
		setPhysicianNpi("");
		setPhysicianExpiration("");
		setPhysicianExpiration("");
		setCommunityCare("");
		setFacilityName("");
		setFacilityNpi("");
		setTrainerName("");
		setTrainerPwd("");
		setDate("");
		setZipCode2("");
		setTaskName("");
		setCaseManagerName("");
		setINS_EDI_ISA05("");
		setINS_EDI_ISA06("");
		setINS_EDI_ISA07("");
		setINS_EDI_ISA08("");
		setINS_EDI_ISA15("");
		setINS_EDI_GS02("");
		setINS_EDI_GS03("");
		setINS_EDI_PRV03("");
		setINS_EDI_RB_2010BB_NM109("");
	}

	public static String getPatientFirstName() {
		return patientFirstName;
	}

	public static void setPatientFirstName(String patientFirstName) {
		GlobalData.patientFirstName = patientFirstName;
	}

	public static String getPatientLastName() {
		return patientLastName;
	}

	public static void setPatientLastName(String patientLastName) {
		GlobalData.patientLastName = patientLastName;
	}

	public static String getPhysicianFirstName() {
		return physicianFirstName;
	}

	public static void setPhysicianFirstName(String physicianFirstName) {
		GlobalData.physicianFirstName = physicianFirstName;
	}

	public static String getPhysicianLastName() {
		return physicianLastName;
	}

	public static void setPhysicianLastName(String physicianLastName) {
		GlobalData.physicianLastName = physicianLastName;
	}
	
	public static String getPhysicianFullName() {
		return physicianFullName;
	}

	public static void setPhysicianFullName(String physicianFullName) {
		GlobalData.physicianFullName = physicianFullName;
	}
	
	public static String getPatientFullName() {
		return patientFullName;
	}

	public static void setPatientFullName(String patientFullName) {
		GlobalData.patientFullName = patientFullName;
	}

	public static String getPatientMRNumber() {
		return patientMRNumber;
	}

	public static void setPatientMRNumber(String patientMRNumber) {
		GlobalData.patientMRNumber = patientMRNumber;
	}

	public static String getClaimNumber() {
		return claimNumber;
	}
	
	public static void setClaimNumber(String claimNumer) {
		GlobalData.claimNumber = claimNumer;
	}
	
	public static String getInsuranceName() {
		return insuranceName;
	}

	public static void setInsuranceName(String insuranceName) {
		GlobalData.insuranceName = insuranceName;
	}

	public static String getInsuranceAbbreviation() {
		return insuranceAbbreviation;
	}

	public static void setInsuranceAbbreviation(String insuranceAbbreviation) {
		GlobalData.insuranceAbbreviation = insuranceAbbreviation;
	}

	public static String getPatientMiddleInitial() {
		return patientMiddleInitial;
	}

	public static void setPatientMiddleInitial(String patientMiddleInitial) {
		GlobalData.patientMiddleInitial = patientMiddleInitial;
	}

	public static String getRemittanceNumber() {
		return remittanceNumber;
	}

	public static void setRemittanceNumber(String remittanceNumber) {
		GlobalData.remittanceNumber = remittanceNumber;
	}

	public static int getCurrentDataRow() {
		return currentDataRow;
	}

	public static void setCurrentDataRow(int currentDataRow) {
		GlobalData.currentDataRow = currentDataRow;
	}

	public static String getAuthorizationNumber() {
		return authorizationNumber;
	}
		
	public static void setAuthorizationNumber(String authorizationNumber) {
		GlobalData.authorizationNumber = authorizationNumber;
	}
	
	public static String getLastName() {
		return lastName;
	}

	public static void setLastName(String lastName) {
		GlobalData.lastName = lastName;
	}
	
	public static String getFirstName() {
		return firstName;
	}

	public static void setFirstName(String firstName) {
		GlobalData.firstName = firstName;
	}
	
	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		GlobalData.userName = userName;
	}
	
	public static String getTemporaryPwd() {
		return temporaryPwd;
	}

	public static void setTemporaryPwd(String temporaryPwd) {
		GlobalData.temporaryPwd = temporaryPwd;
	}
	
	public static String getEmailAddress() {
		return emailAddress;
	}

	public static void setEmailAddress(String emailAddress) {
		GlobalData.emailAddress = emailAddress;
	}
	
	public static String getAddressOne() {
		return addressOne;
	}

	public static void setAddressOne(String addressOne) {
		GlobalData.addressOne = addressOne;
	}
	
	public static String getAddressTwo() {
		return addressTwo;
	}

	public static void setAddressTwo(String addressTwo) {
		GlobalData.addressTwo = addressTwo;
	}
	
	public static String getZipCode() {
		return zipCode;
	}

	public static void setZipCode(String zipCode) {
		GlobalData.zipCode = zipCode;
	}
	
	public static String getZipCode2() {
		return zipCode2;
	}
	
	public static void setZipCode2(String zipCode2) {
		GlobalData.zipCode2 = zipCode2;
	}
	
	public static String getPayerId() {
		return payerId;
	}

	public static void setPayerId(String payerId) {
		GlobalData.payerId = payerId;
	}
	
	public static String getPhysicianUpin() {
		return physicianUpin;
	}

	public static void setPhysicianUpin(String physicianUpin) {
		GlobalData.physicianUpin = physicianUpin;
	}
	
	public static String getPhysicianNpi() {
		return physicianNpi;
	}

	public static void setPhysicianNpi(String physicianNpi) {
		GlobalData.physicianNpi = physicianNpi;
	}
	
	public static String getPhysicianLicense() {
		return physicianLicense;
	}

	public static void setPhysicianLicense(String physicianLicense) {
		GlobalData.physicianLicense = physicianLicense;
	}	
	
	public static String getPhysicianExpiration() {
		return physicianExpiration;
	}

	public static void setPhysicianExpiration(String physicianExpiration) {
		GlobalData.physicianExpiration = physicianExpiration;
	}
	
	public static void setTrainerName(String trainerName) {
		GlobalData.trainerName = trainerName;
	}	
	
	public static String getTrainerName() {
		return trainerName;
	}
	
	public static void setTrainerPwd(String trainerPwd) {
		GlobalData.trainerPwd = trainerPwd;
	}	
	
	public static String getTrainerPwd() {
		return trainerPwd;
	}
	
	public static String getCommunityCare() {
		return communityCare;
	}

	public static void setCommunityCare(String communityCare) {
		GlobalData.communityCare = communityCare;
	}
	
	public static String getDate() {
		return date;
	}

	public static void setDate(String date) {
		GlobalData.date = date;
	}
	
	public static String getLoginCredentials() {
		return loginCredentials;
	}

	public static void setLoginCredentials(String loginCredentials) {
		GlobalData.loginCredentials = loginCredentials;
	}
	
	public static String getFacilityName() {
		return facilityName;
	}

	public static void setFacilityName(String facilityName) {
		GlobalData.facilityName = facilityName;
	}
	
	public static String getFacilityNpi() {
		return facilityNpi;
	}

	public static void setFacilityNpi(String facilityNpi) {
		GlobalData.facilityNpi = facilityNpi;
	}
	
	public static String getTaskName() {
		return taskName;
	}

	public static void setTaskName(String taskName) {
		GlobalData.taskName = taskName;
	}
	
	public static String getCaseManagerName() {
		return caseManager;
	}

	public static void setCaseManagerName(String caseManager) {
		GlobalData.caseManager = caseManager;
	}

	public static void setINS_EDI_ISA05(String INS_EDI_ISA05) {
		GlobalData.INS_EDI_ISA05 = INS_EDI_ISA05;
	}
	
	public static String getINS_EDI_ISA05() {
		return INS_EDI_ISA05;
	}
	
	public static void setINS_EDI_ISA06(String INS_EDI_ISA06) {
		GlobalData.INS_EDI_ISA06 = INS_EDI_ISA06;
	}
	
	public static String getINS_EDI_ISA06() {
		return INS_EDI_ISA06;
	}
	
	public static void setINS_EDI_ISA07(String INS_EDI_ISA07) {
		GlobalData.INS_EDI_ISA07 = INS_EDI_ISA07;
	}

	public static String getINS_EDI_ISA07() {
		return INS_EDI_ISA07;
	}
	
	public static void setINS_EDI_ISA08(String INS_EDI_ISA08) {
		GlobalData.INS_EDI_ISA08 = INS_EDI_ISA08;
	}
	
	public static String getINS_EDI_ISA08() {
		return INS_EDI_ISA08;
	}
	
	public static void setINS_EDI_ISA15(String INS_EDI_ISA15) {
		GlobalData.INS_EDI_ISA15 = INS_EDI_ISA15;
	}

	public static String getINS_EDI_ISA15() {
		return INS_EDI_ISA15;
	}
	
	public static void setINS_EDI_GS02(String INS_EDI_GS02) {
		GlobalData.INS_EDI_GS02 = INS_EDI_GS02;
	}

	public static String getINS_EDI_GS02() {
		return INS_EDI_GS02;
	}
	
	public static void setINS_EDI_GS03(String INS_EDI_GS03) {
		GlobalData.INS_EDI_GS03 = INS_EDI_GS03;
	}

	public static String getINS_EDI_GS03() {
		return INS_EDI_GS03;
	}
	
	public static void setINS_EDI_PRV03(String INS_EDI_PRV03) {
		GlobalData.INS_EDI_PRV03 = INS_EDI_PRV03;
	}
	public static String getINS_EDI_PRV03() {
		return INS_EDI_PRV03;
	}
	
	public static void setINS_EDI_RB_2010BB_NM109(String INS_EDI_RB_2010BB_NM109) {
		GlobalData.INS_EDI_RB_2010BB_NM109 = INS_EDI_RB_2010BB_NM109;
	}
	
	public static String getINS_EDI_RB_2010BB_NM109() {
		return INS_EDI_RB_2010BB_NM109;
	}
	
	public static String getBenefitPeriodCount() {
		return benefitPeriodCount;
	}

	public static void setBenefitPeriodCount(String benefitPeriodCount) {
		GlobalData.benefitPeriodCount = benefitPeriodCount;
	}
	
	public static String getIDGTeam() {
		return idgTeam;
	}

	public static void setIDGTeam(String idgTeam) {
		GlobalData.idgTeam = idgTeam;
	}
}

