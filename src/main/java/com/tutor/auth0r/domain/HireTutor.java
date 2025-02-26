package com.tutor.auth0r.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tutor.auth0r.domain.enumeration.HireStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A HireTutor.
 */
@Entity
@Table(name = "hire_tutor")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HireTutor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_hire")
    private Integer timeHire;

    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private HireStatus status;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hireTutor")
    @JsonIgnoreProperties(value = { "thirdPartyTransactions", "hireTutor", "wallet" }, allowSetters = true)
    private Set<WalletTransaction> walletTransactions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "tutor", "userVerify", "user", "hireTutors", "reports", "wallet", "ratings" }, allowSetters = true)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "tutorDetails", "hireTutors", "hiringHours", "reports", "ratings", "appUser" }, allowSetters = true)
    private Tutor tutor;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HireTutor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeHire() {
        return this.timeHire;
    }

    public HireTutor timeHire(Integer timeHire) {
        this.setTimeHire(timeHire);
        return this;
    }

    public void setTimeHire(Integer timeHire) {
        this.timeHire = timeHire;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public HireTutor totalPrice(Double totalPrice) {
        this.setTotalPrice(totalPrice);
        return this;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public HireStatus getStatus() {
        return this.status;
    }

    public HireTutor status(HireStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(HireStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartAt() {
        return this.startAt;
    }

    public HireTutor startAt(LocalDateTime startAt) {
        this.setStartAt(startAt);
        return this;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return this.endAt;
    }

    public HireTutor endAt(LocalDateTime endAt) {
        this.setEndAt(endAt);
        return this;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public Set<WalletTransaction> getWalletTransactions() {
        return this.walletTransactions;
    }

    public void setWalletTransactions(Set<WalletTransaction> walletTransactions) {
        if (this.walletTransactions != null) {
            this.walletTransactions.forEach(i -> i.setHireTutor(null));
        }
        if (walletTransactions != null) {
            walletTransactions.forEach(i -> i.setHireTutor(this));
        }
        this.walletTransactions = walletTransactions;
    }

    public HireTutor walletTransactions(Set<WalletTransaction> walletTransactions) {
        this.setWalletTransactions(walletTransactions);
        return this;
    }

    public HireTutor addWalletTransaction(WalletTransaction walletTransaction) {
        this.walletTransactions.add(walletTransaction);
        walletTransaction.setHireTutor(this);
        return this;
    }

    public HireTutor removeWalletTransaction(WalletTransaction walletTransaction) {
        this.walletTransactions.remove(walletTransaction);
        walletTransaction.setHireTutor(null);
        return this;
    }

    public AppUser getAppUser() {
        return this.appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public HireTutor appUser(AppUser appUser) {
        this.setAppUser(appUser);
        return this;
    }

    public Tutor getTutor() {
        return this.tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public HireTutor tutor(Tutor tutor) {
        this.setTutor(tutor);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HireTutor)) {
            return false;
        }
        return getId() != null && getId().equals(((HireTutor) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HireTutor{" +
            "id=" + getId() +
            ", timeHire=" + getTimeHire() +
            ", totalPrice=" + getTotalPrice() +
            ", status='" + getStatus() + "'" +
            ", startAt='" + getStartAt() + "'" +
            ", endAt='" + getEndAt() + "'" +
            "}";
    }
}
