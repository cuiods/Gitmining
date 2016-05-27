package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by Harry on 2016/5/27.
 */
@Entity
@Table(name = "sec_repo_label", schema = "gitmining", catalog = "")
public class SecRepoLabelEntity {
    private long repoId;
    private Integer nodeJs = 0;
    private Integer javascript = 0;
    private Integer library = 0;
    private Integer ruby = 0;
    private Integer web = 0;
    private Integer api = 0;
    private Integer vim = 0;
    private Integer plugin = 0;
    private Integer rust = 0;
    private Integer app = 0;
    private Integer client = 0;
    private Integer server = 0;
    private Integer python = 0;
    private Integer framework = 0;
    private Integer json = 0;
    private Integer browser = 0;
    private Integer rails = 0;
    private Integer css = 0;
    private Integer android = 0;
    private Integer jquery = 0;
    private Integer html = 0;
    private Integer test = 0;
    private Integer php = 0;
    private Integer command = 0;
    private Integer tool = 0;
    private Integer demo = 0;
    private Integer wrapper = 0;
    private Integer ios = 0;
    private Integer linux = 0;
    private Integer windows = 0;
    private Integer osX = 0;
    private Integer django = 0;
    private Integer google = 0;
    private Integer generator = 0;
    private Integer docker = 0;
    private Integer image = 0;
    private Integer template = 0;

    @Id
    @Column(name = "repo_id")
    public long getRepoId() {
        return repoId;
    }

    public void setRepoId(long repoId) {
        this.repoId = repoId;
    }

    @Basic
    @Column(name = "node_js")
    public Integer getNodeJs() {
        return nodeJs;
    }

    public void setNodeJs(Integer nodeJs) {
        this.nodeJs = nodeJs;
    }

    @Basic
    @Column(name = "javascript")
    public Integer getJavascript() {
        return javascript;
    }

    public void setJavascript(Integer javascript) {
        this.javascript = javascript;
    }

    @Basic
    @Column(name = "library")
    public Integer getLibrary() {
        return library;
    }

    public void setLibrary(Integer library) {
        this.library = library;
    }

    @Basic
    @Column(name = "ruby")
    public Integer getRuby() {
        return ruby;
    }

    public void setRuby(Integer ruby) {
        this.ruby = ruby;
    }

    @Basic
    @Column(name = "web")
    public Integer getWeb() {
        return web;
    }

    public void setWeb(Integer web) {
        this.web = web;
    }

    @Basic
    @Column(name = "api")
    public Integer getApi() {
        return api;
    }

    public void setApi(Integer api) {
        this.api = api;
    }

    @Basic
    @Column(name = "vim")
    public Integer getVim() {
        return vim;
    }

    public void setVim(Integer vim) {
        this.vim = vim;
    }

    @Basic
    @Column(name = "plugin")
    public Integer getPlugin() {
        return plugin;
    }

    public void setPlugin(Integer plugin) {
        this.plugin = plugin;
    }

    @Basic
    @Column(name = "rust")
    public Integer getRust() {
        return rust;
    }

    public void setRust(Integer rust) {
        this.rust = rust;
    }

