SlideChute
==============

no external dependancies beyond Chute SDK (version 1.120206 or newer)

Description
-----------

This class allows you to pick a photo from the any supported online source such as Facebook, picasa and instagram among others.  It also replaces the standard picker in that it allows you to pick photos from the device or take a photo with the camera.

Screenshots
-----------
![screen1](https://github.com/chute/chute-ios-components/raw/master/components/SlideChute/screenshots/screen1.png)![screen2](https://github.com/chute/chute-ios-components/raw/master/components/SlideChute/screenshots/screen2.png)![screen3](https://github.com/chute/chute-ios-components/raw/master/components/SlideChute/screenshots/screen3.png)![screen4](https://github.com/chute/chute-ios-components/raw/master/components/SlideChute/screenshots/screen4.png)![screen5](https://github.com/chute/chute-ios-components/raw/master/components/SlideChute/screenshots/screen5.png)

Subclassing
-----------

While subclassing this component is possible, it is not really recommended.  If you want to change the appearance of the component it is recommended that you simply modify the .xib file.  Image selection is passed to a delegate method so any custom behavior can be handled there.

Initialization
--------------

 *   delegate - `NSObject <SlideChuteDelegate>` - The delegate for this component.  It should implement two methods.
    *  `-(void)slideChuteController:(SlideChute *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info;`
    *  `-(void)slideChuteControllerDidCancel:(SlideChute *)picker;`


Implementation
--------------


```objective-c
    -(void)showSlideChute{
	    SlideChute *temp = [[SlideChute alloc] init];
	    [temp setDelegate:self];
	    self.modalPresentationStyle = UIModalPresentationCurrentContext;
	    [self presentModalViewController:temp animated:NO];
	    [temp release];
	}
	-(void)slideChuteControllerDidCancel:(SlideChute *)picker{
	    //place code for when the user cancels here
	    //such as removing the picker from the screen
	}
	-(void)slideChuteController:(SlideChute *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info{
	    //place code for when the user picks a photo here and do any
	    //additional work such as removing the picker from the screen
	}
```
