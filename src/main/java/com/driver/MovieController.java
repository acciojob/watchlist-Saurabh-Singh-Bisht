package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    private MovieService movieService = new MovieService();
    @PostMapping("add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("Movie Added Successfully", HttpStatus.OK);
    }
    @PostMapping("add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("Director Added Successfully", HttpStatus.OK);
    }
    @PutMapping("add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie, @RequestParam String director){
        movieService.addMovieDirectorPair(movie, director);
        return new ResponseEntity<>("Director Movie Pair Added", HttpStatus.OK);
    }
    @GetMapping("get-movie-by-name/{movie}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String movie){
        try {
            Movie movie1 = movieService.getMovieByName(movie);
            return new ResponseEntity<>(movie1, HttpStatus.OK);
        }
        catch (MovieNotFoundException ex){
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("get-director-by-name/{director}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String director){
        try{
            Director director1 = movieService.getDirectorByName(director);
            return new ResponseEntity<>(director1, HttpStatus.OK);
        }
        catch (DirectorNotFoundException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> moviesList = movieService.getMovieByDirectorName(director);
        return new ResponseEntity<>(moviesList, HttpStatus.OK);
    }
    @GetMapping("get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> moviesList = movieService.getAllMovies();
        return new ResponseEntity<>(moviesList, HttpStatus.OK);
    }
    @DeleteMapping("delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director){
        movieService.deleteDirectorByName(director);
        return new ResponseEntity<>("Current Director Deleted", HttpStatus.OK);
    }
    @DeleteMapping("delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All Directors are deleted", HttpStatus.OK);
    }
}
