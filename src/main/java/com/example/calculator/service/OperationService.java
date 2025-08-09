package com.example.calculator.service;

import com.example.calculator.dto.OperationRequest;
import com.example.calculator.model.Operation;
import com.example.calculator.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Operation calculate(OperationRequest request) {
        double result;

        switch (request.getOperator()) {
            case "+" -> result = request.getOperand1() + request.getOperand2();
            case "-" -> result = request.getOperand1() - request.getOperand2();
            case "*" -> result = request.getOperand1() * request.getOperand2();
            case "/" -> {
                if (request.getOperand2() == 0) {
                    throw new IllegalArgumentException("Division by zero is not allowed");
                }
                result = request.getOperand1() / request.getOperand2();
            }
            default -> throw new IllegalArgumentException("Unsupported operator: " + request.getOperator());
        }

        Operation operation = new Operation();
        operation.setOperand1(request.getOperand1());
        operation.setOperand2(request.getOperand2());
        operation.setOperator(request.getOperator());
        operation.setResult(result);

        return operationRepository.save(operation);
    }
}





