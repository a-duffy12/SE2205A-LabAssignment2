public class Data {

	// declaration of the variables that govern the simulation
	double arrivalTime;
	double departureTime;

	// constructor
	public Data() {

		// Data's members initialized
		arrivalTime = 0;
		departureTime = 0;
	}

	// setter for the arrival time
	public void setArrivalTime(double a) {
		this.arrivalTime = a;
	}

	// setter for the departure time
	public void setDepartureTime(double d) {
		this.departureTime = d;
	}

	// getter for the arrival time
	public double getArrivalTime() {
		return this.arrivalTime;
	}

	// getter for the departure time
	public double getDepartureTime() {
		return this.departureTime;
	}

}