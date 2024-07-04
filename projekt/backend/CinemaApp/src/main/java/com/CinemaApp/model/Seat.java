package com.CinemaApp.model;


import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "row")
    private int row;

    @Column(name = "coll")
    private int col;

    @Column(name = "hall_id")
    private Long hallId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Long Hall;

    public Seat(Long hallId, int col, int row, boolean status, long id) {
        this.hallId = hallId;
        this.col = col;
        this.row = row;
        this.status = status;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public Long getHall() {
        return Hall;
    }

    public void setHall(Long hall) {
        Hall = hall;
    }
}
