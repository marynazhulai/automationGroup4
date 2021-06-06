package com.customertimes.model;


public class Product {
    private String title;
    private String description;
    private String price;
    private  String errorMessage;



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public String getPrice() {
        return price;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public static Product.Builder newBuilder() {
        return new Product.Builder();
    }

    public Product() {
    }

    private Product(final Product.Builder builder) {
        title = builder.title;
        description = builder.description;
        price = builder.price;
        errorMessage = builder.errorMessage;
    }

    public static final class Builder {
        private String title;
        private String description;
        private String price;
        private String errorMessage;

        private Builder() {
        }


        public Product.Builder withTitle(final String val) {
            title = val;
            return this;
        }

        public Product.Builder withDescription(final String val) {
            description = val;
            return this;
        }


        public Product.Builder withPrice(final String val) {
            price = val;
            return this;
        }

        public Product.Builder withErrorMessage(final String val) {
            errorMessage = val;
            return this;
        }


        public Product build() {
            return new Product(this);
        }
    }
}
