package com.calc.straccumlator;

import org.junit.Test;

import junit.framework.TestCase;

public class StringAccumulatorTest extends TestCase {

	@Test
	public void testEmptyString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 0== sc.add("") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testBlankString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 0== sc.add("                     ") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testSingleValueString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 5==sc.add("5") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testMultiValueString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 17==sc.add("5,3,9") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testMultiValueLongString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 1025==sc.add("1,29,45,50,40,30,20,10,300,100,100,200,100") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testNewLineWithCommaString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 23==sc.add("1\n2\n3,4\n5,6\n2") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testNewLineWithoutCommaString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 23==sc.add("1\n2\n3\n4\n5\n6\n2") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testNewLineInvalidString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 1==sc.add("1,\n") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testDifferentDelimString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 10==sc.add("//;\n1;2;3;4") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testSpecialCharDelimString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 10==sc.add("//*\n1*2*3*4") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testSpecialCharDelimVariableLengthString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 19==sc.add("//****\n1****2****3****4****9") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testDifferentDelimVariableLengthString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 10==sc.add("//;;;;\n1;;;;2;;;;3;;;;4") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testDifferentDelimVariableLengthString1()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 10==sc.add("//@@@@\n1@@@@2@@@@3@@@@4") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testMultiDelimString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 29==sc.add("//@|*|;\n1@2@3*4;5*6;7@1") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testMultiDelimVariableString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 29==sc.add("//@@@|**|;;;;\n1@@@2@@@3**4;;;;5**6;;;;7@@@1") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testGreaterThan1000String()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 21==sc.add("//@@@|**|;;;;\n1@@@2@@@3**4;;;;5**6;;;;7000@@@1001") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testNewLineWithCommaGreaterThan1000String()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			assertTrue( 1016==sc.add("1000\n2\n3,4000\n5,6\n1001\n") );
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
	
	@Test
	public void testNegativeString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			 sc.add("1000\n-22\n3,4000\n5,6\n1001\n");
			 fail("Expected an Exception to be thrown");
		}catch(Exception ex){
			assertEquals("Negative addition not supported -22",ex.getMessage());
		}
    }
	
	@Test
	public void testMultipleNegativeString()
	{
		StringAccumulator sc = new StringAccumulator();
        
		try{
			 sc.add("1000\n-22\n3,4000\n5,-6\n-1001\n");
			 fail("Expected an Exception to be thrown");
		}catch(Exception ex){
			assertEquals("Negative addition not supported -22,-6,-1001",ex.getMessage());
		}
    }

}
