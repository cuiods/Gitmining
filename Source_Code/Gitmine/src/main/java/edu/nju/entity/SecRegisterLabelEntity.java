package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by Harry on 2016/5/27.
 */
@Entity
@Table(name = "sec_register_label", schema = "gitmining", catalog = "")
public class SecRegisterLabelEntity {
    private String registerLogin;
    private int nodeJs;
    private int javascript;
    private int library;
    private int ruby;
    private int web;
    private int api;
    private int vim;
    private int plugin;
    private int rust;
    private int app;
    private int client;
    private int server;
    private int json;
    private int framework;
    private int python;
    private int browser;
    private int rails;
    private int css;
    private int android;
    private int jquery;
    private int html;
    private int test;
    private int php;
    private int command;
    private int tool;
    private int demo;
    private int wrapper;
    private int ios;
    private int linux;
    private int windows;
    private int osX;
    private int django;
    private int google;
    private int generator;
    private int docker;
    private int image;
    private int template;

    @Id
    @Column(name = "register_login")
    public String getRegisterLogin() {
        return registerLogin;
    }

    public void setRegisterLogin(String registerLogin) {
        this.registerLogin = registerLogin;
    }

    @Basic
    @Column(name = "node_js")
    public int getNodeJs() {
        return nodeJs;
    }

    public void setNodeJs(int nodeJs) {
        this.nodeJs = nodeJs;
    }

    @Basic
    @Column(name = "javascript")
    public int getJavascript() {
        return javascript;
    }

    public void setJavascript(int javascript) {
        this.javascript = javascript;
    }

    @Basic
    @Column(name = "library")
    public int getLibrary() {
        return library;
    }

    public void setLibrary(int library) {
        this.library = library;
    }

    @Basic
    @Column(name = "ruby")
    public int getRuby() {
        return ruby;
    }

    public void setRuby(int ruby) {
        this.ruby = ruby;
    }

    @Basic
    @Column(name = "web")
    public int getWeb() {
        return web;
    }

    public void setWeb(int web) {
        this.web = web;
    }

    @Basic
    @Column(name = "api")
    public int getApi() {
        return api;
    }

    public void setApi(int api) {
        this.api = api;
    }

    @Basic
    @Column(name = "vim")
    public int getVim() {
        return vim;
    }

    public void setVim(int vim) {
        this.vim = vim;
    }

    @Basic
    @Column(name = "plugin")
    public int getPlugin() {
        return plugin;
    }

    public void setPlugin(int plugin) {
        this.plugin = plugin;
    }

    @Basic
    @Column(name = "rust")
    public int getRust() {
        return rust;
    }

    public void setRust(int rust) {
        this.rust = rust;
    }

    @Basic
    @Column(name = "app")
    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    @Basic
    @Column(name = "client")
    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    @Basic
    @Column(name = "server")
    public int getServer() {
        return server;
    }

    public void setServer(int server) {
        this.server = server;
    }

    @Basic
    @Column(name = "json")
    public int getJson() {
        return json;
    }

    public void setJson(int json) {
        this.json = json;
    }

    @Basic
    @Column(name = "framework")
    public int getFramework() {
        return framework;
    }

    public void setFramework(int framework) {
        this.framework = framework;
    }

    @Basic
    @Column(name = "python")
    public int getPython() {
        return python;
    }

    public void setPython(int python) {
        this.python = python;
    }

    @Basic
    @Column(name = "browser")
    public int getBrowser() {
        return browser;
    }

    public void setBrowser(int browser) {
        this.browser = browser;
    }

    @Basic
    @Column(name = "rails")
    public int getRails() {
        return rails;
    }

    public void setRails(int rails) {
        this.rails = rails;
    }

    @Basic
    @Column(name = "css")
    public int getCss() {
        return css;
    }

    public void setCss(int css) {
        this.css = css;
    }

    @Basic
    @Column(name = "android")
    public int getAndroid() {
        return android;
    }

    public void setAndroid(int android) {
        this.android = android;
    }

    @Basic
    @Column(name = "jquery")
    public int getJquery() {
        return jquery;
    }

    public void setJquery(int jquery) {
        this.jquery = jquery;
    }

    @Basic
    @Column(name = "html")
    public int getHtml() {
        return html;
    }

    public void setHtml(int html) {
        this.html = html;
    }

    @Basic
    @Column(name = "test")
    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    @Basic
    @Column(name = "php")
    public int getPhp() {
        return php;
    }

    public void setPhp(int php) {
        this.php = php;
    }

    @Basic
    @Column(name = "command")
    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    @Basic
    @Column(name = "tool")
    public int getTool() {
        return tool;
    }

