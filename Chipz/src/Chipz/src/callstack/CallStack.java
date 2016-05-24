package callstack;

import java.util.ArrayList;
import java.util.Stack;
import Contexts.Context;
import Contexts.DataItem;

/**
 * Represents our program call stack
 * @author nh163
 *
 */
public class CallStack {
	// the stack of frames, each represents a processing function..
	private Stack<Frame> frameStack;
	
	/**
	 * Pushes a new frame onto the frameStack which represents a function call.
	 * @param funtionName
	 * @param funtion arguments
	 */
	public void invokeFunction(String funtionName, ArrayList<DataItem> args) {
		
	}
	
	/**
	 * Requests that the current frame process the next statement
	 */
	public void process() {
		
	}
}
