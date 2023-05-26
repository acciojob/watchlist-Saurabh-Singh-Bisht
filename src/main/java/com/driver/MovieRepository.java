package com.driver;

import javax.swing.*;
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

    public void add(String movie, String director) {
        ArrayList<String> movies = directorMoviePair.getOrDefault(director, new ArrayList<>());
        movies.add(movie);
        directorMoviePair.put(director, movies);
    }

    public Optional<Movie> getMovie(String name) {
        if(movieData.containsKey(name)){
            return Optional.of(movieData.get(name));
        }
        return Optional.empty();
    }

    public Optional<Director> getDirector(String name) {
        if(directorData.containsKey(name)){
            return Optional.of(directorData.get(name));
        }
        return Optional.empty();
    }

    public List<String> getMovieByDirector(String name) {
        return directorMoviePair.getOrDefault(name, new ArrayList<>());
    }

    public List<String> getAllMovies() {
        return new ArrayList<>(movieData.keySet());
    }
    public List<String> getAllDirector(){
        return new ArrayList<>(directorData.keySet());
    }

    public void deleteDirector(String name) {
        directorData.remove(name);
        directorMoviePair.remove(name);
    }
}
