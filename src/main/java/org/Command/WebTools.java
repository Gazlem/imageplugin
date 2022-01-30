package org.Command;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JCompositeCommand;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.ForwardMessageBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import org.imageplugin;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import xyz.cssxsh.mirai.plugin.MiraiSeleniumPlugin;
import xyz.cssxsh.selenium.RemoteWebDriverConfig;
import xyz.cssxsh.selenium.SeleniumToolKt;

import java.io.File;

public class WebTools extends JCompositeCommand {
    private RemoteWebDriverConfig config = RemoteWebDriverConfig.INSTANCE;
    public WebTools() {
        super(imageplugin.INSTANCE, "站长工具");
        setDescription("站长工具");
        setPrefixOptional(true);

    }

    @SubCommand("备案")
    public void beian(CommandSender sender, String url) {
        try{
            sender.sendMessage("正在查询。。。请稍等");
            RemoteWebDriver driver = MiraiSeleniumPlugin.INSTANCE.driver(config);
            driver.get("http://micp.chinaz.com/?query="+url);
            long current=System.currentTimeMillis();
            while (true){
                if (SeleniumToolKt.isReady(driver)){
                    break;
                }
                if (current - System.currentTimeMillis()>100000){
                    sender.sendMessage("网页加载超时");
                    break;
                }
            }
            try{
                WebElement a = driver.findElement(By.xpath("/html/body/div[5]/div[4]/table"));
                File screenshot = a.getScreenshotAs(OutputType.FILE);
                ExternalResource res = ExternalResource.create(screenshot);
                net.mamoe.mirai.message.data.Image image1 =  Contact.uploadImage(sender.getSubject(),res);
                sender.sendMessage(new PlainText("备案查询完毕").plus(new At(sender.getUser().getId())).plus(image1));
            } catch (Exception e) {
                sender.sendMessage("备案未被查询到");
                e.printStackTrace();
            }
            driver.quit();
        } catch (Exception e) {
            ForwardMessageBuilder builder = new ForwardMessageBuilder(sender.getSubject());
            builder.add(sender.getUser(),new PlainText(e.getMessage()));
            sender.sendMessage(builder.build());
            e.printStackTrace();
        }
    }
    @SubCommand("ip")
    public void ip(CommandSender sender, String url) {
        try{
            sender.sendMessage("正在查询。。。请稍等");
            RemoteWebDriver driver = MiraiSeleniumPlugin.INSTANCE.driver(config);
            driver.get("http://mip.chinaz.com/?query="+url);
            long current=System.currentTimeMillis();
            while (true){
                if (SeleniumToolKt.isReady(driver)){
                    break;
                }
                if (current - System.currentTimeMillis()>100000){
                    sender.sendMessage("网页加载超时");
                    break;
                }
            }
            WebElement a = driver.findElement(By.xpath("/html/body/div[7]/div[4]"));
            File screenshot = a.getScreenshotAs(OutputType.FILE);
            ExternalResource res = ExternalResource.create(screenshot);
            net.mamoe.mirai.message.data.Image image1 =  Contact.uploadImage(sender.getSubject(),res);
            sender.sendMessage(new PlainText("ip地址查询完毕").plus(new At(sender.getUser().getId())).plus(image1));
            driver.quit();
        } catch (Exception e) {
            ForwardMessageBuilder builder = new ForwardMessageBuilder(sender.getSubject());
            builder.add(sender.getUser(),new PlainText(e.getMessage()));
            sender.sendMessage(builder.build());
            e.printStackTrace();
        }
    }
    @SubCommand("ECO")
    public void SEO(CommandSender sender, String url) {
        try{
            sender.sendMessage("正在查询。。。请稍等");
            RemoteWebDriver driver = MiraiSeleniumPlugin.INSTANCE.driver(config);
            driver.get("http://mseo.chinaz.com/?host="+url);
            long current=System.currentTimeMillis();
            while (true){
                if (SeleniumToolKt.isReady(driver)){
                    break;
                }
                if (current - System.currentTimeMillis()>100000){
                    sender.sendMessage("网页加载超时");
                    break;
                }
            }
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("var red = document.getElementsByClassName('riw-main')[0];var h1 = document.getElementsByClassName('sa-img')[0];red.removeChild(h1);");
            WebElement a = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]"));
            File screenshot = a.getScreenshotAs(OutputType.FILE);
            ExternalResource res = ExternalResource.create(screenshot);
            net.mamoe.mirai.message.data.Image image1 =  Contact.uploadImage(sender.getSubject(),res);
            sender.sendMessage(new PlainText("ECO查询完毕").plus(new At(sender.getUser().getId())).plus(image1));
            driver.quit();
        } catch (Exception e) {
            ForwardMessageBuilder builder = new ForwardMessageBuilder(sender.getSubject());
            builder.add(sender.getUser(),new PlainText(e.getMessage()));
            sender.sendMessage(builder.build());
            e.printStackTrace();
        }
    }
    @SubCommand("ping")
    public void ping(CommandSender sender, String url) {
        //
        try{
            sender.sendMessage("正在查询。。。请稍等");
            RemoteWebDriver driver = MiraiSeleniumPlugin.INSTANCE.driver(config);
            driver.get("http://mping.chinaz.com/?host="+url);
            long current=System.currentTimeMillis();
            while (true){
                if (SeleniumToolKt.isReady(driver)){
                    break;
                }
                if (current - System.currentTimeMillis()>100000){
                    sender.sendMessage("网页加载超时");
                    break;
                }
            }
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            while (true){
                String is= (String) jsExecutor.executeScript(" var a = document.getElementById('progressBar').innerHTML;return a");
                if (is.equals("100%")){
                    break;
                }
            }
            sender.sendMessage("读取完毕！准备图片发送！");
            WebElement a = driver.findElement(By.xpath("/html/body/div[6]/div[4]"));
            File screenshot = a.getScreenshotAs(OutputType.FILE);
            ExternalResource res = ExternalResource.create(screenshot);
            net.mamoe.mirai.message.data.Image image1 =  Contact.uploadImage(sender.getSubject(),res);
            sender.sendMessage(new PlainText("ping查询完毕").plus(new At(sender.getUser().getId())).plus(image1));
            driver.quit();
        } catch (Exception e) {
            ForwardMessageBuilder builder = new ForwardMessageBuilder(sender.getSubject());
            builder.add(sender.getUser(),new PlainText(e.getMessage()));
            sender.sendMessage(builder.build());
            e.printStackTrace();
        }

    }

}
