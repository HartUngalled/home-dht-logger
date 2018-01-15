package com.hartungalled.homedhtlogger.mqtt;

import com.hartungalled.homedhtlogger.service.MeasurementServiceImpl;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Component
public class PostCallback implements MqttCallback {

    @Autowired
    MeasurementServiceImpl measurementService;

    public void connectionLost(Throwable throwable) {

        System.out.println("Console test - connection lost, " + throwable.getMessage());
        //TODO reconnect function

    }

    public void messageArrived(String topic, MqttMessage message) throws MqttException {

        String[] measurementValues = new String( message.getPayload() )
                .replaceAll("[^\\n^0-9]", "")
                .split("\\n");

        if (measurementValues.length==2) {
            postJsonMeasurement(measurementValues[0], measurementValues[1]);
        } else {
            System.out.println("Something is wrong with message...");
        }

    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

        String message;

        try {
            message = new String(iMqttDeliveryToken.getMessage().getPayload());
        } catch (MqttException e) {
            message = "Exception occurred: " + e.getMessage();
        }

        System.out.println("Console test - delivery complete: " +
                "\nMessage: " + message
        );

    }

    /**
     * Temporary, very unpleasant, but working method.
     */
    private void postJsonMeasurement(String temperature, String humidity) {

        try {
            URL url = new URL("http://localhost:8080/dht/savemeasurement");
            URLConnection conn = url.openConnection();
            HttpURLConnection http = (HttpURLConnection)conn;
            http.setRequestMethod("POST");
            http.setDoOutput(true);

            byte[] out = ("{\"temperature\":" + temperature +
                    ",\"humidity\":" + humidity + "}")
                    .getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}