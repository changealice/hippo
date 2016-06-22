#!/usr/bin/python
# encoding=utf-8
# Filename: .py
# User: change.long
# Date: 16/6/22
# Time: 下午12:21
import os
import socket
import subprocess

server = "www.99bill.com"


def pingserver(server):
    result = os.system("ping -c 5 " + server)
    if result:
        print '服务器: %s ping fail' % server
    else:
        print '服务器:%s ping ok' % server
    print result


# 输出日记到/dev/null 黑洞
def pingserverdevnull(server):
    fnull = open(os.devnull, 'w')
    try:
        result = subprocess.call("ping -c 5 " + server, shell=True, stdout=fnull, stderr=fnull)
        if result:
            print '服务器: %s ping fail' % server
        else:
            print '服务器:%s ping ok' % server
        print result
    except Exception, e:
        print e
    finally:
        fnull.close()


def check_aliveness(ip, port):
    sk = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sk.settimeout(100)
    try:
        sk.connect((ip, port))
        print 'server %s %d service is OK!' % (ip, port)
        return True
    except Exception, e:
        print e.message
        print 'server %s %d service is NOT OK!' % (ip, port)
        return False
    finally:
        sk.close()


if __name__ == '__main__':
    # pingserver(server)
    # pingserverdevnull(server)
    aliveness = check_aliveness("127.0.0.1", 3306)
    print 'alive %s' % aliveness
