package dev.fly_yeseul.qna.entities;

import java.util.Date;

public class SessionEntity {
    private Date createdAt;
    private Date updatedAt;
    private Date expiresAt;
    private boolean isExpired;
    private String userEmail;
    private String key;
    private String ua;

    public SessionEntity() {
    }

    public SessionEntity(Date createdAt, Date updatedAt, Date expiresAt, boolean isExpired, String userEmail, String key, String ua) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
        this.isExpired = isExpired;
        this.userEmail = userEmail;
        this.key = key;
        this.ua = ua;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public SessionEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public SessionEntity setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public SessionEntity setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public SessionEntity setExpired(boolean expired) {
        isExpired = expired;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public SessionEntity setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getKey() {
        return key;
    }

    public SessionEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getUa() {
        return ua;
    }

    public SessionEntity setUa(String ua) {
        this.ua = ua;
        return this;
    }
}
