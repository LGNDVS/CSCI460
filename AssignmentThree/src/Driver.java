import java.util.*;

public class Driver 
{
    //Written by John Bemis for CSCI 460 at Montana State University
    
    //The output will sometimes show a job's name, but not the buffer. This
    //is because the job after it preempts it, and that job's arrival time is
    //sooner than the current time, meaning that the job that was preempted
    //had no time do anything. 

    //buffer for part b
    private static int[] buffer = new int[3];
    //random job requests
    private static Request[] requests = new Request[10];
    //set job requests
    private static Request[] setRequests = new Request[7];
    
    //time variable (for now)
    private static int setJobsTime = 0;
    private static int randomJobsTime = 0;

    private static Job t1 = new Job(3, "T1");
    private static Job t2 = new Job(2, "T2");
    private static Job t3 = new Job(1, "T3");

    public static void main(String[] args)
    {
        //initialize buffer and job requests
        setBuffer(0);
        populateSetJobs();
        populateJobs();
        
        //start time at whatever time the first job arrives
        setJobsTime = setRequests[0].getArrivalTime();
        randomJobsTime = requests[0].getArrivalTime();

        //set jobs
        System.out.println("Set Jobs:");
        for(int i = 0; i < setRequests.length; i++)
        {
            //sometimes a job will complete before the next request
            if(setJobsTime < setRequests[i].getArrivalTime())
            {
                setJobsTime = setRequests[i].getArrivalTime();
            }

            if(setRequests[i].getJob() == 1)
            {
                setBuffer(1);
                System.out.print("Time: " + setJobsTime + ", " + "(" + t1.getName() + ")");
                for(int j = 0; j < buffer.length; j++)
                {
                    System.out.print(buffer[j]);
                }
                System.out.print("(" + t1.getName() + ")\n");
                setJobsTime += 3;
            }
            else if(setRequests[i].getJob() == 2)
            {
                //preemption
                if(i + 1 < setRequests.length)
                {
                    if(setRequests[i+1].getJob() == 1 && setRequests[i+1].getArrivalTime() < setJobsTime + 10)
                    {
                        int preempt = setRequests[i+1].getArrivalTime() - setJobsTime;
                        System.out.print("Time: " + setJobsTime + ", " + "(" + t2.getName() + ")");

                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.getName() + ")\n");

                        // if the arrival time of the next job that is preempting is
                        // sooner than the current time, preempt will be negative 
                        // or zero.
                        // the current job will only print its name and continue, with
                        // no time added
                        if(preempt > 0)
                        {
                            setJobsTime += preempt;
                        }
                    }
                    else
                    {
                        System.out.print("Time: " + setJobsTime + ", " + "(" + t2.getName() + ")");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.getName() + ")\n");
                        setJobsTime += 10;
                    }
                }
                else
                {
                    System.out.print("Time: " + setJobsTime + ", " + "(" + t2.getName() + ")");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.getName() + ")\n");
                        setJobsTime += 10;
                }
            }
            else if(setRequests[i].getJob() == 3)
            {
                setBuffer(3);
                //preemption
                if(i + 1 < setRequests.length)
                {
                    if(setRequests[i+1].getJob() == 2 && setRequests[i+1].getArrivalTime() < setJobsTime + 3)
                    {
                        int preempt = setRequests[i+1].getArrivalTime() - setJobsTime;
                        System.out.print("Time: " + setJobsTime + ", " + "(" + t3.getName() + ")");

                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("(" + t3.getName() + ")\n");
                        
                        // if the arrival time of the next job that is preempting is
                        // sooner than the current time, preempt will be negative 
                        // or zero.
                        // the current job will only print its name and continue, with
                        // no time added
                        if(preempt > 0)
                        {
                            setJobsTime += preempt;
                        }
                    }
                    else
                    {
                        System.out.print("Time: " + setJobsTime + ", " + "(" + t3.getName() + ")");
                        for(int j = 0; j < buffer.length; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("(" + t3.getName() + ")\n");
                        setJobsTime += 3;
                    }
                }
                else
                {
                    System.out.print("Time: " + setJobsTime + ", " + "(" + t3.getName() + ")");
                        for(int j = 0; j < buffer.length; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("(" + t3.getName() + ")\n");
                        setJobsTime += 3;
                }
            }
        }
        System.out.println();

        //random jobs
        System.out.println("Random Jobs:");
        for(int i = 0; i < requests.length; i++)
        {
            //sometimes a job will complete before the next request
            if(randomJobsTime < requests[i].getArrivalTime())
            {
                randomJobsTime = requests[i].getArrivalTime();
            }

            if(requests[i].getJob() == 1)
            {
                setBuffer(1);
                System.out.print("Time: " + randomJobsTime + ", " + "(" + t1.getName() + ")");
                for(int j = 0; j < buffer.length; j++)
                {
                    System.out.print(buffer[j]);
                }
                System.out.print("(" + t1.getName() + ")\n");
                randomJobsTime += 3;
            }
            else if(requests[i].getJob() == 2)
            {
                //preemption
                if(i + 1 < requests.length)
                {
                    if(requests[i+1].getJob() == 1 && requests[i+1].getArrivalTime() < randomJobsTime + 10)
                    {
                        int preempt = requests[i+1].getArrivalTime() - randomJobsTime;
                        System.out.print("Time: " + randomJobsTime + ", " + "(" + t2.getName() + ")");

                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.getName() + ")\n");

                        // if the arrival time of the next job that is preempting is
                        // sooner than the current time, preempt will be negative 
                        // or zero.
                        // the current job will only print its name and continue, with
                        // no time added
                        if(preempt > 0)
                        {
                            randomJobsTime += preempt;
                        }
                    }
                    else
                    {
                        System.out.print("Time: " + randomJobsTime + ", " + "(" + t2.getName() + ")");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.getName() + ")\n");
                        randomJobsTime += 10;
                    }
                } 
                else
                {
                    System.out.print("Time: " + randomJobsTime + ", " + "(" + t2.getName() + ")");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("(" + t2.getName() + ")\n");
                        randomJobsTime += 10;
                }
            }
            else if(requests[i].getJob() == 3)
            {
                setBuffer(3);
                //preemption
                if(i + 1 < requests.length)
                {
                    if(requests[i+1].getJob() == 2 && requests[i+1].getArrivalTime() < randomJobsTime + 3)
                    {
                        int preempt = requests[i+1].getArrivalTime() - randomJobsTime;
                        System.out.print("Time: " + randomJobsTime + ", " + "(" + t3.getName() + ")");
                        
                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("(" + t3.getName() + ")\n");

                        // if the arrival time of the next job that is preempting is
                        // sooner than the current time, preempt will be negative 
                        // or zero.
                        // the current job will only print its name and continue, with
                        // no time added
                        if(preempt > 0)
                        {
                            randomJobsTime += preempt;
                        }
                    }
                    else
                    {
                        System.out.print("Time: " + randomJobsTime + ", " + "(" + t3.getName() + ")");
                        for(int j = 0; j < buffer.length; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("(" + t3.getName() + ")\n");
                        randomJobsTime += 3;
                    }
                }
                else
                {
                    System.out.print("Time: " + randomJobsTime + ", " + "(" + t3.getName() + ")");
                        for(int j = 0; j < buffer.length; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("(" + t3.getName() + ")\n");
                        randomJobsTime += 3;
                }
            }
        }
    }

    //helper method to set values in the buffer
    public static void setBuffer(int value)
    {
        for(int i = 0; i < buffer.length; i++)
        {
            buffer[i] = value;
        }
    }

    //herlper method to add job requests to the job array and sort the array
    public static void populateJobs()
    {
        //requests will be populated with random values
        for(int i = 0; i < requests.length; i++)
        {
            requests[i] = new Request();
        }

        System.out.println("Random Jobs:");
        System.out.println("Before sorting: ");
        printJobs(requests);
        //requests will then be sorted by time
        for(int i = 0; i < requests.length; i++)
        {
            for(int j =  i + 1; j < requests.length; j++)
            {
                if(requests[i].getArrivalTime() > requests[j].getArrivalTime())
                {
                    Request temp = new Request();
                    temp = requests[i];
                    requests[i] = requests[j];
                    requests[j] = temp;
                }
            }
        }
        System.out.println("After sorting: ");
        printJobs(requests);
        System.out.println();
    }

    public static void populateSetJobs()
    {
        setRequests[0] = new Request(1, 3);
        setRequests[1] = new Request(3, 2);
        setRequests[2] = new Request(6, 3);
        setRequests[3] = new Request(8, 1);
        setRequests[4] = new Request(10, 2);
        setRequests[5] = new Request(12, 3);
        setRequests[6] = new Request(26, 1);
        System.out.println("Set Jobs:");
        printJobs(setRequests);
        System.out.println();
    }

    //helper method to print the job queue
    public static void printJobs(Request[] jobs)
    {
        System.out.print("[");
        for(int i = 0; i < jobs.length; i++)
        {
            System.out.print(jobs[i].getArrivalTime());
            System.out.print("(" + jobs[i].getJob() + ")");
            if(i != jobs.length - 1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
    }
}