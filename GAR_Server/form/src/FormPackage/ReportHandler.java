package FormPackage;

import static FormPackage.autoRun.GardaArray;
import static FormPackage.ExtractReportDetails.dequeuInstance;
import java.util.Random;

public class ReportHandler extends Thread {
	
	//declare GardaNearByArray
	public GardaCar[] GardaNearByArray = new GardaCar[20];
	//declare Range Array to store the range where to look for Garda cars in, it has two values for the first and second attempts.
	int rangeArray[] = {1500,2500}; //the searching range in meters. the 1500m for the first attempt, and the 2500 for the second.
	
	public void run(){
		while (true) {
			//deal with the first report in the deque if it is not empty.
			if (!dequeuInstance.isEmpty()){
	    		reportHundler();
			} else{
				//wait for 1 second before check the incidentDeque again
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}
	
	//Handle report.
		public void reportHundler(){
			ReportDetails report = new ReportDetails ();
			try{
				System.out.println("Start of hundling the first report at the queue. \n");
				//get the first report at the queue.
				report = (ReportDetails) dequeuInstance.removeFirst();
				//extract it's location
				Double lat = report.getLat();
				Double lng = report.getLng();
				
				//Initializing the GardaNearByArray.
				for (int i = 0 ; i < GardaNearByArray.length ; i++){
					GardaNearByArray[i] = new GardaCar();
				}
				
				//the bellow code is for test only - printing the distance between the report and all Garda cars
				System.out.println("the distance between the incident and all Garda cars is:-");
				for (int i = 0 ; i < GardaArray.length ; i++){
					double distanceBetweenPoints = distFrom(lat,lng,GardaArray[i].getLat(),GardaArray[i].getLng());
					System.out.println("Garda car("+i+") is " +  Math.round(distanceBetweenPoints) + " meters away.");
				}
				
				//attempt to send the report 4 time max.
				for (int attampteNumber=1 ; attampteNumber<=4 ; attampteNumber++){
					//Fill the GardaNearByArray.			
					if (attampteNumber == 1 || attampteNumber == 3){ //no need to re fill the GardaNearByArray at attempt 2 and 4 as they will be the same as 1 and 3, where the search range change once every two attempts.
						fillGardaNearBy(lat,lng,attampteNumber);
					}
					
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(attampteNumber == 1){
					System.out.println("------------------------------------------------------------------------------");
					System.out.println(" NOTE: Here we will start simulate sending the report to the Garda cars, We will attempt \n"
							+ "to send the report 4 times, the first two times it will be sent to Garda cars within 1500m \n"
							+ "range, but for the 3rd and 4th attempts it will be sent to Garda cars that within range 2500m.\n"
							+ "On the other hand, a random ganaretor will simulate the three Possible scenarios of Garda car's respod (one \n"
							+ "car respond, more than one respond, no one respond).");
					System.out.println("------------------------------------------------------------------------------");
					}
					
					//assuming the report been sent to all ....!
					System.out.println("Send the report to all the entries of the GardaNearByArray. (attempt number "+attampteNumber+")");
					//simulate the three scenarios of Garda Cars respond by using random generator between 0-2,
					//0 refers to one car respond, 1 refers to more than one car respond, 2 refers to no one respond.
					Random randomGenerator = new Random();
					int randomInt = randomGenerator.nextInt(3);
					// printing the random generator result
					System.out.print("the random generator came up with number "+randomInt+" which referes to: ");
					
					if (randomInt == 0){
						System.out.println("Respond from only one Garda car has been recieved. THE ACTION: Send the "
								+ "confirmation message to that car.");
						break;
					}
					else if (randomInt == 1){
						System.out.println("More than one Garda cars has responded. THE ACTION: Send the confirmation "
								+ "message to the closest one, and send the ignore message to the others.");
						break;
					}
					else{
						System.out.println("NO Garda car has respond. THE ACTION: for the first attempt on this range,"
								+ "resend the report to the same Garda list. For the second attempt on the same reange, "
								+ "Increase the search range and look for more Garda cars.");
						//after 4 attempts push that report back to the queue and proceed to the next report.
						if (attampteNumber == 4){
							//dequeuInstance.insertLast(report);
							dequeuInstance.insertReprotPriority(report);
							System.out.println("after 4 attempts no one respond for this report so it has been pushed "
									+ "backed to the queue");
						}
					}
				}
				System.out.println("end of the report ---------------------------------------------------------");
			}
			catch (EmptyDequeException ex){
				System.out.println("Error: the error in the reportHundler method, it might be removing from an empty deque!");
			}	
		}
		
		//fill the GardaNearBy array with the Garda cars that are the closest to the incident's location which received as argument.
		public void fillGardaNearBy(double lat,double lng,int attampteNumber){
			int NearByArraySize = 0;
			for (int i = 0 ; i < GardaArray.length ; i++){
				double distanceBetweenPoints = distFrom(lat,lng,GardaArray[i].getLat(),GardaArray[i].getLng());
				// if the distance less than the searching range add it to the GardaNearByArray. 
				//NOTE: the first two attempt pick the first range, the second two attempt pick the second range.
				if (distanceBetweenPoints <= rangeArray[ ( attampteNumber < 3 ? 0 : 1 ) ]){ 			
					GardaNearByArray[NearByArraySize] = GardaArray[i];
					NearByArraySize++ ;
				}
			}
			//printing the picked Garda cars:
			System.out.println("\n The Garda cars that within range less than ("+rangeArray[ ( attampteNumber < 3 ? 0 : 1 ) ]+
					"m) form the incident been added to the GardaNearBy array:- ");
			for ( int i = 0 ; i < NearByArraySize ; i++){
				System.out.println("("+i+") "+GardaNearByArray[i]);
			}
		}
		
		//Calculate distance between two points.
		public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
			double earthRadius = 6371000; //meters
		    double dLat = Math.toRadians(lat2-lat1);
		    double dLng = Math.toRadians(lng2-lng1);
		    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
		               Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    double dist = (float) (earthRadius * c);
		    return dist;
		    }

}
