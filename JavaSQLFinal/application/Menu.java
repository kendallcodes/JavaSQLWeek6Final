package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.AlbumDao;
import dao.ArtistDao;
import dao.CertificationDao;
import entity.Album;
import entity.Artist;
import entity.Certification;

public class Menu {
		private AlbumDao albumDao = new AlbumDao();    
		private ArtistDao artistDao = new ArtistDao();
		private CertificationDao certificationDao = new CertificationDao();
		private final String DATABASE_NAME = "recording_artists";
		private Scanner scanner = new Scanner(System.in);
		private List<String> options = Arrays.asList("Album Menu",
													 "Artist Menu",
													 "Certification Menu");
						
		private List<String> album_options = Arrays.asList("Display All Albums",
				 									 	   "Add A New Album", 
													       "Delete An Album",
													       "Update An Album");
		
		
		private List<String> artist_options = Arrays.asList("Display All Artists",
				 									        "Add A New Artist", 
													        "Delete An Artist",
													        "Update An Artist");
		
		
		private List<String> cert_options = Arrays.asList("Display All Certifications",
				 									      "Add A New Certification", 
													      "Delete A Certification",
													      "Update A Certification");
		
		public void start() {
			String selection = "";
			String subselection = "";
	

			do {
				System.out.println("---------------------------");
				System.out.println("MAIN " + DATABASE_NAME + " MENU");
				System.out.println("---------------------------");
				printMenu(options);
				scanner = new Scanner(System.in);
				selection = scanner.nextLine();
				
				try {
					
					if (selection.equals("1") ) {
						System.out.println("----------------------");
						System.out.println("ALBUM Information Menu");
						System.out.println("----------------------");
						do {
							printMenu(album_options);
							scanner = new Scanner(System.in);
							subselection = scanner.nextLine();
					
							if (subselection.equals("1") ) {
								System.out.println("\tDisplaying all albums...\n");
								displayAllAlbums();
							} else if (subselection.equals("2") ) {
								System.out.println("\tAdding an album...\n");
								addNewAlbum();
							} else if (subselection.equals("3") ) {
								System.out.println("\tDeleting an album...\n");
								deleteAlbum();
							} else if (subselection.equals("4") ) {
								System.out.println("\tUpdating an album...\n");
								updateAlbum();
							} else if (!(subselection.equals("-1"))) {
									System.out.println("Invalid Option!");
							}
								
						} while (!(subselection.equals("-1")));	
						
					} else if (selection.equals("2") ) {
						System.out.println("-----------------------");
						System.out.println("ARTIST Information Menu");
						System.out.println("-----------------------");

						do {
							printMenu(artist_options);
							scanner = new Scanner(System.in);
							subselection = scanner.nextLine();

						    if (subselection.equals("1") ) {
						    	System.out.println("\tDisplaying all artists...\n");
								displayAllArtists();						
							} else if (subselection.equals("2") ) {
								System.out.println("\tAdding an artist...\n");
								addNewArtist();		
							} else if (subselection.equals("3") ) {
								System.out.println("\tDeleting an artist...\n");
								deleteArtist();		
							} else if (subselection.equals("4") ) {
								System.out.println("\tUpdating an artist...\n");
								updateArtist();					
							} else if (!(subselection.equals("-1"))) {
									System.out.println("Invalid Option!");
							}
						} while (!(subselection.equals("-1")));
													
						
					} else if (selection.equals("3") ) {
						System.out.println("------------------------------");
						System.out.println("CERTIFICATION Information Menu");
						System.out.println("------------------------------");

						do {
							printMenu(cert_options);
							scanner = new Scanner(System.in);
							subselection = scanner.nextLine();
							
							if (subselection.equals("1") ) {
								System.out.println("\tDisplaying all certifications...\n");
								displayAllCerts();		
							} else if (subselection.equals("2") ) {
								System.out.println("\tAdding a certification...\n");
								addNewCert();		
							} else if (subselection.equals("3") ) {
								System.out.println("\tDeleting a certification...\n");
								deleteCert();		
							} else if (subselection.equals("4") ) {
								System.out.println("\tUpdating a certification...\n");
								updateCert();		
							} else if (!(subselection.equals("-1"))) {
										System.out.println("Invalid Option!");		
							}
							
						} while (!(subselection.equals("-1")));
						

					} else if (!(selection.equals("-1"))) {
							System.out.println("Invalid Option!");	
					} 
					
				} catch(Exception e) {
						System.out.println("Error!");
			            e.printStackTrace(); 
				}
				
			} while (!(selection.equals("-1")));
			System.out.println("\n\nEnd of program...\n\nThanks for using the " + DATABASE_NAME + " database!");
		
			
		}		

		
	/*
	 * Method:  displayAllAlbums()
	 */
	private void displayAllAlbums() throws SQLException {
		/*
		 * No need for input... print all album data
		 */		
		List<Album> albums = albumDao.getAlbums();
		for (Album album : albums) {	
			Artist artist = artistDao.getArtistById(album.getArtistId());			
			System.out.println("\nName: " + album.getAlbumName() + "\n\tId: " + album.getAlbumId() + "\tArtist: " + artist.getArtistName() 
					+ "\n\tLabel: " + album.getLabel() + "\tGenre: " + album.getGenre());
		}
	}
	
