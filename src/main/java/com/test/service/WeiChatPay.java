package com.test.service;

public class WeiChatPay implements PayService {
    @Override
    public String payMoney() {
        return "微信支付";
    }
}
