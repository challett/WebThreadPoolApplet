package concurrency.WebThreadPoolApplet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.util.*;
import java.util.concurrent.*;
import concurrency.display.*;

/** 
 * <p>WebThreadPoolApplet creates the applet that runs the thread pool and user platform.  Instantiates six users and one WebServerControl.  Labels
 * are used to show which user is being serviced by which thread, as well as the number of requests to the server for each user.  Applet
 * may need to be resized to properly show the contents of the labels.</p>
 * <p>This class is the parallel implementation of the other classes shown in the associated FSP</p>
 * @author      Connor Hallett hallec3@mcmaster.ca
 * @since 		2015-03-01 
 */
public class WebThreadPoolApplet extends Applet {
	
	
	//Initialize Threads
	ThreadPanel Thread1 = new ThreadPanel("Thread1" , Color.blue);
	ThreadPanel Thread2 = new ThreadPanel("Thread2" , Color.blue);
	ThreadPanel Thread3 = new ThreadPanel("Thread3" , Color.blue);
	ThreadPanel Thread4 = new ThreadPanel("Thread4" , Color.blue);
	
	//Initialize User labels
	Label user1tries = new Label();
	Label user2tries = new Label();
	Label user3tries = new Label();
	Label user4tries = new Label();
	Label user5tries = new Label();
	Label user6tries = new Label();
	Label user1serviced = new Label();
	Label user2serviced = new Label();
	Label user3serviced = new Label();
	Label user4serviced = new Label();
	Label user5serviced = new Label();
	Label user6serviced = new Label();
	
	//Initialize User panels
		ThreadPanel User1;
		ThreadPanel User2;
		ThreadPanel User3;
		ThreadPanel User4;
		ThreadPanel User5;
		ThreadPanel User6;
		
	//Initialize Server Control with the worker ThreadPanels
	WebServerControl control = new WebServerControl(4, Thread1, Thread2, Thread3, Thread4);
	
	WebUser webuser1 = new WebUser(control , user1tries, "User1" , user1serviced);
	WebUser webuser2 = new WebUser(control , user2tries, "User2" , user2serviced);
	WebUser webuser3 = new WebUser(control , user3tries, "User3" , user3serviced);
	WebUser webuser4 = new WebUser(control , user4tries, "User4" , user4serviced);
	WebUser webuser5 = new WebUser(control , user5tries, "User5" , user5serviced);
	WebUser webuser6 = new WebUser(control , user6tries, "User6" , user6serviced);
	
	
	
	public void init(){
		//Disable thread buttons, they are run by the control.
		Thread1.run.setEnabled(false);
		Thread2.run.setEnabled(false);
		Thread3.run.setEnabled(false);
		Thread4.run.setEnabled(false);
		Thread1.pause.setEnabled(false);
		Thread2.pause.setEnabled(false);
		Thread3.pause.setEnabled(false);
		Thread4.pause.setEnabled(false);
		
		super.init();
		//Set up default text
		user1serviced.setText("User1 is not active");
		user2serviced.setText("User2 is not active");
		user3serviced.setText("User3 is not active");
		user4serviced.setText("User4 is not active");
		user5serviced.setText("User5 is not active");
		user6serviced.setText("User6 is not active");
		user1tries.setText("User1 attempts:0");
		user2tries.setText("User2 attempts:0");
		user3tries.setText("User3 attempts:0");
		user4tries.setText("User4 attempts:0");
		user5tries.setText("User5 attempts:0");
		user6tries.setText("User6 attempts:0");
		
		//Display user panels
		User1 = new ThreadPanel("User1" , Color.blue);
		User2 = new ThreadPanel("User2" , Color.blue);
		User3 = new ThreadPanel("User3" , Color.blue);
		User4 = new ThreadPanel("User4" , Color.blue);
		User5 = new ThreadPanel("User5" , Color.blue);
		User6 = new ThreadPanel("User6" , Color.blue);
		User1.pause.setEnabled(false);
		User2.pause.setEnabled(false);
		User3.pause.setEnabled(false);
		User4.pause.setEnabled(false);
		User5.pause.setEnabled(false);
		User6.pause.setEnabled(false);
		webuser1.setDisp(User1);
		webuser2.setDisp(User2);
		webuser3.setDisp(User3);
		webuser4.setDisp(User4);
		webuser5.setDisp(User5);
		webuser6.setDisp(User6);
		
		
		//Format Applet
		GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.NORTH;
        gridbag.setConstraints(Thread1, gc);
        gridbag.setConstraints(Thread2, gc);
        gridbag.setConstraints(Thread3, gc);
        gridbag.setConstraints(Thread4, gc);
		add(Thread1);
		add(Thread2);
		add(Thread3);
		add(Thread4);
		gc.gridx = 5;
        gridbag.setConstraints(user1tries, gc);
        gridbag.setConstraints(user2tries, gc);
        gridbag.setConstraints(user3tries, gc);
        gridbag.setConstraints(user4tries, gc);
        gridbag.setConstraints(user5tries, gc);
        gridbag.setConstraints(user6tries, gc);
        //add panels to applet
		add(User1);
		add(User2);
		add(User3);
		add(User4);
		add(User5);
		add(User6);
		add(user1tries);
		add(user2tries);
		add(user3tries);
		add(user4tries);
		add(user5tries);
		add(user6tries);
		gc.gridx = 6;
        gridbag.setConstraints(user1serviced, gc);
        gridbag.setConstraints(user2serviced, gc);
        gridbag.setConstraints(user3serviced, gc);
        gridbag.setConstraints(user4serviced, gc);
        gridbag.setConstraints(user5serviced, gc);
        gridbag.setConstraints(user6serviced, gc);
        //add panels to applet
		add(user1serviced);
		add(user2serviced);
		add(user3serviced);
		add(user4serviced);
		add(user5serviced);
		add(user6serviced);
	}
	
    public void start(){
    	 //Begin running the users when the applet opens, threads will be activated by the control.
    	 User1.start(webuser1);
    	 User2.start(webuser2);
    	 User3.start(webuser3);
    	 User4.start(webuser4);
    	 User5.start(webuser5);
    	 User6.start(webuser6);
    }

    public void stop() {

    }

}
