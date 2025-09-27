/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConnectDB;

/**
 *
 * @author ADMIN
 */
public class NewTypeControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // B1: Nhận dữ liệu từ Client gửi về
            String idParam = request.getParameter("id");
            String typeName = request.getParameter("typeName");

            // Nếu người dùng chỉ gõ /new_type trực tiếp -> hiện form nhập
            if (idParam == null && typeName == null) {
                response.sendRedirect("new_type.html"); // trang chứa form
                return;
            }

            // Nếu có nhưng bị rỗng -> báo lỗi
            if (idParam.isEmpty() || typeName.isEmpty()) {
                response.sendRedirect("bao_loi.html");
                return;
            }

            int typeId = Integer.parseInt(idParam);

            // B2: Kết nối database và xử lý theo nhiệm vụ
            Connection conn = new ConnectDB().getConnection();

            String sqlString = "INSERT INTO HumanType (typeId, typeName) VALUES (?, ?);";
            PreparedStatement ps = conn.prepareStatement(sqlString);

            ps.setInt(1, typeId);
            ps.setString(2, typeName);

            int n = ps.executeUpdate();

            String URL = (n > 0 ? "bao_thanh_cong.html" : "bao_loi.html");

            conn.close();

            // B3: Điều hướng tới trang thông báo thành công/thất bại
            response.sendRedirect(URL);

        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NumberFormatException e) {
            // Nếu người dùng nhập chữ vào ô ID => báo lỗi
            response.sendRedirect("bao_loi.html");
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
        return "NewTypeControl Servlet";
    }

}
