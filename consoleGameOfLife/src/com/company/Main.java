package com.company;

import java.io.IOException;

public class Main {
    private static int xW = 25;
    private static int yH = 79;
    private static int[][] oldLife = new int[xW][yH];
    private static int[][] life = new int[xW][yH];

    public static void cls() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


    public static void spawn() {
        //spawn
        for (int x = 0; x < (xW); x++) {
            for (int y = 0; y < (yH); y++) {
                if ((int) (Math.random() * 2) == 0) oldLife[x][y] = 1;
            }
        }
    }

    private static void copyArrays(){
        for (int x =0; x < (xW); x++){
            for (int y =0; y < (yH); y++) {
                life[x][y] = oldLife[x][y];
            }
        }
    }

    private static void display(){
        copyArrays();
        StringBuilder builder = new StringBuilder();
        for (int x =0; x < (xW); x++){
            builder.setLength(10);
            for (int y =0; y < (yH); y++) {
                if (life[x][y] == 1){
                    builder.insert(y,"o");
                }
                else {
                    builder.insert(y," ");
                }
            }
            System.out.println(builder);
            builder.setLength(0);

        }



    }

    private static int check(int x, int y){

        int alive =0;
        alive += life[(x + xW - 1) % xW][(y + yH - 1 ) % yH];    //левая верхняя
        alive += life[(x + xW) % xW]    [(y + yH - 1 ) % yH];      //левая
        alive += life[(x  + xW + 1) % xW][(y + yH - 1) % yH];    //левая нижняя

        alive += life[(x + xW -1) % xW]  [(y + yH) % yH];          //верхняя
        alive += life[(x + xW +1) % xW]  [(y+ yH) % yH];          //нижняя

        alive += life[(x + xW - 1) % xW][(y + 1 + yH) % yH];    //правая верхняя
        alive += life[(x + xW) % xW]    [(y + 1 + yH) % yH];        //правая
        alive += life[(x  + xW + 1) % xW][(y + 1 + yH) % yH];    //правая нижняя

        return alive;
    }

    public static void newGen(){
        int  alive;
        //правила
        for (int x =0; x < (xW); x++){
            for (int y =0; y < (yH); y++) {

                alive = check(x,y);

                //правила
                if (alive == 3)
                {
                    oldLife[x][y] = 1;
                }
                else if (alive == 2 && life[x][y] == 1)
                {
                    oldLife[x][y] = 1;
                }
                else oldLife[x][y] = 0;
            }

        }
    }

    public static void main(String[] args) throws InterruptedException{
        spawn();
        for (int k=0;k<=300;k++) {
            cls();
            display();
            newGen();
            System.out.printf("Generic: " + k);
            Thread.sleep(80);
        }
    }
}