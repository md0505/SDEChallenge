package com.paytm.analytics;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for DataStore interface
 */
public class DataStoreTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataStoreTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DataStoreTest.class );
    }

    /**
     * Basic Test :-)
     */
    public void testBasicDataStore()
    {
        DataStore store = new DataStoreImpl(3);
        Assert.assertTrue( "store.size()==0 ", store.size()==0 );
        for (double d : new double[]{ 3.8, 9.4 })
            store.add(d);
        Assert.assertTrue( "store.size()==2", store.size()==2 );
        Assert.assertTrue( "store.movingAverage()==(3.8 + 9.4)/2.0", 
                           store.movingAverage()==(3.8 + 9.4)/2.0 );
        store.add(5.1);
        Assert.assertTrue( "store.movingAverage()==(3.8 + 9.4 + 5.1)/3.0",
                           store.movingAverage()==(3.8 + 9.4 + 5.1)/3.0 );
        for (double d : new double[]{ 100.3, 12.7 })
            store.add(d);
        Assert.assertTrue( "store.movingAverage()==(5.1 + 100.3 + 12.7)/3.0",
                           store.movingAverage()==(5.1 + 100.3 + 12.7)/3.0 );
        Assert.assertTrue( "store.get(3)==100.3",
                           store.get(3)==100.3 );
    }

}
