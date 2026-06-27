/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package alumni202557201005;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ANGGUN Z
 */
public class PanelKelas extends javax.swing.JPanel {

    /**
     * Creates new form PanelGuru
     */
    public PanelKelas() {
        initComponents();
        // Mengosongkan semua input form 
        reset();
        
        // Menampilkan data kelas ke tabel
        load_tabel_kelas();
        
        // Mengisi combo box jurusan dari database
        comboJurusan();
        
        // Mengisi combo box wali kelas dari database 
        comboWali();
        
        comboTingkatan();
    }
    
    // Method untuk mengosongkan semua input form
    void reset(){
        // Kosongkan field kode kelas
        tKodeKelas.setText(null);
        // Aktifkan kembali input kode kelas agar bisa diubah
        tKodeKelas.setEditable(true);
        // Kosongkan field nama kelas
        tNamaKelas.setText(null);
        // Kosongkan pilihan pada combo box tingkatan 
        cTingkatan.setSelectedItem(null);
        // Kosongkan pilihan pada combo box jurusan
        cJurusan.setSelectedItem(null);
        // Kosongkan pilihan pada combo box wali kelas
        cWali.setSelectedItem(null);
    }
    
    // Method untuk menampilkan semua data kelas ke dalam tabel
    void load_tabel_kelas(){
        // BUat model tabel baru untuk menyimpan data yang akan ditampilkan
        DefaultTableModel model = new DefaultTableModel();
        
        // Tambahkan nama-nama kolom ke model tabel
        model.addColumn("Kode Kelas");
        model.addColumn("Nama Kelas");
        model.addColumn("TIngkatan");
        model.addColumn("Jurusan");
        model.addColumn("Wali Kelas");
        
        // Buat query SQL untuk mengambil data kelas beserta jurusan dan wali kelas
        String sql = "SELECT k.id_kelas, k.nama_kelas, k.tingkatan, j.nama_jur, g.nama_guru "
                + "FROM kelas k "
                + "LEFT JOIN jurusan j ON k.kode_jur=j.kode_jur "
                + "LEFT JOIN guru g ON k.nip_wali_kelas=g.nip";
        try {
            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Membuat statement SQL
            Statement st = conn.createStatement();

            // Menjalankan query dan menyimpan hasilnya 
            ResultSet rs = st.executeQuery(sql);

            // Iterasi untuk setiap baris hasil query 
            while (rs.next()) {
                // Mengambil nilai dari masing-masing kolom
                String kodeKelas = rs.getString("id_kelas");
                String namaKelas = rs.getString("nama_kelas");
                String tingkatan = rs.getString("tingkatan");
                String jurusan = rs.getString("nama_jur");
                String waliKelas = rs.getString("nama_guru");

                // Menyusun data ke dalam array objek 
                Object[] baris = {kodeKelas, namaKelas, tingkatan, jurusan, waliKelas};

                // Menambahkan data ke model tabel
                model.addRow(baris);
            }
        } catch (SQLException sQLException) {
            // Menampilkan pesan error jika terjadi kegagalan
            JOptionPane.showMessageDialog(null, "Gagal mengambil data!");
        }
        
        // Menampilkan data model ke dalam tabel GUI
        tblKelas.setModel(model);
    }
    
    // Method untuk mengisi combo box jurusan dari  database
    void comboJurusan(){
        
        try {
            // Query untuk mengambil semuua data dari tabel  jurusan
            String sql = "SELECT * FROM jurusan";

            // Buka koneksi ke database
            Connection conn = koneksi.konek();

            // Membuat statement SQL
            Statement statement = conn.createStatement();

            // Menjalankan query dan menyimpan hasilnya 
            ResultSet resultSet = statement.executeQuery(sql);

            // Tambahkan setiap nama jurusan ke dalam combo Box
            while (resultSet.next()) {
                cJurusan.addItem(resultSet.getString("nama_jur"));                
            }
        } catch (SQLException e) {
            // Kosongkan (tidak ada penanganan kesalahan)
        }
        
        // Kosongkan pilihan default combo box
        cJurusan.setSelectedItem(null);
    }
    
