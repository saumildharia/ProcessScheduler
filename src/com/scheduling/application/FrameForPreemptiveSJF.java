package com.scheduling.application;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

class FrameForPreemptiveSJF extends JFrame {
	int CPUBurstTime[];
	Scheduling obj;

	

	public FrameForPreemptiveSJF(Scheduling obj2) {
		// TODO Auto-generated constructor stub
		super("Preemptive SJF");
		
		this.obj = obj;
		// this.setResizable(false);
		this.setVisible(true);
		this.setSize(obj.SCREEN_WIDTH + 100, obj.SCREEN_HEIGHT);
		CPUBurstTime = obj.CPUBurstTime.clone();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		CPUBurstTime = Scheduling.CPUBurstTime.clone();
		
		this.getContentPane().setBackground(Color.white);
		int currentTime = obj.minimumArrivalTime;
		int min, mini = 0, prevmini = 0;
		int leftStart = 50;

		g = this.getContentPane().getGraphics();
		g.drawString("" + obj.minimumArrivalTime, leftStart,
				obj.rectangleUpperPadding + obj.rectangleHeight + 20);

		for (int j = 0; j < obj.sumCPUBurstTime; j++) {
			min = Integer.MAX_VALUE;
			for (int i = 0; i < obj.numberOfProcesses; i++) {
				if (min > CPUBurstTime[i] && obj.arrivalTime[i] <= currentTime
						&& CPUBurstTime[i] != 0) {
					min = CPUBurstTime[i];
					mini = i;
				}
			}
			if (j == 0)
				prevmini = mini;

			if (prevmini != mini || j == obj.sumCPUBurstTime - 1) {
				g = this.getContentPane().getGraphics();
				if (j == obj.sumCPUBurstTime - 1)
					g.drawRect(leftStart, obj.rectangleUpperPadding,
							obj.lengthOfEachBlock * (currentTime + 1),
							obj.rectangleHeight);
				else
					g.drawRect(leftStart, obj.rectangleUpperPadding,
							obj.lengthOfEachBlock * (currentTime),
							obj.rectangleHeight);
				g.drawString("P" + (prevmini + 1), leftStart + 5,
						obj.rectangleUpperPadding + 50);
				leftStart += obj.lengthOfEachBlock * currentTime;
				if (j == obj.sumCPUBurstTime - 1)
					g.drawString("" + (currentTime + 1), leftStart
							+ obj.lengthOfEachBlock, obj.rectangleUpperPadding
							+ obj.rectangleHeight + 20);
				else
					g.drawString("" + (currentTime), leftStart,
							obj.rectangleUpperPadding + obj.rectangleHeight
									+ 20);
			}
			currentTime++;

			CPUBurstTime[mini]--;
			prevmini = mini;
		}
	}
	
	        
}
