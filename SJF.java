import java.util.Scanner;

// preemptive
class Process {
    int processId;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int waitingTime;
    int turnAroundTime;
    int remainingBurstTime;

    Process(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
    }
}

public class SJF {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the No of Processes: ");
        int n = sc.nextInt();

        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the arrival time of process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter the burst time of process " + (i + 1) + ": ");
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
        int completedProcess = 0;

        while (completedProcess < n) {
            Process shortestJob = null;
            for (Process process : processes) {
                if (process.arrivalTime <= currentTime && process.remainingBurstTime > 0) {
                    if (shortestJob == null || process.remainingBurstTime < shortestJob.remainingBurstTime) {
                        shortestJob = process;
                    }
                }
            }
            if (shortestJob != null) {
                currentTime++;
                shortestJob.remainingBurstTime--;

                if (shortestJob.remainingBurstTime == 0) {
                    shortestJob.completionTime = currentTime;
                    shortestJob.turnAroundTime = shortestJob.completionTime - shortestJob.arrivalTime;
                    shortestJob.waitingTime = shortestJob.turnAroundTime - shortestJob.burstTime;

                    completedProcess++;

                }

            } else {
                currentTime++;
            }

        }
        System.out.println("\nSJF Preemptive Scheduling:");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n",
                "Process", "Arrival Time", "Burst Time", "Completion Time", "Turnaround Time", "Waiting Time");

        for (Process process : processes) {
            System.out.printf("%-15d%-15d%-15d%-15d%-15d%-15d\n",
                    process.processId, process.arrivalTime, process.burstTime,
                    process.completionTime, process.turnAroundTime, process.waitingTime);
        }
        sc.close();
    }
}
