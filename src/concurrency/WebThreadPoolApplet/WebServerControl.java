package concurrency.WebThreadPoolApplet;

import java.awt.*;
import java.util.Random;
import concurrency.display.ThreadPanel;

/** 
 * <p>WebServerController controls access to worker threads.  Creates a thread pool of four threads that can complete a task for users of the webserver.</p>
 * <p>This class is the monitor seen in the associated fsp, it monitors and commands the threads.
 * @author      Connor Hallett hallec3@mcmaster.ca
 * @since 		2015-03-01 
 * 
 */
public class WebServerControl {
	protected int workers;
    protected int capacity;
    private ThreadPanel worker1;
    private ThreadPanel worker2;
    private ThreadPanel worker3;
    private ThreadPanel worker4;
    private Worker workerthread1 =  new Worker();
    private Worker workerthread2 =  new Worker();
    private Worker workerthread3 =  new Worker();
    private Worker workerthread4 =  new Worker();
    
    /**
     * Constructor class for WebServerControl
     * @param n Maximum number of workers (4)
     * @param w1 First worker panel
     * @param w2 Second worker panel
     * @param w3 third worker panel
     * @param w4 fourth worker panel
     */
    WebServerControl(int n, ThreadPanel w1, ThreadPanel w2, ThreadPanel w3, ThreadPanel w4) {
        capacity = workers = n;
        worker1 = w1;
        worker2 = w2;
        worker3 = w3;
        worker4 = w4;
    }
    
    /**
     * 
     * @param u The WebUser that is attempting to arrive at the WebServer.
     * @throws InterruptedException users are forced to wait 500ms after their request to insure all users are serviced
     */
    synchronized void arrive(WebUser u) throws InterruptedException {
    	u.counter++;
    	u.worker.setText(u.name + " is waiting");  						//recognize that a user is waiting
    	u.setCounterText(u.name + " attempts:"+ u.counter.toString()); //display the current count of user requests 
        while (workers==0) {
        	u.setCounterText(u.name + " attempts:"+ u.counter.toString());
        	wait();
        }
        --workers;
        wait(100);
        work(u);
        notifyAll();
    }
    
   /**
    * Delegates a users work to a free thread in the thread pool
    * @param u The user that is to have work done
    * @throws InterruptedException Throws InterruptedException
    */
  void work(WebUser u) throws InterruptedException{ 					
	   if(!workerthread1.isWorking()){ 				 						//check is worker is busy
		   	workerthread1.setWorking(true);	
		   	workerthread1.setUser(u);
       		worker1.start(workerthread1, false);							//start the worker
       		u.setServiceText(u.name + " is being serviced by thread 1"); 	//show that the user is being serviced by this worker
       }else if(!workerthread2.isWorking()){
    	   	workerthread2.setWorking(true);
    	   	workerthread2.setUser(u);
       		worker2.start(workerthread2, false);
       		u.setServiceText(u.name + " is being serviced by thread 2");
       }else if(!workerthread3.isWorking()){
    	    workerthread3.setWorking(true);
       		workerthread3.setUser(u);
       		worker3.start(workerthread3, false);
       		u.setServiceText(u.name + " is being serviced by thread 3");
       }else if(!workerthread4.isWorking()){
	       	workerthread4.setWorking(true);
	       	workerthread4.setUser(u);
	       	worker4.start(workerthread4, false);
	       	u.setServiceText(u.name + " is being serviced by thread 4");
       }
	   
   }
  
   /**
    * Increments the monitor when a user is done with a thread
    * @throws InterruptedException Makes threads wait while no threads are busy
    */
    synchronized void depart() throws InterruptedException{
        while (workers==capacity) wait();
        ++workers;
        notifyAll();
        wait(100);
     }
}