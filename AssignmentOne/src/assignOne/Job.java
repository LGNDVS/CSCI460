package assignOne;

public class Job {
	
	//instance variables for type Job
	public int arrival_time;
	public int processing_time;
	
	public Job (int a, int p) 
	{
		// On the creation of a job, set parameters to arrival time and processing time
		arrival_time = a;
		processing_time = p;
	}
}
