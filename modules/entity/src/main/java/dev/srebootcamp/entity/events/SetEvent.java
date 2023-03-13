package dev.srebootcamp.entity.events;

public record SetEvent(Object any, Audit audit) implements IEvent {
}
