package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/12.
 */
@Entity
@Table(name = "repo_label", schema = "gitmining", catalog = "")
@IdClass(RepoLabelPK.class)
public class RepoLabel {
    private String repo;
    private String repoOwner;
    private double web;
    private double app;
    private double api;
    private double framework;
    private double cms;
    private double django;
    private double emacs;
    private double management;
    private double linux;
    private double windows;
    private double interFace;
    private double os;
    private double server;
    private double tool;
    private double plugin;
    private double json;
    private double tempLate;
    private double library;
    private double ui;
    private double dataBase;

    @Id
    @Column(name = "repo")
    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    @Id
    @Column(name = "repo_owner")
    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
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
    @Column(name = "app")
    public double getApp() {
        return app;
    }

    public void setApp(double app) {
        this.app = app;
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
    @Column(name = "framework")
    public double getFramework() {
        return framework;
    }

    public void setFramework(double framework) {
        this.framework = framework;
    }

    @Basic
    @Column(name = "cms")
    public double getCms() {
        return cms;
    }

    public void setCms(double cms) {
        this.cms = cms;
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
    @Column(name = "emacs")
    public double getEmacs() {
        return emacs;
    }

    public void setEmacs(double emacs) {
        this.emacs = emacs;
    }

    @Basic
    @Column(name = "management")
    public double getManagement() {
        return management;
    }

    public void setManagement(double management) {
        this.management = management;
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
    @Column(name = "interface")
    public double getInterFace() {
        return interFace;
    }

    public void setInterFace(double interFace) {
        this.interFace = interFace;
    }

    @Basic
    @Column(name = "os")
    public double getOs() {
        return os;
    }

    public void setOs(double os) {
        this.os = os;
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
    @Column(name = "tool")
    public double getTool() {
        return tool;
    }

    public void setTool(double tool) {
        this.tool = tool;
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
    @Column(name = "json")
    public double getJson() {
        return json;
    }

    public void setJson(double json) {
        this.json = json;
    }

    @Basic
    @Column(name = "template")
    public double getTempLate() {
        return tempLate;
    }

    public void setTempLate(double tempLate) {
        this.tempLate = tempLate;
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
    @Column(name = "ui")
    public double getUi() {
        return ui;
    }

    public void setUi(double ui) {
        this.ui = ui;
    }

    @Basic
    @Column(name = "database")
    public double getDataBase() {
        return dataBase;
    }

    public void setDataBase(double dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepoLabel repoLabel = (RepoLabel) o;

        if (Double.compare(repoLabel.web, web) != 0) return false;
        if (Double.compare(repoLabel.app, app) != 0) return false;
        if (Double.compare(repoLabel.api, api) != 0) return false;
        if (Double.compare(repoLabel.framework, framework) != 0) return false;
        if (Double.compare(repoLabel.cms, cms) != 0) return false;
        if (Double.compare(repoLabel.django, django) != 0) return false;
        if (Double.compare(repoLabel.emacs, emacs) != 0) return false;
        if (Double.compare(repoLabel.management, management) != 0) return false;
        if (Double.compare(repoLabel.linux, linux) != 0) return false;
        if (Double.compare(repoLabel.windows, windows) != 0) return false;
        if (Double.compare(repoLabel.interFace, interFace) != 0) return false;
        if (Double.compare(repoLabel.os, os) != 0) return false;
        if (Double.compare(repoLabel.server, server) != 0) return false;
        if (Double.compare(repoLabel.tool, tool) != 0) return false;
        if (Double.compare(repoLabel.plugin, plugin) != 0) return false;
        if (Double.compare(repoLabel.json, json) != 0) return false;
        if (Double.compare(repoLabel.tempLate, tempLate) != 0) return false;
        if (Double.compare(repoLabel.library, library) != 0) return false;
        if (Double.compare(repoLabel.ui, ui) != 0) return false;
        if (Double.compare(repoLabel.dataBase, dataBase) != 0) return false;
        if (repo != null ? !repo.equals(repoLabel.repo) : repoLabel.repo != null) return false;
        if (repoOwner != null ? !repoOwner.equals(repoLabel.repoOwner) : repoLabel.repoOwner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = repo != null ? repo.hashCode() : 0;
        result = 31 * result + (repoOwner != null ? repoOwner.hashCode() : 0);
        temp = Double.doubleToLongBits(web);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(app);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(api);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(framework);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cms);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(django);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(emacs);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(management);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(linux);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(windows);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(interFace);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(os);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(server);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tool);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(plugin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(json);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tempLate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(library);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ui);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dataBase);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
