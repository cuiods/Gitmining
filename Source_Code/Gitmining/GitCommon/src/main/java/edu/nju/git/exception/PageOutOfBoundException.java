package edu.nju.git.exception;

/**
 * This exception is thrown when the ui module invoke the nextPage or previousPage function but there is no more<br>
 *     pages to return.
 * @author benchaodong
 * @date 2016-03-05
 */
public class PageOutOfBoundException extends Exception {

    String message;

    public PageOutOfBoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
