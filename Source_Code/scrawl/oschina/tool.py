import urllib2
import re
import time


class Response(object):

    __headers = {
        'Connection': 'keep-alive',
        'Cache-Control': 'max-age=0',
        'Accept': 'text/html, */*; q=0.01',
        'X-Requested-With': 'XMLHttpRequest',
        'User-Agent': 'Mozilla 5.10',
        'Accept-Language': 'zh-CN,zh;q=0.8,ja;q=0.6'
    }
    __patter_redirect = re.compile(r"""<script>(\s*)location.href="(.*)"(\s*);(\s*)</script>""");
    __timer_interval = 1

    def get_response(self, url_site, parameter):
        print url_site+parameter
        html = None
        try:
            for i in range(2):
                time.sleep(self.__timer_interval)
                request = urllib2.Request(url=url_site + parameter, headers=self.__headers)
                response = urllib2.urlopen(request, timeout=10)
                html = response.read()
                match = re.search(self.__patter_redirect, html)
                if match:
                    print 'js jump next.'
                    parameter = match.group(2)
                    print parameter
                else:
                    print 'done'
        except ex:
            print ex
        finally:
            return html

    def read_response(self, url):
        print url
        time.sleep(self.__timer_interval)
        request = urllib2.Request(url=url, headers=self.__headers)
        response = urllib2.urlopen(request, timeout=10)
        print 'done'
        return response.read()
