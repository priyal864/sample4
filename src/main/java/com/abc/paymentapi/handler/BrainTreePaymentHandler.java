package com.abc.paymentapi.handler;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.abc.paymentapi.dtos.Payment;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

@Service
public class BrainTreePaymentHandler extends AbstractPaymentHandler {

    @Autowired
    private SendgridEmailGateway sendgridGateway;

    @Autowired
    private TwillioSMSGateway twillioSMSGateway;

    @Autowired
    private PushNotificationGateway pushNotificationGateway;

    @Value("${merchant.id:6DB16C5B-EE84-4548-90F9-0BC04120B3AE}")
    private String merchantId;

    @Value("${public.key:BEFE8AFD9CE04062B718B29960D68800}")
    private String publicKey;

    @Value("${private.key:49885FE3E2FC40CCB00AB28C6E71E7C2/=}")
    private String privateKey;


    public Payment makePayment(String email, String mobile, double amount, String nonce, String deviceData) {
        TransactionRequest request = new TransactionRequest().amount(new BigDecimal(amount)).paymentMethodNonce(nonce)
            .deviceData(deviceData).options().submitForSettlement(true).done();
        BraintreeGateway gateway = new BraintreeGateway(Environment.DEVELOPMENT, merchantId, publicKey, privateKey);
        Result<Transaction> result = gateway.transaction().sale(request);
        loginfo(email, mobile, amount, result.getTransaction().getId());
        sendgridGateway.sendEmailEvent("Success", email, amount);
        twillioSMSGateway.sendSMSEvent("Success", mobile, amount);
        pushNotificationGateway.pushMessageEvent("Success", deviceData, amount);

        Payment payment = new Payment();

        payment.setAmount(amount);
        payment.setPaymentReference(result.getTransaction().getId());
        return payment;

    }

    @Override
    public String getPaymentProviderName() {
        return "BrainTree";
    }

}
