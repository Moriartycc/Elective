package elective.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class FilterFrame extends JFrame {

	private JPanel contentPane;
	private MainFrame mainFrame;
	private String[] typeList = {"无", "专业课", "任选", "通选课", "大类平台课", "英语课", "体育课", "思想教育与政治课"};
	private String[] departmentList = {"无", "数学科学学院", "信息科学技术学院", "物理学院", "化学与分子工程学院", "工学院"};
	private ButtonGroup group1, group2;
	private JPanel panel;
	private JRadioButton radioButton1, radioButton2, radioButton3, radioButton4;
	/**
	 * Create the frame.
	 */
	public FilterFrame(MainFrame _mainFrame) {
		mainFrame = _mainFrame;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/sun/javafx/scene/web/skin/IncreaseIndent_16x16_JFX.png")));
		setTitle("高级筛选信息");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		contentPane.add(new JLabel("高级筛选信息设置", JLabel.CENTER), BorderLayout.NORTH);
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel subPanelName = new JPanel();
		subPanelName.setBorder(BorderFactory.createTitledBorder(null, "课程名称", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelName.setLayout(new BorderLayout());
		subPanelName.add(new JTextField(10), BorderLayout.CENTER);
		
		JPanel subPanelCourseID = new JPanel();
		subPanelCourseID.setBorder(BorderFactory.createTitledBorder(null, "课程编号", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelCourseID.setLayout(new BorderLayout());
		subPanelCourseID.add(new JTextField(10), BorderLayout.CENTER);
		
		JPanel subPanelType = new JPanel();
		subPanelType.setBorder(BorderFactory.createTitledBorder(null, "课程类型", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelType.setLayout(new BorderLayout());
		JComboBox comboBoxType = new JComboBox(typeList);
		comboBoxType.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				mainFrame.renewTable();
			}
			
		});
		subPanelType.add(comboBoxType, BorderLayout.CENTER);
		
		JPanel subPanelTeacherName = new JPanel();
		subPanelTeacherName.setBorder(BorderFactory.createTitledBorder(null, "授课教师", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelTeacherName.setLayout(new BorderLayout());
		subPanelTeacherName.add(new JTextField(10), BorderLayout.CENTER);
		
		JPanel subPanelDepartment = new JPanel();
		subPanelDepartment.setBorder(BorderFactory.createTitledBorder(null, "开课院系", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelDepartment.setLayout(new BorderLayout());
		JComboBox comboBoxDepartment = new JComboBox(departmentList);
		comboBoxDepartment.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				mainFrame.renewTable();
			}
			
		});
		subPanelDepartment.add(comboBoxDepartment, BorderLayout.CENTER);
		
		JPanel subPanelClassID = new JPanel();
		subPanelClassID.setBorder(BorderFactory.createTitledBorder(null, "班号", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelClassID.setLayout(new BorderLayout());
		subPanelClassID.add(new JTextField(10), BorderLayout.CENTER);
		
		JPanel subPanelClassTime = new JPanel();
		subPanelClassTime.setBorder(BorderFactory.createTitledBorder(null, "是否可以与已选课程冲突", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelClassTime.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		radioButton1 = new JRadioButton("是");
		radioButton2 = new JRadioButton("否");
		radioButton2.setSelected(true);
		radioButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.renewTable();
			}
			
		});
		radioButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.renewTable();
			}
			
		});
		group1 = new ButtonGroup();
		group1.add(radioButton1);
		group1.add(radioButton2);
		subPanelClassTime.add(radioButton1);
		subPanelClassTime.add(radioButton2);
		
		JPanel subPanelActivation = new JPanel();
		subPanelActivation.setBorder(BorderFactory.createTitledBorder(null, "是否开启此功能", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelActivation.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		radioButton3 = new JRadioButton("是");
		radioButton4 = new JRadioButton("否");
		radioButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.renewTable();
			}
			
		});
		radioButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.renewTable();
			}
			
		});
		radioButton4.setSelected(true);
		
		group2 = new ButtonGroup();
		group2.add(radioButton3);
		group2.add(radioButton4);
		subPanelActivation.add(radioButton3);
		subPanelActivation.add(radioButton4);
		
		JPanel subPanelHint = new JPanel();
		subPanelHint.setBorder(BorderFactory.createTitledBorder(null, "提示", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelHint.setLayout(new BorderLayout());
		subPanelHint.add(new JLabel("您可以配合普通的信息筛选框进行时间筛选，例如在该框中输入“星期一1~2.”。", JLabel.CENTER), BorderLayout.CENTER);
		
		panel.add(subPanelName, new GBC(0,0,2,1).setFill(GBC.BOTH).setWeight(100, 0));
		panel.add(subPanelCourseID, new GBC(2,0,2,1).setFill(GBC.BOTH).setWeight(100, 0));
		panel.add(subPanelType, new GBC(0,1,1,1).setFill(GBC.BOTH).setWeight(100, 0));
		panel.add(subPanelTeacherName, new GBC(1,1,1,1).setFill(GBC.BOTH).setWeight(100, 0));
		panel.add(subPanelDepartment, new GBC(2,1,1,1).setFill(GBC.BOTH).setWeight(100, 0));
		panel.add(subPanelClassID , new GBC(3,1,1,1).setFill(GBC.BOTH).setWeight(100, 0));
		panel.add(subPanelClassTime, new GBC(0,2,2,1).setFill(GBC.BOTH).setWeight(100, 0));
		panel.add(subPanelActivation, new GBC(2,2,2,1).setFill(GBC.BOTH).setWeight(100, 0));
		panel.add(subPanelHint, new GBC(0,3,4,1).setFill(GBC.BOTH).setWeight(100, 100));
	}
	
	@Override
	public boolean hasFocus() {
		boolean ret = false;
		ret = ((JTextField) ((JPanel) panel.getComponent(0)).getComponent(0)).hasFocus() ||
				((JTextField) ((JPanel) panel.getComponent(1)).getComponent(0)).hasFocus() ||
				((JTextField) ((JPanel) panel.getComponent(3)).getComponent(0)).hasFocus() ||
				((JTextField) ((JPanel) panel.getComponent(5)).getComponent(0)).hasFocus();
		return ret;
	}
	
	public boolean isActivated() {
		return radioButton3.isSelected();
	}
	
	public boolean isDifferentFromSelected() {
		return radioButton1.isSelected();
	}
	
	public String getName() {
		return ((JTextField) ((JPanel) panel.getComponent(0)).getComponent(0)).getText();
	}
	
	public String getCourseID() {
		return ((JTextField) ((JPanel) panel.getComponent(1)).getComponent(0)).getText();
	}
	
	public String getCourseType() {
		String ret = (String) ((JComboBox) ((JPanel) panel.getComponent(2)).getComponent(0)).getSelectedItem();
		if (ret == "无") return ""; else return ret;
	}
	
	public String getTeacherName() {
		return ((JTextField) ((JPanel) panel.getComponent(3)).getComponent(0)).getText();
	}
	
	public String getDepartment() {
		String ret = (String) ((JComboBox) ((JPanel) panel.getComponent(4)).getComponent(0)).getSelectedItem();
		if (ret == "无") return ""; else return ret;
	}
	
	public String getClassID() {
		return ((JTextField) ((JPanel) panel.getComponent(5)).getComponent(0)).getText();
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
}
