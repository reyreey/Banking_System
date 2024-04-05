package org.example.exceptions;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 3/6/2024, Wednesday
 *
 * Exception for validate input data values
 *
 **/
public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
        System.out.println("!!!EXCEPTION!!!");
    }
}

