package com.example.event_bus;

/**
 * Created by youx on 2016-12-05.
 */
public class ItemEvent {

    private int id;
    private String content;
    private EventType type;


    public ItemEvent() {
    }

    public ItemEvent(int id, String content,EventType type) {
        this.id = id;
        this.content = content;
        this.type=type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return content;
    }
}
