package org.Command;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.ForwardMessageBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import org.imageplugin;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import xyz.cssxsh.mirai.plugin.MiraiSeleniumPlugin;
import xyz.cssxsh.selenium.RemoteWebDriverConfig;
import xyz.cssxsh.selenium.SeleniumToolKt;

import java.io.File;

public class PhantomTools extends JSimpleCommand{
    public PhantomTools(){
        super(imageplugin.INSTANCE, "截图");
        setPrefixOptional(true);
    }

    private RemoteWebDriverConfig config = RemoteWebDriverConfig.INSTANCE;
    @Handler
    public void onCommand(CommandSender sender,String url) {
        if (url.indexOf("http") <0) {
            url="https://"+url;
        }
        String finalUrl = url;
        Runnable runnable=()->{

            sender.sendMessage("正在截图。。。请稍等");
            RemoteWebDriver driver = MiraiSeleniumPlugin.INSTANCE.driver(config);
            try{
                driver.get(finalUrl);
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
                long h= (long) jsExecutor.executeScript("return document.documentElement.scrollHeight");
                System.out.println(h);
                driver.manage().window().setSize(new Dimension(1920, Math.toIntExact(h)));
                File screenshot = driver.getScreenshotAs(OutputType.FILE);
                ExternalResource res = ExternalResource.create(screenshot);
                //发送图片
                net.mamoe.mirai.message.data.Image image1 =  Contact.uploadImage(sender.getSubject(),res);
                res.close();
                sender.sendMessage(new PlainText("网站"+driver.getTitle()+"，截图已完成！").plus(new At(sender.getUser().getId())).plus(image1));
            } catch (Exception e) {
                ForwardMessageBuilder builder = new ForwardMessageBuilder(sender.getSubject());
                builder.add(sender.getBot(),new PlainText(e.getMessage()));
                sender.sendMessage(builder.build());
                e.printStackTrace();
            }
            driver.quit();
        };
        new Thread(runnable).start();
    }

}


