package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateReservationDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class UpdateReservationDto {

  /**
   * Запрашиваемое состояние; применяется только после проверки оплаты сервером.
   */
  public enum StatusEnum {
    PAID("PAID");

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

  private String paymentReference;

  public UpdateReservationDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UpdateReservationDto(StatusEnum status, String paymentReference) {
    this.status = status;
    this.paymentReference = paymentReference;
  }

  public UpdateReservationDto status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Запрашиваемое состояние; применяется только после проверки оплаты сервером.
   * @return status
   */
  @NotNull 
  @Schema(name = "status", description = "Запрашиваемое состояние; применяется только после проверки оплаты сервером.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public UpdateReservationDto paymentReference(String paymentReference) {
    this.paymentReference = paymentReference;
    return this;
  }

  /**
   * Непрозрачный идентификатор подтверждённой оплаты у провайдера; не данные карты.
   * @return paymentReference
   */
  @NotNull @Pattern(regexp = "^[A-Za-z0-9_-]+$") @Size(min = 1, max = 128) 
  @Schema(name = "paymentReference", example = "pay_example_123", description = "Непрозрачный идентификатор подтверждённой оплаты у провайдера; не данные карты.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentReference")
  public String getPaymentReference() {
    return paymentReference;
  }

  public void setPaymentReference(String paymentReference) {
    this.paymentReference = paymentReference;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateReservationDto updateReservationDto = (UpdateReservationDto) o;
    return Objects.equals(this.status, updateReservationDto.status) &&
        Objects.equals(this.paymentReference, updateReservationDto.paymentReference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, paymentReference);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateReservationDto {\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    paymentReference: ").append(toIndentedString(paymentReference)).append("\n");
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

