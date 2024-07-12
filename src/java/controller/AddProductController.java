/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.net.httpserver.HttpsServer;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Product;

/**
 *
 * @author MissNga
 */
@WebServlet(name = "AddProductController", urlPatterns = {"/add"})
public class AddProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        // Lấy dữ liệu từ request
//        String name = request.getParameter("name").trim();
//        String image = request.getParameter("image").trim();
//        String priceStr = request.getParameter("price").trim();
//        String title = request.getParameter("title").trim();
//        String description = request.getParameter("description").trim();
//        String categoryStr = request.getParameter("category").trim();
//
//        // Kiểm tra xem có trường nào trống hoặc chỉ chứa khoảng trắng không
//        if (name.isEmpty() || image.isEmpty() || priceStr.isEmpty() || title.isEmpty() || description.isEmpty() || categoryStr.isEmpty()) {
//            // Nếu có trường nào trống hoặc chỉ chứa khoảng trắng, chuyển hướng trở lại trang quản lý sản phẩm với thông báo lỗi
//           // request.setAttribute("mess", "not isbnlk");
//            response.sendRedirect("manager?error=empty"); // Chuyển hướng đến trang quản lý sản phẩm và gửi thông báo lỗi qua tham số error
//            return;
//        }
//
//        // Chuyển đổi giá trị giá và mã danh mục thành dạng số
//        double price = Double.parseDouble(priceStr);
//        int category = Integer.parseInt(categoryStr);
//
//        // Thêm sản phẩm vào cơ sở dữ liệu
//        Product product = new Product();
//        product.setName(name);
//        product.setImageUrl(image);
//        product.setPrice(price);
//        product.setTiltle(title);
//        product.setDescription(description);
//        product.setCategoryId(category);
//
//        HttpSession session = request.getSession();
//        Account a = (Account) session.getAttribute("acc");
//        int cid = a.getUid();
//        product.setSell_ID(cid);
//
//        ProductDBContext pdb = new ProductDBContext();
//        pdb.inSertProduct(product);
//
//        // Chuyển hướng đến trang quản lý sản phẩm sau khi thêm sản phẩm thành công
//        response.sendRedirect("manager");

        Product product = new Product();
//        String name = request.getParameter("name");
//        if (name == null || name.isBlank()) {
//            request.setAttribute("message", "fail");
//            response.sendRedirect("manager");
//            
//        } else {
        product.setName(request.getParameter("name"));
        product.setImageUrl(request.getParameter("image"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setTiltle(request.getParameter("title"));
        product.setCategoryId(Integer.parseInt(request.getParameter("category")));
        product.setDescription(request.getParameter("description"));

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int cid = a.getUid();
        product.setSell_ID(cid);

        ProductDBContext pdb = new ProductDBContext();
        pdb.inSertProduct(product);
        response.sendRedirect("manager");
 //   }

}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
