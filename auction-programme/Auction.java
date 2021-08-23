
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  The class uses string and map manipulations to
 *  retrieve largest key-value pairs based on the rules of the auction.
 *  Afterwards, it collects these pairs into a new map
 * <p>
 */
public class Auction_Level3 {

    // Auction history: first goes the starting price, then pairs of bidder names and bids
    private static final String INPUTDATA =
            "20,A,100,B,120,C,120,C,121,F,144,B,154,C,157,D,158,C,161,C,179,C,180";

    public static void main(String[] args) {

        Map<Integer, String> historyOfValidPrices = new LinkedHashMap<>();
        String inputSubSet[] = INPUTDATA.split(",");

        String newBidInput = "";
        for (int i = 0; i < inputSubSet.length - 1; i += 2) {

            newBidInput += inputSubSet[i + 1] + "," + inputSubSet[i + 2] + ",";

            String highestBidderPair = highestBidder(newBidInput);
            String bidder = highestBidderPair.split(",")[0];
            String amount = highestBidderPair.split(",")[1];
            historyOfValidPrices.put(Integer.parseInt(amount), bidder);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("-," + inputSubSet[0]);
        for (Map.Entry<Integer, String> entry : historyOfValidPrices.entrySet()) {
            sb.append(",");
            sb.append(entry.getValue());
            sb.append(",");
            sb.append(entry.getKey());
        }
        System.out.println(sb);
    }

    private static String highestBidder(String inputData) {

        String[] commaSplit = inputData.split(",");

        String bidderName;
        int bidSum;
        LinkedHashMap<String[], Integer> mapOfBids = new LinkedHashMap<>();

        for (int i = 0; i < commaSplit.length; i += 2) {
            bidderName = commaSplit[i];
            bidSum = Integer.parseInt(commaSplit[i + 1]);
            mapOfBids.put(new String[]{bidderName}, bidSum);
        }

        Map.Entry<String[], Integer> largestBidder = getLargestMapElement(mapOfBids);
        mapOfBids.remove(largestBidder.getKey());
        ConcurrentHashMap<String[], Integer> newMap = new ConcurrentHashMap<>();

        for (Map.Entry<String[], Integer> oneEntry : mapOfBids.entrySet()) {

            String entryKey = oneEntry.getKey()[0];
            String largestBidderName = largestBidder.getKey()[0];

            if (!(entryKey.equals(largestBidderName))) {
                newMap.put(oneEntry.getKey(), oneEntry.getValue());
            }
        }

        if (newMap.containsValue(largestBidder.getValue())) {

            return largestBidder.getKey()[0] + "," + largestBidder.getValue();

        } else {

            if (newMap.isEmpty()) {
                return commaSplit[0] + "," + Integer.parseInt(INPUTDATA.split(",")[0]);
            }

            Map.Entry<String, Integer> secondLargestBidder = getLargestMapElement(newMap);
            int secondLargestBidPlusEntryPrice = secondLargestBidder.getValue() +
                    Integer.parseInt(INPUTDATA.split(",")[0]);
            return largestBidder.getKey()[0] + "," + secondLargestBidPlusEntryPrice;
        }
    }

    /**
     * Retrieves the map pair with the highest value
     *
     * @param map
     * @return
     */
    public static Map.Entry getLargestMapElement(Map<String[], Integer> map) {

        Map.Entry<String[], Integer> maxPair = null;
        for (Map.Entry<String[], Integer> entry : map.entrySet()) {
            if (maxPair == null || entry.getValue().compareTo(maxPair.getValue()) > 0)
                maxPair = entry;
        }
        return maxPair;
    }
}
