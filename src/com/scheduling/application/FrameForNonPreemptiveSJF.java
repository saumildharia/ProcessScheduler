package com.scheduling.application;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

class FrameForNonPreemptiveSJF extends JFrame {
	int CPUBurstTime[];
	static Scheduling obj;

	public FrameForNonPreemptiveSJF(Scheduling obj) {
		super("Non preemptive SJF");
		this.obj = obj;
		// this.setResizable(false);
		this.setVisible(true);
		this.setSize(obj.SCREEN_WIDTH + 100, obj.SCREEN_HEIGHT);
		CPUBurstTime = obj.CPUBurstTime.clone();
	}

	public static void SJF()
	{
		
		obj.Twt = (float) 0.0;
		for(int i=0;i<obj.numberOfProcesses;i++)
		{
			obj.Wt[i]=0;
			}
		int temp;
		int pos;
		
		for(int i=0 ;i<obj.numberOfProcesses;i++)
		{
			pos =i;
			for(int j=i+1;j<obj.numberOfProcesses;j++)
			{ 
				if(obj.CPUBurstTime[j]<obj.CPUBurstTime[pos])
					pos =j;
			}
				
					temp=obj.CPUBurstTime[i];
					obj.CPUBurstTime[i]=obj.CPUBurstTime[pos];
					obj.CPUBurstTime[pos]=(int) temp;

					temp = obj.p[i];
					 obj.p[i]= obj.p[pos];
					 obj.p[pos]= temp;
		 }
				
		if(obj.arrivalTime[0] > 0){
			obj.Wt[0]= obj.arrivalTime[0];
		}
		else 
			obj.Wt[0] = (float) 0.0;
			
		for (int i = 1; i < obj.numberOfProcesses; i++)
		{
			obj.Wt[i]=0;
			for(int j=0;j<i;j++)
				obj.Wt[i]+=obj.CPUBurstTime[j]+obj.arrivalTime[0];
			
		}

		
		
		for(int i=0;i<obj.numberOfProcesses;i++)
		{
			System.out.println("The waiting time for p" + obj.p[i] + " = " + obj.Wt[i]);
		}


		for(int j=0;j<obj.numberOfProcesses;j++){	
			obj.Twt = obj.Twt + obj.Wt[j];
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

		CPUBurstTime = obj.CPUBurstTime.clone();

		int i, j, min, mini = 0;
		int leftStart = 50;

		g = this.getContentPane().getGraphics();
		g.drawString("" + obj.minimumArrivalTime, leftStart,
				obj.rectangleUpperPadding + obj.rectangleHeight + 20);

		for (j = 0; j < obj.numberOfProcesses; j++) {
			min = Integer.MAX_VALUE;
			for (i = 0; i < obj.numberOfProcesses; i++) {
				if (min > CPUBurstTime[i] ) { //&& obj.arrivalTime[i] <= currentTime
					min = CPUBurstTime[i];
					mini = i;
				}
			}

			g = this.getContentPane().getGraphics();
			g.drawRect(leftStart, obj.rectangleUpperPadding,
					obj.lengthOfEachBlock * obj.CPUBurstTime[mini],
					obj.rectangleHeight);
			g.drawString("P" + obj.p[j], leftStart + 5,
					obj.rectangleUpperPadding + 50);
			leftStart += obj.lengthOfEachBlock * obj.CPUBurstTime[mini];

			currentTime += obj.CPUBurstTime[mini];
			g.drawString("" + currentTime, leftStart, obj.rectangleUpperPadding
					+ obj.rectangleHeight + 20);

			CPUBurstTime[mini] = Integer.MAX_VALUE;

		}
	}
}

