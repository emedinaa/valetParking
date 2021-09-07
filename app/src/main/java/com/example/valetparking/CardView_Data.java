package com.example.valetparking;

public class CardView_Data {
    private String card_text_view;
    private int card_image_view;

    //Constructor
    public CardView_Data(String card_text_view, int card_image_view) {
        this.card_text_view = card_text_view;
        this.card_image_view = card_image_view;
    }

    //Metodos Getter y Setter
    public String getCard_text_view() {
        return card_text_view;
    }

    public void setCard_text_view(String card_text_view) {
        this.card_text_view = card_text_view;
    }

    public int getCard_image_view() {
        return card_image_view;
    }

    public void setCard_image_view(int card_image_view) {
        this.card_image_view = card_image_view;
    }
}
