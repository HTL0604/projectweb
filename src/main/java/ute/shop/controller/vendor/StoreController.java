package ute.shop.controller.vendor;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@MultipartConfig(fileSizeThreshold = 1024*1024,maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
@WebServlet(urlPatterns = { "/vendor/stores", "/vendor/store/add" ,"/vendor/store/insert","/vendor/store/edit","/vendor/store/update","/vendor/store/delete","/vendor/store/search"})
public class StoreController extends HttpServlet {

	private static final long serialVersionUID = 1L;

}
