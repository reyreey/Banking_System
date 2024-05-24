package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import exceptions.*;


import javax.persistence.ManyToOne;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * a class for account data
 *
 **/
public class AccountDTO {
    private String accountNumber;
    private AccountType accountType;
    private String accountCustomerId;
    @ManyToOne
    @JsonManagedReference
    private CustomerDTO accountCustomer;
    private long accountLimit;
    private String accountOpenDate;
    private long accountBalance;

    /**
     * @return customer object realted to this account
     */
    public CustomerDTO getAccountCustomer() {
        return accountCustomer;
    }

    /**
     * @param accountCustomer customer object realted to this account
     * @throws CheckAccountCustomerException
     */
    public void setAccountCustomer(CustomerDTO accountCustomer) throws CheckAccountCustomerException {
        if(this.accountCustomer!=null){
            this.accountCustomer.removeAccount(this);
        }
        this.accountCustomer = accountCustomer;
        this.accountCustomer.addAccount(this);
    }

    /**
     * @return account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber account number
     * @throws AccountNumberException
     * @throws NullDataException
     */
    public void setAccountNumber(String accountNumber) throws AccountNumberException, NullDataException {
        if (accountNumber.equals("")){
            throw new NullDataException("account number can not be null!");
        }
        else if (accountNumber.length() != 22){
            throw new AccountNumberException("invalid account number! length of account number should be 22 characters.");
        }
        this.accountNumber = accountNumber;
    }

    /**
     * @return type of account
     */
    public String getAccountType() {
        return accountType.getAccountType();
    }

    /**
     * @param accountType type of account(type 1,2 or 3)
     * @throws AccountTypeException
     * @throws NullDataException
     */
    public void setAccountType(String accountType) throws AccountTypeException, NullDataException {
        if (accountType.equals("")){
            throw new NullDataException("account type can not be null!");
        }

        else if (!accountType.matches("[1-3]")){
            throw new AccountTypeException("invalid account type! account type should be one of this values (1,2 or 3)");
        }

        switch (accountType){
            case "1":
                this.accountType = AccountType.SAVINGS_ACCOUNT;
                break;

            case "2":
                this.accountType = AccountType.FIXED_DEPOSIT_ACCOUNT;
                break;

            case "3":
                this.accountType = AccountType.RECURRING_DEPOSIT_ACCOUNT;
                break;
        }
    }

    /**
     * @return related customer id
     */
    public String getAccountCustomerId() {
        return accountCustomerId;
    }

    /**
     * @param accountCustomerId related customer id
     * @throws NullDataException
     */
    public void setAccountCustomerId(String accountCustomerId) throws NullDataException {
        if (accountCustomerId.equals("")){
            throw new NullDataException("account customer number can not be null!");
        }
        this.accountCustomerId = accountCustomerId;
    }

    /**
     * @return account balance limit
     */
    public long getAccountLimit() {
        return accountLimit;
    }

    /**
     * @param accountLimit account balance limit
     * @throws NullDataException
     */
    public void setAccountLimit(String accountLimit) throws NullDataException {
        if (accountLimit.equals("")){
            throw new NullDataException("account limit can not be null!");
        }
        this.accountLimit = Long.parseLong(accountLimit);
    }

    /**
     * @return account open date
     */
    public String getAccountOpenDate() {
        return accountOpenDate;
    }

    /**
     * @param accountOpenDate account open date
     * @throws NullDataException
     */
    public void setAccountOpenDate(String accountOpenDate) throws NullDataException {
        if (accountOpenDate.equals("")){
            throw new NullDataException("account open date can not be null!");
        }
        this.accountOpenDate = accountOpenDate;
    }

    /**
     * @return account balance
     */
    public long getAccountBalance() {
        return accountBalance;
    }

    /**
     * @param accountBalance account balance
     * @throws AccountBalanceException
     * @throws NullDataException
     */
    public void setAccountBalance(String accountBalance) throws AccountBalanceException, NullDataException {

        if (accountBalance.equals("")){
            throw new NullDataException("account balance can not be null!");
        }
        long balance=Long.parseLong(accountBalance);

        if (balance > accountLimit){
            throw new AccountBalanceException("invalid account balance! account balance can not be greater than account balance.");
        }
        this.accountBalance = Long.parseLong(accountBalance);
    }

    /**
     * @return string presentation of an object data
     */
    @Override
    public String toString() {
        return "AccountDTO{" +
                "ACCOUNT_NUMBER='" + accountNumber + '\'' +
                ", ACCOUNT_OPEN_DATE='" + accountOpenDate + '\'' +
                ", ACCOUNT_BALANCE=" + accountBalance +
                '}';
    }
}
