package org.mypackage.fibonacci;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class TestFibonacci {

  @Test
  public void testCollectFibonacci() {
    List<BigInteger> expected = Arrays.asList(ZERO, ONE, ONE, valueOf(2), valueOf(3), valueOf(5), valueOf(8), valueOf(13));
    int requestedSize = 8;
    List<BigInteger> actual = FibonacciApplication.collectFibonacciValues(requestedSize);
    Assert.assertEquals(requestedSize, actual.size());
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testSingleFibonacci() {
    List<BigInteger> singleElementList = FibonacciApplication.collectFibonacciValues(1);
    Assert.assertEquals(1, singleElementList.size());
    Assert.assertEquals(BigInteger.ZERO, singleElementList.get(0));
  }

}
