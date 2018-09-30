package assignOne; 
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your student number: ");
		int std_no = sc.nextInt();
		
		int last_processor = 0;
		int total_processors = getNumProcessors(std_no);
		
		Processor pOne = new Processor();
		Processor pTwo = new Processor();
		
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
		
		//System.out.println(Arrays.toString(testJobs[0]));
		
	}
	
	public static int getNumProcessors(int number) 
	{
		int processors = 0;
		number = number % 1000;	//Parse the last four digits of a given number
		processors = ((number % 3) + 2);
		System.out.println(processors +" processors");
		return processors;
		
	}
}
