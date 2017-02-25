# -*- coding: UTF-8 -*-
import MySQLdb
import sys
reload(sys)
sys.setdefaultencoding('utf-8')


class SQL(object):

    __host = '139.129.48.182'
    __user = 'cuiods'
    __passwd = '0628'
    __db = 'gitmining'
    __port = 3306
    __conn = None
    __cursor = None
    __comments_query = 'SELECT sec_repo.id,sec_repo.owner,sec_repo.name FROM sec_repo LEFT JOIN comments ON sec_repo.id=comments.repo_id  WHERE comments.repo_id is NULL GROUP BY sec_repo.id ORDER BY sec_repo.star_count LIMIT 1000'
    __news_query = 'SELECT sec_repo.id,sec_repo.owner,sec_repo.name FROM sec_repo LEFT JOIN news ON sec_repo.id=news.repo_id WHERE news.repo_id is NULL GROUP BY sec_repo.id ORDER BY sec_repo.star_count LIMIT 1000'
    __comments_insert = 'INSERT INTO comments(repo_id, comment) VALUES (%s,%s)'
    __news_insert = 'INSERT INTO news(repo_id, title, summary, source, source_url) VALUES (%s,%s,%s,%s,%s)'
    __sec_news_insert = 'INSERT INTO news_os(id, summary, time, title, repo_owner, repo_name) VALUES (%s,%s,%s,%s,%s,%s)'

    __project_os_insert = 'INSERT INTO project_os(p_name, os_id, owner, name, page) VALUES (%s,%s,%s,%s,%s)'
    __comments_os_insert = 'INSERT INTO comments_os(p_name, comment) VALUES (%s,%s)'

    def __init__(self):
        self.__conn = MySQLdb.connect(host=self.__host, user=self.__user, passwd=self.__passwd, db=self.__db,
                                      port=self.__port, charset='utf8')
        # self.__conn.set_character_set('utf8')

        self.__cursor = self.__conn.cursor()
        # self.__cursor.execute('SET NAMES utf8;')
        # self.__cursor.execute('SET CHARACTER SET utf8;')
        # self.__cursor.execute('SET character_set_connection=utf8;')

    def __del__(self):
        self.__cursor.close()
        self.__conn.close()

    def get_repo_without_comments(self):
        self.__cursor.execute(self.__comments_query)
        return self.__cursor.fetchall()

    def insert_comments(self, repo_id, comments=[]):
        length = len(comments)
        values = [None]*length
        for i in range(length):
            values[i] = (repo_id, comments[i])
        return self.__cursor.executemany(self.__comments_insert, values)

    def get_repo_without_news(self):
        self.__cursor.execute(self.__news_query)
        return self.__cursor.fetchall()

    def insert_news(self, repo_id, news, urls):
        length = len(news)
        values = [None] * length
        for i in range(length):
            values[i] = ( repo_id, news[i][0:12], news[i],  0, urls[i])
        return self.__cursor.executemany(self.__news_insert, values)

    def test(self):
        sql = "INSERT INTO comments(repo_id, comment) VALUES ('1','好的')"
        # sql = sql.decode('utf-8')
        self.__cursor.execute(sql)

    def get_next_news_id(self):
        sql = "SELECT max(news_os.id) FROM news_os"
        self.__cursor.execute(sql)
        result = self.__cursor.fetchall()
        if result and result[0][0]:
            result = int(result[0][0])+1
            return result
        return 1

    def insert_sec_news(self, sec_news_object):
        self.__cursor.execute(self.__sec_news_insert, sec_news_object)

    def get_next_page_project(self):
        sql = "SELECT max(page) FROM project_os"
        self.__cursor.execute(sql)
        result = self.__cursor.fetchall()
        if result and result[0][0]:
            return int(result[0][0])
        return 1

    def insert_project_os(self, project_os):
        self.__cursor.execute(self.__project_os_insert, project_os)

    def insert_comments_os(self, p_name, comments=[]):
        print comments
        length = len(comments)
        values = [None]*length
        for i in range(length):
            values[i] = (p_name, comments[i])
        return self.__cursor.executemany(self.__comments_os_insert, values)

if __name__ == "__main__":
    my = SQL()
    my.insert_comments_os('name', ['我是傻逼', '你是傻逼'])

