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

public class Student {
	private String name, studentID, department;
	private char sex;
	private int year, maxCredit;
	private ArrayList<String> preselected, selected;
	
	public String getName() {
		return name;
	}
	
	public String getStudentID() {
		return studentID;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public char getSex() {
		return sex;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMaxCredit() {
		return maxCredit;
	}
	
	public int getCurCredit() {
		int ret = 0;
		
		for (String _course : selected) {
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
	        ret += course.getCredit();
		}
		return ret;
	}
	
	public boolean[] getCurTime() {
		boolean[] ret = new boolean[84];
		for (int i = 0; i < 84; i++) ret[i] = false; //未占用
		
		for (String _course : selected) {
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
	        for (int i : course.getClassTime()) ret[i] = true;
		}
		return ret;
	}

	public ArrayList<String> getPreselected() {
		return preselected;
	}
	
	public ArrayList<String> getSelected() {
		return selected;
	}
	
	void setName(String _name) {
		name = _name;
	}
	
	void setStudentID(String _studentID) {
		studentID = _studentID;
	}
	
	void setDepartment(String _department) {
		department = _department;
	}
	
	void setSex(char _sex) {
		sex = _sex;
	}
	
	void setYear(int _year) {
		year = _year;
	}
	
	void setMaxCredit(int _maxCredit) {
		maxCredit = _maxCredit;
	}
	
	void setPreselected(ArrayList<String> _preselected) {
		preselected = new ArrayList<String>(_preselected);
	}
	
	void setSelected(ArrayList<String> _selected) {
		selected = new ArrayList<String>(_selected);
	}
	
	private void rewriteData() {
		Gson gson = new Gson();
		FileOutputStream file = null;
		try {
			file = new FileOutputStream(Environment.studentPath + studentID + ".json",false);
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
	
	public void removePreselectedList(String _course) {
		preselected.remove(_course);
		rewriteData();
	}
	
	public void addPreselectedList(String _course) {
		preselected.add(_course);
		rewriteData();
	}
	
	public void removeSelectedList(String _course) {
		Course.withdraw(_course);
		selected.remove(_course);
		rewriteData();
	}
	
	public void addSelectedList(String _course) throws CourseException {
		Course.select(_course, this);
		selected.add(_course);
		rewriteData();
	}
}
