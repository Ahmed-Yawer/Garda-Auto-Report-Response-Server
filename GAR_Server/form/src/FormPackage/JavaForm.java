package FormPackage;

import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JavaForm extends HttpServlet {
	
	private static final long serialVersionUID = 1L;	 
    public JavaForm() {
        super();
    }

    //declare a string to store the received message.
	String recievedString;	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			System.out.println("* new report has been recived!");
			//get the received message into string
			int length = req.getContentLength();            
            byte[] input = new byte[length];       
            ServletInputStream sin = req.getInputStream();                       
            int c, count = 0 ;
            while ((c = sin.read(input, count, input.length-count)) != -1) {
                count +=c;
            }
            sin.close();
            recievedString = new String(input);
            
            //sending the respond to the user
            resp.setStatus(HttpServletResponse.SC_OK);
            OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());
            writer.write("Thank you. your report has been recived.");
            writer.flush();
            writer.close();  
            System.out.println("** a resond has been sent.");
            
		}catch (IOException e) {
            try{
            	resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            	resp.getWriter().print(e.getMessage());
            	resp.getWriter().close();
            }catch (IOException ioe) {
            }
        }
		
		//start a new thread to extract the report details from the received string.
		ExtractReportDetails obj = new ExtractReportDetails(recievedString);
		obj.start();
	}
}