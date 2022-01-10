package com.abc.paymentapi.handler;

// @MessagingGateway assume Spring integration gateway interface  
public interface SendgridEmailGateway {

    // @Gateway(requestChannel = "sendgrid.email.request") assume Spring integration channel configured
    void sendEmailEvent(String status, String email, double amount);

}
