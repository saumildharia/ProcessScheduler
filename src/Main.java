
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;

public class Main
{

                static int sumCPUBurstTime;
                static int lengthOfEachBlock;
                static final int SCREEN_WIDTH=700,SCREEN_HEIGHT=280;
                static final int rectangleUpperPadding=50,rectangleHeight=100;
                static int numberOfProcesses;
                static int CPUBurstTime[],priority[];
                static BufferedReader br;
                static Main obj;
                Main()
                {
                                this.obj=this;
                }
               
                public static void main(String[] args) throws NumberFormatException, IOException
                {
                                br=new BufferedReader(new InputStreamReader(System.in));
                                System.out.println("Enter the number of processes : ");
                                numberOfProcesses=Integer.parseInt(br.readLine());
                               
                                CPUBurstTime=new int[numberOfProcesses];
                                priority=new int[numberOfProcesses];
                               
                               
                                for(int i=0;i<numberOfProcesses;i++)
                                {
                                                System.out.println("________________________________________________");
                                                System.out.println("Enter the data for the process "+(i+1));
                                                System.out.println("________________________________________________");
                                                System.out.print("Enter the CPU Burst Time : ");
                                                CPUBurstTime[i]=Integer.parseInt(br.readLine());
                                                System.out.print("Enter the Priority : ");
                                                priority[i]=Integer.parseInt(br.readLine());
                                               
                                }
                                drawGanttChart();

                }
               
                public static void drawGanttChart() throws NumberFormatException, IOException
                {
                    sumCPUBurstTime=0;


                    /* calculating the sum of all cpu burst time */
                    for(int i=0;i<numberOfProcesses;i++)
                    {
                        sumCPUBurstTime+=CPUBurstTime[i];
                    }

                    /* now the total width of the screen is to be divided into sumCPUBurstTime equal parts */
                    lengthOfEachBlock=SCREEN_WIDTH/sumCPUBurstTime;

                    drawGanttChartForPriorityScheduling();
                }
                public static void drawGanttChartForPriorityScheduling()
                {
                                new FrameForPriorityScheduling(obj);
                }
}

class FrameForPriorityScheduling extends JFrame
{
                Main obj;
                int priority[];
                FrameForPriorityScheduling(Main obj)
                {
                                super("Priority Scheduling");
                                this.obj=obj;
                               
                                this.setVisible(true);
                                this.setSize(obj.SCREEN_WIDTH+100, obj.SCREEN_HEIGHT);
                                repaint();
                }
               
                @Override
                public void paint(Graphics g)
                {
                                super.paint(g);
                                priority=obj.priority.clone();
                                this.getContentPane().setBackground(Color.white);
                                int currentTime=0;
                                int leftStart=50;
                   
                                int min,mini = 0;
                                g=this.getContentPane().getGraphics();
                                g.drawString(""+currentTime,leftStart,obj.rectangleUpperPadding+obj.rectangleHeight+20);
                                   
                                for(int j=0;j<obj.numberOfProcesses;j++)
                    {
                        min=Integer.MAX_VALUE;
                        for(int i=0;i<obj.numberOfProcesses;i++)
                        {
                            if(min>priority[i])
                            {
                                min=priority[i];
                                mini=i;
                            }
                        }
                       
                        priority[mini]=Integer.MAX_VALUE;
                       
                        g=this.getContentPane().getGraphics();
                        g.drawRect(leftStart,obj.rectangleUpperPadding,obj.lengthOfEachBlock*obj.CPUBurstTime[mini],obj.rectangleHeight);
                        g.drawString("P"+(mini+1),leftStart+5,obj.rectangleUpperPadding+50);
                        leftStart+=obj.lengthOfEachBlock*obj.CPUBurstTime[mini];
                        currentTime+=obj.CPUBurstTime[mini];
                        g.drawString(""+currentTime,leftStart,obj.rectangleUpperPadding+obj.rectangleHeight+20);
                    }
                }
}