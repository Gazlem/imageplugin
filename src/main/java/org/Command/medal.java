package org.Command;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JCompositeCommand;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.ForwardMessageBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import org.imageplugin;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import xyz.cssxsh.mirai.plugin.MiraiSeleniumPlugin;
import xyz.cssxsh.selenium.RemoteWebDriverConfig;
import xyz.cssxsh.selenium.SeleniumToolKt;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class medal extends JCompositeCommand {
    public medal(){
        super(imageplugin.INSTANCE, "冬奥会");
        setPrefixOptional(true);
    }
    private RemoteWebDriverConfig config = RemoteWebDriverConfig.INSTANCE;

    @SubCommand("当前赛程")
    public void onCommand(CommandSender sender) {
        Runnable runnable=()->{
            sender.sendMessage("正在截图。。。请稍等");
            RemoteWebDriver driver = MiraiSeleniumPlugin.INSTANCE.driver(config);
            driver.get("https://2022.cctv.com/schedule");
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
            sender.sendMessage("访问网站成功，截图开始！");
            try {
                List<WebElement> info = driver.findElements(By.cssSelector("#cardList li"));
                info.forEach(v->{
                    String a = v.findElement(new By.ByClassName("cardList_content")).findElements(By.tagName("div")).get(1).findElements(By.tagName("div")).get(1).getText();
                    if (a.equals("比赛中")){
                        File screenshot = v.getScreenshotAs(OutputType.FILE);
                        ExternalResource res = ExternalResource.create(screenshot);
                        net.mamoe.mirai.message.data.Image image1 =  Contact.uploadImage(sender.getSubject(),res);
                        sender.sendMessage(image1);
                        try {
                            res.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                ForwardMessageBuilder builder = new ForwardMessageBuilder(sender.getSubject());
                builder.add(sender.getBot(),new PlainText(e.getMessage()));
                sender.sendMessage(builder.build());
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }
}
