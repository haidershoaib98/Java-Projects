package project_test;

public class Order {
	private String videos;
	private String genre;
	private String id;
	private double price = 1.5;
	
	public Order(String videos, String genre, String id) {
		this.videos = videos;
		this.genre = genre;
		this.id = id;
		
	}
	
	public String print() {
		return videos + genre + id;
	}
	
	public String printOrder() {
		return "Video: " + videos + ", Genre: " + genre + ", ID: " + id + ", Price: " + price;
	}

	public String getVideos() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = videos;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}