	/*
	 * Method:  addNewAlbum()
	 */	
	private void addNewAlbum() throws SQLException {
		/*
		 * prompt user for all new album data
		 */
		System.out.print("Enter Album Name: ");
		String albumName = scanner.nextLine();	
		System.out.print("Enter Artist: ");
		String artistName = scanner.nextLine();	  // Use this to find the Artist_ID by calling 
												  // artistDao.getArtistByName(artistName).getArtist_id();
		Artist artist = artistDao.getArtistByName(artistName);			
		if (artist == null) {
			artistDao.createNewArtist(artistName);
			artist = artistDao.getArtistByName(artistName);
		}
		System.out.print("Enter Label: ");
		String labelName = scanner.nextLine();	
		System.out.print("Enter Genre: ");
		String genre = scanner.nextLine();	

		albumDao.createAlbum(artist.getArtistId(), albumName, labelName, genre);
	}

	/*
	 * Method:  deleteAlbum()
	 */
	private void deleteAlbum() throws SQLException {
		/*
		 * prompt user for album name, and confirm that they want to delete?:
		 */
		System.out.print("Enter Name of Album to Delete: ");
		String albumName = scanner.nextLine();	
		Album album = albumDao.getAlbumByName(albumName);
		if (album == null) {
			System.out.println("Album doesn't exist!");
		} else {
			System.out.println("Deleting Album...");
			albumDao.deleteAlbumByName(albumName);
		}
			
		
		
	}

	/*
	 * Method:  updateAlbum()
	 */
	private void updateAlbum() throws SQLException {
		/*
		 * prompt user for album name, and possible input changes:
		 */
		System.out.print("Enter Name of Album to Update: ");
		String albumName = scanner.nextLine();	
		Album album = albumDao.getAlbumByName(albumName);
		if (album == null) {
			System.out.println("Album doesn't exist!");
		} else {
			int albumId = album.getAlbumId();
			System.out.print("Enter Change to Album Name: ");
			String newName = scanner.nextLine();
			
			if (newName.equals("")) {
				newName = albumName;
			}
			
			System.out.print("Enter Name of Label to Update: ");
			String label = scanner.nextLine();	
			System.out.print("Enter Genre to Update: ");
			String genre = scanner.nextLine();	
			
			System.out.println("Updating Album...");
			albumDao.updateAlbum(albumId, album.getArtistId(),newName,label,genre);
		}
		
	}

	/*
	 * Method:  displayAllArtists()
	 */
	private void displayAllArtists() throws SQLException {
		/*
		 * No need for input... print all artist data
		 */
		List<Artist> artists = artistDao.getArtists();
		for (Artist artist : artists) {
			System.out.println("Id: " + artist.getArtistId() + " \tName: " + artist.getArtistName());	
		}		
		
	}

