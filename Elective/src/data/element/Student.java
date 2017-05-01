package data.element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.google.gson.Gson;

import data.environment.Environment;

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
}
