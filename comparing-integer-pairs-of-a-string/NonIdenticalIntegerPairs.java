import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The programs splits a String in two,
 * looks for non-identical pairs of Integers and
 * returns the indexes in ascending order
 */

public class NonIdenticalIntegerPairs {

    public static void main(String[] args) {

        String inputLine = "F 1 739 F 2 164 F 3 227 F 4 778 F 5 423 F 6 538 F 7 155 F 8 425 F 9 878 " +
                           "B 1 739 B 2 164 B 3 227 B 4 778 B 5 423 B 6 538 B 7 155 B 8 425 B 9 878";

        String[] lineWithoutBlanks = inputLine.split("\\s+");

        String receivingSign;

        String[] received = null;
        String[] brought = null;

        // Identifies the start of the second half with the letter B
        for (int i = 0; i < lineWithoutBlanks.length; i += 3) {

            receivingSign = lineWithoutBlanks[0];

            if (!receivingSign.equals(lineWithoutBlanks[i])) {

                received = Arrays.copyOfRange(lineWithoutBlanks, 0, i);
                brought = Arrays.copyOfRange(lineWithoutBlanks, i, lineWithoutBlanks.length);

                break;
            }
        }


        // Collects the indexes of non-identical pairs
        ArrayList<Integer> wrongPaymentsDays = new ArrayList<>();
        for (int i = 0; i < received.length; i += 3) {

            if (Integer.parseInt(received[i + 2]) != Integer.parseInt(brought[i + 2])) {
                wrongPaymentsDays.add(Integer.parseInt(received[i + 1]));
            }
        }

        Collections.sort(wrongPaymentsDays);

        StringBuilder outputLine = new StringBuilder();
        for (int i = 0; i < wrongPaymentsDays.size(); i++) {
            outputLine.append(wrongPaymentsDays.get(i));
            if (i + 1 <= wrongPaymentsDays.size())
                outputLine.append(" ");
        }

        System.out.println(outputLine);
    }
}
