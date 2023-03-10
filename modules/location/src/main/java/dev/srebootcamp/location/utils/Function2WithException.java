package dev.srebootcamp.location.utils;

public interface Function2WithException<From1,From2, To> {
    To apply(From1 from1, From2 from2)  throws Exception;
}
