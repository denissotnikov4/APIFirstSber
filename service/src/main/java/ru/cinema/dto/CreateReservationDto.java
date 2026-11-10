package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateReservationDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class CreateReservationDto {

  private UUID sessionId;

  @Valid
  private Set<UUID> seatIds = new LinkedHashSet<>();

  public CreateReservationDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateReservationDto(UUID sessionId, Set<UUID> seatIds) {
    this.sessionId = sessionId;
    this.seatIds = seatIds;
  }

  public CreateReservationDto sessionId(UUID sessionId) {
    this.sessionId = sessionId;
    return this;
  }

  /**
   * Get sessionId
   * @return sessionId
   */
  @NotNull @Valid 
  @Schema(name = "sessionId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sessionId")
  public UUID getSessionId() {
    return sessionId;
  }

  public void setSessionId(UUID sessionId) {
    this.sessionId = sessionId;
  }

  public CreateReservationDto seatIds(Set<UUID> seatIds) {
    this.seatIds = seatIds;
    return this;
  }

  public CreateReservationDto addSeatIdsItem(UUID seatIdsItem) {
    if (this.seatIds == null) {
      this.seatIds = new LinkedHashSet<>();
    }
    this.seatIds.add(seatIdsItem);
    return this;
  }

  /**
   * Get seatIds
   * @return seatIds
   */
  @NotNull @Valid @Size(min = 1, max = 10) 
  @Schema(name = "seatIds", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("seatIds")
  public Set<UUID> getSeatIds() {
    return seatIds;
  }

  @JsonDeserialize(as = LinkedHashSet.class)
  public void setSeatIds(Set<UUID> seatIds) {
    this.seatIds = seatIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateReservationDto createReservationDto = (CreateReservationDto) o;
    return Objects.equals(this.sessionId, createReservationDto.sessionId) &&
        Objects.equals(this.seatIds, createReservationDto.seatIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionId, seatIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateReservationDto {\n");
    sb.append("    sessionId: ").append(toIndentedString(sessionId)).append("\n");
    sb.append("    seatIds: ").append(toIndentedString(seatIds)).append("\n");
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

