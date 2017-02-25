# -*- coding: UTF-8 -*-
import sql
import comments
import news
import news_os
import traceback
import comments_os

mysql = sql.SQL()


def news_start_1000():
    my_news = news.News()
    repos = mysql.get_repo_without_news()
    if len(repos) == 0:
        print 'done'
        exit(0)
    for repo in repos:
        search_news, urls = my_news.get_news(repo[2])
        if search_news:
            mysql.insert_news(repo[0], search_news, urls)
            print '\033[1;31;40m'
            print repo, 'news ,done'
            print '\033[0m'
        else:
            mysql.insert_news(repo[0], [''], [''])
            print repo, 'failed'


def comments_start_1000():

    comment = comments.Comment()
    repos = mysql.get_repo_without_comments()
    if len(repos) == 0:
        print 'done'
        exit(0)
    for repo in repos:
        search_comments = comment.get_comment(repo[2])
        if search_comments:
            mysql.insert_comments(repo[0], search_comments)
            print '\033[1;31;40m'
            print repo, 'comments, done'
            print '\033[0m'
        else:
            mysql.insert_comments(repo[0], [''])
            print repo, 'comments, failed'


def comments_start():

    while True:
        comments_start_1000()
        print '\033[1;31;40m'
        print '*'*50
        print '1000, done'
        print '*'*50
        print '\033[0m'


def news_start():

    while True:
        news_start_1000()
        print '\033[1;31;40m'
        print '*'*50
        print '1000, done'
        print '*'*50
        print '\033[0m'


def news_os_start():

    identity = mysql.get_next_news_id()
    my_news_os = news_os.NewsOS()
    while True:
        try:
            news_body = my_news_os.launcher(identity)
            mysql.insert_sec_news(news_body)
            print '\033[1;31;40m'
            print 'news, done'
            print '\033[0m'
        except:
            traceback.print_exc()
        finally:
            identity += 1


def comments_os_start():
    page = mysql.get_next_page_project()
    my_comments_os = comments_os.CommentsOS()
    my_comments = comments.Comment()
    while True:
        project_names = my_comments_os.projects_url(page)
        for project_name in project_names:
            project_comments = my_comments.get_comments_by_url(project_name)
            if project_comments and my_comments.project_id:
                project = (my_comments.p_name, my_comments.project_id, my_comments.owner, my_comments.name, page)
                mysql.insert_project_os(project)
                mysql.insert_comments_os(my_comments.p_name, project_comments)
        page += 1


if __name__ == "__main__":

    # comments_start()
    # news_start()
    # news_os_start()
    comments_os_start()
