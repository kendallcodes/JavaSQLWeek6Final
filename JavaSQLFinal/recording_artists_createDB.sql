-- Promineo Tech Group Project
-- Week 6 MySQL
-- John, Kendall & Lisa


DROP DATABASE IF EXISTS recording_artists;

CREATE DATABASE IF NOT EXISTS recording_artists;

USE recording_artists;


DROP TABLE IF EXISTS artist,
					 album,
					 certification;

CREATE TABLE artist (
   artist_id	     	INT			NOT NULL	AUTO_INCREMENT,			
   artist_name     		VARCHAR(50) NOT NULL,
   PRIMARY KEY (artist_id)
);

ALTER TABLE artist AUTO_INCREMENT = 10001;

CREATE TABLE album (
   album_id				INT				NOT NULL	AUTO_INCREMENT,	
   artist_id	     	INT				NOT NULL,			
   album_name     		VARCHAR(100)	NOT NULL,
   label				VARCHAR(50),
   genre				VARCHAR(50),
   FOREIGN KEY (artist_id) REFERENCES artist (artist_id), 
   PRIMARY KEY (album_id)
);


CREATE TABLE certification (
   cert_id			INT			NOT NULL	AUTO_INCREMENT,	
   album_id     	INT			NOT NULL,			
   cert_status		ENUM('Diamond', 'Platinum', 'Multi-Platinum', 'Gold'),
   cert_date		DATE,
   FOREIGN KEY (album_id) REFERENCES album (album_id),
   PRIMARY KEY (cert_id)
);

ALTER TABLE certification AUTO_INCREMENT 101;

-- load sample data into recording_artists db


INSERT INTO artist(artist_name)
VALUES 	('The Beatles'),
		('Bob Dylan'),
		('Eagles'),
		('DJ Khaled'),
		('Frankie Goes to Hollywood');
	
SELECT * FROM artist
ORDER BY artist_id;

INSERT INTO album(artist_id, album_name, label, genre)
VALUES 	(10001, 'Rubber Soul', 'Capitol', 'Rock'),
		(10001, 'Sgt. Pepper\'s Lonely Hearts Club Band', 'Capitol', 'Rock'),
		(10001, 'The White Album', 'Apple','Rock'),
		(10002, 'The Freewheelin\' Bob Dylan', 'Columbia', 'Folk'),
		(10002, 'Highway 61 Revisited', 'Columbia', 'Folk'),
		(10002, 'Slow Train Coming', 'Columbia', 'Rock'),
		(10002, 'Infidels', 'Columbia', 'Rock'),
		(10003, 'Desperado', 'Asylum', 'Soft Rock'),
		(10003, 'One of These Nights', 'Asylum', 'Soft Rock'),
		(10003, 'Hotel California', 'Asylum', 'Soft Rock'),
		(10004, 'We the Best', 'Terror Squad E1', 'Hip Hop'),
		(10004, 'We Global', 'Terror Squad E1', 'Hip Hop'),
		(10004, 'I Changed a Lot', 'RED', 'Hip Hop'),
		(10004, 'Major Key', 'Epic', 'Hip Hop'),
		(10004, 'Father of Asahd', 'Roc Nation', 'Hip Hop'),
		(10005, 'Welcome to the Pleasuredome', 'ZTT', 'New Wave'),
		(10005, 'Liverpool', 'ZTT', 'New Wave');
	
SELECT * FROM album;

INSERT INTO certification(album_id, cert_status)
VALUES 	(1,'Platinum'),
		(2,'Diamond'),
		(3,'Diamond'),
		(4,'Platinum'),
		(5,'Platinum'),
		(6,'Platinum'),
		(7,'Gold'),
		(8,'Platinum'),
		(9,'Platinum'),
		(10,'Diamond'),
		(14,'Platinum'),
		(15,'Platinum'),
		(16,'Gold'),
		(17,'Gold');		
		
SELECT * FROM certification;

SELECT a.artist_id, artist_name, a2.album_id, album_name, label, genre, cert_status
FROM artist a, album a2, certification c 
WHERE a.artist_id = a2.artist_id 
AND a2.album_id = c.album_id 
ORDER BY artist_id ;