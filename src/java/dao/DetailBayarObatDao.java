/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.CliniclyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author dinop
 */
public class DetailBayarObatDao {
    private final Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public DetailBayarObatDao() {
        this.conn = CliniclyConnection.connection();
    }
}
