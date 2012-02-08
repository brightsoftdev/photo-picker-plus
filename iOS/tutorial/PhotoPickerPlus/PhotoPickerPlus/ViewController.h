//
//  ViewController.h
//  PhotoPickerPlus
//
//  Created by Brandon Coston on 2/7/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SlideChute.h"

@interface ViewController : UIViewController <SlideChuteDelegate>

@property (nonatomic, readonly) IBOutlet UIImageView *imageView;

-(IBAction)pickPhotoSelected:(id)sender;

@end
