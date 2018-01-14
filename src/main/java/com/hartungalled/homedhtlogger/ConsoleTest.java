package com.hartungalled.homedhtlogger;

import com.hartungalled.homedhtlogger.model.Measurement;
import com.hartungalled.homedhtlogger.mqtt.HomeDhtClient;
import com.hartungalled.homedhtlogger.service.MeasurementServiceImpl;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleTest implements CommandLineRunner {

    @Autowired
    MeasurementServiceImpl measurementService;

    public static void main(String[] args) {

//        testMqttConnection();
        SpringApplication.run(ConsoleTest.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {

        Measurement measurement = new Measurement(24, 33);

        measurementService.save(measurement);
    }

    public static void testMqttConnection() {

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
