/**This is a class for Habits */
public class Habit implements Item{
    private String habName;
    private int time;
    private int exp;
    private int diff;
    private Boolean status;
    private Boolean isBad;

    /**Constructor for habits
     * @param name The name of the habit
     * @param time Periodicy of the habit
     * @param difficult The difficulty of the habit
     * @param status If the habit is completed or not
     * @param bad Wheter the habit is flagged as bad or not
     */
    public Habit(String name, int time, int difficulty, boolean status, Boolean bad){
        this.habName= name;
        this.time= time;
        this.diff= difficulty;
        this.status= status;
        this.isBad= bad;
    }

    public String getName(){
        return this.habName;
    }

    public int getTime(){
        return this.time;
    }

    public Integer getExp(){
        CalcExp();
        return this.exp;
    }

    public Integer getDiff(){
        return this.diff;
    }

    public Boolean getBad(){
        return this.isBad;
    }

    /**Checks if the habit is completed */
    public Boolean IsCompleted(){
        return this.status;
    }

    /**Calculates experience <p>
     * Checks if the habit is bad or not in order to add or substract experience
     */
    public void CalcExp(){
        if(getBad().equals(false))
            this.exp= 10*(diff*time);
        else
            this.exp= -(10*(diff*time));
    }
    /**Returns habit data <p>
     * Checks if the habit is flagged as bad or not
     */
    public String toString(){
        String data, timeString="", diffString="";
        switch(time){
            case 1: timeString= "Monthly"; break;
            case 2: timeString= "Weekly"; break;
            case 3: timeString= "Daily"; break;
        }
        switch(diff){
            case 1: diffString= "Easy"; break;
            case 2: diffString= "Medium"; break;
            case 3: diffString= "Hard"; break;
        }
        if(getBad().equals(false))
            data= "HABIT \n "+"Name: "+habName+"\n Time: "+timeString+"\n Difficulty: "+diffString+"\n Bad?: No\n Experience: "+getExp();
        else
            data= "HABIT \n "+"Name: "+habName+"\n Time: "+timeString+"\n Difficulty: "+diffString+"\n Bad?: Yes\n Experience: "+getExp();
        return data;
    }
}