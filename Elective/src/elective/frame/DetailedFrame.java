package elective.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.google.gson.Gson;

import data.element.Course;
import data.environment.Environment;
import data.exception.CourseException;

public class DetailedFrame extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public DetailedFrame(String _course) {
		File cur = new File(Environment.coursePath + _course + ".json");
		
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
        
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/sun/javafx/scene/web/skin/IncreaseIndent_16x16_JFX.png")));
		setTitle("详细信息 - " + course.getName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(new JLabel("课程详细信息 - " + course.getName() + "(" + course.getCourseID() + ")", JLabel.CENTER), BorderLayout.NORTH);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel subPanelName = new JPanel();
		subPanelName.setBorder(BorderFactory.createTitledBorder(null, "课程名称", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelName.setLayout(new BorderLayout());
		subPanelName.add(new JLabel(course.getName(), JLabel.CENTER), BorderLayout.CENTER);
		
		JPanel subPanelCourseID = new JPanel();
		subPanelCourseID.setBorder(BorderFactory.createTitledBorder(null, "课程编号", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelCourseID.setLayout(new BorderLayout());
		subPanelCourseID.add(new JLabel(course.getCourseID(), JLabel.CENTER), BorderLayout.CENTER);
		
		JPanel subPanelType = new JPanel();
		subPanelType.setBorder(BorderFactory.createTitledBorder(null, "课程类型", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelType.setLayout(new BorderLayout());
		subPanelType.add(new JLabel(course.getType(), JLabel.CENTER), BorderLayout.CENTER);
		
		JPanel subPanelTeacherName = new JPanel();
		subPanelTeacherName.setBorder(BorderFactory.createTitledBorder(null, "授课教师", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelTeacherName.setLayout(new BorderLayout());
		subPanelTeacherName.add(new JLabel(course.getTeacherName(), JLabel.CENTER), BorderLayout.CENTER);
		
		JPanel subPanelDepartment = new JPanel();
		subPanelDepartment.setBorder(BorderFactory.createTitledBorder(null, "开课院系", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelDepartment.setLayout(new BorderLayout());
		subPanelDepartment.add(new JLabel(course.getDepartment(), JLabel.CENTER), BorderLayout.CENTER);
		
		JPanel subPanelCredit = new JPanel();
		subPanelCredit.setBorder(BorderFactory.createTitledBorder(null, "课程学分", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelCredit.setLayout(new BorderLayout());
		subPanelCredit.add(new JLabel(Integer.toString(course.getCredit()), JLabel.CENTER), BorderLayout.CENTER);
		
		JPanel subPanelClassID = new JPanel();
		subPanelClassID.setBorder(BorderFactory.createTitledBorder(null, "班号", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelClassID.setLayout(new BorderLayout());
		subPanelClassID.add(new JLabel(Integer.toString(course.getClassID()), JLabel.CENTER), BorderLayout.CENTER);
		
		JPanel subPanelClassTime = new JPanel();
		subPanelClassTime.setBorder(BorderFactory.createTitledBorder(null, "授课时间", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelClassTime.setLayout(new BorderLayout());
		subPanelClassTime.add(new JLabel(course.getClassTimeString(), JLabel.CENTER), BorderLayout.CENTER);
		
		JPanel subPanelInformation = new JPanel();
		subPanelInformation.setBorder(BorderFactory.createTitledBorder(null, "详细介绍", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.PLAIN, 12), new Color(51, 51, 51)));
		subPanelInformation.setLayout(new BorderLayout());
		JTextArea informationText = new JTextArea(course.getInformation());
		informationText.setEditable(false);
		informationText.setLineWrap(true);
		informationText.setWrapStyleWord(true);
		informationText.setOpaque(false);
		informationText.setFont(((JLabel) subPanelName.getComponent(0)).getFont());
		subPanelInformation.add(informationText, BorderLayout.CENTER);
		
		panel.add(subPanelName, new GBC(0,0,3,1).setFill(GBC.BOTH).setWeight(100, 5));
		panel.add(subPanelCourseID, new GBC(3,0,2,1).setFill(GBC.BOTH).setWeight(100, 5));
		panel.add(subPanelType, new GBC(0,1,1,1).setFill(GBC.BOTH).setWeight(100, 5));
		panel.add(subPanelTeacherName, new GBC(1,1,1,1).setFill(GBC.BOTH).setWeight(100, 5));
		panel.add(subPanelCredit, new GBC(2,1,1,1).setFill(GBC.BOTH).setWeight(100, 5));
		panel.add(subPanelDepartment, new GBC(3,1,2,1).setFill(GBC.BOTH).setWeight(100, 5));
		panel.add(subPanelClassID , new GBC(0,2,1,1).setFill(GBC.BOTH).setWeight(100, 5));
		panel.add(subPanelClassTime, new GBC(1,2,4,1).setFill(GBC.BOTH).setWeight(100, 5));
		panel.add(subPanelInformation, new GBC(0,3,5,2).setFill(GBC.BOTH).setWeight(100, 100));
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
