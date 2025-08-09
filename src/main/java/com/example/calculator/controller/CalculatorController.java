package com.example.calculator.controller;

import com.example.calculator.dto.OperationRequest;
import com.example.calculator.model.Operation;
import com.example.calculator.service.OperationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    private final OperationService operationService;

    public CalculatorController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/calculate")
    public double calculate(@RequestBody @Valid OperationRequest request) {
        double result;

        switch (request.getOperator()) {
            case "+" -> result = request.getOperand1() + request.getOperand2();
            case "-" -> result = request.getOperand1() - request.getOperand2();
            case "*" -> result = request.getOperand1() * request.getOperand2();
            case "/" -> result = request.getOperand2() != 0 ? request.getOperand1() / request.getOperand2() : Double.NaN;
            default -> throw new IllegalArgumentException("Недопустимый оператор");
        }

        Operation operation = new Operation();
        operation.setOperand1(request.getOperand1());
        operation.setOperand2(request.getOperand2());
        operation.setOperator(request.getOperator());
        operation.setResult(result);

        operationService.saveOperation(operation);

        return result;
    }

    @GetMapping("/history")
    public List<Operation> getHistory() {
        return operationService.getAllOperations();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Сервис работает!";
    }
}


