package com.ll.ticket.domain.customer.question.entity;

public enum QuestionCategory {
    PAYMENT("예매/결제"),
    REFUND("취소/환불");
    private final String value;

    QuestionCategory(String value) {
        this.value = value;

    }
    public String getValue() {
        return value;
    }
}
