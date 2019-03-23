package com.test.service;

public class WeiChatPay implements PayMethod {
    @Override
    public String payMoney() {
        return "微信支付";
    }
}
