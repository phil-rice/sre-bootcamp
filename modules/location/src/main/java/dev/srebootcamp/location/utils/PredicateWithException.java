package dev.srebootcamp.location.utils;

public interface PredicateWithException <From> {
    boolean test(From from) throws Exception ;

}
