package DAO;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.loginModel;
import Model.registration;



public class registrationDAO {
	public void register(registration reg) {
		// TODO Auto-generated method stub

		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("INSERT INTO registration( first_name, last_name, email_id, user_id, password) values('"+reg.getFirst_name()+"','"+reg.getLast_name()+"','"+reg.getEmail_id()+"','"+reg.getUser_id()+"','"+reg.getPassword()+"') ");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	
	public String validate(registration reg)
	{
		// TODO Auto-generated method stub
		
		
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select email_id,user_id from registration");
			while(rs.next()){
				String validateUserId=rs.getString("user_id");
				String validateEmailId=rs.getString("email_id");
				if(validateEmailId.equals(reg.getEmail_id())){
					return "Email-Id is already registered!";
				}
				else if(validateUserId.equals(reg.getUser_id())){
					return "User-Id is already registered!";
				}
			}
			st.close();
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return "success";
	}
}