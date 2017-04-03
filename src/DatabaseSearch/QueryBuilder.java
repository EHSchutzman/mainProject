package DatabaseSearch;

/**
 * The QueryBuilder receives the search criteria from the SearchController and forms SQL statements that access the Database.
 */
public class QueryBuilder {

    // Variables for COLA search info
    private static String COLATableName = "";
    private static String COLAFields = "";      // in SQL-compatible format
    // QUERY
    private StringBuilder stringBuilder = new StringBuilder();
    private String query = "";
    private String fromDate;        // Application approval date

    private String toDate;          // Application approval date
    private String brandName;       // Name of brand on application
    private String productName;     // Name of product on application
    private String typeFrom;        // Product/Class code
    private String typeTo;          // Product/Class code
    private String originCode;      // Origin/Location code (abbr.)

    // Empty constructor
    public QueryBuilder() {

    }

    // COLA search constructor
    protected QueryBuilder(String fromDate, String toDate, String brandName, String productName, String typeFrom, String typeTo, String originCode) {
        setFromDate(fromDate);
        setToDate(toDate);
        setBrandName(brandName);
        setProductName(productName);
        setTypeFrom(typeFrom);
        setTypeTo(typeTo);
        setOriginCode(originCode);

        // build query
        stringBuilder.append("SELECT ").append(COLAFields).append(" FROM ").append(COLATableName);

        // Maybe check that no strings are empty before appending where?
        stringBuilder.append(" WHERE");

        boolean firstCond = true;

        // REPLACE WITH ACTUAL DB FIELD NAMES
        if(getFromDate() != null && !getFromDate().isEmpty() && getTypeTo() != null && !getTypeTo().isEmpty()){
            if(!firstCond) {
                stringBuilder.append(" AND ").append(" date BETWEEN #").append(getFromDate());
                stringBuilder.append("# AND #").append(getToDate()).append("#");
            } else {
                stringBuilder.append(" date BETWEEN #").append(getFromDate());
                stringBuilder.append("# AND #").append(getToDate()).append("#");
                firstCond = false;
            }
        }
        if(getBrandName() != null && !getBrandName().isEmpty()){
            if(!firstCond) {
                stringBuilder.append(" AND ").append(" brandName='").append(getBrandName()).append("'");
            } else {
                stringBuilder.append(" brandName='").append(getBrandName()).append("'");
                firstCond = false;
            }
        }
        if(getProductName() != null && !getProductName().isEmpty()){
            if(!firstCond) {
                stringBuilder.append(" AND ").append(" productName='").append(getProductName()).append("'");
            } else {
                stringBuilder.append(" productName='").append(getProductName()).append("'");
                firstCond = false;
            }
        }
        if(getTypeFrom() != null && !getTypeFrom().isEmpty() && getTypeTo() != null && !getTypeTo().isEmpty()){
            if(!firstCond) {
                stringBuilder.append(" AND ").append(" type BETWEEN ").append(Integer.getInteger(getTypeFrom()));
                stringBuilder.append(" AND ").append(Integer.getInteger(getTypeTo()));
            } else {
                stringBuilder.append(" type BETWEEN ").append(Integer.getInteger(getTypeFrom()));
                stringBuilder.append(" AND ").append(Integer.getInteger(getTypeTo()));
                firstCond = false;
            }
        }
        if(getOriginCode() != null && !getOriginCode().isEmpty()){
            if(!firstCond) {
                stringBuilder.append(" AND ").append(" originCode='").append(getOriginCode()).append("'");
            } else {
                stringBuilder.append(" originCode='").append(getOriginCode()).append("'");
                firstCond = false;
            }
        }

        // Generate the query
        setQuery(stringBuilder.toString());
    }

    // Getters and Setters
    private String getFromDate() {
        return fromDate;
    }

    private void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    private String getToDate() {
        return toDate;
    }

    private void setToDate(String toDate) {
        this.toDate = toDate;
    }

    private String getBrandName() {
        return brandName;
    }

    private void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    private String getProductName() {
        return productName;
    }

    private void setProductName(String productName) {
        this.productName = productName;
    }

    private String getTypeFrom() {
        return typeFrom;
    }

    private void setTypeFrom(String typeFrom) {
        this.typeFrom = typeFrom;
    }

    private String getTypeTo() {
        return typeTo;
    }

    private void setTypeTo(String typeTo) {
        this.typeTo = typeTo;
    }

    private String getOriginCode() {
        return originCode;
    }

    private void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    protected String getQuery() {
        return query;
    }

    private void setQuery(String query) {
        this.query = query;
    }
}
