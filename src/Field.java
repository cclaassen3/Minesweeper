/**
 * Created by cclaassen on 9/25/16.
 */
public abstract class Field {

    private String type;
    private boolean uncovered;
    private boolean flagged;

    public Field(String type) {
        this.type = type;
        this.uncovered = false;
        this.flagged = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if (flagged) {
            return ("*");
        } else if (!uncovered) {
            return ("~");
        } else {
            return this.string();
        }
    }

    public boolean isUncovered() {
        return uncovered;
    }

    public void setUncovered(boolean uncovered) {
        this.uncovered = uncovered;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public abstract String string();
}
