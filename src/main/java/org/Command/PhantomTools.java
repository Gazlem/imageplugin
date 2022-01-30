package org.Command;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import org.imageplugin;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import xyz.cssxsh.mirai.plugin.MiraiSeleniumPlugin;
import xyz.cssxsh.selenium.RemoteWebDriverConfig;
import xyz.cssxsh.selenium.SeleniumToolKt;

import java.io.File;

public class PhantomTools extends JSimpleCommand {
    public PhantomTools(){
        super(imageplugin.INSTANCE, "截图");
        setPrefixOptional(true);
    }
    private RemoteWebDriverConfig config = RemoteWebDriverConfig.INSTANCE;
    @Handler
    public void onCommand(CommandSender sender,String url) {
        try{
            sender.sendMessage("正在截图。。。请稍等");
            RemoteWebDriver driver = MiraiSeleniumPlugin.INSTANCE.driver(config);
            driver.get(url);
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
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            ExternalResource res = ExternalResource.create(screenshot);
            //发送图片
            net.mamoe.mirai.message.data.Image image1 =  Contact.uploadImage(sender.getSubject(),res);
            sender.sendMessage(new PlainText("网站"+driver.getTitle()+"，截图已完成！").plus(new At(sender.getUser().getId())).plus(image1));
            driver.quit();
        } catch (Exception e) {
            sender.sendMessage("截图发送错误："+e.getMessage());
            e.printStackTrace();
        }

    }

}


