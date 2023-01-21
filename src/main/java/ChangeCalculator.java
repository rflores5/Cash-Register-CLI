import java.util.*;

public class ChangeCalculator {

    private final int[] bills;

    public ChangeCalculator(int twenty, int ten, int five, int two, int one){
        this.bills = totalBills(twenty, ten, five, two, one);
    }

    //creates a 1-D array of all the bills denoted by their denomination ordered in ascending order
    //ex. [1,2,0,3,2] -> [1,1,2,2,2,10,10,20]
    public int[] totalBills(int twenty, int ten, int five, int two, int one){
        int length = twenty + ten + five + two + one;
        int[] arr = new int[length];
        Arrays.fill(arr, 0,one, 1);
        Arrays.fill(arr, one, one+two, 2);
        Arrays.fill(arr, one+two, one+two+five, 5);
        Arrays.fill(arr, one+two+five, one+two+five+ten, 10);
        Arrays.fill(arr, one+two+five+ten, length, 20);
        return arr;
    }

    //converts array of all bills into a length 5 array with counts of each denomination similar to CL output
    //ex. [1,1,2,2,2,10,10,20] -> [1,2,0,3,2]
    private int[] organizeBills(int[] arr){
        int[] result = new int[5];
        Arrays.fill(result, 0);
        for (int i: arr){
            if(i==1){
                result[4] += 1;
            }else if(i==2) {
                result[3] += 1;
            }else if(i==5){
                result[2] += 1;
            }else if(i==10){
                result[1] += 1;
            }else if(i==20){
                result[0] += 1;
            }
        }
        return result;
    }

    //Finds combination of bills that give exact change or null if none can be found
    public int[] findChange(int change) {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> combo = new LinkedList<>();

        backtrack(combo, change, 0, results);

        int totalCombinations = results.size();
        int[] output;
        if (totalCombinations == 0){
            output = null;
        }else {
            int[] resultArr = results.get(0).stream().mapToInt(Integer::intValue).toArray();
            output = organizeBills(resultArr);
        }
        return output;
    }

    //uses recursive backtrack method to find all possible combinations of change given current amount of each
    //denomination. Modified to only return the result with the fewest number of bills
    private void backtrack(LinkedList<Integer> combo, Integer remain, Integer curr, List<List<Integer>> results) {
        if (remain == 0) {
            // copy the current combination to the result list.
            // returns combination with the least amount of total bills
            if (results.isEmpty()){
                results.add(new ArrayList<Integer>(combo));
            }else{
                if (results.get(0).size() > combo.size()){
                    results.clear();
                    results.add(new ArrayList<Integer>(combo));
                }
            }
            return;
        }
        for (int nextCurr = curr; nextCurr < this.bills.length; ++nextCurr) {
            if ((nextCurr > curr) && (this.bills[nextCurr] == this.bills[nextCurr-1])) {
                continue;
            }
            Integer pick = this.bills[nextCurr];
            // optimization: early stopping
            if (remain - pick < 0) {
                break;
            }
            combo.addLast(pick);
            backtrack(combo, remain-pick, nextCurr+1, results);
            combo.removeLast();
        }
    }
}
