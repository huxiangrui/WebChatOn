package object;

public class Provider extends Person{
	//成员变量
	private String type;	
	

	private static final String PROVIDER_TYPES = "DIE";
	public static final String PROVIDER_TYPE_HELP = "Provider type must be "
				+ "one of the following characters: D(ietitian), "
				+ "I(nternist) or E(xercise Specialist)";
	
	public Provider(String number,String name,String address,String city,String country,String zip,String email,String type){
		super(number,name,address,city,country,zip,email);
		this.type=type;
	}

	public String getType()
	{
		return type;
	}
	
	public void setType(String newType)
	{
		if (PROVIDER_TYPES.indexOf(newType) < 0)
			throw new IllegalArgumentException(PROVIDER_TYPE_HELP);
		type = newType;
	}//setType
	
	//****************utility methods
	
   /** Returns a string representation of the provider consisting of the values,
	 *  converted to strings, of all the instance variables separated by
	 *  the character SEPARATOR.
	 *  @return the string representation of the provider.
	 */
	public String toString()
	{
		return super.toString() + SEPARATOR + type;
	}//toString
	

	
	/** Returns a string representation of the provider in a format that is
     *  suitable for text display.
     *  @return a formatted string representation of the provider
     */
	public String toFormattedString()
	{
		return super.toFormattedString() + "\nType:" + type;
	}//toFormattedString
}
