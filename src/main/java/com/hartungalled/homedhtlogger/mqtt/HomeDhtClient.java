package com.hartungalled.homedhtlogger.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class HomeDhtClient {

    private MqttClient client;
    private MqttConnectOptions connConfig;

    public HomeDhtClient(String userName, String password, String clientId, String brokerUrl) {

        try {

            connConfig = new MqttConnectOptions();
            connConfig.setCleanSession(true);
            connConfig.setUserName(userName);
            connConfig.setPassword(password.toCharArray());

            client = new MqttClient(brokerUrl, clientId);
            client.setCallback(new MqttCallbackImpl());

        } catch (MqttException e) {

            System.out.println( e.getMessage() );

        }

    }

    public void publish(String topicName, int qos, byte[] payload) throws MqttException {

        MqttMessage message = new MqttMessage(payload);
        message.setQos(qos);

        client.connect(connConfig);
        client.publish(topicName, message);
        client.disconnect();

    }

    public void subscribe(String topicName, int qos) throws MqttException {

        client.connect(connConfig);
        client.subscribe(topicName, qos);
//        client.disconnect();

    }



}
