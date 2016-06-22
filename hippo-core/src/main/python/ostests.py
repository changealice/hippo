#!/usr/bin/python
# encoding=utf-8
# Filename: ostests
import datetime
import os

if __name__ == '__main__':
    print datetime.datetime.now()
    print os.name
    print os.curdir
    print os.pardir
    print os.sep
    print os.extsep
    print os.altsep
    print os.pathsep
    print os.linesep
    print os.linesep
    print os.defpath
    print os.devnull
    print os.getcwd()
    print os.getlogin()
    print os.uname()
    statinfo = os.stat("/Users/changealice/tools/opensource/hippo/hippo-core/src/main/python/ostests.py")
    print statinfo

    lfile = os.listdir(os.getcwd())
    for sfileName in lfile:
        if os.path.isdir(sfileName):
            print '目录%s' % sfileName
        elif os.path.isfile(sfileName):
            print '文件%s' % sfileName

    print os.path.curdir
