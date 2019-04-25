package org.cloudutils.config;

import java.util.Optional;

public interface Config {
    Optional<ServiceBusConfig> getServiceBusConfig();

    Optional<RabbitMQConfig> getRabbitMQConfig();
    //define Optional memcache config
    //define Optional redis config
    //define Optional jdbc config
    //define Optional NoSQL config
}
