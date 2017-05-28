package data.element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.google.gson.Gson;

import data.environment.Environment;
import data.exception.CourseException;

public class Course {
	private static String[] dayOfWeek = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
	private String name, courseID, type, teacherName, department;
	private int credit, classID, curNumber, maxNumber;
	private ArrayList<Integer> classTime;
	private String information;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCourseID() {
		return courseID;
	}
	
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTeacherName() {
		return teacherName;
	}
	
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public String getInformation() {
		return information;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public int getClassID() {
		return classID;
	}
	
	public void setClassID(int classID) {
		this.classID = classID;
	}
	
	public int getCurNumber() {
		return curNumber;
	}
	
	public void setCurNumber(int curNumber) {
		this.curNumber = curNumber;
	}
	
	public int getMaxNumber() {
		return maxNumber;
	}
	
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	
	public ArrayList<Integer> getClassTime() {
		return classTime;
	}
	
	public String getClassTimeString() {
		StringBuilder ret = new StringBuilder();
		int nweek = 0, ncounter = 0, last = 0;
		for (int i : classTime) {
			if (i / 12 + 1 > nweek) {
				if (ncounter > 0) ret.append(last + "; ");
				ncounter++;
				nweek = i / 12 + 1;
				ret.append(dayOfWeek[nweek - 1] + (i % 12 + 1) + '~');
			}
			last = i % 12 + 1;
		}
		ret.append(last + ".");
		return ret.toString();
	}
	
	public void setClassTime(ArrayList<Integer> classTime) {
		this.classTime = classTime;
	}	
	
	private void rewriteData() {
		Gson gson = new Gson();
		FileOutputStream file = null;
		try {
			file = new FileOutputStream(Environment.coursePath + courseID + ".json",false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			file.write(gson.toJson(this).toString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void withdraw(String _course) {
		System.out.println(_course);
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
        course.curNumber--;
        course.rewriteData();
	}
	
	public static void select(String _course, Student _stu) throws CourseException {
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
        if (course.curNumber == course.maxNumber) throw new CourseException("课程人数已满！");
        if (course.credit + _stu.getCurCredit() > _stu.getMaxCredit()) throw new CourseException("学分已满！");
        boolean[] curTime = _stu.getCurTime();
        for (int i : course.classTime) {
        	if (curTime[i]) throw new CourseException("时间冲突！");
        }
        course.curNumber++;
        course.rewriteData();
	}
}
