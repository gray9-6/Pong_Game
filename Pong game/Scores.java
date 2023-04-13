import java.awt.*;

public class Scores extends Rectangle {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;

    /*Now i am declaring the variables to keep count of player scores*/
    int player1;
    int player2;

    /*creating a constructor of score class*/
    Scores(int GAME_WIDTH,int GAME_HEIGHT){
        Scores.GAME_HEIGHT = GAME_HEIGHT;
        Scores.GAME_WIDTH = GAME_WIDTH;
    }

    /*Now we want to display the scores*/
    public void draw(Graphics g){
        /*score ka color set kiya*/
        g.setColor(Color.white);
        /*score ka font set kiya*/
        g.setFont(new Font("Consolas",Font.PLAIN,60));
        /*now we want to draw the scores*/
        g.drawString(String.valueOf(player1/10) + String.valueOf(player1 %10),GAME_WIDTH/2 - 90,100);
        g.drawString(String.valueOf(player2/10) + String.valueOf(player2 %10),GAME_WIDTH/2 + 30,100);
        g.setFont(new Font("Consolas",Font.PLAIN,30));

        /*if we want to display the scores the we can do in this way
        g.drawString("SCORES",GAME_WIDTH/2-100,GAME_HEIGHT - 500); */



    }
}
