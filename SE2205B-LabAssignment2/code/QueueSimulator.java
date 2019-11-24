import java.lang.*;

public class QueueSimulator {
	public enum Event {
		ARRIVAL, DEPARTURE
	};

	// required private members of the class
	private double currTime; // current time
	private double arrivalRate; // lambda
	private double serviceTime; // 1/mu aka 1/serviceRate
	private double timeForNextArrival;
	private double timeForNextDeparture;
	private double totalSimTime;
	LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
	LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
	private Event e;

	// given this function: used to create random times based on a given rate of
	// packet arrivals
	public double getRandTime(double arrivalRate) {
		double num, time1, max = 1, min = 0, randNUM;
		randNUM = Math.random();
		time1 = (-1 / arrivalRate) * (Math.log(1 - randNUM));
		// System.out.println(time1);
		return time1;
	}

	// variables aR = lambda, servT = 1/mu, simT = rho
	public QueueSimulator(double aR, double servT, double simT) {

		// initializes all of the parameters within QueueSimulator
		currTime = 0;
		arrivalRate = aR;
		serviceTime = servT;
		totalSimTime = simT;
		timeForNextArrival = currTime + getRandTime(aR);
		timeForNextDeparture = currTime + servT;
	}

	// function to calculate the average waiting time based on the arrival and
	// departure rates
	public double calcAverageWaitingTime() { // results should mirror Little's Law

		int qSize = 0; // counts the size of the queue
		double time = 0; // tracks running time

		while (!eventQueue.isEmpty()) { // runs as long as the queue has items remaining
			Data data = eventQueue.dequeue();
			time += data.getDepartureTime() - data.getArrivalTime(); // adds current wait time to the sum of wait times
			qSize++; // increase the queue size everytime an element is added
		}

		return (time / qSize); // returns the average waiting time
	}

	// function to run the queue simulation
	public double runSimulation() {

		currTime = 0; // simulation starts with a time of 0s
		timeForNextArrival = getRandTime(arrivalRate); // how long until the next packet comes
		timeForNextDeparture = serviceTime + timeForNextArrival; // packet must come in and be processed, this is the
																	// time until the next departure

		// loop to ensure the simulation runs for the required 100,000ms
		while (currTime < totalSimTime) {

			// actions if the next step for the queue is an arrival
			if (timeForNextArrival < timeForNextDeparture || buffer.isEmpty()) {

				currTime += timeForNextArrival; // moves the simulation ahead by the amount of time it takes for the
												// enxt arrival to come in

				Data packet = new Data(); // creates a new packet that is arriving

				packet.setArrivalTime(currTime); // sets the time of arrival for the next packet to the current time
													// (which was adjusted to be
													// when the next arrival comes)

				buffer.enqueue(packet); // adds the new arrival to the queue

				timeForNextDeparture -= timeForNextArrival; // subtracts this time skip from the time until the next
															// departure

				timeForNextArrival = getRandTime(arrivalRate); // generates a new next time for the next arrival

				// actions if the next step for the queue is a departure
			} else {

				currTime += timeForNextDeparture; // moves the simulation ahead by the amount of time it takes for the
													// next departure to leave

				Data packet = buffer.dequeue(); // a packet in the queue is ready to leave

				packet.setDepartureTime(currTime); // sets the time of departure for the next packet to the current time
													// (which was adjusted
													// to be when the next departure leaves)

				eventQueue.enqueue(packet); // registers the event in the queue

				timeForNextArrival -= timeForNextDeparture; // subtracts this time skip from the time until the next
															// arrival

				if (buffer.isEmpty()) { // runs in the case that the queue becomes empty

					timeForNextDeparture = timeForNextArrival + serviceTime; // next departure occurs after the packet
																				// arrives and is processed

				} else { // runs if the queue has elements in it

					timeForNextDeparture = serviceTime; // next departure occurs after the amount of time it takes to
														// process it
				}

			}

		}

		return calcAverageWaitingTime(); // returns the average waiting time based on the given parameters
	}
}
