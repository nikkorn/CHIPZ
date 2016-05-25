package callstack;

import java.util.ArrayList;
import java.util.Stack;
import Contexts.Context;
import Contexts.DataItem;
import constants.C;
import exceptions.MissingEntryFunctionException;
import exceptions.MissingFunctionException;
import function.Function;

/**
 * Represents our program call stack
 * @author nh163
 *
 */
public class CallStack {
	// the stack of frames, each represents a processing function..
	private Stack<Frame> frameStack;
	// list of program functions
	private ArrayList<Function> functions;
	// global context (for global variables)
	private Context globalContext;
	
	public CallStack(ArrayList<Function> functions) throws MissingEntryFunctionException {
		// create a new global context.
		this.globalContext = new Context();
		// we need to find the entry point function in our list and add it as our initial frame.
		boolean initialFunctionExists = false;
		for(Function f : functions) {
			// is this our entry point?
			if(f.getFuntionName().equals(C.ENTRY_POINT_FUNCTION_NAME)) {
				// we found the entry point function. Set our initial frame with it.
				initialFunctionExists = true;
				try {
					invokeFunction(C.ENTRY_POINT_FUNCTION_NAME, new ArrayList<DataItem>(), null);
				} catch (MissingFunctionException e) {
					// Ignore as we have already checked to make sure this function exists.
				}
			}
		}
		// throw a critical error if we never found out entry function.
		if(!initialFunctionExists) {
			throw new MissingEntryFunctionException("missing '" + C.ENTRY_POINT_FUNCTION_NAME + "' entry function");
		}
	}
	
	/**
	 * Pushes a new frame onto the frameStack which represents a function call.
	 * @param funtionName
	 * @param funtion arguments
	 * @param reference to the DataItem return value, to be set be the invoked function.
	 */
	public void invokeFunction(String funtionName, ArrayList<DataItem> args, DataItem returnValueReference) throws MissingFunctionException {
		// does this function even exist?
		boolean functionExists = false;
		for(Function f : functions) {
			// is this our entry point?
			if(f.getFuntionName().equals(funtionName)) {
				// we found the entry point function. Set our initial frame with it.
				functionExists = true;
				invokeFunction(C.ENTRY_POINT_FUNCTION_NAME, new ArrayList<DataItem>(), null);
			}
		}
		// throw a critical error if we never found out entry function.
		if(!functionExists) {
			throw new MissingFunctionException("function '" + funtionName + "' has not been defined");
		}
	}
	
	/**
	 * Request from the top Frame that it is done and can be removed from the call stack.
	 */
	public void popFrame() {
		// pop the frame.
		frameStack.pop();
		// check whether our frameStack is empty, if so then our program is finished.
		if(frameStack.size() == 0) {
			System.out.println("program finished");  // DONE !!!
		}
	}
	
	/**
	 * Requests that the current frame process the next statement.
	 */
	public void process() {
		frameStack.peek().processNextStatement();
	}

	/**
	 * Get the global context, where we store our global variables.
	 * @return
	 */
	public Context getGlobalContext() {
		return globalContext;
	}
}
