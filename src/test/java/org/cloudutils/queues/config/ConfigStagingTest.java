package org.cloudutils.queues.config;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class ConfigStagingTest {

    @Before
    public void setup() {
    }

    @Test
    public void getServiceBusConfig_fetchServiceBusConfigWhenSet_returnsValidConfiguration() {
        ConfigStaging.getInstance().setServiceBusConnectionString(UUID.randomUUID().toString());
        Optional<ServiceBusConfig> serviceBusConfig = ConfigStaging.getInstance().getServiceBusConfig();
        assertTrue(serviceBusConfig.isPresent());
    }
}
