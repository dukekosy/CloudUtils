package org.cloudutils.queues.examples;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.cloudutils.queues.config.Config;
import org.cloudutils.queues.config.ConfigDev;
import org.cloudutils.queues.publisher.ServiceBusSend;

import java.util.Optional;

public class RunPublisher {


    public static void main(String[] args) {


        try {
            ConfigDev.getInstance().setServiceBusConnectionString(Optional.of("Endpoint=sb://<NameOfServiceBusNamespace>.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<AccessKey>"));
            ServiceBusSend.getInstance().publish("", "");
        } catch (ServiceBusException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
