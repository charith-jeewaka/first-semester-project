package lk.ijse.florist_pos.final_project.dto;

public class EventDto {
    private int event_id;
    private String eventType;
    private String eventLocation;
    private String eventDate;
    private String eventPlacedDate;

    public EventDto(){
    }

    public EventDto(int event_id, String eventType, String eventLocation, String eventDate,
                    String eventPlacedDate) {
        this.event_id = event_id;
        this.eventType = eventType;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventPlacedDate = eventPlacedDate;
    }

    public int getEvent_id() {
        return event_id;
    }
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventLocation() {
        return eventLocation;
    }
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventPlacedDate() {
        return eventPlacedDate;
    }
    public void setEventPlacedDate(String eventPlacedDate) {
        this.eventPlacedDate = eventPlacedDate;
    }

    @Override
    public String toString() {
        return EventDto.class.getSimpleName() + " [event_id=" + event_id + ", eventType=" + eventType + ", eventLocation="
                + eventLocation + ", eventDate=" + eventDate + ", eventPlacedDate=" + eventPlacedDate+ "]";

    }

}
