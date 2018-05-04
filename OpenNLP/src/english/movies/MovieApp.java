package english.movies;

public class MovieApp {

	public MovieApp() {}

	public static void main(String[] args) {
		classifierExample();
	}
	
	private static void classifierExample(){
		//String movie = "american gang";
		String movie = "shiva";
		
		String genre = MovieClassifierTrainer.classify(movie.split(" "));
		System.out.println(genre);
	}

}
