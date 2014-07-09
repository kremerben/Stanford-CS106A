/**
 * This class provides methods for working with an array that expands 
 * to include any positive index value supplied by the caller.
 */
public class ExpandableArray {
	/**
	 * Creates a new expandable array with no elements. */
	public ExpandableArray() {
		ExpandableArray = new Object[0];
	}
	
	
	/**
	 * Sets the element at the given index position to the specified.
	 * value. If the internal array is not large enough to contain that
	 * element, the implementation expands the array to make room. 
	 */
	public void set(int index, Object value) {
		if (index >= ExpandableArray.length) { // had index > length-1 == same thing
			Object[] newArray = new Object[index+1]; //needs plus one because it's the length
			for (int i = 0; i < ExpandableArray.length; i++) {
				newArray[i] = ExpandableArray[i];
			}
			ExpandableArray = newArray;
		}
		ExpandableArray[index] = value;		
	}
	
	/**
	 * Returns the element at the specified index position, or null if
	 * no such element exists. Note that this method never throws an
	 * out-of-bounds exception; if the index is outside the bounds of 
	 * the array, the return value is simply null.
	 */
	public Object get(int index) {
//		if (ExpandableArray[index].equals(null)) {
//			return null;
//		}
		if (index >= ExpandableArray.length) return null;
		return ExpandableArray[index];
	} 
	
	private Object[] ExpandableArray;
}