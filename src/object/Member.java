package object;

public class Member extends Person{
	
	//成员变量
	private String status;
		
	private static final String MEMBER_STATUS_VALUES = "AS";
    public static final String MEMBER_STATUS_HELP = "Member status must be "
					+ "one of the following characters: A(ctive) or S(uspended)";
	
	public Member(String number,String name,String address,String city,
			String country,String zip,String email,String status){
		super(number,name,address,city,country,zip,email);
		this.status=status;
	}

	
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String newStatus)
	{
		if (MEMBER_STATUS_VALUES.indexOf(newStatus) < 0)
			throw new IllegalArgumentException(MEMBER_STATUS_HELP);
		status = newStatus;
	}

	public String toString()
	{
		return super.toString() + SEPARATOR + status;
	}

	public String toFormattedString()
	{
		return super.toFormattedString() + "\nStatus:" + status;
	}

}
