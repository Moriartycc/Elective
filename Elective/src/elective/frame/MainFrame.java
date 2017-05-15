package elective.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JList;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import data.element.Course;
import data.element.Student;
import data.environment.Environment;

import java.awt.Toolkit;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainFrame ccb = this;
	private JPanel contentPane1;
	private JTable table1, table2, table3;
	private JLabel label2;
	private JLabel label3;
	private Student user;
	public MainFrame(Student _user) {
		this.user = _user;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/sun/javafx/scene/web/skin/IncreaseIndent_16x16_JFX.png")));
		setTitle("\u8BFE\u7A0B\u7BA1\u7406");
		setBounds(100, 100, 1900, 982);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(null);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(14, 75, 922, 405);
		contentPane1.add(scrollPane1);
		
		table1 = new JTable();
		scrollPane1.setViewportView(table1);
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"课程名",
				"课程号",
				"课程类别",
				"学分",
				"教师",
				"班号",
				"开课单位",
				"课程时间",
				"限选/已选",
				"添加预选",
				"删除"
			}
		){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override  
            public boolean isCellEditable(int row,int column){  
                if (column < 9) return false; else return true;
			}
		});
		table1.setEnabled(true);
		table1.getColumnModel().getColumn(0).setPreferredWidth(100);
		table1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table1.getColumnModel().getColumn(2).setPreferredWidth(60);
		table1.getColumnModel().getColumn(3).setPreferredWidth(30);
		table1.getColumnModel().getColumn(4).setPreferredWidth(60);
		table1.getColumnModel().getColumn(5).setPreferredWidth(30);
		table1.getColumnModel().getColumn(6).setPreferredWidth(100);
		table1.getColumnModel().getColumn(7).setPreferredWidth(100);
		table1.getColumnModel().getColumn(8).setPreferredWidth(60);
		table1.getColumnModel().getColumn(9).setPreferredWidth(50);
		table1.getColumnModel().getColumn(10).setPreferredWidth(50);
		TableEvent e1 = new TableEvent() {

			@Override
			public void invoke(ActionEvent e) {
				TableButton button = (TableButton)e.getSource();
				user.addPreselectedList((String) table1.getModel().getValueAt(button.getRow(), 1));
				renewList1();
				renewList2();
			}
			
		};
 		table1.getColumnModel().getColumn(9).setCellRenderer(new TableButtonRender("添加"));
		TableButtonEditor editor1 = new TableButtonEditor(e1, "添加");
		table1.getColumnModel().getColumn(9).setCellEditor(editor1);
		
		TableEvent e2 = new TableEvent() {

			@Override
			public void invoke(ActionEvent e) {
				
			}
			
		};
 		table1.getColumnModel().getColumn(10).setCellRenderer(new TableButtonRender("删除"));
		TableButtonEditor editor2 = new TableButtonEditor(e2, "删除");
		table1.getColumnModel().getColumn(10).setCellEditor(editor2);
		renewList1();
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(946, 75, 922 , 405);
		contentPane1.add(scrollPane2);
		
		table2 = new JTable();
		scrollPane2.setViewportView(table2);
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"课程名",
				"课程号",
				"课程类别",
				"学分",
				"教师",
				"班号",
				"开课单位",
				"课程时间",
				"限选/已选",
				"选课",
				"删除"
			}
		){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override  
            public boolean isCellEditable(int row,int column){  
                if (column < 9) return false; else return true;
            }
		});
		table2.setEnabled(true);
		table2.getColumnModel().getColumn(0).setPreferredWidth(100);
		table2.getColumnModel().getColumn(1).setPreferredWidth(100);
		table2.getColumnModel().getColumn(2).setPreferredWidth(60);
		table2.getColumnModel().getColumn(3).setPreferredWidth(30);
		table2.getColumnModel().getColumn(4).setPreferredWidth(60);
		table2.getColumnModel().getColumn(5).setPreferredWidth(30);
		table2.getColumnModel().getColumn(6).setPreferredWidth(100);
		table2.getColumnModel().getColumn(7).setPreferredWidth(100);
		table2.getColumnModel().getColumn(8).setPreferredWidth(60);
		table2.getColumnModel().getColumn(9).setPreferredWidth(50);
		table2.getColumnModel().getColumn(10).setPreferredWidth(50);
		e1 = new TableEvent() {

			@Override
			public void invoke(ActionEvent e) {
				
			}
			
		};
 		table2.getColumnModel().getColumn(9).setCellRenderer(new TableButtonRender("选课"));
		editor1 = new TableButtonEditor(e1, "选课");
		table2.getColumnModel().getColumn(9).setCellEditor(editor1);
		
		e2 = new TableEvent() {

			@Override
			public void invoke(ActionEvent e) {
				TableButton button = (TableButton)e.getSource();
				user.removePreselectedList((String) table2.getModel().getValueAt(button.getRow(), 1));
				renewList1();
				renewList2();
			}
			
		};
 		table2.getColumnModel().getColumn(10).setCellRenderer(new TableButtonRender("删除"));
		editor2 = new TableButtonEditor(e2, "删除");
		table2.getColumnModel().getColumn(10).setCellEditor(editor2);
		renewList2();
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(14, 517, 922 , 405);
		contentPane1.add(scrollPane3);
		
		table3 = new JTable();
		scrollPane3.setViewportView(table3);
		table3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"课程名",
				"课程号",
				"课程类别",
				"学分",
				"教师",
				"班号",
				"开课单位",
				"课程时间",
				"限选/已选",
				"退课"
			}
		){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override  
            public boolean isCellEditable(int row,int column){  
                if (column < 9) return false; else return true;
            }
		});
		table3.setEnabled(true);
		
		JLabel label1 = new JLabel("\u7B5B\u9009\u8BFE\u7A0B");
		label1.setBounds(14, 44, 72, 18);
		contentPane1.add(label1);
		
		label2 = new JLabel("\u9884\u9009\u5217\u8868");
		label2.setBounds(946, 44, 72, 18);
		contentPane1.add(label2);
		
		label3 = new JLabel("\u5DF2\u9009\u5217\u8868");
		label3.setBounds(14, 493, 72, 18);
		contentPane1.add(label3);
		table3.getColumnModel().getColumn(0).setPreferredWidth(100);
		table3.getColumnModel().getColumn(1).setPreferredWidth(100);
		table3.getColumnModel().getColumn(2).setPreferredWidth(60);
		table3.getColumnModel().getColumn(3).setPreferredWidth(30);
		table3.getColumnModel().getColumn(4).setPreferredWidth(60);
		table3.getColumnModel().getColumn(5).setPreferredWidth(30);
		table3.getColumnModel().getColumn(6).setPreferredWidth(100);
		table3.getColumnModel().getColumn(7).setPreferredWidth(100);
		table3.getColumnModel().getColumn(8).setPreferredWidth(60);
		table3.getColumnModel().getColumn(9).setPreferredWidth(50);
		renewList3();
	}
	
	void renewList1() {
		File dir = new File(Environment.coursePath);
		File[] files = dir.listFiles();
		DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
		tableModel.setRowCount(0);
		for (File cur : files) {
			Vector<Object> arr = new Vector<Object>();
			
	        Scanner scanner = null;
	        StringBuilder buffer = new StringBuilder();
	        try {
	            scanner = new Scanner(cur, "utf-8");
	            while (scanner.hasNextLine()) {
	                buffer.append(scanner.nextLine());
	            }
	 
	        } catch (FileNotFoundException e) { 
	 
	        } finally {
	            if (scanner != null) {
	                scanner.close();
	            }
	        }
	         
	        Gson gson = new Gson();
	        Course course = gson.fromJson(buffer.toString(), Course.class); 
	        if (!(this.user.getSelected().contains(course.getCourseID()) || (this.user.getPreselected().contains(course.getCourseID())))) {
				arr.add(course.getName());
				arr.add(course.getCourseID());
				arr.add(course.getType());
				arr.add(course.getCredit());
				arr.add(course.getTeacherName());
				arr.add(course.getClassID());
				arr.add(course.getDepartment());
				arr.add(course.getClassTime().toString());
				arr.add(Integer.toString(course.getCurNumber()) + '/' + Integer.toString(course.getMaxNumber()));
				tableModel.addRow(arr);
	        }
		}
	
		table1.validate();
		table1.updateUI();
	}
	
	void renewList2() {
		DefaultTableModel tableModel = (DefaultTableModel) table2.getModel();
		tableModel.setRowCount(0);
		for (String courseName : user.getPreselected()) {
			File cur = new File(Environment.coursePath + courseName + ".json");
			Vector<Object> arr = new Vector<Object>();
			
	        Scanner scanner = null;
	        StringBuilder buffer = new StringBuilder();
	        try {
	            scanner = new Scanner(cur, "utf-8");
	            while (scanner.hasNextLine()) {
	                buffer.append(scanner.nextLine());
	            }
	 
	        } catch (FileNotFoundException e) { 
	 
	        } finally {
	            if (scanner != null) {
	                scanner.close();
	            }
	        }
	         
	        Gson gson = new Gson();
	        Course course = gson.fromJson(buffer.toString(), Course.class); 
	        arr.add(course.getName());
	        arr.add(course.getCourseID());
			arr.add(course.getType());
			arr.add(course.getCredit());
			arr.add(course.getTeacherName());
			arr.add(course.getClassID());
			arr.add(course.getDepartment());
			arr.add(course.getClassTime().toString());
			arr.add(Integer.toString(course.getCurNumber()) + '/' + Integer.toString(course.getMaxNumber()));
			tableModel.addRow(arr);
		}
	
		table2.validate();
		table2.updateUI();
	}
	
	void renewList3() {
		DefaultTableModel tableModel = (DefaultTableModel) table3.getModel();
		tableModel.setRowCount(0);
		for (String courseName : user.getSelected()) {
			File cur = new File(Environment.coursePath + courseName + ".json");
			Vector<Object> arr = new Vector<Object>();
			
	        Scanner scanner = null;
	        StringBuilder buffer = new StringBuilder();
	        try {
	            scanner = new Scanner(cur, "utf-8");
	            while (scanner.hasNextLine()) {
	                buffer.append(scanner.nextLine());
	            }
	 
	        } catch (FileNotFoundException e) { 
	 
	        } finally {
	            if (scanner != null) {
	                scanner.close();
	            }
	        }
	         
	        Gson gson = new Gson();
	        Course course = gson.fromJson(buffer.toString(), Course.class); 
	        arr.add(course.getName());
	        arr.add(course.getCourseID());
			arr.add(course.getType());
			arr.add(course.getCredit());
			arr.add(course.getTeacherName());
			arr.add(course.getClassID());
			arr.add(course.getDepartment());
			arr.add(course.getClassTime().toString());
			arr.add(Integer.toString(course.getCurNumber()) + '/' + Integer.toString(course.getMaxNumber()));
			tableModel.addRow(arr);
		}
		table3.validate();
		table3.updateUI();
	}
}
