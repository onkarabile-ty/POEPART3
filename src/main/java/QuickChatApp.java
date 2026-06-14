import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;




class User {

    String username;
    String password;
    String phoneNumber;
    // Username must contain _ and max 5 chars
    public boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password at least 8 chars
    public boolean checkPassword(String password) {
        return password.length() >= 8;
    }

    // SA number check
    public boolean checkPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() <= 12;
    }

    public void register(String username, String password, String phone) {

        if (checkUsername(username)) {
            this.username = username;
        } else {
            System.out.println("Username incorrect");
            return;
        }

        if (checkPassword(password)) {
            this.password = password;
        } else {
            System.out.println("Password incorrect");
            return;
        }

        if (checkPhoneNumber(phone)) {
        } else {
            System.out.println("Phone incorrect");
            return;
        }

        System.out.println("Registration successful");
    }

    public boolean login(String user, String pass) {

        if (user.equals(username) && pass.equals(password)) {

            System.out.println("Login successful");
            return true;
        }

        System.out.println("Login failed");
        return false;
    }
}

// Message class
final class Message {

    static void displayStoredMessages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void displayLongestMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void searchMessageID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void searchRecipient(String recipient) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void deleteMessageByHash(String hash) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static void displayReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String message;
    private final String messageHash;

