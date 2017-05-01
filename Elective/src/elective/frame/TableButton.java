package elective.frame;

import javax.swing.JButton;

public class TableButton extends JButton{

    private int row;

    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public TableButton() {

    }

    public TableButton(String name) {
        super(name);
    }

}
