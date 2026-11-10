package ru.cinema.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import ru.cinema.dto.TicketDto;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ReservationDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class ReservationDto {

  private UUID id;

  private UUID sessionId;

  @Valid
  private Set<UUID> seatIds = new LinkedHashSet<>();

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    HELD("HELD"),
    
    PAID("PAID"),
    
    CANCELLED("CANCELLED"),
    
    EXPIRED("EXPIRED");

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

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime expiresAt;

  private Long totalKopecks;

  @Valid
  private List<@Valid TicketDto> tickets = new ArrayList<>();

  public ReservationDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ReservationDto(UUID id, UUID sessionId, Set<UUID> seatIds, StatusEnum status, OffsetDateTime expiresAt, Long totalKopecks, List<@Valid TicketDto> tickets) {
    this.id = id;
    this.sessionId = sessionId;
    this.seatIds = seatIds;
    this.status = status;
    this.expiresAt = expiresAt;
    this.totalKopecks = totalKopecks;
    this.tickets = tickets;
  }

  public ReservationDto id(UUID id) {
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

  public ReservationDto sessionId(UUID sessionId) {
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

  public ReservationDto seatIds(Set<UUID> seatIds) {
    this.seatIds = seatIds;
    return this;
  }

  public ReservationDto addSeatIdsItem(UUID seatIdsItem) {
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

  public ReservationDto status(StatusEnum status) {
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

  public ReservationDto expiresAt(OffsetDateTime expiresAt) {
    this.expiresAt = expiresAt;
    return this;
  }

  /**
   * Срок оплаты; после него HELD становится EXPIRED и места освобождаются. Для PAID срок больше не применяется.
   * @return expiresAt
   */
  @NotNull @Valid 
  @Schema(name = "expiresAt", description = "Срок оплаты; после него HELD становится EXPIRED и места освобождаются. Для PAID срок больше не применяется.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("expiresAt")
  public OffsetDateTime getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(OffsetDateTime expiresAt) {
    this.expiresAt = expiresAt;
  }

  public ReservationDto totalKopecks(Long totalKopecks) {
    this.totalKopecks = totalKopecks;
    return this;
  }

  /**
   * Зафиксированная сервером сумма брони в копейках (RUB).
   * minimum: 0
   * @return totalKopecks
   */
  @NotNull @Min(0L) 
  @Schema(name = "totalKopecks", description = "Зафиксированная сервером сумма брони в копейках (RUB).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("totalKopecks")
  public Long getTotalKopecks() {
    return totalKopecks;
  }

  public void setTotalKopecks(Long totalKopecks) {
    this.totalKopecks = totalKopecks;
  }

  public ReservationDto tickets(List<@Valid TicketDto> tickets) {
    this.tickets = tickets;
    return this;
  }

  public ReservationDto addTicketsItem(TicketDto ticketsItem) {
    if (this.tickets == null) {
      this.tickets = new ArrayList<>();
    }
    this.tickets.add(ticketsItem);
    return this;
  }

  /**
   * Для PAID — один билет на каждое место; для остальных статусов пустой массив.
   * @return tickets
   */
  @NotNull @Valid 
  @Schema(name = "tickets", description = "Для PAID — один билет на каждое место; для остальных статусов пустой массив.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tickets")
  public List<@Valid TicketDto> getTickets() {
    return tickets;
  }

  public void setTickets(List<@Valid TicketDto> tickets) {
    this.tickets = tickets;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservationDto reservationDto = (ReservationDto) o;
    return Objects.equals(this.id, reservationDto.id) &&
        Objects.equals(this.sessionId, reservationDto.sessionId) &&
        Objects.equals(this.seatIds, reservationDto.seatIds) &&
        Objects.equals(this.status, reservationDto.status) &&
        Objects.equals(this.expiresAt, reservationDto.expiresAt) &&
        Objects.equals(this.totalKopecks, reservationDto.totalKopecks) &&
        Objects.equals(this.tickets, reservationDto.tickets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sessionId, seatIds, status, expiresAt, totalKopecks, tickets);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReservationDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    sessionId: ").append(toIndentedString(sessionId)).append("\n");
    sb.append("    seatIds: ").append(toIndentedString(seatIds)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    expiresAt: ").append(toIndentedString(expiresAt)).append("\n");
    sb.append("    totalKopecks: ").append(toIndentedString(totalKopecks)).append("\n");
    sb.append("    tickets: ").append(toIndentedString(tickets)).append("\n");
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

