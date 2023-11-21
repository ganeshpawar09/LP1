
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// non preemptive
class Process {
    int processId;
    int arrivalTime;
    int burstTime;
    int priority;
    int completionTime;
    int turnAroundTime;
    int waitingTime;
    int remainingBurstTime;

    Process(int processId, int arrivalTime, int burstTime, int priority) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
        this.priority = priority;

    }

}

public class priority {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the No of Processes: ");
        int n = sc.nextInt();

        ArrayList<Process> processes = new ArrayList<Process>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the arrival time of process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter the burst time of process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            System.out.print("Enter the priority of process " + (i + 1) + ": ");
            int priority = sc.nextInt();

            processes.add(new Process(i + 1, arrivalTime, burstTime, priority));
        }
        processes.sort((a, b) -> Integer.compare(a.priority, b.priority));
        Collections.reverse(processes);

        int currentTime = 0;
        int completedProcess = 0;
        while (completedProcess < n) {
            Process currProcess = null;

            for (Process process : processes) {
                if (process.arrivalTime <= currentTime && process.remainingBurstTime > 0) {
                    currProcess = process;
                    break;
                }
            }

            if (currProcess != null) {
                currentTime += currProcess.burstTime;
                currProcess.completionTime = currentTime;
                currProcess.turnAroundTime = currProcess.completionTime - currProcess.arrivalTime;
                currProcess.waitingTime = currProcess.turnAroundTime - currProcess.burstTime;
                currProcess.remainingBurstTime = 0;
                completedProcess++;
            } else {
                currentTime++;
            }

        }
        System.out.println("\nPriority Non-Preemptive Scheduling:");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n",
                "Process", "Arrival Time", "Burst Time", "Priority", "Completion Time", "Turnaround Time",
                "Waiting Time");

        for (Process process : processes) {
            System.out.printf("%-15d%-15d%-15d%-15d%-15d%-15d%-15d\n",
                    process.processId, process.arrivalTime, process.burstTime,
                    process.priority, process.completionTime, process.turnAroundTime, process.waitingTime);
        }
        sc.close();
    }

}