	/*
	 * Method:  addNewArtist()
	 */	
	private void addNewArtist() throws SQLException {
		/*
		 * prompt user for all new artist data
		 */
		System.out.print("Enter New Artist: ");
		String artist_name = scanner.nextLine();
		artistDao.createNewArtist(artist_name);		
	}

	
	/*
	 * Method:  deleteArtist()
	 */
	private void deleteArtist() throws SQLException {
		/*
		 * prompt user for artist name, and confirm that they want to delete?:
		 */
		System.out.print("Enter Name of Artist to Delete: ");
		String artistName = scanner.nextLine();
		
		System.out.println("Deleting Artist...");
		Artist artist = artistDao.getArtistByName(artistName);
		if (artist == null) {
			System.out.println("Artist does not exist!");
			System.out.println("Delete Artist not performed!");
		} else {


			System.out.println("Id: " + artist.getArtistId() + " \tName: " + artist.getArtistName());

			System.out.println("\t**  This will also delete all of the associated data in the " + DATABASE_NAME + " database!  **\n");
			System.out.print("Would you like to proceed, yes or no?  ");
			String response = scanner.nextLine();
			if (response.equalsIgnoreCase("yes")) {
				artistDao.deleteArtist(artistName);	
			} else {
				System.out.println("Delete Artist not performed!");
			}	
		}
	}

	/*
	 * Method:  updateArtist()
	 */
	private void updateArtist() throws SQLException {
		/*
		 * prompt user for artist name to change, and new artist name
		 */
		System.out.print("Enter Name of Artist to Update: ");
		String artistName = scanner.nextLine();
		System.out.println("Updating Artist...");
		Artist artist = artistDao.getArtistByName(artistName);
		if (artist == null) {
			System.out.println("Artist Not Found in database: " + DATABASE_NAME);
			System.out.println("Update Artist not performed!");

		} else {

			System.out.println("Id: " + artist.getArtistId() + " \tName: " + artist.getArtistName());	

			System.out.println();
			System.out.print("Enter NEW Name of Artist to Update: ");
			String newName = scanner.nextLine();
			if (newName == null) {
				System.out.println("Update Artist not performed!");
			} else {
				artistDao.updateArtist(artistName,newName);
			}	
		}
	}

	/*
	 * Method:  displayAllCerts()
	 */
		private void displayAllCerts() throws SQLException {
			List<Certification> certification = certificationDao.displayAllCerts();
				for (Certification certs : certification) {
				System.out.println(certs.getCertId() + " " + certs.getAlbumId() + " " + certs.getCertStatus() + " " + certs.getCertDate()); 
					}
	}


	/*
	 * Method:  addNewCert()
	 */	
	private void addNewCert() throws SQLException {

		System.out.print("Enter Name of Album for New Certification: ");
		String albumName = scanner.nextLine();
		Album album = albumDao.getAlbumByName(albumName);
		if (album == null) {
			System.out.println("Album Not Found in database: " + DATABASE_NAME);
			System.out.println("Add New Certification not performed!");
		} else {	
			System.out.println("Enter New Certification...");
			String certStatus = scanner.nextLine(); 
			System.out.println("Enter Certifcation Date..");
			String certDate = scanner.nextLine(); 
			certificationDao.addNewCert(album.getAlbumId(), certStatus, certDate);
		}	
	}

	/*
	 * Method:  deleteCert()
	 */
	private void deleteCert() throws SQLException {
		System.out.println("Enter Certification Id to delete....");
		int certId = Integer.parseInt(scanner.nextLine());
		certificationDao.deleteCert(certId);
	}

	/*
	 * Method:  updateCert()
	 */

	private void updateCert() throws SQLException {
		
		System.out.println("Enter Certification ID to update...");
		int certId = Integer.parseInt(scanner.nextLine());		
		System.out.println("Enter New Certification Status...");
		String certStatus = scanner.nextLine();
		System.out.println("Enter New Certification Date...");
		String certDate = scanner.nextLine();
		certificationDao.updateCert(certId, certStatus, certDate);

		
	}


	/*
	 * Method:  printMenu()
	 */
	private void printMenu(List<String> options) {
		System.out.println("\n------------------------------------------------------------");
		System.out.println("\tPlease SELECT AN OPTION...\n\tAll requests are on the " + DATABASE_NAME + " database!\n------------------------------------------------------------");
		for (int i = 0; i<options.size(); i++) {
				System.out.println("   "+(i+1) + ") " + options.get(i));
			}		
		System.out.println("  -1) Exit Menu");
		System.out.println("------------------------------------------------------------");
	}
	
	
	
	
}
