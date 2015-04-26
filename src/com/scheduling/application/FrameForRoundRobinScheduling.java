package com.scheduling.application;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

class FrameForRoundRobinScheduling extends JFrame {
	static Scheduling obj;
	int CPUBurstTime[];

	public FrameForRoundRobinScheduling(Scheduling obj) {
		super("Round Robin Scheduling");
		this.obj = obj;

		this.setVisible(true);
		this.setSize(obj.SCREEN_WIDTH + 100, obj.SCREEN_HEIGHT);
		repaint();
	}

	public static void RoundRobin() {
		int i, j, tq, k, m;
		int B[] = new int[10];
		int count[] = new int[10];
		int Rrobin[][] = new int[10][10];
		int max = 0;
		obj.Twt = (float) 0.0;
		for (i = 0; i < obj.numberOfProcesses; i++) {
			B[i] = obj.CPUBurstTime[i];
			System.out.println("Burst Time for Process p" + (i+1)+ " = " + B[i]);
			//System.out.println( );
			if (max < B[i])
				max = B[i];
			obj.Wt[i] = 0;
		}
		//System.out.println("Enter the time Quantum= ");
		tq = obj.timeQuantum;
		// TO find the dimension of the Rrobin array
		m = max / tq + 1;
		// initializing Rrobin array
		for (i = 0; i < obj.numberOfProcesses; i++) {
			for (j = 0; j < m; j++) {
				Rrobin[i][j] = 0;
			}
		}
		// placing value in the Rrobin array
		i = 0;
		while (i < obj.numberOfProcesses) {
			j = 0;
			while (B[i] > 0) {
				if (B[i] >= tq) {
					B[i] = B[i] - tq;
					Rrobin[i][j] = tq;
					j++;
				} else {
					Rrobin[i][j] = B[i];
					B[i] = 0;
					j++;
				}
			}
			count[i] = j - 1;
			i++;
		}
		/*System.out.println("Display");
		for (i = 0; i < obj.numberOfProcesses; i++) {
			for (j = 0; j < m; j++) {
				System.out.println("Rr[" + i + "," + j + "]=" + Rrobin[i][j]);
				System.out.println(" ");
			}
			System.out.println("");
		}*/
		// calculating weighting time
		int x = 0;
		i = 0;
		while (x < obj.numberOfProcesses) {
			for (int a = 0; a < x; a++) {
				obj.Wt[x] = obj.Wt[x] + Rrobin[a][i];
			}
			i = 0;
			int z = x;
			j = count[z];
			k = 0;
			while (k < j) {
				if (i == obj.numberOfProcesses + 1) {
					i = 0;
					k++;
				} else {
					if (i != z) {
						obj.Wt[z] = obj.Wt[z] + Rrobin[i][k];
					}
					i++;
				}
			}
			x++;
		}

		for (i = 0; i < obj.numberOfProcesses; i++) {
			System.out.println("Weighting Time for process P" + (i+1) + " = "
					+ obj.Wt[i]);
		}
		// calculating Average Weighting Time
		for (i = 0; i < obj.numberOfProcesses; i++)
			obj.Twt = obj.Twt + obj.Wt[i];
		obj.Awt = obj.Twt / obj.numberOfProcesses;
		System.out.println("Total weighting time is " + obj.Twt);
		System.out.println("Average weighting time " + obj.Awt);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		CPUBurstTime = obj.CPUBurstTime.clone();
		this.getContentPane().setBackground(Color.white);
		int currentTime = 0;
		int leftStart = 50;
		int processNumber = 0;
		int min, mini = 0;
		g = this.getContentPane().getGraphics();
		g.drawString("" + currentTime, leftStart, obj.rectangleUpperPadding
				+ obj.rectangleHeight + 20);
		int temptime = 0;
		for (currentTime = 0; currentTime <= obj.sumCPUBurstTime; currentTime++) {
			temptime++;

			if (temptime > obj.timeQuantum || CPUBurstTime[processNumber] == 0
					|| currentTime == obj.sumCPUBurstTime - 1) {
				// System.out.println(currentTime);
				int blockLength = temptime - 1 < obj.timeQuantum ? temptime - 1
						: obj.timeQuantum;

				g = this.getContentPane().getGraphics();
				g.drawRect(leftStart, obj.rectangleUpperPadding,
						obj.lengthOfEachBlock * blockLength,
						obj.rectangleHeight);
				g.drawString("P" + (processNumber + 1), leftStart + 5,
						obj.rectangleUpperPadding + 50);
				leftStart += obj.lengthOfEachBlock * blockLength;

				g.drawString("" + (currentTime), leftStart,
						obj.rectangleUpperPadding + obj.rectangleHeight + 20);
				temptime = 1;
				int counter = 0;
				do {
					counter++;
					if (counter == obj.numberOfProcesses + 1)
						break;
					processNumber = (processNumber + 1) % obj.numberOfProcesses;
				} while (CPUBurstTime[processNumber] == 0);

			}

			CPUBurstTime[processNumber]--;
		}
	}
}
