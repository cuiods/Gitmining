package edu.nju.model.imp;

import edu.nju.entity.SecRegisterLabelEntity;
import edu.nju.entity.SecRepoLabelEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Harry on 2016/6/2.
 */
@Service
public class LabelCalculator {

    public SecRegisterLabelEntity starRepoCompute(SecRegisterLabelEntity registerLabel, SecRepoLabelEntity repoLabel){
        registerLabel.setNodeJs(repoLabel.getNodeJs()/(registerLabel.getNodeJs()+1)+registerLabel.getNodeJs());
        registerLabel.setJavascript(repoLabel.getJavascript()/(registerLabel.getJavascript()+1)+registerLabel.getJavascript());
        registerLabel.setLibrary(repoLabel.getLibrary()/(registerLabel.getLibrary()+1)+registerLabel.getLibrary());
        registerLabel.setRuby(repoLabel.getRuby()/(registerLabel.getRuby()+1)+registerLabel.getRuby());
        registerLabel.setWeb(repoLabel.getWeb()/(registerLabel.getWeb()+1)+registerLabel.getWeb());
        registerLabel.setApi(repoLabel.getApi()/(registerLabel.getApi()+1)+registerLabel.getApi());
        registerLabel.setVim(repoLabel.getVim()/(registerLabel.getVim()+1)+registerLabel.getVim());
        registerLabel.setPlugin(repoLabel.getPlugin()/(registerLabel.getPlugin()+1)+registerLabel.getPlugin());
        registerLabel.setRust(repoLabel.getRust()/(registerLabel.getRust()+1)+registerLabel.getRust());
        registerLabel.setApp(repoLabel.getApp()/(registerLabel.getApp()+1)+registerLabel.getApp());
        registerLabel.setClient(repoLabel.getClient()/(registerLabel.getClient()+1)+registerLabel.getClient());
        registerLabel.setServer(repoLabel.getServer()/(registerLabel.getServer()+1)+registerLabel.getServer());
        registerLabel.setJson(repoLabel.getJson()/(registerLabel.getJson()+1)+registerLabel.getJson());
        registerLabel.setFramework(repoLabel.getFramework()/(registerLabel.getFramework()+1)+registerLabel.getFramework());
        registerLabel.setPython(repoLabel.getPython()/(registerLabel.getPython()+1)+registerLabel.getPython());
        registerLabel.setBrowser(repoLabel.getBrowser()/(registerLabel.getBrowser()+1)+registerLabel.getBrowser());
        registerLabel.setRails(repoLabel.getRails()/(registerLabel.getRails()+1)+registerLabel.getRails());
        registerLabel.setCss(repoLabel.getCss()/(registerLabel.getCss()+1)+registerLabel.getCss());
        registerLabel.setAndroid(repoLabel.getAndroid()/(registerLabel.getAndroid()+1)+registerLabel.getAndroid());
        registerLabel.setJquery(repoLabel.getJquery()/(registerLabel.getJquery()+1)+registerLabel.getJquery());
        registerLabel.setHtml(repoLabel.getHtml()/(registerLabel.getHtml()+1)+registerLabel.getHtml());
        registerLabel.setTest(repoLabel.getTest()/(registerLabel.getTest()+1)+registerLabel.getTest());
        registerLabel.setPhp(repoLabel.getPhp()/(registerLabel.getPhp()+1)+registerLabel.getPhp());
        registerLabel.setCommand(repoLabel.getCommand()/(registerLabel.getCommand()+1)+registerLabel.getCommand());
        registerLabel.setTool(repoLabel.getTool()/(registerLabel.getTool()+1)+registerLabel.getTool());
        registerLabel.setDemo(repoLabel.getDemo()/(registerLabel.getDemo()+1)+registerLabel.getDemo());
        registerLabel.setWrapper(repoLabel.getWrapper()/(registerLabel.getWrapper()+1)+registerLabel.getWrapper());
        registerLabel.setIos(repoLabel.getIos()/(registerLabel.getIos()+1)+registerLabel.getIos());
        registerLabel.setLinux(repoLabel.getLinux()/(registerLabel.getLinux()+1)+registerLabel.getLinux());
        registerLabel.setWindows(repoLabel.getWindows()/(registerLabel.getWindows()+1)+registerLabel.getWindows());
        registerLabel.setOsX(repoLabel.getOsX()/(registerLabel.getOsX()+1)+registerLabel.getOsX());
        registerLabel.setDjango(repoLabel.getDjango()/(registerLabel.getDjango()+1)+registerLabel.getDjango());
        registerLabel.setGoogle(repoLabel.getGoogle()/(registerLabel.getGoogle()+1)+registerLabel.getGoogle());
        registerLabel.setGenerator(repoLabel.getGenerator()/(registerLabel.getGenerator()+1)+registerLabel.getGenerator());
        registerLabel.setDocker(repoLabel.getDocker()/(registerLabel.getDocker()+1)+registerLabel.getDocker());
        registerLabel.setImage(repoLabel.getImage()/(registerLabel.getImage()+1)+registerLabel.getImage());
        registerLabel.setTemplate(repoLabel.getTemplate()/(registerLabel.getTemplate()+1)+registerLabel.getTemplate());

        return registerLabel;
    }

