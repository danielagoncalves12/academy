package com.ctw.workstation.booking.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_BOOKING")
public class Booking extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="bookingIdGenerator")
    @SequenceGenerator(name="bookingIdGenerator", sequenceName="SEQ_BOOKING_ID", allocationSize=1)
    private Long id;

    @Column(name="RACK_ID")
    private Long rackId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RACK_ID", updatable = false, insertable = false, nullable = false)
    private Rack rack;

    @Column(name="REQUESTER_ID")
    private Long requestId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REQUESTER_ID", updatable = false, insertable = false, nullable = false)
    private TeamMember teamMember;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BOOK_FROM", nullable = false)
    private LocalDateTime bookFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BOOK_TO", nullable = false)
    private LocalDateTime bookTo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MODIFIED_AT", nullable = false)
    private LocalDateTime modifiedAt;

    @PrePersist
    public void onPrePersist() {
        this.setCreatedAt(LocalDateTime.now());
        this.setModifiedAt(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public TeamMember getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    public LocalDateTime getBookFrom() {
        return bookFrom;
    }

    public void setBookFrom(LocalDateTime bookFrom) {
        this.bookFrom = bookFrom;
    }

    public LocalDateTime getBookTo() {
        return bookTo;
    }

    public void setBookTo(LocalDateTime bookTo) {
        this.bookTo = bookTo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}