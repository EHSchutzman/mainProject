package Form;


import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

public class Form {
    private String ttbID;
    private String repID;
    private String permitNo;
    private String source;
    private String serialNo;
    private String alcoholType;
    private String brandName;
    private String fancifulName;
    private double alcoholContent;

    private String applicantCity;
    private String applicantState;
    private String applicantZip;
    private String applicantCountry;
    private String mailingAddress;

    private String formula;
    private String phoneNo;
    private String email;
    private String labelText;
    private String labelImage;
    private Date submitDate;
    private String signature;
    private String status;
    private String agentID;
    private String applicantID;
    private Date approvedDate;
    private Date expirationDate;

    // wines only
    private String vintageYear;
    private int phLevel;
    private String grapeVarietals;
    private String wineAppelation;

    //application type
    ArrayList<Boolean> applicationType;
    ArrayList<String> typeText;

    /*private int type; //use type code to search it in DB, 80 - wine, 901 - beer

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
    //mailing address
    private String addressMailing;
    private String addressMailing1;
    private String addressMailing2;
    private String cityMailing;
    private String stateMailing;
    private String zipCodeMailing;
    private String countryMailing;
    //Applicant's Certification
    private String applicantName;
    //TTB Use (accept/reject)
    private String agentName;*/

    //constructor
    public Form() {}

    //generate a unique ID for the form
    public String makeUniqueID () {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public Form(String ttbID, String repID, String permitNo, String source, String serialNo, String alcoholType,
                String brandName, String fancifulName, double alcoholContent, String applicantCity, String applicantState,
                String applicantZip, String applicantCountry, String mailingAddress, String formula, String phoneNo,
                String email, String labelText, String labelImage, Date submitDate, String signature, String status,
                String agentID, String applicantID, Date approvedDate, Date expirationDate, String vintageYear,
                int phLevel, String grapeVarietals, String wineAppelation, ArrayList<Boolean> applicationType, ArrayList<String> typeText) {
        this.ttbID = ttbID;
        this.repID = repID;
        this.permitNo = permitNo;
        this.source = source;
        this.serialNo = serialNo;
        this.alcoholType = alcoholType;
        this.brandName = brandName;
        this.fancifulName = fancifulName;
        this.alcoholContent = alcoholContent;
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
}