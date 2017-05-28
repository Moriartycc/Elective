package elective.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import data.element.Course;
import data.element.Student;
import data.environment.Environment;
import data.exception.CourseException;
import elective.Main;

import java.awt.Toolkit;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel1, panel2, panel3, panel4, panelScroll, panelTimetable;
	private JButton btn1, btn2, btn3;
	private JTable table, timeTable;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu Menu1 = new JMenu("操作(A)"), Menu2 = new JMenu("帮助(H)");
	private Student user;
	ArrayList<String> planSelectedList = new ArrayList<String>();
	private byte nowState = 0; // 0-选课计划, 1-预选列表, 2-已选列表
	private byte dispState = 0; //0-不显示显示, 1-显示

	public MainFrame(Student _user) {
		this.user = _user;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/sun/javafx/scene/web/skin/IncreaseIndent_16x16_JFX.png")));
		setTitle("课程管理 - " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
		new Timer(1000, new TimeControlListener(this)).start();
		setBounds(100, 100, 800, 600);
		setJMenuBar(menuBar);
		Menu1.setFont(new Font("宋体",Font.PLAIN, 12));
		Menu2.setFont(new Font("宋体",Font.PLAIN, 12));
		Menu1.setMnemonic('A');
		Menu2.setMnemonic('H');
		menuBar.add(Menu1);
		menuBar.add(Menu2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());
		setContentPane(contentPane);
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());
		panel3.setLayout(new BorderLayout());
		panel4.setLayout(new BorderLayout());
		panelScroll = new JPanel();
		panelScroll.setLayout(new BorderLayout());
		panelScroll.setBorder(BorderFactory.createTitledBorder(null, "课程列表", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		panelTimetable = new JPanel();
		panelTimetable.setLayout(new BorderLayout());
		panelTimetable.setBorder(BorderFactory.createTitledBorder(null, "课表", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));


		table = new JTable();
		timeTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		JScrollPane timeTablePanel = new JScrollPane(timeTable);
		panelTimetable.add(timeTablePanel, BorderLayout.CENTER);
		panelScroll.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(panel1, new GBC(0,0,7,1).setFill(GBC.BOTH).setWeight(5, 5));
		contentPane.add(panel3, new GBC(0,1,1,6).setFill(GBC.BOTH).setWeight(5, 100));
		contentPane.add(panelScroll, new GBC(1,1,5,5).setFill(GBC.BOTH).setWeight(100, 100));
		contentPane.add(panel4, new GBC(6,1,1,6).setFill(GBC.BOTH).setWeight(5, 100));
		contentPane.add(panel2, new GBC(1,6,5,1).setFill(GBC.BOTH).setWeight(5, 5));
		
		btn1 = new JButton("预选列表");
		btn2 = new JButton("已选列表");
		btn3 = new JButton("展开课表");
		panel2.add(btn1, BorderLayout.WEST);
		panel2.add(btn2, BorderLayout.EAST);
		panel2.add(new JLabel("西京大学选课系统 Version 0.2", JLabel.CENTER), BorderLayout.SOUTH);
		panel1.add(new JLabel("选课计划", JLabel.CENTER), BorderLayout.CENTER);
		panel1.add(btn3, BorderLayout.WEST);

		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"课程名",
				"",
				"课程类别",
				"学分",
				"教师",
				"班号",
				"开课单位",
				"课程时间",
				"限选/已选",
			}
		){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setWidth(0);
		table.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(7).setPreferredWidth(160);
		table.setEnabled(true);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				switch (nowState) {
				case 0:
					if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
						user.addPreselectedList((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
						renewList();
					} else if (SwingUtilities.isRightMouseButton(e)) {  
		                JPopupMenu popMenu = new JPopupMenu();  
		                JTable table = (JTable) e.getComponent();  

		                int row = table.rowAtPoint(e.getPoint());  
		                if (row == -1) {  
		                    return ;  
		                }  

		                int[] rows = table.getSelectedRows();  
		                boolean inSelected = false ;  
	
		                for(int r : rows){  
		                    if(row == r){  
		                        inSelected = true ;  
		                        break ;  
		                    }  
		                }  

		                if(!inSelected){  
		                    table.setRowSelectionInterval(row, row);  
		                }  

		                popMenu.add(new JMenuItem("详细信息"));  
		                ((JMenuItem) popMenu.getComponent(0)).addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								DetailedFrame detailedFrame = new DetailedFrame((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
								detailedFrame.setVisible(true);
								detailedFrame.setResizable(true);
							}
		                	
		                });
		                popMenu.show(e.getComponent(), e.getX(), e.getY());  
		            } 
					break;
				case 1:
					if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
						try {
							user.addSelectedList((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
							user.removePreselectedList((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
							renewList();
						} catch (CourseException ce) {
							JOptionPane.showMessageDialog(getContentPane(),"选课失败！" + ce.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
						}
					
					} else if (SwingUtilities.isRightMouseButton(e)) {  
		                JPopupMenu popMenu = new JPopupMenu();  
		                JTable table = (JTable) e.getComponent();  

		                int row = table.rowAtPoint(e.getPoint());  
		                if (row == -1) {  
		                    return ;  
		                }  

		                int[] rows = table.getSelectedRows();  
		                boolean inSelected = false ;  
	
		                for(int r : rows){  
		                    if(row == r){  
		                        inSelected = true ;  
		                        break ;  
		                    }  
		                }  

		                if(!inSelected){  
		                    table.setRowSelectionInterval(row, row);  
		                }  

		                popMenu.add(new JMenuItem("详细信息"));  
		                ((JMenuItem) popMenu.getComponent(0)).addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								DetailedFrame detailedFrame = new DetailedFrame((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
								detailedFrame.setVisible(true);
								detailedFrame.setResizable(true);
							}
		                	
		                });
		                popMenu.add(new JMenuItem("移出预选列表"));
		                ((JMenuItem) popMenu.getComponent(1)).addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								user.removePreselectedList((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
								renewList();
							}
		                	
		                });
		                popMenu.show(e.getComponent(), e.getX(), e.getY());  
		            } 
					break;
				case 2:
					if (SwingUtilities.isRightMouseButton(e)) {  
		                JPopupMenu popMenu = new JPopupMenu();  
		                JTable table = (JTable) e.getComponent();  

		                int row = table.rowAtPoint(e.getPoint());  
		                if (row == -1) {  
		                    return ;  
		                }  

		                int[] rows = table.getSelectedRows();  
		                boolean inSelected = false ;  
	
		                for(int r : rows){  
		                    if(row == r){  
		                        inSelected = true ;  
		                        break ;  
		                    }  
		                }  

		                if(!inSelected){  
		                    table.setRowSelectionInterval(row, row);  
		                }  

		                popMenu.add(new JMenuItem("详细信息"));  
		                ((JMenuItem) popMenu.getComponent(0)).addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								DetailedFrame detailedFrame = new DetailedFrame((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
								detailedFrame.setVisible(true);
								detailedFrame.setResizable(true);
							}
		                	
		                });
		                popMenu.add(new JMenuItem("移出已选列表"));
		                ((JMenuItem) popMenu.getComponent(1)).addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								user.addPreselectedList((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
								user.removeSelectedList((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
								renewList();
							}
		                	
		                });
		                popMenu.show(e.getComponent(), e.getX(), e.getY());  
		            } 
					break;
				}
			}
		});

		timeTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"周一",
						"周二",
						"周三",
						"周四",
						"周五",
						"周六",
						"周日",
				}
		){
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (nowState) {
				case 0:
					nowState = 1;
					((JLabel) panel1.getComponent(0)).setText("预选列表");
					btn1.setText("选课计划");
					btn2.setText("已选列表");
					renewList();
					break;
				case 1:
					nowState = 0;
					((JLabel) panel1.getComponent(0)).setText("选课计划");
					btn1.setText("预选列表");
					btn2.setText("已选列表");
					renewList();
					break;
				case 2:
					nowState = 0;
					((JLabel) panel1.getComponent(0)).setText("选课计划");
					btn1.setText("预选列表");
					btn2.setText("已选列表");
					renewList();
					break;
				}
				
			}
			
		});
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (nowState) {
				case 0:
					nowState = 2;
					((JLabel) panel1.getComponent(0)).setText("已选列表");
					btn1.setText("选课计划");
					btn2.setText("预选列表");
					renewList();
					break;
				case 1:
					nowState = 2;
					((JLabel) panel1.getComponent(0)).setText("已选列表");
					btn1.setText("选课计划");
					btn2.setText("预选列表");
					renewList();
					break;
				case 2:
					nowState = 1;
					((JLabel) panel1.getComponent(0)).setText("预选列表");
					btn1.setText("选课计划");
					btn2.setText("已选列表");
					renewList();
					break;
				}
				
			}
			
		});
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(dispState){
					case 0:
						dispState = 1;
						btn3.setText("收起课表");
						setSize(1200, 600);
						contentPane.add(panelTimetable, new GBC(8,1,5,5).setFill(GBC.BOTH).setWeight(120, 100));
						break;
					case 1:
						dispState = 0;
						btn3.setText("展开课表");
						setSize(800, 600);
						contentPane.remove(5);
						break;
				}
