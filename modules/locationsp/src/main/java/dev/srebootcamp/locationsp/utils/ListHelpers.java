package dev.srebootcamp.locationsp.utils;


import java.util.ArrayList;
import java.util.List;

public interface ListHelpers {
    static <T, T1> List<T1> map(List<T> list, FunctionWithException<T, T1> function) throws Exception {
        List<T1> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }


}
