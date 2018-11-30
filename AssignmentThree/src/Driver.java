import java.util.*;

/*
 * Logan Davis
 * CSCI460 - Assignment 3
 * 11/29/2018
*/

public class Driver
{
    // declaration of buffer used in part b
    private static int[] buffer = new int[3];

    // random job requests
    private static Request[] requests = new Request[10];

    //set job requests
    private static Request[] setRequests = new Request[7];
    
    // initalizing time variables 
    private static int randJobTime = 0;
    private static int jobTime = 0;

    private static Job t1 = new Job(3, "T1");
    private static Job t2 = new Job(2, "T2");
    private static Job t3 = new Job(1, "T3");

    public static void main(String[] args)
    {
        //initialize buffer and job requests
        setBuffer(0);
        popSetJobs();
        popJobs();
        
        // initialize time at whatever time the first job arrives
        jobTime = setRequests[0].getArrivalTime();
        randJobTime = requests[0].getArrivalTime();

        // set jobs
        System.out.println("--- Set Jobs ---");
        for(int i = 0; i < setRequests.length; i++)
        {
            // occasionally a job will complete before the next request
            if(jobTime < setRequests[i].getArrivalTime())
            {
                jobTime = setRequests[i].getArrivalTime();
            }

            if(setRequests[i].getJob() == 1)
            {
                setBuffer(1);
                System.out.print("time " + jobTime + ", " + "{" + t1.getName() + "}");
                for(int j = 0; j < buffer.length; j++)
                {
                    System.out.print(buffer[j]);
                }
                System.out.print("{" + t1.getName() + "}\n");
                jobTime += 3;
            }

            else if(setRequests[i].getJob() == 2)
            {
                // preemption of jobs
                if(i + 1 < setRequests.length)
                {
                    if(setRequests[i+1].getJob() == 1 && setRequests[i+1].getArrivalTime() < jobTime + 10)
                    {
                        int preempt = setRequests[i+1].getArrivalTime() - jobTime;
                        System.out.print("time " + jobTime + ", " + "{" + t2.getName() + "}");

                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print(t2.getName() + "\n");

                        /*
                         * If arrival time of next job (that will preempt) is sooner than current time
                         * preempt will be negative value or zero
                        */
                        if(preempt > 0)
                        {
                            jobTime += preempt;
                        }
                    }

                    else
                    {
                        System.out.print("time " + jobTime + ", " + "{" + t2.getName() + "}");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("{" + t2.getName() + "}\n");
                        jobTime += 10;
                    }
                }

                else
                {
                    System.out.print("time " + jobTime + ", " + "{" + t2.getName() + "}");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("{" + t2.getName() + "}\n");
                        jobTime += 10;
                }
            }

            else if(setRequests[i].getJob() == 3)
            {
                setBuffer(3);

                // preemption of jobs
                if(i + 1 < setRequests.length)
                {
                    if(setRequests[i+1].getJob() == 2 && setRequests[i+1].getArrivalTime() < jobTime + 3)
                    {
                        int preempt = setRequests[i+1].getArrivalTime() - jobTime;
                        System.out.print("time " + jobTime + ", " + "{" + t3.getName() + "}");

                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("{" + t3.getName() + "}\n");
                        
                        /*
                         * If arrival time of next job (that will preempt) is sooner than current time
                         * preempt will be negative value or zero
                        */
                        if(preempt > 0)
                        {
                            jobTime += preempt;
                        }
                    }

                    else
                    {
                        System.out.print("time " + jobTime + ", " + "{" + t3.getName() + "}");
                        for(int j = 0; j < buffer.length; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("{" + t3.getName() + "}\n");
                        jobTime += 3;
                    }
                }

                else
                {
                    System.out.print("time " + jobTime + ", " + "{" + t3.getName() + "}");
                        for(int j = 0; j < buffer.length; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("{" + t3.getName() + "}\n");
                        jobTime += 3;
                }
            }
        }
        System.out.println();

        // Random Jobs
        System.out.println("--- Random Jobs ---");
        for(int i = 0; i < requests.length; i++)
        {
            // occasionally a job will complete before the next request
            if(randJobTime < requests[i].getArrivalTime())
            {
                randJobTime = requests[i].getArrivalTime();
            }

            if(requests[i].getJob() == 1)
            {
                setBuffer(1);
                System.out.print("time " + randJobTime + ", " + "{" + t1.getName() + "}");
                for(int j = 0; j < buffer.length; j++)
                {
                    System.out.print(buffer[j]);
                }
                System.out.print("{" + t1.getName() + "}\n");
                randJobTime += 3;
            }
            else if(requests[i].getJob() == 2)
            {
                // Preemption of jobs
                if(i + 1 < requests.length)
                {
                    if(requests[i+1].getJob() == 1 && requests[i+1].getArrivalTime() < randJobTime + 10)
                    {
                        int preempt = requests[i+1].getArrivalTime() - randJobTime;
                        System.out.print("time " + randJobTime + ", " + "{" + t2.getName() + "}");

                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("{" + t2.getName() + "}\n");

                        /*
                         * If arrival time of next job (that will preempt) is sooner than current time
                         * preempt will be negative value or zero
                        */
                        if(preempt > 0)
                        {
                            randJobTime += preempt;
                        }
                    }
                    else
                    {
                        System.out.print("time " + randJobTime + ", " + "{" + t2.getName() + "}");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("{" + t2.getName() + "}\n");
                        randJobTime += 10;
                    }
                } 
                else
                {
                    System.out.print("time " + randJobTime + ", " + "{" + t2.getName() + "}");
                        for(int j = 0; j < 10; j++)
                        {
                            System.out.print("N");
                        }
                        System.out.print("{" + t2.getName() + "}\n");
                        randJobTime += 10;
                }
            }
            else if(requests[i].getJob() == 3)
            {
                setBuffer(3);

                // preemption of jobs
                if(i + 1 < requests.length)
                {
                    if(requests[i+1].getJob() == 2 && requests[i+1].getArrivalTime() < randJobTime + 3)
                    {
                        int preempt = requests[i+1].getArrivalTime() - randJobTime;
                        System.out.print("time " + randJobTime + ", " + "{" + t3.getName() + "}");
                        
                        for(int j = 0; j < preempt; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("{" + t3.getName() + "}\n");

                        /*
                         * If arrival time of next job (that will preempt) is sooner than current time
                         * preempt will be negative value or zero
                        */
                        if(preempt > 0)
                        {
                            randJobTime += preempt;
                        }
                    }
                    else
                    {
                        System.out.print("time " + randJobTime + ", " + "{" + t3.getName() + "}");
                        for(int j = 0; j < buffer.length; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("{" + t3.getName() + "}\n");
                        randJobTime += 3;
                    }
                }
                else
                {
                    System.out.print("time " + randJobTime + ", " + "{" + t3.getName() + "}");
                        for(int j = 0; j < buffer.length; j++)
                        {
                            System.out.print(buffer[j]);
                        }
                        System.out.print("{" + t3.getName() + "}\n");
                        randJobTime += 3;
                }
            }
        }
    }

    // method to set values in buffer
    public static void setBuffer(int value)
    {
        for(int i = 0; i < buffer.length; i++)
        {
            buffer[i] = value;
        }
    }

    // method to add job requests to job array and sort
    public static void popJobs()
    {
        // requests populated with random values
        for(int i = 0; i < requests.length; i++)
        {
            requests[i] = new Request();
        }

        System.out.println("--- Random Jobs ---");
        System.out.print("Before sorting: ");
        printJobs(requests);

        // requests sorted by time
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
        System.out.print("After sorting:  ");
        printJobs(requests);
        System.out.println();
    }

    public static void popSetJobs()
    {
        // setting the given request from the assignment directions
        setRequests[0] = new Request(1, 3);
        setRequests[1] = new Request(3, 2);
        setRequests[2] = new Request(6, 3);
        setRequests[3] = new Request(8, 1);
        setRequests[4] = new Request(10, 2);
        setRequests[5] = new Request(12, 3);
        setRequests[6] = new Request(26, 1);

        System.out.println("--- Set Jobs ---");
        printJobs(setRequests);
        System.out.println();
    }

    // method to print the jobs
    public static void printJobs(Request[] jobs)
    {
        System.out.print("[");
        for(int i = 0; i < jobs.length; i++)
        {
            System.out.print(jobs[i].getArrivalTime());
            System.out.print("{" + jobs[i].getJob() + "}");
            if(i != jobs.length - 1)
            {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
    }
}