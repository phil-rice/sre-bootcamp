package dev.srebootcamp.images.utils;

public interface BiConsumerWithException<T1, T2,E extends Exception> {
    void accept(T1 o, T2 i) throws E;
}
