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
 * MovieDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class MovieDto {

  private UUID id;

  private String title;

  private Integer durationMinutes;

  /**
   * Gets or Sets ageRating
   */
  public enum AgeRatingEnum {
    _0_("0+"),
    
    _6_("6+"),
    
    _12_("12+"),
    
    _16_("16+"),
    
    _18_("18+");

    private String value;

    AgeRatingEnum(String value) {
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
    public static AgeRatingEnum fromValue(String value) {
      for (AgeRatingEnum b : AgeRatingEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private AgeRatingEnum ageRating;

  public MovieDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MovieDto(UUID id, String title, Integer durationMinutes, AgeRatingEnum ageRating) {
    this.id = id;
    this.title = title;
    this.durationMinutes = durationMinutes;
    this.ageRating = ageRating;
  }

  public MovieDto id(UUID id) {
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

  public MovieDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", example = "Интерстеллар", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public MovieDto durationMinutes(Integer durationMinutes) {
    this.durationMinutes = durationMinutes;
    return this;
  }

  /**
   * Get durationMinutes
   * minimum: 1
   * @return durationMinutes
   */
  @NotNull @Min(1) 
  @Schema(name = "durationMinutes", example = "169", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("durationMinutes")
  public Integer getDurationMinutes() {
    return durationMinutes;
  }

  public void setDurationMinutes(Integer durationMinutes) {
    this.durationMinutes = durationMinutes;
  }

  public MovieDto ageRating(AgeRatingEnum ageRating) {
    this.ageRating = ageRating;
    return this;
  }

  /**
   * Get ageRating
   * @return ageRating
   */
  @NotNull 
  @Schema(name = "ageRating", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ageRating")
  public AgeRatingEnum getAgeRating() {
    return ageRating;
  }

  public void setAgeRating(AgeRatingEnum ageRating) {
    this.ageRating = ageRating;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MovieDto movieDto = (MovieDto) o;
    return Objects.equals(this.id, movieDto.id) &&
        Objects.equals(this.title, movieDto.title) &&
        Objects.equals(this.durationMinutes, movieDto.durationMinutes) &&
        Objects.equals(this.ageRating, movieDto.ageRating);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, durationMinutes, ageRating);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MovieDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    durationMinutes: ").append(toIndentedString(durationMinutes)).append("\n");
    sb.append("    ageRating: ").append(toIndentedString(ageRating)).append("\n");
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

