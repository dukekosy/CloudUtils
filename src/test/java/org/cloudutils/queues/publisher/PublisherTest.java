package org.cloudutils.queues.publisher;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.cloudutils.queues.config.ConfigDev;
import org.cloudutils.queues.config.ServiceBusConfig;
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

    @Before
    public void setup() {
        configDev = PowerMockito.mock(ConfigDev.class);

        Whitebox.setInternalState(ConfigDev.class, "instance", configDev);
    }

    @Test(expected = NullPointerException.class)
    public void publish_noJsonToPublish_throwsNullPointerException() throws ServiceBusException, InterruptedException {
        PowerMockito.when(configDev.getServiceBusConfig()).thenReturn(Optional.of(ServiceBusConfig.testBuilder()
                                                                                                  .connectionString(
                                                                                                          "Endpoint=sb://abc.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<AccessKey>")
                                                                                                  .build()));
        ServiceBusSend.getInstance().publish(UUID.randomUUID().toString(), MessageBuilder.testBuilder().message("").build());
    }

    @Test(expected = MissingResourceException.class)
    public void publish_withMissingConnectionString_throwsException() throws ServiceBusException, InterruptedException {

        PowerMockito.when(configDev.getServiceBusConfig())
                    .thenReturn(Optional.empty());

        ServiceBusSend.getInstance().publish(UUID.randomUUID().toString(), MessageBuilder.testBuilder().build());

    }

    @Test(expected = MissingResourceException.class)
    public void publish_successfully() throws ServiceBusException, InterruptedException {

        PowerMockito.when(configDev.getServiceBusConfig())
                    .thenReturn(Optional.of(ServiceBusConfig.testBuilder()
                                                            .connectionString(
                                                                    "Endpoint=sb://abc.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<AccessKey>")
                                                            .build()));

        ServiceBusSend.getInstance().publish(UUID.randomUUID().toString(), MessageBuilder.testBuilder().build());

    }
}
