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

    public void addMovieDirectorPair(String movie, String director) throws MovieNotFoundException,DirectorNotFoundException{
        Optional<Movie> movieOpt = movieRepository.getMovie(movie);
        Optional<Director> directorOpt = movieRepository.getDirector(director);
        if(movieOpt.isEmpty()){
            throw new MovieNotFoundException("Movie Not Present");
        }
        if(directorOpt.isEmpty()){
            throw  new DirectorNotFoundException("Director Not Present");
        }
        Director director1 = directorOpt.get();
        director1.setNumberOfMovies(director1.getNumberOfMovies()+1);
        movieRepository.add(director1);

        movieRepository.add(movie,director);
    }

    public Movie getMovieByName(String name) throws MovieNotFoundException{
        Optional<Movie> movieOpt = movieRepository.getMovie(name);
        if(movieOpt.isPresent()){
            return movieOpt.get();
        }
        throw new MovieNotFoundException("Invalid Movie Name");
    }

    public Director getDirectorByName(String name) {
        Optional<Director> directorOpt = movieRepository.getDirector(name);
        if(directorOpt.isPresent()){
            return directorOpt.get();
        }
        throw new DirectorNotFoundException("Invalid Director Name");
    }

    public List<String> getMoviesByDirectorName(String name) {
        return movieRepository.getMovieByDirector(name);
    }

    public List<String> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public void deleteDirector(String name) {
        movieRepository.deleteDirector(name);
    }

    public void deleteAllDirector() {
        List<String> director = movieRepository.getAllDirector();
        for(String list: director){
            movieRepository.deleteDirector(list);
        }
    }
}
