package jdbc.model;

public class CustomerModel {
    private int customerId;
    private String customerCompanyName;
    private String customerContactName;
    private String customerContactTitle;
    private String customerAddress;
    private String customerCity;
    private String customerRegion;
    private String customerPostalCode;
    private String customerCountry;
    private String customerPhoneNumber;
    private String customerFaxNumber;

    public CustomerModel() {
    }

    public CustomerModel(int customerId, String customerCompanyName, String customerContactName,
                         String customerContactTitle, String customerAddress, String customerCity,
                         String customerRegion, String customerPostalCode, String customerCountry,
                         String customerPhoneNumber, String customerFaxNumber) {
        this.customerId = customerId;
        this.customerCompanyName = customerCompanyName;
        this.customerContactName = customerContactName;
        this.customerContactTitle = customerContactTitle;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerRegion = customerRegion;
        this.customerPostalCode = customerPostalCode;
        this.customerCountry = customerCountry;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerFaxNumber = customerFaxNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    public String getCustomerContactName() {
        return customerContactName;
    }

    public void setCustomerContactName(String customerContactName) {
        this.customerContactName = customerContactName;
    }

    public String getCustomerContactTitle() {
        return customerContactTitle;
    }

    public void setCustomerContactTitle(String customerContactTitle) {
        this.customerContactTitle = customerContactTitle;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerRegion() {
        return customerRegion;
    }

    public void setCustomerRegion(String customerRegion) {
        this.customerRegion = customerRegion;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerFaxNumber() {
        return customerFaxNumber;
    }

    public void setCustomerFaxNumber(String customerFaxNumber) {
        this.customerFaxNumber = customerFaxNumber;
    }
}