    public void setTool(int tool) {
        this.tool = tool;
    }

    @Basic
    @Column(name = "demo")
    public int getDemo() {
        return demo;
    }

    public void setDemo(int demo) {
        this.demo = demo;
    }

    @Basic
    @Column(name = "wrapper")
    public int getWrapper() {
        return wrapper;
    }

    public void setWrapper(int wrapper) {
        this.wrapper = wrapper;
    }

    @Basic
    @Column(name = "ios")
    public int getIos() {
        return ios;
    }

    public void setIos(int ios) {
        this.ios = ios;
    }

    @Basic
    @Column(name = "linux")
    public int getLinux() {
        return linux;
    }

    public void setLinux(int linux) {
        this.linux = linux;
    }

    @Basic
    @Column(name = "windows")
    public int getWindows() {
        return windows;
    }

    public void setWindows(int windows) {
        this.windows = windows;
    }

    @Basic
    @Column(name = "os_x")
    public int getOsX() {
        return osX;
    }

    public void setOsX(int osX) {
        this.osX = osX;
    }

    @Basic
    @Column(name = "django")
    public int getDjango() {
        return django;
    }

    public void setDjango(int django) {
        this.django = django;
    }

    @Basic
    @Column(name = "google")
    public int getGoogle() {
        return google;
    }

    public void setGoogle(int google) {
        this.google = google;
    }

    @Basic
    @Column(name = "generator")
    public int getGenerator() {
        return generator;
    }

    public void setGenerator(int generator) {
        this.generator = generator;
    }

    @Basic
    @Column(name = "docker")
    public int getDocker() {
        return docker;
    }

    public void setDocker(int docker) {
        this.docker = docker;
    }

    @Basic
    @Column(name = "image")
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Basic
    @Column(name = "template")
    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecRegisterLabelEntity that = (SecRegisterLabelEntity) o;

        if (nodeJs != that.nodeJs) return false;
        if (javascript != that.javascript) return false;
        if (library != that.library) return false;
        if (ruby != that.ruby) return false;
        if (web != that.web) return false;
        if (api != that.api) return false;
        if (vim != that.vim) return false;
        if (plugin != that.plugin) return false;
        if (rust != that.rust) return false;
        if (app != that.app) return false;
        if (client != that.client) return false;
        if (server != that.server) return false;
        if (json != that.json) return false;
        if (framework != that.framework) return false;
        if (python != that.python) return false;
        if (browser != that.browser) return false;
        if (rails != that.rails) return false;
        if (css != that.css) return false;
        if (android != that.android) return false;
        if (jquery != that.jquery) return false;
        if (html != that.html) return false;
        if (test != that.test) return false;
        if (php != that.php) return false;
        if (command != that.command) return false;
        if (tool != that.tool) return false;
        if (demo != that.demo) return false;
        if (wrapper != that.wrapper) return false;
        if (ios != that.ios) return false;
        if (linux != that.linux) return false;
        if (windows != that.windows) return false;
        if (osX != that.osX) return false;
        if (django != that.django) return false;
        if (google != that.google) return false;
        if (generator != that.generator) return false;
        if (docker != that.docker) return false;
        if (image != that.image) return false;
        if (template != that.template) return false;
        if (registerLogin != null ? !registerLogin.equals(that.registerLogin) : that.registerLogin != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = registerLogin != null ? registerLogin.hashCode() : 0;
        result = 31 * result + nodeJs;
        result = 31 * result + javascript;
        result = 31 * result + library;
        result = 31 * result + ruby;
        result = 31 * result + web;
        result = 31 * result + api;
        result = 31 * result + vim;
        result = 31 * result + plugin;
        result = 31 * result + rust;
        result = 31 * result + app;
        result = 31 * result + client;
        result = 31 * result + server;
        result = 31 * result + json;
        result = 31 * result + framework;
        result = 31 * result + python;
        result = 31 * result + browser;
        result = 31 * result + rails;
        result = 31 * result + css;
        result = 31 * result + android;
        result = 31 * result + jquery;
        result = 31 * result + html;
        result = 31 * result + test;
        result = 31 * result + php;
        result = 31 * result + command;
        result = 31 * result + tool;
        result = 31 * result + demo;
        result = 31 * result + wrapper;
        result = 31 * result + ios;
        result = 31 * result + linux;
        result = 31 * result + windows;
        result = 31 * result + osX;
        result = 31 * result + django;
        result = 31 * result + google;
        result = 31 * result + generator;
        result = 31 * result + docker;
        result = 31 * result + image;
        result = 31 * result + template;
        return result;
    }
}
