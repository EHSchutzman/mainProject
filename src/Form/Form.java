
import java.time.LocalDate;
import java.util.UUID;

public class Form {

    private String formID;
    //Application
    private String repID;
    private String permitNo;
    private String source;
    private int type; //use type code to search it in DB, 80 - wine, 901 - beer
    private String brandName;
    //address
    private String companyName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String tradename;
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

    //Revisions
    //Wine
    private String alterVintageDate;
    private String alterpHLevel;
    private String alterWineAlcoholContent;
    //Beer
    private String alterBeerAlcoholContent;

    //constructor
    public Form() {
        this.setStatus("processing");
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

    public String getPermitNo() {
        return permitNo;
    }

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
