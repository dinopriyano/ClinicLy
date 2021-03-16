/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.KamarDao;
import dao.ObatDao;
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
import model.KamarModel;
import model.ObatModel;

/**
 *
 * @author dinop
 */
@WebServlet(name = "Kamar", urlPatterns = {"/Kamar"})
public class KamarController extends HttpServlet {

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
        KamarDao dao = new KamarDao();
        Date dateNow = new Date(Calendar.getInstance().getTime().getTime());
        Gson g=new Gson();
        if(page==null){
            List<KamarModel> list=dao.getAllKamar();
            String result=g.toJson(list);
            response.setContentType("text/html;charset=UTF-8");
            out.println(result);
        }
        else if(page.equals("insert")){
            KamarModel model = new KamarModel();
            model.setIdKamar(dao.getNewIdKamar());
            model.setNamaRuang(request.getParameter("namaKamar"));
            model.setNoKamar(request.getParameter("noKamar"));
            model.setKelas(request.getParameter("kelas"));
            model.setHargaPerhari(Double.parseDouble(request.getParameter("hargaPerhari")));
            model.setDeskripsiKamar(request.getParameter("desKamar"));
            model.setKapasitas(Integer.parseInt(request.getParameter("kapasitas")));
            model.setTerisi(Integer.parseInt(request.getParameter("terisi")));
            model.setStatus(request.getParameter("status"));
            dao.save(model, page);
            response.setContentType("text/html;charset=UTF-8");
            out.print("Data Tersimpan");
        }
        else if(page.equals("update")){
            KamarModel model = new KamarModel();
            try{
                model.setIdKamar(request.getParameter("idKamar"));
                model.setNamaRuang(request.getParameter("namaKamar"));
                model.setNoKamar(request.getParameter("noKamar"));
                model.setKelas(request.getParameter("kelas"));
                model.setHargaPerhari(Double.parseDouble(request.getParameter("hargaPerhari")));
                model.setDeskripsiKamar(request.getParameter("desKamar"));
                model.setKapasitas(Integer.parseInt(request.getParameter("kapasitas")));
                model.setTerisi(Integer.parseInt(request.getParameter("terisi")));
                model.setStatus(request.getParameter("status"));
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
