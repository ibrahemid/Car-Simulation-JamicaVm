import java.util.Random;
public class EventHandler extends Thread {
    CarAccelerator carAccelerator;
    EventType type = null;
    CarMovement car;
    EventHandler(CarAccelerator carAccelerator, EventType type, CarMovement car) {
        // pause the input thread then continue
        this.carAccelerator = carAccelerator;
        this.car = car;
        this.type = type;
    }
    @Override
    public void run() {
        if (this.type == EventType.WORKERS_ON_THE_WAY) {
            carAccelerator.changeStatus(false);
            handelWorkerEvent();
            carAccelerator.changeStatus(true);
        } else if (this.type == EventType.HANDEL_SPEED_CAMERA) {
            carAccelerator.changeStatus(false);
            handelSpeedCamera();
            carAccelerator.changeStatus(true);
        } else if (this.type == EventType.TRAFFIC_LIGHT) {
            carAccelerator.changeStatus(false);
            handelTrafficLight();
            carAccelerator.changeStatus(true);
        }

    }
    private void handelWorkerEvent() {

        if (car.getSpeed() > 30) {

            while (car.getSpeed() != 30) {
                System.out.println("\n --- slowing the car down by 5 | current Speed Is:" + car.speed);
                car.speed -= Constants.CHANGE_SPEED_RATE_CONSTANT;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            int r = new Random().nextInt(4);
            car.direction = car.directions[r];

        }
        System.out.println("\nthe car changed the direction to " + car.direction + " | current  direction is  "
                + car.direction + " | current  speed is  " + car.speed);


        System.out.println("\n" +
                "*************************************************" +
                "\n( Success)\tReturning to the normal state" +
                "\n*************************************************");
    }

    private void handelSpeedCamera() {
        int meters = new Random().nextInt((500 - 100) + 1) + 100;// 100 -250 - 420 - 170
        int distanceTaken = 0;
        System.out.println("--------------------------------------------------" +
                "\nspeed camera is detected on #" + meters + " Meters away");
        while (distanceTaken < meters) {
            System.out.println("\nThe car is moving slowly with " + car.speed + " speed , Distance taken is "
                    + distanceTaken + " Distance left is " + (meters - distanceTaken));
            if (car.speed > Constants.OPTIMAL_ROAD_SPEED) {
                car.speed -= Constants.CHANGE_SPEED_RATE_CONSTANT;
            } else {
                System.out.println("optimal speed is reached ,heading to speed camera with the optimal speed");
            }
            if (distanceTaken < meters) {
                distanceTaken += Constants.DISTANCE_RATE_CONSTANT;// speed to distance
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("\nthe car has passed the speed camera with the optimal speed \n");

        System.out.println("\n" +
                "*************************************************" +
                "\n( Success)\tReturning to the normal state" +
                "\n*************************************************");
    }

    private void handelTrafficLight() {

        int meters = new Random().nextInt((500 - 100) + 1) + 100;// 100 -250 - 420 - 170
        int distanceTaken = 0;
        int toRedDuration = new Random().nextInt((10 - 3) + 1) + 3;
        System.out.println("--------------------------------------------------" +
                "\nTraffic is detected after [" + meters + "] Meters");
        int i = 0;
        while (distanceTaken < meters) {
            System.out.println("\nTraffic status is " + "[[ YELLOW ]]" + " car is moving slowly with " + car.speed
                    + " speed , Distance taken is " + distanceTaken + " Distance left is " + (meters - distanceTaken));

            if (toRedDuration == (i)) {
                //the timer for the red flag is finished , The traffic is red which require a hard brake
                System.out.println("\n!!!! Warning !!!!  The traffic  is now " + "[[ RED ]]" + " Taking Action : Pressing hard brake\n");
                car.speed -= car.speed;
                System.out.println("The Car has completely stopped | The traffic light is " + "[[ RED ]]" + " Waiting for the green Light");
            } else if (car.speed > 10) {
                car.speed -= Constants.CHANGE_SPEED_RATE_CONSTANT;
            } else {
                //speed is 9 or less
                System.out.println("\npressing brakes .... waiting for the flag to turn red \n");
            }
            distanceTaken += Constants.DISTANCE_RATE_CONSTANT;// speed to distance

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            i++;
        }
        System.out.println("\n Traffic light is [ GREEN ] \n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\nthe car has passed the Traffic light \n");

        System.out.println("\n" +
                "*************************************************" +
                "\n( Success )\tReturning to the normal state" +
                "\n*************************************************");


    }
}
