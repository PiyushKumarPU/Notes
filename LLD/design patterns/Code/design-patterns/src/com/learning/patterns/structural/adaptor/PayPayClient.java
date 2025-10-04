package com.learning.patterns.structural.adaptor;

/*
 * Client: PayPay
 * - Uses PaymentGateway interface without knowing underlying bank details.
 */
public class PayPayClient {

    private PaymentGateway gateway;

    public PayPayClient(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void makePayment(double amount) {
        String result = gateway.pay(amount);
        System.out.println("PayPay: " + result);
    }

    static void main() {
        // PayPay using Mizuho Bank
        PaymentGateway mizuhoAdapter = new PayPayPaymentAdapter(new MizuhoBankAPI());
        PayPayClient client1 = new PayPayClient(mizuhoAdapter);
        client1.makePayment(10000);

        // PayPay using MUFG Bank
        PaymentGateway mufgAdapter = new PayPayPaymentAdapter(new MUFGBankAPI());
        PayPayClient client2 = new PayPayClient(mufgAdapter);
        client2.makePayment(20000);

        // PayPay using SMBC Bank
        PaymentGateway smbcAdapter = new PayPayPaymentAdapter(new SMBCBankAPI());
        PayPayClient client3 = new PayPayClient(smbcAdapter);
        client3.makePayment(30000);
    }
}

