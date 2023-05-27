package com.driver;

import java.util.List;
import java.util.Optional;

public class MovieService {
    private MovieRepository movieRepository = new MovieRepository();

    public void addMovie(Movie movie) {
        movieRepository.add(movie);
    }

    public void addDirector(Director director) {
        movieRepository.add(director);
    }

    public void addMovieDirectorPair(String movie, String director) throws MovieNotFoundException, DirectorNotFoundException{
        Optional<Movie> movieOpt = movieRepository.getMovie(movie);
        Optional<Director> directorOpt = movieRepository.getDirector(director);
        if(movieOpt.isEmpty()){
            throw new MovieNotFoundException("Movie Not Present in Database");
        }
        if(directorOpt.isEmpty()){
            throw new DirectorNotFoundException("Director Not Present in Database");
        }
        Director director1 = directorOpt.get();
        director1.setNumberOfMovies(director1.getNumberOfMovies()+1);
        movieRepository.add(director1);

        movieRepository.add(movie, director);
    }

    public Movie getMovieByName(String movie) throws MovieNotFoundException{
        Optional<Movie> curr = movieRepository.getMovie(movie);
        if(curr.isPresent()){
            return curr.get();
        }
        throw new MovieNotFoundException("Movie Not Present in Database");
    }

    public Director getDirectorByName(String director) {
        Optional<Director> directorOpt = movieRepository.getDirector(director);
        if(directorOpt.isPresent()){
            return directorOpt.get();
        }
        throw new DirectorNotFoundException("Director Not Present in Database");
    }

    public List<String> getMovieByDirectorName(String director) {
        return movieRepository.getDirectorMovies(director);
    }

    public List<String> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public void deleteDirectorByName(String director) {
        List<String> moviesName = movieRepository.getDirectorMovies(director);
        for(String curr: moviesName){
            movieRepository.deleteMovies(curr);
        }
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirectors() {
        List<String> directorName = movieRepository.getAllDirector();
        for (String curr: directorName){
            deleteDirectorByName(curr);
        }
    }
}
