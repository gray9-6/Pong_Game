import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    /* i have created the object of GamePanel class */
    GamePanel panel;

    /* i have created a constructor of our GameFrame class , in which we will initialize the components*/
    GameFrame(){
        /*intitialized the GamePanel class*/
        panel = new GamePanel();
        /*this keyword represents to object of currect class(here GameFrame class)
        * if i have the object of GameFrame class then this object will refer to that object
        * agar mai koi abject banunga Gameframe ka toh usme ye saari chije aa jayengi becoz of this keyword*/


        /*added panel in our frame object*/
        this.add(panel);
        /*added title of fram*/
        this.setTitle("Pong Game");
        /*set resizable to false, so that external source can't change the size of game*/
        this.setResizable(false);
        /*background color of panel*/
        this.setBackground(Color.BLACK);

        /*with this function , we can close the game by clicking on cross button*/
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*JFrame have this function called pack(),,,,what this function does is that ,
         *whatever the size of panel dimensions , we have given
        * it will take all of that dimensions itself, hume cursor se khich kar usko bada nahi karna padega  */
        this.pack();
        /*frame ki visibility set kar di ture par*/
        this.setVisible(true);
        /*we don't want to give any location or size to the fucntions we have , that's why we used this*/
        this.setLocationRelativeTo(null);
    }
}
