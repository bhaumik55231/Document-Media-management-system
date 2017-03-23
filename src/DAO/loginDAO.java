package DAO;

import java.sql.ResultSet;
import java.sql.Statement;

import Model.loginModel;


public class loginDAO {
	public String loginUser(loginModel login)
	{
		// TODO Auto-generated method stub
		
		
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select first_name,user_id,password from registration");
			while(rs.next()){
				String validateUser=rs.getString("user_id");
				String validatePassword=rs.getString("password");
				String firstName=rs.getString("first_name");
				if(validateUser.equals(login.getUser_id()) && validatePassword.equals(login.getPassword())){
					return firstName;
				}
			}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return "Invalid User Credentials";
	}
}
