public class CarAccelerator extends Thread {
    public boolean status;
    CarMovement carMovement;
    CarAccelerator(CarMovement carMovement, boolean status) {
        this.carMovement = carMovement;
        this.status = status;
    }
    @Override
    public void run() {
        while (true) {
            if (status) {
                if (this.carMovement.getSpeed() <= Constants.MINIMUM_SPEED_OF_THE_CAR) {
                    this.carMovement.speedUp();
                } else if (this.carMovement.getSpeed() >= Constants.MAXIMUM_SPEED_OF_THE_ROAD) {
                    this.carMovement.slowDown();
                } else {
                    this.carMovement.speedUpInc();
                }
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void changeStatus(boolean status) {
        this.status = status;
    }
}
