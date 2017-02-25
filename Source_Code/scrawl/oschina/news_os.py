# -*- coding: UTF-8 -*-
import tool
import re
import datetime
from lxml import etree


class NewsOS(object):

    __site = 'http://www.oschina.net/news/'
    __title_path = u'//head/title'
    __time_path = u"//div[@id='NewsChannel']/div[@class='NewsBody']/div[@class='News']/div[@class='NewsEntity']/div[@class='PubDate']"
    __summary_path = u"//div[@id='NewsChannel']/div[@class='NewsBody']/div[@class='News']/div[@class='NewsEntity']/div[@class='Body NewsContent TextContent']//text()"
    __time_pattern = re.compile(r"""(\d{4}).(\d{2}).(\d{2}).""")
    __owner_name_pattern = re.compile("""https://github.com/(\w+)/(\w+)""")

    def __init__(self):
        self.url = None
        self.id = None
        self.title = None
        self.summary = None
        self.time = None
        self.owner = ''
        self.name = ''
        self.html = None
        self.response_body = None

    def launcher(self, identity=1):
        self.url = self.__site+str(identity)
        self.id = identity
        self.title = None
        self.summary = None
        self.time = None
        self.owner = ''
        self.name = ''
        self.html = None
        self.response_body = None
        self.__read()
        self.__read_title()
        self.__read_time()
        self.__read_summary()
        self.__read_owner_name()
        return self.get_database_array()

    def __read(self):
        response = tool.Response()
        response_body = response.read_response(self.url)
        self.response_body = response_body
        self.html = etree.HTML(response_body)

    def __read_title(self):
        titles = self.html.xpath(self.__title_path)
        if titles:
            self.title = titles[0].text
            print self.title

    def __read_time(self):
        times = self.html.xpath(self.__time_path)
        if times:
            publish_time = times[0]
            text_array = ''.join( publish_time.xpath(u"text()"))
            match = re.search(self.__time_pattern,text_array)
            year = int(match.group(1))
            month = int(match.group(2))
            day = int(match.group(3))
            dateC = datetime.datetime(year, month, day, 0, 0, 0)
            self.time = dateC

    def __read_summary(self):
        summary = self.html.xpath(self.__summary_path)
        if summary:
            self.summary = ''.join(summary)
            print self.summary

    def __read_owner_name(self):
        match = re.search(self.__owner_name_pattern, self.response_body)
        if match:
            self.owner = match.group(1)
            self.name = match.group(2)
            print self.owner
            print self.name

    def get_database_array(self):
        return self.id, self.summary, self.time, self.title, self.owner, self.name

if __name__ == '__main__':
    os = NewsOS()
    os.launcher(3)




