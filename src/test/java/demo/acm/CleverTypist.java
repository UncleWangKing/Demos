package demo.acm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CleverTypist {
    static int N = 1000005;
    static int M = 7;
    static int UP = 1;
    static int DOWN = -1;
    static int factor[] = new int[M];
    static int num[] = new int[6];

    static boolean vis[][] = new boolean[N][6];
    static class Node{
        int val, idx, step;
        Node(int v, int i, int s) {
            val = v;
            idx = i;
            step = s;
        }
    }

    static int swap(int num, int idx1, int idx2){
        int v1 = (num / factor[idx1]) % 10;
        int v2 = (num / factor[idx2]) % 10;
        num += (v2 - v1) * factor[idx1];
        num += (v1 - v2) * factor[idx2];
        return num;
    }

    static int up_down(int num, int idx, int ops){
        return num + ops * factor[idx];
    }

    static int bfs(int orig, int dest){
        List<Node> qu = new ArrayList<Node>();
        qu.add(new Node(orig, 0, 0));
        vis[orig][0] = true;
        while(0 != qu.size()){
            Node cur = qu.get(0);
            qu.remove(0);
            int sta = cur.val, idx = cur.idx, step = cur.step;
            if(sta == dest)
                return step;
            ++step;
            if(idx != 0){ //swap0
                int next = swap(sta, 0, idx);
                if(!vis[next][idx]) {
                    qu.add(new Node(next, idx, step));
                    vis[next][idx] = true;
                }
            }
            if(idx != 5){ //swap1
                int next = swap(sta, 5, idx);
                if(!vis[next][idx]) {
                    qu.add(new Node(next, idx, step));
                    vis[next][idx] = true;
                }
            }
            int val = (sta / factor[idx]) % 10;
            if(val != 9 && val != num[idx]){ //up
                int next = up_down(sta, idx, UP);
                if(!vis[next][idx]) {
                    qu.add(new Node(next, idx, step));
                    vis[next][idx] = true;
                }
            }
            if(val != 0 && val != num[idx]){ //down
                int next = up_down(sta, idx, DOWN);
                if(!vis[next][idx]) {
                    qu.add(new Node(next, idx, step));
                    vis[next][idx] = true;
                }
            }
            if(idx != 0 && (idx == 5 || val == num[idx])){ //left
                if(!vis[sta][idx - 1]) {
                    qu.add(new Node(sta, idx - 1, step));
                    vis[sta][idx - 1] = true;
                }
            }
            if(idx != 5 && (idx == 0 || val == num[idx])){ //right
                if(!vis[sta][idx + 1]) {
                    qu.add(new Node(sta, idx + 1, step));
                    vis[sta][idx + 1] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int orig = Integer.valueOf(sc.next());
        int dest = Integer.valueOf(sc.next());
        factor[5] = 1;
        for(int i = 4; i >= 0; --i)
            factor[i] = 10 * factor[i + 1];
        for(int i = 0; i < 6; ++i) {
            num[i] = dest / (int) Math.pow(10, 5-i) % 10;
        }

//        for (int i = 0; i < 6; i++) {
//            System.out.print(factor[i] + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < 6; i++) {
//            System.out.print(num[i] + " ");
//        }
//        System.out.println();
        System.out.println(bfs(orig, dest));
    }
}
