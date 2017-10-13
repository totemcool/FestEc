package com.mxj.mxj_core.app.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by totem on 2017/10/11.
 */

public class Configurator {
    private static final HashMap<String,Object> MXJ_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    public Configurator(){
        MXJ_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String,Object> getMxjConfigs(){
        return MXJ_CONFIGS;
    }

    private static class Holder{
        private static final  Configurator INSTANCE = new Configurator();
    }

    public final void configure(){
        initCons();
        MXJ_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host){
        MXJ_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    private void initCons(){
        if(ICONS.size() > 0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i= 1; i < ICONS.size(); i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    //尽量让变量变得不可变性 final  减少不应该更改的错误  JVM也会对final方法和变量进行优化
    private void checkConfiguration(){
        final boolean isReady = (boolean) MXJ_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) MXJ_CONFIGS.get(key.name());
    }
}
