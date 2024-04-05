package org.example.exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate account balance value
 **/
public class AccountInvalidDataException extends InvalidDataException{
    public AccountInvalidDataException(String message) {
        super(message);
    }
}
