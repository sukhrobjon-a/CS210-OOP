public class StepCounter {
    static int totalStepsAlUsers = 0;
    private int individualSteps = 0;
    public void walk(int steps){
        individualSteps+=steps;
        totalStepsAlUsers+=steps;
    }

    public int getIndividualSteps() {
        return individualSteps;
    }
    public static int getTotalStepsAlUsers(){
        return totalStepsAlUsers;
    }
    public static void resetGlobalSteps(){
        totalStepsAlUsers = 0;
    }

}
