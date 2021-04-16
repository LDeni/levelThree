import java.util.ArrayList;


    public class Tasks {
        public Tasks() {
        }

        public static void main(String[] args) {

               Integer[] array = {3, 8, 4, 5, 0, 5, 5, 4, 8, 2, 1};
     //        int[] array = {1, 1, 4, 1, 1, 1, 1, 4, 1, 1, 4};

            System.out.println(getNumbersAfter4(array));
   //       System.out.println(arrayHas1and4(array));

        }

        public static boolean arrayHas1and4(Integer[] array) {
            int counter1 = 0;
            int counter4 = 0;
            for (int i = 0; i < array.length; i++) {
                if ((array[i]) == 1) {
                    counter1++;
                } else if ((array[i]) == 4) {
                    counter4++;
                }
            }
            if (counter1 != 0 && counter4 != 0) {
                return true;
            } else {
                return false;
            }
        }

        public static Integer[] getNumbersAfter4(Integer[] array) {
            ArrayList<Integer> arrayListAfter4 = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                arrayListAfter4.add(array[i]);
                if (array[i] == 4)
                    arrayListAfter4.clear();
            }

            if (array.length == arrayListAfter4.size()) {
                throw new RuntimeException("Number 4 is not included in array");
            }
            Integer[] result = new Integer[arrayListAfter4.size()];
            for (int i = 0; i < result.length ; i++) {
                result[i] = arrayListAfter4.get(i);
            }
            return result;
        }
    }

