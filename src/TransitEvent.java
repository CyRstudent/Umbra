
/**
 * Represents a single transit event detected in a stellar light curve
 * Contains summary statistics for the transit window
 */

public class TransitEvent {
	
	private double startTime;
	private double endTime;
	private double minFlux;
	private double maxFlux;
	private int pointCount;
	
	/**
	 * Creates a new TransitEvent with the given summary statistics.
	 *
	 * @param startTime   Time at which the flux dropped below threshold
	 * @param endTime     Time at which the flux recovered above threshold
	 * @param minFlux     Deepest flux value recorded during the transit
	 * @param maxFlux     Shallowest flux value recorded during the transit
	 * @param pointCount  Number of data points captured inside the transit
	 */
	
	public TransitEvent( double startTime, double endTime, double minFlux, double maxFlux, int pointCount)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.minFlux = minFlux;
		this.maxFlux = maxFlux;
		this.pointCount = pointCount;
	}
	
	@Override
	public String toString()
	{
		return ( " Start: "+ startTime + " End: " + endTime + " Min: " + minFlux + " Max: " + maxFlux + " Count: " + pointCount);
	}
	
	public double getStartTime()
	{
		return startTime;
	}

	public double getEndTime()
	{
		return endTime;
	}
	
	public double getMinFlux()
	{
		return minFlux;
	}
	
	public double getMaxFlux()
	{
		return maxFlux;
	}
	
	public int getPointCount()
	{
		return pointCount;
	}
	
	
	public double getDuration() 
	{
	    return endTime - startTime;
	}

	public double getMidTime() 
	{
	    return (startTime + endTime) / 2.0;
	}
}
