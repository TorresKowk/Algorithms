public class basic {

    public void daffodilNum(int digit) {
        // print 2 daffodil Number per row
        int count = 0;
        for (int i = (int) Math.pow(10, digit-1); i < (int) Math.pow(10, digit); i++) {
            int sum = 0;
            for (int index = 0; index < digit; index++) {
                int curDigit = (i / ((index == 0) ? 1 : ((int) Math.pow(10, index)))) % 10;
                sum += Math.pow(curDigit, 3);
            }
            if (sum == i) {
                System.out.print(i);
                System.out.print(' ');
                count++;
                if (count == 2) {
                    System.out.println();
                    count = 0;
                }
            }
        }
    }

    public int maxSumSubarray(int[] array) {
        //base case
        if (array == null || array.length == 0) return -1;

        int curSum = 0;
        int finalSum = Integer.MIN_VALUE;
        int curLeft = 0;
        int curRight = 0;
        while (curRight < array.length) {
            curSum += array[curRight];
            if (curRight + 1 < array.length && array[curRight] >= array[curRight + 1]) {
                finalSum = Math.max(finalSum, curSum);
                curSum = 0;
                curLeft = curRight + 1;
            } else if (curRight == array.length) {
                finalSum = Math.max(finalSum, curSum);
            }
            curRight++;
        }
        return finalSum;
    }

    public static void main(String[] args) {
        basic b = new basic();
        b.daffodilNum(3);
        int[] array1 = {4, 2, 1, 5, 6, 7, 4, 3};
        System.out.println(b.maxSumSubarray(array1));
    }

}
