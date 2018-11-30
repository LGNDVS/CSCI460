import java.util.*;

public class Request
{
    int arrivalTime;
    int job;
    Random rand = new Random();

    public Request()
    {
        // random generate arrivalTime between 0 and 29 (added 1 because time starts at 1)
        this.arrivalTime = rand.nextInt(30);
        this.arrivalTime += 1;

        // picks a random job (1,2,3)
        this.job = rand.nextInt(3);
        this.job += 1;
    }

    // constructor 
    public Request(int arrivalTime, int job)
    {
        this.arrivalTime = arrivalTime;
        this.job = job;
    }

    public int getJob()
    {
        return this.job;
    }

    public int getArrivalTime()
    {
        return this.arrivalTime;
    }

}