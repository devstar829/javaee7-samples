package org.javaee7.jaxrs.invocation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;

/**
 * @author Arun Gupta
 */
@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>JAX-RS 2 Client Invocation API</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>JAX-RS 2 Client Invocation API at " + request.getContextPath() + "</h1>");
        out.println("Initializing client...<br>");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()
                + "/webresources/resource");

        // GET
        out.print("Building a GET request ...<br>");
        Invocation i1 = target.request().buildGet();
        out.print("GET request ready ...<br>");
        
        // POST
        out.print("Building a POST request...<br>");
        MultivaluedHashMap<String, String> map = new MultivaluedHashMap<>();
        map.add("name", "Name");
        map.add("age", "17");
        Invocation i2 = target.request().buildPost(Entity.form(map));
        out.print("POSTed request ready...<br>");
        
        Collection<Invocation> is = Arrays.asList(i1, i2);
        
        for (Invocation i : is) {
            i.invoke();
            System.out.println("Request invoked");
        }

        out.println("... done.<br>");
        
        out.println("</body>");
        out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
