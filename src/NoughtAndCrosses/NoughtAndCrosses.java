package NoughtAndCrosses;

import java.util.Random;
import java.util.Scanner;

public class NoughtAndCrosses {

    private static final char HUMAN_DOT = 'X';
    private static final char AI_DOT = 'O';
    private static final char EMPTY_DOT = '*';
    private static final int MAP_SIZE_X = 3;
    private static final int MAP_SIZE_Y = 3;
    private static final int WIN_LENGTH = 3;
    private static final char [][] map = new char[MAP_SIZE_X][MAP_SIZE_Y];
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rnd = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while(true) {
            humanTurn();
            printMap();
            if(checkWin(HUMAN_DOT)) {
                System.out.println("Выиграл игрок");
                break;
            }
            if(isMapFull()) {
                System.out.println("Nobody");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(AI_DOT)) {
                System.out.println("Выиграл компьютер");
                break;
            }
            if (isMapFull()) {
                System.out.println("Nobody");
                break;
            }
        }
        sc.close();
    }

    private static void initMap() {
        for (int i = 0; i < MAP_SIZE_X; i++) {
            for (int j = 0; j < MAP_SIZE_Y; j++) {
                map[i][j] = EMPTY_DOT;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i <= MAP_SIZE_X; i++) System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < MAP_SIZE_Y; i++ ) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < MAP_SIZE_X; j++) System.out.print(map [i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x,y;
        do {
            System.out.println("Введите координаты X и Y: ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isValidCell(x, y) || !isEmptyCell(x, y));
        map [y][x] = HUMAN_DOT;
    }

    private static void aiTurn() {
        int x,y;
        do {
            x = rnd.nextInt(MAP_SIZE_X);
            y = rnd.nextInt(MAP_SIZE_Y);
        } while (!isEmptyCell(x, y));
        map[y][x] = AI_DOT;
    }

    private static boolean checkWin(char c) {

        //horizontal
        if(map[0][0] == c && map [0][1] == c && map [0][2] == c) return true;
        if(map[1][0] == c && map [1][1] == c && map [1][2] == c) return true;
        if(map[2][0] == c && map [2][1] == c && map [2][2] == c) return true;
        //vertical
        if(map[0][0] == c && map [1][0] == c && map [2][0] == c) return true;
        if(map[0][1] == c && map [1][1] == c && map [2][1] == c) return true;
        if(map[0][2] == c && map [1][2] == c && map [2][2] == c) return true;
        //diagonal
        if(map[0][0] == c && map [1][1] == c && map [2][2] == c) return true;
        if(map[2][0] == c && map [1][1] == c && map [1][2] == c) return true;

        return false;

    }

    private static boolean checkLine (int x, int y, char c) {

        for (int vx = -1; vx <= 1; vx++) { if (x + vx <= map.length && x + vx >= 0) {
            for (int vy = -1; vy <= 1; vy++) { if (y + vy <= map.length && y + vy >= 0) {
                int nextPointX = vx;
                int nextPointY = vy;
                int currentLine = 1;
                for (int winLine = 1; winLine < WIN_LENGTH; winLine++) {
                    if (map[x][y] == map[x + nextPointX][y + nextPointY]) {
                        currentLine++;
                        nextPointX++;
                        nextPointY++;
                    }
                }
                if (currentLine == WIN_LENGTH) return true;
            }
            }
        }
        }
        return false;
    }

    private static boolean isMapFull() {
        for (int i = 0; i < MAP_SIZE_Y; i++) {
            for (int j = 0; j < MAP_SIZE_X; j++) {
                if (map[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < MAP_SIZE_X && y>= 0 && y < MAP_SIZE_Y;
    }

    private static boolean isEmptyCell (int x, int y) {
        return map [y][x] == EMPTY_DOT;
    }
}
