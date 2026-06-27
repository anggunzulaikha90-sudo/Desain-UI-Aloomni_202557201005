/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package alumni202557201005;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ANGGUN Z
 */
public class PanelSiswa extends javax.swing.JPanel {

    /**
     * Creates new form PanelJurusan
     */
    public PanelSiswa() {
        initComponents();
        // Memanggil method reset untuk menggosongkan semua  input pada form
        reset();
        
        // MEmanggil method untuk menampilkan data siswa ke dalam tabel
        load_tabel_siswa();
        
        // Memanggil method untuk mengisi combo box kelas dari database
        comboKelas();
    }
    
    // Method untuk mengosongkan semua  input pada form siswa
    void reset(){
        // Mengosongkan field NIS
        tNIS.setText(null);
        
        // Mengosongkan field Nama Siswa
        tNamaSiswa.setText(null);
        
        // Mengosongkan pilihan pada combo box Jenis Kelamin
        cJenisKelamin.setSelectedItem(null);
        
        // Mengosongkan field Tempat Lahir
        tTempatLahir.setText(null);
        
        // Mengosongkan pilihan pada komponen kalender Tanggal Lahir
        tTanggal.setCalendar(null);
        
        // Mengosongkan pilihan pada combo box Kelas
        cKelas.setSelectedItem(null);
        
        // Mengosongkan field Alamat
        tAlamat.setText(null);
        
        // Menghapus icon pada label foto
        tFoto.setText(null);
        
        // Megatur teks label foto menjadi "Foto"
        tFoto.setText("Foto");
        
        // Mengosongkan path file foto yang disimpan
        tFotoPath.setText(null);
    }
    
    // Method untuk mengisi combo box kelas dari tabel 'kelas' di database
    void comboKelas(){
        
        try {
            // Query SQL untuk mengambil semua data kelas
            String sql = "SELECT * FROM kelas";

            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Siapkan prepared statement untuk menjalankan query
            Statement statement = conn.createStatement();

            // Menjalankan query dan menyimpan hasilnya
            ResultSet resultSet = statement.executeQuery(sql);

            // Mengambil data satu per satu dan menambahkannya ke combo box
            while (resultSet.next()) {
               cKelas.addItem(resultSet.getString("id_kelas"));
            }
        } catch (SQLException e) {
            // JIka error, tampilkan pesan error
            
        }
        
        // Mengosongkan pilihan combo box setelah diisi
        cKelas.setSelectedItem(null);
    }
    
