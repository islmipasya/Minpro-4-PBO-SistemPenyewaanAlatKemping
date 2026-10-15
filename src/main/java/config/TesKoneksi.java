package config;

import java.sql.Connection;
import java.sql.SQLException;

public class TesKoneksi {

    public static void main(String[] args) {

        System.out.println("Test Koneksi");

        try {
            Connection conn = Koneksi.getConnection();

            if (conn != null) {
                System.out.println("Koneksi Berhasil");
            }

        } catch (SQLException e) {
            System.out.println("Koneksi Gagal");
        }
    }
}