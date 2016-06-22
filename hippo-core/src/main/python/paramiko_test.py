#!/usr/bin/python
# encoding=utf-8
# Filename: .py
# User: change.long
# Date: 16/6/22
# Time: 下午1:02
import datetime
import threading

import paramiko


def sshcmd(ip, user, password, cmds):
    try:
        client = paramiko.SSHClient()
        client.load_system_host_keys()  # 需要安装pip install cryptography==1.2.1
        client.set_missing_host_key_policy(paramiko.AutoAddPolicy)
        client.connect(ip, 22, user, password, timeout=5)
        for cmd in cmds:
            stdin, stdout, stderr = client.exec_command(cmd)
            lines = stdout.readlines()
            for line in lines:
                print line,
        print '%s\t 运行完毕\r\n' % ip
    except Exception, e:
        print '%s\t 运行失败,失败原因\r\n%s' % (ip, e)
    finally:
        client.close()


def upload(ip, username, password, localpath, remotepath):
    try:
        t = paramiko.Transport((ip, 22))
        t.connect(username=username, password=password)
        sftp = paramiko.SFTPClient.from_transport(t)
        sftp.put(localpath=localpath, remotepath=remotepath)
        print '上传文件%s到%s成功' % (localpath, remotepath)
    except Exception, e:
        print '%s\t 运行失败,失败原因\r\n%s' % (ip, e)
    finally:
        t.close()


def download(ip, username, password, remotepath, localpath):
    try:
        t = paramiko.Transport((ip, 22))
        t.connect(username=username, password=password)
        sftp = paramiko.SFTPClient.from_transport(t)
        sftp.get(remotepath=remotepath, localpath=localpath)
        print '下载文件%s到%s成功' % (remotepath, localpath)
    except Exception, e:
        print '%s\t 运行失败,失败原因\r\n%s' % (ip, e)
    finally:
        t.close()


if __name__ == '__main__':
    cmds = ['ls /root', 'ifconfig']
    servers = ['192.168.47.196', '192.168.15.31', '192.168.64.253']
    user1 = 'root'
    password1 = '111111'
    localpath = "/Users/changealice/changedownload.txt"
    remotepath = "/root/change.txt"
    print "程序开始运行%s" % datetime.datetime.now()
    threads = []  # 线程数组
    for server in servers:
        th = threading.Thread(target=sshcmd, args=(server, user1, password1, cmds))  # 创建线程
        th.start()  # 启动线程调用start方法,而不是run方法
        threads.append(th)  # 添加到线程数组中

    for th in threads:
        th.join()  # 等待每个线程执行完毕

    upload(ip=servers[0], username=user1, password=password1, localpath=localpath, remotepath=remotepath)
    download(ip=servers[0], username=user1, password=password1, localpath=localpath, remotepath=remotepath)
