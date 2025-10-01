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
    public int update(int id, DtoHumanType newItem) {
        // TODO: viết code update
        return 0;
    }

    @Override
    public void searchById(int id) {
        // TODO: viết code search
        
    }
}

