package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * FieldViolationDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class FieldViolationDto {

  private String field;

  private String message;

  public FieldViolationDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FieldViolationDto(String field, String message) {
    this.field = field;
    this.message = message;
  }

  public FieldViolationDto field(String field) {
    this.field = field;
    return this;
  }

  /**
   * Get field
   * @return field
   */
  @NotNull 
  @Schema(name = "field", example = "seatIds", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("field")
  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public FieldViolationDto message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  @NotNull 
  @Schema(name = "message", example = "size must be between 1 and 10", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FieldViolationDto fieldViolationDto = (FieldViolationDto) o;
    return Objects.equals(this.field, fieldViolationDto.field) &&
        Objects.equals(this.message, fieldViolationDto.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FieldViolationDto {\n");
    sb.append("    field: ").append(toIndentedString(field)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

