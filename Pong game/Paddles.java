import java.awt.*;
import java.awt.event.KeyEvent;

/*paddle function is extending rectangle class which will help us in draw various structures like rectangele and many more
* it gives us the super keyword which will help us to draw the particular graphics very precisely and in very good way
* we are able to draw and paint only in Jpanel class,, but to paint or draw int diffrent class we use rectangle extension
* so we can use graphics by extending rectangle and java awt*/
public class Paddles extends Rectangle {
    /*as we have two paddles , so we will have to give both of them different name
    * like id1 and id2*/
    int id;
    /*and both the pabbles will move up and dowm so we will need speed in Y direction only */
    int yVelocity;
    /*and the paddles will move with what speed*/
    int speed = 10;

    /*now i have created the paddle class constructor , int which i have passed some arguments
    * where x and y are the x co-ordinate of the paddles and y co-ordinate of the paddle*/
    Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        /*whenever we call this function it will the paddle with these variables on it*/
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        /*we have taken ,id variable different ,because we need all these variables for different paddles(like we have
        * more than one paddles,,so whether it is paddle1 or paddle2 we will get the id according to that */
        this.id = id;
    }

    /*after this i need draw function as well*/
    public void draw(Graphics g){
        /*agar meri id 1 hai toh mera paddle blue mein print hoga else red color ka hoga*/
        if(id==1){
            g.setColor(Color.blue);
        }
        else{
            g.setColor(Color.red);
        }

        /*after setting the color we just need to fill the rectangle(means Paddle)*/
        /*why we are using only width and height, becoz we have provided these things in the super constructor, and
         * what is the super constructor , as we know that rectangle class allows us to use the super constructor
         * in which we have provided the x,y,Paddle_width, paddle_height, and now the super constructor is aligning
         * the g of graphics to utilise the all those things, everywhere in the class
         * we haven't declared the x,y,width and height anywhere , but due to the rectangle class and super keyword
         * in the rectange class we are able to use those things,
         * becoz they are alredy provide in the rectangle class in built*/
        g.fillRect(x,y,width,height);
    }

    /*agar key pressed kiya toh kya hoga*/
    public void keyPressed(KeyEvent e){
        switch(id){
            /*case 1 is when paddle1 is being operated*/
            case 1:
                /*so agar mai keyboard se w press kar raha hu toh mujhe uapr jana hai(i.e  y have -ve direction)*/
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDirection(-speed);
                    /*so now i have to create this setYdirection method*/
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    /*or agar s press kar rha hu toh downward direction mein jaana hai (i.e y have +ve direction)*/
                    setYDirection(speed);
                }
                break;

            /*case 2 is when paddle2 is getting operated*/
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    setYDirection(-speed);
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDirection(speed);
                }
                break;
        }
    }

    /*so for releasing the key we have this releasing function*/
    public void keyReleased(KeyEvent e){
        switch(id){
            /*case 1 is when paddle1 is being operated*/
            case 1:
                /*so agar mai keyboard se w press kar raha hu toh mujhe uapr jana hai(i.e  y have -ve direction)*/
                if(e.getKeyCode() == KeyEvent.VK_W){
                    setYDirection(0);
                    /*so now i have to create this setYdirection method*/
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    /*or agar s press kar rha hu toh downward direction mein jaana hai (i.e y have +ve direction)*/
                    setYDirection(0);
                }
                break;

            /*case 2 is when paddle2 is getting operated*/
            case 2:
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    setYDirection(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    setYDirection(0);
                }
                break;
        }
    }

    /*so now i have to set Y direction ,, */
    public void setYDirection(int i) {
        yVelocity = i;
        /*This means whatever the directions we are getting , my velocit should be i
        * and move my paddles over here*/
    }

    /*so we are creating the move functions for paddles*/
    public void move(){
        /*se simply needs to increase the y velocity ,, becoz my paddles are moving in y direction only
        * but there are multiple things which we have to take consideration of ,, that when we are moving the paddle
        * 1. when we are hitting the keys, so for that ,, we wil create a class in GamePanel*/
        y = y + yVelocity;
    }
}

