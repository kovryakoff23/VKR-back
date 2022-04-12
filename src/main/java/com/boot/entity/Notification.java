package com.boot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Date dateCreate;
    int type;
    String message;
    public Notification(){}
    public Notification(Long id, Date dateCreate, int type, String message) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.type = type;
        this.message = message;
    }
    public Notification(Date dateCreate, int type, String message) {
        this.dateCreate = dateCreate;
        this.type = type;
        this.message = message;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
