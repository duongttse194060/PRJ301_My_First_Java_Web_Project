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
        Connection con = new ConnectDB().getConnection();

        if (con == null) {
            return result;
        }

        try {
            Statement ml = con.createStatement();
            String sqlString = "SELECT typeId, typeName FROM HumanType";

            ResultSet rs = ml.executeQuery(sqlString);
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
        int kq = 0;
        Connection conn = null;

        try {
            conn = new ConnectDB().getConnection();
            String sql = "INSERT INTO HumanType(typeName) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, x.getTypeName());
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHumanType.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
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
    public int delete(int id) {
        int n = 0;
        Connection conn = null;

        try {
            conn = new ConnectDB().getConnection(); // Tạo connection
            String sql = "DELETE FROM HumanType WHERE typeId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHumanType.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DaoHumanType.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return n;
    }

    @Override
    public int update(DtoHumanType x) {
        int kq = 0;
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnection();
            String sqlString = "UPDATE HumanType SET typeName = ? WHERE typeId = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, x.getTypeName());
            ps.setInt(2, x.getTypeId());
            kq = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHumanType.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
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
        DtoHumanType x = null;
        Connection con = new ConnectDB().getConnection();

        try {
            Statement ml = con.createStatement();
            String sqlString = "SELECT typeId, typeName FROM HumanType WHERE typeId = " + id;
            ResultSet rs = ml.executeQuery(sqlString);

            while (rs.next()) {
                int typeId = rs.getInt("typeId");
                String typeName = rs.getString("typeName");
                x = new DtoHumanType(typeId, typeName);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoHumanType.class.getName()).log(Level.SEVERE, null, ex);
        }

        return x;
    }
}
