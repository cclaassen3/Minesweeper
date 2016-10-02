/**
 * Created by cclaassen on 9/25/16.
 */
public class NumberField extends Field {

    int total;

    public NumberField() {
        super("number");
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String string() {
        if (total == 0) {
            return " ";
        } else {
            return Integer.toString(total);
        }
    }
}
