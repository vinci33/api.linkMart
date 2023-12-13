package com.linkmart.forms;

import com.linkmart.models.User;

import java.util.Objects;

public class LoginForm {

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser(String Username, String Password, String Email) {
        return new User(Username, Password, Email);
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginForm loginForm = (LoginForm) o;
        return Objects.equals(email, loginForm.email) && Objects.equals(password, loginForm.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}