    // Method untuk mengisi combo box wali kelas dari database
    void comboWali(){
        try {
            // Query untuk mengambil semuua data dari guru
            String sql = "SELECT * FROM guru";

            // Buka koneksi ke database
            Connection conn = koneksi.konek();

            // Membuat statement SQL
            Statement statement = conn.createStatement();

            // Menjalankan query dan menyimpan hasilnya 
            ResultSet resultSet = statement.executeQuery(sql);

            // Tambahkan setiap nama guru ke dalam combo Box
            while (resultSet.next()) {
                cWali.addItem(resultSet.getString("nama_guru"));                
            }
        } catch (SQLException e) {
            // Kosongkan (tidak ada penanganan kesalahan)
        }
        
        // Kosongkan pilihan default combo box
        cWali.setSelectedItem(null);
    }
    
    // Method untuk mengambil kode jurusan berdasarkan nama jurusan
    String KodeJurusan(String NamaJurusan){
        try {
            // Query dengan parameter untuk mencari jurusan berdasarkan nama
            String sql = "SELECT * FROM jurusan WHERE nama_jur = ?";

            // Buka koneksi ke database
            Connection conn = koneksi.konek();

            // Membuat prepared statement
            PreparedStatement  ps = conn.prepareStatement(sql);

            // Isi parameter query dengan nama jurusan
            ps.setString(1, NamaJurusan);
            
            // Menjalankan query dan menyimpan hasilnya 
            ResultSet resultSet = ps.executeQuery();

            // Tambahkan setiap nama guru ke dalam combo Box
            while (resultSet.next()) {
                return resultSet.getString("kode_jur");                
            }
        } catch (SQLException e) {
            // Jika error, kembalikan String kosong
            return "";
        }
        // Jika tidak ditemukan, kembalikan string kosong
        return "";
    }
    
    // Method untuk mengambil NIP berdasarkan nama guru
    String NIP(String NamaGuru){
        
        try {
            // Query dengan parameter untuk mencari guru berdasarkan nama
            String sql = "SELECT * FROM guru WHERE nama_guru = ?";

            // Buka koneksi ke database
            Connection conn = koneksi.konek();

            // Siapkan prepared statement 
            PreparedStatement ps = conn.prepareStatement(sql);

            // Isi parameter query dengan nmaa guru
            ps.setString(1, NamaGuru);

            // Jalankan query dan ambil hasilnya 
            ResultSet resultSet = ps.executeQuery();

            // Jika data ditemukan, kembalikan NIP guru
            while (resultSet.next()) {
                return resultSet.getString("nip");
            }
        } catch (SQLException sQLException) {
            // JIka error, kembalikan string kosong
            return "";
        }
        
        // JIka tidak ditemukan, kembalikan string kosong
        return "";
    }
    
    void comboTingkatan() {
    try {
        String sql = "SELECT DISTINCT tingkatan FROM kelas";
        Connection conn = koneksi.konek();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            cTingkatan.addItem(resultSet.getString("tingkatan"));
        }
    } catch (SQLException e) {
        
    }

