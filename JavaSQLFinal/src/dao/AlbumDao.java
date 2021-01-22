package dao;

import java.sql.Connection;

public class AlbumDao {

	private Connection connection;
	
	public AlbumDao() {
		connection = DBConnection.getConnection();
	}
	
}
