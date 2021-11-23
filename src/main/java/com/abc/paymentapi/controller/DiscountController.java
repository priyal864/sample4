package com.abc.paymentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.paymentapi.dtos.Discount;
import com.abc.paymentapi.dtos.Payment;
import com.abc.paymentapi.handler.DiscountHandler;

@Controller
public class DiscountController {

    @Autowired
    private DiscountHandler discountHandler;

    @RequestMapping(value = "/applydiscount", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody Payment discount(@RequestBody Discount discount) {
        Payment payment = discountHandler.applyDiscount(
            discount.getAmount(),
            discount.getType(),
            discount.getYears(),
            discount.getEmail(),
            discount.getMobile(),
            discount.isSubscribe(),
            discount.getNonce(),
            discount.getDeviceData()
        );

        return payment;

    }

}
