package DAO;
import java.io.InputStream; 
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Model.retrieveFile;
import Model.uploadFile;


public class uploadDAO {
	public void fileupload(uploadFile uf) {
		try{
			Statement st=Dbconn.getConnection().createStatement();
			int row= st.executeUpdate("INSERT INTO upload_file(file,file_name, file_type, description, user_id) values('"+uf.getFile()+"','"+uf.getFile_name()+"','"+uf.getFile_type()+"','"+uf.getDescription()+"','"+uf.getUser_id()+"')");
			if(row>0){
				System.out.println("Inserted");
			st.close();
			
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public List<retrieveFile> fetchMediaList(retrieveFile rf) {
		// Course course = new Course();
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from upload_file where user_id='"+rf.getUser_id()+"'");
		
		while (rs.next()) {
			
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id("user_id");
			materiallist.add(rf1);

		}
		stmt1.close();
		
		
		
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;

	}
	public static retrieveFile fetchMedia(int materialid) throws ClassNotFoundException, SQLException {
		retrieveFile coursematerial = new retrieveFile();
		
		Statement st=Dbconn.getConnection().createStatement();
		System.out.println(materialid);
		ResultSet rs = st.executeQuery("select * from upload_file where Sr_no ='"+materialid+"'");
		
		rs.next();
		coursematerial.setSrno(rs.getInt("Sr_no"));
		coursematerial.setFile(rs.getBlob("file"));
		coursematerial.setFile_name(rs.getString("file_name"));
		coursematerial.setFile_type(rs.getString("file_type"));
		coursematerial.setDescription(rs.getString("description"));
		coursematerial.setUser_id(rs.getString("user_id"));
		st.close();
		
		return coursematerial;

	}

	
}
