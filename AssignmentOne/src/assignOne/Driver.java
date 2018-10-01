package assignOne; 
import java.text.DecimalFormat;
import java.util.*;


public class Driver {
	
	// Student Number = 02170066  
	// 0066 % 3 = 0
	// 0 + 2 = 2 Processors 
	public int total_processors = 2; 
	
	//Creation of the two processors from my student ID
	public static Processor pZero = new Processor();
	public static Processor pOne = new Processor();
	
	//variable tracks the last processor used
	public static int last_processor = 0;

	
	public static void main(String[] args) 
	{
		
		//Initializing RandomJobs array 
		Job[] RandomJobs = new Job[100];		
		
		//PART b.1, Running program 100 times and calculating statistics 
		int[] circularTT = new int[100];
		int[] bestCircularTT = new int[100];
		
		
		/*
		 * 	bestCircular RandomJob Simulation 
		 */
		for(int i = 0; i < 100; i++) 
		{
			
			// Filling RandomJobs array with new random values through each iteration of the program call
			for(int j = 0; j < RandomJobs.length; j++) 
			{
				// Setting random processing times between 1 - 500
				Random r = new Random();
				int low = 1;
				int high = 501;
				int randomProcessingTime = r.nextInt(high - low) + low;
				RandomJobs[j] = new Job(j, randomProcessingTime);
						
			}
			
			// Calling CIRCULAR method on a set of RandomJobs
			circularTT[i] = CIRCULAR(RandomJobs);

		}
		
		
		/*
		 * 	bestCircular RandomJob Simulation 
		 */
		for(int i = 0; i < 100; i++) 
		{
			
			// Filling RandomJobs array with new random values through each iteration of the program call
			for(int j = 0; j < RandomJobs.length; j++) 
			{
				Random r = new Random();
				int low = 1;
				int high = 501;
				int randomProcessingTime = r.nextInt(high - low) + low;
				RandomJobs[j] = new Job(j, randomProcessingTime);
						
			}
			// bestCalling CIRCULAR method on a set of RandomJobs
			bestCircularTT[i] = bestCIRCULAR(RandomJobs);

		}
		
		
		
		//initializing testJobs array with the given data table from assignment
		Job[] testJobs = new Job[12];
		testJobs[0] = new Job(4,9);
		testJobs[1] = new Job(15,2);
		testJobs[2] = new Job(18,16);
		testJobs[3] = new Job(20,3);
		testJobs[4] = new Job(26,29);
		testJobs[5] = new Job(29,198);
		testJobs[6] = new Job(35,7);
		testJobs[7] = new Job(45,170);
		testJobs[8] = new Job(57,180);
		testJobs[9] = new Job(83,178);
		testJobs[10] = new Job(88,73);
		testJobs[11] = new Job(95,8);
		
		// Printing solutions to questions from assignment
		System.out.println("----------\n" + "PART a" + "\n----------");
		System.out.println("Total Number of Processors = 2" + "\n");
		
		// Only printing 3 decimal places
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(3);
		
		System.out.println("----------\n" + "PART b.1" + "\n----------");
		System.out.println("100 Random Job Circular Turnaround Time Statistics: ");
		System.out.println("Minimum --> "+ min(circularTT) + " ms");
		System.out.println("Maximum --> "+ max(circularTT) + " ms");
		System.out.println("Average --> " + mean(circularTT) + " ms");
		
		
		// Printing stddev in only 3 decimal places
		System.out.print("Standard Deviation --> ");
		System.out.println(df.format(stddev(circularTT)) + " ms\n" );
		
		
		System.out.println("----------\n" + "PART b.2" + "\n----------");
		System.out.println("Sequence of 12 Test Jobs - CIRCULAR Turnaround Time:  " + CIRCULAR(testJobs) + " ms\n");
		
		
		System.out.println("----------\n" + "PART c" + "\n----------");
		System.out.println("Sequence of 12 Test Jobs - bestCIRCULAR Turnaround Time: " + bestCIRCULAR(testJobs) + " ms\n");
		
		System.out.println("100 Random Job bestCircular Turnaround Time Statistics: ");
		System.out.println("Minimum --> "+ min(bestCircularTT) + " ms");
		System.out.println("Maximum --> "+ max(bestCircularTT) + " ms");
		System.out.println("Average --> " + mean(bestCircularTT) + " ms");
		
		// Printing stddev in only 3 decimal places
		System.out.print("Standard Deviation --> ");
		System.out.println(df.format(stddev(bestCircularTT)) + " ms");
		
	}
	
	
	public static int CIRCULAR(Job[] jobArray)
	{
		//making sure that the total processing time is reset after every CIRCULAR method call
		pZero.ProcessorTotalTime = 0;
		pOne.ProcessorTotalTime = 0;
		
		for(int i = 0; i < jobArray.length; i++) 
		{
			if(last_processor == 0) 
			{
				pZero.ProcessorTotalTime = pZero.ProcessorTotalTime + jobArray[i].processing_time + 1;
				last_processor = 1;
				
			} 
			
			else if(last_processor == 1) 
			{
				pOne.ProcessorTotalTime = pOne.ProcessorTotalTime + jobArray[i].processing_time + 1;
				last_processor = 0;
				
			}
	
		}
		
		//System.out.println("CIRCULAR OTT = " + findOTT(pZero.ProcessorTotalTime, pOne.ProcessorTotalTime, jobArray));
		return findOTT(pZero.ProcessorTotalTime, pOne.ProcessorTotalTime, jobArray);
		
	}
	
