package org.cloudutils.queues.publisher;

import com.microsoft.azure.servicebus.primitives.MessagingEntityNotFoundException;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.cloudutils.config.ConfigDev;
import org.cloudutils.config.ServiceBusConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import java.util.MissingResourceException;
import java.util.Optional;
import java.util.UUID;

public class PublisherTest {

    ConfigDev configDev;
    String connectionString;
    String queueName;


    @Before
    public void setup() {
        connectionString = "Endpoint=sb://cloudutils.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=QOVVfAJXtfN0NVE2IW9N1Wy13sbXFpXiHdxjM+6SuM0=";
        queueName = "location";
        configDev = PowerMockito.mock(ConfigDev.class);

        Whitebox.setInternalState(ConfigDev.class, "instance", configDev);
    }

    @Ignore("Integration Test")
    @Test(expected = MessagingEntityNotFoundException.class)
    public void publish_noJsonToPublish_throwsMessagingEntityNotFound() throws ServiceBusException, InterruptedException {
        PowerMockito.when(configDev.getServiceBusConfig()).thenReturn(Optional.of(ServiceBusConfig.testBuilder()
                                                                                                  .connectionString(connectionString)
                                                                                                  .build()));
        ServiceBusSend.getInstance().publish(UUID.randomUUID().toString(), MessageBuilder.testBuilder().message("").build());
    }

    @Test(expected = MissingResourceException.class)
    public void publish_withMissingConnectionString_throwsException() throws ServiceBusException, InterruptedException {

        PowerMockito.when(configDev.getServiceBusConfig())
                    .thenReturn(Optional.empty());

        ServiceBusSend.getInstance().publish(UUID.randomUUID().toString(), MessageBuilder.testBuilder().build());

    }

    @Ignore("Integration Test")
    @Test
    public void publish_successfully() throws ServiceBusException, InterruptedException {

        PowerMockito.when(configDev.getServiceBusConfig())
                    .thenReturn(Optional.of(ServiceBusConfig.testBuilder()
                                                            .connectionString(connectionString)
                                                            .build()));

        ServiceBusSend.getInstance().publish(queueName, MessageBuilder.testBuilder().build());

    }
}
