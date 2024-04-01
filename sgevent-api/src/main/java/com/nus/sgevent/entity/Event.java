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
@Table(name = "event")
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(generator = "UUID") // 自动生成UUID
    private UUID eventId; // 使用UUID作为主键类型
    private String eventTitle;
    private String eventDesc;
    private Date eventCreateDt;
    private Date eventStartDt;
    private Date eventEndDt;
    private String eventPlace;
    private int eventCapacity;
    private String eventOwnerId;
    private String eventStatus;
}
