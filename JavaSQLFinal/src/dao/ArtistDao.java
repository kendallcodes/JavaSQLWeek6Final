package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Artist;

public class ArtistDao {

	private Connection connection;
	private final String GET_ARTISTS_QUERY = "SELECT * FROM artist";
	private final String GET_ARTIST_BY_NAME_QUERY = "SELECT * FROM artist WHERE artist_name = ?";
	private final String CREATE_NEW_ARTIST_UPDATE = "INSERT INTO artist (artist_name) VALUES (?)";
	private final String DELETE_ARTIST_UPDATE = "DELETE FROM artist WHERE artist_name = ?";
	private final String UPDATE_ARTIST_UPDATE = "UPDATE artist SET artist_name = ? WHERE artist_id = ?";
	
	public ArtistDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Artist> getArtists() throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_ARTISTS_QUERY);
		ResultSet rs = ps.executeQuery();
		List<Artist> artists = new ArrayList<Artist>();
		while (rs.next()) {
			artists.add(populateArtist(rs.getInt(1),rs.getString(2)));
		}
		return artists;
	}
	
	public Artist getArtistByName(String artistName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_ARTIST_BY_NAME_QUERY);
		ps.setString(1,artistName);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return populateArtist(rs.getInt(1),rs.getString(2));
		} else {
			return null;
		}
	}
	
	public void createNewArtist(String artistName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (CREATE_NEW_ARTIST_UPDATE);
		ps.setString(1, artistName);
		ps.executeUpdate();
	}
	
	public void deleteArtist(String artistName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (DELETE_ARTIST_UPDATE);
		ps.setString(1, artistName);
		ps.executeUpdate();		
	}
	
	public void updateArtist(String artistName, String newName) throws SQLException {
		Artist artist = getArtistByName(artistName);
		PreparedStatement ps = connection.prepareStatement (UPDATE_ARTIST_UPDATE);
		ps.setString(1, newName);
		ps.setInt(2, artist.getArtist_id());
		ps.executeUpdate();	
	}
	

	private Artist populateArtist(int artist_id, String artist_name) {
		return new Artist(artist_id,artist_name);
	}
}
