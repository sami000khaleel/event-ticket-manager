package com.example.event_ticket_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ticketValidations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketValidation {
@Id
@Column(name = "id",nullable = false,updatable = false)
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
@Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketValidationStatusEnum status;

@Column(name = "validationMethod",nullable = false)
@Enumerated(EnumType.STRING)
private TicketValidationMethod validationMethod;

    @CreatedDate
    @Column(name="createdAt",updatable = false,nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updatedAt",nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketValidation that = (TicketValidation) o;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createdAt, updatedAt);
    }
}
