import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MessagePart3Test {

    @Test
    public void testSentMessagesArray() {

        Message msg =
                new Message(
                        1,
                        "+27834557896",
                        "Did you get the cake?"
                );

        Message.sentMessages.add(msg);

        assertEquals(
                "Did you get the cake?",
                Message.sentMessages.get(0).getMessage()
        );
    }

    @Test
    public void testLongestMessage() {

        Message msg =
                new Message(
                        2,
                        "+27838884567",
                        "Where are you? You are late! I have asked you to be on time."
                );

        Message.storedMessages.add(msg);

        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                Message.storedMessages.get(0).getMessage()
        );
    }

    @Test
    public void testRecipientSearch() {

        Message msg =
                new Message(
                        5,
                        "+27838884567",
                        "Ok, I am leaving without you."
                );

        Message.storedMessages.add(msg);

        assertEquals(
                "+27838884567",
                msg.getRecipient()
        );
    }

    @Test
    public void testDeleteMessage() {

        Message msg =
                new Message(
                        2,
                        "+27838884567",
                        "Where are you?"
                );

        Message.storedMessages.add(msg);

        int before =
                Message.storedMessages.size();

        Message.deleteMessageByHash(
                msg.getMessageHash()
        );

        int after =
                Message.storedMessages.size();

        assertEquals(before - 1, after);
    }
}