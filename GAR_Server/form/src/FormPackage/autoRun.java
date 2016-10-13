package FormPackage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class autoRun extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	//declare GardaArray
	public static GardaCar[] GardaArray = new GardaCar[20];
	
    public void init() throws ServletException
    {
          System.out.println("---------------------------------------------------------------------------");
          System.out.println("------------------------- Inicialasing the server -------------------------");
          System.out.println("---------------------------------------------------------------------------");
          
        //Initializing the GardaArray
  		for (int i = 0 ; i < GardaArray.length ; i++){
  			GardaArray[i] = new GardaCar();
  		}
  		//Fill GardaArray with dummy values
  		for (int i = 0 ; i < GardaArray.length ; i++){
  			GardaArray[i].setFirstMember("Ahmed"+i);
  			GardaArray[i].setSecondMember("Yawer"+i);
  			GardaArray[i].setCarReg(1000+i);
  			GardaArray[i].setLat(53.385157+(i*0.001));
  			GardaArray[i].setLng(-6.390496+(i*0.001));
  		}
  		//for test - printing the GardaArray
  		System.out.print("The Garda array is:-\n");
  		for (int i = 0 ; i < GardaArray.length ; i++){
  			System.out.print("("+i+") "+GardaArray[i].getFirstMember()+" | ");
  			System.out.print(GardaArray[i].getSecondMember()+" | ");
  			System.out.print(GardaArray[i].getCarReg()+" | ");
  			System.out.print(GardaArray[i].getLat()+" | ");
  			System.out.print(GardaArray[i].getLng()+"\n");
  		}
  		
  		//start the report handling thread.
  		ReportHandler obj = new ReportHandler();
  		obj.start();
    }
}