package br.com.jms.producer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerService {

    @JmsListener(destination = "Test-Queue")
    public void receiveMessage(String request) {
        System.out.println(">>>>>>>>>>>>>>>>>");
        System.out.println("Received on Default Listener...");
        System.out.println(request);
    }

    @JmsListener(destination = "Test-Queue",
            selector = "Operation='LISTENER1'")
    public void receiveMessage1(String request) {
        System.out.println(">>>>>>>>>>>>>>>>>");
        System.out.println("Received on Listener 1...");
        System.out.println(request);
    }

    @JmsListener(destination = "Test-Queue",
            selector = "Operation='LISTENER2'")
    public void receiveMessage2(String request) {
        System.out.println(">>>>>>>>>>>>>>>>>");
        System.out.println("Received on Listener 2...");
        System.out.println(request);
    }

}
