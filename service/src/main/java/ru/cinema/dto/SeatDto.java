package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SeatDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class SeatDto {

  private UUID id;

  private Integer row;

  private Integer number;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    AVAILABLE("AVAILABLE"),
    
    HELD("HELD"),
    
    SOLD("SOLD");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  public SeatDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SeatDto(UUID id, Integer row, Integer number, StatusEnum status) {
    this.id = id;
    this.row = row;
    this.number = number;
    this.status = status;
  }

  public SeatDto id(UUID id) {
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

  public SeatDto row(Integer row) {
    this.row = row;
    return this;
  }

  /**
   * Ряд от экрана; экран перед первым рядом.
   * minimum: 1
   * @return row
   */
  @NotNull @Min(1) 
  @Schema(name = "row", description = "Ряд от экрана; экран перед первым рядом.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("row")
  public Integer getRow() {
    return row;
  }

  public void setRow(Integer row) {
    this.row = row;
  }

  public SeatDto number(Integer number) {
    this.number = number;
    return this;
  }

  /**
   * Номер места слева направо при взгляде на экран.
   * minimum: 1
   * @return number
   */
  @NotNull @Min(1) 
  @Schema(name = "number", description = "Номер места слева направо при взгляде на экран.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("number")
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public SeatDto status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @NotNull 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SeatDto seatDto = (SeatDto) o;
    return Objects.equals(this.id, seatDto.id) &&
        Objects.equals(this.row, seatDto.row) &&
        Objects.equals(this.number, seatDto.number) &&
        Objects.equals(this.status, seatDto.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, row, number, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SeatDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    row: ").append(toIndentedString(row)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

