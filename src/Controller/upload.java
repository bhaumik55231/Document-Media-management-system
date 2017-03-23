package Controller;

import java.io.IOException;  
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.uploadDAO;
import Model.retrieveFile;
import Model.uploadFile;
import serviceLayer.validation;
/**
 * Servlet implementation class upload
 */
@WebServlet("/upload")
@MultipartConfig
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String description=request.getParameter("description");
		String userId=(String)session.getAttribute("userId");
		Part filePart = request.getPart("fileName");
		String file_name=filePart.getSubmittedFileName();
		String file_type=filePart.getContentType();
		InputStream inputStream = null;
		
		if (filePart != null) {
            inputStream = filePart.getInputStream();
		}
		
		
		uploadFile uf=new uploadFile();
		retrieveFile rf=new retrieveFile();
		uf.setFile(inputStream);
		uf.setFile_name(file_name);
		uf.setFile_type(file_type);
		uf.setDescription(description);
		uf.setUser_id(userId);
		rf.setUser_id(userId);
		
		
		validation validation=new validation(); 
		String validatefile=validation.fileupload(uf);
		if(validatefile.equals("Please Upload File") || validatefile.equals("Please enter file Description!")){
			request.setAttribute("error", validatefile);
			RequestDispatcher rd=request.getRequestDispatcher("main.jsp");
			rd.forward(request, response);
		}
		else{
		
		uploadDAO dao= new uploadDAO();
		dao.fileupload(uf);
		
		
		List<retrieveFile> media_files = new ArrayList<retrieveFile>();
		media_files=dao.fetchMediaList(rf);
		
		
		session.setAttribute("media_files", media_files);
		response.sendRedirect("main.jsp");
		}
	}
}
