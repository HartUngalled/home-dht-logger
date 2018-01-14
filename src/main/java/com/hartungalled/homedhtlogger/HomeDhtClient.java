package com.hartungalled.homedhtlogger;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

public class HomeDhtClient {

    private MqttClient client;
    private MqttConnectOptions connConfig;

    public HomeDhtClient(String userName, String password, String clientId, String brokerUrl) {

        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);

        try {

            connConfig = new MqttConnectOptions();
            connConfig.setCleanSession(true);
            connConfig.setUserName(userName);
            connConfig.setPassword(password.toCharArray());

            client = new MqttClient(brokerUrl, clientId, dataStore);
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
