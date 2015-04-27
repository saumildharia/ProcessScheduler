package com.scheduling.application;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

class FrameForPriorityScheduling extends JFrame {
	static Scheduling obj;
	int priority[];

	public FrameForPriorityScheduling(Scheduling obj2) {
		// TODO Auto-generated constructor stub
		super("Priority Scheduling");
		this.obj = obj;

		this.setVisible(true);
		this.setSize(obj.SCREEN_WIDTH + 100, obj.SCREEN_HEIGHT);
		repaint();
	}
	
	public static void Priority()
	{
		int i,j,max;
		obj.w = (float) 0.0;
		obj.Twt = (float) 0.0;
		max = 1;
		int B[] = new int[10];
		int P[] = new int[10];
		
		for (i = 0; i < obj.numberOfProcesses; i++)
		{
			B[i] = obj.CPUBurstTime[i];
			System.out.println("Burst Time for Process p"+ (i+1) +" = " + B[i]);
			//System.out.println();
			//System.out.println("Enter the priority for process P"+ i +" = ");
			P[i]=obj.priority[i];
			
			if (max<P[i])
			
			{
				max = P[i];
			}
		}
			j = 0;
			while (j <= max)
			{
				i = 0;
				while (i < obj.numberOfProcesses)
				{
					if (P[i] == j)
					{
						obj.Wt[i] = obj.w;
						obj.w = obj.w + B[i];
					}
					i++;
				}
				j++;
			}
			
			//printing the waiting time for each process
			for (int m = 0; m < obj.numberOfProcesses; m++)
			{
				System.out.println("The waiting time for p" + (m+1) + " = " +obj.Wt[m]);
			}
			//calculating average weighting Time
			for (i = 0; i < obj.numberOfProcesses ; i++)
			{
				obj.Twt = obj.Twt + obj.Wt[i];
			}
			obj.Awt = obj.Twt / obj.numberOfProcesses;
			System.out.println("Total weighting time is" + obj.Twt);
			System.out.println("Average weighting time" + obj.Awt);
		}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		priority = obj.priority.clone();
		this.getContentPane().setBackground(Color.white);
		int currentTime = 0;
		int leftStart = 50;

		int min, mini = 0;
		g = this.getContentPane().getGraphics();
		g.drawString("" + currentTime, leftStart, obj.rectangleUpperPadding
				+ obj.rectangleHeight + 20);

		for (int j = 0; j < obj.numberOfProcesses; j++) {
			min = Integer.MAX_VALUE;
			//min = 1;
			for (int i = 0; i < obj.numberOfProcesses; i++) {
				if (min > priority[i]) {
					min = priority[i];
					mini = i;
				}
			}

			priority[mini] = Integer.MAX_VALUE;

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
