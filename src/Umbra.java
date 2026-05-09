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
		testList.add(new DataPoint(4,0.985));
		testList.add(new DataPoint(5,0.985));
		testList.add(new DataPoint(6,0.985));
		testList.add(new DataPoint(7,0.888));
		testList.add(new DataPoint(8,0.800));
		testList.add(new DataPoint(9,0.985));
		testList.add(new DataPoint(10,0.985));
		testList.add(new DataPoint(11,0.985));
		testList.add(new DataPoint(12,1));
		testList.add(new DataPoint(13,1));
		testList.add(new DataPoint(14,1));
		testList.add(new DataPoint(15,1));
		testList.add(new DataPoint(16,1));
		testList.add(new DataPoint(17,1));
		testList.add(new DataPoint(18,1));
		testList.add(new DataPoint(19,1));
		testList.add(new DataPoint(20,1));
		testList.add(new DataPoint(21,1));
		testList.add(new DataPoint(22,1));
		testList.add(new DataPoint(23,1));
		testList.add(new DataPoint(24,1));
		testList.add(new DataPoint(25,1));
		testList.add(new DataPoint(26,1));
		testList.add(new DataPoint(27,1));
		testList.add(new DataPoint(28,1));
		testList.add(new DataPoint(29,1.7));
		testList.add(new DataPoint(30,0.985));
		testList.add(new DataPoint(31,0.985));
		testList.add(new DataPoint(32,0.985));
		testList.add(new DataPoint(33,0.985));
		testList.add(new DataPoint(34,0.888));
		testList.add(new DataPoint(35,0.800));
		testList.add(new DataPoint(36,0.985));
		testList.add(new DataPoint(37,0.985));
		testList.add(new DataPoint(38,0.985));
		testList.add(new DataPoint(39,1));
		testList.add(new DataPoint(40,1));
		testList.add(new DataPoint(41,1));
		testList.add(new DataPoint(42,1));

		
		double baseline = calculateBaseline(testList);
		double threshold = calculateThreshold(baseline);

		System.out.println("Baseline: " + baseline);
		System.out.println("Threshold: " + threshold);
		System.out.println("Transit: ");
		
		List<TransitEvent> transitList = detectTransits(testList, threshold);
		
		for( TransitEvent event : transitList)
		{
			System.out.println(event);
		}
		
	}
	
	
	public static List<TransitEvent> detectTransits(List<DataPoint> lightCurve, double threshold)
	{
		ArrayList<TransitEvent> list = new ArrayList<>();
		double currentStart = 0;
		double endTime = 0;
		double minFlux = Double.MAX_VALUE;
		double maxFlux = Double.MIN_VALUE;
		int pointCount = 0;
		int isTransitPhase = 0;
		int isNonTransitPhase = 0;
		for( DataPoint point : lightCurve)
		{
			if(isTransitPhase < MIN_TRANSIT_POINTS)
			{
				if(point.getFlux() < threshold)
				{			
					if(isTransitPhase==0)
					{
						currentStart = point.getTime();
						minFlux = point.getFlux();
						maxFlux = point.getFlux();
					}
					else
					{
						if(point.getFlux() < minFlux )
						{
							minFlux = point.getFlux();
						}
						if(point.getFlux() > maxFlux )
						{
							maxFlux = point.getFlux();
						}
					}
					isTransitPhase++;
					pointCount++;
				}
				else
				{
					isTransitPhase = 0;
					pointCount = 0;
				}
			}
			else
			{
				if(isNonTransitPhase < MIN_TRANSIT_POINTS)
				{
					if(point.getFlux() < threshold)
					{
						isNonTransitPhase = 0;
						endTime = point.getTime();
						pointCount++;
						
						if(point.getFlux() < minFlux )
						{
							minFlux = point.getFlux();
						}
						if(point.getFlux() > maxFlux )
						{
							maxFlux = point.getFlux();
						}
					}
					else
					{
						isNonTransitPhase++;
					}
				}
				else
				{
					list.add(new TransitEvent(currentStart, endTime, minFlux, maxFlux, pointCount));
					isTransitPhase = 0;
					isNonTransitPhase = 0;
					pointCount = 0;
					minFlux = Double.MAX_VALUE;
					maxFlux = Double.MIN_VALUE;
				}
				
			}

		}
	
		return list;	
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
