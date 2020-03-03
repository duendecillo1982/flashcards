

/* Please, do not rename it */
class Problem {

    public static void main(String args[]) {
        String operator = args[0];
        // write your code here
        int[] array = new int[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            array[i] = Integer.valueOf(args[i+1]);
        }
        if (operator.equals("MAX")) {
            int max = array[0];
            for (int j = 0; j < array.length; j++) {
                if (array[j] > max) {
                    max = array[j];
                }
            }
            System.out.println(max);
        } else if (operator.equals("MIN")) {
            int min = array[0];
            for (int j = 0; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                }
            }
            System.out.println(min);
        } else if (operator.equals("SUM")) {
            int sum = 0;
            for (int j = 0; j < array.length; j++) {
                sum += array[j];
            }
            System.out.println(sum);
        }
    }
}