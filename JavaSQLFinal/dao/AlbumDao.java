package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Album;


public class AlbumDao {

	private Connection connection;
	
	private final String GET_ALBUM_BY_ALBUM_NAME_QUERY = "SELECT * FROM album WHERE album_name = ? ";
	private final String GET_ALL_ALBUMS_QUERY = "SELECT * FROM album";
	private final String CREATE_NEW_ALBUM_QUERY = "INSERT INTO album (artist_id, album_name, label, genre) VALUES(?, ?, ?, ?)";
	private final String DELETE_ALBUM_NAME_QUERY = "DELETE FROM album WHERE album_name = ?";
	private final String UPDATE_ALBUM_QUERY = "UPDATE album SET artist_id = ?, label = ?, genre = ?, album_name = ? WHERE album_id = ?";
	
	public AlbumDao() {
		connection = DBConnection.getConnection();
		//albumDao = new AlbumDao();
	}
	
	public List<Album> getAlbums() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_ALL_ALBUMS_QUERY).executeQuery();
		List<Album> albums = new ArrayList<Album>();		
		while (rs.next()) {
			albums.add(new Album(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}		
		return albums;
		
	}	
	
	
	public Album getAlbumByName(String albumName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_ALBUM_BY_ALBUM_NAME_QUERY);
		ps.setString(1, albumName);
		ResultSet rs = ps.executeQuery();
		Album album = null;		
		if (rs.next()) {
			album = new Album(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
		}		
		return album;
		
	}	
	
	public void createAlbum (int artistId, String albumName, String label, String genre) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_ALBUM_QUERY);
		ps.setInt(1, artistId);
		ps.setString(2, albumName); 
		ps.setString(3, label);
		ps.setString(4, genre);
	    ps.executeUpdate();		
	}
	
	public void createAlbumByName(int artistId, String albumName, String label, String genre) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_ALBUM_QUERY);
		ps.setInt(1, artistId);
		ps.setString(2, albumName); 
		ps.setString(3, label);
		ps.setString(4, genre);
	    ps.executeUpdate();		
	}
	
	public void deleteAlbumByName(String albumName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_ALBUM_NAME_QUERY); 
		ps.setString(1, albumName);
		ps.executeUpdate();        	
	}
	
	public void updateAlbum(int albumId, int artistId, String albumName, String label, String genre) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_ALBUM_QUERY);
		System.out.println(albumId + " " + artistId + " " + albumName + " " + label + " " + genre);
		ps.setInt(1, artistId);
		ps.setString(2, label);
		ps.setString(3, genre);
		ps.setString(4, albumName);
		ps.setInt(5, albumId );
		ps.executeUpdate();
	}
	
}
