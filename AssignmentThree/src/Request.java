import java.util.*;

public class Request
{
    int arrivalTime;
    int job;
    Random rand = new Random();

    public Request()
    {
        // rand.nextInt(bound) is 0 (inclusive) to the bound (exclusive)
        // so I decided to go from 0 to 29 and then add 1 because time will
        // start at 1
        this.arrivalTime = rand.nextInt(30);
        this.arrivalTime += 1;

        // again, since rand.nextInt() includes zero, I want to add 1
        // to only get numbers between 1 and 3
        this.job = rand.nextInt(3);
        this.job += 1;
    }

    public Request(int arrivalTime, int job)
    {
        this.arrivalTime = arrivalTime;
        this.job = job;
    }

    public int getArrivalTime()
    {
        return this.arrivalTime;
    }

    public int getJob()
    {
        return this.job;
    }
}