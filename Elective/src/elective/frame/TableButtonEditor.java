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
            //��������Զ�����¼�������
                event.invoke(e);
            }

        });

    }

    public TableButtonEditor(TableEvent e, String _text) {
        this(_text);
        this.event = e;
    }
    /*
    ��д�༭������������һ����ť��JTable
    */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
//      setClickCountToStart(1);
//�����������İ�ť���ڵ��к��зŽ�button����
        button.setRow(row);
        button.setColumn(column);
        return button;
    }


}
