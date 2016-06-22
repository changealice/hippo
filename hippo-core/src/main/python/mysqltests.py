#!/usr/bin/python
# encoding=utf-8
# Filename: mysqltests
import datetime
import os
import MySQLdb as mysql

if __name__ == '__main__':
    print 'hello world'
    print datetime.datetime.now()
    print mysql
    print os.name
    try:
        conn = mysql.connect(host="127.0.0.1", user="root", passwd="", db="test", port=3306, charset="utf8")
        print conn
        cur = conn.cursor()
        cur.execute("insert into t_user(user_name,password,account_options) values('java','123',NULL)")
        conn.commit()
        print "获取数据"
        count = cur.execute("select * from t_user")
        print "总条数 %d" % count
        result = cur.fetchone()
        print "id:%s,username:%s,password:%s,option:%s" % result
        print "-----------------------"
        results = cur.fetchmany(2)
        for r in results:
            print "id:%s,username:%s,password:%s,option:%s" % r
        print "-----------------------"
        results = cur.fetchall()
        for r in results:
            print "id:%s,username:%s,password:%s,option:%s" % r
        print "-----------------------"
    except mysql.Error, e:
        print 'Mysql error %d:%s' % (e.args[0], e.args[1])
    finally:
        cur.close()
        conn.close()