    cTingkatan.setSelectedItem(null);
}
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tKodeKelas = new javax.swing.JTextField();
        tNamaKelas = new javax.swing.JTextField();
        cTingkatan = new javax.swing.JComboBox<>();
        cJurusan = new javax.swing.JComboBox<>();
        cWali = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKelas = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 20));
        setPreferredSize(new java.awt.Dimension(802, 604));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(114, 167, 239));
        jPanel1.setPreferredSize(new java.awt.Dimension(483, 30));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Data Kelas");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jPanel1.add(jLabel8, java.awt.BorderLayout.LINE_START);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201005/img/Aloomni/icons8-close-20 (1).png"))); // NOI18N
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, java.awt.BorderLayout.LINE_END);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setMinimumSize(new java.awt.Dimension(250, 289));
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 289));

        jLabel1.setText("Kode Kelas");

        jLabel2.setText("Nama Kelas");

        jLabel3.setText("Tingkatan");

        jLabel4.setText("Jurusan");

        cWali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cWaliActionPerformed(evt);
            }
        });

        jLabel5.setText("Wali Kelas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tKodeKelas)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cWali, javax.swing.GroupLayout.Alignment.LEADING, 0, 238, Short.MAX_VALUE)
                                .addComponent(cJurusan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(tNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cTingkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tKodeKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cTingkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cWali, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel4.setLayout(new java.awt.BorderLayout());

        btnHapus.setBackground(new java.awt.Color(255, 0, 51));
        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201005/img/Aloomni/icons8-trash-18.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(114, 167, 239));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201005/img/Aloomni/icons8-resetwhite-18.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnTambah.setBackground(new java.awt.Color(102, 204, 0));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201005/img/Aloomni/icons8-plus-18.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUbah.setBackground(new java.awt.Color(255, 153, 0));
        btnUbah.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201005/img/Aloomni/icons8-pen-squared-18.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnReset))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 20));
        jPanel6.setLayout(new java.awt.CardLayout());

        tblKelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKelasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKelas);

        jPanel6.add(jScrollPane2, "card2");

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cWaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cWaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cWaliActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
         // Ambil nilai kode kelas dari input field tKodeKelas
        String KodeKelas = tKodeKelas.getText();
        try {
            // Buat query SQL untuk menhapus data dari tabel 'kelas' berdasarkan id_kelas
            String sql = "DELETE FROM kelas id_kelas=?";

            // Buka koneksi ke database
            Connection conn = koneksi.konek();

            // Siapkan perintah SQL yang mendukung parameter (?) 
            PreparedStatement statement = conn.prepareStatement(sql);

            // Isi nilai parameter pertama (id_kelas) dengan KOdeKelas yang diinput
            statement.setString(1, KodeKelas);
            
            // Jalankan perintah untuk menghapus data dari database
            statement.execute();

            // Tampilkan pesan bahwa data berhasil dihapus
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (SQLException sQLException) {
            // Tampilkan pesan jika terjadi kesalahan saat menghapus data
            JOptionPane.showMessageDialog(null, "Data gagal dihapus!");
        }
        
        // Tampilkan ulang data ke tabel agar data terbaru muncul
        load_tabel_kelas();
        
        // Kosongkan semua inputan pada form
        reset();   
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
         // Ambil nilai kode kelas dari input field
        String KodeKelas = tKodeKelas.getText();
        
        // Ambil nilai nama kelas dari input field
        String NamaKelas = tNamaKelas.getText();
        
        // Ambil pilihan tingkatan dari combo box dan ubah menjadi string
        String Tingkatan = cTingkatan.getSelectedItem().toString();
        
        // Ambil nama jurusan dari combo box, lalu konversi ke kode jurusan lewat method KOdeJurusan()
        String Jurusan = KodeJurusan(cJurusan.getSelectedItem().toString());
        
        // Ambil nama wali kelas dari combo box, lalu konversi ke NIP guru lewat method NIP()
        String WaliKelas = NIP(cWali.getSelectedItem().toString());
        
        try {
            // Buat query SQL untuk mennyimpan data ke tabel kelas 
            String sql = "INSERT INTO kelas (id_kelas, nama_kelas, tingkatan, kode_jur, nip_wali_kelas) VALUES(?,?,?,?)";

            // Buka koneksi ke database
            Connection conn = koneksi.konek();

            // Siapkan perintah SQL dengan parameter 
            PreparedStatement statement = conn.prepareStatement(sql);

            // Masukkan data ke parameter query (urutan sesuai dengan tanda tanya di query)
            statement.setString(1, KodeKelas);
            statement.setString(2, NamaKelas);
            statement.setString(3, Tingkatan);
            statement.setString(4, Jurusan);
            statement.setString(5, WaliKelas);
            
            // Jalankan query untuk menyimpan data ke database
            statement.execute();

            // Tampilkan pesan bahwa data berhasil disimpan
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        } catch (SQLException sQLException) {
            // Jika terjadi error saat Menyimpan, tampilkan pesan gagal
            JOptionPane.showMessageDialog(null, "Data gagal disimpan!");
        }
        
        // Tampilkan kembali data kelas terbaru di tabel
        load_tabel_kelas();
        
        // Kosongkan semua input di form
        reset();  
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        // Ambil nilai kode kelas dari input field
        String KodeKelas = tKodeKelas.getText();
        
        // Ambil nilai nama kelas dari input field
        String NamaKelas = tNamaKelas.getText();
        
        // Ambil pilihan tingkatan dari combo box dan ubah menjadi string
        String Tingkatan = cTingkatan.getSelectedItem().toString();
        
        // Ambil nama jurusan dari combo box, lalu konversi ke kode jurusan lewat method KOdeJurusan()
        String Jurusan = KodeJurusan(cJurusan.getSelectedItem().toString());
        
        // Ambil nama wali kelas dari combo box, lalu konversi ke NIP guru lewat method NIP()
        String WaliKelas = NIP(cWali.getSelectedItem().toString());
        
        try {
            // Buat query SQL untuk mengubah data kelsa berdasarkan id_kelas
            String sql = "UPDATE kelas SET nama_kelas=?, tingkatan=?, kode_jur=?, nip_wali_kelas=?"
                    + "WHERE id_kelas= ?";

            // Buka koneksi ke database
            Connection conn = koneksi.konek();

            // Siapkan perintah SQL yang akan diisi nilai-nilainya 
            PreparedStatement statement = conn.prepareStatement(sql);

            // Isi nilai-nilai parameter (tanda tanya di query) sesuai urutannya
            statement.setString(1, NamaKelas);
            statement.setString(2, Tingkatan);
            statement.setString(3, Jurusan);
            statement.setString(4, WaliKelas);
            statement.setString(5, KodeKelas);

            // Jalankan query untuk melakukan update data
            statement.execute();

            // Tampilkan pesan bahwa data berhasil diubah
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        } catch (SQLException sQLException) {
            // Jika terjadi kesalahan saat update, tampilkan pesan gagal
            JOptionPane.showMessageDialog(null, "Data gagal diubah!");
        }
        
        // Setelah data diubah, tampilkan ulang data ke dalam tabel
        load_tabel_kelas();
        
        // KOsongkan inputan agar siap diisi data baru
        reset();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tblKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKelasMouseClicked
        // TODO add your handling code here:
        // Mengambil indeks baris yang diklik oleh pengguna di tabel kelas
        int barisYangDipilih = tblKelas.rowAtPoint(evt.getPoint());
        
        // Mengambil nilai kolom ke 0 (kode kelas) pada baris yang dipilih
        String KodeKelas = tblKelas.getValueAt(barisYangDipilih, 0).toString();
        
        // Mengambil nilai dari kolom ke-1 (nama kelas) pada baris yang dipilih 
        String NamaKelas = tblKelas.getValueAt(barisYangDipilih, 1).toString();
        
        // Mengambil nilai dari kolom ke-2 (tingkatan kelas) pada baris yang dipilih
        String Tingkatan = tblKelas.getValueAt(barisYangDipilih, 2).toString();
        
        // Mengambil nilai dari kolom ke-3 (nama jurusan) pada baris yang dipilih
        String Jurusan = tblKelas.getValueAt(barisYangDipilih, 3).toString();
        
        // Buuat variabel untuk menyimpan nama wali kelas
        String WaliKelas;
        
        // Cek apakah kolom ke-4 (wali kelas) berisi data atau tidak
        if (tblKelas.getValueAt(barisYangDipilih, 4) != null) {
            //Jika kosong (null), set nilai WaliKelas juga null
            WaliKelas = tblKelas.getValueAt(barisYangDipilih, 4).toString();
         }else {
            // Jika kosong (null), set nilai WaliKelas juga null
            WaliKelas = null;
        }
        
        // Tampilkan kode kelas ke dalam input  text (tidak bisa diedit)
        tKodeKelas.setText(KodeKelas);
        tKodeKelas.setEditable(false);
        
        // Tampilkan nama kelas ke text field 
        tNamaKelas.setText(NamaKelas);
        
        // Tampilkan tingkatan ke combo box tingkatan
        cTingkatan.setSelectedItem(Tingkatan);
        
        // Tampilkan jurusan ke combo box jurusan
        cJurusan.setSelectedItem(Jurusan);
        
        // Tampilkan nama wali kelas ke combo box wali
        cWali.setSelectedItem(WaliKelas);
    }//GEN-LAST:event_tblKelasMouseClicked

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cJurusan;
    private javax.swing.JComboBox<String> cTingkatan;
    private javax.swing.JComboBox<String> cWali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField tKodeKelas;
    private javax.swing.JTextField tNamaKelas;
    private javax.swing.JTable tblKelas;
    // End of variables declaration//GEN-END:variables
}
