package com.nus.sgevent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "eventId") // Use eventId for equals() and hashCode()
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    private String eventTitle;
    private String eventDesc;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventStartDt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventEndDt;
    @ManyToOne
    @JoinColumn(name = "eventOwnerId")
    private User eventOwner;

    // public Event() {}

    // public Event(Long eventId, String eventTitle, String eventDesc, Date eventStartDt, Date eventEndDt, User eventOwner) {
    //     this.eventId = eventId;
    //     this.eventTitle = eventTitle;
    //     this.eventDesc = eventDesc;
    //     this.eventStartDt = eventStartDt;
    //     this.eventEndDt = eventEndDt;
    //     this.eventOwner = eventOwner;
    // }

    // // Getters and Setters
    // public Long getEventId() { return eventId; }
    // public void setEventId(Long eventId) { this.eventId = eventId; }
    // public String getEventTitle() { return eventTitle; }
    // public void setEventTitle(String eventTitle) { this.eventTitle = eventTitle; }
    // public String getEventDesc() { return eventDesc; }
    // public void setEventDesc(String eventDesc) { this.eventDesc = eventDesc; }
    // public Date getEventStartDt() { return eventStartDt; }
    // public void setEventStartDt(Date eventStartDt) { this.eventStartDt = eventStartDt; }
    // public Date getEventEndDt() { return eventEndDt; }
    // public void setEventEndDt(Date eventEndDt) { this.eventEndDt = eventEndDt; }
    // public User getEventOwner() { return eventOwner; }
    // public void setEventOwner(User eventOwner) { this.eventOwner = eventOwner; }

}
