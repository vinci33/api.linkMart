package com.linkmart.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "image")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "request_id")
    private String request_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="request_id", updatable = false, insertable = false)
    private RequestModel requestModel;

    @Column(name = "image_path")
    private String image_path;
    @Column(name = "created_at", insertable = false, updatable = false)
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "id='" + id + '\'' +
                ", request_id='" + request_id + '\'' +
                ", requestModel=" + requestModel +
                ", image_path='" + image_path + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
