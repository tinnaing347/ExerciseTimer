package com.tinnaing347.exercisetimer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

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
	
	private JLabel getlabelTimer() {
		return labelTimer;
	}
	
	
	private int getimerVal() {
		return timerVal;
	}
	
	static int gettotalTime() {
		return totalTime;
	}
	
	private int getwaitTime() {
		return waitTime;
	}
	
	//need this to reset total time in the case that set is pressed more than once
	static void resetTotalTime() {
		totalTime = 0;
	}
	
	static void resetCounter() {
		counter = 0;
	}

	Timer createTimer() {
		final Timer timer = new Timer(1000, null);
		final JLabel l = getlabelTimer();
		final int timerVal = getimerVal();
		final int waitTime = getwaitTime();
		ActionListener taskPerformer = new ActionListener() {
			int j = waitTime;
			int i = timerVal;
		      public void actionPerformed(ActionEvent evt) {
		    	if (j == 0 & i >=0) {
		    		l.setText(Integer.toString(i--));
		    		l.setForeground(Color.red);
		    	}
		    	if (i < 0) {
		    		l.setForeground(Color.black);
		    		timer.stop();
		    	}
		    	if (j > 0) j--;
		      }
		  };
		 timer.addActionListener(taskPerformer);
		 return timer;
	}
	
	void remove(JFrame frame) {
		frame.remove(labelTimer);
		frame.remove(textTimer);
		frame.remove(textLabel);
	}
	
	
}
