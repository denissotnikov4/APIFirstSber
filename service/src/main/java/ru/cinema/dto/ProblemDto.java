package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import ru.cinema.dto.FieldViolationDto;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RFC 9457 Problem Details; дополнительные поля code и errors расширяют стандарт.
 */

@Schema(name = "ProblemDto", description = "RFC 9457 Problem Details; дополнительные поля code и errors расширяют стандарт.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class ProblemDto {

  private URI type;

  private String title;

  private Integer status;

  private String detail;

  private String instance;

  private String code;

  @Valid
  private List<@Valid FieldViolationDto> errors = new ArrayList<>();

  public ProblemDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProblemDto(URI type, String title, Integer status, String detail, String instance, String code) {
    this.type = type;
    this.title = title;
    this.status = status;
    this.detail = detail;
    this.instance = instance;
    this.code = code;
  }

  public ProblemDto type(URI type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @NotNull @Valid 
  @Schema(name = "type", example = "about:blank", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public URI getType() {
    return type;
  }

  public void setType(URI type) {
    this.type = type;
  }

  public ProblemDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", example = "Not Found", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProblemDto status(Integer status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * minimum: 400
   * maximum: 599
   * @return status
   */
  @NotNull @Min(400) @Max(599) 
  @Schema(name = "status", example = "404", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public ProblemDto detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Безопасное описание без SQL, stack trace и внутренних данных.
   * @return detail
   */
  @NotNull 
  @Schema(name = "detail", example = "Бронь не найдена.", description = "Безопасное описание без SQL, stack trace и внутренних данных.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("detail")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public ProblemDto instance(String instance) {
    this.instance = instance;
    return this;
  }

  /**
   * Путь запроса без query-параметров и секретов.
   * @return instance
   */
  @NotNull 
  @Schema(name = "instance", example = "/api/v1/reservations/550e8400-e29b-41d4-a716-446655440000", description = "Путь запроса без query-параметров и секретов.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("instance")
  public String getInstance() {
    return instance;
  }

  public void setInstance(String instance) {
    this.instance = instance;
  }

  public ProblemDto code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Стабильный машинный код ошибки.
   * @return code
   */
  @NotNull 
  @Schema(name = "code", example = "RESERVATION_NOT_FOUND", description = "Стабильный машинный код ошибки.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ProblemDto errors(List<@Valid FieldViolationDto> errors) {
    this.errors = errors;
    return this;
  }

  public ProblemDto addErrorsItem(FieldViolationDto errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Ошибки валидации без отклонённых значений.
   * @return errors
   */
  @Valid 
  @Schema(name = "errors", description = "Ошибки валидации без отклонённых значений.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errors")
  public List<@Valid FieldViolationDto> getErrors() {
    return errors;
  }

  public void setErrors(List<@Valid FieldViolationDto> errors) {
    this.errors = errors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProblemDto problemDto = (ProblemDto) o;
    return Objects.equals(this.type, problemDto.type) &&
        Objects.equals(this.title, problemDto.title) &&
        Objects.equals(this.status, problemDto.status) &&
        Objects.equals(this.detail, problemDto.detail) &&
        Objects.equals(this.instance, problemDto.instance) &&
        Objects.equals(this.code, problemDto.code) &&
        Objects.equals(this.errors, problemDto.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, title, status, detail, instance, code, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProblemDto {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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