    // Method  untuk menampilkan data seiswa ke dalam tabel
    void load_tabel_siswa(){
        // MEmbuat model tabel baru
        DefaultTableModel model = new DefaultTableModel();
        
        // Menambahkan kolom kedalam model tabel
        model.addColumn("NIS");
        model.addColumn("Nama Siswa");
        model.addColumn("L/P");
        model.addColumn("Tempat Lahir");
        model.addColumn("Tgl Lahir");
        model.addColumn("Kelas");
        model.addColumn("HP");
        
        // Query SQL untuk mengambil semua data  siswa
        String sql = "SELECT * FROM siswa";
        
        try {
            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Membuat statement SQL
            Statement st = conn.createStatement();

            // Menjalankan query dan menyimpan hasilnya
            ResultSet rs = st.executeQuery(sql);

            // Melakukan iterasi untuk setiap baris hasil query
            while (rs.next()) {
               // Mengambil data dari setiap kolom
               String nis = rs.getString("nis");
               String namaSiswa = rs.getString("nama_siswa");
               String jenisKelamin = rs.getString("gender");
               String tempatLahir = rs.getString("tempat_lahir");
               String tglLahir = rs.getString("tgl_lahir");
               String kelas = rs.getString("id_kelas");
               String hp = rs.getString("no_hp");
               
               // Meyimpan data dalam array
               Object[] baris = {nis, namaSiswa, jenisKelamin, tempatLahir, tglLahir, kelas,hp};
               
               // Menambahkan data sebagai baris baru di model tabel
               model.addRow(baris);
            }
        } catch (SQLException sQLException) {
            // Menampilkan pesan error jika terjadi kegagalan saat mengambil data
            JOptionPane.showMessageDialog(null, "Gagal mengambil data!");
        }
        
        // Mengosongkan pilihan combo box setelah diisi
        tblSiswa.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tFoto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tNIS = new javax.swing.JTextField();
        tNamaSiswa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cJenisKelamin = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cKelas = new javax.swing.JComboBox<>();
        tHP = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tAlamat = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        tTempatLahir = new javax.swing.JTextField();
        tTanggal = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        tFotoPath = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSiswa = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 20));
        setMinimumSize(new java.awt.Dimension(802, 604));
        setPreferredSize(new java.awt.Dimension(802, 604));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(802, 330));
        jPanel1.setPreferredSize(new java.awt.Dimension(802, 330));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(114, 167, 239));
        jPanel3.setPreferredSize(new java.awt.Dimension(802, 30));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Siswa");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        jPanel3.add(jLabel1, java.awt.BorderLayout.LINE_START);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/alumni202557201005/img/Aloomni/icons8-close-20 (1).png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel2, java.awt.BorderLayout.LINE_END);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setMinimumSize(new java.awt.Dimension(100, 30));
        jPanel4.setPreferredSize(new java.awt.Dimension(802, 30));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnTambah)
                .addGap(18, 18, 18)
                .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHapus)
                .addGap(18, 18, 18)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(311, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnReset))
                .addContainerGap())
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel2.setMinimumSize(new java.awt.Dimension(802, 289));
        jPanel2.setPreferredSize(new java.awt.Dimension(802, 289));

        tFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tFoto.setText("Foto");
        tFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tFotoMouseClicked(evt);
            }
        });

        jLabel4.setText("NIS");

        jLabel5.setText("Nama");

        jLabel6.setText("Jenis Kelamin");

        jLabel7.setText("Tanggal Lahir");

        jLabel8.setText("Alamat");

        jLabel9.setText("HP");

        jLabel10.setText("Kelas");

        tAlamat.setColumns(20);
        tAlamat.setRows(5);
        jScrollPane2.setViewportView(tAlamat);

        jLabel11.setText("Tempat Lahir");

        tTanggal.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(tFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(131, 131, 131))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(cJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(tTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tNIS, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(tHP)
                    .addComponent(cKelas, 0, 200, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel9))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tNIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(cJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 30, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 20));
        jPanel5.setMinimumSize(new java.awt.Dimension(802, 250));
        jPanel5.setPreferredSize(new java.awt.Dimension(802, 250));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setPreferredSize(new java.awt.Dimension(722, 20));

        tFotoPath.setText("jLabel3");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tFotoPath)
                .addContainerGap(671, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(tFotoPath))
        );

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel8.setLayout(new java.awt.CardLayout());

        tblSiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSiswa);

        jPanel8.add(jScrollPane1, "card2");

        jPanel5.add(jPanel8, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tblSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaMouseClicked
        // TODO add your handling code here:
        // Mengambil indeks baris yang diklik pada tabel siswa
int baris = tblSiswa.rowAtPoint(evt.getPoint());
 
// Mengambil nilai dari kolom pertama (NIS) pada baris yang diklik dan mengubah ke String
String nis = tblSiswa.getValueAt(baris, 0).toString();
 
// Mengambil nilai dari kolom kedua (Nama Siswa)
String namaSiswa = tblSiswa.getValueAt(baris, 1).toString();
 
// Mengambil objek dari kolom ketiga (Jenis Kelamin)
Object jkObj = tblSiswa.getValueAt(baris, 2);
 
// Mengambil objek dari kolom keempat (Tempat Lahir)
Object tempatObj = tblSiswa.getValueAt(baris, 3);
 
// Mengambil objek dari kolom kelima (Tanggal Lahir)
Object tglObj = tblSiswa.getValueAt(baris, 4);
 
// Mengambil objek dari kolom keenam (Kelas)
Object kelasObj = tblSiswa.getValueAt(baris, 5);
 
// Mengambil objek dari kolom ketujuh (Nomor HP)
Object hpObj = tblSiswa.getValueAt(baris, 6);
 
// Menampilkan nilai NIS pada field input dan membuatnya tidak bisa diubah
tNIS.setText(nis);
tNIS.setEditable(false);
 
// Menampilkan nama siswa ke field input
tNamaSiswa.setText(namaSiswa);
 
// Mengonversi objek menjadi string, jika null maka hasilnya null atau string kosong
String jenisKelamin = (jkObj != null) ? jkObj.toString() : null;
String tempatLahir = (tempatObj != null) ? tempatObj.toString() : "";
String tglLahir = (tglObj != null) ? tglObj.toString() : null;
String idKelas = (kelasObj != null) ? kelasObj.toString() : null;
String noHP = (hpObj != null) ? hpObj.toString() : "";
 
// Menampilkan tempat lahir, no HP, dan memilih kelas sesuai data
tTempatLahir.setText(tempatLahir);
tHP.setText(noHP);
cKelas.setSelectedItem(idKelas);
 
// Jika tanggal lahir tidak null dan tidak kosong, ubah ke format Date dan tampilkan di komponen kalender
if (tglLahir != null && !tglLahir.isEmpty()) {
    try {
        tTanggal.setDate(java.sql.Date.valueOf(tglLahir));
    } catch (IllegalArgumentException e) {
        // Jika gagal parsing tanggal, kosongkan field tanggal
        tTanggal.setDate(null);
    }
} else {
    tTanggal.setDate(null);
}
 
// Konversi kode jenis kelamin ke bentuk tampilan yang dipahami pengguna
switch (jenisKelamin) {
    case "L":
        cJenisKelamin.setSelectedItem("Laki - laki");
        break;
    case "P":
        cJenisKelamin.setSelectedItem("Perempuan");
        break;
    default:
        cJenisKelamin.setSelectedItem(null);
        break;
}
 
try {
    // Query untuk mengambil data alamat dan foto berdasarkan NIS
    String sql = "SELECT alamat, foto FROM siswa WHERE nis = ?";
 
    // Membuka koneksi ke database
    Connection conn = koneksi.konek();
 
    // Menyiapkan statement SQL dengan parameter
    PreparedStatement ps = conn.prepareStatement(sql);
 
    // Mengisi parameter dengan NIS
    ps.setString(1, nis);
 
    // Menjalankan query dan menyimpan hasilnya
    ResultSet rs = ps.executeQuery();
 
    // Jika data ditemukan
    if (rs.next()) {
        // Mengambil alamat dan foto dari hasil query
        String alamat = rs.getString("alamat");
        String foto = rs.getString("foto");
 
        // Menampilkan alamat ke field input
        tAlamat.setText(alamat);
 
        // Jika path foto tidak kosong, tampilkan gambar ke label foto
        if (foto != null && !foto.isEmpty()) {
            ImageIcon icon = new ImageIcon(foto);
            Image image = icon.getImage().getScaledInstance(tFoto.getWidth(), tFoto.getHeight(), Image.SCALE_SMOOTH);
            tFotoPath.setText(foto);
            tFoto.setText(null);
            tFoto.setIcon(new ImageIcon(image));
        } else {
            // Jika tidak ada foto, set teks "Foto" dan hapus icon
            tFoto.setText("Foto");
            tFoto.setIcon(null);
        }
    }
} catch (SQLException e) {
    // Menampilkan error ke konsol jika terjadi kesalahan SQL
    System.err.println(e.getMessage());
}
    }//GEN-LAST:event_tblSiswaMouseClicked

    private void tFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tFotoMouseClicked
        // TODO add your handling code here:
        
        // Blok try digunakan untuk menangani kemungkinan error saat memilihh dan memuat file gambar 
        
        try {
            // Membuat objek JFileChooser untuk membuka dialog pemilihan file
            JFileChooser chooser = new JFileChooser();

            // Menampilkan dialog dan menyimpan hasil aksi pengguna (OK atau Cancel)
            int result = chooser.showOpenDialog(null);

            // Mengecek apakah pengguna menekan tombol "Open" (OK)
            if (result == JFileChooser.APPROVE_OPTION) {
                // Mengambil fille yang dipilih oleh pengguna
                File file = chooser.getSelectedFile();
                
                if (file != null) {
                    // Membuat objek ImageIcon dari file gambar yang dipilih
                    ImageIcon icon = new ImageIcon(file.toString());

                    // Mengubah ukuran gambar agar sesuai dengan label foto
                    Image image = icon.getImage().getScaledInstance(
                            tFoto.getWidth(),
                            tFoto.getHeight(),
                            Image.SCALE_SMOOTH
                    );

                    // Membuat ImageIcon baru dari gambar yang telah diubah ukurannya
                    ImageIcon ic = new ImageIcon(image);

                    // Menghapus teks pada tabel foto
                    tFoto.setText(null);

                    // Menampilkan gambar (icon) ke dalam label tFoto
                    tFoto.setIcon(ic);

                    // Mengambil path absolut dari file gambar dan menyimpannya ke field tFotoPath
                    String filename = file.getAbsolutePath();
                    tFotoPath.setText(filename);
                }
            } else {
                // Jika pengguna menekan tombol cancel, tampilkan pesan ke konsol
                System.out.println("Pemilihan file dibatalkan oleh pengguna.");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error Upload: " + e.getMessage());
        }
    }//GEN-LAST:event_tFotoMouseClicked

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        // Mengambil teks dari field NIS
String nis = tNIS.getText();
 
