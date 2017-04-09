package Form;
import java.time.LocalDate;
import java.util.UUID;
public class Form {
    private String TTBID;
    //Application
    private String repID;
    private String permitNo;
    private String source;
    private int type; //use type code to search it in DB, 80 - wine, 901 - beer
    private String brandName;
    //address
    private String companyName;
    private String address;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String tradename;
    private String fancifullName;
    //mailing address
    private String addressMailing;
    private String addressMailing1;
    private String addressMailing2;
    private String cityMailing;
    private String stateMailing;
    private String zipCodeMailing;
    private String countryMailing;
    //
    private String phoneNumber;
    private String email;
    private String alcoholContent;
    //Wine only
    private String vintageYear;
    private String pHLevel;
    //Applicant's Certification
    private LocalDate completedDate; // 09/22/2003
    private String applicantName;
    //TTB Use (accept/reject)
    private String status;
    private LocalDate approvalDate;
    private String agentName;
    private LocalDate expirationDate;
    //constructor
    public Form() {
    }
    public Form(String formID, LocalDate completedDate, String repID, String brandName, String fancifulName,
                String source, String companyName, String permitNo, String tradename, String phoneNumber,
                String email, String alcoholContent, String applicantName, int type, String status) {
        this.fancifullName = fancifulName;
        this.formID = formID;
        this.repID = repID;
        this.permitNo = permitNo;
        this.source = source;
        this.type = type;
        this.brandName = brandName;
        this.companyName = companyName;
        this.tradename = tradename;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.alcoholContent = alcoholContent;
        this.completedDate = completedDate;
        this.applicantName = applicantName;
        this.status = status;
    }

    //generate a unique ID for the form
    public String makeUniqueID () {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public String getFormID() {
        return formID;
    }
    public void setFormID(String formID) {
        this.formID = formID;
    }
    public String getRepID() {
        return repID;
    }
    public void setRepID(String repID) {
        this.repID = repID;
    }
    public String getPermitNo() { return permitNo; }
    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getTradename() {
        return tradename;
    }
    public void setTradename(String tradename) {
        this.tradename = tradename;
    }
    public String getAddressMailing() {
        return addressMailing;
    }
    public void setAddressMailing(String addressMailing) {
        this.addressMailing = addressMailing;
    }
    public String getAddressMailing1() {
        return addressMailing1;
    }
    public void setAddressMailing1(String addressMailing1) {
        this.addressMailing1 = addressMailing1;
    }
    public String getAddressMailing2() {
        return addressMailing2;
    }
    public void setAddressMailing2(String addressMailing2) {
        this.addressMailing2 = addressMailing2;
    }
    public String getCityMailing() {
        return cityMailing;
    }
    public void setCityMailing(String cityMailing) {
        this.cityMailing = cityMailing;
    }
    public String getStateMailing() {
        return stateMailing;
    }
    public void setStateMailing(String stateMailing) {
        this.stateMailing = stateMailing;
    }
    public String getZipCodeMailing() {
        return zipCodeMailing;
    }
    public void setZipCodeMailing(String zipCodeMailing) {
        this.zipCodeMailing = zipCodeMailing;
    }
    public String getCountryMailing() {
        return countryMailing;
    }
    public void setCountryMailing(String countryMailing) {
        this.countryMailing = countryMailing;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAlcoholContent() {
        return alcoholContent;
    }
    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }
    public String getVintageYear() {
        return vintageYear;
    }
    public void setVintageYear(String vintageYear) {
        this.vintageYear = vintageYear;
    }
    public String getpHLevel() {
        return pHLevel;
    }
    public void setpHLevel(String pHLevel) {
        this.pHLevel = pHLevel;
    }
    public LocalDate getCompletedDate() {
        return completedDate;
    }
    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }
    public String getApplicantName() {
        return applicantName;
    }
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDate getApprovalDate() {
        return approvalDate;
    }
    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }
    public String getAgentName() {
        return agentName;
    }
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getFancifullName() {
        return fancifullName;
    }

    public void setFancifullName(String fancifullName) {
        this.fancifullName = fancifullName;
    }

    public String getTTBID() {
        return TTBID;
    }
}