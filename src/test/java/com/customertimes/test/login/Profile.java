package com.customertimes.test.login;

public class Profile {
    private String profileEmailCss = "[type=\"email\"]";
    private String profileEmailXpath = "//*[@type='email']";

    private String profileUsernameCss = "[name=\"username\"]";
    private String profileUsernameXpath = "//*[@name='username']";

    private String profileSetUsernameCss = "[aria-label=\"Button to save/set the username\"]";
    private String profileSetUsernameXpath = "//*[@aria-label='Button to save/set the username']";

    private String profileChooseFileCss = "[type=\"file\"]";
    private String profileChooseFileXpath = "//*[@type='file']";

    private String profileUploadPictureCss = "[aria-label=\"Button to upload the profile picture\"]";
    private String profileUploadPictureXpath = "//*[@aria-label='Button to upload the profile picture']";

    private String profileImageUrlCss = "[name=\"imageUrl\"]";
    private String profileImageUrlXpath = "//*[@name='imageUrl']";

    private String profileLinkImageCss = "[aria-label=\"Button to include image from link\"]";
    private String profileLinkImageXpath = "//*[@aria-label='Button to include image from link']";
}
