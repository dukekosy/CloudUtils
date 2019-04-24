package org.cloudutils.queues.publisher;

import com.google.auto.value.AutoValue;
import com.microsoft.azure.servicebus.Message;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;

@AutoValue
public abstract class MessageBuilder {

    public abstract String message();

    public abstract String contentType();

    public abstract Optional<String> label();

    public abstract String messageId();

    public abstract Duration timeToLive();

    public static MessageBuilder create(String message, String contentType, Optional<String> label, String messageId, Duration timeToLive) {
        return builder()
                .message(message)
                .contentType(contentType)
                .label(label)
                .messageId(messageId)
                .timeToLive(timeToLive)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_MessageBuilder.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder message(String message);

        public abstract Builder contentType(String contentType);

        public abstract Builder label(Optional<String> label);

        public abstract Builder messageId(String messageId);

        public abstract Builder timeToLive(Duration timeToLive);

        public abstract MessageBuilder build();
    }

    public Message toMessage() {
        Message message = new Message(message().getBytes(StandardCharsets.UTF_8));
        message.setContentType(this.contentType());
        message.setLabel(this.label().orElse(""));
        message.setMessageId(this.messageId());
        message.setTimeToLive(this.timeToLive());
        return message;
    }

    @Override
    public String toString() {
        return this.message();
    }
}
