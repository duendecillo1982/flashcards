class Problem {

    public static void main(String args[]) {
        String operator = args[0];
        int arg1 = Integer.valueOf(args[1]);
        int arg2 = Integer.valueOf(args[2]);
        if (operator.equals("+")) {
            System.out.println(arg1 + arg2);
        } else if (operator.equals("-")) {
            System.out.println(arg1 - arg2);
        } else if (operator.equals("*")) {
            System.out.println(arg1 * arg2);
        } else {
            System.out.println("Unknown operator");
        }
    }
}