public class Job
{
    private int priority;
    private String name;

    public Job(int priority, String name)
    {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority()
    {
        return this.priority;
    }

    public String getName()
    {
        return this.name;
    }
}