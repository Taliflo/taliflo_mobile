//
//  TLFBusinessStore.m
//  Taliflo
//
//  Created by NR-Mac on 1/27/2014.
//  Copyright (c) 2014 Taliflo Inc. All rights reserved.
//

#import "TLFBusinessStore.h"

@interface TLFBusinessStore ()

@end

@implementation TLFBusinessStore

+ (instancetype)getInstance
{
    static TLFBusinessStore *instance = nil;
    static dispatch_once_t pred;
    
    if (instance) return instance;
    
    dispatch_once(&pred, ^{
        instance = [TLFBusinessStore alloc];
        instance = [instance initPrivate];
    });
    
    return instance;
}

// This method should not be called
- (instancetype)init
{
    @throw [NSException exceptionWithName:@"Singleton"
                                   reason:@"Use +[TLFCauseStore getInstance]"
                                 userInfo:nil];
}

- (instancetype)initPrivate
{
    return [super init];
}

@end
