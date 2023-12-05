package com.linkmart.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "admins")
public class Admins {
    @Id
    private String id;
    @Column(name="username", unique = true)
    private String username; //unique
    @Column(name="password")
    private String password;

    @Column(name="created_at", insertable=false, updatable=false)
    private Timestamp createdAt;


    public Admins() {
        ULID ulid = new ULID();
        this.id = ulid.nextULID();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admins admins = (Admins) o;
        return Objects.equals(id, admins.id) && Objects.equals(username, admins.username) && Objects.equals(password, admins.password) && Objects.equals(createdAt, admins.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, createdAt);
    }

    public void setId(String id) {
        this.id = id;
    }
}