    static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();

    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generate random 10 digit message ID
    // Generate random message ID
    private String generateMessageID() {

        Random random = new Random();
        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    // Ensure message ID is not more than 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Recipient must start with international code and max 10 chars after +
    public String checkRecipientCell() {

        if (recipient.startsWith("+") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Create message hash using:
    // First 2 digits of ID
    // Message number
    // First and last words of message
    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        return messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // Message length check
    public String checkMessageLength() {

        if (message.length() <= 250) {
            return "Message ready to send.";
        }

        int exceeded = message.length() - 250;

        return "Message exceeds 250 characters by " + exceeded +
                ", please reduce the size.";
    }

    // Send, store or discard
    public String sentMessage(String choice) {

        switch (choice.toLowerCase()) {

            case "send" -> {
                sentMessages.add(this);
                return "Message successfully sent.";
            }

            case "store" -> {
                storedMessages.add(this);
                storeMessage();
                return "Message successfully stored.";
            }

            case "discard" -> {
                return "Press 0 to delete the message.";
            }

            default -> {
                return "Invalid option.";
            }
        }
    }

    // Print messages
    // Print all sent messages
    public static String printMessages() {

        if (sentMessages.isEmpty()) {
            return "No messages sent.";
        }

        String result = "\nSENT MESSAGES\n";

        for (Message msg : sentMessages) {

            result += "------------------------------\n";
            result += "Message ID: " + msg.messageID + "\n";
            result += "Message Hash: " + msg.messageHash + "\n";
            result += "Recipient: " + msg.recipient + "\n";
            result += "Message: " + msg.message + "\n";
        }

        return result;
    }

    // Total messages
    // Return total sent messages
    public static int returnTotalMessages() {
        return sentMessages.size();
    }

    // Store in JSON format
    public void storeMessage() {

        String json = "{\n"+
                "  \"messageID\": \"" + messageID + "\",\n" +
                "  \"messageHash\": \"" + messageHash + "\",\n" +
                "  \"recipient\": \"" + recipient + "\",\n" +
                "  \"message\": \"" + message + "\"\n" +
                "}";

        System.out.println("\nJSON STORED MESSAGE:");
        System.out.println(json);
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }
}

// Main class

public class QuickChatApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        User user = new User();

        // =========================
        // USER REGISTRATION
        // =========================
        System.out.println("========== REGISTER ==========");

        System.out.print("Enter username: ");
        String regUser = input.nextLine();

        System.out.print("Enter password: ");
        String regPass = input.nextLine();

        System.out.print("Enter SA phone number: ");
        String regPhone = input.nextLine();

        user.register(regUser, regPass, regPhone);

        // =========================
        // USER LOGIN
        // =========================
        System.out.println("\n========== LOGIN ==========");

        System.out.print("Enter username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter password: ");
        String loginPass = input.nextLine();

        boolean loggedIn = user.login(loginUser, loginPass);

        if (!loggedIn) {
            System.out.println("Cannot continue.");
            return;
        }

        // WELCOME MESSAGE
       
        System.out.println("\nWelcome to QuickChat.");

    
        System.out.print("How many messages would you like to enter? ");
        int totalMessages = input.nextInt();
        input.nextLine();

        int currentMessage = 1;

        // Program keeps running until user selects Quit
        while (true) {
            // quit is updated from 3 to 4
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Stored Messages");
            System.out.println("4) Quit");

            int option = input.nextInt();
            input.nextLine();

            switch (option) {

                case 1 -> {
                    if (currentMessage > totalMessages) {
                        System.out.println("You have reached the maximum number of messages.");
                        break;
                    }

                    System.out.print("Enter recipient number: ");
                    String recipient = input.nextLine();

                    System.out.print("Enter your message: ");
                    String text = input.nextLine();

                    Message message = new Message(currentMessage, recipient, text);

                    System.out.println("\n" + message.checkRecipientCell());
                    System.out.println(message.checkMessageLength());

                    if (!message.checkMessageLength().equals("Message ready to send.")) {
                        break;
                    }

                    System.out.println("\nChoose an option:");
                    System.out.println("Send");
                    System.out.println("Store");
                    System.out.println("Discard");
                    System.out.print("Selection: ");

                    String choice = input.nextLine();

                    String result = message.sentMessage(choice);

                    System.out.println(result);

                    // Display full details if sent
                    if (choice.equalsIgnoreCase("send")) {

                        System.out.println("\nMESSAGE DETAILS");
                        System.out.println("Message ID: " + message.getMessageID());
                        System.out.println("Message Hash: " + message.getMessageHash());
                        System.out.println("Recipient: " + message.getRecipient());
                        System.out.println("Message: " + message.getMessage());
                    }

                    currentMessage++;
                }

                case 2 -> System.out.println("Coming Soon.");

               case 3 -> {

        System.out.println("\n===== STORED MENU =====");

        System.out.println("1. Display Stored Messages");
        System.out.println("2. Display Longest Message");
        System.out.println("3. Search Message ID");
        System.out.println("4. Search Recipient");
        System.out.println("5. Delete Message Using Hash");
        System.out.println("6. Display Report");

        int subOption = input.nextInt();
        input.nextLine();

        switch (subOption) {

            case 1 -> Message.displayStoredMessages();

            case 2 -> Message.displayLongestMessage();

            case 3 -> {
                System.out.print("Enter Message ID: ");
                String id = input.nextLine();
                Message.searchMessageID(id);
            }

            case 4 -> {
                System.out.print("Enter Recipient: ");
                String recipient = input.nextLine();
                Message.searchRecipient(recipient);
            }

            case 5 -> {
                System.out.print("Enter Message Hash: ");
                String hash = input.nextLine();
                Message.deleteMessageByHash(hash);
            }

            case 6 -> Message.displayReport();

            default -> System.out.println("Invalid option.");
        }
    }

    
    case 4 -> {

        System.out.println("\nApplication closed.");
        System.out.println("Total messages sent: "
                + Message.returnTotalMessages());

        System.out.println(Message.printMessages());

        return;
    }

    default -> System.out.println("Invalid option.");
}
                
            }
        }
    }
public class Part3Features {

    // Arrays required by Part 3
    static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<Message> storedMessages = new ArrayList<>();
    static ArrayList<Message> discardedMessages = new ArrayList<>();

    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();

   
    @SuppressWarnings("NonPublicExported")
    public static void addSentMessage(Message msg) {

        sentMessages.add(msg);
        messageHashes.add(msg.getMessageHash());
        messageIDs.add(msg.getMessageID());
    }

