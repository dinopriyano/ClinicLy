/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.PasienDao;
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
import model.PasienModel;

/**
 *
 * @author dinop
 */
@WebServlet(name = "Pasien", urlPatterns = {"/Pasien"})
public class PasienController extends HttpServlet {

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
        PasienDao dao = new PasienDao();
        Date dateNow = new Date(Calendar.getInstance().getTime().getTime());
        Gson g=new Gson();
        if(page==null){
            List<PasienModel> list=dao.getAllPasien();
            String result=g.toJson(list);
            response.setContentType("text/html;charset=UTF-8");
            out.println(result);
        }
        else if(page.equals("insert")){
            PasienModel model = new PasienModel();
            model.setId_pasien(dao.getNewIdPasien());
            model.setUser_id(dao.getNewIdUser());
            model.setCreated_user_id(request.getParameter("createdUserId"));
            model.setNama_pasien(request.getParameter("nama"));
            model.setTgl_lahir(Date.valueOf(request.getParameter("tglLahir")));
            model.setJenis_kelamin(request.getParameter("jenisKelamin"));
            model.setNo_ktp(request.getParameter("noKtp"));
            model.setAlamat(request.getParameter("alamat"));
            model.setNo_hp(request.getParameter("noHp"));
            model.setGol_darah(request.getParameter("golDarah"));
            model.setCreated_date(dateNow);
            model.setEmail(request.getParameter("email"));
            model.setPassword(request.getParameter("password"));
            model.setId_role("R3");
            model.setStatus(true);
            dao.save(model, page);
            response.setContentType("text/html;charset=UTF-8");
            out.print("Data Tersimpan");
        }
        else if(page.equals("update")){
            PasienModel model = new PasienModel();
            try{
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
