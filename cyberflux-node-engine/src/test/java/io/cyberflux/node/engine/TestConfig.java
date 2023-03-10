package io.cyberflux.node.engine;

import io.cyberflux.meta.reactor.Reactor;
import io.cyberflux.node.engine.annotation.CyberBean;
import io.cyberflux.node.engine.annotation.CyberReactor;
import io.cyberflux.reactor.mqtt.CyberFluxMqttReactor;

@CyberReactor
public class TestConfig {
    @CyberBean
    public Reactor mqttserver() {
        return CyberFluxMqttReactor.builder().build();
    }
}