    // Add message to Stored Array
    
    @SuppressWarnings("NonPublicExported")
    public static void addStoredMessage(Message msg) {

        storedMessages.add(msg);
        messageHashes.add(msg.getMessageHash());
        messageIDs.add(msg.getMessageID());
    }

    // Add message to Discarded Array
    @SuppressWarnings("NonPublicExported")
    public static void addDiscardedMessage(Message msg) {

        discardedMessages.add(msg);
    }

    // ==========================================
    // Display sender and recipient of all stored
    // messages
    // ==========================================
    public static void displayStoredMessages() {

        if (storedMessages.isEmpty()) {

            System.out.println("No stored messages found.");
            return;
        }

        System.out.println("\n===== STORED MESSAGES =====");

        for (Message msg : storedMessages) {

            System.out.println("---------------------");
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getMessage());
        }
    }

    // ==========================================
    // Longest Stored Message
    // ==========================================
    public static void displayLongestMessage() {

        if (storedMessages.isEmpty()) {

            System.out.println("No stored messages available.");
            return;
        }

        Message longest = storedMessages.get(0);

        for (Message msg : storedMessages) {

            if (msg.getMessage().length() >
                    longest.getMessage().length()) {

                longest = msg;
            }
        }

        System.out.println("\nLongest Message:");
        System.out.println(longest.getMessage());
    }

    // ==========================================
    // Search Message ID
    // ==========================================
    public static void searchMessageID(String id) {

        for (Message msg : sentMessages) {

            if (msg.getMessageID().equals(id)) {

                System.out.println("Recipient: "+ msg.getRecipient());

                System.out.println("Message: "+ msg.getMessage());

                return;
            }
        }

        for (Message msg : storedMessages) {

            if (msg.getMessageID().equals(id)) {

                System.out.println("Recipient: "+ msg.getRecipient());

                System.out.println("Message: "+ msg.getMessage());

                return;
            }
        }

        System.out.println("Message ID not found.");
    }

    // ==========================================
    // Search Recipient
    // ==========================================
    public static void searchRecipient(String recipient) {

        boolean found = false;

        System.out.println("\nMessages for: "
                + recipient);

        for (Message msg : sentMessages) {

            if (msg.getRecipient().equals(recipient)) {

                System.out.println(msg.getMessage());
                found = true;
            }
        }

        for (Message msg : storedMessages) {

            if (msg.getRecipient().equals(recipient)) {

                System.out.println(msg.getMessage());
                found = true;
            }
        }

        if (!found) {

            System.out.println("No messages found.");
        }
    }

    // ==========================================
    // Delete Message Using Hash
    // ==========================================
    public static void deleteMessageByHash(String hash) {

        for (int i = 0;
             i < storedMessages.size();
             i++) {

            if (storedMessages.get(i)
                    .getMessageHash()
                    .equals(hash)) {

                System.out.println(
                        "Message: \""
                                + storedMessages.get(i)
                                .getMessage()
                                + "\" successfully deleted."
                );

                storedMessages.remove(i);

                return;
            }
        }

        System.out.println("Message hash not found.");
    }

    // ==========================================
    // Full Report
    // ==========================================
    public static void displayReport() {

        System.out.println(
                "\n========== MESSAGE REPORT ==========");

        for (Message msg : sentMessages) {

            System.out.println("--------------------------");
            System.out.println(
                    "Message Hash: "
                            + msg.getMessageHash());

            System.out.println(
                    "Recipient: "+ msg.getRecipient());

            System.out.println(
                    "Message: "
                            + msg.getMessage());
        }

        for (Message msg : storedMessages) {

            System.out.println("--------------------------");
            System.out.println(
                    "Message Hash: "+ msg.getMessageHash());

            System.out.println(
                    "Recipient: "+ msg.getRecipient());

            System.out.println(
                    "Message: "+ msg.getMessage());
        }
    }
}
