package dev.srebootcamp.approvalOrchestrationApi.utils;

public interface FunctionWithError<From,To> {
    To apply(From from) throws Exception;
}
