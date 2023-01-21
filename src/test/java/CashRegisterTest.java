import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CashRegisterTest {

    private static CashRegister testRegister;

    @BeforeEach
    public void setup(){
        testRegister = new CashRegister();
        String seedPutCommand = "put 1 2 3 4 5";
        testRegister.parseInput(seedPutCommand);
    }

    @Test
    public void testGet(){
        String output = "$68 1 2 3 4 5";
        ByteArrayOutputStream oByte = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(oByte);
        System.setOut(printStream);
        testRegister.parseInput("get");
        String actualOutput = oByte.toString().trim();
        assertEquals(output, actualOutput);
    }

    @Test
    public void testPut(){
        try{
            String putCommand2 = "put 1 2 3 0 5";
            String output = "$128 2 4 6 4 10";
            ByteArrayOutputStream oByte = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(oByte);
            System.setOut(printStream);
            testRegister.parseInput(putCommand2);
            String actualOutput = oByte.toString().trim();
            assertEquals(output, actualOutput);

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testTake(){
        String takeCommand = "take 0 1 2 1 3";
        String output = "$43 1 1 1 3 2";
        ByteArrayOutputStream oByte = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(oByte);
        System.setOut(printStream);
        testRegister.parseInput(takeCommand);
        String actualOutput = oByte.toString().trim();
        assertEquals(output, actualOutput);
    }


    @Test
    public void testChange(){
        String changeCommand = "change 11";
        String output = "0 1 0 0 1";
        ByteArrayOutputStream oByte = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(oByte);
        System.setOut(printStream);
        testRegister.parseInput(changeCommand);
        String actualOutput = oByte.toString().trim();
        assertEquals(output, actualOutput);
    }

    @Test
    public void testChange2(){
        String changeCommand2 = "change 24";
        String output = "1 0 0 2 0";
        ByteArrayOutputStream oByte = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(oByte);
        System.setOut(printStream);
        testRegister.parseInput(changeCommand2);
        String actualOutput = oByte.toString().trim();
        assertEquals(output, actualOutput);
    }

    @Test
    public void testValidInput(){
        String invalidCommand = "put 2 3";
        String output = "Invalid input";
        ByteArrayOutputStream oByte = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(oByte);
        System.setOut(printStream);
        testRegister.parseInput(invalidCommand);
        String actualOutput = oByte.toString().trim();
        assertEquals(output, actualOutput);
    }

    @Test
    public void testQuit(){
        boolean flag = testRegister.parseInput("quit");
        assertFalse(flag);
    }

}
