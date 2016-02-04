package com.zhy.changeskin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.TextUtils;

import com.zhy.changeskin.utils.L;

/**
 * Created by zhy on 15/9/22.
 */
public class ResourceManager
{
    private static final String DEFTYPE_DRAWABLE = "drawable";
    private static final String DEFTYPE_COLOR = "color";
    private Resources mResources;
    private String mPluginPackageName;
    private String mSuffix;


    public ResourceManager(Resources res, String pluginPackageName, String suffix)
    {
        mResources = res;
        mPluginPackageName = pluginPackageName;

        if (suffix == null)
        {
            suffix = "";
        }
        mSuffix = suffix;

    }

    /**
     * 获得插件的Drawable
     * @param name
     * @return
     */
    public Drawable getDrawableByName(String name)
    {
        try
        {
            name = appendSuffix(name);
            L.e("name = " + name + " , " + mPluginPackageName);
            return mResources.getDrawable(mResources.getIdentifier(name, DEFTYPE_DRAWABLE, mPluginPackageName));
        } catch (Resources.NotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获得插件的Layout
     * @param name
     * @return
     */
    public XmlResourceParser getLayout(String name){
            name = appendSuffix(name);
            return  mResources.getLayout(mResources.getIdentifier(name, DEFTYPE_DRAWABLE, mPluginPackageName));
    }

    /**
     * 获得插件的Color
     * @param name
     * @return
     */
    public int getColor(String name) throws Resources.NotFoundException
    {
        name = appendSuffix(name);
        L.e("name = " + name);
        return mResources.getColor(mResources.getIdentifier(name, DEFTYPE_COLOR, mPluginPackageName));
    }

    /**
     * 获得插件的ColorStateList
     * Android ColorStateList可以添加xml文件可以结合selector使背景颜色变化更加自由。
     * @param name
     * @return
     */
    public ColorStateList getColorStateList(String name)
    {
        try
        {
            name = appendSuffix(name);
            L.e("name = " + name);
            return mResources.getColorStateList(mResources.getIdentifier(name, DEFTYPE_COLOR, mPluginPackageName));

        } catch (Resources.NotFoundException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    private String appendSuffix(String name)
    {
        if (!TextUtils.isEmpty(mSuffix))
            return name += "_" + mSuffix;
        return name;
    }

}
