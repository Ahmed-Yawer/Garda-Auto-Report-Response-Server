package FormPackage;

public class ExtractReportDetails extends Thread{
	//create Incident Deque instance.
	public static IncidentDeque dequeuInstance = new IncidentDeque();
		
	private String recievedString;
	
	//the class constructor, used to pass the received string to this thread.
	public ExtractReportDetails(String recievedString){
		this.recievedString = recievedString;
	}
	
	//starting the thread of extract the report details from the received string.
	public void run(){
		//converting the received string to array of char to extract the information.
        char[] arrayOfCharRecived = recievedString.toCharArray();
        //declare array of String to arrange the extracted data  in. 
        String[] temp = new String[6];
        int j =0;
        //extract the data from the array of char into array of String.
        for (int i=0 ; i < arrayOfCharRecived.length ; i++){
        	if ( arrayOfCharRecived[i] != '^'){
        		if ( temp[j] == null){
        			temp[j] = Character.toString(arrayOfCharRecived[i]);
        		}
        		else{
        			temp[j] += arrayOfCharRecived[i];
        		}            		
        	}
        	else{
        		j++;
        		continue;
        	}
        }
        
        //arrange the data into report instance
		ReportDetails report = new ReportDetails();
		report.setName(temp[0]);
		report.setPhoneNumber(temp[1]);
		report.setHomeAddress(temp[2]);
		report.setTheReport(temp[3]);
		report.setLat(Double.parseDouble(temp[4]));
		report.setLng(Double.parseDouble(temp[5]));
		
		//getting the incident's details into String to use for finding it's priority.
		String theReceivedRrport = temp[3];
		
		//setting the priority level
		if ( theReceivedRrport.equals("My car been stolen")){
			report.setPriorityLevel(1);
		}
		else if (theReceivedRrport.equals("My home been broke in.")){
			report.setPriorityLevel(2);
		}
		else if (theReceivedRrport.equals("I am facing street Accident")){
			report.setPriorityLevel(0);
		}
		else{
			report.setPriorityLevel(9);
		}
		System.out.println("**** The report priority has been specified.");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//adding the received report to the incidents queue.
		dequeuInstance.insertReprotPriority(report);
		System.out.println("***** The report has been added to the incident's queue");
		//for test - printing all the report queue.
		System.out.println("****** Printing all the reports in the queue..");
		System.out.println(dequeuInstance);
		System.out.println("--------------------------------------------------");
	}
}
