package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MovieRepository {
    private HashMap<String, Movie> movieData = new HashMap<>();
    private HashMap<String, Director> directorData = new HashMap<>();
    private HashMap<String, ArrayList<String>> directorMoviePair = new HashMap<>();

    public void add(Movie movie) {
        movieData.put(movie.getName(), movie);
    }

    public void add(Director director) {
        directorData.put(director.getName(), director);
    }

    public Optional<Movie> getMovie(String movie) {
        if(movieData.containsKey(movie)){
            return Optional.of(movieData.get(movie));
        }
        return Optional.empty();
    }

    public Optional<Director> getDirector(String director) {
        if(directorData.containsKey(director)){
            return Optional.of(directorData.get(director));
        }
        return Optional.empty();
    }

    public void add(String movie, String director) {
        ArrayList<String> movieList = directorMoviePair.getOrDefault(director, new ArrayList<>());
        movieList.add(movie);
        directorMoviePair.put(director, movieList);
    }

    public List<String> getDirectorMovies(String director) {
        return directorMoviePair.getOrDefault(director, new ArrayList<>());
    }

    public List<String> getAllMovies() {
        return new ArrayList<>(movieData.keySet());
    }

    public void deleteMovies(String curr) {
        movieData.remove(curr);
    }

    public void deleteDirector(String director) {
        directorData.remove(director);
        directorMoviePair.remove(director);
    }

    public List<String> getAllDirector() {
        return new ArrayList<>(directorData.keySet());
    }
}
