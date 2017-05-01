package elective;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import data.element.Student;
import data.environment.Environment;
import elective.frame.MainFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Toolkit;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Student login(String _ID) throws FileNotFoundException {
		String fullFileName = Environment.studentPath+_ID+".json";
        
        File file = new File(fullFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
 
        } catch (FileNotFoundException e) {
            throw e; 
 
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
         
        Gson gson = new Gson();
        
        return gson.fromJson(buffer.toString(), Student.class); 
	}
	/**
	 * Create the frame.
	 */
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/sun/javafx/scene/web/skin/OrderedListNumbers_16x16_JFX.png")));
		setTitle("\u897F\u4EAC\u5927\u5B66\u9009\u8BFE\u7CFB\u7EDF Ver 0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button1 = new JButton("\u767B\u5F55");
		button1.addActionListener(new Button1Handler());
		button1.setBounds(148, 194, 154, 27);
		contentPane.add(button1);
		
		JLabel lblVersion = new JLabel("\u897F\u4EAC\u5927\u5B66\u9009\u8BFE\u7CFB\u7EDF Version 0.1");
		lblVersion.setFont(new Font("宋体", Font.PLAIN, 15));
		lblVersion.setBounds(117, 253, 223, 18);
		contentPane.add(lblVersion);
		
		JLabel label1 = new JLabel("\u5B66\u53F7\uFF1A");
		label1.setBounds(72, 58, 73, 18);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("\u5BC6\u7801\uFF1A");
		label2.setBounds(72, 98, 73, 18);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
		label3.setBounds(72, 138, 73, 18);
		contentPane.add(label3);
		
		textField1 = new JTextField();
		textField1.setBounds(148, 55, 192, 24);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(148, 95, 192, 24);
		contentPane.add(textField2);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(148, 135, 192, 24);
		contentPane.add(textField3);
	}
	
	private class Button1Handler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Student user = null;
			try {
				user = login(textField1.getText());
			} catch (FileNotFoundException _e) {
				JOptionPane.showMessageDialog(getContentPane(),"该学号不存在，请重试！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JFrame mainFrame = new MainFrame(user);
			mainFrame.setVisible(true);
			mainFrame.setResizable(false);
		}
	}
}
