package org.example.fxdemo.event;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import lombok.Getter;

@Getter
public class ItemEvent<T> extends Event {
    public static final EventType<ItemEvent> ANY;
    public static final EventType<ItemEvent> ITEM_CLICK;
    public static final EventType<ItemEvent> ITEM_ADDED;

    static {
        ANY = new EventType<>(Event.ANY, "ITEM");
        ITEM_CLICK = new EventType<>(ItemEvent.ANY, "ITEM_CLICK");
        ITEM_ADDED = new EventType<>(ItemEvent.ANY, "ITEM_ADDED");
    }

    private final T item;

    public ItemEvent(Object source, EventTarget target, EventType<ItemEvent> eventType, T item) {
        super(source, target, eventType);
        this.item = item;
    }

    public ItemEvent(Object source, EventTarget target, T item) {
        this(source, target, ITEM_CLICK, item);
    }

}