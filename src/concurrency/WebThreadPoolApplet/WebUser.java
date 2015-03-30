package concurrency.WebThreadPoolApplet;

import java.awt.*;
import java.awt.event.ActionEvent;

import concurrency.display.ThreadPanel;

/** 
 * <p>WebUser emulates the users of the web server. A request is put out to the WebServerController every time that the ThreadPanel rotates 
 * 360 degrees or when the run button is pressed for the first time.  The number of requests sent is monitored by the internal counter.</p>
 * <p>This is the "requests" class in the associated fsm.  It acts as the body that "requests" tasks from the server</p>
 * @author      Connor Hallett hallec3@mcmaster.ca
 * @since		2015-03-01
 */
public class WebUser implements Runnable {
	WebServerControl control;
	Integer counter = 0;
	Label label;
	String name;
	Label worker;
	ThreadPanel display;
	
	/**
	 * Constructor class for WebUser
	 * @param c Denotes the controller being used
	 * @param l denotes the Label used to display the counter
	 * @param id denotes the name of the user
	 * @param Serviced denoting the current state of the user (if a thread is working on it or not)
	 */
	public WebUser(WebServerControl c, Label l, String id, Label Serviced){
		control = c;
		label = l;
		name = id;
		worker = Serviced;
	}
	
	/**
	 * Update the label displaying the state of the user
	 * @param newtext New text to display
	 */
	public void setServiceText(String newtext){
		this.worker.setText(newtext);
	}
	
	/**
	 * Setup the ThreadPanel associated with the user.
	 * @param disp ThreadPanel associated with user
	 */
	public void setDisp(ThreadPanel disp){
		display = disp;
	}

	/**
	 * Update the label displaying the current counter value
	 * @param newtext new text to display
	 */
	public void setCounterText(String newtext){
		this.label.setText(newtext);
	}
	
	/**
	 * Attempts to access the WebServer, rotates its panel when successful, and then exits the webserver, freeing up a thread for another user.
	 */
	@Override
	public void run(){
		while(true){
			try {
				control.arrive(this);
				ThreadPanel.rotate(360);
				control.depart();
				ActionEvent ae = new ActionEvent((Object)display.pause, ActionEvent.ACTION_PERFORMED, "");
				display.pause.dispatchEvent(ae);
				ThreadPanel.rotate(1);
				counter = 0;
			}catch (InterruptedException e){}
		}
	}
}
