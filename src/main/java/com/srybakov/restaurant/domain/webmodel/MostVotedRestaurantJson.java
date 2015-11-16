package com.srybakov.restaurant.domain.webmodel;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
public class MostVotedRestaurantJson {

    private String message = "";
    private String restaurantName;
    private Long voteNumber;

    public MostVotedRestaurantJson() {
    }

    public Long getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(Long voteNumber) {
        this.voteNumber = voteNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
