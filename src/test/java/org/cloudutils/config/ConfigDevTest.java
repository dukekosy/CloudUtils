package org.cloudutils.config;

import org.cloudutils.config.ConfigDev;
import org.cloudutils.config.ServiceBusConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class ConfigDevTest {

    @Before
    public void setup() {
    }

    @Test
    public void getServiceBusConfig_fetchServiceBusConfigWhenSet_returnsValidConfiguration() {
        ConfigDev.getInstance().setServiceBusConnectionString(UUID.randomUUID().toString());
        Optional<ServiceBusConfig> serviceBusConfig = ConfigDev.getInstance().getServiceBusConfig();
        assertTrue(serviceBusConfig.isPresent());
    }

}
