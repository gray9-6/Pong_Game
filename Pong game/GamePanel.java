import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.Random;

/*JPanel will implements the Runnable class which allows us to run the program*/
public class GamePanel extends JPanel implements Runnable{
    /*Declaring dimensions of game panel*/
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH*(0.55));
    /*Dimension here is class*/
    static final Dimension SCREEN_SIZE= new Dimension(GAME_WIDTH,GAME_HEIGHT);

    /*Now we will give dimensions of paddles*/
    static final int PADDLE_HEIGHT = 100;
    static final int PADDLE_WIDTH = 25;

    /*i am intializing the ball dia*/
    static final int BALL_DIAMETRE = 20;

    /*We are using the thread class becoz when a program runs , it runs only once , and that becomes process,,
    * but if we have to do multiple tasks in single process then every single process have threads
    * so basically what happens is ,, when the game runs ,, ball have have to run multiple times
    * so it creates the multiple thread in which ,, it was moving fast ,, but to naked eyes ,,, it shows like
    * it was moving */
    Thread gameThread;

    /*So i want image variable which will draw my paddle*/
    Image image;
    /*i also want graphics to draw */
    Graphics graphics;

    /*so i want more than 1 paddle , so i need to make the paddle class objects*/
    Paddles paddle1;
    Paddles paddle2;

    /*so now i am creating the object of ball class*/
    Ball ball;

    /*now we are creating the objetc fro score class*/
    Scores score = new Scores(GAME_WIDTH,GAME_HEIGHT);

    /*i have created a constructor of GamePanel class*/
    GamePanel(){

        /*as i need multiple paddles so i will call the newPaddles method , which will give me the paddles*/
        newPaddles();
        /*so whenever my game panel starts i will create the new ball*/
        newBall();
        /*what ever key i am pressing it will focus on that */
        this.setFocusable(true);
        /*at the same time i have to addkeylistner, becoz if i am not adding this keylistner to my Jpanel then
        * it will not be able to listen the key which i am pressing on mp laptop*/
        this.addKeyListener(new AL());
        /*with this function i will tell that i prefer the game panel to be of screen_size object*/
        this.setPreferredSize(SCREEN_SIZE);
        /*So now i have to start the theread,,, so i have initialized the game thread
        and passed the current class onject int it,,, and with statr function i have started the thread*/
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newBall() {
        /*i am creating the ball object,,, so we need the x direction of ball ,, which will be the center of table
        * and y direction will be randommly chosen,, that it should go to left or right*/
        /*so we have declared the inbuilt random class function witch size 2(i.e 0 & 1) */
        Random random = new Random(2);
        ball = new Ball(GAME_WIDTH/2 - BALL_DIAMETRE/2,random.nextInt(GAME_HEIGHT/2-BALL_DIAMETRE),BALL_DIAMETRE,BALL_DIAMETRE);


    }

    /*function to create new paddles*/
    private void newPaddles() {
        paddle1 = new Paddles(0,(GAME_HEIGHT/2 - PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddles(GAME_WIDTH -PADDLE_WIDTH,(GAME_HEIGHT/2 - PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    /*now we will use paint function*/

    @Override
    public void paint(Graphics g) {
        /*why we are using the image and graphics here
        * becoz here we are using multiple classes to print and draw particular things
        * so only having a single paint function will not helps us to create this project easily */
        /*That's why we have created the image object which will allow us to create the image from anywhere
        * and this image will store all those figures and then will come to paint function,, and that paint
        * fucntion  will paint the particular figure for us*/
        /*so what we have done is we have created the image*/
        image = createImage(getWidth(),getHeight());
        /*  and then using the graphics we have got the graphics  which we have stored in image*/
        graphics = image.getGraphics();
        /*  and then using the graphics we called the draw function */
        draw(graphics);
        /*this g.drawImage will create the figure for us*/
        g.drawImage(image,0,0,this);
        /*now with these i can able to draw any thing anywhere*/
    }

    /*and in the draw function we are using the graphics
     and then using the paddle we are calling the draw function,, which will draw the paddle for us
    this function will draw the paddles*/
    /*and also calling the draw function for ball*/
    private void draw(Graphics g) {
        /*drawn for paddles*/
        paddle1.draw(g);
        paddle2.draw(g);
        /*drawn for ball*/
        ball.draw(g);
        /*drawn for score*/
        score.draw(g);
    }

    /*This will allow me to create a multiple threads,, becoz this will allow me to create a run loop
    * which will allow me to run loop,, which will allow me to run my loop again and again and
    * create the Game panel multiple times*/
    @Override
    public void run() {
        /*in this long variable i will have the last time,,when was the last time my thread introduced in the system
        * becoz the execution happens in nano time (10^-9 sec) ,, so this much fast execution happens in the system*/
        long lastTime = System.nanoTime();

        /*we will divide the nano seconds in 60 parts*/
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        /*detla means change,, what are the changes i am able to see in time*/
        double delta = 0;

        /*this loops is very important ,, we can use this loops in various projects*/
        /*what this loop does, is that ,, as the time is going on ,, we need to check whether time is moving or not
        * becoz if the time is moving ,, my project is working ,, then everthing will move,,
        * if not then my screen will get stuck and mothing will move*/
        while(true){

            if(score.player1 == 2 || score.player2 == 2){
                break;
            }
            /*now will have present time of system,, i.e at what time this new thread is introduced in system*/
            long now = System.nanoTime();

            delta += (now - lastTime)/ns;

            /*what this means ,, so if my game was started some times before,, so there must be some last time
            * and now i am at some particular time ,,, so this means there would be some difference ,, if my game
            * is running ,, if that is the case ,, then i will get some positive values,, so if i am getting the positive
            * values then it is fine  ,,, but if i am not getting the positive values
            * then i will run loop again and again  */

            /*so now we have done this,,, so my last time will become the curr time (i.e now)*/
            lastTime = now;

            /*if delta is >= 1 ,, then i have to do multiple operations,, i.e i have to make my paddles moveables */
            if(delta >= 1){

                /*this moveable part,, i have to do in real time ,, so i will create a move functuion*/
                move();
                /*and then we call the repaint function,,, becoz we are calling the game again and again*/
                repaint();
                /*so every time we check for collison*/
                checkCollison();
                /*and then we simply decrease the delta ,,, if it is greater than 0*/
                delta--;
            }

        }
    }

    /*we need to check the collison ,, whether it is happening with upper walls or side walls*/
    private void checkCollison() {
        /*bounce ball of the top or bottom window edges*/
        /*agar ball ki y value 0 se kam hai it means upper wall se collison hui hai*/
        if(ball.y <= 0){
            /*or agar upar wall se collison ho rahi hai to hume uski direction change kar deni hai
            * taaki wo upper waal se takra kar niche ki taraf aa jaye*/
            ball.setYDirection(-ball.yVelocity);
        }
        /*ab agar ball lower edge ko hit kar rahi hai,to uski direction ,, upar ki taraf mod do*/
        if(ball.y >= GAME_HEIGHT - BALL_DIAMETRE){
            ball.setYDirection(-ball.yVelocity);
        }
        /*agar ball paddle 1 se takara gayi toh ,, hume uski direction change karni hai*/
        if(ball.intersects(paddle1)){
            ball.xVelocity = -ball.xVelocity;
            /*jab ball paddle se takra rahi hai to ball ki speed badha denge*/
            ball.xVelocity++;
            /*or agar ball niche ko y direction mein jaa rahi hai toh uss side uski speed badha denge */
            if(ball.yVelocity >0){
                ball.yVelocity++;
            }
            /*nahi toh agar wo upar ke side jaa rhi hai , toh uski speed uss direction men bdha denge*/
            else{
                ball.yVelocity--;
            }

            /*or ab ball ko set kar denge */
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        /*similarly agar ball paddle2 se takra gayi toh uski direction change karni hai*/
        if(ball.intersects(paddle2)){
            ball.xVelocity = -ball.xVelocity;
            /*jab ball paddle se takra rahi hai to uski speed badha denge*/
            ball.yVelocity++;
            /*or agar ball niche ko y direction mein jaa rahi hai toh uss side uski speed badha denge */
            if(ball.yVelocity >0){
                ball.yVelocity++;
            }
            /*nahi toh agar wo upar ke side jaa rhi hai , toh uski speed uss direction men bdha denge*/
            else{
                ball.yVelocity--;
            }
            /*or ab ball ko set kar denge */
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        /*agar mera paddle1 niche waali waal se takra rha hai toh*/
        if(paddle1.y <= 0){
            /*to maine paddle ki y direction ko  0 kar di ,, becoz mai nahi chahta ki wo usse niche jaaye*/
            paddle1.y = 0;
        }
        /*agar paddle1 upar waali wall se takra gaya toh */
        if(paddle1.y >= GAME_HEIGHT-PADDLE_HEIGHT){
            /*toh mai paddle1 ki ki y ditrection ko GAME_HEIGHT-PADDLE_HEIGHT ke equal kar dunga, becoz i don't want it to go beyond that waal*/
            paddle1.y =GAME_HEIGHT-PADDLE_HEIGHT;
        }
        /*agar mera paddle2 niche waali waal se takra rha hai toh*/
        if(paddle2.y <= 0){
            /*to maine paddle ki y direction ko  0 kar di ,, becoz mai nahi chahta ki wo usse niche jaaye*/
            paddle2.y = 0;
        }
        /*agar paddle2 upar waali wall se takra gaya toh */
        if(paddle2.y >= GAME_HEIGHT-PADDLE_HEIGHT){
            /*toh mai paddle2 ki  y ditrection ko GAME_HEIGHT-PADDLE_HEIGHT ke equal kar dunga, becoz i don't want it to go beyond that waal*/
            paddle2.y =GAME_HEIGHT-PADDLE_HEIGHT;
        }
        /*ab agar ball right side walls se takra rahi hai toh uski jagah new ball aa jaye game mein*/
        if(ball.x >= GAME_WIDTH-BALL_DIAMETRE){

            newPaddles();
            newBall();
            score.player1++;
        }
        /*ab agar ball left side walls se takra rahi hai toh uski jagah new ball aa jaye game mein*/
        if(ball.x <= 0){

            newPaddles();
            newBall();
            score.player2++;
        }

    }

    /*so in the moveable function we have to make our paddles moveable,,
    so for that we have implemeted the move function*/
    private void move() {
        /*so in the move function i will call the paddles*/
        /*but we have't maked the move function ,, so will make the move function in paddle*/
        /*also in this function we are calling the move function for ball*/
        paddle1.move();
        paddle2.move();
        /*also in this function we are calling the move function for ball*/
        ball.move();

    }

    /*so what happens is whenever we are hitting the keys from keyboard,, at that time we are doing some operations
    * so, whenever we are pressing the keys or releasing the keys ,, some events are happennig*/
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e){

            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);;
        }
    }
}
