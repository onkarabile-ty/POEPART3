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
    

   