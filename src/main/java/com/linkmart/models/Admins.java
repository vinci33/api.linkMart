package com.linkmart.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "admins")
public class Admins {
    @Id
    private String id;//ULID
    @Column(name="username", unique = true)
    private String username; //unique
    @Column(name="password")
    private String password;

    @Column(name="created_at", insertable=false, updatable=false)
    private Timestamp createdAt;

    @OneToMany(mappedBy="admins")
    private List<ReportCase> reportCasesList;

    public Admins() {
        ULID ulid = new ULID();
        this.id = ulid.nextULID();
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


}
