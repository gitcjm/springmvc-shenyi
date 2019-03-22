package com.test.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 包括: 余额支付、银行卡支付、支付宝支付、微信支付等
 * */
public class UserPay {

    private HttpServletResponse response;
    private PayService payMethod;

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setPayMethod(PayService payMethod) {
        this.payMethod = payMethod;
    }

    public PayService getPayMethod() {
        return payMethod;
    }

    // 根据不同的payMethod实例，进行支付
    public void pay() throws IOException {
        response.getWriter().write(payMethod.payMoney());
    }

}
