package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by Harry on 2016/5/28.
 */
@Entity
@Table(name = "sec_register_label", schema = "gitmining", catalog = "")
public class SecRegisterLabelEntity {
    private String registerLogin;
    private double nodeJs;
    private double javascript;
    private double library;
    private double ruby;
    private double web;
    private double api;
    private double vim;
    private double plugin;
    private double rust;
    private double app;
    private double client;
    private double server;
    private double json;
    private double framework;
    private double python;
    private double browser;
    private double rails;
    private double css;
    private double android;
    private double jquery;
    private double html;
    private double test;
    private double php;
    private double command;
    private double tool;
    private double demo;
    private double wrapper;
    private double ios;
    private double linux;
    private double windows;
    private double osX;
    private double django;
    private double google;
    private double generator;
    private double docker;
    private double image;
    private double template;

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
    public double getNodeJs() {
        return nodeJs;
    }

    public void setNodeJs(double nodeJs) {
        this.nodeJs = nodeJs;
    }

    @Basic
    @Column(name = "javascript")
    public double getJavascript() {
        return javascript;
    }

    public void setJavascript(double javascript) {
        this.javascript = javascript;
    }

    @Basic
    @Column(name = "library")
    public double getLibrary() {
        return library;
    }

    public void setLibrary(double library) {
        this.library = library;
    }

    @Basic
    @Column(name = "ruby")
    public double getRuby() {
        return ruby;
    }

    public void setRuby(double ruby) {
        this.ruby = ruby;
    }

    @Basic
    @Column(name = "web")
    public double getWeb() {
        return web;
    }

    public void setWeb(double web) {
        this.web = web;
    }

    @Basic
    @Column(name = "api")
    public double getApi() {
        return api;
    }

    public void setApi(double api) {
        this.api = api;
    }

    @Basic
    @Column(name = "vim")
    public double getVim() {
        return vim;
    }

    public void setVim(double vim) {
        this.vim = vim;
    }

    @Basic
    @Column(name = "plugin")
    public double getPlugin() {
        return plugin;
    }

    public void setPlugin(double plugin) {
        this.plugin = plugin;
    }

    @Basic
    @Column(name = "rust")
    public double getRust() {
        return rust;
    }

    public void setRust(double rust) {
        this.rust = rust;
    }

    @Basic
    @Column(name = "app")
    public double getApp() {
        return app;
    }

    public void setApp(double app) {
        this.app = app;
    }

    @Basic
    @Column(name = "client")
    public double getClient() {
        return client;
    }

    public void setClient(double client) {
        this.client = client;
    }

    @Basic
    @Column(name = "server")
    public double getServer() {
        return server;
    }

    public void setServer(double server) {
        this.server = server;
    }

    @Basic
    @Column(name = "json")
    public double getJson() {
        return json;
    }

    public void setJson(double json) {
        this.json = json;
    }

    @Basic
    @Column(name = "framework")
    public double getFramework() {
        return framework;
    }

    public void setFramework(double framework) {
        this.framework = framework;
    }

    @Basic
    @Column(name = "python")
    public double getPython() {
        return python;
    }

    public void setPython(double python) {
        this.python = python;
    }

    @Basic
    @Column(name = "browser")
    public double getBrowser() {
        return browser;
    }

    public void setBrowser(double browser) {
        this.browser = browser;
    }

    @Basic
    @Column(name = "rails")
    public double getRails() {
        return rails;
    }

    public void setRails(double rails) {
        this.rails = rails;
    }

    @Basic
    @Column(name = "css")
    public double getCss() {
        return css;
    }

    public void setCss(double css) {
        this.css = css;
    }

    @Basic
    @Column(name = "android")
    public double getAndroid() {
        return android;
    }

    public void setAndroid(double android) {
        this.android = android;
    }

    @Basic
    @Column(name = "jquery")
    public double getJquery() {
        return jquery;
    }

    public void setJquery(double jquery) {
        this.jquery = jquery;
    }

    @Basic
    @Column(name = "html")
    public double getHtml() {
        return html;
    }

    public void setHtml(double html) {
        this.html = html;
    }

    @Basic
    @Column(name = "test")
    public double getTest() {
        return test;
    }

    public void setTest(double test) {
        this.test = test;
    }

    @Basic
    @Column(name = "php")
    public double getPhp() {
        return php;
    }

    public void setPhp(double php) {
        this.php = php;
    }

    @Basic
    @Column(name = "command")
    public double getCommand() {
        return command;
    }

    public void setCommand(double command) {
        this.command = command;
    }

    @Basic
    @Column(name = "tool")
    public double getTool() {
        return tool;
    }

    public void setTool(double tool) {
        this.tool = tool;
    }

    @Basic
    @Column(name = "demo")
    public double getDemo() {
        return demo;
    }

