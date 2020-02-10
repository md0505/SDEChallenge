package com.paytm.analytics;

import java.util.*;

public interface DataStore {

    boolean add(Double v);

    double get(int pos);

    public double movingAverage();

    public int size(); 

}
