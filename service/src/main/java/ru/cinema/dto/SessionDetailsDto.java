package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import ru.cinema.dto.MovieDto;
import ru.cinema.dto.SeatDto;
import ru.cinema.dto.SessionDto;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SessionDetailsDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class SessionDetailsDto {

  private SessionDto session;

  private MovieDto movie;

  @Valid
  private List<@Valid SeatDto> seats = new ArrayList<>();

  public SessionDetailsDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SessionDetailsDto(SessionDto session, MovieDto movie, List<@Valid SeatDto> seats) {
    this.session = session;
    this.movie = movie;
    this.seats = seats;
  }

  public SessionDetailsDto session(SessionDto session) {
    this.session = session;
    return this;
  }

  /**
   * Get session
   * @return session
   */
  @NotNull @Valid 
  @Schema(name = "session", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("session")
  public SessionDto getSession() {
    return session;
  }

  public void setSession(SessionDto session) {
    this.session = session;
  }

  public SessionDetailsDto movie(MovieDto movie) {
    this.movie = movie;
    return this;
  }

  /**
   * Get movie
   * @return movie
   */
  @NotNull @Valid 
  @Schema(name = "movie", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("movie")
  public MovieDto getMovie() {
    return movie;
  }

  public void setMovie(MovieDto movie) {
    this.movie = movie;
  }

  public SessionDetailsDto seats(List<@Valid SeatDto> seats) {
    this.seats = seats;
    return this;
  }

  public SessionDetailsDto addSeatsItem(SeatDto seatsItem) {
    if (this.seats == null) {
      this.seats = new ArrayList<>();
    }
    this.seats.add(seatsItem);
    return this;
  }

  /**
   * Все реальные места зала; отсутствующие координаты означают проходы.
   * @return seats
   */
  @NotNull @Valid 
  @Schema(name = "seats", description = "Все реальные места зала; отсутствующие координаты означают проходы.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("seats")
  public List<@Valid SeatDto> getSeats() {
    return seats;
  }

  public void setSeats(List<@Valid SeatDto> seats) {
    this.seats = seats;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionDetailsDto sessionDetailsDto = (SessionDetailsDto) o;
    return Objects.equals(this.session, sessionDetailsDto.session) &&
        Objects.equals(this.movie, sessionDetailsDto.movie) &&
        Objects.equals(this.seats, sessionDetailsDto.seats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(session, movie, seats);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionDetailsDto {\n");
    sb.append("    session: ").append(toIndentedString(session)).append("\n");
    sb.append("    movie: ").append(toIndentedString(movie)).append("\n");
    sb.append("    seats: ").append(toIndentedString(seats)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

