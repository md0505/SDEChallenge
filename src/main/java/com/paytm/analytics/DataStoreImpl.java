package com.paytm.analytics;

import java.util.*;

public class DataStoreImpl implements DataStore {

    private List<Double> nums;
    private double sum = 0.0;
    private int n = 1;

    public DataStoreImpl(int n) { 
        nums = new ArrayList<Double>(); 
        this.n = n;
    }

    public boolean add(Double v) {
        boolean ok = nums.add(v);
        if (ok) {
            sum += v;
            if (nums.size() > n)
                sum -= nums.get(nums.size() - n - 1);
        }
        return ok;
    }


    public double get(int pos) {
        return nums.get(pos);
    }

    public double movingAverage() {
        return sum / (nums.size() > n ? n : nums.size()); 
    }

    public int size() {
        return nums.size();
    }
}
