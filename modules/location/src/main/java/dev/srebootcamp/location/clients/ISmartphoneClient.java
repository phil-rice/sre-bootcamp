package dev.srebootcamp.location.clients;

import java.util.List;
import java.util.Map;

public interface ISmartphoneClient {
    /** A truck id and it's position */
    List<Map<String, Object>> retrieveTrucksAndPosition();
}
