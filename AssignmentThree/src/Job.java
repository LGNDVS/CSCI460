public class Job
{
    private int priorityLevel;
    private String name;

    // constructor
    public Job(int priority, String name)
    {
        priorityLevel = priority;
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public int getPriority()
    {
        return this.priorityLevel;
    }

}