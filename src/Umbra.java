import java.util.List;
import java.util.ArrayList;

public class Umbra {

	private static final double THRESHOLD_PERCENTAGE = 0.99;

	public static void main(String[] args)
	{
		
		System.out.println("Umbra Initialised...");
		
		List<DataPoint> TestList = new ArrayList<>();
		
		TestList.add(new DataPoint(1,1));
		TestList.add(new DataPoint(2,1));
		TestList.add(new DataPoint(3,0.985));
		TestList.add(new DataPoint(4,1));
		TestList.add(new DataPoint(5,1));

		System.out.println("Baseline: " + CalculateBaseline(TestList));
		System.out.println("Threshold: " + CalculateThreshold(CalculateBaseline(TestList)));
		
		
	}
	
	public static double CalculateBaseline(List<DataPoint> list)
	{
		double sum = 0;
		
		for( DataPoint point : list)
		{
			sum += point.getFlux();
		}
		
		return sum/list.size();
	}
	
	public static double CalculateThreshold(double Baseline)
	{
		return Baseline*THRESHOLD_PERCENTAGE;
	}
}
