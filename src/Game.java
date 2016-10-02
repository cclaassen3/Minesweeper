import java.util.Scanner;

/**
 * Created by cclaassen on 9/25/16.
 */
public class Game {

    private Gameboard game;
    private boolean gameover;

    public Game() {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("How big would you like your gameboard to be?");
        //System.out.println("Height (3 - 20):");
        //int height = scanner.nextInt();
        //System.out.println("Length (3 - 9):");
        //int length = scanner.nextInt();
        game = new Gameboard(9, 9);
        gameover = false;
    }

    public void makeMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'uncover' or 'flag' to decide what to do in your next move:");
        boolean valid = false;
        String type = "";
        while (!valid) {
            type = scanner.next();
            if (type.equals("uncover") || type.equals("flag")) {
                valid = true;
            } else {
                System.out.println("--> Invalid input. Try again ;)");
            }
        }
        System.out.println("  Which row?");
        int row = scanner.nextInt();
        System.out.println("  Which column?");
        int column = scanner.nextInt();
        Field field = game.fields[row - 1][column - 1];
        if (field.isUncovered()) {
            System.out.println("--> Oops, already uncovered. Go again!\n");
        } else if (type.equals("flag")) {
            field.setFlagged(true);
        } else {
            field.setUncovered(true);
            if (field.getType() == "mine") {
                gameover = true;
            }
        }
        printBoard();
    }

    public Gameboard getGame() {
        return game;
    }

    public void setGame(Gameboard game) {
        this.game = game;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public void printBoard() {
        System.out.println(game.toString());
    }
}
