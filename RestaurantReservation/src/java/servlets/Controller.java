package servlets;

import datastore.DAOSQLite;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Reservation;

/**
 * All of this application's web pages send their requests to this controller
 * which then updates the model / database as needed and transfers control with
 * data to one the the HTML/JSP view-oriented programs for display.
 *
 * @author John Phillips
 */
public class Controller extends HttpServlet {

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

        // get real path to the sqlite db
        ServletContext sc = this.getServletContext();
        String dbPath = sc.getRealPath("/WEB-INF/reservation.db");

        // set default url
        String url = "/home.html";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }

        // perform action and set url
        if (action.equalsIgnoreCase("home")) {
            System.out.println("controller:home");
            url = "/home.html";

        } else if (action.equalsIgnoreCase("createRecord")) {
            System.out.println("controller:createRecord");
            int bloodSugar = 0;

            // get parameters passed in from the request
            String lastName = request.getParameter("lastname");
            String firstName = request.getParameter("firstname");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String notes = request.getParameter("notes");

            // store data in an Reservation object
            Reservation reservation = new Reservation(0, lastName, firstName, date, time, notes);
            System.out.println("Controller:createRecord:res=" + reservation);

            // validate the parameters
            if (lastName == null || date == null || time == null
                    || lastName.isEmpty() || date.isEmpty() || time.isEmpty()) {
                url = "/createRecord.jsp";
            } else {
                // insert this data record into the database
                DAOSQLite.createRecord(reservation, dbPath);
                url = "/home.html";
            }

        } else if (action.equalsIgnoreCase("report")) {
            System.out.println("controller:report");
            String email = request.getParameter("email");
            if (email == null || email.isEmpty()) {
                email = "%";
            }
            String startdate = request.getParameter("startdate");
            String enddate = request.getParameter("enddate");
            String lowhigh = request.getParameter("lowhigh");
            List<Reservation> mydata = DAOSQLite.retrieveAllRecords(dbPath);
            request.setAttribute("email", email);
            request.setAttribute("startdate", startdate);
            request.setAttribute("enddate", enddate);
            request.setAttribute("lowhigh", lowhigh);
            request.setAttribute("mydata", mydata);
            url = "/showRecords.jsp";

        } else if (action.equalsIgnoreCase("deleteRecord")) {
            System.out.println("controller:deleteRecord");
            String idString = request.getParameter("id");
            if (idString == null || idString.isEmpty()) {
                url = "/deleteRecord.jsp";
            } else {
                DAOSQLite.deleteRecord(Integer.parseInt(idString), dbPath);
                url = "/home.html";
            }

        } else if (action.equalsIgnoreCase("makeDB")) {
            System.out.println("controller:makeDB");
            DAOSQLite.dropTable(dbPath);
            DAOSQLite.createTable(dbPath);
            DAOSQLite.populateTable(dbPath);
            url = "/home.html";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);
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
        return "Controller for Employee App";
    }// </editor-fold>

}
