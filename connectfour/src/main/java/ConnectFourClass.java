
import connectfourclient.Connect4Client;
import connectfourclient.Player;

import java.io.IOException;
import java.util.*;

public class ConnectFourClass {
    public static void main(String[] args) throws IOException {

        Connect4Client myClient = new Connect4Client("139.126.184.118", 9032, new Player() {
            @Override
            public int nextMove(char c, char[][] board) {
                int move = getRand();
                //check verticals
                ArrayList<Integer> full;
                full = fullCol(board);

                ArrayList<Integer> verticals = checkVerticals(board);

                ArrayList<Integer> horzontal = checkHorz(board);


                if (verticals.get(0) != 10) {
                     move = Collections.max(verticals);



                    while (full.contains(move)  &&  verticals.contains(move)) {
                        move = getRand();
                    }
                    return move;
                }



//                Scanner scanner = new Scanner(System.in);
//                String input = scanner.nextLine();
                //return Integer.parseInt(input);
                return move;
            }

            @Override
            public String getTeamName() {
                return "Sleep Deprived!";
            }
        });
        myClient.turnOnDebugging();
        myClient.start();
    }

    private static int getRand() {
        Random rand = new Random();
        int rand_int = rand.nextInt(7);
        return rand_int;
    }

    private static ArrayList<Integer> fullCol(char[][] grid) {
        ArrayList<Integer> hazards = new ArrayList<>();
        for (int i = 0; i < 7; i++) {//x (col)
            if (grid[i][5] != ' ') {
                hazards.add(i);
            }
        }
        if (hazards.isEmpty()) {
            hazards.add(10);
        }
        return hazards;
    }

    private static ArrayList<Integer> checkVerticals(char[][] grid) {
        ArrayList<Integer> hazards = new ArrayList<>();
        char temp = grid[0][0];
        int count = 0;
        for (int i = 0; i < 6; i++) {//x (col)
            for (int j = 0; j < 5; j++) {//y (row)
                if (grid[i][j + 1] != ' ' && temp == (grid[i][j + 1])) {
                    count += 1;
                    if (count > 0) {
                        hazards.add(i);
                    }
                } else {
                    count = 0;
                    temp = grid[i][j + 1];

                }
            }
        }
        if (hazards.isEmpty()) {
            hazards.add(10);
        }
        return hazards;
    }

    private static ArrayList<Integer> checkHorz(char[][] grid) {
        ArrayList<Integer> hazards = new ArrayList<>();
        char temp = grid[0][0];
        int count = 0;
        if (hazards.isEmpty()) {
            for (int j = 0; j < 5; j++) {//y (row)
                for (int i = 0; i < 6; i++) {//x (col)
                    if (grid[i + 1][j] != ' ' && temp == (grid[i + 1][j])) {
                        count += 1;
                        if (count > 0) {
                            hazards.add(i + 1);
                        }
                    } else {
                        count = 0;
                        temp = grid[i + 1][j];

                    }
                }
            }
            hazards.add(10);
        }
        return hazards;
    }


}
