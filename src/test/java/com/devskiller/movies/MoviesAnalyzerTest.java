package com.devskiller.movies;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MoviesAnalyzerTest {

  private List<Movie> movies;
  private List<User> users;

  @Before
  public void setUp() throws Exception {
    Gson gson = new Gson();
    movies = gson.fromJson(Files.readString(Path.of("src/test/resources/movies.json")), new TypeToken<ArrayList<Movie>>(){}.getType());
    users = gson.fromJson(Files.readString(Path.of("src/test/resources/users.json")), new TypeToken<ArrayList<User>>(){}.getType());
  }

  @Test(timeout = 1000)
  public void shouldReturnTop3ForFirstUser() {
    //given
    MoviesAnalyzer moviesAnalyzer = new MoviesAnalyzer(movies, users);

    //when
    List<String> titles = moviesAnalyzer.topFavouriteMoviesAmongFriends(15291);

    //then
    assertThat(titles).isEqualTo(Lists.newArrayList("Pulp Fiction", "The Godfather", "Schindler's List"));
  }

  @Test(timeout = 1000)
  public void shouldReturnTop3ForSecondUser() {
    //given
    MoviesAnalyzer moviesAnalyzer = new MoviesAnalyzer(movies, users);

    //when
    List<String> titles = moviesAnalyzer.topFavouriteMoviesAmongFriends(7001);

    //then
    assertThat(titles).isEqualTo(Lists.newArrayList("Pulp Fiction", "Schindler's List", "The Godfather"));
  }
  @Test(timeout = 1000)
  public void shouldReturnTop3ForThirdUser() {
    //given
    MoviesAnalyzer moviesAnalyzer = new MoviesAnalyzer(movies, users);

    //when
    List<String> titles = moviesAnalyzer.topFavouriteMoviesAmongFriends(51417);

    //then
    assertThat(titles).isEqualTo(Lists.newArrayList("The Dark Knight", "The Godfather", "The Shawshank Redemption"));
  }
  @Test(timeout = 1000)
  public void shouldReturnTop3ForFourthUser() {
    //given
    MoviesAnalyzer moviesAnalyzer = new MoviesAnalyzer(movies, users);

    //when
    List<String> titles = moviesAnalyzer.topFavouriteMoviesAmongFriends(62289);

    //then
    assertThat(titles).isEqualTo(Lists.newArrayList("The Dark Knight", "The Godfather", "Pulp Fiction"));
  }
}