package com.scheduling.application;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

@SuppressWarnings("serial")
class FrameForFCFS extends JFrame {
	int arrivalTime[];
	static Scheduling obj;

	

	public FrameForFCFS(Scheduling obj) {
		// TODO Auto-generated constructor stub
		super("FCFS");
		this.obj = obj;
		// this.setResizable(false);
		this.setVisible(true);
		this.setSize(obj.SCREEN_WIDTH + 100, obj.SCREEN_HEIGHT); //?
		arrivalTime = obj.arrivalTime.clone();
	}

	
	public static void Fcfs()
	{
		
		//float Twt;
		//int B[] = new int[10];
		
		obj.Twt = (float) 0.0;
		
		for (int i = 0; i <obj.numberOfProcesses; i++)
		{
			//obj.CPUBurstTime[i] = obj.CPUBurstTime[i];
			System.out.println("Burst Time for Process p"+ (i+1) +" = " + obj.CPUBurstTime[i]);
			//System.out.println(obj.CPUBurstTime[i]);
		}
		
		// to assign the waiting time for the 1st precesss including the arrival time
		if (obj.arrivalTime[0] > 0)
		{
			obj.Wt[0] = obj.arrivalTime[0];
		}
		else
			obj.Wt[0] = (float) 0.0;
		
		// to calculate the waiting time for others
		for (int i = 1; i < obj.numberOfProcesses; i++)
		{
			obj.Wt[i] = obj.CPUBurstTime[i-1] + obj.Wt[i-1];
		}
		
		//printing the waiting time for each process
		for (int i = 0; i < obj.numberOfProcesses; i++)
		{
			System.out.println("The waiting time for p" + (i+1) + " = " +obj.Wt[i]);
		}
		//Calculating Average Weighting Time
		for (int i = 0; i < obj.numberOfProcesses; i++)
		{
			obj.Twt = obj.Twt + obj.Wt[i];
		}
		obj.Awt = obj.Twt / obj.numberOfProcesses;
		System.out.println("Total weighting time is " + obj.Twt);
		System.out.println("Average weighting time " + obj.Awt);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.getContentPane().setBackground(Color.white);

		int currentTime = obj.minimumArrivalTime;
		arrivalTime = obj.arrivalTime.clone();

		int i, j, min, mini = 0;
		int leftStart = 50;
		g = this.getContentPane().getGraphics();
		g.drawString("" + obj.minimumArrivalTime, leftStart,
				obj.rectangleUpperPadding + obj.rectangleHeight + 20);
		for (j = 0; j < obj.numberOfProcesses; j++) {
			min = Integer.MAX_VALUE;
			for (i = 0; i < obj.numberOfProcesses; i++) {
				if (min > arrivalTime[i]) {
					min = arrivalTime[i];
					mini = i;
				}
			}
			arrivalTime[mini] = Integer.MAX_VALUE;

			g = this.getContentPane().getGraphics();
			g.drawRect(leftStart, obj.rectangleUpperPadding,
					obj.lengthOfEachBlock * obj.CPUBurstTime[mini],
					obj.rectangleHeight);
			g.drawString("P" + (mini + 1), leftStart + 5,
					obj.rectangleUpperPadding + 50);
			leftStart += obj.lengthOfEachBlock * obj.CPUBurstTime[mini];

			currentTime += obj.CPUBurstTime[mini];
			g.drawString("" + currentTime, leftStart, obj.rectangleUpperPadding
					+ obj.rectangleHeight + 20);

		}
	}
	
	
}
