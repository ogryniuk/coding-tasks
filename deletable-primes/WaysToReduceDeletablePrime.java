/**
 *  The class finds the number of ways to reduce a deletable prime
 */

public class WaysToReduceDeletablePrime {

    static boolean isPrime(long number){
        if (number == 0 | number == 1)
            return false;
        if(number == 2)
            return true;
        if (number % 2 == 0)
                return false;
        int squareRoot = (int) Math.sqrt(number);
        for(int i = 3; i <= squareRoot; i+= 2){
            if(number % i == 0)
                return false;
        }
        return true;
    }

    static int numberOfDigits(long number) {
        int digitCount = 0;
        for (long i = number; i > 0; i /= 10)
            digitCount++;
        return digitCount;
    }

    static Long removeOneDigit(long number, int digitToRemove){

            String foo = Long.toString(number);

            String partOne = null;
            if(digitToRemove > 1){
                partOne = foo.substring(0, digitToRemove - 1);
            }

            String partTwo = null;
            if(digitToRemove < numberOfDigits(number)) {
                partTwo = foo.substring(digitToRemove);
            }

            String complete;
            if (partOne == null){
                complete = partTwo;
            } else if (partTwo == null){
                complete = partOne;
            } else {
                complete = partOne + partTwo;
            }

            return Long.parseLong(complete);
     }

    /**
     * The central method of the class. Recursion allows counting the total number of ways
     * @param digits
     * @param number
     * @return
     */
    static int countDeletablePrimes(int digits, long number) {
        int count = 0;

            for (int i = 0; i < digits; i++) {

                digits = numberOfDigits(number);
                long newNumber = removeOneDigit(number, i + 1);

                if (isPrime(newNumber)) {
                    if (newNumber / 10 == 0) {
                        count++;
                    } else {
                        count += countDeletablePrimes(digits, newNumber);
                    }
                }
            }

        return count;
    }

    public static void main(String[] args) {

        long number = 4567;

        int digits = numberOfDigits(number);

        System.out.println(countDeletablePrimes(digits, number));
    }
}
