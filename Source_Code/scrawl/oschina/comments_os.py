from lxml import etree
import tool


class CommentsOS(object):

    __project_site = 'http://www.oschina.net/project/list?prefix=&sort=view&p='
    __site = 'http://www.oschina.net/'
    __href_path = u"//div[@class='ProjectList']/ul[@class='List']/li/h3/a"

    def __init__(self):
        self.__tool = tool.Response()

    def projects_url(self, page=1):
        response = self.__tool.read_response(self.__project_site+str(page))
        html = etree.HTML(response.decode('utf-8'))
        li_url = html.xpath(self.__href_path)
        project_name = []
        if li_url:
            for li in li_url:
                url = (li.get('href'))
                project_name.append(url.split('/')[-1])
        return project_name

if __name__ == '__main__':
    os = CommentsOS()
    print os.projects_url()
