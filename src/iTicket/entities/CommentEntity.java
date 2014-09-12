package iTicket.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment", schema = "", catalog = "iTicket")
public class CommentEntity {
    private int id;
    private String content;
    private Timestamp creationDate;
    private int userId;
    private int ticketId;
    private TicketEntity ticketByTicketId;
    private UserEntity userByUserId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "creation_date")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (id != that.id) return false;
        if (ticketId != that.ticketId) return false;
        if (userId != that.userId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + ticketId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false)
    public TicketEntity getTicketByTicketId() {
        return ticketByTicketId;
    }

    public void setTicketByTicketId(TicketEntity ticketByTicketId) {
        this.ticketByTicketId = ticketByTicketId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
