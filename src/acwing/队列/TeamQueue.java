package acwing.队列;

import java.util.*;
public class TeamQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, k = 1;
        while ((n = Integer.parseInt(scanner.nextLine())) != 0) {
            Map<Integer, Integer> map = new HashMap<>();
            Map<Integer, Queue<Integer>> queues = new HashMap<>();
            Queue<Queue<Integer>> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                String[] members = scanner.nextLine().split(" ");
                int total = Integer.parseInt(members[0]);
                Queue<Integer> queue = new LinkedList<>();
                for (int j = 1; j <= total; j++) {
                    Integer num = Integer.parseInt(members[j]);
                    map.put(num, i);
                }
                queues.put(i, queue);
            }
            String cmd;
            System.out.println("Scenario #" + k++);
            while (!(cmd = scanner.nextLine()).equals("STOP")) {
                if (cmd.startsWith("ENQUEUE")) {
                    Integer num = Integer.parseInt(cmd.split(" ")[1]);
                    Integer qnum = map.get(num);
                    Queue<Integer> queue = queues.get(qnum);
                    if (queue.isEmpty()) q.offer(queue);
                    queue.offer(num);
                } else {
                    Queue<Integer> queue = q.peek();
                    System.out.println(queue.poll());
                    if (queue.isEmpty())    q.poll();
                }
            }
            System.out.println();
        }
    }
}