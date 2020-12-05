package mini_projects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class TowersOfHanoiSolver {

    // Disk object class that contains size of the disk
    private static class Disk {
        final int size;

        private Disk(int size) {
            this.size = size;
        }
    }

    private final Stack<Disk> pegA = new Stack<>();
    private final Stack<Disk> pegB = new Stack<>();
    private final Stack<Disk> pegC = new Stack<>();
    private final int size;
    private int moveCount = 0;


    public TowersOfHanoiSolver(int n) {
        for (int i = n; i > 0; i--) {
            pegA.push(new Disk(i));
        }
        this.size = n;
    }

    public void solve() {
        drawTowers();
        moveTower(pegA, pegA.size(), pegC, pegB);
    }

    private void moveTower(Stack<Disk> source, int height, Stack<Disk> dest, Stack<Disk> temp) {
        // if the tower is 1dick tall, just move that dick to the destination peg
        if (height == 1) {
            Disk disk = source.pop();
            dest.push(disk);

            drawTowers();
            return;
        }

        // moving all the disks except the bottom large dick to the temp peg
        moveTower(source, height-1, temp, dest);

        // move the bottom large dick to the destination peg
        Disk bottomDisk = source.pop();
        dest.push(bottomDisk);

        drawTowers();

        // move all the disks in temp peg to the destination peg
        moveTower(temp, height-1, dest, source);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int height;

        do {
            System.out.print("Enter height of Towers: ");
            height = Integer.parseInt(reader.readLine());
        } while (height < 1);

        TowersOfHanoiSolver tohs = new TowersOfHanoiSolver(height);
        tohs.solve();
    }

    /*
     *       —┃—          —┃—          —┃—
     *      ——┃——        ——┃——        ——┃——
     *     ———┃———      ———┃———      ———┃———
     *    ————┃————    ————┃————    ————┃————
     *   —————┃—————  —————┃—————  —————┃—————
     * ^^^^^^[A]^^^^^^^^^^[B]^^^^^^^^^^[C]^^^^^^
     */

    private void drawTowers() {
        Stack<Disk> tmpC = new Stack<>();
        Stack<Disk> tmpA = new Stack<>();
        Stack<Disk> tmpB = new Stack<>();
        Disk disk;

        StringBuilder sb = new StringBuilder();

        System.out.println();
//        System.out.println("-".repeat(size*6 + 13));
        for (int i = 1; i <= size; i++) {

            sb.append("  ");

            if(pegA.size() == size-(i-1) && pegA.size() != 0) {
                disk = pegA.pop();
                tmpA.push(disk);
                sb.append(" ".repeat(size-disk.size));
                sb.append("—".repeat(disk.size)).append("┃").append("—".repeat(disk.size));
                sb.append(" ".repeat(size-disk.size));
            } else {
                sb.append(" ".repeat(size)).append("┃").append(" ".repeat(size));
            }

            sb.append("  ");

            if(pegB.size() == size-(i-1) && pegB.size() != 0) {
                disk = pegB.pop();
                tmpB.push(disk);
                sb.append(" ".repeat(size-disk.size));
                sb.append("—".repeat(disk.size)).append("┃").append("—".repeat(disk.size));
                sb.append(" ".repeat(size-disk.size));
            } else {
                sb.append(" ".repeat(size)).append("┃").append(" ".repeat(size));
            }

            sb.append("  ");

            if(pegC.size() == size-(i-1) && pegC.size() != 0) {
                disk = pegC.pop();
                tmpC.push(disk);
                sb.append(" ".repeat(size-disk.size));
                sb.append("—".repeat(disk.size)).append("┃").append("—".repeat(disk.size));
                sb.append(" ".repeat(size-disk.size));
            } else {
                sb.append(" ".repeat(size)).append("┃").append(" ".repeat(size));
            }

            sb.append(System.lineSeparator());
        }
        sb.append("^".repeat(size + 1)).append("[A]")
                .append("^".repeat(2 * size)).append("[B]")
                .append("^".repeat(2 * size)).append("[C]")
                .append("^".repeat(size + 1)).append(System.lineSeparator()).append(" Move Count: ").append(moveCount++);
        System.out.println(sb.toString());
//        System.out.println("-".repeat(size*6 + 13));
        System.out.println();

        while (!tmpA.isEmpty()) pegA.push(tmpA.pop());
        while (!tmpB.isEmpty()) pegB.push(tmpB.pop());
        while (!tmpC.isEmpty()) pegC.push(tmpC.pop());

    }
}
