//
//  GCConstants.h
//
//  Copyright 2011 Chute Corporation. All rights reserved.
//

//////////////////////////////////////////////////////////
//                                                      //
//                   VERSION 1.120206                   //
//                                                      //
//////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////
// Set which service is to be used
// 0 - Facebook
// 1 - Evernote
// 2 - Chute
// 3 - Twitter
// 4 - Foursquare

#define kSERVICE 0

////////////////////////////////////////////////////////////////////////////////////////////////////////

#define API_URL @"http://api.getchute.com/v1/"
#define SERVER_URL @"http://getchute.com"

////////////////////////////////////////////////////////////////////////////////////////////////////////

#define kUDID               [[UIDevice currentDevice] uniqueIdentifier]
#define kDEVICE_NAME        [[UIDevice currentDevice] name]
#define kDEVICE_OS          [[UIDevice currentDevice] systemName]
#define kDEVICE_VERSION     [[UIDevice currentDevice] systemVersion]

//replace the following setting with your own client info
#define kOAuthCallbackURL               @"http://getchute.com/oauth/callback"
#define kOAuthCallbackRelativeURL       @"/oauth/callback"
#define kOAuthClientID                  @"PUT_CLIENT_ID_HERE"
#define kOAuthClientSecret              @"PUT_CLIENT_SECRET_HERE"

#define kOAuthPermissions               @"all_resources manage_resources profile resources"

#define kOAuthTokenURL                  @"http://getchute.com/oauth/access_token"