/*
The knapsack problem is a problem in combinatorial optimization:
Given a set of items, each with a weight and a value, determine the number of each item
to include in a collection so that the total weight is less than or equal to a given limit
and the total value is as large as possible. It derives its name from the problem faced by
someone who is constrained by a fixed-size knapsack and must fill it with the most valuable items.
The most common problem being solved is the 0-1 knapsack problem, which restricts the number
x_{i} of copies of each kind of item to zero or one. Given a set of n items numbered from 1 up to n,
each with a weight w_{i} and a value v_{i}, along with a maximum weight capacity W
 */

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsackProblem
{

    // Create main method
    public  static void main(String[] args)
    {
        int[] weight = {10, 40, 20, 30};
        int[] value = {60, 40, 100, 120};
        int capacity = 50;

        double maxValue = getMaxValue(weight, value, capacity);
        System.out.println("The maximum value is = " + maxValue);
    }

    // Create getMaxValue method ie. the function to get maximum value
    public static double getMaxValue(int[] weight, int[] value, int capacity)
    {
        ItemValue[] itemVal = new ItemValue[weight.length];

        for (int i = 0; i < weight.length; i++)
        {
            itemVal[i] = new ItemValue(weight[i], value[i], i);

        }

        // Sort items by value
        Arrays.sort(itemVal, new Comparator<ItemValue>()
        {
            @Override
            public int compare(ItemValue o1, ItemValue o2) {
                return o2.cost.compareTo(o1.cost);
            }
        });

        double totalValue = 0d;

        for (ItemValue i:itemVal)
        {
            int curWeight = (int) i.weight;
            int curValue = (int) i.value;

            if (capacity-curWeight >= 0)
            {
                capacity = capacity - curWeight; // we can pick this weight into the Knapsack
                totalValue += curValue;

            }
            else{
                // item can't be picked
                double fraction = ((double) capacity/(double)curWeight);
                totalValue += (curValue*fraction);
                capacity = (int)(capacity - (curWeight*fraction));
                break;
            }
        }

        return  totalValue;

    }

    // item value class

    static  class ItemValue
    {
        Double cost;
        double weight, value, ind;

        // item value function
        public  ItemValue(int weight, int value, int ind)
        {
            this.weight = weight;
            this.value = value;
            this.ind = ind;
            cost = new Double(value/weight);

        }

    }

}
