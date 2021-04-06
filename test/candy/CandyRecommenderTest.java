//POD B1 group work 
//Asmaa Chaudhry 
package candy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Goals:
// Cover all lines of the two methods we are testing: 
// readCandyFile() and findLikedCandies(). 
// This means when you run the coverage test, it should highlight all lines of the two methods in green.
// A coverage score that achieves either: 
// 57% for the CandyRecommender.java file or  62% for the src folder. 


class CandyRecommenderTest {
     
    //before each test initialize empty arrays 
    
    @BeforeEach
    void setUp() throws Exception {
        CandyRecommender.initialize();
    }
    
    
// The purpose of this test is to see what happens when the Candy.txt file has the wrong format
// Such as putting in a candy name as a key and the value. 
// The file should be formatted like : candy name: ingredient 1, ingredient 2 
// Initialized the candies list and Ingredient map 
// The expected result will be that the method will not read in the Candy.txt file correctly

    @Test
    void testReadIllFormattedCandyFile() throws FileNotFoundException {
        
        CandyRecommender.readCandyFile("IllFormattedCandy.txt");
       
        List<Candy> candies = CandyRecommender.getCandies();
        List<String> candyName = new ArrayList<>();
        
        IngredientMap iMap = CandyRecommender.getIngredientMap();
        
        for(Candy c: candies) {
            candyName.add(c.getName());
        }
        // check if candy names are stored properly
        assertTrue(candyName.contains("M&Ms"));
        assertTrue(candyName.contains("Peanut M&Ms"));
        assertEquals(2, candyName.size());
        
        // check if ingredient map is stored properly 
        // part 1 
        Collection<Candy> chocCandies = iMap.getCandyWith("CHOCOLATE"); //collection of chocolate candies
        List<String> chocCandyNames = new ArrayList<>(); //list of string with candies that have chococlate in them
        
        for(Candy c: chocCandies) {
            chocCandyNames.add(c.getName());
        }
        assertTrue(chocCandyNames.contains("Peanut M&Ms"));
        assertEquals(2, chocCandyNames.size());
        assertTrue(chocCandyNames.contains("M&Ms"));
        
        
        
        // checking for candy ingredient that is supposed to be a candy name (M&Ms)
        // part 2 
        Collection<Candy> mmCandies = iMap.getCandyWith("CHOCOLATE"); 
        List<String> mmCandyNames = new ArrayList<>(); 
        
        for(Candy c: mmCandies) {
            mmCandyNames.add(c.getName());
        }
        assertTrue(mmCandyNames.contains("M&Ms"));
        assertEquals(1, mmCandyNames.size());
        
        // part 3 
        Collection<Candy> peanutCandies = iMap.getCandyWith("PEANUTS"); 
        List<String> peanutCandyNames = new ArrayList<>(); 
        
        for(Candy c: peanutCandies) {
            peanutCandyNames.add(c.getName());
        }
        assertTrue(peanutCandyNames.contains("Peanut M&Ms"));
        assertEquals(1, peanutCandyNames.size());
    }
  
    
// Testing to see what happens when a txt is formatted correctly 
// Expected output is that candies array contains Hershey and size of list is 1  
    
    @Test
    void testProperCandyFile1() throws FileNotFoundException {
  
      CandyRecommender.readCandyFile("ProperCandy1.txt");
      List<Candy> candies = CandyRecommender.getCandies();
      List<String> candyNames = new ArrayList<>();
      IngredientMap ingredientMap = CandyRecommender.getIngredientMap();
      
      for (Candy c: candies) {
          candyNames.add(c.getName()); }
      
      //check if candy names are stored properly
      assertTrue(candyNames.contains("Hersheys"));
      assertEquals(1, candyNames.size());
      
      //check if iMap is stored properly
      //Chocolate ingredient
      Collection<Candy> cCandies = ingredientMap.getCandyWith("CHOCOLATE");
      List<String> cCandyNames = new ArrayList<>();
      for (Candy c: cCandies) {
          cCandyNames.add(c.getName()); }
      
      assertTrue(cCandyNames.contains("Hersheys"));
      assertEquals(1, cCandyNames.size());
      
  }

    
// Testing to see what happens when a blank .txt file is read
// Expected output is empty candy array size and empty file
    
