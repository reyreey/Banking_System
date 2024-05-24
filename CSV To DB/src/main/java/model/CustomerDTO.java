package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import exceptions.BirthDateException;
import exceptions.NationalIdException;
import exceptions.NullDataException;


import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * a class for customer data
 *
 **/
public class CustomerDTO {
    private String customerId;
    private String customerName;
    private String customerSurname;
    private String customerAddress;
    private String customerZipCode;
    private String customerNationalId;
    private String customerBirthDate;
    @OneToMany(mappedBy = "customerDTO")
    @JsonBackReference
    private Collection<AccountDTO> customerAccounts= new ArrayList<>();

    /**
     * @return customer ralated accounts
     */
    public Collection<AccountDTO> getCustomerAccounts() {
        return customerAccounts;
    }

    /**
     * @param customerAccounts customer ralated accounts
     */
    public void setCustomerAccounts(Collection<AccountDTO> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }

    /**
     * add new account to customer accounts
     * @param account new account object
     */
    public void addAccount(AccountDTO account) {
        customerAccounts.add(account);
    }

    /**
     * remove an  account from customer accounts
     * @param account account object
     */
    public void removeAccount(AccountDTO account){
        customerAccounts.remove(account);
    }

    /**
     * @return customer id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId customer id
     * @throws NullDataException
     */
    public void setCustomerId(String customerId) throws NullDataException {
        if (customerId.equals("")){
            throw new NullDataException("customer id could not be null!");
        }
        this.customerId = customerId;
    }

    /**
     * @return customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName customer name
     * @throws NullDataException
     */
    public void setCustomerName(String customerName) throws NullDataException {
        if (customerName.equals("")){
            throw new NullDataException("customer name could not be null!");
        }
        this.customerName = customerName;
    }

    /**
     * @return customer surname
     */
    public String getCustomerSurname() {
        return customerSurname;
    }

    /**
     * @param customerSurname customer surname
     * @throws NullDataException
     */
    public void setCustomerSurname(String customerSurname) throws NullDataException {
        if (customerSurname.equals("")){
            throw new NullDataException("customer surname could not be null!");
        }
        this.customerSurname = customerSurname;
    }

    /**
     * @return customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress customer address
     * @throws NullDataException
     */
    public void setCustomerAddress(String customerAddress) throws NullDataException {
        if (customerAddress.equals("")){
            throw new NullDataException("customer address could not be null!");
        }
        this.customerAddress = customerAddress;
    }

    /**
     * @return customer zip code
     */
    public String getCustomerZipCode() {
        return customerZipCode;
    }

    /**
     * @param customerZipCode customer zip code
     * @throws NullDataException
     */
    public void setCustomerZipCode(String customerZipCode) throws NullDataException {
        if (customerZipCode.equals("")){
            throw new NullDataException("customer zip code could not be null!");
        }
        this.customerZipCode = customerZipCode;
    }

    /**
     * @return customer national id
     */
    public String getCustomerNationalId() {
        return customerNationalId;
    }

    /**
     * @param customerNationalId customer national id
     * @throws NationalIdException
     * @throws NullDataException
     */
    public void setCustomerNationalId(String customerNationalId) throws NationalIdException, NullDataException {
        if (customerNationalId.equals("")){
            throw new NullDataException("customer national id could not be null!");
        }
        else if (customerNationalId.length()!=10) {
            throw new NationalIdException("invalid national id! length of customer national id should be 10 digits.");
        }
        this.customerNationalId = customerNationalId;
    }

    /**
     * @return customer birthdate
     */
    public String getCustomerBirthDate() {
        return customerBirthDate;
    }

    /**
     * @param customerBirthDate  customer birthdate
     * @throws BirthDateException
     * @throws NullDataException
     */
    public void setCustomerBirthDate(String customerBirthDate) throws BirthDateException, NullDataException {
        if (customerBirthDate.equals("")){
            throw new NullDataException("customer birth date could not be null!");
        }
        int bd=Integer.parseInt(customerBirthDate);
        if(bd>1995){
            throw new BirthDateException("invalid birth date! customer birth date can not be after 1995.");
        }
        this.customerBirthDate = customerBirthDate;
    }

    /**
     * @return string presentation of customer object
     */
    @Override
    public String toString() {
        return "CustomerDTO{" +
                "CUSTOMER_ID='" + customerId + '\'' +
                ", CUSTOMER_NAME='" + customerName + '\'' +
                ", CUSTOMER_SURNAME='" + customerSurname + '\'' +
                ", CUSTOMER_NATIONAL_ID='" + customerNationalId + '\'' +
                ", CUSTOMER_ACCOUNTS=" + customerAccounts +
                '}';
    }
}
