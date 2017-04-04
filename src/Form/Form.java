package Form;
import java.util.Date;

public class Form {

    //Application
    private int repID;
    private int registry;
    private boolean domestic;
    private boolean beer;
    private String brandName;
    private String address;
    private int phoneNumber;
    private String email;
    private double alcoholPercentage;
    //Wine only
    private int vintageYear;
    private double pHLevel;

    //Applicant's Certification
    private Date appDate; // 09/22/2003
    private String applicantName;

    //Revisions
    //Wine
    private String alterVintageDate;
    private String alterpHLevel;
    private String alterWineAlcoholContent;
    //Beer
    private String alterBeerAlcoholContent;

    public Form(int repID, int registry, boolean domestic, boolean beer, String brandName, String address, int phoneNumber, String email, double alcoholPercentage, int vintageYear, double pHLevel, Date appDate, String applicantName, String alterVintageDate, String alterpHLevel, String alterWineAlcoholContent, String alterBeerAlcoholContent) {
        this.repID = repID;
        this.registry = registry;
        this.domestic = domestic;
        this.beer = beer;
        this.brandName = brandName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.alcoholPercentage = alcoholPercentage;
        this.vintageYear = vintageYear;
        this.pHLevel = pHLevel;
        this.appDate = appDate;
        this.applicantName = applicantName;
        this.alterVintageDate = alterVintageDate;
        this.alterpHLevel = alterpHLevel;
        this.alterWineAlcoholContent = alterWineAlcoholContent;
        this.alterBeerAlcoholContent = alterBeerAlcoholContent;
    }

    public int getRepID() {
        return repID;
    }

    public void setRepID(int repID) {
        this.repID = repID;
    }

    public int getRegistry() {
        return registry;
    }

    public void setRegistry(int registry) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public int getVintageYear() {
        return vintageYear;
    }

    public void setVintageYear(int vintageYear) {
        this.vintageYear = vintageYear;
    }

    public double getpHLevel() {
        return pHLevel;
    }

    public void setpHLevel(double pHLevel) {
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
