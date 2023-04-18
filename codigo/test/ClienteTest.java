package test;

import business.*;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ClienteTest {
    
    @Test
    public void demo(){
        assertTrue(1 + 1 == 2);
    }

    @BeforeEach
    public void setUp(){
        Serie s = new Serie("Breaking Bad", "Drama", "Portuguese", 25);
        Cliente c = new Cliente("xX_TesterCraft_Xx", "teste123");
    }
    
    @Test
    public void test() {
    	
    }
    


}
