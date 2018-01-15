package com.hartungalled.homedhtlogger.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.time.LocalDateTime;

public class ConsoleCallback implements MqttCallback {

    public void connectionLost(Throwable throwable) {

        System.out.println("Console test - connection lost");

    }

    public void messageArrived(String topic, MqttMessage message) throws MqttException {

        System.out.println("Console test - message arrived: " +
                "\nTime: " + LocalDateTime.now() +
                "\nTopic: " + topic +
                "\nMessage: " + new String( message.getPayload() )
        );

    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

        String message;

        try {
            message = new String( iMqttDeliveryToken.getMessage().getPayload() );
        } catch (MqttException e) {
            message = "Exception occurred: " + e.getMessage();
        }

        System.out.println("Console test - delivery complete: " +
                "\nMessage: " + message
        );

    }
}