    public SecRegisterLabelEntity unstarRepoCompute(SecRegisterLabelEntity registerLabel,SecRepoLabelEntity repoLabel){
        registerLabel.setNodeJs(-repoLabel.getNodeJs()/(registerLabel.getNodeJs()+1)+registerLabel.getNodeJs());
        registerLabel.setJavascript(-repoLabel.getJavascript()/(registerLabel.getJavascript()+1)+registerLabel.getJavascript());
        registerLabel.setLibrary(-repoLabel.getLibrary()/(registerLabel.getLibrary()+1)+registerLabel.getLibrary());
        registerLabel.setRuby(-repoLabel.getRuby()/(registerLabel.getRuby()+1)+registerLabel.getRuby());
        registerLabel.setWeb(-repoLabel.getWeb()/(registerLabel.getWeb()+1)+registerLabel.getWeb());
        registerLabel.setApi(-repoLabel.getApi()/(registerLabel.getApi()+1)+registerLabel.getApi());
        registerLabel.setVim(-repoLabel.getVim()/(registerLabel.getVim()+1)+registerLabel.getVim());
        registerLabel.setPlugin(-repoLabel.getPlugin()/(registerLabel.getPlugin()+1)+registerLabel.getPlugin());
        registerLabel.setRust(-repoLabel.getRust()/(registerLabel.getRust()+1)+registerLabel.getRust());
        registerLabel.setApp(-repoLabel.getApp()/(registerLabel.getApp()+1)+registerLabel.getApp());
        registerLabel.setClient(-repoLabel.getClient()/(registerLabel.getClient()+1)+registerLabel.getClient());
        registerLabel.setServer(-repoLabel.getServer()/(registerLabel.getServer()+1)+registerLabel.getServer());
        registerLabel.setJson(-repoLabel.getJson()/(registerLabel.getJson()+1)+registerLabel.getJson());
        registerLabel.setFramework(-repoLabel.getFramework()/(registerLabel.getFramework()+1)+registerLabel.getFramework());
        registerLabel.setPython(-repoLabel.getPython()/(registerLabel.getPython()+1)+registerLabel.getPython());
        registerLabel.setBrowser(-repoLabel.getBrowser()/(registerLabel.getBrowser()+1)+registerLabel.getBrowser());
        registerLabel.setRails(-repoLabel.getRails()/(registerLabel.getRails()+1)+registerLabel.getRails());
        registerLabel.setCss(-repoLabel.getCss()/(registerLabel.getCss()+1)+registerLabel.getCss());
        registerLabel.setAndroid(-repoLabel.getAndroid()/(registerLabel.getAndroid()+1)+registerLabel.getAndroid());
        registerLabel.setJquery(-repoLabel.getJquery()/(registerLabel.getJquery()+1)+registerLabel.getJquery());
        registerLabel.setHtml(-repoLabel.getHtml()/(registerLabel.getHtml()+1)+registerLabel.getHtml());
        registerLabel.setTest(-repoLabel.getTest()/(registerLabel.getTest()+1)+registerLabel.getTest());
        registerLabel.setPhp(-repoLabel.getPhp()/(registerLabel.getPhp()+1)+registerLabel.getPhp());
        registerLabel.setCommand(-repoLabel.getCommand()/(registerLabel.getCommand()+1)+registerLabel.getCommand());
        registerLabel.setTool(-repoLabel.getTool()/(registerLabel.getTool()+1)+registerLabel.getTool());
        registerLabel.setDemo(-repoLabel.getDemo()/(registerLabel.getDemo()+1)+registerLabel.getDemo());
        registerLabel.setWrapper(-repoLabel.getWrapper()/(registerLabel.getWrapper()+1)+registerLabel.getWrapper());
        registerLabel.setIos(-repoLabel.getIos()/(registerLabel.getIos()+1)+registerLabel.getIos());
        registerLabel.setLinux(-repoLabel.getLinux()/(registerLabel.getLinux()+1)+registerLabel.getLinux());
        registerLabel.setWindows(-repoLabel.getWindows()/(registerLabel.getWindows()+1)+registerLabel.getWindows());
        registerLabel.setOsX(-repoLabel.getOsX()/(registerLabel.getOsX()+1)+registerLabel.getOsX());
        registerLabel.setDjango(-repoLabel.getDjango()/(registerLabel.getDjango()+1)+registerLabel.getDjango());
        registerLabel.setGoogle(-repoLabel.getGoogle()/(registerLabel.getGoogle()+1)+registerLabel.getGoogle());
        registerLabel.setGenerator(-repoLabel.getGenerator()/(registerLabel.getGenerator()+1)+registerLabel.getGenerator());
        registerLabel.setDocker(-repoLabel.getDocker()/(registerLabel.getDocker()+1)+registerLabel.getDocker());
        registerLabel.setImage(-repoLabel.getImage()/(registerLabel.getImage()+1)+registerLabel.getImage());
        registerLabel.setTemplate(-repoLabel.getTemplate()/(registerLabel.getTemplate()+1)+registerLabel.getTemplate());

        return registerLabel;
    }
}