// Mengambil teks dari field Nama Siswa
String namaSiswa = tNamaSiswa.getText();
 
// Mengambil item yang dipilih dari combo box jenis kelamin dan mengubahnya menjadi string
String jenisKelamin = cJenisKelamin.getSelectedItem().toString();
 
// Variabel untuk menyimpan hasil konversi jenis kelamin (L/P)
String jK = null;
 
// Mengambil teks dari field Tempat Lahir
String tempatLahir = tTempatLahir.getText();
 
// Mengambil tanggal dari komponen kalender
Date tglLahirDate = tTanggal.getDate();
 
// Mengubah tanggal lahir menjadi format "yyyy-MM-dd"
String tglLahir = new SimpleDateFormat("yyyy-MM-dd").format(tglLahirDate);
 
// Mengambil teks dari field nomor HP
String hp = tHP.getText();
 
// Mengambil item yang dipilih dari combo box kelas
String kelas = cKelas.getSelectedItem().toString();
 
// Mengambil teks dari field alamat
String alamat = tAlamat.getText();
 
// Mengambil path file dari label path foto
String filePath = tFotoPath.getText();
 
// Konversi jenis kelamin dari teks menjadi kode (L atau P)
switch (jenisKelamin) {
    case "Laki - laki":
        jK = "L";
        break;
    case "Perempuan":
        jK = "P";
        break;
    default:
        jK = null;
        break;
}
 
