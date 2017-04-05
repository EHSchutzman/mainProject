package DatabaseSearch;

/**
 * The QueryBuilder receives the search criteria from the SearchController and forms SQL statements that access the Database.
 */
public class QueryBuilder {

    // QUERY
    private StringBuilder stringBuilder = new StringBuilder();
    private String tableName;
    private String fields;      // in SQL-compatible format
    private String query = "";

    // User information
    private String username;
    private String password;

    // Application information
    private String appID;
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

    // User lookup search constructor
    protected QueryBuilder(String tableName, String fields,
                           String username, String password) {
        setTableName(tableName);
        setFields(fields);
        setUsername(username);
        setPassword(password);

        // build query
        stringBuilder.append("SELECT ").append(getFields()).append(" FROM APP.").append(getTableName());

        // Maybe check that no strings are empty before appending where?
        stringBuilder.append(" WHERE");

        boolean firstCond = true;

        if (getUsername() != null && !getUsername().isEmpty() && getPassword() != null && !getPassword().isEmpty()) {
            stringBuilder.append(" LOGIN_NAME='").append(getUsername()).append("'");
            stringBuilder.append(" AND PASSWORD='").append(getPassword()).append("'");
        } else { // Error
            System.out.println("Error! We need both a username and password!");
        }

        // Generate the query
        setQuery(stringBuilder.toString());
    }

    /**
     * COLA search constructors
     **/
    // Search for specific application via ID
    protected QueryBuilder(String tableName, String fields,
                           String appID) {
        setTableName(tableName);
        setFields(fields);
        setAppID(appID);
        setFromDate(null);
        setToDate(null);
        setBrandName(null);
        setProductName(null);
        setTypeFrom(null);
        setTypeTo(null);
        setOriginCode(null);

        // Build the search
        buildCOLASearchQuery();

        // Generate the query
        setQuery(stringBuilder.toString());
    }

    // Search for all applications via criteria
    protected QueryBuilder(String tableName, String fields,
                           String fromDate, String toDate, String brandName, String productName, String typeFrom, String typeTo, String originCode) {
        setTableName(tableName);
        setFields(fields);
        setAppID(null);
        setFromDate(fromDate);
        setToDate(toDate);
        setBrandName(brandName);
        setProductName(productName);
        setTypeFrom(typeFrom);
        setTypeTo(typeTo);
        setOriginCode(originCode);

        // Build the search
        buildCOLASearchQuery();

        // Generate the query
        setQuery(stringBuilder.toString());
    }

    // Build the COLA Search Query after parameters have been input
    private void buildCOLASearchQuery() {
        // build query
        stringBuilder.append("SELECT ").append(getFields()).append(" FROM APP.").append(getTableName());

        // Maybe check that no strings are empty before appending where?
        stringBuilder.append(" WHERE");

        boolean firstCond = true;

        // REPLACE WITH ACTUAL DB FIELD NAMES
        if (getAppID() != null && !getAppID().isEmpty()) {
            if (!firstCond) {
                stringBuilder.append(" AND").append(" APP_ID=").append(getAppID());
            } else {
                stringBuilder.append(" APP_ID=").append(getAppID());
                firstCond = false;
            }
        }
        if (getFromDate() != null && !getFromDate().isEmpty() && getTypeTo() != null && !getTypeTo().isEmpty()) {
            if (!firstCond) {
                stringBuilder.append(" AND").append(" DATE BETWEEN '").append(getFromDate());
                stringBuilder.append("' AND '").append(getToDate()).append("'");
            } else {
                stringBuilder.append(" DATE BETWEEN '").append(getFromDate());
                stringBuilder.append("' AND '").append(getToDate()).append("'");
                firstCond = false;
            }
        }
        if (getBrandName() != null && !getBrandName().isEmpty()) {
            if (!firstCond) {
                stringBuilder.append(" AND").append(" BRAND_NAME='").append(getBrandName()).append("'");
            } else {
                stringBuilder.append(" BRAND_NAME='").append(getBrandName()).append("'");
                firstCond = false;
            }
        }
        if (getProductName() != null && !getProductName().isEmpty()) {
            if (!firstCond) {
                stringBuilder.append(" AND").append(" PRODUCT_NAME='").append(getProductName()).append("'");
            } else {
                stringBuilder.append(" PRODUCT_NAME='").append(getProductName()).append("'");
                firstCond = false;
            }
        }
        if (getTypeFrom() != null && !getTypeFrom().isEmpty() && getTypeTo() != null && !getTypeTo().isEmpty()) {
            if (!firstCond) {
                stringBuilder.append(" AND").append(" TYPE BETWEEN ").append(getTypeFrom());
                stringBuilder.append(" AND ").append(getTypeTo());
            } else {
                stringBuilder.append(" TYPE BETWEEN ").append(getTypeFrom());
                stringBuilder.append(" AND ").append(getTypeTo());
                firstCond = false;
            }
        }
        if (getOriginCode() != null && !getOriginCode().isEmpty()) {
            if (!firstCond) {
                stringBuilder.append(" AND").append(" ORIGIN_CODE='").append(getOriginCode()).append("'");
            } else {
                stringBuilder.append(" ORIGIN_CODE='").append(getOriginCode()).append("'");
                firstCond = false;
            }
        }
    }

    // Getters and Setters
    private String getTableName() {
        return tableName;
    }

    private void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private String getFields() {
        return fields;
    }

    private void setFields(String fields) {
        this.fields = fields;
    }

    private String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private String getAppID() {
        return appID;
    }

    private void setAppID(String appID) {
        this.appID = appID;
    }

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
