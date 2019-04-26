package org.cloudutils.rabbitmq.azure;

import org.cloudutils.rabbitmq.Rabbit;

public abstract class AzureRabbit implements Rabbit {
    @Override
    public void publish() {
        sendMessage();
    }

    @Override
    public void subscribe() {
        receiveMessage();
    }

    //AWS Specific abstracted out of implementation
    public abstract void sendMessage();

    public abstract void receiveMessage();

    public abstract void createClient();

    public abstract void createMessage();
}