// Variabel untuk menyimpan path file foto tujuan
String foto = null;
 
// Mengecek apakah ada path file foto yang dipilih
if (filePath.length() != 0) {
    try {
        // Menyimpan path sumber file
        String sourcePath = filePath;
        File sourceFile = new File(sourcePath);
 
        // Menentukan folder tujuan untuk menyimpan foto
        String destinationFolderPath = "src/foto/";
        File destinationFolder = new File(destinationFolderPath);
 
        // Jika folder tujuan belum ada, buat folder tersebut
        if (!destinationFolder.exists()) {
            destinationFolder.mkdir();
        }
 
        // Mengambil ekstensi file (contoh: jpg, png, dll)
        String extension = sourcePath.substring(sourcePath.lastIndexOf('.') + 1);
 
        // Membuat nama file baru yang unik berdasarkan timestamp
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destinationFileName = "foto-" + timestamp + "." + extension;
 
        // Membuat file tujuan dengan path dan nama file baru
        File destinationFile = new File(destinationFolderPath + destinationFileName);
 
        // Menyalin file dari sumber ke tujuan
        Files.copy(sourceFile.toPath(), destinationFile.toPath());
 
        // Menyimpan path foto yang telah dipindahkan
        foto = destinationFile.getPath();
 
    } catch (Exception e) {
        // Menampilkan pesan error jika gagal mengupload file
        JOptionPane.showMessageDialog(null, "Gagal upload file: " + e.getMessage());
    }
} else {
    // Jika tidak ada file yang dipilih, set null
    foto = null;
}
 
