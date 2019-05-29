package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.libelektra.*;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        Key key = Key.create("user");
        try (KDB kdb = KDB.open(key)) {
            KeySet set = KeySet.create();
            Key namespace = Key.create("user/chatapp");
            kdb.get(set, namespace);

            String applicationDestPrefix = set.lookup("user/chatapp/applicationDestPrefix").getString();
            String StompBrokerRelay =   set.lookup("user/chatapp/broker/name").getString();
            String relayHost = set.lookup("user/chatapp/broker/host").getString();
            int relayPort = set.lookup("user/chatapp/broker/port").getInteger();
            String login = set.lookup("user/chatapp/broker/login").getString();
            String passcode = set.lookup("user/chatapp/broker/pw").getString();

            registry.setApplicationDestinationPrefixes(applicationDestPrefix);
            registry.enableStompBrokerRelay(StompBrokerRelay)
                    .setRelayHost(relayHost)
                    .setRelayPort(relayPort)
                    .setClientLogin(login)
                    .setClientPasscode(passcode);
        } catch (KDB.KDBException e) {
            e.printStackTrace();
        }
    }
}
