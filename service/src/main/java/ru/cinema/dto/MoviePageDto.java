package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import ru.cinema.dto.MovieScheduleDto;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MoviePageDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class MoviePageDto {

  @Valid
  private List<@Valid MovieScheduleDto> items = new ArrayList<>();

  private Integer page;

  private Integer size;

  private Long totalElements;

  public MoviePageDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MoviePageDto(List<@Valid MovieScheduleDto> items, Integer page, Integer size, Long totalElements) {
    this.items = items;
    this.page = page;
    this.size = size;
    this.totalElements = totalElements;
  }

  public MoviePageDto items(List<@Valid MovieScheduleDto> items) {
    this.items = items;
    return this;
  }

  public MoviePageDto addItemsItem(MovieScheduleDto itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
   */
  @NotNull @Valid 
  @Schema(name = "items", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("items")
  public List<@Valid MovieScheduleDto> getItems() {
    return items;
  }

  public void setItems(List<@Valid MovieScheduleDto> items) {
    this.items = items;
  }

  public MoviePageDto page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   * minimum: 0
   * @return page
   */
  @NotNull @Min(0) 
  @Schema(name = "page", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("page")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public MoviePageDto size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * minimum: 1
   * maximum: 100
   * @return size
   */
  @NotNull @Min(1) @Max(100) 
  @Schema(name = "size", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("size")
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public MoviePageDto totalElements(Long totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * minimum: 0
   * @return totalElements
   */
  @NotNull @Min(0L) 
  @Schema(name = "totalElements", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("totalElements")
  public Long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MoviePageDto moviePageDto = (MoviePageDto) o;
    return Objects.equals(this.items, moviePageDto.items) &&
        Objects.equals(this.page, moviePageDto.page) &&
        Objects.equals(this.size, moviePageDto.size) &&
        Objects.equals(this.totalElements, moviePageDto.totalElements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, page, size, totalElements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MoviePageDto {\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
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

