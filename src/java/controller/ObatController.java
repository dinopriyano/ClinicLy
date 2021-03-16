/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.ObatDao;
import dao.RoleDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ObatModel;
import model.RoleModel;

/**
 *
 * @author dinop
 */
@WebServlet(name = "ObatController", urlPatterns = {"/Obat"})
public class ObatController extends HttpServlet {

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
        PrintWriter out=response.getWriter();
        ObatDao dao = new ObatDao();
        Date dateNow = new Date(Calendar.getInstance().getTime().getTime());
        Gson g=new Gson();
        if(page==null){
            List<ObatModel> list=dao.getAllObat();
            String result=g.toJson(list);
            response.setContentType("text/html;charset=UTF-8");
            out.println(result);
        }
        else if(page.equals("insert")){
            ObatModel model = new ObatModel();
            model.setIdObat(dao.getNewIdObat());
            model.setNamaObat(request.getParameter("namaObat"));
            model.setSatuan(request.getParameter("satuan"));
            model.setStok(Double.parseDouble(request.getParameter("stok")));
            model.setHargaJual(Double.parseDouble(request.getParameter("hargaJual")));
            model.setWaktu(dateNow);
            model.setUserId(request.getParameter("userId"));
            dao.save(model, page);
            response.setContentType("text/html;charset=UTF-8");
            out.print("Data Tersimpan");
        }
        else if(page.equals("update")){
            ObatModel model = new ObatModel();
            try{
                model.setIdObat(request.getParameter("idObat"));
                model.setNamaObat(request.getParameter("namaObat"));
                model.setSatuan(request.getParameter("satuan"));
                model.setStok(Double.parseDouble(request.getParameter("stok")));
                model.setHargaJual(Double.parseDouble(request.getParameter("hargaJual")));
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
