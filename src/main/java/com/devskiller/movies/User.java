package com.devskiller.movies;

import java.util.List;

public record User(int userId, String email, List<Integer> friends) {
}
