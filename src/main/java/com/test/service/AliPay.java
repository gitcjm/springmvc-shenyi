package com.test.service;

public class AliPay implements PayMethod {
    @Override
    public String payMoney() {
        return "支付宝支付";
    }
}
