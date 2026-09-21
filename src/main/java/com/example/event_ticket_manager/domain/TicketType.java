package com.example.event_ticket_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.AutoPopulatingList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="ticketTypes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketType {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
@Column(name="name",nullable = false)
    private String name;
@Column(name = "price",nullable = false)
    private double price;
@Column(name = "totalAvailable")
    private Integer totalAvailable;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "eventId")
    private Event event;
@OneToMany(mappedBy = "ticketType",cascade = CascadeType.ALL)
private List<Ticket> ticketTypes=new ArrayList<>();


    @CreatedDate
    @Column(name="createdAt",updatable = false,nullable = false)
private LocalDateTime createdAt;
@LastModifiedDate
@Column(name = "updatedAt",nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketType that = (TicketType) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(totalAvailable, that.totalAvailable) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, totalAvailable, createdAt, updatedAt);
    }
}