try {
    // Query SQL untuk menyimpan data siswa ke database
    String sql = "INSERT INTO siswa(nis,nama_siswa,gender,tempat_lahir,tgl_lahir,alamat,no_hp,id_kelas,foto)"
            + " VALUES(?,?,?,?,?,?,?,?,?)";
 
    // Membuka koneksi ke database
    Connection conn = koneksi.konek();
 
    // Menyiapkan statement SQL dengan parameter
    PreparedStatement statement = conn.prepareStatement(sql);
 
    // Mengisi nilai parameter satu per satu
    statement.setString(1, nis);
    statement.setString(2, namaSiswa);
    statement.setString(3, jK);
    statement.setString(4, tempatLahir);
    statement.setString(5, tglLahir);
    statement.setString(6, alamat);
    statement.setString(7, hp);
    statement.setString(8, kelas);
    statement.setString(9, foto);
 
    // Menjalankan query penyimpanan
    statement.execute();
 
    // Menampilkan pesan bahwa data berhasil disimpan
    JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
} catch (SQLException e) {
    // Menampilkan pesan jika terjadi kesalahan saat menyimpan data
    JOptionPane.showMessageDialog(null, "Data gagal disimpan!");
}
 
// Memuat ulang data siswa ke tabel
load_tabel_siswa();
 
// Mengosongkan semua input form setelah data disimpan
reset();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        // Mengambil NIS dari field input
String nis = tNIS.getText();
 
// Mengambil Nama Siswa dari field input
String namaSiswa = tNamaSiswa.getText();
 
// Mengambil nilai dari combo box jenis kelamin dan mengubah menjadi String
String jenisKelamin = cJenisKelamin.getSelectedItem().toString();
 
// Variabel untuk menyimpan kode jenis kelamin ('L' atau 'P')
String jK = null;
 
// Mengambil Tempat Lahir dari field input
String tempatLahir = tTempatLahir.getText();
 
// Mengambil tanggal lahir dari komponen kalender
Date tglLahirDate = tTanggal.getDate();
 
// Mengubah tanggal lahir menjadi format "yyyy-MM-dd"
String tglLahir = new SimpleDateFormat("yyyy-MM-dd").format(tglLahirDate);
 
// Mengambil Nomor HP dari field input
String hp = tHP.getText();
 
// Mengambil Kelas dari combo box
String kelas = cKelas.getSelectedItem().toString();
 
// Mengambil Alamat dari field input
String alamat = tAlamat.getText();
 
// Mengambil path file foto dari field input tersembunyi
String filePath = tFotoPath.getText();
 
// Mengonversi pilihan jenis kelamin ke kode (L/P)
switch (jenisKelamin) {
    case "Laki - laki":
        jK = "L";
        break;
    case "Perempuan":
        jK = "P";
        break;
    default:
        jK = null;
        break;
}
 
// Variabel untuk menyimpan path foto asli yang tersimpan di database
String fotoAsli = null;
 
try {
    // Query untuk mengambil path foto berdasarkan NIS
    String sql = "SELECT foto FROM siswa WHERE nis = ?";
    Connection conn = koneksi.konek();
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, nis);
    ResultSet rs = ps.executeQuery();
 
    // Jika data ditemukan, simpan path foto ke variabel fotoAsli
    if (rs.next()) {
        fotoAsli = rs.getString("foto");
    }
} catch (SQLException e) {
    // Tampilkan pesan jika gagal mengambil foto dari database
    JOptionPane.showMessageDialog(null, "Gagal mengambil foto asli: " + e.getMessage());
}
 
// Menentukan apakah foto diubah oleh pengguna
boolean fotoDiubah = (fotoAsli == null && !filePath.isEmpty())
        || (fotoAsli != null && !fotoAsli.equals(filePath));
 
// Jika foto diubah, variabel 'foto' akan diisi dengan path baru
String foto = fotoAsli;
 
