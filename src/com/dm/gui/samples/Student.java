package com.dm.gui.samples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class Student implements Comparable {

	private String firstName;
	private String lastName;
	private String gender;
	private TreeSet<String> classes;

	public Student(String firstName, String lastName, String gender, TreeSet<String> classes) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.classes = classes;
	}

	public Student() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public TreeSet<String> getClasses() {
		return classes;
	}

	public void setClasses(TreeSet<String> classes) {
		this.classes = classes;
	}

	public boolean equals(Student student1) {
		if (this.firstName.equals(student1.firstName) && this.firstName.equals(student1.lastName)) {
			return true;
		} else {
			return false;
		}
	}

	public int compareTo(Object student1) {
		return this.lastName.compareTo(((Student) student1).lastName);
	}

	public String toString() {
		String stringOutput = "student\n";
		stringOutput += "  first-name = " + firstName + "\n";
		stringOutput += "  last-name = " + lastName + "\n";
		stringOutput += "  gander = " + gender + "\n";
		for (String enrolledInClass: classes) {
			stringOutput += "  enrolled-in-class = " + enrolledInClass + "\n";
		}
		return stringOutput;
	}

	public static ArrayList<Student> fromFile(String fileName) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		System.out.println("File was not found.");
		String line = reader.readLine();
		ArrayList<Student> allStudents = new ArrayList<Student>();
		while (line != null) {
			line = reader.readLine();
			if (line != null && line.equals("student")) {
				ArrayList<String> studentLines = new ArrayList<String>();
				studentLines.add(line);
				line = reader.readLine();
				while (line.length() > 0) {
					studentLines.add(line);
					line = reader.readLine();
				}
				allStudents.add(Student.fromArrayList(studentLines));
			}
		}
		reader.close();
		return allStudents;
	}

	public static Student fromArrayList(ArrayList<String> lines) {
		Student student = new Student();
		String firstName = lines.get(1).split("=")[1].trim();
		String lastName = lines.get(2).split("=")[1].trim();
		String gender = lines.get(3).split("=")[1].trim();

		TreeSet<String> classes = new TreeSet<String>();
		for (int i = 4; i < lines.size(); i++) {
			String class1 = lines.get(i).split("=")[1].trim();
			classes.add(class1);
		}

		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setGender(gender);
		student.setClasses(classes);

		return student;
	}

	public static void main(String[] args) throws Exception {
//		ArrayList<String> lines = new ArrayList<String>();
//		lines.add("student");
//		lines.add("  first-name = Madison");
//		lines.add("  last-name = Mullins");
//		lines.add("  gender = female");
//		lines.add("  enrolled-in-class = Physics");
//		lines.add("  enrolled-in-class = Chemistry");
//		lines.add("  enrolled-in-class = Music");
//		lines.add("  enrolled-in-class = Biology");
//		lines.add("  enrolled-in-class = Mathematics");
//
//		Student student1 = Student.fromArrayList(lines);
//
//		System.out.println(student1);
		
		ArrayList<Student> allStudents = Student.fromFile("data/students.txt");
		
		for (Student student1: allStudents) {
			System.out.println(student1);
		}
	}

}