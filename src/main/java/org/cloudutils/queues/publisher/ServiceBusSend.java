package org.cloudutils.queues.publisher;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.cloudutils.queues.ServiceBus.MessageBuilder;
import org.cloudutils.queues.config.ConfigDev;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
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

    private final Gson GSON = new Gson();

    public void publish(final String queueName, final MessageBuilder message) throws ServiceBusException, InterruptedException {

        TopicClient sendClient = new TopicClient(new ConnectionStringBuilder(ConfigDev.getInstance()
                                                                                      .getServiceBusConfig()
                                                                                      .connectionString()
                                                                                      .orElseThrow(
                                                                                              () -> new MissingResourceException("Connection string is missing for message : " + message.message(),
                                                                                                                                 "Config",
                                                                                                                                 "connectionString")),
                                                                             queueName));
        sendMessagesAsync(sendClient, message).thenRunAsync(() -> sendClient.closeAsync());
    }

    public void publishDelayed(String queueName, MessageBuilder message, long delay) {

    }

    private CompletableFuture<Void> sendMessagesAsync(TopicClient sendClient, MessageBuilder messageBuilder) {
        List<HashMap<String, String>> data =
                GSON.fromJson(messageBuilder.message(),
                              new TypeToken<List<HashMap<String, String>>>() {
                              }.getType());

        List<CompletableFuture> tasks = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            Message message = messageBuilder.toMessage();
            System.out.printf("Message sending: Id = %s\n", message.getMessageId());
            tasks.add(
                    sendClient.sendAsync(message).thenRunAsync(() -> {
                        System.out.printf("\tMessage acknowledged: Id = %s\n", message.getMessageId());
                    }));
        }
        return CompletableFuture.allOf(tasks.toArray(new CompletableFuture<?>[tasks.size()]));
    }

}
