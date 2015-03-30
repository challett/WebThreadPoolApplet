package concurrency.WebThreadPoolApplet;

import concurrency.display.ThreadPanel;

/** 
 * <p>Worker is the thread type that makes up the thread pool.  It simulates work by rotating the ThreadPanel 360 degrees.</p>
 * <p>This class is the "Thread" class in the associated fsm, it replicates "doing work" while inside of the webserver.</p>
 * @author      Connor Hallett hallec3@mcmaster.ca
 * @since 		2015-03-01 
 * 
 **/
public class Worker implements Runnable {
	private boolean busy = false;
	private WebUser user;
	ThreadPanel display;

	
	public Worker(){
	}
	/**
	 * Sets the user that the worker is currently working on
	 * @param u WebUser that worker is to work on.
	 */
	public void setUser(WebUser u){
		this.user = u;
	}
	
	/**
	 * Indicates whether the worker is currently busy working or not
	 * @param working boolean value of current working state
	 */
	public void setWorking(boolean working){
		this.busy = working;
	}
	
	/**
	 * Boolean value indicating the current working state of the worker
	 * @return Boolean value for current working state
	 */
	public boolean isWorking(){return this.busy;};
	
	/**
	 * Rotates its ThreadPanel while working on a request from a user, becomes available afterwards
	 */
	@Override
	public void run() {
		try {
			ThreadPanel.rotate(360);
			user.setServiceText(user.name + "'s request has been serviced");
			busy = false;
		} catch (InterruptedException e) {}
	}
	
	

}
