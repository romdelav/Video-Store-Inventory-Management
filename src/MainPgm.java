/*
 * This main program creates an array of video objects named "videoArray" and loads 
 * that array from an input file named "videos.txt". This main program also includes several static methods that interact 
 * with those video objects in a number of ways. Among those methods are: 
 * 1) a printInventory() static void method that calls the toString() method defined in the Video object class to print the 
 * contents of the video collection in a String format 
 * 2) a computeAvgCopiesForStar() static method that searches through the collection to return the average number of copies 
 * of movies bought for a given star 
 * 3) a printMovieTitlesInCollection() static method that accepts whichGenre1 and whichGenre 2 as extra parameters and prints 
 * a list of the movie titles in the collection that are either whichGenre1 or whichGenre2 
 * 4) a printMovieTitlesCostingMore() static method that accepts a parameter value named overThisUnitPrice and prints 
 * the title of all movies with a unit price greater than that parameter value 
 * 5) a printMovieTitlesForStarGenre() static method that accepts whichStar and whichGenre as extra parameters and 
 * prints a list of all the movies in the collection featuring whichStar in whichGenre
 * 6) a printNumberOnHand() static method that prints each movie title along with a number of copies on hand for each movie 
 * title in the inventory. The output is displayed in two columns. 
 * 7) a sortVideos() method that uses Insertion Sort to organize the video objects in the array in ascending order of unit 
 * price
 */

import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

public class MainPgm {
	
	//printInventory prints the contents of the video collection
	public static void printInventory(Video videos[], int counter) {
		System.out.println("Here is the full inventory of movies present at this time: ");
		for(int b = 0; b < counter; b++) {
			System.out.println(videos[b].toString());
		}
	}
	
	//computeAvgCopiesForStar searches through the array of video objects to return the average number 
	//of copies of movies bought for a given star.
    public static double computeAvgCopiesForStar(Video videos[], int counter, String whichStar) {
    	double sum = 0;
    	int count = 0;
    	for(int b = 0; b < counter; b++ ) {
			if(videos[b].getStar().compareTo(whichStar) == 0) {
				
				//computes the sum of all numberBought values for whichStar
				sum += videos[b].getNumberBought(); 
				
				//computes the number of occurrences of numberBought values for whichStar
				count++;  
			}
			
			//if no movies featuring whichStar were bought, return 0
			if(videos[b].getNumberBought() == 0) {
				return 0;
			}
		}
    	
    	//else, return the average number of copies of movies bought for whichStar
    	double avgCopies = sum / count;
    	return avgCopies;   
    }

	//printMovieTitlesInCollection prints a list of the movie titles in the array
	//that are either whichGenre1 or whichGenre2
	public static void printMovieTitlesInCollection(Video videos[], int counter, String whichGenre1,
			                                        String whichGenre2) {
		System.out.println("\nMovies belonging to the genres " + whichGenre1 + " and " + whichGenre2 +":");
		for(int b = 0; b < counter; b++) {
			if(videos[b].genreText() == whichGenre1) {
				System.out.println(videos[b].getMovieTitle());
			}
			if(videos[b].genreText() == whichGenre2) {
				System.out.println(videos[b].getMovieTitle());
			}
		}
		System.out.println();
	}	
	
	//printMovieTitlesCostingMore prints the title of all movies with a unit price greater 
	//than the parameter value passed into the method
	public static void printMovieTitlesCostingMore(Video videos[], int counter, double overThisUnitPrice) {
		System.out.println("Here are the movies in this collection with a unit price greater than $" + overThisUnitPrice );
		for (int b = 0; b < counter; b++) {
			if(videos[b].getUnitPrice() > overThisUnitPrice) {
				System.out.println(videos[b].getMovieTitle());
			}
		}
		System.out.println();
	}
	
