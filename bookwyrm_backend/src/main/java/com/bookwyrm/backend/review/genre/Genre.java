package com.bookwyrm.backend.review.genre;

public enum Genre {
    OVERALL(1),
    ADVENTURE(1),
    ACTION(1),
    BEDTIME(1),
    COMEDY(1),
    CHILDREN(1),
    DRAMA(1),
    FANTASY(1),
    GOTHIC(1),
    HORROR(1),
    HISTORICAL_FICTION(1),
    ALTERNATIVE_HISTORY(1),
    MYSTERY(1),
    ROMANCE(1),
    SCIENCE_FICTION(1),
    THRILLER(1);

    private int rating;

    private Genre(int rating){
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
