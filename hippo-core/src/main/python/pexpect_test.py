#!/usr/bin/python
# encoding=utf-8
# Filename: .py
# User: change.long
# Date: 16/6/22
# Time: 下午1:02
import pexpect

def sshCmd(ip, user, passwd, cmd):
    ssh = pexpect.spawn('ssh %s@%s "%s"' % (user, ip, cmd))
    try:
        i = ssh.expect(['password:', 'continue connecting(yes/no)?'], timeout=5)
        if i == 0:
            ssh.sendline(passwd)
        elif i == 1:
            ssh.sendline('yes\n')
            ssh.expect('password:')
            ssh.sendline(passwd)
        ssh.sendline(cmd)
        r = ssh.read()
        print r
        ret = 0
    except pexpect.EOF:
        print "EOF"
        ret = -1
    except pexpect.TIMEOUT:
        print "TIMEOUT"
        ret = -2
    finally:
        ssh.close()
    return ret

sshCmd('192.168.47.196', 'root', '111111', 'ls /root')
