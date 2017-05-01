package elective.frame;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TableButtonEditor extends DefaultCellEditor{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TableButton button;

    private TableEvent event;

    public TableButtonEditor(String _text) {
        super(new JTextField());
        button = new TableButton(_text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //这里调用自定义的事件处理方法
                event.invoke(e);
            }

        });

    }

    public TableButtonEditor(TableEvent e, String _text) {
        this(_text);
        this.event = e;
    }
    /*
    重写编辑器方法，返回一个按钮给JTable
    */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
//      setClickCountToStart(1);
//将这个被点击的按钮所在的行和列放进button里面
        button.setRow(row);
        button.setColumn(column);
        return button;
    }


}
