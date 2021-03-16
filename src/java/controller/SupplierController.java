/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.RoleDao;
import dao.SupplierDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RoleModel;
import model.SupplierModel;

/**
 *
 * @author dinop
 */
@WebServlet(name = "SupplierController", urlPatterns = {"/Supplier"})
public class SupplierController extends HttpServlet {

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
        String page=request.getParameter("page");
        Date dateNow = new Date(Calendar.getInstance().getTime().getTime());
        PrintWriter out=response.getWriter();
        SupplierDao dao = new SupplierDao();
        Gson g=new Gson();
        if(page==null){
            List<SupplierModel> list=dao.getAllSupplier();
            String result=g.toJson(list);
            response.setContentType("text/html;charset=UTF-8");
            out.println(result);
        }
        else if(page.equals("insert")){
            SupplierModel model = new SupplierModel();
            model.setIdSupplier(dao.getNewIdSupplier());
            model.setNamaSupplier(request.getParameter("namaSupplier"));
            model.setAlamat(request.getParameter("alamat"));
            model.setNoTelp(request.getParameter("noTelp"));
            model.setEmail(request.getParameter("email"));
            model.setUserrID(request.getParameter("userId"));
            model.setWaktu(dateNow);
            dao.save(model, page);
            response.setContentType("text/html;charset=UTF-8");
            out.print("Data Tersimpan");
        }
        else if(page.equals("update")){
            SupplierModel model = new SupplierModel();
            try{
                model.setIdSupplier(request.getParameter("idSupplier"));
                model.setNamaSupplier(request.getParameter("namaSupplier"));
                model.setAlamat(request.getParameter("alamat"));
                model.setNoTelp(request.getParameter("noTelp"));
                model.setEmail(request.getParameter("email"));
                dao.save(model, page);
                response.setContentType("text/html;charset=UTF-8");
                out.print("Data Terupdate");
            }
            catch (Exception e) {
                out.print(e.getMessage());
            }
        }
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
