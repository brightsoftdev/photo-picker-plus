Chute Starter Project Tutorial
==============================

Photo Picker Plus is a drop-in component that replaces the default photo picker in your app.  It allows you to take a photo as well as choose a photo from the device or from several online sources.  This tutorial will show you how to use the Photo Picker Plus component to present the user with a multi-service photo picker and use the chosen image to set an imageView.  This tutorial was written using version 5.0 of the iOS SDK and version 4.2 of Xcode.  Uses Chute SDK version 1.0.4 or newer (the version number can be found in the GCConstants.h file).  Some changes may need to be made for other software versions.

![image1](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/1.png)
![image2](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/2.png)
![image3](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/3.png)

Create A New Project
--------------------
Start by creating a new Xcode project.  A single view application will be easiest to modify for this tutorial.  You can choose whatever name you like, I'll call it PhotoPickerPlus.  Be sure that “Use Automatic Reference Counting” is unchecked as the SDK does not currently support ARC.

![image4](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/4.png)

Preparation
-----------
1.  Download the Chute SDK from https://github.com/chute/Chute-SDK/tree/master/iOS
2.  Download the PhotoPickerPlus component from https://github.com/chute/photo-picker-plus/tree/master/iOS/PhotoPickerPlus
3.  Create a Chute developer account and make a new app in Chute at http://apps.getchute.com/
	*  For the URL you can enter http://getchute.com/ if you don't have a site for your app
	*  For the Callback URL you can use http://getchute.com/oauth/callback if you don't need callbacks for another purpose.

![image5](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/5.png)
![image6](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/6.png)

Add The SDK And Component And Link Dependancies
-----------------------------------------------
1. Add the SDK to the project
2. Add the picker component
3. Link the required libraries
     *  AssetsLibrary
     *  CFNetwork
     *  EventKit
     *  libz.dylib
     *  MessageUI
     *  MobileCoreServices
     *  Security
     *  SystemConfiguration

![image7](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/7.png)

Edit Your App ID And Secret
---------------------------
The next step is to enter your chute app information in the GCConstants.h file.  This file can be found at the root of the Chute SDK directory.  You will need to fill in your APP ID and APP secret from the summary tab of your admin panel.  If you used a custom Redirect URL when setting up your app on Chute you will also need to adjust the `kOauthCallbackURL` to match the callback url you set.  Then set the `kOauthCallbackRelativeURL` to everything after the base in the callback url.  If you used `http://getchute.com/oauth/callback` then you can leave these as they are.

![image8](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/8.png)

At this point you may want to try running the project to make sure that everything is added ok.  If it builds then everything should be correctly added and linked.

Set ViewController As Delegate And Add Objects/Methods
-----------------------------------------------
In your viewController.h file import PhotoPickerPlus.h and set up the class as a `PhotoPickerPlusDelegate`.  Then add an object for the image view and a method for pressing the pick photo button.  This should look similar to this

viewController.h

```objective-c
	#import <UIKit/UIKit.h>
	#import "PhotoPickerPlus.h"

	@interface ViewController : UIViewController <PhotoPickerPlusDelegate>

	@property (nonatomic, readonly) IBOutlet UIImageView *imageView;

	-(IBAction)pickPhotoSelected:(id)sender;

	@end
```

Synthesize ImageView And Write The Display Method
-------------------------------------------------
In viewController.m you now need to synthesize your imageView object and write the method to display the photo picker plus component.  The method will initialize the controller, set itself as the delegate, present it and release it.  We also want to have out current view visible behind the first screen of the picker so we will add a line for that as well.  The code for all this is

viewController.m

```objective-c
	@synthesize imageView;

	-(IBAction)pickPhotoSelected:(id)sender{
	    PhotoPickerPlus *temp = [[PhotoPickerPlus alloc] init];
	    [temp setDelegate:self];
	    [self setModalPresentationStyle:UIModalPresentationCurrentContext];
	    [self presentViewController:temp animated:YES completion:^(void){
	        [temp release];
	    }];
	}
```

Write The Delegate Methods
--------------------------
The PhotoPickerPlusDelegate methods are `PhotoPickerPlusControllerDidCancel:` and `PhotoPickerPlusController:didFinishPickingMediaWithInfo:`.  These work exactly the same as the UIImagePickerController delegate methods to make things easier.  You can refer to Apple's documentation on UIImagePickerControllerDelegate to see what the keys in the dictionary are.  The only one we are going to be concerned with is `UIImagePickerControllerOriginalImage`.  So our cancel method will just dismiss the picker and our success method will display the image in the imageView and dismiss the picker.  The code for these methods is

viewController.m

```objective-c
	-(void) PhotoPickerPlusControllerDidCancel:(PhotoPickerPlus *)picker{
	    [self dismissViewControllerAnimated:YES completion:^(void){
        
	    }];
	}
	-(void) PhotoPickerPlusController:(PhotoPickerPlus *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info{
	    [self dismissViewControllerAnimated:YES completion:^(void){
	        [[self imageView] setImage:[info objectForKey:UIImagePickerControllerOriginalImage]];
	    }];
	}
```

Create The UI
-------------
Open viewController.xib and add an UIImageView covering most of the view and a UIButton below it.  Hook the UIImageView up to the imageView object and hook up the pickPhotoSelected method to the touchUpInside event of the button.  You do this by right clicking on the file's owner and dragging from the circle for the outlet or action that you want to it's corresponding object either in the object's list or in the view itself.  You can set the title for the button whatever you want by selecting it then changing it's title in the attribute inspector on the right side of the screen.  I called mine Pick Photo.  You also probably want to set the mode for the UIImageView to aspectFit which is accessed from the attribute inspector for the UIImageView.

![image9](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/9.png)

Conclusion
----------
You should have a fully working app now that allows you to take a photo, pick an image from the device, or pick an image from a variety of online sources.  Due to the picker accessing the ALAssets it will present the user with a dialog asking if they want to allow location services.  This is because ALAssets have location data associated with the images.  If the user declines then the picker will not allow them to choose images that are on the device however all other sources should still work.