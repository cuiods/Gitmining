package edu.nju.model.exception;

/**
 * Created by Harry on 2016/5/11.
 * This exception contains the error message when web user login gitmine website.<br/>
 *
 */
public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
