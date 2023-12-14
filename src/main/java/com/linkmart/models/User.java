package com.linkmart.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;//ULID

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy="users", cascade={CascadeType.ALL})
    private List<RequestModel> requestModel;

    @Column(name="username", unique = true)
    private String username; //unique

    @Column(name="password")
    private String password;

    @Column(name="user_email", unique = true)
    @Email(message = "Email should be valid")
    private String userEmail; //unique

    @Column(name="created_at", insertable=false, updatable=false)
    private Timestamp createdAt;
    @Column(name="updated_at", insertable=false, updatable=false)
    private Timestamp updatedAt;

    @OneToMany(mappedBy="user" , cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<UserAddress> userAddressList;

    @OneToMany(mappedBy="user" , cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<UserPaymentMethod> userPaymentMethodList;

    @OneToMany(mappedBy="user" , cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<Provider> providerList;

    @OneToMany(mappedBy="user" , cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<ProviderVerification> providerVerificationList;


    public User() {
        ULID ulid = new ULID();
        this.id = ulid.nextULID();
    }

    public User( String username, String password, String userEmail) {
        ULID ulid = new ULID();
        this.id = ulid.nextULID();
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(userEmail, user.userEmail) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, userEmail, createdAt, updatedAt);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
