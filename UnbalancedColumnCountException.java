
public class UnbalancedColumnCountException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnbalancedColumnCountException(int neededColumnCount, int actualColumnCount) {
		super("Line has " + actualColumnCount + " columns but needs " + neededColumnCount);
	}
}
