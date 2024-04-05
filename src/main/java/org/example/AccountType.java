package org.example;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 4/2/2024, Tuesday
 **/
public enum AccountType {

    SAVINGS_ACCOUNT(1),
    RECURRING_DEPOSIT_ACCOUNT(2),
    FIXED_DEPOSIT_ACCOUNT(3);

    private final int accountType;
    private AccountType(int accountType) {
        this.accountType = accountType;
    }
    public int getAccountType() {
        return accountType;
    }
}
