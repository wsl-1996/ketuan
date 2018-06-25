package com.skqtec.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "MESSAGE", schema = "ketuanDB", catalog = "")
public class MessageEntity {
    private int id;
    private int messageType;
    private int contentType;
    private Timestamp time;
    private int fromUserId;
    private int toUserId;
    private int state;
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message_type")
    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    @Basic
    @Column(name = "content_type")
    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "from_USER_id")
    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    @Basic
    @Column(name = "to_USER_id")
    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = new String (content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (id != that.id) return false;
        if (messageType != that.messageType) return false;
        if (contentType != that.contentType) return false;
        if (fromUserId != that.fromUserId) return false;
        if (toUserId != that.toUserId) return false;
        if (state != that.state) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (!content.equals(that.content)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + messageType;
        result = 31 * result + contentType;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + fromUserId;
        result = 31 * result + toUserId;
        result = 31 * result + state;
        result = 31 * result + content.hashCode();
        return result;
    }
}
