# -*- coding: UTF-8 -*-
import tool
from lxml import etree


class News(object):

    __search_site = 'http://www.oschina.net/search'
    __tool = None
    __news = u"//html/body/div[@id='OSC_Screen']/div[@id='OSC_Content']/div[@id='SearchPancel']/div[@id='SearchResults']/ul[@id='results']/li/p[@class='outline']"
    __source_url = u"//html/body/div[@id='OSC_Screen']/div[@id='OSC_Content']/div[@id='SearchPancel']/div[@id='SearchResults']/ul[@id='results']/li/p[@class='url']"
    __pager = u"//html/body/div[@id='OSC_Screen']/div[@id='OSC_Content']/div[@id='SearchPancel']/div[@id='SearchResults']/ul[@class='pager']/li/a"
    __query = 'http://www.oschina.net/search?scope=news&days=0&q=spring&p=2'

    def __init__(self):
        self.__tool = tool.Response()

    def __get_page(self):
        print 'try to read page number'
        parameter = '?scope=news&days=0&q=' + self.__query + "&p=1"
        response = self.__tool.get_response(self.__search_site, parameter)
        html = etree.HTML(response)
        li_news = html.xpath(self.__news)
        li_urls = html.xpath(self.__source_url)
        li_pager = html.xpath(self.__pager)

        num_page = 0
        for pager in li_pager:
            page = pager.xpath(u"text()")
            if page and str.isdigit(page[0]):
                page = int(page[0])
                num_page = page if page > num_page else num_page

        result = []
        for news in li_news:
            content = (''.join(news.xpath(u"text()")))
            result.append(content)
        urls = []
        for url in li_urls:
            content = (''.join(url.xpath(u"text()")))
            urls.append(content)
        return num_page, result, urls

    def __one_page_news(self, page):

        print 'try to read news in page:', page
        parameter = '?scope=news&days=0&q=' + self.__query+"&p="+str(page)
        response = self.__tool.get_response(self.__search_site, parameter)
        html = etree.HTML(response)
        li_news = html.xpath(self.__news)
        li_urls = html.xpath(self.__source_url)
        result = []
        for news in li_news:
            content = (''.join(news.xpath(u"text()")))
            result.append(content)
        urls = []
        for url in li_urls:
            content = (''.join(url.xpath(u"text()")))
            urls.append(content)
        return result, urls

    def get_news(self, query):
        self.__query = query
        num_page, result, urls = self.__get_page()
        num_page = min(num_page, 10)
        for page in range(2, num_page+1):
            temp_result, temp_urls = (self.__one_page_news(page))
            result.extend(temp_result)
            urls.extend(temp_urls)
        return result, urls

if __name__ == '__main__':
    a = News()
    result, urls = a.get_news('spring')
    print result
    print urls
