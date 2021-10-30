import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Finds subarrays with zero sums.
 * If the whole array sums up to zero, the programme returns it as well.
 */

public class ZeroSumSubArrays {

    public static void main(String[] args) {

        int[] fooArray = {1, 4, -5, 3, 2, -5, 1, -4, 2, 2};

        List arraysOfRightCombinations = zeroSum(fooArray);

        for (Object oneArray : arraysOfRightCombinations) {
            System.out.println(Arrays.toString((Object[]) oneArray));
        }
    }

    /**
     * Returns a list of correct combinations.
     * @param array
     * @return
     */
    private static List zeroSum(int[] array) {

        List<Integer[]> validCombinations = new ArrayList<>();

        // Adds a full array with a zero sum.
        if (IntStream.of(array).sum() == 0) {
            validCombinations
                    .add(Arrays.stream(array)
                    .boxed()
                    .toArray(Integer[]::new));
        }

        for (int x = 0; x < array.length; x++) {

            Integer[] oneValidCombination;

            if (array[x] == 0) {
                oneValidCombination = new Integer[]{array[x]};
                validCombinations.add(oneValidCombination);

            // Builds element by element all other zero sum subarrays and adds them to the list.
            } else {
                int temporarySum = array[x];
                oneValidCombination = new Integer[array.length];
                oneValidCombination[0] = array[x];

                int indexCounter = 0;
                for (int y = x + 1; y < array.length; y++) {
                    indexCounter++;
                    temporarySum += array[y];
                    oneValidCombination[indexCounter] = array[y];
                    if (temporarySum == 0) {
                        var arrayWithNullsRemoved = Arrays.
                                        stream(oneValidCombination).
                                        filter(Objects::nonNull).
                                        toArray(Integer[]::new);
                        validCombinations.add(arrayWithNullsRemoved);
                        break;
                    }
                }
            }
        }
        return validCombinations;
    }
}
