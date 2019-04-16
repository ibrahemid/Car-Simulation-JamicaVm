import java.util.PriorityQueue;
import java.util.Random;

public class EventThrower implements Runnable {
    PriorityQueue<EventTypeWithTime> priorityQueue;

    EventThrower(PriorityQueue<EventTypeWithTime> eventTypeWithTime) {
        this.priorityQueue = eventTypeWithTime;
    }

    @Override
    public void run() {
        while (true) {
            int i = new Random().nextInt(3);
            this.priorityQueue.add(new EventTypeWithTime(EventType.values()[i].toString()));// start at benchmark here
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
