import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem2 {

    /*
     * Handles getting input from file in order to be sent off to greedy method that determines what requests should be
     * fulfilled and when.
     */
    public static void P2 () {
        // Gets list of requests.
        ArrayList<int[]> requests = new ArrayList<>();
        int latestDayOverall = 0;
        try {
            File inputFile = new File("./input2.txt");
            Scanner fileIn = new Scanner(inputFile);

            // Get each request into list.
            while (fileIn.hasNextLine()) {
                String request = fileIn.nextLine();

                // Parses request data.
                Scanner requestReader = new Scanner(request);
                int requestID = requestReader.nextInt();
                int requestAmount = requestReader.nextInt();
                int requestLastDay = requestReader.nextInt();

                latestDayOverall = Integer.max(latestDayOverall, requestLastDay);

                requests.add(new int[] {requestID, requestAmount, requestLastDay});
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Sends request data to greedy method to get back a completed schedule.
        int[][] schedule = scheduleRequests(requests, Integer.min(latestDayOverall, requests.size()));

        // Prints out resulting optimal schedule. Note that when a schedule has empty days in it, requests made after
        // said gap are shifted to fill said gaps (and thus are fulfilled sooner). This approach versus leaving the empty
        // days was not explicitly stated in the handout, so this implementation was chosen.
        int totalMoneyMade = 0;
        int dayNum = 1;
        System.out.println("Optimal schedule:");
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] != null) {
                totalMoneyMade += schedule[i][1];
                System.out.println("Day " + dayNum + ": " + schedule[i][0] + " " + schedule[i][1] + " " + schedule[i][2]);
                dayNum++;
            }
        }
        System.out.println("Total money made: " + totalMoneyMade);

    }

    /*
     * Makes a schedule that will give the highest amount of income.
     */
    private static int[][] scheduleRequests(ArrayList<int[]> requests, int daysToScheduleFor) {
        int[][] schedule = new int[daysToScheduleFor][];

        // Orders requests based on which has the highest payout.
        requests.sort((o1, o2) -> o2[1] - o1[1]);

        // Assigns requests to days in the schedule based on what fulfill-able (aka not past the last day) request pays
        // the most.
        for (int curDay = daysToScheduleFor; curDay > 0; curDay--) {
            boolean requestFound = false;
            int requestIndex = 0;
            // Looks for best request.
            while (requestIndex < requests.size() && !requestFound) {
                if (requests.get(requestIndex)[2] >= curDay) {
                    requestFound = true;
                    schedule[curDay - 1] = requests.remove(requestIndex);
                } else {
                    requestIndex++;
                }
            }
        }

        return schedule;
    }
}
