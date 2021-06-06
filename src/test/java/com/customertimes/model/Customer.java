package com.customertimes.model;

public class Customer {
    private String email;
    private String emailForRegistration;
    private String password;
    private String answer;


    public String getEmail() {
        return email;
    }

    public String getEmailForRegistration() {
        return emailForRegistration;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAnswer() {
        return answer;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Customer() {
    }

    private Customer(final Builder builder) {
        email = builder.email;
        emailForRegistration = builder.emailForRegistration;
        password = builder.password;
        answer = builder.answer;
    }

    public static final class Builder {
        private String email;
        private String password;
        private String answer;
        private String emailForRegistration;

        private Builder() {
        }


        public Builder withName(final String val) {
            email = val;
            return this;
        }

        public Builder withPassword(final String val) {
            password = val;
            return this;
        }


        public Builder withAnswer(final String val) {
            answer = val;
            return this;
        }

        public Builder withEmailForRegistration() {
            emailForRegistration = "mz" + System.currentTimeMillis() + "@ctdev.io";
            return this;
        }

        public Builder with(final String val) {
            email = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