    @Basic
    @Column(name = "app")
    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app;
    }

    @Basic
    @Column(name = "client")
    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    @Basic
    @Column(name = "server")
    public Integer getServer() {
        return server;
    }

    public void setServer(Integer server) {
        this.server = server;
    }

    @Basic
    @Column(name = "python")
    public Integer getPython() {
        return python;
    }

    public void setPython(Integer python) {
        this.python = python;
    }

    @Basic
    @Column(name = "framework")
    public Integer getFramework() {
        return framework;
    }

    public void setFramework(Integer framework) {
        this.framework = framework;
    }

    @Basic
    @Column(name = "json")
    public Integer getJson() {
        return json;
    }

    public void setJson(Integer json) {
        this.json = json;
    }

    @Basic
    @Column(name = "browser")
    public Integer getBrowser() {
        return browser;
    }

    public void setBrowser(Integer browser) {
        this.browser = browser;
    }

    @Basic
    @Column(name = "rails")
    public Integer getRails() {
        return rails;
    }

    public void setRails(Integer rails) {
        this.rails = rails;
    }

    @Basic
    @Column(name = "css")
    public Integer getCss() {
        return css;
    }

    public void setCss(Integer css) {
        this.css = css;
    }

    @Basic
    @Column(name = "android")
    public Integer getAndroid() {
        return android;
    }

    public void setAndroid(Integer android) {
        this.android = android;
    }

    @Basic
    @Column(name = "jquery")
    public Integer getJquery() {
        return jquery;
    }

    public void setJquery(Integer jquery) {
        this.jquery = jquery;
    }

    @Basic
    @Column(name = "html")
    public Integer getHtml() {
        return html;
    }

    public void setHtml(Integer html) {
        this.html = html;
    }

    @Basic
    @Column(name = "test")
    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    @Basic
    @Column(name = "php")
    public Integer getPhp() {
        return php;
    }

    public void setPhp(Integer php) {
        this.php = php;
    }

    @Basic
    @Column(name = "command")
    public Integer getCommand() {
        return command;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }

    @Basic
    @Column(name = "tool")
    public Integer getTool() {
        return tool;
    }

    public void setTool(Integer tool) {
        this.tool = tool;
    }

    @Basic
    @Column(name = "demo")
    public Integer getDemo() {
        return demo;
    }

    public void setDemo(Integer demo) {
        this.demo = demo;
    }

    @Basic
    @Column(name = "wrapper")
    public Integer getWrapper() {
        return wrapper;
    }

    public void setWrapper(Integer wrapper) {
        this.wrapper = wrapper;
    }

    @Basic
    @Column(name = "ios")
    public Integer getIos() {
        return ios;
    }

    public void setIos(Integer ios) {
        this.ios = ios;
    }

    @Basic
    @Column(name = "linux")
    public Integer getLinux() {
        return linux;
    }

    public void setLinux(Integer linux) {
        this.linux = linux;
    }

    @Basic
    @Column(name = "windows")
    public Integer getWindows() {
        return windows;
    }

    public void setWindows(Integer windows) {
        this.windows = windows;
    }

    @Basic
    @Column(name = "os_x")
    public Integer getOsX() {
        return osX;
    }

    public void setOsX(Integer osX) {
        this.osX = osX;
    }

    @Basic
    @Column(name = "django")
    public Integer getDjango() {
        return django;
    }

    public void setDjango(Integer django) {
        this.django = django;
    }

    @Basic
    @Column(name = "google")
    public Integer getGoogle() {
        return google;
    }

    public void setGoogle(Integer google) {
        this.google = google;
    }

    @Basic
    @Column(name = "generator")
    public Integer getGenerator() {
        return generator;
    }

    public void setGenerator(Integer generator) {
        this.generator = generator;
    }

    @Basic
    @Column(name = "docker")
    public Integer getDocker() {
        return docker;
    }

    public void setDocker(Integer docker) {
        this.docker = docker;
    }

    @Basic
    @Column(name = "image")
    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    @Basic
    @Column(name = "template")
    public Integer getTemplate() {
        return template;
    }

    public void setTemplate(Integer template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecRepoLabelEntity that = (SecRepoLabelEntity) o;

        if (repoId != that.repoId) return false;
        if (nodeJs != null ? !nodeJs.equals(that.nodeJs) : that.nodeJs != null) return false;
        if (javascript != null ? !javascript.equals(that.javascript) : that.javascript != null) return false;
        if (library != null ? !library.equals(that.library) : that.library != null) return false;
        if (ruby != null ? !ruby.equals(that.ruby) : that.ruby != null) return false;
        if (web != null ? !web.equals(that.web) : that.web != null) return false;
        if (api != null ? !api.equals(that.api) : that.api != null) return false;
        if (vim != null ? !vim.equals(that.vim) : that.vim != null) return false;
        if (plugin != null ? !plugin.equals(that.plugin) : that.plugin != null) return false;
        if (rust != null ? !rust.equals(that.rust) : that.rust != null) return false;
        if (app != null ? !app.equals(that.app) : that.app != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (server != null ? !server.equals(that.server) : that.server != null) return false;
        if (python != null ? !python.equals(that.python) : that.python != null) return false;
        if (framework != null ? !framework.equals(that.framework) : that.framework != null) return false;
        if (json != null ? !json.equals(that.json) : that.json != null) return false;
        if (browser != null ? !browser.equals(that.browser) : that.browser != null) return false;
        if (rails != null ? !rails.equals(that.rails) : that.rails != null) return false;
        if (css != null ? !css.equals(that.css) : that.css != null) return false;
        if (android != null ? !android.equals(that.android) : that.android != null) return false;
        if (jquery != null ? !jquery.equals(that.jquery) : that.jquery != null) return false;
        if (html != null ? !html.equals(that.html) : that.html != null) return false;
        if (test != null ? !test.equals(that.test) : that.test != null) return false;
        if (php != null ? !php.equals(that.php) : that.php != null) return false;
        if (command != null ? !command.equals(that.command) : that.command != null) return false;
        if (tool != null ? !tool.equals(that.tool) : that.tool != null) return false;
        if (demo != null ? !demo.equals(that.demo) : that.demo != null) return false;
        if (wrapper != null ? !wrapper.equals(that.wrapper) : that.wrapper != null) return false;
        if (ios != null ? !ios.equals(that.ios) : that.ios != null) return false;
        if (linux != null ? !linux.equals(that.linux) : that.linux != null) return false;
        if (windows != null ? !windows.equals(that.windows) : that.windows != null) return false;
        if (osX != null ? !osX.equals(that.osX) : that.osX != null) return false;
        if (django != null ? !django.equals(that.django) : that.django != null) return false;
        if (google != null ? !google.equals(that.google) : that.google != null) return false;
        if (generator != null ? !generator.equals(that.generator) : that.generator != null) return false;
        if (docker != null ? !docker.equals(that.docker) : that.docker != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (template != null ? !template.equals(that.template) : that.template != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (repoId ^ (repoId >>> 32));
        result = 31 * result + (nodeJs != null ? nodeJs.hashCode() : 0);
        result = 31 * result + (javascript != null ? javascript.hashCode() : 0);
        result = 31 * result + (library != null ? library.hashCode() : 0);
        result = 31 * result + (ruby != null ? ruby.hashCode() : 0);
        result = 31 * result + (web != null ? web.hashCode() : 0);
        result = 31 * result + (api != null ? api.hashCode() : 0);
        result = 31 * result + (vim != null ? vim.hashCode() : 0);
        result = 31 * result + (plugin != null ? plugin.hashCode() : 0);
        result = 31 * result + (rust != null ? rust.hashCode() : 0);
        result = 31 * result + (app != null ? app.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (server != null ? server.hashCode() : 0);
        result = 31 * result + (python != null ? python.hashCode() : 0);
        result = 31 * result + (framework != null ? framework.hashCode() : 0);
        result = 31 * result + (json != null ? json.hashCode() : 0);
        result = 31 * result + (browser != null ? browser.hashCode() : 0);
        result = 31 * result + (rails != null ? rails.hashCode() : 0);
        result = 31 * result + (css != null ? css.hashCode() : 0);
        result = 31 * result + (android != null ? android.hashCode() : 0);
        result = 31 * result + (jquery != null ? jquery.hashCode() : 0);
        result = 31 * result + (html != null ? html.hashCode() : 0);
        result = 31 * result + (test != null ? test.hashCode() : 0);
        result = 31 * result + (php != null ? php.hashCode() : 0);
        result = 31 * result + (command != null ? command.hashCode() : 0);
        result = 31 * result + (tool != null ? tool.hashCode() : 0);
        result = 31 * result + (demo != null ? demo.hashCode() : 0);
        result = 31 * result + (wrapper != null ? wrapper.hashCode() : 0);
        result = 31 * result + (ios != null ? ios.hashCode() : 0);
        result = 31 * result + (linux != null ? linux.hashCode() : 0);
        result = 31 * result + (windows != null ? windows.hashCode() : 0);
        result = 31 * result + (osX != null ? osX.hashCode() : 0);
        result = 31 * result + (django != null ? django.hashCode() : 0);
        result = 31 * result + (google != null ? google.hashCode() : 0);
        result = 31 * result + (generator != null ? generator.hashCode() : 0);
        result = 31 * result + (docker != null ? docker.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (template != null ? template.hashCode() : 0);
        return result;
    }
}
