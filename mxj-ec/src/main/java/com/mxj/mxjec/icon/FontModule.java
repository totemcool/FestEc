package com.mxj.mxjec.icon;

import com.joanzapata.iconify.IconFontDescriptor;


/**
 * Created by totem on 2017/10/12.
 */

public class FontModel implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return null;
    }

    @Override
    public com.joanzapata.iconify.Icon[] characters() {
        return new com.joanzapata.iconify.Icon[0];
    }
}
