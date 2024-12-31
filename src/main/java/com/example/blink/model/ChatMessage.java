package com.example.blink.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ChatMessage {

    @Id
    // Changed from Long to String to accommodate non-numeric values like UUIDs
    private String id;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    public enum MessageType {
        TEXT, IMAGE, FILE
    }

    // Default constructor
    public ChatMessage() {
    }

    // Factory method for deserialization
    @JsonCreator
    public ChatMessage(@JsonProperty("id") String id, @JsonProperty("type") MessageType type, @JsonProperty("content") String content, @JsonProperty("sender") User sender, @JsonProperty("recipient") User recipient) {
        this.id = id != null ? id : "";
        this.type = type != null ? type : MessageType.TEXT;
        this.content = content != null ? content : "";
        this.sender = sender != null ? sender : new User();
        this.recipient = recipient != null ? recipient : new User();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id != null ? id : "";
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type != null ? type : MessageType.TEXT;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content != null ? content : "";
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender != null ? sender : new User();
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient != null ? recipient : new User();
    }
}
