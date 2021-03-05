
import java.text.DecimalFormat;

public class Video {

	private String movieTitle;
	private String productionCompany;
	private String star;
	private double unitPrice;
	private int numberBought;
	private int numberSold;
	private int numberRented;
	private int numberDamaged;
	private int genre;
	
	public Video() {
		movieTitle = "unknown";
		productionCompany = "unknown";
		star = "unknown";
		unitPrice = 0.0;
		numberBought = 0;
		numberSold = 0;
		numberRented = 0;
		numberDamaged = 0;
		genre = 0;
	}
	
	public Video(String movieTitle, String productionCompany, String star, double unitPrice, 
			     int numberBought, int numberSold, int numberRented, int numberDamaged, 
			     int genre) {
		this.movieTitle = movieTitle;
		this.productionCompany = productionCompany;
		this.star = star;
		this.unitPrice = unitPrice;
		this.numberBought = numberBought;
		this.numberSold = numberSold;
		this.numberRented = numberRented;
		this.numberDamaged = numberDamaged;
		this.genre = genre;
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}

	public String getProductionCompany() {
		return productionCompany;
	}

	public String getStar() {
		return star;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public int getNumberBought() {
		return numberBought;
	}

	public int getNumberSold() {
		return numberSold;
	}

	public int getNumberRented() {
		return numberRented;
	}

	public int getNumberDamaged() {
		return numberDamaged;
	}

	public int getGenre() {
		return genre;
	}

	public String genreText() {
		if(genre == 1) {
			return "Action";
		}
		if(genre == 2) {
			return "Horror";
		}
		if(genre == 3) {
			return "SciFi";
		}
		if(genre == 4) {
			return "Comedy";
		}		
		else return null;
	}

	public String toString() {
		
		DecimalFormat Currency = new DecimalFormat("$0.00");
		
		return "Movie title: " + movieTitle + "\n" + "Production company: " + productionCompany
			   + "\n" + "Star: " + star +"\n" + "Unit price: " + Currency.format(unitPrice) + "\n" + 
			   "Number bought: " + numberBought + "\n" + "Number sold to date: " + numberSold 
			   + "\n" + "Number rented out: " + numberRented + "\n" + "Number damaged: " 
			   + numberDamaged + "\n" + "Genre: " + genreText() + "\n";
	}
	
	public int compareTo(Video other) {
		if(this.unitPrice < other.unitPrice) return -1;
		else if(this.unitPrice == other.unitPrice) return 0;
		else return 1;
		}
}
