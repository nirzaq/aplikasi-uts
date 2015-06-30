package id.arief.belajar.aplikasi.crud;

import java.sql.*;

public class MahasiswaDao {
	private Connection koneksiDatabase;
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost/crud";
	private String dbUsername = "root";
	private String dbPassword = "123456";

	public void connect() {
		try {
			Class.forName(dbDriver);
			koneksiDatabase = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (Exception error) {
			error.printStackTrace();
		}

	}

	public void disconnect() {
		try {
			if (koneksiDatabase != null) {
				koneksiDatabase.close();
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	public void simpan(Mahasiswa m) {
		try {
			connect();

			String sql = "INSERT INTO mahasiswa (nim, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, alamat) VALUES (?,?,?,?,?,?)";

			PreparedStatement ps = koneksiDatabase.prepareStatement(sql);
			ps.setString(1, m.getNim());
			ps.setString(2, m.getNama());
			ps.setString(3, m.getTempatLahir());
			ps.setDate(4, new java.sql.Date(m.getTanggalLahir().getTime()));
			ps.setString(5, m.getJenisKelamin().toString());
			ps.setString(6, m.getAlamat());

			ps.executeUpdate();


			disconnect();
		} catch (Exception error) {
			error.printStackTrace();
		}
	}

}