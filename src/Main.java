class Main {

    private static final int SIZE = 10000000;
    private static final int HALF = SIZE / 2;

    public static void main(String s[]) {
        Main o = new Main();
        o.runOneThread();
        o.runTwoThreads();

    }

        public float[] calculate(float[] arr) {
            for (int i = 0; i < arr.length; i++){
                arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));}
            return arr;
        }

        public void runOneThread() {

            float[] arr1 = new float[SIZE];
            for (int i = 0; i < arr1.length; i++) arr1[i] = 1;
            long a = System.currentTimeMillis();
            calculate(arr1);

            long b = System.currentTimeMillis();



            System.out.println("Замер времени по первому методу " + (b - a));
        }

        public void runTwoThreads() {
            float[] arr1 = new float[SIZE];
            float[] arr2 = new float[HALF];
            float[] arr3 = new float[HALF];
            for (int i = 0; i < arr1.length; i++) arr1[i] = 1;

            long a = System.currentTimeMillis();

            System.arraycopy(arr1, 0, arr2, 0, HALF);
            System.arraycopy(arr1, HALF, arr3, 0, HALF);

            Thread t1 = new Thread() {
                public void run() {
                    float[] a1 = calculate(arr2);
                    System.arraycopy(a1, 0, arr2, 0, a1.length);
                }
            };

            t1.start();

            new Thread() {
                public void run() {
                    float[] a2 = calculate(arr3);
                    System.arraycopy(a2, 0, arr3, 0, a2.length);
                }
            }
            .start();

            System.arraycopy(arr2, 0, arr1, 0, HALF);
            System.arraycopy(arr3, 0, arr1, HALF, HALF);
            System.out.println("Замер времени по второму методу:" + (System.currentTimeMillis() - a));
        }


}
