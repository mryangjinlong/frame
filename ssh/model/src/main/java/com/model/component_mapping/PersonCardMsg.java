package com.model.component_mapping;

/**
 * 是Person的一部份所以这个对象就不需要主键了
 */
public class PersonCardMsg {

    private  int cardId;
    private String address;
    private String cardName;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
