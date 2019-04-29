package org.cloudutils.queues.publisher;


import com.microsoft.azure.servicebus.primitives.ServiceBusException;

public interface Publisher {

    void publish(String topic, MessageBuilder message) throws ServiceBusException, InterruptedException;

    void publishDelayed(String topic, MessageBuilder message, long delay);

}
