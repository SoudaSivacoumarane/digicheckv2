package com.sterling.digicheck.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sterling.digicheck.batch.exception.BatchException;
import com.sterling.digicheck.document.dao.DocumentDAO;
import com.sterling.digicheck.document.entity.DocumentEntity;

public class ImageServlet extends HttpServlet {	   
    
	/** Serial Version UID*/
	private static final long serialVersionUID = -8286537011169333526L;
	private static final String DOCUMENT_BEAN_NAME = "documentDAO";
	private static final String ID = "id";
	private static final String CONTENT_TYPE = "image/jpeg";
	private static final String CONTENT_LENGTH = "Content-Length";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
    	
    	final ServletContext servletContext = getServletContext();
    	final ApplicationContext context    = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    	DocumentDAO documentDAO = (DocumentDAO) context.getBean(DOCUMENT_BEAN_NAME);
    	DocumentEntity documentEntity = null;
        // Get ID from request.
        String imageId = request.getParameter(ID);
                
        // Check if ID is supplied to the request.
        if (imageId == null) {
            // Do your thing if the ID is not supplied to the request.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Lookup Image by ImageId in database.
        // Do your "SELECT * FROM Image WHERE ImageID" thing.
        //Image image = imageDAO.find(imageId);
        try {
			documentEntity = documentDAO.getDocument(imageId);
		} catch (BatchException e) {			
			e.printStackTrace();
		}

        // Check if image is actually retrieved from database.
        if (documentEntity == null) {
            // Do your thing if the image does not exist in database.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Init servlet response.
        response.reset();
        response.setBufferSize(documentEntity.getDocFile().length);
        response.setContentType(CONTENT_TYPE);
        response.setHeader(CONTENT_LENGTH, String.valueOf(documentEntity.getDocFile().length));
        //response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");

        // Prepare streams.
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        InputStream inputStream = null;
        try {
        	inputStream = new ByteArrayInputStream(documentEntity.getDocFile());        	
        	
            // Open streams.
            input = new BufferedInputStream(inputStream);
            output = new BufferedOutputStream(response.getOutputStream());

            // Write file contents to response.
            byte[] buffer = new byte[documentEntity.getDocFile().length];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } finally {
            // Gently close streams.
            close(output);
            close(input);
        }
    }

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
    }
}