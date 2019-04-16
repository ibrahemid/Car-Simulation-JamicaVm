import java.util.Random;

public class CarMovement {
    String[] directions = {"north", "west", "east", "south"};
    String direction;


    int speed = 0;

    CarMovement() {

        this.SetDirction();

        this.printSpeed();
    }

    public void speedUp() {
        speed += Constants.CHANGE_SPEED_RATE_CONSTANT;
        this.printSpeed();
    }


    public void speedUpInc() {
        boolean s = new Random().nextBoolean();
        //carMovementThread += s ? increaseSpeedBy : -increaseSpeedBy;
        speed += s ? Constants.CHANGE_SPEED_RATE_CONSTANT : -Constants.CHANGE_SPEED_RATE_CONSTANT;
        this.printSpeed();
    }

    public void slowDown() {
        speed -= Constants.CHANGE_SPEED_RATE_CONSTANT;
    }

    public void printSpeed() {
        System.out.println("--------------------------------------------------");
        System.out.println("\n car is moving | car speed is : " + speed + " | direction is : " + direction);

    }

    public int getSpeed() {
        return speed;
    }


    public void SetDirction() {
        int r = new Random().nextInt(4);
        direction = directions[r];
    }
}
