package org;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.Command.PhantomTools;
import org.Command.WebTools;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import xyz.cssxsh.mirai.plugin.MiraiSeleniumPlugin;
import xyz.cssxsh.selenium.RemoteWebDriverConfig;
import java.io.File;

public final class imageplugin extends JavaPlugin implements Runnable{
    public static final imageplugin INSTANCE = new imageplugin();
    private RemoteWebDriverConfig config = RemoteWebDriverConfig.INSTANCE;

    private imageplugin() {

        super(new JvmPluginDescriptionBuilder("org.imageplugin", "1.0")
                .name("imageplugin")
                .author("枫叶秋林")
                .dependsOn("xyz.cssxsh.mirai.plugin.mirai-selenium-plugin", false)
                .build()
        );
    }



    @Override
    public void onEnable() {
        CommandManager.INSTANCE.registerCommand(new PhantomTools(),true);
        CommandManager.INSTANCE.registerCommand(new WebTools(),true);
        if (MiraiSeleniumPlugin.INSTANCE.setup(true)){
            getLogger().info("网页截图插件已加载");
        }else{
            getLogger().info("初始化失败,准备下载");
            new Thread(new imageplugin()).start();
        }
    }
    @Override
    public void run() {
        getLogger().info("开始下载");
        MiraiSeleniumPlugin.INSTANCE.chromium("98");
        getLogger().info("下载完成！");
    }
}