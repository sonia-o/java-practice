package com.dm.gui.samples;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public class MyTablePanel extends JPanel {
	
	public ArrayList<Student> myStudents;

	public MyTablePanel() {
		try {
			myStudents = Student.fromFile("data/students.txt");
		} catch (Exception e) {
			System.out.println("Cannot read from file.");
		}
		
		setLayout(new BorderLayout());
		
		JTable table = new JTable(new MyTableModel());
		add(table, BorderLayout.CENTER);
		
		
		
	}
	
	private class MyTableModel extends AbstractTableModel {

		public int getRowCount() {
			return myStudents.size();
		}

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String columnValue = "";
			switch (columnIndex) {
			case 0:
				columnValue = myStudents.get(rowIndex).getFirstName();
				break;
			case 1:
				columnValue = myStudents.get(rowIndex).getLastName();
				break;
			}
			return columnValue;
		}
		
	}

}
