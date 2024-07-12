/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.CategoryDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Category;
import model.Product;

public class HomeController extends HttpServlet {

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
        // Khai báo kích thước trang là 15 sản phẩm
        final int PAGE_SIZE = 15;
        
        // Lấy danh sách các danh mục sản phẩm từ cơ sở dữ liệu
        List<Category> listCategories = new CategoryDBContext().getAllCategories();
        request.setAttribute("listCategories", listCategories);

        // Xác định trang hiện tại, mặc định là trang đầu tiên (page = 1)
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }

        // Lấy danh sách sản phẩm cho trang hiện tại từ cơ sở dữ liệu
        ProductDBContext productDAO = new ProductDBContext();
        List<Product> listProducts = productDAO.getProductsWithPagging(page, PAGE_SIZE);
        
        // Tính toán tổng số trang dựa trên tổng số sản phẩm và kích thước trang
        int totalProducts = productDAO.getTotalProducts();
        int totalPage = totalProducts / PAGE_SIZE; //1
        if (totalProducts % PAGE_SIZE != 0) {
            totalPage += 1;
        }
        //Đặt thông tin về trang hiện tại, tổng số trang và danh sách sản phẩm vào attribute của request
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listProducts", listProducts);

        // Lưu URL hiện tại vào session để làm lịch sử điều hướng
        request.getSession().setAttribute("urlHistory", "home");
        // Chuyển hướng request tới trang home.jsp để hiển thị thông tin sản phẩm và danh mục sản phẩm
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
