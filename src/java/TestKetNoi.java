

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import util.ConnectDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class TestKetNoi extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            // Thêm Bootstrap CSS + JS
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>");
            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js'></script>");
            out.println("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>");
            out.println("<title>Servlet TestKetNoi</title>");
            out.println("</head>");
            out.println("<body class='container'>");
            out.println("<h1 class='text-center text-primary'>Kiểm tra kết nối</h1>");

            // B1: Tạo kết nối với Database
            Connection con = new ConnectDB().getConnection();

            // B2: Tạo đối tượng Statement
            Statement ml;
            String data = "<table class='table table-bordered table-striped'>";
            try {
                ml = con.createStatement();
                String sqlString = "SELECT humanId, humanName, dob, gender, typeId, userHuman, passHuman FROM human;";

                // B3: Nhận kết quả ResultSet
                ResultSet rs = ml.executeQuery(sqlString);

                // B4: Header
                data += "<thead><tr>"
                        + "<th>ID</th>"
                        + "<th>Full Name</th>"
                        + "<th>Day of Birth</th>"
                        + "<th>Gender</th>"
                        + "<th>TypeID</th>"
                        + "<th>User</th>"
                        + "</tr></thead><tbody>";

                // Lặp dữ liệu
                while (rs.next()) {
                    data += "<tr>";
                    int humanId = rs.getInt("humanId");
                    String humanName = rs.getString("humanName");
                    Date dob = rs.getDate("dob");
                    boolean gender = rs.getBoolean("gender");
                    int typeId = rs.getInt("typeId");
                    String user = rs.getString("userHuman");
                    // String pass = rs.getString("passHuman"); // Không hiển thị password ra ngoài

                    data += "<td>" + humanId + "</td>";
                    data += "<td>" + humanName + "</td>";
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    data += "<td>" + sdf.format(dob) + "</td>";
                    // Nếu Male thì hiện ảnh Male.jpg, Female thì Female.jpg
                    String genderImg = gender ? "Male.jpg" : "Female.jpg";
                    data += "<td><img src='images/" + genderImg + "' alt='gender' width='30'></td>";
                    data += "<td>" + typeId + "</td>";
                    data += "<td>" + user + "</td>";
                    data += "</tr>";
                }
                data += "</tbody>";
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestKetNoi.class.getName()).log(Level.SEVERE, null, ex);
                data += "<tr><td colspan='6' class='text-danger'>Lỗi khi truy vấn dữ liệu!</td></tr>";
            }

            data += "</table>";
            out.print(data);

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
