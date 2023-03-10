package dev.srebootcamp.locationsp.utils;

public interface FunctionWithException <From, To> {
    To apply(From from) throws Exception ;
}
