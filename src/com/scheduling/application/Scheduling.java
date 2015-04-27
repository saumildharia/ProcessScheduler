package com.scheduling.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scheduling {

	static int minimumArrivalTime, sumCPUBurstTime;
	static int lengthOfEachBlock;
	static final int SCREEN_WIDTH = 700, SCREEN_HEIGHT = 280;
	static final int rectangleUpperPadding = 50, rectangleHeight = 100;
	static int numberOfProcesses;
	static int CPUBurstTime[], arrivalTime[], priority[],p[];
	static BufferedReader br;
	static int timeQuantum;
	static Scheduling obj;
	//static int n; 
	//static int Bu[] = new int[10];
	static float Twt; // Total waiting time
	static float Awt; //Average waiting time 
	static float w; 
	float[] A; 
	static float Wt[] = new float[10]; // Waiting time for each process

	Scheduling() {
		Scheduling.obj = this;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number of processes : ");
		numberOfProcesses = Integer.parseInt(br.readLine());

		CPUBurstTime = new int[numberOfProcesses];
		arrivalTime = new int[numberOfProcesses];
		priority = new int[numberOfProcesses];
		p = new int [numberOfProcesses];

		for (int i = 0; i < numberOfProcesses; i++) {
			System.out.println("________________________________________________");
			System.out.println("Enter the data for the process " + (i + 1));
			System.out.println("________________________________________________");
			System.out.print("Enter the arrival Time : ");
			arrivalTime[i] = Integer.parseInt(br.readLine());
			System.out.print("Enter the CPU Burst Time : ");
			CPUBurstTime[i] = Integer.parseInt(br.readLine());
			p[i]=i+1;
		}

		drawGanttChart();

	}

	public static void drawGanttChart() throws NumberFormatException,
			IOException {
		int choice;
		sumCPUBurstTime = 0;
		/* calculating the sum of all cpu burst time */
		for (int i = 0; i < numberOfProcesses; i++) {
			sumCPUBurstTime += CPUBurstTime[i];
		}

		/*
		 * now the total width of the screen is to be divided into
		 * sumCPUBurstTime equal parts
		 */
		lengthOfEachBlock = SCREEN_WIDTH / sumCPUBurstTime;

		/*
		 * claculating the minimum arrival time
		 */
		minimumArrivalTime = Integer.MAX_VALUE;
		for (int i = 0; i < numberOfProcesses; i++) {
			if (minimumArrivalTime > arrivalTime[i])
				minimumArrivalTime = arrivalTime[i];
		}

		/* asking the user which gantt chart do you want */

		System.out.println("YOU HAVE THE FOLLOWING CHOICES : \n");
		System.out.println("1. Draw Gantt Chart for FCFS Algorithm");
		System.out.println("2. Draw Gantt Chart for Non-Preemptive SJF Algorithm");
		System.out.println("3. Draw Gantt Chart for Priority Algorithm");
		System.out.println("4. Draw Gantt Chart for Round Robin");

		System.out.println("5. Exit");
		
		while (true) {
			System.out.println("ENTER YOUR CHOICE : ");
			choice = Integer.parseInt(br.readLine());

			switch (choice) {
			case 1:
				FrameForFCFS.Fcfs();
				drawGanttChartForFCFS();
				break;
			case 2:
				FrameForNonPreemptiveSJF.SJF();
				drawGanttChartForNonPreemptiveSJF();
				break;
			
			case 3:
				for (int i = 0; i < numberOfProcesses; i++) {
					
					System.out.print("Enter the priority for process" + (i + 1) +" ");
					priority[i] = Integer.parseInt(br.readLine());
				}
				FrameForPriorityScheduling.Priority();
				drawGanttChartForPriorityScheduling();
				break;
				
			case 4:
				System.out.print("Enter the time Quantum : ");
				timeQuantum = Integer.parseInt(br.readLine());
				FrameForRoundRobinScheduling.RoundRobin();
				drawGanttChartForRoundRobinScheduling();
				break;
				
			case 5:
				System.exit(0);
				
			default:
				System.out
						.println("You Entered a wrong Choice\nPlease fill in the choice again...");
			}
		}
	}

	public static void drawGanttChartForFCFS() {
		new FrameForFCFS(obj);
	}

	public static void drawGanttChartForNonPreemptiveSJF() {
		new FrameForNonPreemptiveSJF(obj);
	}

	public static void drawGanttChartForPriorityScheduling() {
		new FrameForPriorityScheduling(obj);
	}

	public static void drawGanttChartForRoundRobinScheduling() {
		new FrameForRoundRobinScheduling(obj);
	}

}
