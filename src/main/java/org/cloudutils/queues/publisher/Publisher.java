package org.cloudutils.queues.publisher;


import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.cloudutils.queues.ServiceBus.MessageBuilder;

public interface Publisher {

    void publish(String queueName, MessageBuilder message) throws ServiceBusException, InterruptedException;

    void publishDelayed(String queueName, MessageBuilder message, long delay);

}