    public void setDemo(double demo) {
        this.demo = demo;
    }

    @Basic
    @Column(name = "wrapper")
    public double getWrapper() {
        return wrapper;
    }

    public void setWrapper(double wrapper) {
        this.wrapper = wrapper;
    }

    @Basic
    @Column(name = "ios")
    public double getIos() {
        return ios;
    }

    public void setIos(double ios) {
        this.ios = ios;
    }

    @Basic
    @Column(name = "linux")
    public double getLinux() {
        return linux;
    }

    public void setLinux(double linux) {
        this.linux = linux;
    }

    @Basic
    @Column(name = "windows")
    public double getWindows() {
        return windows;
    }

    public void setWindows(double windows) {
        this.windows = windows;
    }

    @Basic
    @Column(name = "os_x")
    public double getOsX() {
        return osX;
    }

    public void setOsX(double osX) {
        this.osX = osX;
    }

    @Basic
    @Column(name = "django")
    public double getDjango() {
        return django;
    }

    public void setDjango(double django) {
        this.django = django;
    }

    @Basic
    @Column(name = "google")
    public double getGoogle() {
        return google;
    }

    public void setGoogle(double google) {
        this.google = google;
    }

    @Basic
    @Column(name = "generator")
    public double getGenerator() {
        return generator;
    }

    public void setGenerator(double generator) {
        this.generator = generator;
    }

    @Basic
    @Column(name = "docker")
    public double getDocker() {
        return docker;
    }

    public void setDocker(double docker) {
        this.docker = docker;
    }

    @Basic
    @Column(name = "image")
    public double getImage() {
        return image;
    }

    public void setImage(double image) {
        this.image = image;
    }

    @Basic
    @Column(name = "template")
    public double getTemplate() {
        return template;
    }

    public void setTemplate(double template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecRegisterLabelEntity that = (SecRegisterLabelEntity) o;

        if (Double.compare(that.nodeJs, nodeJs) != 0) return false;
        if (Double.compare(that.javascript, javascript) != 0) return false;
        if (Double.compare(that.library, library) != 0) return false;
        if (Double.compare(that.ruby, ruby) != 0) return false;
        if (Double.compare(that.web, web) != 0) return false;
        if (Double.compare(that.api, api) != 0) return false;
        if (Double.compare(that.vim, vim) != 0) return false;
        if (Double.compare(that.plugin, plugin) != 0) return false;
        if (Double.compare(that.rust, rust) != 0) return false;
        if (Double.compare(that.app, app) != 0) return false;
        if (Double.compare(that.client, client) != 0) return false;
        if (Double.compare(that.server, server) != 0) return false;
        if (Double.compare(that.json, json) != 0) return false;
        if (Double.compare(that.framework, framework) != 0) return false;
        if (Double.compare(that.python, python) != 0) return false;
        if (Double.compare(that.browser, browser) != 0) return false;
        if (Double.compare(that.rails, rails) != 0) return false;
        if (Double.compare(that.css, css) != 0) return false;
        if (Double.compare(that.android, android) != 0) return false;
        if (Double.compare(that.jquery, jquery) != 0) return false;
        if (Double.compare(that.html, html) != 0) return false;
        if (Double.compare(that.test, test) != 0) return false;
        if (Double.compare(that.php, php) != 0) return false;
        if (Double.compare(that.command, command) != 0) return false;
        if (Double.compare(that.tool, tool) != 0) return false;
        if (Double.compare(that.demo, demo) != 0) return false;
        if (Double.compare(that.wrapper, wrapper) != 0) return false;
        if (Double.compare(that.ios, ios) != 0) return false;
        if (Double.compare(that.linux, linux) != 0) return false;
        if (Double.compare(that.windows, windows) != 0) return false;
        if (Double.compare(that.osX, osX) != 0) return false;
        if (Double.compare(that.django, django) != 0) return false;
        if (Double.compare(that.google, google) != 0) return false;
        if (Double.compare(that.generator, generator) != 0) return false;
        if (Double.compare(that.docker, docker) != 0) return false;
        if (Double.compare(that.image, image) != 0) return false;
        if (Double.compare(that.template, template) != 0) return false;
        if (registerLogin != null ? !registerLogin.equals(that.registerLogin) : that.registerLogin != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = registerLogin != null ? registerLogin.hashCode() : 0;
        temp = Double.doubleToLongBits(nodeJs);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(javascript);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(library);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ruby);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(web);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(api);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(vim);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(plugin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rust);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(app);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(client);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(server);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(json);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(framework);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(python);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(browser);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rails);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(css);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(android);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(jquery);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(html);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(test);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(php);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(command);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tool);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(demo);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(wrapper);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ios);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(linux);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(windows);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(osX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(django);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(google);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(generator);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(docker);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(image);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(template);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
