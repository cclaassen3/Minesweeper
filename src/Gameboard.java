import java.awt.font.NumericShaper;
import java.util.*;

/**
 * Created by cclaassen on 9/25/16.
 */
public class Gameboard {

    int length;
    int height;
    Field[][] fields;

    public Gameboard(int height, int length) {
        this.height = height;
        this.length = length;
        this.fields = new Field[height][length];
        setupBoard();
    }

    public Gameboard() {
        this(8, 8);
    }

    public void setupBoard() {
        populateFields();
        setupNumbers();
        initialUncover();
    }

    private void populateFields () {
        Random random = new Random();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < length; column++) {
                Field field;
                int nextRandom = random.nextInt(8);
                if (nextRandom < 2) {
                    field = new MineField();
                } else {
                    field = new NumberField();
                }
                fields[row][column] = field;
            }
        }
    }

    private void setupNumbers() {
        Random random = new Random();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < length; column++) {
                Field field = fields[row][column];
                if (field.getType().equals("number")) {
                    int count = findNumMinesAround(row, column);
                    NumberField numberField = (NumberField) field;
                    numberField.setTotal(count);
                }
            }
        }
    }

    private void initialUncover() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < length; column++) {
                Field field = fields[row][column];
                if (field.getType().equals("number")) {
                    NumberField numberField = (NumberField) field;
                    if (numberField.getTotal() == 0) {
                        numberField.setUncovered(true);
                        uncoverAround(row, column);

                    }
                }
            }
        }
    }

    private void uncoverAround(int row, int column) {
        for (int y = row - 1; y < row + 2; y++) {
            for (int x = column - 1; x < column + 2; x++) {
                if (y >= 0 && y < height && x >= 0 && x < length) {
                    fields[y][x].setUncovered(true);
                }
            }
        }
    }

    private int findNumMinesAround(int row, int column) {
        int count = 0;
        for (int y = row - 1; y < row + 2; y++) {
            for (int x = column - 1; x < column + 2; x++) {
                if (y >= 0 && y < height && x >= 0 && x < length) {
                    if (fields[y][x].getType().equals("mine")) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("           ");
        for (int column = 0; column < length; column++) {
            builder.append(column + 1);
            builder.append(" ");
        }
        String columnNums = builder.toString();
        builder.append("\n\n");
        for (int row = 0; row < height; row++) {
            builder.append("      ");
            if (row < 9) {
                builder.append(" ");
            }
            builder.append(row + 1);
            builder.append("   ");
            for (int column = 0; column < length; column++) {
                builder.append(fields[row][column].toString());
                builder.append(" ");
            }
            builder.append("   ");
            builder.append(row + 1);
            builder.append("\n");
        }
        builder.append("\n");
        builder.append(columnNums);
        builder.append("\n");
        return builder.toString();
    }
}
