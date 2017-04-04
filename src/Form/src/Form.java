//package Form;
import java.util.Date;

public class Form {

    private int formNumber;
    //Application
    private String repID;
    private String registry;
    private boolean domestic; //false for imported
    private boolean beer;     //false for wine
    private String brandName;
    //address
    private String companyName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String tradename;
    //
    private String phoneNumber;
    private String email;
    private String alcoholPercentage;
    //Wine only
    private String vintageYear;
    private String pHLevel;

    //Applicant's Certification
    private Date appDate; // 09/22/2003
    private String applicantName;

    //TTB Use (accept/reject)
    private boolean accepted;   //false if rejected
    private Date approvalDate;
    private String agentName;
    private Date expirationDate;

    //Revisions
    //Wine
    private String alterVintageDate;
    private String alterpHLevel;
    private String alterWineAlcoholContent;
    //Beer
    private String alterBeerAlcoholContent;

    //empty constructor
    public Form() {
    }

    public int getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(int formNumber) {
        this.formNumber = formNumber;
    }

    public String getRepID() {
        return repID;
    }

    public void setRepID(String repID) {
        this.repID = repID;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    public boolean isBeer() {
        return beer;
    }

    public void setBeer(boolean beer) {
        this.beer = beer;
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

    public String getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(String alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
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

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAlterVintageDate() {
        return alterVintageDate;
    }

    public void setAlterVintageDate(String alterVintageDate) {
        this.alterVintageDate = alterVintageDate;
    }

    public String getAlterpHLevel() {
        return alterpHLevel;
    }

    public void setAlterpHLevel(String alterpHLevel) {
        this.alterpHLevel = alterpHLevel;
    }

    public String getAlterWineAlcoholContent() {
        return alterWineAlcoholContent;
    }

    public void setAlterWineAlcoholContent(String alterWineAlcoholContent) {
        this.alterWineAlcoholContent = alterWineAlcoholContent;
    }

    public String getAlterBeerAlcoholContent() {
        return alterBeerAlcoholContent;
    }

    public void setAlterBeerAlcoholContent(String alterBeerAlcoholContent) {
        this.alterBeerAlcoholContent = alterBeerAlcoholContent;
    }
}
