package br.com.jota.finance.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Goal")
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_goals")
    private Long idGoal;
    private String description;
    private BigDecimal targetValue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Goal() {
    }

    public Goal(String description, BigDecimal targetValue, LocalDateTime startDate, LocalDateTime endDate, User user) {
        this.description = description;
        this.targetValue = targetValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public Long getIdGoal() {
        return idGoal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(BigDecimal targetValue) {
        this.targetValue = targetValue;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return Objects.equals(idGoal, goal.idGoal);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idGoal);
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id_goal=" + idGoal +
                ", description='" + description + '\'' +
                ", targetValue=" + targetValue +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", idUser=" + user +
                '}';
    }
}
