import java.util.List;
import java.util.ArrayList;

public class Umbra {

	private static final double THRESHOLD_PERCENTAGE = 0.99;
	private static final int MIN_TRANSIT_POINTS = 3;
	private static final double TELESCOPE_ERROR_PERCENTAGE = 0.01;

	public static void main(String[] args)
	{
		
		System.out.println("Umbra Initialised...");
		
		List<DataPoint> testList = new ArrayList<>();
		
		testList.add(new DataPoint(1,1));
		testList.add(new DataPoint(2,1));
		testList.add(new DataPoint(3,0.985));
		testList.add(new DataPoint(4,1));
		testList.add(new DataPoint(5,1));
		
		double baseline = calculateBaseline(testList);
		double threshold = calculateThreshold(baseline);

		System.out.println("Baseline: " + baseline);
		System.out.println("Threshold: " + threshold);
		
		
	}
	
	/**
	 * Calculates the mean flux of the provided light curve dataset
	 *
	 * @param list  List of DataPoints representing the light curve
	 * @return      The mean flux value, used as the stellar baseline
	 */
	
	public static double calculateBaseline(List<DataPoint> list)
	{
		double sum = 0;
		
		for( DataPoint point : list)
		{
			sum += point.getFlux();
		}
		
		return sum/list.size();
	}
	
	/**
	 * Calculates the threshold value with the provided Baseline value and Threshold percentage.
	 *
	 * @param baseline  Mean flux of the light curve
	 * @return      The minimum value for the transit trigger
	 */
	
	public static double calculateThreshold(double baseline)
	{
		return baseline*THRESHOLD_PERCENTAGE;
	}
}
