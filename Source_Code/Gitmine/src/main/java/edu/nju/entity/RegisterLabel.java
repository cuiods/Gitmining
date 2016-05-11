package edu.nju.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2016/5/11.
 */
@Entity
@Table(name = "register_label", schema = "gitmining", catalog = "")
public class RegisterLabel {
    private String register;
    private double web;
    private double activeRecord;
    private double app;
    private double api;
    private double framework;
    private double cms;
    private double django;
    private double emacs;
    private double irc;
    private double mac;
    private double management;
    private double linux;
    private double windows;
    private double interFace;
    private double os;
    private double server;
    private double source;
    private double textMate;
    private double tool;
    private double website;
    private double pulgin;
    private double json;
    private double tempLate;
    private double library;
    private double ui;
    private double database;
    private double other;

    @Id
    @Column(name = "register")
    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
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
    @Column(name = "active_record")
    public double getActiveRecord() {
        return activeRecord;
    }

    public void setActiveRecord(double activeRecord) {
        this.activeRecord = activeRecord;
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
    @Column(name = "irc")
    public double getIrc() {
        return irc;
    }

    public void setIrc(double irc) {
        this.irc = irc;
    }

    @Basic
    @Column(name = "mac")
    public double getMac() {
        return mac;
    }

    public void setMac(double mac) {
        this.mac = mac;
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
    @Column(name = "inter_face")
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
    @Column(name = "source")
    public double getSource() {
        return source;
    }

    public void setSource(double source) {
        this.source = source;
    }

    @Basic
    @Column(name = "text_mate")
    public double getTextMate() {
        return textMate;
    }

    public void setTextMate(double textMate) {
        this.textMate = textMate;
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
    @Column(name = "website")
    public double getWebsite() {
        return website;
    }

    public void setWebsite(double website) {
        this.website = website;
    }

    @Basic
    @Column(name = "pulgin")
    public double getPulgin() {
        return pulgin;
    }

    public void setPulgin(double pulgin) {
        this.pulgin = pulgin;
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
    @Column(name = "temp_late")
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
    public double getDatabase() {
        return database;
    }

    public void setDatabase(double database) {
        this.database = database;
    }

    @Basic
    @Column(name = "other")
    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterLabel that = (RegisterLabel) o;

        if (Double.compare(that.web, web) != 0) return false;
        if (Double.compare(that.activeRecord, activeRecord) != 0) return false;
        if (Double.compare(that.app, app) != 0) return false;
        if (Double.compare(that.api, api) != 0) return false;
        if (Double.compare(that.framework, framework) != 0) return false;
        if (Double.compare(that.cms, cms) != 0) return false;
        if (Double.compare(that.django, django) != 0) return false;
        if (Double.compare(that.emacs, emacs) != 0) return false;
        if (Double.compare(that.irc, irc) != 0) return false;
        if (Double.compare(that.mac, mac) != 0) return false;
        if (Double.compare(that.management, management) != 0) return false;
        if (Double.compare(that.linux, linux) != 0) return false;
        if (Double.compare(that.windows, windows) != 0) return false;
        if (Double.compare(that.interFace, interFace) != 0) return false;
        if (Double.compare(that.os, os) != 0) return false;
        if (Double.compare(that.server, server) != 0) return false;
        if (Double.compare(that.source, source) != 0) return false;
        if (Double.compare(that.textMate, textMate) != 0) return false;
        if (Double.compare(that.tool, tool) != 0) return false;
        if (Double.compare(that.website, website) != 0) return false;
        if (Double.compare(that.pulgin, pulgin) != 0) return false;
        if (Double.compare(that.json, json) != 0) return false;
        if (Double.compare(that.tempLate, tempLate) != 0) return false;
        if (Double.compare(that.library, library) != 0) return false;
        if (Double.compare(that.ui, ui) != 0) return false;
        if (Double.compare(that.database, database) != 0) return false;
        if (Double.compare(that.other, other) != 0) return false;
        if (register != null ? !register.equals(that.register) : that.register != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = register != null ? register.hashCode() : 0;
        temp = Double.doubleToLongBits(web);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(activeRecord);
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
        temp = Double.doubleToLongBits(irc);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(mac);
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
        temp = Double.doubleToLongBits(source);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(textMate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tool);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(website);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pulgin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(json);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tempLate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(library);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ui);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(database);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(other);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
