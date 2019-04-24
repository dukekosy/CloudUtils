package org.cloudutils.queues.config;

import java.util.Optional;

public interface Config {
    Optional<ServiceBusConfig> getServiceBusConfig();
    //define Optional memcache config
    //define Optional redis config
    //define Optional jdbc config
    //define Optional NoSQL config
}
