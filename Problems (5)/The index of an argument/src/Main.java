class Problem {

    public static void main(String[] args) {
        int result = -1;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.equals("test")) {
                result = i;
            }
        }
        System.out.println(result);
    }
}