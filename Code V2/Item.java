/**This is an interface for Tasks and habits */
public interface Item{
    public abstract Boolean IsCompleted();
    public abstract void CalcExp();
    public abstract String toString(); //Override of toString Java method
    public abstract String getName();
    public abstract Integer getExp();
}