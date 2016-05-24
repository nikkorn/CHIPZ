package callstack;

import java.util.ArrayList;
import java.util.Stack;
import Contexts.Context;
import Contexts.DataItem;
import function.Function;

/**
 * Represents a frame that is pushed onto the CallStack
 * @author Nikolas Howard
 *
 */
public class Frame {
	// the function represented by the frame.
	private Function frameFunction;
	// the stack of data item contexts, each context represents a new scope.
	private Stack<Context> frameContextStack;
	// the index of the token being processed in the frameFunction.
	private int frameFunctionTokenIndex;
	// reference to the call stack, needed for when a function call is made from this frame.
	private CallStack callStack;
	
	/**
	 * constructor.
	 * @param frameFunction
	 * @param args for the function call
	 * @param reference to the callStack
	 */
	public Frame(Function frameFunction, ArrayList<DataItem> args, CallStack callStack) {
		// set the fame function.
		this.frameFunction = frameFunction;
		// set reference to the call stack.
		this.callStack = callStack;
		// push a fresh starting context onto the frameContextStack.
		pushNewFrameContextOntoStack();
		// add our function arguments to our initial context.
		Context currentContext = frameContextStack.peek();
		for(int argsIndex = 0; argsIndex < args.size(); argsIndex++) {
			// get the name of the parameter from the function.
			String paramName = frameFunction.getParams().get(argsIndex);
			// define and set the variable in the context.
			currentContext.defineDataItem(paramName, args.get(argsIndex).getType());
			currentContext.setDataValue(paramName, args.get(argsIndex).getValue());
		}
		// we will start processing function tokens from first index.
		frameFunctionTokenIndex = 0;
	}
	
	/**
	 * Push a new frame context onto the stack, defining a new scope for variables.
	 */
	public void pushNewFrameContextOntoStack() {
		frameContextStack.push(new Context());
	}
	
	/**
	 * Pop a frame context from the stack, exiting a scope for variables.
	 */
	public void popFrameContextFromStack() {
		frameContextStack.pop();
	}

	/**
	 * Get the frame function.
	 * @return
	 */
	public Function getFrameFunction() {
		return frameFunction;
	}
	
	/**
	 * Called when a function call is made in during processing of this frame.
	 * @param funtionName
	 * @param args
	 */
	private void invokeFunction(String funtionName, ArrayList<DataItem> args) {
		callStack.invokeFunction(funtionName, args);
	}
}
