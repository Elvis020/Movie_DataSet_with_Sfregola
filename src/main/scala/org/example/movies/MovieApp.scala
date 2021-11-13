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

}