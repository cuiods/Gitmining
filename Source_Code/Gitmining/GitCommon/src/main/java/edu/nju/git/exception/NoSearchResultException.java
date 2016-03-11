package edu.nju.git.exception;

/**
 * This exception is thrown when this is no result to match the search keyword.
 * @author benchaodong
 * @date 2016-03-08
 */
public class NoSearchResultException extends Exception {

    private String message;

    public NoSearchResultException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
