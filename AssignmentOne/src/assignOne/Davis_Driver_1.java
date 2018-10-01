package assignOne; 
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


public class Davis_Driver_1 {
	

	
	// Student Number = 02170066  
	// 0066 % 3 = 0
	// 0 + 2 = 2 Processors 
	public int total_processors = 2; 
	
	//Creation of the two processors from my student ID
	public static Davis_ProcessorClass_1 pZero = new Davis_ProcessorClass_1();
	public static Davis_ProcessorClass_1 pOne = new Davis_ProcessorClass_1();
	
	//variable tracks the last processor used
	public static int last_processor = 0;

	
	public static void main(String[] args) throws IOException 
	{
		
		//Initializing RandomJobs array 
		Davis_JobClass_1[] RandomJobs = new Davis_JobClass_1[100];		
		
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
				RandomJobs[j] = new Davis_JobClass_1(j, randomProcessingTime);
						
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
				RandomJobs[j] = new Davis_JobClass_1(j, randomProcessingTime);
						
			}
			// bestCalling CIRCULAR method on a set of RandomJobs
			bestCircularTT[i] = bestCIRCULAR(RandomJobs);

		}
		
		
		
		//initializing testJobs array with the given data table from assignment
		Davis_JobClass_1[] testJobs = new Davis_JobClass_1[12];
		testJobs[0] = new Davis_JobClass_1(4,9);
		testJobs[1] = new Davis_JobClass_1(15,2);
		testJobs[2] = new Davis_JobClass_1(18,16);
		testJobs[3] = new Davis_JobClass_1(20,3);
		testJobs[4] = new Davis_JobClass_1(26,29);
		testJobs[5] = new Davis_JobClass_1(29,198);
		testJobs[6] = new Davis_JobClass_1(35,7);
		testJobs[7] = new Davis_JobClass_1(45,170);
		testJobs[8] = new Davis_JobClass_1(57,180);
		testJobs[9] = new Davis_JobClass_1(83,178);
		testJobs[10] = new Davis_JobClass_1(88,73);
		testJobs[11] = new Davis_JobClass_1(95,8);
		
		// Printing solutions to questions from assignment
		PrintWriter out = new PrintWriter(new FileWriter("C:\\Miscellaneous\\output\\Davis-1.output")); 
		
		out.println("----------");
		out.println("PART a");
		out.println("----------");
		out.println("Total Number of Processors = 2");
		out.println();
		
		// Only printing 3 decimal places
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(3);
		
		out.println("----------");
		out.println("PART b.1");
		out.println("----------");
		out.println("100 Random Job Circular Turnaround Time Statistics: ");
		out.println("Minimum --> "+ min(circularTT) + " ms");
		out.println("Maximum --> "+ max(circularTT) + " ms");
		out.println("Average --> " + mean(circularTT) + " ms");
		
		
		// Printing stddev in only 3 decimal places
		out.print("Standard Deviation --> ");
		out.println(df.format(stddev(circularTT)) + " ms" );
		out.println();
		
		out.println("----------");
		out.println("PART b.2");
		out.println("----------");
		out.println("Sequence of 12 Test Jobs - CIRCULAR Turnaround Time:  " + CIRCULAR(testJobs) + " ms");
		out.println();
		
		
		out.println("----------");
		out.println("PART c");
		out.println("----------");
		out.println("Sequence of 12 Test Jobs - bestCIRCULAR Turnaround Time: " + bestCIRCULAR(testJobs));
		out.println();
		
		out.println("100 Random Job bestCircular Turnaround Time Statistics: ");
		out.println("Minimum --> "+ min(bestCircularTT) + " ms");
		out.println("Maximum --> "+ max(bestCircularTT) + " ms");
		out.println("Average --> " + mean(bestCircularTT) + " ms");
		
		// Printing stddev in only 3 decimal places
		out.print("Standard Deviation --> ");
		out.println(df.format(stddev(bestCircularTT)) + " ms");
		
		out.close();
	}
	
	
	public static int CIRCULAR(Davis_JobClass_1[] jobArray)
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
	
	public static int bestCIRCULAR(Davis_JobClass_1[] jobArray) 
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
	
	public static int findOTT(int timeZero, int timeOne, Davis_JobClass_1[] jobArray) 
	{
		// initializing overalTT variable to 0 
		int overallTT = 0;
		
		// if else statement determining which processor has greater processing time (Used in calculating OTT)
		if(timeZero > timeOne) 
		{
			overallTT = timeZero;			
		}
		
		else
		{
			overallTT = timeOne;			
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
