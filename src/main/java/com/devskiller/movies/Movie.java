package com.devskiller.movies;

import java.util.List;

public record Movie(String title,
                    String duration,
                    List<String> actors,
                    List<Integer> watchlist,
                    List<Integer> favorites,
                    List<Rating> ratings) {
}