	//printMovieTitlesForStarGenre prints the movies featuring whichStar in whichGenre
	public static void printMovieTitlesForStarGenre(Video videos[], int counter, String whichStar, int whichGenre) { 
		for(int b = 0; b < counter; b++) {
			if(whichGenre == videos[b].getGenre()) {
				System.out.println("Here are the movie titles starring " + whichStar + " which are also of type genre " 
	                       + videos[b].genreText() + ": ");
				break;
			}	
		}
		
		//prints a list of the movies featuring whichStar in whichGenre
		boolean found = false;
		for(int b = 0; b < counter; b++) {
			if(videos[b].getStar().compareTo(whichStar) == 0) {
				if(videos[b].getGenre() == whichGenre) {
					found = true;
					System.out.println(videos[b].getMovieTitle());
				}				
			}
		}
		
		//if the search is unsuccessful, prints a message only once
		if(found == false) {
			System.out.println("There are no movies in the collection matching this criteria");
		}
	}
	
	//printNumberOnHand prints each movie title along with a number of copies on hand for each
	//movie title in the inventory. 
	public static void printNumberOnHand(Video videos[], int counter) {
		System.out.println();
		System.out.printf("%-30s%-20s\n", "Movie Title         ", "         Number on Hand");
		System.out.printf("%-30s%-20s\n", "===========================", "         ==============");
		for(int b = 0; b < counter; b++) {
			
			//computes the number of copies on hand
			int numberOnHand = videos[b].getNumberBought() - videos[b].getNumberSold() - videos[b].getNumberRented() - videos[b].getNumberDamaged();
			
			//prints the movie titles and number of copies on hand in two columns of output
			System.out.printf("%-30s%15s%-20s\n", videos[b].getMovieTitle(), "               ", numberOnHand);
		}
		System.out.println();
	}
	
	//sortVideos uses Insertion Sort to organize the video objects in the array in ascending order of unit 
	//price
	public static void InsertionSort(Video videos[], int counter) {
		Video temp;
		for(int i = 0; i < counter; i++) {
			temp = videos[i];
			int j = i;
			while(j > 0 && temp.compareTo(videos[j-1]) < 0) {  
				videos[j] = videos[j-1];
				j--;
			}
			videos[j] = temp;
		}
		
		printInventory(videos, counter);
	}
	
	public static void main(String[] args) {
		
		String movieTitle;
		String productionCompany;
		String star;
		double unitPrice;
		int numberBought;
		int numberSold;
		int numberRented;
		int numberDamaged;
		int genre;
		int counter = 0;
		
		Video [] videoArray = new Video[130];
		
		//loads data from the "videos.txt" input file 
		try {
			Scanner inputFile = new Scanner(new File("videos.txt"));
			while(inputFile.hasNext()) {
				movieTitle = inputFile.nextLine();
				productionCompany = inputFile.nextLine();
				star = inputFile.nextLine();
				unitPrice = inputFile.nextDouble();
				numberBought = inputFile.nextInt();
				numberSold = inputFile.nextInt();
				numberRented = inputFile.nextInt();	
				numberDamaged = inputFile.nextInt();
				genre = inputFile.nextInt();
				videoArray[counter] = new Video(movieTitle, productionCompany, star, unitPrice, numberBought, 
						                        numberSold, numberRented, numberDamaged, genre);
				counter++;
				inputFile.nextLine();
			}
			inputFile.close();	
		}
		catch(Exception e) {
			System.out.println(e);
			return;
		}
		
		printInventory(videoArray, counter);	
			
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		System.out.println("The average number of copies for the star John Travolta is: " + 
                           decimalFormat.format(computeAvgCopiesForStar(videoArray, counter, "John Travolta")));
			
		printMovieTitlesInCollection(videoArray, counter, "Horror", "SciFi");
	
		printMovieTitlesCostingMore(videoArray, counter, 17.45);
		
		printMovieTitlesForStarGenre(videoArray, counter, "Leo DiCaprio", 1);
		System.out.println();
		printMovieTitlesForStarGenre(videoArray, counter, "Leo DiCaprio", 4);
		System.out.println();
		printMovieTitlesForStarGenre(videoArray, counter, "Liam Neeson", 4);
		
		printNumberOnHand(videoArray, counter);

		InsertionSort(videoArray, counter);
		
	
		}
	
	}


