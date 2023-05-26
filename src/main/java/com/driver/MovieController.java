package com.driver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movies")
public class MovieController {
    private MovieService movieService = new MovieService();

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity("Movie added successfully", HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("Director added successfully", HttpStatus.CREATED);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie,@RequestParam String director){
        try {
            movieService.addMovieDirectorPair(movie, director);
            return new ResponseEntity<>("Pair Added successfully", HttpStatus.CREATED);
        }
        catch (MovieNotFoundException e){
            return new ResponseEntity<>("Movie Not Present", HttpStatus.NOT_FOUND);
        }
        catch (DirectorNotFoundException e){
            return new ResponseEntity<>("Director Not Present", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(String name){
        try{
            Movie movie = movieService.getMovieByName(name);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        }
        catch (MovieNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(String name){
        try{
            Director director = movieService.getDirectorByName(name);
            return new ResponseEntity<>(director, HttpStatus.OK);
        }
        catch (DirectorNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(String name){
        List<String> moviesList = movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<>(moviesList, HttpStatus.OK);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> getAllMovies(){
        List<String> list = movieService.getAllMovies();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(String name){
        movieService.deleteDirector(name);
        return new ResponseEntity<>("Director Deleted Successfully", HttpStatus.OK);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirector();
        return new ResponseEntity<>("Deleted All Directors", HttpStatus.OK);
    }
}
