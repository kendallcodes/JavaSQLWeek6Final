package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Certification;

public class CertificationDao {
	
	private Connection connection; 
	private final String GET_CERTS_QUERY = "SELECT * FROM certification";
	private final String CREATE_NEW_CERT_QUERY = "INSERT INTO certification(album_id, cert_status, cert_date) VALUES (?,?,?)";
	private final String UPDATE_CERT_QUERY = "UPDATE certification SET cert_status = ?, cert_date = ? WHERE cert_id = ?";
	private final String DELETE_CERT_QUERY = "DELETE FROM certification WHERE cert_id = ?";
	
	public CertificationDao() {
		connection = DBConnection.getConnection(); 
	}
	
	public List<Certification> displayAllCerts() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CERTS_QUERY).executeQuery();
		List<Certification> certifications = new ArrayList<Certification>(); 
			
			while (rs.next()) { 
				certifications.add(populateCertifications(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
			}
			return certifications; 
		}

	public
	void addNewCert (int albumId, String certStatus, String certDate) throws SQLException { 
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CERT_QUERY); 
		ps.setInt(1,albumId);
		ps.setString(2, certStatus);
		ps.setString(3, certDate);
		ps.executeUpdate();
	}
	
	public void updateCert (int certId, String certStatus, String certDate) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_CERT_QUERY);
		ps.setString(1, certStatus);
		ps.setString(2, certDate);
		ps.setInt(3, certId); 
		ps.executeUpdate();	
	}
	
	public void deleteCert (int certId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CERT_QUERY);
		ps.setInt(1, certId);
		ps.executeUpdate();	
	}
	private Certification populateCertifications(int certId , int albumId, String certStatus, String certDate) {
		return new Certification (certId, albumId, certStatus, certDate);
	}	
}


