public class EventTypeWithTime implements Comparable<EventTypeWithTime> {
    String eventType;
    long startAt;
    long finishAt;

    public EventTypeWithTime(String eventType) {
        this.eventType = eventType;
        this.startAt = System.currentTimeMillis();
    }

    public void finished() {
        this.finishAt = System.currentTimeMillis();
    }

    public long calculate() {
        return (this.finishAt - this.startAt);
    }


    public String getEventType() {
        return eventType;
    }

    @Override
    public int compareTo(EventTypeWithTime o) {
        return (int) this.startAt - (int) o.startAt;
    }

    @Override
    public String toString() {
        return "--------------------------------------------------" +
                "\n****Time Stamp****" +
                "\nEvent >>> " + eventType +
                "\nstartAt >>> " + startAt +
                "\nfinishAt >>> " + finishAt +
                "\nTime it Took to response >>> [" + calculate() + "] ms " +
                "\n****Time Stamp****" +
                "\n--------------------------------------------------"
                ;
    }

}
