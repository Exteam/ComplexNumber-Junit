import org.junit.*;
import static org.junit.Assert.*;

public class E8tester {
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("E8tester");
	}

	// check that ComplexDivideByZeroException exists, that it's of proper type, and that
	// it initializes correctly
	@Test public void cdbz_exception_test() {
		ComplexDivideByZeroException e = new ComplexDivideByZeroException("test");
		String errMsg = "ComplexDivideByZeroException must be an AritmeticException";
		assertTrue(errMsg, e instanceof ArithmeticException);
		errMsg = "ComplexDivideByZeroException must know its error message string";
		assertEquals(errMsg, "test", e.getMessage());
	}
	
	// check that InvalidComplexException exists, that it's of proper type, and that
	// it initializes correctly
	@Test public void ic_exception_test() {
		InvalidComplexException e = new InvalidComplexException("test");
		String errMsg = "InvalidComplexException must be a NumberFormatException";
		assertTrue(errMsg, e instanceof NumberFormatException);
		errMsg = "NumberFormatException must know its error message string";
		assertEquals(errMsg, "test", e.getMessage());
	}

	// sample complex values
	private static double[][] complexNums = { 
		{-1.0,-1.0}, {-1.0,-0.5}, {-1.0,0.0}, {-1.0, 0.5}, {-1.0, 1.0}, {-1.0, 1.5}, {-1.0, 2.0}, 
		{-0.5,-1.0}, {-0.5,-0.5}, {-0.5,0.0}, {-0.5, 0.5}, {-0.5, 1.0}, {-0.5, 1.5}, {-0.5, 2.0}, 
		{ 0.0,-1.0}, { 0.0,-0.5}, { 0.0,0.0}, { 0.0, 0.5}, { 0.0, 1.0}, { 0.0, 1.5}, { 0.0, 2.0}, 
		{ 0.5,-1.0}, { 0.5,-0.5}, { 0.5,0.0}, { 0.5, 0.5}, { 0.5, 1.0}, { 0.5, 1.5}, { 0.5, 2.0}, 
		{ 1.0,-1.0}, { 1.0,-0.5}, { 1.0,0.0}, { 1.0, 0.5}, { 1.0, 1.0}, { 1.0, 1.5}, { 1.0, 2.0}, 
		{ 1.5,-1.0}, { 1.5,-0.5}, { 1.5,0.0}, { 1.5, 0.5}, { 1.5, 1.0}, { 1.5, 1.5}, { 1.5, 2.0}, 
		{ 2.0,-1.0}, { 2.0,-0.5}, { 2.0,0.0}, { 2.0, 0.5}, { 2.0, 1.0}, { 2.0, 1.5}, { 2.0, 2.0}
	};

	// check that we can initialize using the first constructor (real, im) and get
	// back what we put in
	@Test public void constructor_1_test() {
		for (double[] pair : complexNums) {
			Complex c = new Complex(pair[0], pair[1]);
			assertEquals("real value mismatch", pair[0], c.R(),0.0001);
			assertEquals("imaginary value mismatch", pair[1], c.I(),0.0001);
		}
	}

	// check that we can initialize using the second constructor (real) and get
	// back what we put in
	@Test public void constructor_2_test() {
		for (double[] pair : complexNums) {
			Complex c = new Complex(pair[0]);
			assertEquals("real value mismatch", pair[0], c.R(),0.0001);
			assertEquals("imaginary value mismatch", 0, c.I(),0.0001);
		}
	}
	
	// check that we can initialize using the third constructor (real) and get
	// back what we put in
	@Test public void constructor_3_test() {
		for (double[] pair : complexNums) {
			String s1 = "" + pair[0];
			Complex c1 = new Complex(s1);
			assertEquals("real value mismatch for " + s1, pair[0], c1.R(),0.0001);
			assertEquals("imaginary value mismatch for " + s1, 0, c1.I(),0.0001);
			String s2 = "" + pair[1] + "i";
			Complex c2 = new Complex(s2);
			assertEquals("real value mismatch for " + s2, 0, c2.R(),0.0001);
			assertEquals("imaginary value mismatch for " + s2, pair[1], c2.I(),0.0001);
			String s3 = "" + pair[1] + "I";
			Complex c3 = new Complex(s3);
			assertEquals("real value mismatch for " + s3, 0, c3.R(),0.0001);
			assertEquals("imaginary value mismatch for " + s3, pair[1], c3.I(),0.0001);
		}
	}
	
	// check that an error is thrown for an invalid input
	private static String[] invalidStrings= {"", "nothing", "1.1.1", "1.2x", "1.2ii"};

	@Test public void constructor_3exep_test() {
		for (String s : invalidStrings) {
			try {
				Complex c = new Complex(s);
				fail("should have thrown an exception for input \"" + s + "\"");
			} catch (InvalidComplexException e) {}
		}
	}

	// check that sums work as expected
	@Test public void test_sums() {
		for (int i=0;  i<complexNums.length;  i++) {
			for (int j=0;  j<complexNums.length; j++) {
				double[] z1 = complexNums[i];
				double[] z2 = complexNums[j];
				double[] z3 = E8data0.cSums[i][j];
				Complex c3 = new Complex(z1[0],z1[1]).add(new Complex(z2[0],z2[1]));
				assertEquals("real value mismatch", z3[0], c3.R(), 0.0001);
				assertEquals("imaginary value mismatch", z3[1], c3.I(), 0.0001);
			}
		}
	}

	// check that differences work as expected
	@Test public void test_diffs() {
		for (int i=0;  i<complexNums.length;  i++) {
			for (int j=0;  j<complexNums.length; j++) {
				double[] z1 = complexNums[i];
				double[] z2 = complexNums[j];
				double[] z3 = E8data1.cDiffs[i][j];
				Complex c3 = new Complex(z1[0],z1[1]).sub(new Complex(z2[0],z2[1]));
				assertEquals("real value mismatch", z3[0], c3.R(), 0.0001);
				assertEquals("imaginary value mismatch", z3[1], c3.I(), 0.0001);
			}
		}
	}
	
	// check that products work as expected
	@Test public void test_prods() {
		for (int i=0;  i<complexNums.length;  i++) {
			for (int j=0;  j<complexNums.length; j++) {
				double[] z1 = complexNums[i];
				double[] z2 = complexNums[j];
				double[] z3 = E8data2.cProds[i][j];
				Complex c3 = new Complex(z1[0],z1[1]).mult(new Complex(z2[0],z2[1]));
				assertEquals("real value mismatch", z3[0], c3.R(), 0.0001);
				assertEquals("imaginary value mismatch", z3[1], c3.I(), 0.0001);
			}
		}
	}
	
	// check that quotients work as expected
	@Test public void test_quots() {
		for (int i=0;  i<complexNums.length;  i++) {
			for (int j=0;  j<complexNums.length; j++) {
				double[] z1 = complexNums[i];
				double[] z2 = complexNums[j];
				double[] z3 = E8data3.cQuots[i][j];
				try {
				   Complex c3 = new Complex(z1[0],z1[1]).div(new Complex(z2[0],z2[1]));
				   assertEquals("real value mismatch", z3[0], c3.R(), 0.0001);
				   assertEquals("imaginary value mismatch", z3[1], c3.I(), 0.0001);
				} catch (ComplexDivideByZeroException e) {}
			}
		}
	}

	// check that divide by zero gets flagged as it should
	@Test public void test_dbz() {
		try {
			new Complex(10.0,10.0).div( new Complex(0.0, 0.0) );
			fail("divide by zero should result in an exception");
		} catch(ComplexDivideByZeroException e) {}
	}

	// check if the string formatting is as expected
	@Test public void test_tostring() {
		for (int i = 0;  i < complexNums.length;  i++) {
			double[] pair  = complexNums[i];
			String exp = complexNames[i];
			Complex c = new Complex(pair[0], pair[1]);
			String act = c.toString();
			assertEquals(exp, act);
		}
	}


	private String[] complexNames = {
           "-1.0-1.0i", "-1.0-0.5i", "-1.0+0.0i", "-1.0+0.5i", "-1.0+1.0i", "-1.0+1.5i", "-1.0+2.0i", 
           "-0.5-1.0i", "-0.5-0.5i", "-0.5+0.0i", "-0.5+0.5i", "-0.5+1.0i", "-0.5+1.5i", "-0.5+2.0i", 
           "0.0-1.0i", "0.0-0.5i", "0.0+0.0i", "0.0+0.5i", "0.0+1.0i", "0.0+1.5i", "0.0+2.0i", 
           "0.5-1.0i", "0.5-0.5i", "0.5+0.0i", "0.5+0.5i", "0.5+1.0i", "0.5+1.5i", "0.5+2.0i", 
           "1.0-1.0i", "1.0-0.5i", "1.0+0.0i", "1.0+0.5i", "1.0+1.0i", "1.0+1.5i", "1.0+2.0i", 
           "1.5-1.0i", "1.5-0.5i", "1.5+0.0i", "1.5+0.5i", "1.5+1.0i", "1.5+1.5i", "1.5+2.0i", 
           "2.0-1.0i", "2.0-0.5i", "2.0+0.0i", "2.0+0.5i", "2.0+1.0i", "2.0+1.5i", "2.0+2.0i"
	};

}
