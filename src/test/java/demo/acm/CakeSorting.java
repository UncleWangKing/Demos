package demo.acm;


public class CakeSorting {
    static int[] list = {4,5,1,3,2};
    static Integer m_nSearch = 0;
    static Integer m_nMaxSwap = UpperBound(list);

    static int[] bestList = new int[m_nMaxSwap];
    static int[] tempList = new int[m_nMaxSwap + 1];
    public static void main(String[] args) {
        search(0);

        System.out.println("总搜索次数 = " + m_nSearch);
        print(bestList, m_nMaxSwap);
        System.out.println("最优方案次数 = " + m_nMaxSwap);
    }

    static void Reverse(int[] list, int begin, int end){

        for (; begin < end; begin++, end--) {
            int temp = list[begin];
            list[begin] = list[end];
            list[end] = temp;
        }
    }
    static void print(int[] list, int length){
        for (int i = 0; i < length; i++) {
            System.out.println(list[i]);
        }
    }

    static boolean isSorted(int[] list){
        for (int i = 1; i < list.length; i++)
            if(list[i - 1] > list[i])
                return false;

        return true;
    }

    static void search(int step){
        m_nSearch++;

        int estimate = LowerBound(list);
        if(step + estimate > m_nMaxSwap)
            return;

        if(isSorted(list)){
            if(step < m_nMaxSwap){
                m_nMaxSwap = step;
                for (int i = 0; i < m_nMaxSwap; i++) {
                    bestList[i] = tempList[i];
                }
                return;
            }
        }

        for (int i = 1; i < list.length; i++) {
            Reverse(list, 0, i);
            tempList[step] = i;
            search(step + 1);
            Reverse(list, 0, i);
        }
    }

    private static int UpperBound(int[] list){
        return (list.length - 1) * 2;
    }

    private static int LowerBound(int[] list) {
        int count = 0;
        for (int i = 1; i < list.length; i++) {
            int t = list[i] - list[i - 1];
            if(1 != t && -1 != t)
                count++;
        }
        return count;
    }
}
