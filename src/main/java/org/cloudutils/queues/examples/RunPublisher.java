package org.cloudutils.queues.examples;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.cloudutils.queues.ServiceBus.MessageBuilder;
import org.cloudutils.queues.config.ConfigDev;
import org.cloudutils.queues.publisher.ServiceBusSend;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

public class RunPublisher {


    public static void main(String[] args) {


        try {
            ConfigDev.getInstance()
                     .setServiceBusConnectionString(Optional.of(
                             "Endpoint=sb://<NameOfServiceBusNamespace>.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<AccessKey>"));
            ServiceBusSend.getInstance()
                          .publish("", MessageBuilder.builder().message("")
                                                     .contentType("application/json")
                                                     .messageId(UUID.randomUUID().toString())
                                                     .timeToLive(Duration.ofMinutes(2))
                                                     .build());
        } catch (ServiceBusException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
