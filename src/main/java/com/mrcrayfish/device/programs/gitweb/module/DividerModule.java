package com.mrcrayfish.device.programs.gitweb.module;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Layout;

import java.util.Map;

/**
 * Author: MrCrayfish
 */
public class DividerModule extends Module
{
    @Override
    public String[] getRequiredData()
    {
        return new String[] { "size" };
    }

    @Override
    public int calculateHeight(Map<String, String> data, int width)
    {
        return Math.max(0, Integer.parseInt(data.get("size")));
    }

    @Override
    public void generate(Application app, Layout layout, int width, Map<String, String> data) {}
}