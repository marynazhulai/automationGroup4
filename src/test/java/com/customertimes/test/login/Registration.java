package com.customertimes.test.login;

public class Registration {
    //У меня 3 варианта css селектора. Тут я все три прописала, а дальше выбирала самый "короткий"
    //И 2 варианта Xpath

    private String registrationEmailCss1 = "mat-card input[aria-label=\"Email address field\"]";
    private String registrationEmailCss2 = "mat-card [aria-label=\"Email address field\"]";
    private String registrationEmailCss3 = "[aria-label=\"Email address field\"]";
    private String registrationEmailXpath1 = "//mat-card//*[@aria-label='Email address field']";
    private String registrationEmailXpath2 = "//*[@aria-label='Email address field']";

    private String registrationPasswordCss = "[aria-label=\"Field for the password\"]";
    private String registrationPasswordXpath = "//*[@aria-label='Field for the password']";

    private String registrationRepeatPasswordCss = "[aria-label=\"Field to confirm the password\"]";
    private String registrationRepeatPasswordXpath = "//*[@aria-label='Field to confirm the password']";

    private String registrationSecurityQuestionCss = "[role=combobox]";
    private String registrationSecurityQuestionXpath = "//*[@role='combobox']";

    private String registrationAnswerCss = "[aria-label=\"Field for the answer to the security question\"]";
    private String registrationAnswerXpath ="//*[@aria-label='Field for the answer to the security question']";

    private String registrationRegisterCss = "[type=submit]";
    private String registrationRegisterXpath = "//*[@type='submit']";
}
