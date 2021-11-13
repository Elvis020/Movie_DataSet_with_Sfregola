package org.example.movies.entities

import java.time.LocalDate
import org.slf4j.LoggerFactory



case class Movie(genres: List[Genre],
                 id: Int,
                 imdbId: String,
                 originalLanguage: String,
                 originalTitle: String,
                 title: String,
                 overview: String,
                 popularity: Option[Float],
                 releaseDate: Option[LocalDate],
                 revenue: Int,
                 budget: Int,
                 duration: Option[Double],
                 voteAverage: Float,
                 voteCount: Float)

object Movie {
  import Parsers._

  private val logger = LoggerFactory.getLogger(this.getClass)

  def parse(row: Map[String, String]): Option[Movie] = {
    // Mandatory fields
    val movie = for{
      genres <- parseGenres(row,"genres")
      id <- parseInt(row,"id")
      imdbId <- parseString(row,"imdb_id")
      originalLanguage <- parseString(row,"original_language")
      originalTitle <- parseString(row,"original_title")
      overview <- parseString(row,"overview")
      budget <- parseInt(row,"budget")
    } yield{
      // Nullable fields
        val popularity = parseFloat(row,"popularity")
        val releaseDate = parseLocalDate(row,"release_date")
        val runtimeInMinutes = parseDouble(row,"runtime")


      // Nullable fields with default
      val revenue = parseInt(row,"revenue").getOrElse[Int](0)
      val title = parseString(row,"title").getOrElse[String](originalTitle)
      val voteAverage = parseFloat(row,"vote_average").getOrElse[Float](0)
      val voteCount = parseFloat(row,"vote_count").getOrElse[Float](0)

      Movie(
        genres,
        id,
        imdbId,
        originalLanguage,
        originalTitle,
        title,
        overview,
        popularity,
        releaseDate,
        revenue,
        budget,
        runtimeInMinutes,
        voteAverage,
        voteCount
      )
    }
    if(movie.isEmpty) logger.warn("Skipping malformed movie row")
    movie
  }
}