package dev.srebootcamp.entity.services;

import dev.srebootcamp.entity.events.IEvent;

import java.util.List;

public interface INameService {
    List<IEvent> getEventsFor(String nameSpace, String name);

}
