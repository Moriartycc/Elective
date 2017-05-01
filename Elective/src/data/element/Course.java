package data.element;

import java.util.*;

public class Course {
	private String name, courseID, type, teacherName, department;
	private int credit, classID, curNumber, maxNumber;
	private ArrayList<Integer> classTime;
	
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
	
	public void setClassTime(ArrayList<Integer> classTime) {
		this.classTime = classTime;
	}	
}
