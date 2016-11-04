# -*- coding: UTF-8 -*-
import re
from lxml import etree
import tool


class Comment(object):

    __site = "http://www.oschina.net/"
    __search_site = "http://www.oschina.net/search"
    __comments_site = "http://www.oschina.net/p/list_p_tweets"
    __data = None
    __patter_id = re.compile(r"""data:"id="\+(\d+)\+"&type=""")
    __search_path = u"//ul[@id='results']/li[1]/div/h3/a"
    __top_tweets = u"//ul[@class='TopTweets']/li/span[@class='body']/span[@class='log']"
    __tool = None
    p_name = None
    owner = None
    name = None
    project_id = None
    __owner_name_pattern = re.compile("""https://github.com/(\w+)/(\w+)""")

    def __init__(self):
        self.__tool = tool.Response()

    def get_comments_by_url(self, p_name):
        self.owner = None
        self.name = None
        self.p_name = p_name
        html = self.__tool.get_response(self.__site, 'p/'+p_name+"?fromerr=HwU7RANg")

        id_match = re.search(self.__patter_id, html)
        owner_name_pattern = re.search(self.__owner_name_pattern, html)
        if owner_name_pattern:
            self.owner = owner_name_pattern.group(1)
            self.name = owner_name_pattern.group(2)
        if not id_match:
            return None
        self.project_id = id_match.group(1)
        if not self.project_id:
            return None
        return self.__get_comments(self.project_id)

    def __get_identity(self, query):
        print 'get comments identity', query
        search_response = self.__tool.get_response(self.__search_site, '?q='+query+'&scope=project')
        if not search_response:
            return None
        li_page = etree.HTML(search_response.decode('utf-8'))
        li = li_page.xpath(self.__search_path)
        if li:
            href = li[0].get('href')
            html = self.__tool.get_response(href, '')
            match = re.search(self.__patter_id, html)
            if match:
                project_id = match.group(1)
                if project_id:
                    return project_id
        print 'done'
        return None

    def __get_comments(self, project_id):
        if not (project_id and type(project_id) == str and str.isdigit(project_id)):
            print 'no project found'
            return None

        def get_comments(page):
            parameter = "?project="+project_id+"&p="+str(page)
            comments_response = self.__tool.read_response(self.__comments_site + parameter)
            page = etree.HTML(comments_response.decode('utf-8'))
            return page.xpath(self.__top_tweets)

        result = []
        i = 1
        while True:
            print 'comments in page', str(i)
            comments = get_comments(i)
            if not comments:
                print 'comments, done'
                return result
            i += 1
            for comment in comments:
                contents = (comment.xpath(u"text()"))
                content = (''.join(contents))
                result.append(content)

    def get_comment(self, query):
        return self.__get_comments(self.__get_identity(query))

if __name__ == '__main__':
    a = Comment()
    print a.get_comment('spring')