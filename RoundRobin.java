import java.util.Scanner;

import java.util.LinkedList;
import java.util.Queue;

// Peemptive
class Process {
    int processId;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnAroundTime;
    int waitingTime;
    int remainingBurstTime;

    Process(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
    }
}

public class RoundRobin {

    public static void main(String[] args) {

        int n = 4;
        int tq = 2;
        Process[] processes = { new Process(1, 0, 5),
                new Process(2, 1, 4),
                new Process(3, 2, 2),
                new Process(4, 4, 1) };

        Scanner sc = new Scanner(System.in);
        // System.out.print("Enter the No of Processes :");
        // int n = sc.nextInt();
        // System.out.print("Enter the Time Quantum :");
        // int tq = sc.nextInt();

        // Process[] processes = new Process[n];

        // for (int i = 0; i < n; i++) {
        // System.out.print("Enter the arrival time of process " + (i + 1) + " :");
        // int arrivalTime = sc.nextInt();
        // System.out.print("Enter the burst time of process " + (i + 1) + " :");
        // int burstTime = sc.nextInt();

        // processes[i] = new Process(i + 1, arrivalTime, burstTime);

        // }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (processes[j].arrivalTime > processes[j + 1].arrivalTime) {
                    Process temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;

                }
            }
        }
        int currentTime = 0;
        int preTime = -1;
        int completedProcess = 0;
        int processIndex = -1;
        Queue<Integer> readyQueue = new LinkedList<>();
        while (completedProcess < n) {
            Process currProcess = null;
            for (int i = 0; i < n; i++) {
                if (processes[i].arrivalTime > preTime && processes[i].arrivalTime <= currentTime
                        && processes[i].remainingBurstTime > 0) {
                    readyQueue.add(i);
                }

            }
            if (processIndex != -1) {
                if (processes[processIndex].remainingBurstTime > 0) {
                    readyQueue.add(processIndex);
                }
            }
            System.out.println(readyQueue);

            if (!readyQueue.isEmpty()) {
                processIndex = readyQueue.peek();
                readyQueue.poll();
                currProcess = processes[processIndex];
            } else {
                processIndex = 0;
            }

            if (currProcess != null) {
                if (currProcess.remainingBurstTime <= tq) {
                    preTime = currentTime;
                    currentTime = currentTime + currProcess.remainingBurstTime;

                    currProcess.completionTime = currentTime;
                    currProcess.turnAroundTime = currProcess.completionTime - currProcess.arrivalTime;
                    currProcess.waitingTime = currProcess.turnAroundTime - currProcess.burstTime;
                    currProcess.remainingBurstTime = 0;

                    completedProcess++;
                } else {
                    preTime = currentTime;
                    currProcess.remainingBurstTime -= tq;
                    currentTime += tq;
                }
            } else {
                preTime = currentTime;
                currentTime++;
            }

        }
        System.out.println("Round Robin Scheduling");
        System.out.println("Time Quantum :" + tq);
        System.out.printf("%-15s%-15s%-15s%-25s%-25s%-15s\n",
                "Process", "Arrival Time", "Burst Time", "Completion Time", "Turnaround Time",
                "Waiting Time");

        for (Process process : processes) {
            System.out.printf("%-15d%-15d%-15d%-25d%-25d%-15d\n",
                    process.processId, process.arrivalTime, process.burstTime,
                    process.completionTime, process.turnAroundTime, process.waitingTime);
        }
        sc.close();
    }

}
