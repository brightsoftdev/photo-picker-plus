//
//  ViewController.h
//  ChuteStarterProject
//
//  Created by Brandon Coston on 2/12/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PhotoPickerPlus.h"

@interface ViewController : UIViewController <PhotoPickerPlusDelegate>

@property (nonatomic, readonly) IBOutlet UIImageView *imageView;

-(IBAction)pickPhotoSelected:(id)sender;

@end
