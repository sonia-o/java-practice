package com.so;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class MyFileChooser implements ActionListener {
	
	JFrame mainFrame;
	JPanel mainPanel;
	JToolBar toolBar;
	JButton openFileButton;
	JButton saveFileButton;
	JLabel fileNameLabel;
	JTextArea textArea;
	
	
	public MyFileChooser() {
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 400);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		toolBar = new JToolBar();
		mainPanel.add(toolBar, BorderLayout.NORTH);
		
		openFileButton = new JButton("Open File");
		openFileButton.addActionListener(this);
		saveFileButton = new JButton("Save File");
		saveFileButton.addActionListener(this);
		toolBar.add(openFileButton);
		toolBar.add(saveFileButton);
		
//		fileNameLabel = new JLabel("No file selected");
//		mainPanel.add(fileNameLabel, BorderLayout.CENTER);
		
		textArea = new JTextArea();
//		mainPanel.add(textArea, BorderLayout.CENTER);
		textArea.setText("No file selected");
		
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		
		JScrollPane scroller = new JScrollPane(textArea);
		JScrollBar bar = new JScrollBar();
		scroller.add(bar);
		mainPanel.add(scroller, BorderLayout.CENTER);

	}
	
	public void actionPerformed(ActionEvent event) {
//		JLabel nameLabel = new JLabel("Update the text area: ");
//		JTextField nameField = new JTextField();
//		JComponent[] inputs = new JComponent[2];
//		inputs[0] = nameLabel;
//		inputs[1] = nameField;
//		int returnValue = JOptionPane.showConfirmDialog(null, inputs, "My Dialogue", JOptionPane.OK_CANCEL_OPTION);
//		if (returnValue == JOptionPane.OK_OPTION) {
//			textArea.setText(nameField.getText());
//		}
//		System.out.println(nameField.getText());
		
		System.out.println("Button clicked!!");
		System.out.println(event.getActionCommand());
		JFileChooser fileChooserDialog = new JFileChooser();
		if (event.getActionCommand().equals("Open File")) {
			int returnValue = fileChooserDialog.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(fileChooserDialog.getSelectedFile().getAbsolutePath()));
					String line = reader.readLine();
					textArea.setText("");
					while (line != null) {
						textArea.append(line + "\n");
						line = reader.readLine();
					}
					reader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		} else {
			int returnValue = fileChooserDialog.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				try {
					String textToSave = textArea.getText();
					File fileChecker = new File(fileChooserDialog.getSelectedFile().getAbsolutePath());
					if (fileChecker.exists()) {
						System.out.println("File already exists.");
						int returnValue1 = JOptionPane.showConfirmDialog(null, "File already exists. Are you sure you want to override?", "Save Warning", JOptionPane.YES_NO_OPTION);
						if (returnValue1 == JOptionPane.YES_OPTION) {
							BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooserDialog.getSelectedFile().getAbsolutePath()));
							writer.write(textToSave);
							writer.close();
						} 
					} else {
						BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooserDialog.getSelectedFile().getAbsolutePath()));
						writer.write(textToSave);
						writer.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MyFileChooser app = new MyFileChooser();
		});
	}

}
