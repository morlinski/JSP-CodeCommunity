package codecomm.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommunityContentController extends HttpServlet {

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
        
        //if(false){
        try {
        //String processedURL = request.getRequestURL()
        String url = request.getRequestURL().toString();
        url = url.split("/")[4];
        String firstChar = url.charAt(0)+"";
        firstChar = firstChar.toUpperCase();
        url = firstChar+url.substring(1);
        url = "/CommunityContent/"+url+"/index.jsp";
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CommunityContentController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommunityContentController at " + request.getRequestURL() + "</h1>");
            out.println("<h1>Servlet CommunityContentController goes to " + url + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
        } catch(StackOverflowError e){ 
            getServletContext().getRequestDispatcher("/index.html").forward(request, response);
        }
        }
    //}
    
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
