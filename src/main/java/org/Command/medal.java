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
    @SubCommand("今日赛程")
//    choice 0为开始比赛项目，1为正在进行项目，2今天已完成项目
    public void onCommand(CommandSender sender,int choice) {
        if (choice>2 || choice<0){
            sender.sendMessage("选择参数错误");
            return;
        }
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
                ForwardMessageBuilder seedbuilder = new ForwardMessageBuilder(sender.getSubject());
                List<WebElement> info = driver.findElements(By.cssSelector("#cardList li"));
                String choiceS="";
                if (choice==0){
                     choiceS="未开始";
                }else if (choice==1){
                     choiceS="比赛中";
                }else if (choice==2) {
                     choiceS="已结束";
                }
                String finalChoiceS = choiceS;
                info.forEach(v->{
                    String a = v.findElement(new By.ByClassName("cardList_content")).findElements(By.tagName("div")).get(1).findElements(By.tagName("div")).get(1).getText();
                    if (a.equals(finalChoiceS)){
                        File screenshot = v.getScreenshotAs(OutputType.FILE);
                        ExternalResource res = ExternalResource.create(screenshot);
                        net.mamoe.mirai.message.data.Image image1 =  Contact.uploadImage(sender.getSubject(),res);
                        seedbuilder.add(sender.getBot(),image1);
//                        sender.sendMessage(image1);
                        try {
                            res.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                sender.sendMessage(seedbuilder.build());
            } catch (Exception e) {
                ForwardMessageBuilder builder = new ForwardMessageBuilder(sender.getSubject());
                builder.add(sender.getBot(),new PlainText(e.getMessage()));
                sender.sendMessage(builder.build().plus(new At(sender.getUser().getId())));
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }
    //https://www.beijing2022.cn/
    @SubCommand("奖牌")
    public void Mymedal(CommandSender sender) {
        Runnable runnable=()->{
            sender.sendMessage("正在截图。。。请稍等");
            RemoteWebDriver driver = MiraiSeleniumPlugin.INSTANCE.driver(config);
            driver.get("https://results.beijing2022.cn/beijing-2022/olympic-games/zh/results/all-sports/medal-standings.htm");
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
                WebElement info = driver.findElement(By.xpath("/html/body/main/div/div[1]/div[1]/div[1]/div[2]"));
                File screenshot = info.getScreenshotAs(OutputType.FILE);
                ExternalResource res = ExternalResource.create(screenshot);
                net.mamoe.mirai.message.data.Image image1 =  Contact.uploadImage(sender.getSubject(),res);
                sender.sendMessage(image1);
            } catch (Exception e) {
                ForwardMessageBuilder builder = new ForwardMessageBuilder(sender.getSubject());
                builder.add(sender.getBot(),new PlainText(e.getMessage()));
                sender.sendMessage(builder.build().plus(new At(sender.getUser().getId())));
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }
    //GeneralScheduleTable

}
