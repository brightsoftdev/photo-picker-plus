PhotoPickerPlus
==============

no external dependancies beyond Chute SDK (version 1.0.4 or newer)

Description
-----------

This class allows you to pick a photo from the any supported online source such as Facebook, picasa and instagram among others.  It also replaces the standard picker in that it allows you to pick photos from the device or take a photo with the camera.

Screenshots
-----------
![screen1](https://github.com/chute/photo-picker-plus/raw/master/iOS/PhotoPickerPlus/screenshots/screen1.png)![screen2](https://github.com/chute/photo-picker-plus/raw/master/iOS/PhotoPickerPlus/screenshots/screen2.png)![screen3](https://github.com/chute/photo-picker-plus/raw/master/iOS/PhotoPickerPlus/screenshots/screen3.png)![screen4](https://github.com/chute/photo-picker-plus/raw/master/iOS/PhotoPickerPlus/screenshots/screen4.png)![screen5](https://github.com/chute/photo-picker-plus/raw/master/iOS/PhotoPickerPlus/screenshots/screen5.png)

Subclassing
-----------

While subclassing this component is possible, it is not really recommended.  If you want to change the appearance of the component it is recommended that you simply modify the .xib file.  Image selection is passed to a delegate method so any custom behavior can be handled there.

Initialization
--------------

 *   delegate - `NSObject <PhotoPickerPlusDelegate>` - The delegate for this component.  It should implement two methods.
    *  `-(void)PhotoPickerPlusController:(PhotoPickerPlus *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info;`
    *  `-(void)PhotoPickerPlusControllerDidCancel:(PhotoPickerPlus *)picker;`


Implementation
--------------


```objective-c
    -(void)showPhotoPickerPlus{
	    PhotoPickerPlus *temp = [[PhotoPickerPlus alloc] init];
	    [temp setDelegate:self];
	    self.modalPresentationStyle = UIModalPresentationCurrentContext;
	    [self presentModalViewController:temp animated:NO];
	    [temp release];
	}
	-(void)PhotoPickerPlusControllerDidCancel:(PhotoPickerPlus *)picker{
	    //place code for when the user cancels here
	    //such as removing the picker from the screen
	}
	-(void)PhotoPickerPlusController:(PhotoPickerPlus *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info{
	    //place code for when the user picks a photo here and do any
	    //additional work such as removing the picker from the screen
	}
```
