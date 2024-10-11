package com.devskiller.movies;

import java.util.*;
import java.util.stream.Collectors;

public class MoviesAnalyzer {

  private final List<Movie> movies;
  private final List<User> users;

  public MoviesAnalyzer(List<Movie> movies, List<User> users) {
    this.movies = movies;
    this.users = users;
  }

  public List<Integer> getFriends(int userId) {
    return users.stream()
            .filter(user -> user.userId() == userId)
            .map(User::friends)
            .findFirst()
            .orElse(Collections.emptyList());
  }

  private List<String> getFavoriteMovies(int friendId) {
    List<String> favoriteMovies = new ArrayList<>();
    for (Movie movie : movies) {
      if (movie.favorites().contains(friendId)) {
        favoriteMovies.add(movie.title());
      }
    }
    return favoriteMovies;
  }

  public List<String> topFavouriteMoviesAmongFriends(int userId) {
    List<Integer> friends = getFriends(userId);
    Map<String, Integer> movieCount = new HashMap<>();

    for (int friendId : friends) {
      List<String> favoriteMovies = getFavoriteMovies(friendId);
      for (String movie : favoriteMovies) {
        movieCount.put(movie, movieCount.getOrDefault(movie, 0) + 1);
      }
    }

    return movieCount.entrySet()
            .stream()
            .sorted((a, b) -> {
              int countCompare = b.getValue().compareTo(a.getValue());
              return countCompare != 0 ? countCompare : a.getKey().compareTo(b.getKey());
            })
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
  }
}
