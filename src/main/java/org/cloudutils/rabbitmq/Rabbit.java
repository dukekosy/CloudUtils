package org.cloudutils.rabbitmq;

public interface Rabbit {
    //strategy pattern
    void publish();

    void subscribe();

}
