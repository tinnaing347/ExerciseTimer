package com.tinnaing347.exercisetimer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TimerLabel {
	JLabel labelTimer;
	JTextField textTimer;
	JTextField textLabel;
	
	int timerVal;
	static int totalTime;
	int waitTime;
	static int counter = 0;
	int id = 0;
	
	TimerLabel(JLabel l1, JTextField t1, JTextField t2) {
		labelTimer= l1;
		textTimer = t1;
		textLabel = t2;
		counter++;
		id = counter;
	}
	
	void setLabelTimer() {
		
		String text = textTimer.getText();
		
		try {
			timerVal = parseTextTimer(text);
			totalTime += timerVal;
			waitTime = totalTime - timerVal;
			labelTimer.setText(Integer.toString(timerVal));
		} catch (IllegalArgumentException e) { //catch error here for now; todo add InputVerifier
			String message = text + " is invalid value for timer: " + id + ".\nPlease try again with a number";
	        JOptionPane.showMessageDialog(null,
	                                      message,
	                                      "Invalid Value",
	                                      JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private int parseTextTimer(String text) {
		try {
			timerVal = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			throw e;
		}
		
		//take care of negative value for now;
		if (timerVal < 0) {
			throw new IllegalArgumentException();
		} else {
			return timerVal;
		}
	}
	
	JLabel getlabelTimer() {
		return labelTimer;
	}
	
	
	int getimerVal() {
		return timerVal;
	}
	
	static int gettotalTime() {
		return totalTime;
	}
	
	int getwaitTime() {
		return waitTime;
	}
	
	//need this to reset total time in the case that set is pressed more than once
	static void resetTotalTime() {
		totalTime = 0;
		counter = 0;
	}
}
