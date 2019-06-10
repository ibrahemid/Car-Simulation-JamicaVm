import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

class CarMain {
    PriorityQueue<EventTypeWithTime> events = new PriorityQueue<>();
    private CarMovement carMovement;//carMovement feature
    private Thread carAccesleratorTH, sensor, eventThrowerThread;

    public CarMain() {
        carMovement = new CarMovement();
        CarAccelerator accelerator = new CarAccelerator(carMovement, true);
        carAccesleratorTH = accelerator;//sleep afterWhile 5 sec
        sensor = new Thread(new CarSensor(carMovement, events, accelerator));//sleep afterWhile 5 sec
        eventThrowerThread = new Thread(new EventThrower(events));//sleep afterWhile 5 sec 	execute every n second
    }
    void startCar() {
        carAccesleratorTH.start();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                sensor.start();
            }
        }, 1000);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                eventThrowerThread.start();
            }
        }, 5000);

    }
}
