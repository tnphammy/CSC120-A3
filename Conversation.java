import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


class Conversation implements Chatbot {

  // Attributes 
  private ArrayList<String> transcript; // Makes a list of all the user's inputs and the machine response.
 
  /**
   * Constructor 
   */
  Conversation() {
    transcript = new ArrayList<String>();
    
  }

  /**
   * Starts and runs the conversation with the user
   */ 
  public void chat() {
    System.out.println("How many rounds would you like to talk to me for?");
    Scanner input = new Scanner(System.in);
    int rounds = input.nextInt(); // Establish the number of rounds
  
    System.out.println("\nHi there! Whatcha thinkin' about?");
    input.nextLine(); // Swallow the next line first
    
    // Call respond() method:
    for (int i = 0; i < rounds; i++) { 
      String text = input.nextLine(); 
      transcript.add(text); // Adds user's input to our transcript
      String response = respond(text);
      transcript.add(response);
      System.out.println(response);
    
    } 
    input.close();

    // Ends the chat:
    System.out.println("Okay. Bye!");
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    System.out.println("\nTRANSCRIPT:");
    System.out.println("\nHi there! Whatcha thinkin' about?"); 

    for(int i = 0; i < transcript.size(); i ++) { 
      System.out.println(transcript.get(i));
    }
    System.out.println("Okay. Bye!");

  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    
    // Create a list of canned responses.
    ArrayList<String> cannedResponses = new ArrayList<String>();
    cannedResponses.add("Mhm-hmm.");
    cannedResponses.add("Okay.");
    cannedResponses.add("Really?");
    cannedResponses.add("Uh-huh.");
    cannedResponses.add("Tell me more...");

    // Create a list of pronouns.
    ArrayList<String> pronouns = new ArrayList<String>();
    pronouns.add("I");
    pronouns.add("am");
    pronouns.add("me");
    pronouns.add("my");
    pronouns.add("you");
    pronouns.add("are");
    pronouns.add("your");
    pronouns.add("i'm");
    pronouns.add("you're");

    // Locate pronouns from the user's input
    String[] arrStrings = inputString.split(" "); // Split the sentence into word units
    for (String word: arrStrings) {
      if (pronouns.contains(word.toLowerCase())) { // Checks each word to see if there are any pronouns
          return mirror(arrStrings); // Calls the mirror() method
      }
    }
    
    // If no pronouns detected, give random canned response
    Random random = new Random();
    int responseNumber = random.nextInt(cannedResponses.length); // Choose a random index
    return cannedResponses[responseNumber]; // Respond correspondingly with the chosen index
    
  }

  /** 
   * Mirrors pronouns from the user's input.
   * @param words array of words from user's input
   * @return the mirrored response
   */
  public static String mirror(String words[]) {
    for (int i = 0; i < words.length; i++) {
      switch (words[i].toLowerCase()) {
        case "i":
          words[i] = "you";
          break;
        case "i'm":
          words[i] = "you're";
          break;
        case "me":
          words[i] = "you";
          break;
        case "am":
          words[i] = "are";
          break;
        case "my":
          words[i] = "your";
          break;
        case "you":
          if (i == 0) {
            words[i] = "I"; // (not completely foolproof) Way to ensure "You" -> "I" (as a subject) – aka when it's the first word of the sentence.
          }
          else {
            words[i] = "me";
          }
          break;
        case "are":
          words[i] = "am";
          break;
        case "you're":
          words[i] = "i'm";
          break;
        case "your":
          words[i] = "my";
          break;
      }
    }

    return String.join(" ", words) + "?";
  }
    

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
