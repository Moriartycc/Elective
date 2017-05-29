package elective;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import data.element.Student;
import data.environment.Environment;
import elective.frame.MainFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		setTitle("西京大学选课系统 Ver 0.2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 331);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		JButton button1 = new JButton("登陆");
		button1.addActionListener(new Button1Handler(this));
		button1.setBounds(148, 194, 154, 27);
		contentPane.add(button1);
		
		JLabel lblVersion = new JLabel("西京大学选课系统 Version 0.2");
		lblVersion.setFont(new Font("宋体", Font.PLAIN, 15));
		lblVersion.setBounds(117, 253, 223, 18);
		contentPane.add(lblVersion);
		
		JLabel label1 = new JLabel("学号:");
		label1.setBounds(72, 58, 73, 18);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("密码:");
		label2.setBounds(72, 98, 73, 18);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("验证码:");
		label3.setBounds(72, 138, 73, 18);
		contentPane.add(label3);
		
		textField1 = new JTextField();
		textField1.setBounds(148, 55, 192, 24);
		textField1.setColumns(10);
		textField1.addKeyListener(new KeyHandler(this));
		contentPane.add(textField1);
		
		textField2 = new JPasswordField();
		textField2.setColumns(10);
		textField2.setBounds(148, 95, 192, 24);
		textField2.addKeyListener(new KeyHandler(this));
		contentPane.add(textField2);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(148, 135, 192, 24);
		textField3.addKeyListener(new KeyHandler(this));
		contentPane.add(textField3);
		
	}
	
	private class Button1Handler implements ActionListener
	{
		private Main ref;
		
		Button1Handler(Main _ref) {
			ref = _ref;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Student user = null;
			if (!textField2.getText().equals("123")) {
				JOptionPane.showMessageDialog(getContentPane(),"密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				user = login(textField1.getText());
			} catch (FileNotFoundException _e) {
				JOptionPane.showMessageDialog(getContentPane(),"该学号不存在，请重试！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JFrame mainFrame = new MainFrame(user);
			mainFrame.setVisible(true);
			mainFrame.setResizable(true);
			ref.dispose();
		}

	}
	
	private class KeyHandler implements KeyListener {
		
		private Main ref;
		
		KeyHandler(Main _ref) {
			ref = _ref;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() != KeyEvent.VK_ENTER) return;
			Student user = null;
			if (!textField2.getText().equals("123")) {
				JOptionPane.showMessageDialog(getContentPane(),"密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				user = login(textField1.getText());
			} catch (FileNotFoundException _e) {
				JOptionPane.showMessageDialog(getContentPane(),"该学号不存在，请重试！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JFrame mainFrame = new MainFrame(user);
			mainFrame.setVisible(true);
			mainFrame.setResizable(true);
			ref.dispose();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
