package com.spnd.business.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogDetails {
    private String id;
    private String state;
    private String type;
    private String host;
    private Long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("state", state)
                .append("timestamp", timestamp)
                .toString();
    }
}
