package org.cloudutils.queues.config;

import java.util.Optional;

public class ConfigStaging implements Config {

    //singleton used to maintain one configuration instance application wide
    private ConfigStaging() {
    }

    private static final ConfigStaging instance = new ConfigStaging();

    public static ConfigStaging getInstance() {
        return instance;
    }

    Optional<ServiceBusConfig> serviceBusConfig;

    @Override
    public Optional<ServiceBusConfig> getServiceBusConfig() {
        return serviceBusConfig;
    }

    //have to use a setter here to maintain singleton status and allow editing configuration settings
    public void setServiceBusConnectionString(String serviceBusConnectionString) {
        this.serviceBusConfig = Optional.of(ServiceBusConfig.builder().connectionString(serviceBusConnectionString).build());
    }

}
