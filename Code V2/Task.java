/**This is a class for Tasks */
public class Task implements Item{
    private String taskName;
    private String deadline;
    private int exp;
    private int diff;
    private Boolean status;

    /**Constructor for tasks
     * @param name The name of the task
     * @param deadline Limit date for the task
     * @param difficulty The difficulty of the task
     * @param status If the task is completed or not
     */
    public Task(String name, String deadline, int difficulty, boolean status){
        this.taskName= name;
        this.deadline= deadline;
        this.diff= difficulty;
        this.status= status;
    }

    public String getName(){
        return this.taskName;
    }

    public String getDeadline(){
        return this.deadline;
    }

    public Integer getExp(){
        CalcExp();
        return this.exp;
    }

    public Integer getDiff(){
        return this.diff;
    }


    /**Checks if the task is completed */
    public Boolean IsCompleted(){
        return this.status;
    }

    /**Calculates experience */
    public void CalcExp(){
        this.exp= 100*(diff);
    }
    /**Returns task data */
    public String toString(){
        return "TASK\n"+"Name: "+taskName+"\nDeadline: "+deadline+"\nDifficulty: "+diff+"\nEst.Experience: "+getExp();
    }
}