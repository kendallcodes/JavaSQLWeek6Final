package entity; 

public class Certification {
	
	private int certId;
	private int albumId; 
	private String certStatus; 
	private String certDate;
	


	public Certification (int certId, int albumId, String certStatus, String certDate) {
		this.setAlbumId(albumId);
		this.setCertId(certId);
		this.setCertStatus(certStatus);
		this.setCertDate(certDate);
	}

	public int getCertId() {
		return certId;
	}

	public void setCertId(int certId) {
		this.certId = certId;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(String certStatus) {
		this.certStatus = certStatus;
	}

	public String getCertDate() {
		return certDate;
	}

	public void setCertDate(String certDate) {
		this.certDate = certDate;
	
    }

	
}
	
