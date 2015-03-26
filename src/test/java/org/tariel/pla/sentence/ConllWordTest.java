/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tariel.pla.sentence;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public class ConllWordTest {
    
    public ConllWordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of decodeParams method, of class ConllWord.
     */
    @Test
    public void testDecodeParams() {
	System.out.println("decodeParams");
	String params = "PST|INS|SG|ADJP|M|PERF";
	ConllWord instance = new ConllWord();
	instance.decodeParams(params);

	assertTrue(instance.tense.equals("PST")
		&& instance.lcase.equals("INS")
		&& instance.number.equals("SG")
		&& instance.verbrepr.equals("ADJP")
		&& instance.gender.equals("M")
		&& instance.aspect.equals("PERF"));
    } 
}
