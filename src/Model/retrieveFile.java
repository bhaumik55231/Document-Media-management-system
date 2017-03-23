package Model;

import java.io.Serializable;
import java.sql.Blob;

public class retrieveFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int srno;
	private Blob file;
	private String file_name;
	private String file_type;
	private String description;
	private String user_id;
	
	
	
	
	
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/*public int getSr_no() {
		return Sr_no;
	}
	public void setSr_no(int sr_no) {
		Sr_no = sr_no;
	}*/
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
	
}
