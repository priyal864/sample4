package com.abc.paymentapi.handler;

// @MessagingGateway assume Spring integration gateway interface  
public interface TwillioSMSGateway {

    // @Gateway(requestChannel = "twillio.sms.request") assume Spring integration channel configured
    void sendSMSEvent(String status, String mobile, double amount);

}
