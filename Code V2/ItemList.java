import java.util.*;
/**This is a class that holds all items (Habits and tasks)
 * 
 */
public class ItemList{
    public List<Habit> habits;
    public List<Task> tasks;

    public ItemList(){
        habits= new ArrayList<>();
        tasks= new ArrayList<>();
    }
}