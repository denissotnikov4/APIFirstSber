package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.UUID;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TicketDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class TicketDto {

  private UUID id;

  private UUID seatId;

  private String admissionCode;

  public TicketDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TicketDto(UUID id, UUID seatId, String admissionCode) {
    this.id = id;
    this.seatId = seatId;
    this.admissionCode = admissionCode;
  }

  public TicketDto id(UUID id) {
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

  public TicketDto seatId(UUID seatId) {
    this.seatId = seatId;
    return this;
  }

  /**
   * Get seatId
   * @return seatId
   */
  @NotNull @Valid 
  @Schema(name = "seatId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("seatId")
  public UUID getSeatId() {
    return seatId;
  }

  public void setSeatId(UUID seatId) {
    this.seatId = seatId;
  }

  public TicketDto admissionCode(String admissionCode) {
    this.admissionCode = admissionCode;
    return this;
  }

  /**
   * Непрозрачный код для контроля входа; доступен только владельцу билета.
   * @return admissionCode
   */
  @NotNull 
  @Schema(name = "admissionCode", description = "Непрозрачный код для контроля входа; доступен только владельцу билета.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("admissionCode")
  public String getAdmissionCode() {
    return admissionCode;
  }

  public void setAdmissionCode(String admissionCode) {
    this.admissionCode = admissionCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketDto ticketDto = (TicketDto) o;
    return Objects.equals(this.id, ticketDto.id) &&
        Objects.equals(this.seatId, ticketDto.seatId) &&
        Objects.equals(this.admissionCode, ticketDto.admissionCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, seatId, admissionCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TicketDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    seatId: ").append(toIndentedString(seatId)).append("\n");
    sb.append("    admissionCode: ").append(toIndentedString(admissionCode)).append("\n");
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

