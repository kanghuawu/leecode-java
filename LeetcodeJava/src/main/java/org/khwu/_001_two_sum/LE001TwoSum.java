package org.khwu._001_two_sum;

import java.util.HashMap;

public class LE001TwoSum {
    public int[] twoSum(int[] nums, int target) {
    	HashMap<Integer, Integer> find = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
        	if(find.containsKey(target - nums[i])) return new int[] {find.get(target - nums[i]), i};
        	else find.put(nums[i], i);
        }
        return new int[] {0, 0};
    }
    public static void main(String[] arg){
    	int[] nums = new int[] {2, 7, 11, 15};
    	int target = 9;
    	LE001TwoSum test = new LE001TwoSum();
    	int[] toprint = test.twoSum(nums, target);
    	System.out.println(toprint[0] + ", " + toprint[1]);
    }
}
