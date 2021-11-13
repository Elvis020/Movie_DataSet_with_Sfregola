package org.example.movies
import org.example.movies.entities.Movie

import PrintResultHelpers._

/**
 * Project: get-programming-with-scala-lesson33
 * Package: org.example.movies
 *
 * User: elvis 😎😎
 * Date: 13/11/2021
 * Time: 08:57
 *
 * Happy Coding
 */
object MovieApp extends App {
  val dataset = new MoviesDataset("movies_metadata.csv")
  val movies = dataset.movies

  private val unknown = "--"

  // queries will be added here

  printResult(
    "How many movies are there in the dataset?",
    answer = {
      val totCount = movies.size
      s"${totCount} movies"
    }
  )

  printResult(
    "How many of the movies were released in 1987?",
    answer = {
      val countFrom1987 = movies.flatMap(_.releaseDate).count(_.getYear == 1987)
      s"${countFrom1987} movies"
    }
  )

  printResult(
    "How many of the movies were released in 1987?",
    answer = {
      val countFrom1987 = movies.flatMap(_.releaseDate).count(_.getYear == 1987)
      s"${countFrom1987} movies"
    }
  )

  printResult(
    "How many of the movies don't have release dates",
    answer = {
      val countMoviesWithoutReleaseDates = movies.map(_.releaseDate).count(_ == None)
      s"${countMoviesWithoutReleaseDates} movies"
    }
  )
  printResult(
    "What are the top 5 movies per vote average and count?",
    answer = {
      val top5PerVoteAverage = movies.filter(_.voteCount > 50).sortBy(movie => (-movie.voteAverage, -movie.voteCount)).take(5)
            top5PerVoteAverage.map{
              movie => s"[AVG: ${movie.voteAverage}, COUNT:${movie.voteCount}] -> ${movie.title}\n"}
          }.toString()
  )

  printResult(
    "What are the top 5 movies per popularity?",
    answer = {
      val top5PerPopularity = movies.sortBy(mov => -mov.popularity.getOrElse(0f)).take(5)
      top5PerPopularity.map{
        movie => s"[POPULARITY : ${movie.popularity.getOrElse(unknown)} - ${movie.title}]\n"
      }.toString()
    }
  )
  printResult(
    "What are the top 5 non-english movies per popularity?",
    answer = {
      val top5NonEng = movies.filterNot(_.originalLanguage == "en").sortBy(p => -(p.popularity.getOrElse(0f))).take(5)
      top5NonEng.map{
        movie => s"[POPULARITY : ${movie.popularity.getOrElse(unknown)} - ${movie.title}]\n"
      }.toString()
    }
  )
  printResult(
    "5 non-english movies?",
    answer = {
      val top5NonEng = movies.filterNot(_.originalLanguage == "en").take(5)
      top5NonEng.map{
        movie => s"LANG:${movie.originalLanguage}, RELEASE_DATE:${movie.releaseDate.getOrElse(unknown)} ${movie.title}(${movie.originalTitle})]\n"
      }.toString()
    }
  )
  printResult(
    "How many Italian movies are there?",
    answer = {
      val italianMovies = movies.count(_.originalLanguage == "it")
      s"There are ${italianMovies} italian movies"
    }
  )
  printResult(
    "How many German movies are there?",
    answer = {
      val germanMovies = movies.count(_.originalLanguage == "de")
      s"There are ${germanMovies} german movies"
    }
  )
  printResult(
    "Which film made the most profit?",
    answer = {
      val filmWithMostProfit = movies.sortBy(movie => -(movie.revenue - movie.budget)).head
      val formatter = java.text.NumberFormat.getInstance()
      s"${filmWithMostProfit.title} made the most profit of $$${formatter.format(filmWithMostProfit.revenue - filmWithMostProfit.budget)}"
    }
  )
  printResult(
    "What are the genres of movies available in this dataset",
    answer = {
      val listOfGenres = movies.flatMap(_.genres).map(_.name).distinct
      s"List of genres include: ${listOfGenres}"
    }
  )
}