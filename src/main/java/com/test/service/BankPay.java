package com.test.service;

public class BankPay implements PayService {
    @Override
    public String payMoney() {
        return "银行卡支付";
    }
}
