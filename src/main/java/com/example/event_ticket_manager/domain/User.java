package com.example.event_ticket_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

// jpa entity
@Entity
@Table(name="users")
// why not use @Data?
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// what does this do?
@Builder

public class User {
    @Id
    @Column(name="id",updatable = false,nullable = false)
    private UUID id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="email",nullable = false)
    private String email;

    @OneToMany(mappedBy = "organizer",cascade = CascadeType.ALL)
    private List<Event> organizedEvents=new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "userAttendingEvents",joinColumns = @JoinColumn(name = "UserId"),inverseJoinColumns = @JoinColumn(name = "EventId"))
    private List<Event> attendingEvents=new ArrayList<>();
    @ManyToMany
    @JoinTable(name="userStaffingEvents",
    joinColumns=@JoinColumn(name="userId"),
    inverseJoinColumns = @JoinColumn(name="eventId"))
    private List<Event> staffingEvents=new ArrayList<>();
    @CreatedDate
    @Column(name="createdAt",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt",nullable = false,updatable = true)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, createdAt, updatedAt);
    }
}
