package callstack;

import java.util.ArrayList;
import java.util.Stack;
import Contexts.Context;
import Contexts.DataItem;
import exceptions.MissingFunctionException;
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
	// reference to the return value DataItem stored in the frame which called this one.
	private DataItem returnValueReference;
	
	/**
	 * constructor.
	 * @param frameFunction
	 * @param args for the function call
	 * @param reference to the callStack
	 */
	public Frame(Function frameFunction, ArrayList<DataItem> args, CallStack callStack, DataItem returnValueReference) {
		// set the fame function.
		this.frameFunction = frameFunction;
		// set reference to the call stack.
		this.callStack = callStack;
		// set reference to the calling frames DataITem return value.
		this.returnValueReference = returnValueReference;
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
	 * Called when we are done with this frame.
	 */
	private void finish() {
		callStack.popFrame();
	}
	
	/**
	 * Called when a function call is made in during processing of this frame.
	 * @param funtionName
	 * @param args
	 */
	private void invokeFunction(String funtionName, ArrayList<DataItem> args, DataItem returnValueReference) {
		try {
			callStack.invokeFunction(funtionName, args, returnValueReference);
		} catch (MissingFunctionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Called by the system. Process the next statement in our function.
	 */
	public void processNextStatement() {
		// TODO Auto-generated method stub
	}
}
