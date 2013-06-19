package eh.team.snowfeed.model;

public class user {
	int id;
	String username;
	String password;
	String email;
	String phoneNum;
	
	//constructors
	public user() {}
	public user(int id,String username,String password,String email,String phoneNum)
	{
		this.id = id;
		this.username = username;
		this.password =password;
		this.email =email;
		this.phoneNum = phoneNum;
	}
	public user(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	public user(String username, String email, String phoneNum)
	{
		this.username = username;
		this.email = email;
		this.phoneNum = phoneNum;
	}
	public user(String username,String password, String email, String phoneNum)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNum = phoneNum;
		
	}
	//end of constructors
	
	//get methods
	public int getId()
	{
		return this.id;
	}
	public String getUsername()
	{
		return this.username;
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getEmail()
	{
		return this.email;
	}
	public String getPhone()
	{
		return this.phoneNum;
	}
	//end of get methods
	
	//set methods
	public void setId(int id)
	{
		this.id = id;
	}
	public void setUsername(String username)
	{
		this.username=username;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public void setEmail(String email)
	{
		this.email =email;
	}
	public void setPhone(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	//end of set methods
}
