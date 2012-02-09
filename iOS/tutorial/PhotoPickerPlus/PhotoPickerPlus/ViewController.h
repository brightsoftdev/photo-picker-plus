//
//  ViewController.h
//  PhotoPickerPlus
//
//  Created by Brandon Coston on 2/7/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PhotoPickerPlus.h"

@interface ViewController : UIViewController <photoPickerPlusDelegate>

@property (nonatomic, readonly) IBOutlet UIImageView *imageView;

-(IBAction)pickPhotoSelected:(id)sender;

@end
