package org.cloudutils.queues.publisher;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.cloudutils.config.ConfigDev;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.concurrent.CompletableFuture;

public final class ServiceBusSend implements Publisher {

    private ServiceBusSend() {
    }

    private static final ServiceBusSend instance = new ServiceBusSend();

    public static ServiceBusSend getInstance() {
        return instance;
    }

    public void publish(final String topic, final MessageBuilder message) throws ServiceBusException, InterruptedException {

        TopicClient sendClient = new TopicClient(new ConnectionStringBuilder(
                ConfigDev.getInstance()
                         .getServiceBusConfig()
                         .orElseThrow(
                                 () -> new MissingResourceException("Connection string is missing for message : " + message.message(),
                                                                    "Config",
                                                                    "connectionString")).connectionString(),
                topic));

        sendMessagesAsync(sendClient, message).thenRunAsync(() -> sendClient.closeAsync());
    }

    public void publishDelayed(String topic, MessageBuilder message, long delay) {

    }

    private CompletableFuture<Void> sendMessagesAsync(TopicClient sendClient, MessageBuilder messageBuilder) {

        List<CompletableFuture> tasks = new ArrayList<>();
        Message message = messageBuilder.toMessage();
        System.out.printf("Message sending: Id = %s\n", message.getMessageId());
        tasks.add(
                sendClient.sendAsync(message).thenRunAsync(() -> {
                    System.out.printf("\tMessage acknowledged: Id = %s\n", message.getMessageId());
                }));
        return CompletableFuture.allOf(tasks.toArray(new CompletableFuture<?>[tasks.size()]));
    }

}