//				JOptionPane.showMessageDialog(MainFrame.this.getContentPane(), "-1s", "错误", 0);
				return;
			}
		});
		renewList();
	}
	
	void renewList() {
		File dir = new File(Environment.coursePath);
		File[] files = dir.listFiles();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
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
	        
	        switch (nowState) {
		        case 0: 
		        	if (!(this.user.getSelected().contains(course.getCourseID()) || (this.user.getPreselected().contains(course.getCourseID())))) {
		        	    arr.add(course.getName());
		        	    arr.add(course.getCourseID());
						arr.add(course.getType());
						arr.add(course.getCredit());
						arr.add(course.getTeacherName());
						arr.add(course.getClassID());
						arr.add(course.getDepartment());
						arr.add(course.getClassTimeString());
						arr.add(Integer.toString(course.getCurNumber()) + '/' + Integer.toString(course.getMaxNumber()));
						tableModel.addRow(arr);
			        }
		        	break;
		        case 1:
		        	if (this.user.getPreselected().contains(course.getCourseID())) {
		        	    arr.add(course.getName());
		        	    arr.add(course.getCourseID());
						arr.add(course.getType());
						arr.add(course.getCredit());
						arr.add(course.getTeacherName());
						arr.add(course.getClassID());
						arr.add(course.getDepartment());
						arr.add(course.getClassTimeString());
						arr.add(Integer.toString(course.getCurNumber()) + '/' + Integer.toString(course.getMaxNumber()));
						tableModel.addRow(arr);
			        }
		        	break;
		        case 2:
		        	if (this.user.getSelected().contains(course.getCourseID())) {
		        	    arr.add(course.getName());
		        	    arr.add(course.getCourseID());
						arr.add(course.getType());
						arr.add(course.getCredit());
						arr.add(course.getTeacherName());
						arr.add(course.getClassID());
						arr.add(course.getDepartment());
						arr.add(course.getClassTimeString());
						arr.add(Integer.toString(course.getCurNumber()) + '/' + Integer.toString(course.getMaxNumber()));
						tableModel.addRow(arr);
			        }
		        	break;
	        }
		}
	
		tableModel.fireTableDataChanged();
	}
	
	private class GBC extends GridBagConstraints  
	{  
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	//��ʼ�����Ͻ�λ��  
	   public GBC(int gridx, int gridy)  
	   {  
	      this.gridx = gridx;  
	      this.gridy = gridy;  
	   }  
	  
	   //��ʼ�����Ͻ�λ�ú���ռ����������  
	   public GBC(int gridx, int gridy, int gridwidth, int gridheight)  
	   {  
	      this.gridx = gridx;  
	      this.gridy = gridy;  
	      this.gridwidth = gridwidth;  
	      this.gridheight = gridheight;  
	   }  
	  
	   //���뷽ʽ  
	   public GBC setAnchor(int anchor)  
	   {  
	      this.anchor = anchor;  
	      return this;  
	   }  
	  
	   //�Ƿ����켰���췽��  
	   public GBC setFill(int fill)  
	   {  
	      this.fill = fill;  
	      return this;  
	   }  
	  
	   //x��y�����ϵ�����  
	   public GBC setWeight(double weightx, double weighty)  
	   {  
	      this.weightx = weightx;  
	      this.weighty = weighty;  
	      return this;  
	   }  
	  
	   //�ⲿ���  
	   public GBC setInsets(int distance)  
	   {  
	      this.insets = new Insets(distance, distance, distance, distance);  
	      return this;  
	   }  
	  
	   //�����  
	   public GBC setInsets(int top, int left, int bottom, int right)  
	   {  
	      this.insets = new Insets(top, left, bottom, right);  
	      return this;  
	   }  
	  
	   //�����  
	   public GBC setIpad(int ipadx, int ipady)  
	   {  
	      this.ipadx = ipadx;  
	      this.ipady = ipady;  
	      return this;  
	   }  
	}

	private class TimeControlListener implements ActionListener {
		JFrame ref;
		
		TimeControlListener(JFrame _ref) {
			ref = _ref;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ref.setTitle("课程管理 - " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
		}
		
	}
}
