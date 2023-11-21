
import java.util.Scanner;

public class FCFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the no of processes :");
        int n = sc.nextInt();

        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter the arrival time :");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter the burst time :");
            int burstTime = sc.nextInt();

            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (processes[j].arrivalTime > processes[j + 1].arrivalTime) {
                    Process temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }

        int currentTime = 0;
        for (Process process : processes) {
            process.completionTime = currentTime + process.burstTime;
            process.turnArountTime = process.completionTime - process.arrivalTime;
            process.waitingTime = process.completionTime - process.burstTime;

            currentTime = process.completionTime;
        }

        System.out.println("\nFCFS Scheduling:");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n",
                "Process", "Arrival Time", "Burst Time", "Completion Time", "Turnaround Time", "Waiting Time");

        for (Process process : processes) {
            System.out.printf("%-15d%-15d%-15d%-15d%-15d%-15d\n",
                    process.processId, process.arrivalTime, process.burstTime,
                    process.completionTime + processes[0].arrivalTime, process.turnArountTime, process.waitingTime);
        }

        sc.close();
    }

}

class Process {
    int processId;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnArountTime;
    int waitingTime;

    Process(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

}