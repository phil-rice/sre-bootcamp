package dev.srebootcamp.images.utils;

import java.util.ArrayList;
import java.util.List;

public interface ListHelpers {

    static <T> List<T> combine(List<T> l1, List<T> l2) {
        List<T> result = new ArrayList<>(l1);
        result.addAll(l2);
        return result;
    }
    static <T, T1> List<T1> map(List<T> list, FunctionWithException<T, T1> function) throws Exception {
        List<T1> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    static <T> List<T> fill(T t, int n) {
        return new java.util.ArrayList<T>(java.util.Collections.nCopies(n, t));
    }


    static <T, E extends Exception> void forEachWithIndex(List<T> list, BiConsumerWithException<T, Integer, E> consumer) throws E {
        for (int i = 0; i < list.size(); i++) consumer.accept(list.get(i), i);
    }
}
