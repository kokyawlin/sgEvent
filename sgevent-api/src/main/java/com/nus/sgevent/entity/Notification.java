package com.nus.sgevent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue
    private UUID notificationId; 
    private String notiMessage;
    private UUID eventId; 
    private UUID userId;  
    private Date notificationDt;

 
}
