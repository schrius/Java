/*Author: Dathan Huang 
 *CISC3320 Operating System
 * Date: March 4, 2017

 *idSize is a constant variable holding the size of the pid_manager
 *idnum is a private variable that indicate current amount of pid that has created.
 * p_id[] is an array holding each pid element
 */

public class pid_manager {
	final int MIN_PID = 300;
	final int MAX_PID = 5000;
	final int idSize = 4701;
	private int idnum;
	private int p_id[];
	// class constructor
	pid_manager() {
		idnum = 0;
	}

	// allocate_map create new object of p_id to hold the ipd elements.
	// in success, 1 is return, otherwise -1 is return.
	public int allocate_map() {
		p_id = new int[idSize];
		if (p_id == null)
			return -1;
		else
			return 1;
	}
	//  Allocates and returns a pid ;returns -1 if unable to allocate a pid.
	//  if current amount of pid is equal to the size, return -1 immediately.
	public int allocate_pid() {
		if (idnum == idSize)
			return -1;
		for (int i = 0; i < idSize; i++) {
			if (p_id[i] == 0) {
				p_id[i] = 1;
				idnum++;
				return i + MIN_PID;
			}
		}
		return -1;
	}
	// Releases a pid
	// if pid is found release it and decrease idnum.
	public void release_pid(int pid) {
		if (p_id[pid-MIN_PID] == 1)
			p_id[pid-MIN_PID] = 0;
		idnum--;
	}
}
