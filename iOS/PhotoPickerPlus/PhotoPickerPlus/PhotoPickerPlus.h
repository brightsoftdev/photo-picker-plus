//
//  PhotoPickerPlus.h
//  ChuteSDKDevProject
//
//  Created by Brandon Coston on 1/21/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "GetChute.h"

@protocol PhotoPickerPlusDelegate;

@interface PhotoPickerPlus : GCUIBaseViewController <UIImagePickerControllerDelegate, UINavigationControllerDelegate, UITableViewDataSource, UITableViewDelegate, UIActionSheetDelegate, UIWebViewDelegate>

@property (nonatomic, assign) NSObject <PhotoPickerPlusDelegate> *delegate;

@property (nonatomic) int accountIndex;

@property (nonatomic, readonly) IBOutlet UIView *sourceView;
@property (nonatomic, readonly) IBOutlet UIView *accountView;
@property (nonatomic, readonly) IBOutlet UIView *albumView;
@property (nonatomic, readonly) IBOutlet UIView *photoView;

@property (nonatomic, retain) NSArray *photoAlbums;
@property (nonatomic, retain) NSArray *accounts;
@property (nonatomic, retain) NSArray *albums;
@property (nonatomic, retain) NSArray *photos;

@property (nonatomic, readonly) IBOutlet UITableView *accountsTable;
@property (nonatomic, readonly) IBOutlet UITableView *albumsTable;
@property (nonatomic, readonly) IBOutlet UITableView *photosTable;

@property (nonatomic, readonly) IBOutlet UINavigationItem *albumsBarTitle;
@property (nonatomic, readonly) IBOutlet UINavigationItem *photosBarTitle;

@property (nonatomic, readonly) IBOutlet UIView *photoCountView;
@property (nonatomic, readonly) IBOutlet UILabel *photoCountLabel;

@property (nonatomic, readonly) IBOutlet UIView *AddServiceView;
@property (nonatomic, readonly) IBOutlet UIWebView *AddServiceWebView;


@property (nonatomic) BOOL appeared;


-(UIView*)tableView:(UITableView *)tableView viewForIndexPath:(NSIndexPath*)indexPath;

@end

@protocol PhotoPickerPlusDelegate <NSObject>

-(void)PhotoPickerPlusController:(PhotoPickerPlus *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info;
-(void)PhotoPickerPlusControllerDidCancel:(PhotoPickerPlus *)picker;

@end

