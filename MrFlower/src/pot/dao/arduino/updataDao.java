package pot.dao.arduino;

import pot.util.DBConnection;

import java.sql.*;

/**
 * Created by lvsijian8 on 2017/4/19.
 */
public class updataDao {
    public String Updata(int pot_id, int out_temperature, int out_humidity, int in_humidity, int water, int light, int fertilizer) {
        String state = "";
        Connection con = null;
        PreparedStatement prepstmt = null;
        ResultSet rs = null;
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        String sqlUpdata = "INSERT INTO pot_" + pot_id + " (time, out_temperature, out_humidity, in_humidity, light, water, fertilizer) VALUES (?,?,?,?,?,?,?);";
        String sqlFindset = "SELECT bottle_day,bottle_time,bottle_ml,water_day,water_time,water_ml FROM pot WHERE pot_id=?";
        try {
            con = DBConnection.getDBConnection();
            prepstmt = con.prepareStatement(sqlUpdata);
            prepstmt.setTimestamp(1, now);
            prepstmt.setInt(2, out_temperature);
            prepstmt.setInt(3, out_humidity);
            prepstmt.setInt(4, in_humidity);
            prepstmt.setInt(5, light);
            prepstmt.setInt(6, water);
            prepstmt.setInt(7, fertilizer);
            prepstmt.executeUpdate();
            prepstmt = con.prepareStatement(sqlFindset);
            prepstmt.setInt(1, pot_id);
            rs = prepstmt.executeQuery();
            while (rs.next()) {

                state += "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeDB(con, prepstmt, rs);
        }
        return state;
    }
}
