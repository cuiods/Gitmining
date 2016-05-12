package edu.nju.model.pojo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by Harry on 2016/5/11.
 */
public class WebUser {

    @Size(min = 1, max = 50, message = "username length must between 1 and 50!")
    @NotBlank
    private String userName;

    @Size(min = 6, message = "password must has at least 6 characters!")
    private String password;

    @Email(message = "please input the correct email address")
    private String email;

    public WebUser() {
    }

    public WebUser(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
