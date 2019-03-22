package com.test.service;

public class AliPay implements PayService {
    @Override
    public String payMoney() {
        return "支付宝支付";
    }
}
