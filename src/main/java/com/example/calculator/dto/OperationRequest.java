package com.example.calculator.dto;

import jakarta.validation.constraints.NotNull;

public class OperationRequest {

    @NotNull
    private Double operand1;

    @NotNull
    private Double operand2;

    @NotNull
    private String operator;

    public Double getOperand1() {
        return operand1;
    }

    public void setOperand1(Double operand1) {
        this.operand1 = operand1;
    }

    public Double getOperand2() {
        return operand2;
    }

    public void setOperand2(Double operand2) {
        this.operand2 = operand2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
