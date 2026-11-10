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
import ru.cinema.dto.SessionDto;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MovieScheduleDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class MovieScheduleDto {

  private MovieDto movie;

  @Valid
  private List<@Valid SessionDto> sessions = new ArrayList<>();

  public MovieScheduleDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MovieScheduleDto(MovieDto movie, List<@Valid SessionDto> sessions) {
    this.movie = movie;
    this.sessions = sessions;
  }

  public MovieScheduleDto movie(MovieDto movie) {
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

  public MovieScheduleDto sessions(List<@Valid SessionDto> sessions) {
    this.sessions = sessions;
    return this;
  }

  public MovieScheduleDto addSessionsItem(SessionDto sessionsItem) {
    if (this.sessions == null) {
      this.sessions = new ArrayList<>();
    }
    this.sessions.add(sessionsItem);
    return this;
  }

  /**
   * Get sessions
   * @return sessions
   */
  @NotNull @Valid 
  @Schema(name = "sessions", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sessions")
  public List<@Valid SessionDto> getSessions() {
    return sessions;
  }

  public void setSessions(List<@Valid SessionDto> sessions) {
    this.sessions = sessions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MovieScheduleDto movieScheduleDto = (MovieScheduleDto) o;
    return Objects.equals(this.movie, movieScheduleDto.movie) &&
        Objects.equals(this.sessions, movieScheduleDto.sessions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(movie, sessions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MovieScheduleDto {\n");
    sb.append("    movie: ").append(toIndentedString(movie)).append("\n");
    sb.append("    sessions: ").append(toIndentedString(sessions)).append("\n");
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

