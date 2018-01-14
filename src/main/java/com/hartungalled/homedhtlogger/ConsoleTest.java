package com.hartungalled.homedhtlogger;

import org.eclipse.paho.client.mqttv3.MqttException;

public class ConsoleTest {

    public static void main(String[] args) {

        String subTopic		= "Modules/Dht/#";
        String pubTopic 	= "Modules/Dht/Temp";
        String message 		= "Hello World";
        int qos 			= 0;

        int port 			= 12689;
        String broker 		= "m20.cloudmqtt.com";
        String protocol     = "tcp";
        String url          = protocol + "://" + broker + ":" + port;

        String clientId 	= "1057";
        String userName     = "qyrrmsap";
        String password     = "kOn4LD68EMAz";

        HomeDhtClient client = new HomeDhtClient(userName, password, clientId, url);

        try {

            client.publish(pubTopic, qos, message.getBytes());
            client.subscribe(subTopic, qos);

        } catch (MqttException e) {

            System.out.println( e.getMessage() );

        }
    }

}
