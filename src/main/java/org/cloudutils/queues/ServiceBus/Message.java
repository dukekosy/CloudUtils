package org.cloudutils.queues.ServiceBus;

import com.google.auto.value.AutoValue;

import java.time.Duration;

@AutoValue
public abstract class Message {

    public abstract String message();

    public abstract String contentType();

    public abstract String label();

    public abstract String messageId();

    public abstract Duration timeToLive();

}
