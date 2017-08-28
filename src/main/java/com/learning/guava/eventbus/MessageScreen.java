package com.learning.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class MessageScreen {
    @Subscribe
    public void printMessage(String message) {
        System.out.println("message::"+message);
    }

    @Subscribe
    public void printMessage2(String message) {
        System.out.println("message2::"+message);
    }
    @Subscribe
    public void printMessage2(Object message) {
        System.out.println("message3::"+message);
    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new MessageScreen());
        eventBus.post(1);
    }
}