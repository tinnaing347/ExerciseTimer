package com.tinnaing347.exercisetimer;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ExerciseTimer {

	private JFrame frame;
	
	private ArrayList<TimerLabel> labels =new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExerciseTimer window = new ExerciseTimer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExerciseTimer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 474, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 3));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            SwingUtilities.invokeLater(new Runnable() {
	                @Override
	                public void run() {
	                	JTextField temptextLabel = new JTextField("Exercise");
	                	JTextField temptextTimer = new JTextField("Time");
	                	JLabel templabelTimer =new JLabel("0");
	                	
	                	TimerLabel myTempLabel = new TimerLabel(templabelTimer, temptextTimer, temptextLabel);
	                	labels.add(myTempLabel);
	                	
	                	
	                    frame.getContentPane().add(temptextLabel);
	                    frame.getContentPane().add(temptextTimer);
	                    frame.getContentPane().add(templabelTimer);
	                    frame.validate();
	                    frame.repaint();
	                }
	            });
			}
		});
		addButton.setBounds(35, 35, 117, 25);
		panel.add(addButton);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		
		JButton setButton = new JButton("Set");
		panel_1.add(setButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (TimerLabel myL : labels) myL.remove(frame);
                TimerLabel.resetCounter();
                labels.clear();
                frame.validate();
                frame.repaint();
			}
		});
		panel_1.add(clearButton);
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//need this to reset total time in the case that set is pressed more than once
				TimerLabel.resetTotalTime();
				for (TimerLabel l: labels) l.setLabelTimer();
			}
		});
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		
		JButton startButton = new JButton("Start");
		panel_2.add(startButton);
		startButton.addActionListener(new ActionListener() {
			ArrayList<Timer> timerLs =new ArrayList<>();
			public void actionPerformed(ActionEvent e) {
				for (TimerLabel myL : labels) {timerLs.add(myL.createTimer());}
				for (Timer t : timerLs) {t.start();}
				timerLs.clear();
			}
		}); //startButton action listener ends
	}

}
