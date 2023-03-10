package io.cyberflux.reactor.mqtt.utils;

import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageIdVariableHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttSubAckMessage;
import io.netty.handler.codec.mqtt.MqttSubAckPayload;
import io.netty.handler.codec.mqtt.MqttUnsubAckMessage;

public final class MqttMessageBuilder {

    private static MqttFixedHeader FIXED_HEADER(MqttMessageType type, int remainingLength) {
        return new MqttFixedHeader(type, false, MqttQoS.AT_MOST_ONCE, false, remainingLength);
    }


    public static MqttConnAckMessage buildConnAckMessage(MqttConnectReturnCode returnCode) {
        return new MqttConnAckMessage(FIXED_HEADER(MqttMessageType.CONNACK, 0x02),
            new MqttConnAckVariableHeader(returnCode, false)
        );
    }

    public static MqttSubAckMessage buildSubAckMessage(int messageId, Iterable<Integer> reasonCodes) {
        return new MqttSubAckMessage(FIXED_HEADER(MqttMessageType.SUBACK, 0x02),
            MqttMessageIdVariableHeader.from(messageId), new MqttSubAckPayload(reasonCodes)
        );
    }

    public static MqttUnsubAckMessage buildUnsubAckMessage(int messageId) {
        return new MqttUnsubAckMessage(FIXED_HEADER(MqttMessageType.UNSUBACK, 0x02),
            MqttMessageIdVariableHeader.from(messageId)
        );
    }

    public static MqttMessage buildPingRespMessage() {
        return new MqttMessage(FIXED_HEADER(MqttMessageType.PINGRESP, 0));
    }
}
