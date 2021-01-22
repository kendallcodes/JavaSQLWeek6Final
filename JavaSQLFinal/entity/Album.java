package entity;

public class Album {
	private int albumId;
	private String artistId;
	private String albumName;
	private String label;
	private String genre;
	
	//Constructor
	
	public Album(int albumId, String albumName, String label, String genre) {  // is this correct nomenclature
		this.setAlbumId(albumId);
		this.setAlbumName(albumName);
		this.setLabel(label);
		this.setGenre(genre);
	}

	//Getters and Setters

	public int getAlbumId() {
		return albumId;
	}


	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}


	public String getAlbumName() {
		return albumName;
	}


	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}

}
