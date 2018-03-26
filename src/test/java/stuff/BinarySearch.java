package stuff;

import java.util.*;

public class BinarySearch {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            list.add(random.nextInt(10000));
        }
        for (Integer num:list)
            System.out.println(num);
//        Collections.sort(list);
//        System.out.println(get(list, 300, 0, list.size() - 1));
    }
    public static int get(List<Integer> list, Integer num){
        int begin = 0;
        int end = list.size() - 1;
        int index = (end - begin) / 2;
        while(end != begin) {
            if (list.get(index).equals(num))
                return index;
            else if (num > list.get(index)) {
                begin = index + 1;
                index = (end - begin) / 2 + begin;
            } else {
                end = index - 1;
                index = (end - begin) / 2 + begin;
            }
        }
        if(list.get(end).equals(num))
            return end;
        return -1;
    }
    //此处begin end 不能是Integer
    public static int get(List<Integer> list, Integer num, int begin, int end){
        if(begin == end){
            if(list.get(end).equals(num))
                return end;
            else
                return -1;
        }

        int index = (end - begin) / 2 + begin;

        if (list.get(index).equals(num))
            return index;
        else if (num > list.get(index)) {
            return get(list, num, index + 1, end);
        } else {
            return get(list, num, begin, index - 1);
        }
    }
}
