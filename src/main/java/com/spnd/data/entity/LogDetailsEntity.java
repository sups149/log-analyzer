package com.spnd.data.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "EVENT_LOG_DETAILS")
public class LogDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_LOG_ID")
    private String id;
    @Column(name = "EVENT_ID")
    private String eventId;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "HOST")
    private String host;
    @Column(name = "DURATION")
    private Long duration;
    @Column(name = "ALERT")
    private boolean alert;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }


    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
