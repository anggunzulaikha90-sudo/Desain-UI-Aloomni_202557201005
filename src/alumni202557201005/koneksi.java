/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumni202557201005;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ANGGUN Z
 */
public class koneksi {
    // Mendeklarasikan variabel koneksi sebagai static agar bisa diakses dari mana saja di dalama class 
    private static Connection mysqlconfig;
    
    //Method static untuk membuka koneksi ke database MySQL
    public static Connection konek(){
        try {
            // URL koneksi ke databse: jdbcmysql://[host]:[port]/[nama_database]
            String url = "jdbc:mysql://localhost:3306/alumni_202557201005";

            // Username database
            String user = "root";

            // Password database
            String pass = "";

            // Membuka koneksi ke database dan menyimpannya di variabel mysqlconfig
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (SQLException sQLException) {
            // Menampilkan pesan error jika koneksi gagal
            System.out.println(sQLException.getMessage());
        } 
        // Mengembalikan objek koneksi (bisa null jika gagal)
        return mysqlconfig;
    }
}
