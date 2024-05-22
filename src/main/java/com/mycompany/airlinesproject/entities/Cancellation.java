package com.mycompany.airlinesproject.entities;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "cancellation")
public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "canc_id")
    private Long cancellationId;

    @Column(name = "ticket_id")
    private String ticketId;

    @Column(name = "code")
    private String code;

    @Column(name = "canc_date")
    private Date cancellationDate;

    // Constructors, getters, and setters

    public Cancellation(String ticketId, String code, Date cancellationDate) {
        this.ticketId = ticketId;
        this.code = code;
        this.cancellationDate = cancellationDate;
    }

    public Cancellation() {

    }

    public Cancellation(Long canc_id,String ticketId, String code, Date cancellationDate) {
        this.ticketId = ticketId;
        this.code = code;
        this.cancellationDate = cancellationDate;
        this.cancellationId = canc_id;
    }

    // Getters and setters

    public Long getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(Long cancellationId) {
        this.cancellationId = cancellationId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Date cancellationDate) {
        this.cancellationDate = cancellationDate;
    }
}