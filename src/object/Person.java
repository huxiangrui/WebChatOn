package object;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Person {
	//成员变量
	private String number;   
	private String name;   
	private String address;   
	private String city ;  
	private String country;  
	private String zip;  
	private String email;   

	protected static final char SEPARATOR = '#';
	public static final int NAME_LENGTH = 25;	
	public static final int STREET_LENGTH = 25;
	public static final int CITY_LENGTH = 14;
	public static final int STATE_LENGTH = 2;
	public static final int ZIP_LENGTH = 5;
		
	public Person(String number,String name,String address,String city,String country,String zip,String email){ 
		this.number=number;
		this.name=name;
		this.address=address;
		this.city=city;
		this.country=country;
		this.zip=zip;
		this.email=email;
	}
	//********************accessor methods
	
	/** Returns the person's unique number
	 *  @return the person's unique number
	 */
	public String getNumber()
	{
		return number;
	}//getNumber

	/**
	 * Returns the person's name
	 * @return the person's name
	 */
	public String getName()
	{
		return name;
	}//getName	
	
	/**
	 * Returns the person's street address
	 * @return the person's street address
	 */
	public String getAddress()
	{
		return address;
	}//getStreet
	
	/**
	 * Returns the person's city
	 * @return the person's city
	 */	
	public String getCity()
	{
		return city;
	}//getCity
	
	/**
	 * Returns the person's state
	 * @return the person's state
	 */
	public String getCountry()
	{
		return country;
	}//getState
	
	/**
	 * Returns the person's zip code
	 * @return the person's zip code
	 */
	public String getZip()
	{
		return zip;
	}//getZip
		
	/**
	 * Returns the person's email address
	 * @return the person's email address
	 */
	public String getEmail()
	{
		return email;
	}//getEmail
	
	//**********************mutator methods
	public void setNumber(String aNumber){
		if (aNumber == null || aNumber.length() == 0)
			throw new IllegalArgumentException("A number is required");
		String regEx = "^[1-9]*$";
	    Pattern pattern = Pattern.compile(regEx);
	    Matcher matcher = pattern.matcher(aNumber);
	    if(matcher.matches()&&aNumber.length()==9){
	    	number=aNumber;
	    }
	    else{
	    	throw new IllegalArgumentException("Number code must be exactly " 
						+ ZIP_LENGTH + " digits");
	    }
	}
	
	/** Changes the person's name
	 *  @param aName the new name
	 *  @throws IllegalArgumentException if aName is null, an empty string
	 *             or longer than NAME_LENGTH characters
	 */
	public void setName(String aName)
	{
		if (aName == null || aName.length() == 0)
			throw new IllegalArgumentException("A name is required");
		else if (aName.length() > NAME_LENGTH)
			throw new IllegalArgumentException("Name may not be more than " 
				+ NAME_LENGTH + " characters");
		name = aName;
	}//setName		
	
	/** Changes the person's street address
	 *  @param aStreet the new street address
	 *  @throws IllegalArgumentException if aStreet is not null
	 *             and is longer than STREET_LENGTH characters
	 */
	public void setAddress(String aAddress)
	{
		if (aAddress == null) aAddress = "";
		else if (aAddress.length() > STREET_LENGTH) //street is not required
					throw new IllegalArgumentException("Street may not be more than " 
						+ STREET_LENGTH + " characters");
		address = aAddress;
	}//setStreet

	/** Changes the person's city
	 *  @param aCity the new city
	 *  @throws IllegalArgumentException if aCity is not null
	 *             and is longer than CITY_LENGTH characters
	 */
	public void setCity(String aCity)
	{
		if (aCity == null) aCity = "";
		else if (aCity.length() > CITY_LENGTH) //city is not required
			throw new IllegalArgumentException("City may not be more than " 
					+ CITY_LENGTH + " characters");
		city = aCity;
	}//setCity
	
	/** Changes the person's state
	 *  @param aState the new state
	 *  @throws IllegalArgumentException if aState is not 
	 *             exactly STATE_LENGTH letters, null or an empty string
	 */
	public void setCountry(String aCountry)
	{
		if (aCountry == null) aCountry = "";
		else if (aCountry.length() > 0) //state is not required
		{
			if (aCountry.length() != STATE_LENGTH) 
				throw new IllegalArgumentException("State must be exactly "
				 		+ STATE_LENGTH + " letters");
			//test whether each character is a letter
			for (int i = 0; i < STATE_LENGTH; i++)
				if(! Character.isLetter(aCountry.charAt(i)))
					throw new IllegalArgumentException("State must be " 
						+ STATE_LENGTH + " letters only");
		}		
				
		country = aCountry;
	}//setState
	
	/** Changes the person's zip code
	 *  @param aZip the new zip code
	 *  @throws IllegalArgumentException if aZip 
	 *             is not null or an empty string and
	 *             is not composed entirely of digits or
	 *             is longer than ZIP_LENGTH digits 
	 */
	public void setZip(String aZip)
	{
		if (aZip == null) aZip = "";         //Replace null with an empty string
		else if (aZip.length() > 0)          //if given, zip must be 5 digits
		{
			//test for correct length
			if (aZip.length() != ZIP_LENGTH) 
				throw new IllegalArgumentException("Zip code must be exactly " 
						+ ZIP_LENGTH + " digits");
			
			//test whether each character is a digit
			for (int i = 0; i < ZIP_LENGTH; i++)
				if (!Character.isDigit(aZip.charAt(i)))
					throw new IllegalArgumentException("Zip code must be " 
							+ ZIP_LENGTH + " digits only");
		}		
				
		zip = aZip;
	}//setZip
	
	/** Changes the person's email address.  No validation is done.
	 *  @param anEmail the new email address
	 */
	public void setEmail (String anEmail)
	{
		if (anEmail == null) anEmail = "";
		email = anEmail;
	}//setEmail
		
	//***********************utility methods
	
    /** Returns a string representation of the person consisting of the values,
     *  converted to strings, of all the instance variables separated by
     *  the character SEPARATOR.
     *  @return the string representation of the person.
     */
    public String toString()
    {
        return  "" + number + SEPARATOR + name + SEPARATOR  
         + address + SEPARATOR + city + SEPARATOR + country + SEPARATOR 
         + zip + SEPARATOR + email;
    }//toString
        
    /** Changes all the instance variables to the values given by the string 
     *  representation of the person.
     *  @param data the string representation of the person
     *  @throws NumberFormatException if the person number 
     *				 is not a valid integer
     *  @throws IllegalArgumentException if any of the values are
     *             invalid.
     *  @throws IndexOutOfBoundsException if there are 
     *             not enough values in the string
     */

    
    /** Returns a string representation of the person in a format that is
     *  suitable for text display.
     *  @return a formatted string representation of the person
     */
    public String toFormattedString()
    {
    	String personString   = "Number:" + number
    			      		  + "\nName:" + name
    			      		  + "\nAddress:" + address
    			     		  + "\nCity:" + city
    			      		  + "\nCountry:" + country
    			      		  + "\nZip Code:" + zip
    			      		  + "\nEmail:" + email;
    	return personString;
    }

}
