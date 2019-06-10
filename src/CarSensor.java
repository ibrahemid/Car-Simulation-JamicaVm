import java.util.PriorityQueue;

public class CarSensor implements Runnable {

    CarMovement carMovement = null;
    PriorityQueue<EventTypeWithTime> queue = null;
    EventHandler eventHandler;
    CarAccelerator carAccelerator;

    CarSensor(CarMovement speed, PriorityQueue<EventTypeWithTime> queue,
              CarAccelerator carAccelerator) {
        this.carMovement = speed;
        this.queue = queue;
        this.carAccelerator = carAccelerator;
    }

    @Override
    public void run() {
        while (true) {
            EventTypeWithTime eventTypeWithTime = queue.poll();
            if (eventTypeWithTime != null) {


                String event = eventTypeWithTime.getEventType();
                if (event.equals(EventType.WORKERS_ON_THE_WAY.toString())) {
                    System.out.println("##################################################" +
                            "\n Event Occurred .... \n" +
                            "##################################################\n\n" +
                            "<!!!!> Workers on the Way <!!!!>  \n");
                    eventHandler = new EventHandler(carAccelerator, EventType.WORKERS_ON_THE_WAY,
                            carMovement); // check
                    System.out.println(
                            ">>>> Workers on the way Event Handler Thread Is starting >>>> \n");
                    eventHandler.start();
                    eventTypeWithTime.finished();
                    System.out.println(eventTypeWithTime.toString());

                } // fire the event && measure the time it take
                else if (event.equals(EventType.HANDEL_SPEED_CAMERA.toString())) {
                    System.out.println("\n##################################################" +
                            "\n Event Occurred .... \n" +
                            "##################################################\n\n" +
                            "<!!!!> Speed Camera on the Road   <!!!!> \n");
                    eventHandler = new EventHandler(carAccelerator, EventType.HANDEL_SPEED_CAMERA,
                            carMovement); // check
                    System.out.println(
                            ">>>> speed Camera on the Road Event Handler Thread Is starting >>>>  \n");
                    eventHandler.start();
                    eventTypeWithTime.finished();
                    System.out.println(eventTypeWithTime.toString());


                }
                else if (event.equals(EventType.TRAFFIC_LIGHT.toString())) {
                    System.out.println("##################################################" +
                            "\n Event Occurred .... \n" +
                            "##################################################\n\n" +
                            "<!!!!> Detected Traffic Light  <!!!!>");
                    eventHandler = new EventHandler(carAccelerator, EventType.TRAFFIC_LIGHT,
                            carMovement); // check
                    System.out.println(
                            ">>>> Traffic Light Event Handler Thread Is starting >>>>");
                    eventHandler.start();
                    eventTypeWithTime.finished();
                    System.out.println(eventTypeWithTime.toString());

                }

            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
