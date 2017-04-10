package Form;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

public class Form {
    // Label Info
    private String ttbID;
    private String repID;           // x textfield
    private String permitNo;        // x textfield
    private String serialNo;        // x textfield
    private String source;          // x radio buttons
    private String alcoholType;     // x radio buttons
    private String brandName;       // x textfield
    private String fancifulName;    // x textfield
    private double alcoholContent;  // x textfield v
    private String formula;         // x
    private String labelImage;      //
    private String labelText;       // x
    // Wines only
    private String vintageYear;     // x
    private int phLevel;            // x
    private String grapeVarietals;  // x
    private String wineAppelation;  // x
    // Application type
    ArrayList<Boolean> applicationType;// x check boxes
    ArrayList<String> typeText;        // x textfields corresponding to their check boxes

    // Applicant info
    // Addresses
    private String applicantStreet; // x two textfields
    private String applicantCity;   // x
    private String applicantState;  // x
    private String applicantZip;    // x
    private String applicantCountry;// x
    private String mailingAddress;  // x has checkbox to see if it should be the same as regular address
    //
    private String signature;       // x
    private String phoneNo;         // x
    private String email;           // x

    // Application info
    private Date submitDate;        // x
    private String status;          // x
    private String agentID;         //
    private String applicantID;     //
    private Date approvedDate;      // x
    private Date expirationDate;    // x
    private String approvalComments;// x




    //constructor
    public Form() {}

    //generate a unique ID for the form
    public String makeUniqueID () {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public Form(String ttbID, String repID, String permitNo, String source, String serialNo, String alcoholType,
                String brandName, String fancifulName, double alcoholContent, String applicantStreet, String applicantCity, String applicantState,
                String applicantZip, String applicantCountry, String mailingAddress, String formula, String phoneNo,
                String email, String labelText, String labelImage, Date submitDate, String signature, String status,
                String agentID, String applicantID, Date approvedDate, Date expirationDate, String vintageYear,
                int phLevel, String grapeVarietals, String wineAppelation, ArrayList<Boolean> applicationType, ArrayList<String> typeText, String approvalComments) {
        this.ttbID = ttbID;
        this.repID = repID;
        this.permitNo = permitNo;
        this.source = source;
        this.serialNo = serialNo;
        this.alcoholType = alcoholType;
        this.brandName = brandName;
        this.fancifulName = fancifulName;
        this.alcoholContent = alcoholContent;
        this.applicantStreet = applicantStreet;
        this.applicantCity = applicantCity;
        this.applicantState = applicantState;
        this.applicantZip = applicantZip;
        this.applicantCountry = applicantCountry;
        this.mailingAddress = mailingAddress;
        this.formula = formula;
        this.phoneNo = phoneNo;
        this.email = email;
        this.labelText = labelText;
        this.labelImage = labelImage;
        this.submitDate = submitDate;
        this.signature = signature;
        this.status = status;
        this.agentID = agentID;
        this.applicantID = applicantID;
        this.approvedDate = approvedDate;
        this.expirationDate = expirationDate;
        this.vintageYear = vintageYear;
        this.phLevel = phLevel;
        this.grapeVarietals = grapeVarietals;
        this.wineAppelation = wineAppelation;
        this.applicationType = applicationType;
        this.typeText = typeText;
        this.approvalComments = approvalComments;
    }

    public String getTtbID() {
        return ttbID;
    }

    public String getRepID() {
        return repID;
    }

    public String getPermitNo() {
        return permitNo;
    }

    public String getSource() {
        return source;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public String getAlcoholType() {
        return alcoholType;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getFancifulName() {
        return fancifulName;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public String getApplicantStreet() {
        return applicantStreet;
    }

    public String getApplicantCity() {
        return applicantCity;
    }

    public String getApplicantState() {
        return applicantState;
    }

    public String getApplicantZip() {
        return applicantZip;
    }

    public String getApplicantCountry() {
        return applicantCountry;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public String getFormula() {
        return formula;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public String getLabelText() {
        return labelText;
    }

    public String getLabelImage() {
        return labelImage;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public String getSignature() {
        return signature;
    }

    public String getStatus() {
        return status;
    }

    public String getAgentID() {
        return agentID;
    }

    public String getApplicantID() {
        return applicantID;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public String getApprovalComments() {
        return approvalComments;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getVintageYear() {
        return vintageYear;
    }

    public int getPhLevel() {
        return phLevel;
    }

    public String getGrapeVarietals() {
        return grapeVarietals;
    }

    public String getWineAppelation() {
        return wineAppelation;
    }

    public ArrayList<Boolean> getApplicationType() {
        return applicationType;
    }

    public ArrayList<String> getTypeText() {
        return typeText;
    }

    public void setTtbID(String ttbID) {
        this.ttbID = ttbID;
    }

    public void setRepID(String repID) {
        this.repID = repID;
    }

    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setAlcoholType(String alcoholType) {
        this.alcoholType = alcoholType;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setFancifulName(String fancifulName) {
        this.fancifulName = fancifulName;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public void setApplicantStreet(String applicantStreet) {
        this.applicantStreet = applicantStreet;
    }

    public void setApplicantCity(String applicantCity) {
        this.applicantCity = applicantCity;
    }

    public void setApplicantState(String applicantState) {
        this.applicantState = applicantState;
    }

    public void setApplicantZip(String applicantZip) {
        this.applicantZip = applicantZip;
    }

    public void setApplicantCountry(String applicantCountry) {
        this.applicantCountry = applicantCountry;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public void setLabelImage(String labelImage) {
        this.labelImage = labelImage;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setApprovalComments(String approvalComments) {
        this.approvalComments = approvalComments;
    }

    public void setVintageYear(String vintageYear) {
        this.vintageYear = vintageYear;
    }

    public void setPhLevel(int phLevel) {
        this.phLevel = phLevel;
    }

    public void setGrapeVarietals(String grapeVarietals) {
        this.grapeVarietals = grapeVarietals;
    }

    public void setWineAppelation(String wineAppelation) {
        this.wineAppelation = wineAppelation;
    }

    public void setApplicationType(ArrayList<Boolean> applicationType) {
        this.applicationType = applicationType;
    }

    public void setTypeText(ArrayList<String> typeText) {
        this.typeText = typeText;
    }
}