    @Test
    void testBlankCandyFile() throws FileNotFoundException {
       
        CandyRecommender.readCandyFile("BlankCandy.txt");
        List<Candy> candies = CandyRecommender.getCandies();
        List<String> candyNames = new ArrayList<>();
        IngredientMap ingredientMap = CandyRecommender.getIngredientMap();
        
        assertEquals(0,candies.size());
        
    }
    

// Testing to see what happens when .txt file has 2 candies and 2 different ingredients 
// Expected output is that the candies and ingredients are stored properly 
    
    @Test
    void testProperCandyFile2() throws FileNotFoundException {
      
        CandyRecommender.readCandyFile("ProperCandy2.txt");
        List<Candy> candies = CandyRecommender.getCandies();
        List<String> candyNames = new ArrayList<>();
        IngredientMap ingredientMap = CandyRecommender.getIngredientMap();
        
        for (Candy c: candies) {
            candyNames.add(c.getName()); }
        
        //check if candy names are stored properly
        assertTrue(candyNames.contains("Hersheys"));
        assertTrue(candyNames.contains("Peanut Butter Cups"));
        assertEquals(2, candyNames.size());
        
        //check if iMap is stored properly
        //Peanut Butter ingredient
        Collection<Candy> pbCandies = ingredientMap.getCandyWith("PEANUT BUTTER");
        List<String> pbCandyNames = new ArrayList<>();
        for (Candy c: pbCandies) { 
            pbCandyNames.add(c.getName());
            System.out.println(c.getName());
            }
        
        assertTrue(pbCandyNames.contains("Peanut Butter Cups"));
        assertEquals(1, pbCandyNames.size());
        
        //Chocolate ingredient
        Collection<Candy> cCandies = ingredientMap.getCandyWith("CHOCOLATE");
        List<String> cCandyNames = new ArrayList<>();
        for (Candy c: cCandies) { 
            cCandyNames.add(c.getName());
            System.out.println(c.getName());
            }
        
        assertTrue(cCandyNames.contains("Hersheys"));
        assertEquals(1, cCandyNames.size());    
    }

    
// Testing to see what happens when all candies are disliked
// array of size 0 will be returned 
    
    @Test 
    void testfindLikedCandiesDislike(){
        
      Set <Candy> likedCandies = CandyRecommender.findLikedCandies();
      assertEquals(0, likedCandies.size());

    }
    
    
// Testing to see what happens when 2 ingredients are added to the likes list 
// expected output is that candies that contains those ingredients will be returned 
    
    @Test 
    void testfindLikedCandiesLiked() throws FileNotFoundException{
        
      CandyRecommender.readCandyFile("Candy.txt");
      List<String> likes = new ArrayList();
      CandyRecommender.addToLike("CHOCOLATE");
      CandyRecommender.addToLike("PEPPERMINT");
      Set<Candy> likedCandies = CandyRecommender.findLikedCandies();
      assertEquals(10, likedCandies.size());
    }
    
    
 // Testing to see what happens when an ingredient is added to the 
 // likes list and another ingredient is added to the dislikes list   
    @Test 
    void testfindLikedCandiesDisliked() throws FileNotFoundException{
        
      CandyRecommender.readCandyFile("Candy.txt");
      List<String> likes = new ArrayList();
      CandyRecommender.addToDislikes("CHOCOLATE");
      CandyRecommender.addToLike("PEPPERMINT");
      Set<Candy> likedCandies = CandyRecommender.findLikedCandies();
      assertEquals(10, likedCandies.size());
    }
    
}




