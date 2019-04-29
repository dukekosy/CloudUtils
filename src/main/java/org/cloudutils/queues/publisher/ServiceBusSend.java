package org.cloudutils.queues.publisher;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.cloudutils.config.ConfigDev;

import java.util.HashMap;
import java.util.MissingResourceException;

public final class ServiceBusSend implements Publisher {

    private ServiceBusSend() {
    }

    private static final ServiceBusSend instance = new ServiceBusSend();
    private static HashMap<String, TopicClient> topicClients = new HashMap<>();

    public static ServiceBusSend getInstance() {
        return instance;
    }

    @Override
    public void publish(final String topic, final MessageBuilder message) throws ServiceBusException, InterruptedException {
        TopicClient topicClient = createTopic(topic);
        sendMessagesAsync(topicClient, message);
    }

    private TopicClient createTopic(final String topic) throws ServiceBusException, InterruptedException {
        if (!topicClients.keySet().stream().filter(key -> topic.equals(key)).findFirst().isPresent()) {
            TopicClient topicClient = new TopicClient(new ConnectionStringBuilder(
                    ConfigDev.getInstance()
                             .getServiceBusConfig()
                             .orElseThrow(
                                     () -> new MissingResourceException("Connection string is missing for topic : " + topic,
                                                                        "Config",
                                                                        "connectionString")).connectionString(),
                    topic));
            topicClients.put(topic, topicClient);
            return topicClient;
        } else {
            return topicClients.get(topic);
        }
    }

    @Override
    public void publishDelayed(final String topic, final MessageBuilder message, long delay) {

    }

    private void sendMessagesAsync(TopicClient sendClient, MessageBuilder messageBuilder) {

        Message message = messageBuilder.toMessage();
        System.out.printf("Message sending: Id = %s\n", message.getMessageId());
        sendClient.sendAsync(message);
    }

    public void closeTopic(final String topic) {
        topicClients.get(topic).closeAsync();
    }
}
