package org.example.exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate customer birthdate value
 *
 **/
public class BirthDateException extends CustomerInvalidDataException {
    public BirthDateException(String message) {
        super(message);
    }
}
