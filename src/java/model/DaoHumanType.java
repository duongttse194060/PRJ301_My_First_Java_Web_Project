package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConnectDB;
import java.sql.PreparedStatement;


public class DaoHumanType implements Workable<DtoHumanType> {

    @Override
    public List<DtoHumanType> getAll() {
        List<DtoHumanType> result = new ArrayList<>();

        // --- B1: Tạo kết nối tới Database
        Connection con = new ConnectDB().getConnection();

        if (con == null) {
            return result; // nếu không kết nối được thì trả list rỗng
        }

        // --- B2: Tạo đối tượng mang lệnh [Statement]
        try {
            Statement ml = con.createStatement();
            String sqlString = "SELECT typeId, typeName FROM humanType";

            // --- B3: Nhận kết quả thi hành [ResultSet]
            ResultSet rs = ml.executeQuery(sqlString);

            // --- B4: Lặp và xử lý dữ liệu
            while (rs.next()) {
                int typeId = rs.getInt("typeId");
                String typeName = rs.getString("typeName");

                DtoHumanType x = new DtoHumanType(typeId, typeName);
                result.add(x);
            }

            rs.close();
            ml.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHumanType.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public int add(DtoHumanType x) {
        // TODO: viết code insert vào DB
        return 0;
    }

    @Override
    public int delete(int id) {
        // TODO: viết code delete
        return 0;
    }

    @Override
    public int update(DtoHumanType x) {
        int kq = 0;
        Connection conn = null;
        try {
            // ===== 2.1: Tạo kết nối
            conn = new ConnectDB().getConnection();

            // ===== 2.2: Tạo câu lệnh SQL
            String sqlString = "UPDATE HumanType SET typeName = ? WHERE typeId = ?";

            PreparedStatement ps = conn.prepareStatement(sqlString);

            // ===== 2.3: Gán giá trị cho tham số
            ps.setString(1, x.getTypeName());
            ps.setInt(2, x.getTypeId());

            // ===== 2.4: Thực thi lệnh
            kq = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DaoHumanType.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                // ===== 2.5: Đóng kết nối
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoHumanType.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kq;
    }

    @Override
    public DtoHumanType searchById(int id) {
        // TODO: viết code search
        DtoHumanType x = null;

        // --- B1: Tạo kết nối tới Database
        Connection con = new ConnectDB().getConnection();

        // --- B2: Tạo đối tượng mang lệnh [Statement]
        Statement ml;
        try {
            ml = con.createStatement();
            String sqlString = "SELECT typeId, typeName "
                    + "FROM humanType "
                    + "WHERE typeId = " + id;

            // --- B3: Nhận kết quả thi hành [ResultSet] dựa vào việc thực thi phương thức [.executeQuery()]
            ResultSet rs = ml.executeQuery(sqlString);

            // --- B4: Lặp và xử lý dữ liệu
            while (rs.next()) {
                // --- Lấy dữ liệu từ ResultSet
                int typeId = rs.getInt("typeId");
                String typeName = rs.getString("typeName");

                // --- Tạo đối tượng DTO dựa vào dữ liệu đã lấy ở trên
                x = new DtoHumanType(typeId, typeName);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHumanType.class.getName()).log(Level.SEVERE, null, ex);
        }

        return x;

    }
}
