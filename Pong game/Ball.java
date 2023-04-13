import java.awt.*;
import java.util.Random;

/*Ball class is also extending the rectangle class which will allow the ball class to use the super
* to inherit the rectangle class properties*/
public class Ball extends Rectangle {
    /*now ball will move in both the direction so,, we need two velocity*/
    int xVelocity;
    int yVelocity;

    /*setting the intial speed of ball*/
    int initialSpeed = 5;

    /*Declaring the random class (in built*/
    Random random;

    /*creating the ball class constructor and passing some parameters
    * x --> ball kaha se start hogi horizontally,,, y ---> ball kaha se start hogi vertically*/
    Ball(int x, int y, int width, int height){
        /*so we need these things in the balls*/
        super(x,y,width,height);
        /*so this Random class allow us to use random values, we just have to provide range
        * so this random will choose either 0,1  randomly,,, why we are using random?? ,,,,
        * becoz in the pong table , ball will be at middle of table,,, so when game starts
        * the ball can either go to left or go to right ,, both the players have equal chance of getting ball
        * at starting of the game*/
        random = new Random();
        int randomXDirection = random.nextInt(2);

        /*so if randomXDirection == 0,, then i am assigning the ball to the opposite side */
        if(randomXDirection == 0){
            randomXDirection--;
        }
        /*or uss ball ki direction set kar di towards randomxdirection*/
        setXDirection(randomXDirection);

        int randomYDirection = random.nextInt(2);
        /*so agar randomYdirection == 0,, hai toh mai ball ko opposite direction mein bhejunga i.e upper direction of Y*/
        if(randomYDirection == 0){
            randomYDirection--;
        }
        /*or ab uss ball ki direction set kar di towards y direction*/
        setYDirection(randomYDirection);
    }

    /*so ab ball ko y direction mein set karne ke liye y ki velocity ko randomYdirection ke equal kar dunga*/
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }
    /*so ab ball ko x direction mein set karne ke liye x ki velocity ko randomXdirection ke equal kar dunga*/
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    /*so now i need move function*/
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }

    /*now at last we need to draw the ball*/
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y,width,height);

        /*Now i am drawing the line on the table vertivally*/
        g.drawLine(1000/2,0,1000/2,555);
        /*creating a circle at the center of field
        g.drawOval((1000/2) - 25,(555/2)-25,50,50);     */
        /*creating another small circle inside the first one
        g.drawOval((1000/2) - 15,(555/2)-15,30,30);   */
    }

}
