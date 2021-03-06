/**
 * @author Pod B1
 * Asmaa Chaudhry 
 * CandyReccomender JUNIT tests
 */

package candy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * This program reads candies and their ingredients from a file.  It then asks 
 * the user which of those ingredients they like/dislike.  Finally, it rates 
 * the candies and tells the user which ones they would probably like.
 */
public class CandyRecommender {
    // All known candies
    private static List<Candy> candies = new ArrayList<>();
    
    // The ingredients the user likes
    private static List<String> likes = new ArrayList<>();
    
    // The ingredients the user dislikes
    private static List<String> dislikes = new ArrayList<>();
    
    // A map from ingredients to candies containing the ingredient.
    private static IngredientMap ingredientMap = new IngredientMap();
    
    
    /**
     * added for testing
     * clear out arrays for testing  
     */
    static void initialize() {
        candies = new ArrayList<>();
        likes = new ArrayList<>(); 
        dislikes = new ArrayList <>();
        ingredientMap = new IngredientMap();
        Set<Candy> possible = new HashSet<>();
    }
    
    /**
     * Read the candy information from the file called Candy.txt.  The file 
     * should be formatted like this:  candy name:ingredient 1, ingredient 2
     * Initializes the candies list and the ingredientMap.
     * @throws FileNotFoundException if the Candy.txt file cannot be found.
     */
    static void readCandyFile(String filename) throws FileNotFoundException {
        try (Scanner in = new Scanner (new File (filename))) {
            // Read in each candy
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] parsedLine = line.split(":");
                String name = parsedLine[0];
                String[] ingredients = parsedLine[1].split(",");
                Candy candy = new Candy( name, ingredients);
                candies.add(candy);
                
                // Add the information to the ingredient map.
                for (String ingredient : ingredients) {
                    ingredientMap.add(ingredient, candy);
                }
            }
        }
        System.out.println("Read " + candies.size() + 
                           " candies from the file.");
    }
   
    /**
     * For each ingredient found in any candy, ask the user if they like, 
     * dislike, or are neutral about that ingredient.
     */
    static void getUserPreferences() {
        try (Scanner in = new Scanner (System.in)) {
        
            // Ask the user about each ingredient
            for (String ingredient : ingredientMap.ingredients()) {
                System.out.print ("How much do you like " + ingredient + 
                                  "? (1-like, 0-ok, -1-dislike) ");
                String answer = in.nextLine();
                try {
                    int pref = Integer.parseInt(answer);
                    switch (pref) {
                    case 1:
                        likes.add(ingredient);
                        break;
                    case -1:
                        dislikes.add(ingredient);
                        break;
                    default:
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter 1, 0, or -1");
                }
            }
        }
    }
  
    /**
     * Recommend candies that you think the user will like based upon their
     * likes and dislikes and what the candies contain.  It will not suggest
     * any candidates that contain ingredients the user dislikes.  It will 
     * give a score to candies that contain only ingredients the user likes.
     * A higher score is better.
     * @return the candies that this user would probably like
     */
    static Set<Candy> findLikedCandies() {
        Set<Candy> possible = new HashSet<>();
        for (String ingredient : likes) {
            possible.addAll (ingredientMap.getCandyWith(ingredient));
        }
        for (String ingredient : dislikes) {
            possible.removeAll (ingredientMap.getCandyWith(ingredient));
        }
        return possible;
    }
   
    /**
     * Outputs the list of candies to the screen
     * @param possible the candies to output
     */
    static void recommendCandies(Set<Candy> possible) {
        if (possible.size() == 0) {
            System.out.println("Too picky! No candy recommendations for you.");            
        }
        
        for (Candy c: possible) {
            System.out.println(c.getName() + ": " + c.score(likes));
        }
    }
      
    /**
     * added for testing
     * @return list of candies
     */ 
    static List<Candy> getCandies(){
       return candies; 
    }
       
    /**
     * added for testing
     * @return list of likes
     */
    static List<String> getLikes(){
        return likes; 
    }
    
    /**
     * added for testing
     * @return list of dislikes
     */
    static List<String> getDislikes(){
        return dislikes; 
    }
    
    /**
     * added for testing
     * @return IngredientMap
     */ 
    static IngredientMap getIngredientMap() {
        return ingredientMap;
    }
    
    /**
     * added for testing
     * @param String Ingredients added to likes list
     */
    static void addToLike(String ingredient) {
        likes.add(ingredient);
    }
    
    /**
     * added for testing
     * @param String Ingredients added to dislikes list
     */
    static void addToDislikes(String ingredient) {
        dislikes.add(ingredient);
    }
    
    /**
     * added for testing
     * @return findLikedCandies() method
     */ 
    public static Set<Candy> getFindlikedCandies(){
        return findLikedCandies();
    }
    
    /**
     * Reads the candies from a file, asks the user for their likes and 
     * dislikes, and makes a recommendation
     * @param args none
     */
    public static void main(String[] args) {
        try {
            readCandyFile("Candy.txt");
            getUserPreferences();
            Set<Candy> likedCandies = findLikedCandies();
            recommendCandies(likedCandies);
        } catch (FileNotFoundException e) {
            System.out.println ("Unable to read the Candy.txt file");
        }
    }

}









