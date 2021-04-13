/**
 * @author Asmaa Chaudhry 
 * CandyTest JUNIT assignment 
 */

package candy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Conducting JUNIT tests for Candy.java file 
 */

class CandyTest {
    
    /**
     * Before each JUNIT test
     * Call on Candy.Initialize to clear arrays and name
     */
    @BeforeEach
    void setUp() throws Exception {
       Candy.initialize();
    }
    
    /**
     * Testing empty string Name with list of ingredients
     * Expected output candy name and ingredients stored properly
     */
    @Test
    void testBlankCandyNameConstructor() {
        
        //set up String [] ingredients
        String [] ingredients1 = new String [3];
        ingredients1 [0] = "CHOCOLATE";
        ingredients1 [1] = "CARAMEL";
        ingredients1 [2] = "PEANUTS";
        
        //call Constructor method, pass in string name and ingredients array
        Candy myCandy1 = new Candy(" ", ingredients1);
        
        //check if candy name and ingredients are stored properly
        assertTrue(myCandy1.getName().contains(" "));
        assertEquals(3, myCandy1.getIngredients().size());        
    }
    
    /**
     * Testing String candy Name "Snickers" with list of ingredients
     * Expected output candy name and ingredients stored properly
     */
    @Test
    void testCandyNameConstrcutor() {
        
        //set up String [] ingredients
        String [] ingredients2 = new String [3];
        ingredients2 [0] = "CHOCOLATE";
        ingredients2 [1] = "CARAMEL";
        ingredients2 [2] = "PEANUTS";
        
        //call Constructor method, pass in string name and ingredients array
        Candy myCandy2 = new Candy("Snickers", ingredients2);
        
        //check if candy name and ingredients are stored properly
        assertTrue(myCandy2.getName().contains("Snickers"));
        assertEquals(3, myCandy2.getIngredients().size());
        assertTrue(myCandy2.getIngredients().contains("CHOCOLATE"));  
    }
    
    /**
     * Testing Score method from Candy
     * Add all Snicker Ingredients to likes list 
     * Calculate score 
     * Expected output of score is 10 
     */
    @Test
    void testLikedCandyScore() {
        
        //set up String [] ingredients
        String [] ingredients3 = new String [3];
        ingredients3 [0] = "CHOCOLATE";
        ingredients3 [1] = "CARAMEL";
        ingredients3 [2] = "PEANUTS";
        
        //call Constructor method, pass in string name and ingredients array
        Candy myCandy3 = new Candy("Snickers", ingredients3);
        
        //add ingredients to liked list array
        List<String> likes = new ArrayList<>();
        likes.add("CHOCOLATE");
        likes.add("CARAMEL");
        likes.add("PEANUTS");
        
        //ingredient not in list added -- does not affect score 
        likes.add("TOFFEE");
        
        //calculate score with Candy score method 
        int likedCandyScore = myCandy3.score(likes);
        
        //check if score is 10 
        assertEquals(10, likedCandyScore); 
    }
    
    /**
     * Testing Score method from Candy
     * 0 Snickers ingredients are added to likes list
     * Calculate score 
     * Expected output of score is 0 
     */
    @Test
    void testNoLikedCandyScore() {
        //set up String [] ingredients
        String [] ingredients4 = new String [3];
        ingredients4 [0] = "CHOCOLATE";
        ingredients4 [1] = "CARAMEL";
        ingredients4 [2] = "PEANUTS";
        
        //call Constructor method, pass in string name and ingredients array
        Candy myCandy4 = new Candy("Snickers", ingredients4);
        
        //add ingredients to liked list array
        List<String> likes = new ArrayList<>();
        
        //calculate score with Candy score method 
        int noLikedCandyScore = myCandy4.score(likes);
        
        //check if score is 0 
        assertEquals(0, noLikedCandyScore);    
    }
    
    /**
     * Testing Score method from Candy
     * Half of Snicker ingredients are added to likes list
     * Calculate score 
     * Expected output of score is 5 
     */
    @Test
    void checkSomeLikedCandyScore() {
        //set up String [] ingredients
        String [] ingredients4 = new String [4];
        ingredients4 [0] = "CHOCOLATE";
        ingredients4 [1] = "CARAMEL";
        ingredients4 [2] = "PEANUTS";
        ingredients4 [3] = "TOFFEE";
        
        //call Constructor method, pass in string name and ingredients array
        Candy myCandy5 = new Candy("Snickers", ingredients4);
        
        //add ingredients to liked list array
        List<String> likes = new ArrayList<>();
        likes.add("CARAMEL");
        likes.add("PEANUTS");
        
        //check to see if ingredients are stored properly
        //check if likes list contains an ingredient 
        assertTrue(myCandy5.getIngredients().contains("CARAMEL"));
        assertTrue(myCandy5.getIngredients().contains("PEANUTS"));
        assertTrue(likes.contains("PEANUTS"));        
        
        //calculate score with Candy score method 
        int someLikedCandyScore = myCandy5.score(likes);
        
        //check if score is 5 
        assertEquals(5, someLikedCandyScore); 
        
    }    
}











