Adding Photo Picker+ To A Project
=================================

Photo Picker Plus is a drop-in component that replaces the default photo picker in your app.  It allows you to take a photo as well as choose a photo from the device or from several online sources.  This tutorial will show you how to replace the default UIImagePicker in your application with Photo Picker Plus.  This tutorial was written using version 5.0 of the iOS SDK and version 4.2 of Xcode.  Uses Chute SDK version 1.0.4 or newer (the version number can be found in the GCConstants.h file).  Some changes may need to be made for other software versions.

![image1](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/1.png)
![image2](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/2.png)
![image3](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/3.png)

Preparation
-----------
1.  Download the Chute SDK from https://github.com/chute/Chute-SDK/tree/master/iOS
2.  Download the PhotoPickerPlus component from https://github.com/chute/photo-picker-plus/tree/master/iOS/PhotoPickerPlus
3.  If you don't have a Chute developer account or an app created on chute for this project then create a Chute developer account and make a new app in Chute at http://apps.getchute.com/

![image5](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/5.png)
![image6](https://github.com/chute/photo-picker-plus/raw/master/iOS/tutorial/screenshots/6.png)

Add The SDK And Component And Link Dependancies
-----------------------------------------------
1. Add the Chute SDK to the project
2. Add the Photo Picker Plus component
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
The next step is to enter your chute app information in the GCConstants.h file.  This file can be found at the root of the Chute SDK directory.  You will need to fill in your APP ID and APP secret from the summary tab of your admin panel.  You will also need to adjust the redirect URL to match the callback url from the admin panel.  Then set the redirect relative url to everything after the base in the callback url.

At this point you may want to try running the project to make sure that everything is added ok.  If it builds then everything should be correctly added and linked.

Change your delegate
--------------------
In the header for the controller that will be using the component import the PhotoPickerPlus.h file and inherit the PhotoPickerPlusDelegate instead of the UIImagePickerDelegate protocol.

```objective-c
	#import <UIKit/UIKit.h>
	#import "PhotoPickerPlus.h"

	@interface ViewController : UIViewController <PhotoPickerPlusDelegate>
	
	//existing code for your class

	@end
```

Change The Delegate Methods
---------------------------
In your class change imagePickerControllerDidCancel: to PhotoPickerPlusControllerDidCancel: and change imagePickerController:didFinishPickingMediaWithInfo: to PhotoPickerPlusController:didFinishPickingMediaWithInfo:.  You can leave the code in these methods exactly the same as you had before because the return values are the same format.  If you need to know which source the info came from you can call sourceType on the picker.

```objective-c
	-(void) PhotoPickerPlusControllerDidCancel:(PhotoPickerPlus *)picker{
	    //cancel code
	}
	-(void) PhotoPickerPlusController:(PhotoPickerPlus *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info{
	    //image picked code
	}
```

Displaying The Image Picker
---------------------------
Finally replace the code to display the image picker.  Photo Picker Plus lets the user select a source for the image so you don't need to set it ahead of time.  The only other difference is that you should set your viewController's ModalPresentationStyle to UIModalPresentationCurrentContext for a non-navigation based project or set your navigationViewController's ModalPresentationStyle to UIModalPresentationCurrentContext for a  navigation based project.

```objective-c
	PhotoPickerPlus *temp = [[PhotoPickerPlus alloc] init];
	[temp setDelegate:self];
	[self setModalPresentationStyle:UIModalPresentationCurrentContext];
	[self presentViewController:temp animated:YES completion:^(void){
	    [temp release];
	}];
```

Conclusion
----------
You now have a multi-service photo picker in your app instead of Apple's UIImagePickerController.  Most of your existing code for the imagePicker should still work because the info dictionary returned is designed to match the info dictionary returned by the UIImagePicker.