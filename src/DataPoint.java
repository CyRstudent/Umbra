
public class DataPoint {

	private double Time;
	private double Flux;
	
	public DataPoint(double Time, double Flux) {
		this.Time = Time;
		this.Flux = Flux;
	}
	
	public double getTime()
	{
		return Time;
	}
	
	public double getFlux()
	{
		return Flux;
	}
}
