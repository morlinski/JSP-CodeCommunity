package codecomm.business;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codecomm.util.PasswordUtil;
import codecomm.util.ConnectionPool;
import codecomm.util.DBUtil;
import java.sql.*;
import codecomm.data.CodeData;

public class UserLogin extends HttpServlet {
    CodeData sessionData = null;
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
        
        String url = "/index.jsp";
        String action = request.getParameter("action");
        if(action==null){
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        else if(action.equals("login")){
            //check to see if the username exists in the database, then get the salt and compare the hashed password.
            String username = request.getParameter("j_username");
            String password = request.getParameter("j_password");
            
            try {
                PasswordUtil.checkUserName(username);
                PasswordUtil.checkPasswordStrength(password);
                
                //Database check here, redirect back to registration.
                //..
                    ConnectionPool pool = ConnectionPool.getInstance();
                    Connection connection = pool.getConnection();
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                try {
                    
                    String query = "SELECT username, role, password, salt FROM dbcommUsers WHERE username = ?;";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, username);
                    rs = ps.executeQuery();
                    OUTER:if(rs.next()){
                        String salt = rs.getString("salt");
                        String hashPass = PasswordUtil.hashPassword(password+salt);
                        
                        if(hashPass.equals(rs.getString("password"))){
                            
                            //attempt 2
                            //request.getSession().setAttribute("Registered","isRegistered");
                            //request.getSession().setAttribute("CurrentRole",rs.getString("role"));
                            //System.out.println("CHECK ME:"+request.getSession().getAttribute("Registered")+" , "+request.getSession().getAttribute("CurrentRole"));
                            /*CodeData*/ sessionData = new CodeData("isRegistered",rs.getString("role"));
                            
                            /*request.getSession().setAttribute("RegisteredUser",sessionData);*/ //2nd last attempt
                            
                            //request.getSession().setAttribute("Registered",new CodeData("isRegistered",rs.getString("role")));
                           
                            break OUTER;
                        }
                    } else {
                        //no user exists yet, retry login page.                   
                        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                //..
                //test PasswordUtil.
                }
                catch(SQLException e){
                    
                }
                finally {
                    DBUtil.closeResultSet(rs);
                    DBUtil.closePreparedStatement(ps);
                    pool.freeConnection(connection);
                    
                }
                //change login to logout?
                request.getSession().setAttribute("UserName", username);
                request.getSession().setAttribute("RegisteredUser",sessionData);
                
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

            } catch (Exception e){          
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
            

//            response.setContentType("text/html;charset=UTF-8");
//            try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UserLogin</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            //out.println("<h1>Username+Password: "+username+","+password+"</h1>");
//              
//            out.println("</body>");
//            out.println("</html>");
//        }
        }
        
        else if(action.equals("register")){
            //check to see if the username exists in the database, then get the salt and compare the hashed password.
            String username = request.getParameter("j_username1");
            String password = request.getParameter("j_password1");
     
            try {
                PasswordUtil.checkUserName(username);
                PasswordUtil.checkPasswordStrength(password);
                
                //Database check here, redirect back to registration.
                //..
                    ConnectionPool pool = ConnectionPool.getInstance();
                    Connection connection = pool.getConnection();
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                try {
                    
                    String query = "SELECT username FROM dbcommUsers WHERE username = ?";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, username);
                    rs = ps.executeQuery();
                    if(rs.next()){
                        //rs.getString("username");
                        request.setAttribute("rejectionMessage", "This username is already taken.");
                        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                    } else {
                        //no user exists yet, so continue.
                        String salt = PasswordUtil.getSalt();
                        String hashPass = PasswordUtil.hashPassword(password+salt);
                        String insert_query = "INSERT INTO dbcommUsers (username, password, salt) VALUES (?,?,?);";
                        ps = connection.prepareStatement(insert_query);
                        ps.setString(1, username);
                        ps.setString(2, hashPass);
                        ps.setString(3, salt);
                        //add int affected = ps.executeUpdated(); to monitor the affected row.
                        ps.executeUpdate();
                        //now set the cookie.
                       
                        request.getSession().setAttribute("Registered","isRegistered");                        
                        request.getSession().setAttribute("CurrentRole","isUser");
                        
                        //System.out.println("CHECK ME:"+request.getSession().getAttribute("Registered")+" , "+request.getSession().getAttribute("CurrentRole"));
                        
                        /*CodeData*/ sessionData = new CodeData("isRegistered","isUser");
                        request.getSession().setAttribute("UserName", username);
                        request.getSession().setAttribute("RegisteredUser",sessionData);
                        
                        
                        //request.getSession().setAttribute("Registered",new CodeData("isRegistered","isUser"));//***
                        
                    }
                //..
                //test PasswordUtil.
                }
                catch(SQLException e){
                    //request.setAttribute("rejectionMessage", e.getMessage());
                    request.setAttribute("rejectionMessage", "This username is already taken.");
                    getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                }
                finally {
                    DBUtil.closeResultSet(rs);
                    DBUtil.closePreparedStatement(ps);
                    pool.freeConnection(connection);
                    
                }
                //request.getSession().setAttribute("Registered",new CodeData("isRegistered","isUser"));//***
                getServletContext().getRequestDispatcher("/ThankYou.jsp").forward(request, response);

            } catch (Exception e){
                request.setAttribute("rejectionMessage", e.getMessage());
                getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            }
            
            
//            response.setContentType("text/html;charset=UTF-8");
//            try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UserLogin</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            //out.println("<h1>Username+Password: "+username+","+password+"</h1>");
//              
//            out.println("</body>");
//            out.println("</html>");
//        }
        }
        
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UserLogin</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UserLogin at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

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
        doPost(request,response);
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