if (fotoDiubah) {
    try {
        // Ambil file dari path baru
        File sourceFile = new File(filePath);
 
        // Dapatkan ekstensi file
        String extension = filePath.substring(filePath.lastIndexOf('.') + 1);
 
        // Buat nama file baru berdasarkan waktu agar unik
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destinationPath = "src/foto/foto-" + timestamp + "." + extension;
 
        // Salin file ke folder tujuan
        File destFile = new File(destinationPath);
        Files.copy(sourceFile.toPath(), destFile.toPath());
 
        // Simpan path tujuan ke variabel 'foto'
        foto = destinationPath;
 
    } catch (Exception e) {
        // Tampilkan pesan jika gagal upload file
        JOptionPane.showMessageDialog(null, "Gagal upload file: " + e.getMessage());
    }
}
 
try {
    // Query SQL berbeda tergantung apakah foto diubah atau tidak
    String sql2;
    if (fotoDiubah) {
        sql2 = "UPDATE siswa SET nama_siswa=?, gender=?, tempat_lahir=?, "
                + "tgl_lahir=?, alamat=?, no_hp=?, id_kelas=?, foto=? WHERE nis=?";
    } else {
        sql2 = "UPDATE siswa SET nama_siswa=?, gender=?, tempat_lahir=?, "
                + "tgl_lahir=?, alamat=?, no_hp=?, id_kelas=? WHERE nis=?";
    }
 
    // Membuka koneksi ke database
    Connection conn = koneksi.konek();
 
    // Menyiapkan statement untuk eksekusi SQL
    PreparedStatement statement = conn.prepareStatement(sql2);
 
    // Menetapkan parameter umum
    statement.setString(1, namaSiswa);
    statement.setString(2, jK);
    statement.setString(3, tempatLahir);
    statement.setString(4, tglLahir);
    statement.setString(5, alamat);
    statement.setString(6, hp);
    statement.setString(7, kelas);
 
    // Jika foto diubah, tetapkan parameter tambahan
    if (fotoDiubah) {
        statement.setString(8, foto);
        statement.setString(9, nis);
    } else {
        statement.setString(8, nis);
    }
 
    // Eksekusi perintah update
    statement.execute();
 
    // Tampilkan pesan sukses
    JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
 
} catch (SQLException e) {
    // Tampilkan pesan jika update gagal
    JOptionPane.showMessageDialog(null, "Gagal memperbarui data: " + e.getMessage());
}
 
// Muat ulang tabel agar perubahan terlihat
load_tabel_siswa();
 
// Kosongkan form setelah proses selesai
reset();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        // Mengambil nilai NIS dari field input
        String nis = tNIS.getText();
        
        // Menyiapkan perintah SQL untuk menghapus data siswa berdasarkan NIS
        String sql = "DELETE FROM siswa WHERE nis=?";
        
        try {
            // Membuka koneksi ke database
            Connection conn = koneksi.konek();

            // Menyiapkan statement SQL untuk dieksekusi
            PreparedStatement statement = conn.prepareStatement(sql);

            // Mengisi parameter pertama (tanda ?) dengan nilai NIS
            statement.setString(1, nis);

            // Menjalankan perintah DELETE
            statement.execute();

            // Menampilkan pesan bahwa data berhasil dihapus
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (SQLException e) {
            // Menampilkan pesan jika terjadi kesalahan saat menghapus
            JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e.getMessage());
        } 
        
        // Memuuat ulang data  tabel agar tampilan diperbarui
        load_tabel_siswa();
        
        // Mengosongkan semua input form setelah data dihapus
        reset();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cJenisKelamin;
    private javax.swing.JComboBox<String> cKelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea tAlamat;
    private javax.swing.JLabel tFoto;
    private javax.swing.JLabel tFotoPath;
    private javax.swing.JTextField tHP;
    private javax.swing.JTextField tNIS;
    private javax.swing.JTextField tNamaSiswa;
    private com.toedter.calendar.JDateChooser tTanggal;
    private javax.swing.JTextField tTempatLahir;
    private javax.swing.JTable tblSiswa;
    // End of variables declaration//GEN-END:variables
}
