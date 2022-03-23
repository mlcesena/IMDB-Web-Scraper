import java.beans.EventHandler;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.ParseException;

import javax.swing.Action;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/* 	To run:
*		cd into applications (type 'cd application' into terminal)
*		paste 'javac --module-path ../javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml GUI.java' and press enter
*		paste 'java --module-path ../javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml GUI.java' and press enter
*		there ya go

		javac --module-path ../javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml GUI.java && java --module-path ../javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml GUI.java
*/

public class GUI extends Application{

	public static Stage primaryStage;
	public static Scene titleScene;
	public static Scene loadingScene;
	public static Scene mainScene;
	public static MovieSuggestion movieSuggestion = MovieSuggestion.getMovieSuggestion();

	public static void main(String[] args) throws InterruptedException {
		GUI gui = new GUI();
		gui.newGUI(args);
	}

	public void newGUI(String[] args) throws InterruptedException {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		GUI.primaryStage = arg0;
		
		loadingScene = loadingScene();
		mainScene = mainScene();
		titleScene = titleScene();
		
		GUI.primaryStage.setScene(titleScene);
		GUI.primaryStage.show();
	}
	
	public Scene titleScene() {
		GridPane grid = new GridPane();
		grid.setPrefSize(1280, 720);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		Label loadingLabel = new Label("IEEE IMDB Scraper");
		grid.add(loadingLabel, 0, 0);

		Button openButton = new Button("Enter");
		openButton.setOnAction(e -> {
			GUI.primaryStage.setScene(loadingScene);
			try {
				movieSuggestion.loadMovieInfo();
			} catch (FileNotFoundException | ParseException | MalformedURLException e1) {
				e1.printStackTrace();
			}
			GUI.primaryStage.setScene(mainScene);
			});
		grid.add(openButton, 0, 1);
		
		return new Scene(grid);
	}

	public Scene mainScene() {
		GridPane grid = new GridPane();
		grid.setPrefSize(1280, 720);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Label loadingLabel = new Label("IEEE IMDB Scraper");
		grid.add(loadingLabel, 1, 0);

		ComboBox<String> genre1 = new ComboBox<String>();
		ComboBox<String> genre2 = new ComboBox<String>();
		ComboBox<String> genre3 = new ComboBox<String>();

		genre1.getItems().addAll("", "Drama", "Crime", "Action", "Biography", "History", "Adventure", "Western", "Romance", "Sci-Fi", "Fantasy", "Mystery", "Thriller", "Family", "War", "Animation", "Music", "Comedy", "Film-Noir", "Musical", "Sport");
		genre2.getItems().addAll("", "Drama", "Crime", "Action", "Biography", "History", "Adventure", "Western", "Romance", "Sci-Fi", "Fantasy", "Mystery", "Thriller", "Family", "War", "Animation", "Music", "Comedy", "Film-Noir", "Musical", "Sport");
		genre3.getItems().addAll("", "Drama", "Crime", "Action", "Biography", "History", "Adventure", "Western", "Romance", "Sci-Fi", "Fantasy", "Mystery", "Thriller", "Family", "War", "Animation", "Music", "Comedy", "Film-Noir", "Musical", "Sport");

		ListView<Movie> movieList = new ListView<Movie>();
		movieList.setMinWidth(400);

		movieList.getItems().addAll(MovieSuggestion.getMovies());

		genre1.setOnAction(e -> {
			movieList.getItems().remove(0, movieList.getItems().size());
			movieList.getItems().addAll(movieSuggestion.getMatchingMovies(genre1.getValue(), genre2.getValue(), genre3.getValue()));
			});
		genre2.setOnAction(e -> {
			movieList.getItems().remove(0, movieList.getItems().size());
			movieList.getItems().addAll(movieSuggestion.getMatchingMovies(genre1.getValue(), genre2.getValue(), genre3.getValue()));
			});
		genre3.setOnAction(e -> {
			movieList.getItems().remove(0, movieList.getItems().size());
			movieList.getItems().addAll(movieSuggestion.getMatchingMovies(genre1.getValue(), genre2.getValue(), genre3.getValue()));
			});

		grid.add(genre1, 0, 1);
		grid.add(genre2, 1, 1);
		grid.add(genre3, 2, 1);

		grid.add(movieList, 1, 2);

		return new Scene(grid);
	}

	public Scene loadingScene() {
		GridPane grid = new GridPane();
		grid.setPrefSize(1280, 720);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Label loadingLabel = new Label("Loading...");
		grid.add(loadingLabel, 0, 0);

		return new Scene(grid);
	}

}