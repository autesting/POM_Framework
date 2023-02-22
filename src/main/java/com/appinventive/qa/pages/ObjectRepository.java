package com.appinventive.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ObjectRepository  {

	// Locators of Login
	
	@FindBy(xpath="//h2[contains(text(),'Sign In')]")
	public	static WebElement verifyEmailtext;
	
	@FindBy(xpath="//input[@id=\"okta-signin-username\"]")
	public	static WebElement  username;
	
	@FindBy(xpath="//h2[contains(text(),'Password')]")
	public	static WebElement verifyPasswordtext;

    @FindBy(xpath="//input[@id='mat-input-0']")
    public	static WebElement EmailInput;

    @FindBy(xpath="//input[@id='mat-input-1']")
    public	static WebElement PasswordInput;

    @FindBy(xpath="//button[.='Login']")
    public	static WebElement LoginButton;


	@FindBy(xpath="//input[@class=\"password-with-toggle\"]")
	public	static WebElement password;
	
	@FindBy(xpath="//*[@id=\"form6\"]/div[1]/div[1]/div/div/p")
	public	static WebElement password_incorrect_message;
	
	@FindBy(xpath="//*[contains(text(),'Please Log in')]")
	public	static WebElement verifylogintext;
	
	@FindBy(xpath="//button[@class=\"btn btn-lg btn-primary btn-block mb-5\"]")
	public static WebElement LoginBtn;
	
	@FindBy(xpath="//input[@value=\"Next\"]")
	public	static WebElement UsernamePageNextBtn;
	
	@FindBy(xpath="//input[@value=\"Verify\"]")
	public	static WebElement PasswordPageVerifyBtn;
	
	@FindBy(xpath="//*[contains(text(),'Security Question')]")
	public	static WebElement verifySecQuestiontext;
	
	@FindBy(xpath="//input[@name=\"answer\"]")
	public	static WebElement SecurityAnswer;
	
	@FindBy(xpath="//input[@value=\"Verify\"]")
	public static WebElement SecurityAnsPageVerifyBtn;
	
	// All Locators in Email page
	
	@FindBy(id="okta-signin-username")
	public static WebElement Email_Inpt;
	
    @FindBy(xpath="//label[@for='input5']")
    public static WebElement RememberMe_Chkbx;
    
	@FindBy(id="okta-signin-submit")
	public static WebElement Next_Button;
	
	@FindBy(xpath="//a[@class='link help js-help']")
	public	static WebElement Help_Link;
	
    @FindBy(xpath="//a[@class='inline-block margin-l-10']")
    public  static WebElement PrivacyPolicy1;
    
    @FindBy(xpath="//a[contains(text(),'Okta')]")
    public  static WebElement Okta_Link1;
	
	// All locators in Password page
    
    @FindBy(xpath="//*[contains(@id,'input')]")
    public static WebElement Password_Inpt;
    
    @FindBy(xpath="//*[contains(@type,'submit')]")
    public  static WebElement Verify_Button1 ;

    @FindBy(xpath="//a[@class='link goto']")
    public static WebElement SignOut_Link1  ;
    
    @FindBy(xpath="//a[@class='inline-block margin-l-10']")
    public  static WebElement PrivacyPolicy2;

    @FindBy(xpath="//a[contains(text(),'Okta')]")
    public static WebElement Okta_Link2 ;
    
    // All locators in Security question page
    
    @FindBy(xpath="//*[contains(@name,'answer')]")
    public   static WebElement Answer_Inpt;

    @FindBy(xpath="//*[contains(text(),'Do not challenge me on this device again')]")
    public  static WebElement Security_Chkbx;

    @FindBy(xpath="//input[@type='submit']")
    public  static WebElement Verify_Button2 ; 

    @FindBy(xpath="//a[@class='link goto']")
    public  static WebElement SignOut_Link2 ;   
 
    @FindBy(xpath="//a[@class='inline-block margin-l-10']")
    public  static WebElement PrivacyPolicy3 ; 

    @FindBy(xpath="//a[contains(text(),'Okta')]")
    public  static WebElement Okta_Link3 ; 

        
	// All Locators on Application Landing page after login
	
    @FindBy(xpath="//input[@id='applicationSearch']")
    public static WebElement Search_Inpt ; 

    @FindBy(xpath="//img[@src='/file/general/AD-Grid-icon.png']")
    public  static WebElement ADGrid_Icon1 ; 

    @FindBy(xpath="//img[@src='/file/general/AD-Grid-icon.png']")
    public static  WebElement ADGrid_Icon2 ; 
    
    @FindBy(xpath="//img[@src='/file/general/AD-Help-icon.png']")
    public static   WebElement ADHelp_Icon ;
    
    @FindBy(xpath="//img[@src='/file/general/AD-Profile-User-icon.png']")
    public static WebElement ADProfileUser_Icon ;
    
    @FindBy(xpath="//a[contains(text(),'Edit Profile')]")
    public static WebElement Edit_profile ;

    @FindBy(xpath="//a[@id=\"CC-loginHeader-logout\"]")
    public static WebElement logout ;

    @FindBy(xpath="//p[contains(text(),'User Management')]")
    public static WebElement UserManagement_Icon ;
    
    @FindBy(xpath="//span[contains(text(),'User Management')]")
    public static WebElement UserManagement_Link ; 
    
    @FindBy( xpath="//a[@href='/adportal/customermanagement']//img[@alt='Logo Alternative Text']")
    public static WebElement CustomerManagement_Icon ; 

    @FindBy(xpath="//p[contains(text(),'Customer Management')]")
    public static WebElement CustomerManagement_Link ; 

    @FindBy(xpath="//img[@class='img img-responsive AD-SettingIcon']")
    public static WebElement Settings_Icon ;

    @FindBy(xpath="//p[contains(text(),'Settings')]")
    public static WebElement Settings_Link ; 

    @FindBy(xpath="//*[@class='ViewmoreDotes']")
    public static WebElement ADViewmore_Drpdwn ;
   
    @FindBy(id="AD-arrow-icon")
    public static WebElement ADArrow_Icon ; 

    @FindBy(xpath="//li[@id='secondaryNavigation-wi100027-tab-0']//a[@class='appicon']")
    public static WebElement Dashboard_AppIcon ; 

    @FindBy(xpath="//span[contains(text(),'User Management')]")
    public static WebElement UserManagement_AppIcon ;

    @FindBy(xpath="//a[@href='/adportal/applications']")
    public static WebElement Application_AppIcon ;

    @FindBy(xpath="//*[contains(text(),'Customers')]")
	public static WebElement Customers_AppIcon ;

    @FindBy(xpath="//li[@id='secondaryNavigation-wi100027-tab-4']//a[@class='appicon']")
    public static WebElement Settings_AppIcon ;
    
    // All locators for verify layout after login
    
    @FindBy(xpath="//input[@id=\"dynamicSearchValue\"]")
    public static WebElement Search_bar;
    
    @FindBy(xpath="//span[@class=\"AD-HeaderRight AD-Welcome-Box\"]/span")
    public static WebElement WebPortal;
    
    @FindBy(xpath="//img[@id=\"AD-GridContainer1\"]")
    public static WebElement GridIcon;
       
    @FindBy(xpath="//img[@id=\"AD-HelpIcon2\"]")
    public static WebElement HelpIcon;
    
    @FindBy(xpath="//img[@id=\"AD-DropDown1\"]")
    public static WebElement AccountIcon;
    
    @FindBy(xpath="//span[contains(text(),'Dashboard')]")
    public static WebElement Dashboard;
    
    @FindBy(xpath="//p[contains(text(),\"assume Role\")]")
    public static WebElement Assumerole;
    
    @FindBy(xpath="//span[contains(text(),'System log')]")
    public static WebElement Systemlog;
    
    @FindBy(xpath="//span[contains(text(),'Settings')]")
    public static WebElement Settings;
    
    @FindBy(xpath="//p[contains(text(),'System Log')]")
    public static WebElement SystemLog_icon;
        
    @FindBy(xpath="//p[contains(text(),'assume Role')]")
    public static WebElement AssumeRole_icon;
       
    @FindBy(xpath="//p[contains(text(),'settings')]")
    public static WebElement Settings_icon;
    
    @FindBy(xpath="//span[@class=\"ViewmoreDotes\"]")
    public static WebElement Fav_Application_3dots;
    
    @FindBy(xpath="//p[contains(text(),'Recent Activities')]")
    public static WebElement  RecentActivities;
    
      
    // Locators under UserManagment Link
    
    @FindBy(xpath="//button[@id='create_group']")
    public  static WebElement Group_button;

    @FindBy(xpath="//h4[text()=\"Create New Group\"]//parent::div//following-sibling::div//input[@type=\"text\"]")
    public static WebElement Create_Group_name;
    
    @FindBy(xpath="//button[@id=\"myModalGroupFooter2\"]")
    public static WebElement Create_button;
    
    @FindBy(xpath="//div[@id=\"UMCreateGroup13\"]")
    public static WebElement Edit_Group_AddApplication;
    
    @FindBy(xpath="//input[@id=\"applicationSearchUM_Group\"]")
    public static WebElement Edit_Group_AddApplication_dropdown;
    
    @FindBy(xpath="//input[@id=\"applicationSearchUM_Group\"]")
    public static WebElement Edit_Grp_SelApplication_ADD_Button;
    
    @FindBy(xpath="//button[text()=\"Save\"]")
    public static WebElement Create_Group_SaveButton;
    
    @FindBy(xpath="//button[@id=\"create_group\"]")
    public  static WebElement SubGroup_createbutton;
    
    @FindBy(xpath="//h4[text()=\"Create New Group\"]//parent::div//following-sibling::div//input[@type=\"text\"]")
    public static WebElement Create_SubGroup_name;
    
    @FindBy(xpath="//button[@id=\"create_user\"]")
    public static WebElement Create_user_button;
    
    @FindBy(xpath="//div[@class=\"slider round\"]")
    public static WebElement Create_user_Slider;
      
    @FindBy(xpath="//h4[text()=\"Create New User\"]//parent::div//following-sibling::div//input[@id=\"UserName\"]")
    public static WebElement Create_user_username;
    
    @FindBy(xpath="//input[@id=\"Useremail\"]")
    public static WebElement NewUser_Email;
    
    @FindBy(xpath="//button[@id=\"createUserbutton\"]")
    public static WebElement NewUser_Create_button;
    
    @FindBy(xpath="//*[@id=\"UMAssignedGroup24\"]")
    public static WebElement NewUser_SAVE_button;
    
    @FindBy(xpath="//*[@id=\"Parentgroup\"]")
    public static WebElement Groups_Navigator;
    
    @FindBy(xpath="//a[@id=\"tab_user1\"]/span")
    public static WebElement Users_tab;
    
    @FindBy(xpath="//div[@id=\"UMCreateGroup13\"]")
    public static WebElement Users_Add_Application;
    
    @FindBy(xpath="//input[@id=\"applicationSearchUM_Group\"]")
    public static WebElement Choose_Application_dropdown;
    
    @FindBy(xpath="//button[@id=\"UM-AddApplicationfooter2\"]")
    public static WebElement Choose_Application_AddButton;
    
    @FindBy(xpath="//button[text()=\"Save\"]")
    public static WebElement EditUser_Save_button;
    
    @FindBy(xpath="//label[@id=\"ModalAddApplication19\"]")
    public static WebElement Choose_File;
    
    @FindBy(xpath="//span[@id=\"UMAssignedGroup7\"]")
    public static WebElement User_AssignTo_Group;
    
    @FindBy(xpath="//input[@id=\"groupsSearchUM_User\"]")
    public static WebElement User_AssignTo_Group_Search;
      
    @FindBy(xpath="//button[@id=\"UMAssignedGroup16\"]")
    public static WebElement AssignTo_Group_SaveButton;
    
    @FindBy(xpath="//p[text()=\"DISABLE\"]")
    public static WebElement Edit_User_Disable;
    
    @FindBy(xpath="//button[@data-target=\"#UM-UserEnble\"]/p")
    public static WebElement Edit_User_Enable;
    
    @FindBy(xpath="//button[contains(text(),'DISABLE')]")
    public static WebElement Disable_confirmation;
    
    @FindBy(xpath="//button[contains(text(),'ENABLE')]")
    public static WebElement Enable_confirmation;
    
    @FindBy(xpath="//p[text()=\"DELETE\"]")
    public static WebElement Edit_User_Delete;
    
    @FindBy(xpath="(//button[contains(text(),'DELETE')])[2]")
    public static WebElement user_Delete_confirmation;
    
    @FindBy(xpath="//span[contains(text(),'Assume Role')]")
    public static WebElement AssumeRole_sidepanel;
    
    @FindBy(xpath="//div[@id=\"logoutAssumeRole\"]/span")
    public static WebElement AssumeRolelogout ;
    
                                                                       
    // Locators under Customers Link
    
    @FindBy(xpath="//span[contains(text(),'Storage')]")
    public static WebElement Customer_Storage_link ;
    
    @FindBy(xpath="//span[contains(text(),'Applications')]")
    public static WebElement Customer_Applications_link ;
    
    @FindBy(xpath="//span[contains(text(),'Settings')]")
    public static WebElement Customer_Settings_link ;
    
    @FindBy(xpath="//*[@id=\"requestPolicy\"]")
    public static WebElement Request_Policy ;
    
    @FindBy(xpath="//span[@id=\"cus_create_customer\"]")
    public static WebElement Customer_button ;
    
    @FindBy(xpath="//input[@id=\"adCreateCustomerName\"]")
    public static WebElement Create_customer_name ;
    
    @FindBy(xpath="//input[@id=\"adCreateCustomerDomainName\"]")
    public static WebElement Create_customer_domain ;
    
    @FindBy(xpath="//input[@id=\"adCreateCustomerDescription\"]")
    public static WebElement Create_customer_Agreemnt_Number ;
    
    @FindBy(xpath="//input[@id=\"example-tel-input\"]")
    public static WebElement Create_customer_contacts_phonenumber ;
    
    @FindBy(xpath="//input[@placeholder=\"Email\"]")
    public static WebElement Create_customer_contacts_email ;
    
    @FindBy(xpath="//input[@id=\"adCreateCustomerAddressStreet\"]")
    public static WebElement Create_customer_Address_Street ;
    
    @FindBy(xpath="//input[@id=\"adCreateCustomerAddressCityName\"]")
    public static WebElement Create_customer_Address_City ;
    
    @FindBy(xpath="//input[@id=\"adCreateCustomerAddressPostalcode\"]")
    public static WebElement Create_customer_Address_Zipcode ;
    
    @FindBy(xpath="(//div[@class=\"AD-ApplicationSerch form-control col-md-12\"])[1]")
    public static WebElement Create_customer_Address_Country ;
    
    @FindBy(xpath="(//div[@class=\"AD-ApplicationSerch form-control col-md-12\"])[2]")
    public static WebElement Create_customer_Address_State ;
    
    @FindBy(xpath="//div[@id=\"CMcontact36\"]")
    public static WebElement Create_customer_AddApplications ;
    
    @FindBy(xpath="//input[@id=\"applicationSearchCM_Customer\"]")
    public static WebElement Choose_an_application_dropdown ;
    
    @FindBy(xpath="//div[@id=\"CMCustcreateGroup18\"]")
    public static WebElement Create_Customer_AssignUser;
    
    @FindBy(xpath="(//label[contains(text(),'choose file')])[1]")
    public static WebElement Create_customer_Attachments_choosefile ;
    
    @FindBy(xpath="(//label[contains(text(),'choose file')])[2]")
    public static WebElement Create_customer_CompanyLogo_choosefile ;
    
    @FindBy(xpath="(//button[contains(text(),'Save')])[1]")
    public static WebElement Create_customer_Save_Button;
    
    @FindBy(xpath="//button[contains(text(),'ADD')]")
    public static WebElement Create_customer_SelApplication_ADD_Button;
    
    @FindBy(xpath="//a[@id=\"allCustomerslink\"]/span")
    public static WebElement Customer_Home_AllTab;
    
    
    @FindBy(xpath="//span[@id='cus_create_group']")
    public  static WebElement Customer_Group_button;
    
    @FindBy(xpath="//button[@id=\"CMModalGroup11\"]")
    public static WebElement Customer_Create_button;
    
    @FindBy(xpath="//div[@id=\"CMCustcreateGroup11\"]")
    public static WebElement Customer_Editgrp_AddApplication;
     
    @FindBy(xpath="//img[@class=\"img img-responsive AD-Email\"]")
    public static WebElement Customer_Emergency_Notification;
    
    @FindBy(xpath="//textarea[@id=\"cust_notification_text\"]")
    public static WebElement Emergency_Notification_SearchTenantName;
    
    @FindBy(xpath="//input[@id=\"AD-SubjectTxtArea\"]")
    public static WebElement Emergency_Notification_Subject;
    
    @FindBy(xpath="//textarea[@id=\"AD-EmailBodyTxtArea\"]")
    public static WebElement Emergency_Notification_EmailBody;
    
    @FindBy(xpath="//button[contains(text(),\"SEND\")]")
    public static WebElement Emergency_Notification_SEND_Button;
    
    @FindBy(xpath="(//label[contains(text(),\"choose file\")])[3]")
    public static WebElement Edit_user_chooseFile;
    
    @FindBy(xpath="//p[contains(text(),'DISABLE')]")
    public static WebElement Edit_Cust_DISABLEbtn;
    
    @FindBy(xpath="//button[@id=\"CMRightContact50\"]")
    public static WebElement Edit_Cust_ARCHIVEbtn;
    
    @FindBy(xpath="//button[@data-bind=\"click: archiveCustomer\"]")
    public static WebElement Edit_Cust_ARCHIVEbtn_confirmation;
    
    @FindBy(xpath="//button[@id=\"CMRightContact51\"]")
    public static WebElement Edit_Cust_DELETEbtn;
    
    @FindBy(xpath="//button[contains(text(),'DELETE')]")
    public static WebElement Cust_DelConfirmation_DELETEbtn;
    
    @FindBy(xpath="//button[contains(text(),'CANCEL')]")
    public static WebElement Cust_DelConfirmation_CANCELbtn;
    
    @FindBy(xpath="//span[@id=\"cus_create_user\"]")
    public static WebElement Customer_create_user;
    
    @FindBy(xpath="//span[text()=\"New\"]")
    public static WebElement Customer_storage_New;

    @FindBy(xpath="//span[@id=\"CMCreateUserContainer33\"]")
    public static WebElement CustUsers_Add_Application;
    
    @FindBy(xpath="//input[@id=\"applicationSearchCM_User\"]")
    public static WebElement CustChoose_Application_dropdown;
    
    @FindBy(xpath="//span[contains(text(),\"Freshmarx Edit 1\")]")
    public static WebElement CustUser_Select_Application;
    
    @FindBy(xpath="//button[contains(text(),'ADD')]")
    public static WebElement CustChoose_Application_AddButton;
    
    @FindBy(xpath="//span[@id=\"CMCreateUserContainer33\"]")
    public static WebElement Cust_EditUser_ADD_Application;
    
    @FindBy(xpath="//input[@id=\"applicationSearchCM_User\"]")
    public static WebElement EditUser_choose_application_searchdropdown ;
    
    @FindBy(xpath="//button[@id=\"CMModalAddApplication10\"]")
    public static WebElement EditUser_SelApplication_ADD_Button;
     
    @FindBy(xpath="//button[@id=\"CMModalAddAssignedGroup22\"]")
    public static WebElement Cust_EditUser_SaveButton;
       
}