package com.smart.spel;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpelTests {

	@Test
	public void testStringConcat() {
		
		ExpressionParser parser = new SpelExpressionParser();
		
		Expression exp = parser.parseExpression("'Hello ' + 'World'");
		
		String message = (String) exp.getValue();
		
		assertEquals("Result should be Hello World", "Hello World", message);
	}

}
