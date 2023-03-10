package dev.srebootcamp.location.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ListHelpers {
    static <T, T1> List<T1> map(List<T> list, FunctionWithException<T, T1> function) throws Exception {
        List<T1> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    static <T, T1> List<T1> collect(List<T> list, PredicateWithException<T> filter, FunctionWithException<T, T1> function) throws Exception {
        List<T1> result = new ArrayList<>();
        for (T t : list) {
            if (filter.test(t)) {
                result.add(function.apply(t));
            }
        }
        return result;
    }

    static <ID, From1, From2, To> List<To> enrichFromMap(List<From1> list, Map<ID, From2> map, FunctionWithException<From1, ID> idFunction, Function2WithException<From1, From2, To> merge) throws Exception {
        return collect(list,
                from -> map.containsKey(idFunction.apply(from)),
                from -> merge.apply(from, map.get(idFunction.apply(from))));
    }



    static <ID, T> Map<ID, T> toIdMap(List<T> list, FunctionWithException<T, ID> idFunction) throws Exception {
        Map<ID, T> result = new HashMap<>();
        for (T t : list) result.put(idFunction.apply(t), t);
        return result;
    }

}
