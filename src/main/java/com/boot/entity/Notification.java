package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Date dateCreate;
    int type;
    String message;
    public Notification(Date dateCreate, int type, String message) {
        this.dateCreate = dateCreate;
        this.type = type;
        this.message = message;
    }

}
