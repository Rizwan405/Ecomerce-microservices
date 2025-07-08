package com.rizwan.ecomerce.email;

import lombok.Getter;

public enum EmailTemplates {
    ORDER_CONFIRMATION("payment-confirmation.html","payment successfully processed"),
    PAYMENT_CONFIRMATION("order-confirmation.html","order confirmation");

    @Getter
    private String templateName;
    @Getter
    private String subject;

    EmailTemplates(String templateName, String subject){
        this.templateName = templateName;
        this.subject = subject;
    }
}
