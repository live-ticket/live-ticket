package com.ll.ticket.global.app;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

//@Configuration
public class AppConfig {
    @Getter
    private static String recaptchaSiteKey;
    @Value("${google.recaptcha.key.site}")
    public void setRecaptchaSiteKey(String recaptchaSiteKey) {
        this.recaptchaSiteKey = recaptchaSiteKey;
    }

    @Getter
    private static String recaptchaSecretKey;
    @Value("${google.recaptcha.key.secret}")
    public void setRecaptchaSecretKey(String recaptchaSecretKey) {
        this.recaptchaSecretKey = recaptchaSecretKey;
    }

    @Getter
    private static String recaptchaUrl;
    @Value("${google.recaptcha.url}")
    public void setRecaptchaUrl(String recaptchaUrl) {
        this.recaptchaUrl = recaptchaUrl;
    }

    @Getter
    private static String tossPaymentsSecretKey;
    @Value("${tossPayments.secretKey}")
    public void setTossPaymentsSecretKey(String tossPaymentsSecretKey) {
        this.tossPaymentsSecretKey = tossPaymentsSecretKey;
    }
}
