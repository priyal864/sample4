package com.abc.paymentapi.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abc.paymentapi.dtos.Payment;

public abstract class AbstractPaymentHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract String getPaymentProviderName();

    public abstract Payment makePayment(String email, String mobile, double amount, String nonce, String deviceData);

    public void loginfo(String email, String mobile, double amount, String paymentReference) {
        logger.info(
            "Payment Success Email: {0} Mobile: {1} Amount: {2} Payment Reference: {3} Payment Component: {4}",
            email,
            mobile,
            amount,
            paymentReference,
            this.getPaymentProviderName()
        );
    }

}
