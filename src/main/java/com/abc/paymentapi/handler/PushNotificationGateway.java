package com.abc.paymentapi.handler;

// @MessagingGateway assume Spring integration gateway interface  
public interface PushNotificationGateway {

    // @Gateway(requestChannel = "push.notification.request") assume Spring integration channel configured
    void pushMessageEvent(String status, String device, double amount);

}
