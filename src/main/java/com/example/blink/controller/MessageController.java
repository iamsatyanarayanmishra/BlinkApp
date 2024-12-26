package com.example.blink.controller;

import com.example.blink.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Send a broadcast message to all subscribers.
     * Mapped to "/app/chat.send".
     */
    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessage chatMessage) {
        try {
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        } catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
            throw new RuntimeException("Failed to send broadcast message");
        }
    }

    /**
     * Send a private message to a specific user.
     * Mapped to "/app/chat.sendToUser".
     */
    @MessageMapping("/chat.sendToUser")
    public void sendToUser(ChatMessage chatMessage) {
        try {
            if (chatMessage.getRecipient() == null || chatMessage.getRecipient().isEmpty()) {
                throw new IllegalArgumentException("Recipient is required for private messages.");
            }
            messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipient(), // Recipient's username
                "user/queue/messages", // User-specific queue
                chatMessage
            );
        } catch (Exception e) {
            System.err.println("Error sending private message: " + e.getMessage());
            throw new RuntimeException("Failed to send private message");
        }
    }
}
