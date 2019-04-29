package org.cloudutils.queues.publisher;


import com.microsoft.azure.servicebus.primitives.ServiceBusException;

public interface Publisher {

    void publish(final String topic, final MessageBuilder message) throws ServiceBusException, InterruptedException;

    void publishDelayed(final String topic, final MessageBuilder message, long delay);

    void closeTopic(final String topic);

}
