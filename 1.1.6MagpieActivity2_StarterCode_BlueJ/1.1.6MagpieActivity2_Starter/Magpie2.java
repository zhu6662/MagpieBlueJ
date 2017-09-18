/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie2
{
    /**
     * Get a default greeting   
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    
    /**
     * Search for one word in phrase. The search is not case
     * sensitive. This method will check that the given goal
     * is not a substring of a longer string (so, for
     * example, "I know" does not contain "no").
     * 
     * @param statement
     *            the string to search
     * @param goal
     *            the string to search for
     * @param startPos
     *            the character of the string to begin the
     *            search at
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal,
    int startPos)
    {
        String phrase = statement.trim();
        // The only change to incorporate the startPos is in
        // the line below
        int psn = phrase.toLowerCase().indexOf(
                goal.toLowerCase(), startPos);
        int iteration = 1;
        // Refinement--make sure the goal isn't part of a
        // word
        while (psn >= 0)
        {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0)
            {
                before = phrase.substring(psn - 1, psn)
                .toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(
                    psn + goal.length(),
                    psn + goal.length() + 1)
                .toLowerCase();
            }
            System.out.println("iteration: " + iteration);
            System.out.println("psn: " + psn);
            System.out.println("before: " + before);
            System.out.println("after: " + after);
            // If before and after aren't letters, we've
            // found the word
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a
                // letter
            && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0)))
            {
                return psn;
            }
            iteration++;

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(),
                psn + 1);

        }

        return -1;
    }

    
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        boolean empty = true;
        for(int i = 0; i < statement.length(); i++){
            char x = statement.charAt(i);
            if(x != ' ')
                empty = false;
        }
        if(empty)
            return "Say something, please.";
        if (findKeyword(statement,"no",0) >= 0)
        {
            response = "Why so negative?";
        }
        else if (findKeyword(statement,"mother",0) >= 0
                || findKeyword(statement,"father",0) >= 0
                || findKeyword(statement,"sister",0) >= 0
                || findKeyword(statement,"brother",0) >= 0)
        {
            response = "Tell me more about your family.";
        }
        else if (findKeyword(statement,"dog",0) >=0 
                    || findKeyword(statement,"cat",0) >=0 )
        {
            response = "Tell me more about you pets.";
        }
        else if (findKeyword(statement,"Mr.",0) >= 0 ) 
        {
            response = "He sounds like a good teacher";
        }
        else if (findKeyword(statement,"Mrs.",0) >=0
                || findKeyword(statement,"Ms.",0) >=0)
        {
            response = "She sounds like a good teacher";
        }
        else if (findKeyword(statement,"You",0) >= 0 ) 
        {
            response = "I am Magpie.";
        }
        else if (findKeyword(statement,"Why",0) >= 0 ) 
        {
            response = "I don’t know. Do you know?";
        }
        else if (findKeyword(statement,"Where",0) >= 0 ) 
        {
            response = "It is probably somewhere in Canada";
        }
        else
        {
            response = getRandomResponse();
        }
        return response;
    }

    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 6;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }
        else if (whichResponse == 4)
        {
            response = "I see...";
        }
        else if (whichResponse == 4)
        {
            response = "Amazing. How was your day today?";
        }
    return response;
    }
}
