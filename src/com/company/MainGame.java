package com.company;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainGame {
    private GraphicPanel g;
    private MyHeroes my;
    private ListEnemy listEnemy;

    public static void main(String[] args) {
        new MainGame().go();

    }


    MainGame(){
        JFrame f = new JFrame("NEW GAME");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,400);
        f.setLocationRelativeTo(null);

        g = new GraphicPanel();
        my = new MyHeroes();
        listEnemy = new ListEnemy();
        f.add(g);

        f.setVisible(true);

    }

    private void go() {
        while (true){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            g.repaint();

        }
    }

//////////////////////////////////////////////////////////////



    public class GraphicPanel extends JPanel{

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            g.fillRect(0,0,600,400);
                my.paint(g);
                listEnemy.paint(g);
                listEnemy.move();

        }
    }


    //////////////////////////////////////////////////////////////////////////


    public class MyHeroes{
         int[][] myStartPosition = {{200,365},{210,365},{220,365},{210,355}};
         Graphics2D g;


        public void paint(Graphics gr) {
            g = (Graphics2D) gr;
                g.setColor(Color.WHITE);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        g.fillRect(myStartPosition[i][0],myStartPosition[i][1],10,10);

                    }
                 }

        }
    }

    ////////////////////////////////////////////////////////////////////////////

       public class ListEnemy{

        ArrayList<Enemy> list = new ArrayList<>();

           public ListEnemy() {
               for (int i = 0; i < 5; i++) {
                   list.add(new Enemy(i*60));
               }
           }

           public void paint(Graphics g){
               g.setColor(Color.WHITE);
               for (int i = 0; i < list.size(); i++) {
                   list.get(i).paint(g);
               }
           }
           public void move(){

               for (int i = 0; i < list.size(); i++) {
                   list.get(i).move();
               }
           }
       }


    ////////////////////////////////////////////////////////////////////////////////

    public class Enemy{
        private boolean direction = true;
        private int startX ;

        public Enemy(int startX) {
            this.startX = startX;
        }

        private int[][] enemy = {
                {0,0,0,0,1},
                {0,0,1,0,1},
                {0,1,0,1,1},
                {0,0,1,0,1},
                {0,0,0,0,1}};


        public void paint(Graphics g) {

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (enemy[i][j] == 1)
                    g.fillRect( startX+10*i+5,10*j,10,10);
                }
            }
        }

        public void move(){
            if (direction && startX + 50 > 600){
                direction = false;
            }
            if (!direction && startX <= 0){
                direction = true;
            }
            if (!direction)
            startX -=1;
            if (direction)
            startX +=1;
        }
    }


}