	public static int bestCIRCULAR(Job[] jobArray) 
	{
		//making sure that the total processing time is reset after every CIRCULAR method call
		pZero.ProcessorTotalTime = 0;
		pOne.ProcessorTotalTime = 0;
		
		int bestProcessor = 0;
		
		for(int i = 0; i < jobArray.length; i++) 
		{
			if(bestProcessor == 0) 
			{
				pZero.ProcessorTotalTime = pZero.ProcessorTotalTime + jobArray[i].processing_time + 1;
				
				
				if(pZero.ProcessorTotalTime < pOne.ProcessorTotalTime) 
				{
					bestProcessor = 0; 
				}
				else
				{
					bestProcessor = 1;
				}
				
				
			}
			
			else if(bestProcessor == 1) 
			{
				pOne.ProcessorTotalTime = pOne.ProcessorTotalTime + jobArray[i].processing_time + 1;
				
				
				if(pZero.ProcessorTotalTime < pOne.ProcessorTotalTime) 
				{
					bestProcessor = 0; 
				}
				else
				{
					bestProcessor = 1;
				}
				
			}
		}
		
		
		return findOTT(pZero.ProcessorTotalTime, pOne.ProcessorTotalTime, jobArray);
		//System.out.println("bestCIRCULAR OTT = " + findOTT(pZero.ProcessorTotalTime, pOne.ProcessorTotalTime, jobArray));
		
	}
	
	public static int findOTT(int timeZero, int timeOne, Job[] jobArray) 
	{
		// initializing overalTT variable to 0 
		int overallTT = 0;
		
		// if else statement determining which processor has greater processing time (Used in calculating OTT)
		if(timeZero > timeOne) 
		{
			overallTT = timeZero + jobArray[0].arrival_time;			
		}
		
		else
		{
			overallTT = timeOne + jobArray[0].arrival_time;			
		}
		
		return overallTT;
	}
	
	
	
	/*
	 * 
	 * Maximum, Minimum, Average(mean), standard deviation Calculations
	 * 
	 */
	
	// Return maximum value in an array 
	public static int max(int[] a) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > max)
				max = a[i];
		}
		return max;
	}

	// Return minimum value in an array
	public static int min(int[] a) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < min)
				min = a[i];
		}
		return min;
	}

	// Return average value in an array
	public static double mean(int[] a) {
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i];
		}
		return sum / a.length;
	}

	// Return sample variance of array, NaN if no such value. (NEEDED TO CALCULATE STDDEV)
	public static double var(int[] a) {
		if (a.length == 0)
			throw new RuntimeException("Array size is 0.");
		double avg = mean(a);
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum += (a[i] - avg) * (a[i] - avg);
		}
		return sum / (a.length - 1);
	}
	
	// Return sample standard deviation of array, NaN if no such value.
	public static double stddev(int[] a) {
		return Math.sqrt(var(a));
	}
}
