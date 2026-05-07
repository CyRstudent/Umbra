
public class DataPoint {

	private double time;
	private double flux;
	
	public DataPoint(double time, double flux) {
		this.time = time;
		this.flux = flux;
	}
	
	public double getTime()
	{
		return time;
	}
	
	public double getFlux()
	{
		return flux;
	}
}
