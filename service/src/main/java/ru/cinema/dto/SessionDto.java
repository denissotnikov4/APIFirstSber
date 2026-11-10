package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SessionDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class SessionDto {

  private UUID id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime startsAt;

  private String hallName;

  private Long priceKopecks;

  public SessionDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SessionDto(UUID id, OffsetDateTime startsAt, String hallName, Long priceKopecks) {
    this.id = id;
    this.startsAt = startsAt;
    this.hallName = hallName;
    this.priceKopecks = priceKopecks;
  }

  public SessionDto id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public SessionDto startsAt(OffsetDateTime startsAt) {
    this.startsAt = startsAt;
    return this;
  }

  /**
   * Get startsAt
   * @return startsAt
   */
  @NotNull @Valid 
  @Schema(name = "startsAt", example = "2026-11-15T19:00+05:00", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("startsAt")
  public OffsetDateTime getStartsAt() {
    return startsAt;
  }

  public void setStartsAt(OffsetDateTime startsAt) {
    this.startsAt = startsAt;
  }

  public SessionDto hallName(String hallName) {
    this.hallName = hallName;
    return this;
  }

  /**
   * Get hallName
   * @return hallName
   */
  @NotNull 
  @Schema(name = "hallName", example = "Зал 1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("hallName")
  public String getHallName() {
    return hallName;
  }

  public void setHallName(String hallName) {
    this.hallName = hallName;
  }

  public SessionDto priceKopecks(Long priceKopecks) {
    this.priceKopecks = priceKopecks;
    return this;
  }

  /**
   * Одинаковая цена за каждое место этого сеанса, в копейках (RUB).
   * minimum: 0
   * @return priceKopecks
   */
  @NotNull @Min(0L) 
  @Schema(name = "priceKopecks", example = "45000", description = "Одинаковая цена за каждое место этого сеанса, в копейках (RUB).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("priceKopecks")
  public Long getPriceKopecks() {
    return priceKopecks;
  }

  public void setPriceKopecks(Long priceKopecks) {
    this.priceKopecks = priceKopecks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionDto sessionDto = (SessionDto) o;
    return Objects.equals(this.id, sessionDto.id) &&
        Objects.equals(this.startsAt, sessionDto.startsAt) &&
        Objects.equals(this.hallName, sessionDto.hallName) &&
        Objects.equals(this.priceKopecks, sessionDto.priceKopecks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, startsAt, hallName, priceKopecks);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    startsAt: ").append(toIndentedString(startsAt)).append("\n");
    sb.append("    hallName: ").append(toIndentedString(hallName)).append("\n");
    sb.append("    priceKopecks: ").append(toIndentedString(priceKopecks)).append("\n");
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

