package com.abc.paymentapi.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.paymentapi.dtos.Payment;

@Service
public class DiscountHandler {

    @Autowired
    private BrainTreePaymentHandler brainTreePaymentHandler;

    public Payment applyDiscount(
        double amount,
        int type,
        int years,
        String email,
        String mobile,
        boolean subscribe,
        String nonce,
        String deviceData
    ) {
        double discountedAmount = 0;
        double disc = (years > 5) ? (double)5 / 100 : (double)years / 100;
        if (type == 1)
        {
            discountedAmount = amount;
        }
        else if (type == 2)
        {
            discountedAmount = (amount - (0.1 * amount)) - disc * (amount - (0.1 * amount));
        }
        else if (type == 3)
        {
            discountedAmount = (0.7 * amount) - disc * (0.7 * amount);
        }
        else if (type == 4)
        {
            discountedAmount = (amount - (0.5 * amount)) - disc * (amount - (0.5 * amount));
        }

        return brainTreePaymentHandler.makePayment(email, mobile, discountedAmount, nonce, deviceData);
    }

}
