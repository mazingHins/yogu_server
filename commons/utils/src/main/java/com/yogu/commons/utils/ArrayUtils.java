/**
 * 
 */
package com.yogu.commons.utils;

/**
 * <br>
 * 
 * JFan 2014年12月10日 下午12:33:25
 */
public final class ArrayUtils {

	private ArrayUtils() {
	}

	/**
	 * An empty immutable {@code Object} array.
	 */
	public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
	/**
	 * An empty immutable {@code Class} array.
	 */
	public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
	/**
	 * An empty immutable {@code String} array.
	 */
	public static final String[] EMPTY_STRING_ARRAY = new String[0];
	/**
	 * An empty immutable {@code long} array.
	 */
	public static final long[] EMPTY_LONG_ARRAY = new long[0];
	/**
	 * An empty immutable {@code Long} array.
	 */
	public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
	/**
	 * An empty immutable {@code int} array.
	 */
	public static final int[] EMPTY_INT_ARRAY = new int[0];
	/**
	 * An empty immutable {@code Integer} array.
	 */
	public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
	/**
	 * An empty immutable {@code short} array.
	 */
	public static final short[] EMPTY_SHORT_ARRAY = new short[0];
	/**
	 * An empty immutable {@code Short} array.
	 */
	public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
	/**
	 * An empty immutable {@code byte} array.
	 */
	public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
	/**
	 * An empty immutable {@code Byte} array.
	 */
	public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
	/**
	 * An empty immutable {@code double} array.
	 */
	public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
	/**
	 * An empty immutable {@code Double} array.
	 */
	public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
	/**
	 * An empty immutable {@code float} array.
	 */
	public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
	/**
	 * An empty immutable {@code Float} array.
	 */
	public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
	/**
	 * An empty immutable {@code boolean} array.
	 */
	public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
	/**
	 * An empty immutable {@code Boolean} array.
	 */
	public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
	/**
	 * An empty immutable {@code char} array.
	 */
	public static final char[] EMPTY_CHAR_ARRAY = new char[0];
	/**
	 * An empty immutable {@code Character} array.
	 */
	public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];

	/**
	 * <p>
	 * Create a type-safe generic array.
	 * </p>
	 * 
	 * <p>
	 * The Java language does not allow an array to be created from a generic type:
	 * </p>
	 * 
	 * <pre>
	 * public static &lt;T&gt; T[] createAnArray(int size) {
	 * 	return new T[size]; // compiler error here
	 * }
	 * 
	 * public static &lt;T&gt; T[] createAnArray(int size) {
	 * 	return (T[]) new Object[size]; // ClassCastException at runtime
	 * }
	 * </pre>
	 * 
	 * <p>
	 * Therefore new arrays of generic types can be created with this method. For example, an array of Strings can be created:
	 * </p>
	 * 
	 * <pre>
	 * String[] array = ArrayUtils.toArray(&quot;1&quot;, &quot;2&quot;);
	 * String[] emptyArray = ArrayUtils.&lt;String&gt; toArray();
	 * </pre>
	 * 
	 * <p>
	 * The method is typically used in scenarios, where the caller itself uses generic types that have to be combined into an array.
	 * </p>
	 * 
	 * <p>
	 * Note, this method makes only sense to provide arguments of the same type so that the compiler can deduce the type of the array itself. While it is possible to select the type explicitly like in
	 * <code>Number[] array = ArrayUtils.&lt;Number&gt;toArray(Integer.valueOf(42), Double.valueOf(Math.PI))</code>, there is no real advantage when compared to
	 * <code>new Number[] {Integer.valueOf(42), Double.valueOf(Math.PI)}</code>.
	 * </p>
	 * 
	 * @param <T> the array's element type
	 * @param items the varargs array items, null allowed
	 * @return the array, not null unless a null array is passed in
	 * @since 3.0
	 */
	@SafeVarargs
	public static <T> T[] toArray(final T... items) {
		return items;
	}

	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if an array of Objects is empty or {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final Object[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>
	 * Checks if an array of primitive longs is empty or {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final long[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>
	 * Checks if an array of primitive ints is empty or {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>
	 * Checks if an array of primitive shorts is empty or {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final short[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>
	 * Checks if an array of primitive chars is empty or {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final char[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>
	 * Checks if an array of primitive bytes is empty or {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final byte[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>
	 * Checks if an array of primitive doubles is empty or {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final double[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>
	 * Checks if an array of primitive floats is empty or {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final float[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>
	 * Checks if an array of primitive booleans is empty or {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is empty or {@code null}
	 * @since 2.1
	 */
	public static boolean isEmpty(final boolean[] array) {
		return array == null || array.length == 0;
	}

	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if an array of Objects is not empty or not {@code null}.
	 * </p>
	 * 
	 * @param <T> the component type of the array
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static <T> boolean isNotEmpty(final T[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>
	 * Checks if an array of primitive longs is not empty or not {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final long[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>
	 * Checks if an array of primitive ints is not empty or not {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final int[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>
	 * Checks if an array of primitive shorts is not empty or not {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final short[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>
	 * Checks if an array of primitive chars is not empty or not {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final char[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>
	 * Checks if an array of primitive bytes is not empty or not {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final byte[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>
	 * Checks if an array of primitive doubles is not empty or not {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final double[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>
	 * Checks if an array of primitive floats is not empty or not {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final float[] array) {
		return (array != null && array.length != 0);
	}

	/**
	 * <p>
	 * Checks if an array of primitive booleans is not empty or not {@code null}.
	 * </p>
	 * 
	 * @param array the array to test
	 * @return {@code true} if the array is not empty or not {@code null}
	 * @since 2.5
	 */
	public static boolean isNotEmpty(final boolean[] array) {
		return (array != null && array.length != 0);
	}

	// nullToEmpty
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static Object[] nullToEmpty(final Object[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_OBJECT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 3.2
	 */
	public static Class<?>[] nullToEmpty(final Class<?>[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_CLASS_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static String[] nullToEmpty(final String[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_STRING_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static long[] nullToEmpty(final long[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_LONG_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static int[] nullToEmpty(final int[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_INT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static short[] nullToEmpty(final short[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_SHORT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static char[] nullToEmpty(final char[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_CHAR_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static byte[] nullToEmpty(final byte[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_BYTE_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static double[] nullToEmpty(final double[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_DOUBLE_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static float[] nullToEmpty(final float[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_FLOAT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static boolean[] nullToEmpty(final boolean[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_BOOLEAN_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static Long[] nullToEmpty(final Long[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_LONG_OBJECT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static Integer[] nullToEmpty(final Integer[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_INTEGER_OBJECT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static Short[] nullToEmpty(final Short[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_SHORT_OBJECT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static Character[] nullToEmpty(final Character[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_CHARACTER_OBJECT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static Byte[] nullToEmpty(final Byte[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_BYTE_OBJECT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static Double[] nullToEmpty(final Double[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_DOUBLE_OBJECT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static Float[] nullToEmpty(final Float[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_FLOAT_OBJECT_ARRAY;
		}
		return array;
	}

	/**
	 * <p>
	 * Defensive programming technique to change a {@code null} reference to an empty one.
	 * </p>
	 * 
	 * <p>
	 * This method returns an empty array for a {@code null} input array.
	 * </p>
	 * 
	 * <p>
	 * As a memory optimizing technique an empty array passed in will be overridden with the empty {@code public static} references in this class.
	 * </p>
	 * 
	 * @param array the array to check for {@code null} or empty
	 * @return the same array, {@code public static} empty array if {@code null} or empty input
	 * @since 2.5
	 */
	public static Boolean[] nullToEmpty(final Boolean[] array) {
		if (array == null || array.length == 0) {
			return EMPTY_BOOLEAN_OBJECT_ARRAY;
		}
		return array;
	}

}
