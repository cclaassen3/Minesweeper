/**
 * Created by cclaassen on 9/25/16.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome :)\nHere's a new game... good luck!\n");

        Game game = new Game();
        game.printBoard();

        do {
            System.out.println("//Making move");
            game.makeMove();
        } while (!game.isGameover());

        System.out.println("End of game!");
    }
}
