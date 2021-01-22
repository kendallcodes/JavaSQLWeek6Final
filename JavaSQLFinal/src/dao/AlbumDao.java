package dao;

import java.sql.Connection;
<<<<<<< HEAD
=======
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Album;


public class AlbumDao {

	private Connection connection;

	
	public AlbumDao() {
		connection = DBConnection.getConnection();
	}
	

	private final String GET_ALBUM_BY_ALBUM_NAME_QUERY = "SELECT * FROM recording_artists WHERE album_name = ? ";
	private final String CREATE_NEW_ALBUM_QUERY = "INSERT INTO recording_artists(album_name, label, genre) VALUES(?, ?, ?)";
	private final String DELETE_ALBUM_NAME_QUERY = "DELETE FROM recording_artists WHERE album_name = ?";
	private final String UPDATE_ALBUM_NAME_QUERY = "UPDATE FROM recording_artist WHERE album_name = ?";
	
	public AlbumDao() {
		connection = DBConnection.getConnection();
		//albumDao = new AlbumDao();
	}
	
	public List<Album> getAlbums() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_ALBUM_BY_ALBUM_NAME_QUERY).executeQuery();
		List<Album> albums = new ArrayList<Album>();
		
		while (rs.next()) {
			albums.add(new Album(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		
		return albums;
		
	}
	
	public void createAlbumByName(String albumName, String artistName, String label, String genre, String certification) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_ALBUM_QUERY);
		ps.setString(1, albumName); //artistID
		ps.setString(2, artistName);
		ps.setString(3, label);
		ps.setString(4, genre);
	    ps.executeUpdate();
		
	}
	
	public void deleteAlbumByName(String albumName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_ALBUM_NAME_QUERY); 
		ps.setString(1, albumName);
		ps.executeUpdate();        
		
	}
	
	public void updateAlbumByName(String albumName, String label, String genre) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_ALBUM_NAME_QUERY);
		ps.setString(1, albumName);
		ps.setString(2, arg1);
		ps.setString(2, label);
		ps.setString(3, genre);
		ps.executeUpdate();
	}

}
