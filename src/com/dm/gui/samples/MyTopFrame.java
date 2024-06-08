package com.dm.gui.samples;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MyTopFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH=800;
	private static final int DEFAULT_HEIGHT=700;
	JToolBar toolBar;
	JButton addStudentsButton;
	JTextField studentName;
	JPanel namePanel;
	MyTablePanel myTable;
	JLabel label;
	
	public MyTopFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		myTable = new MyTablePanel();
		add(myTable, BorderLayout.CENTER);
		
		toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		addStudentsButton = new JButton("Add Student");
		toolBar.add(addStudentsButton);
		addStudentsButton.addActionListener(this);
		
		label = new JLabel();
		label.setText("");
		add(label, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		
		studentName = new JTextField("(Enter name)", 2);
		namePanel = new JPanel();
		namePanel.add(studentName);
		int result = JOptionPane.showConfirmDialog(null, studentName, "Student Name", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			String name = studentName.getText();
			String[] nameArray = name.split(" ");
			Student student1 = new Student(nameArray[0], nameArray[1], null, null);
			myTable.myStudents.add(student1);
			myTable.revalidate();
		} 
		
		
	}
	
	
	

}
