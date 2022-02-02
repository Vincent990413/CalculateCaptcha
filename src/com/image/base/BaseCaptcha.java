package com.image.base;

import com.image.constants.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 模板方法设计模式的抽象类 分为配置与绘图两个步骤
 * 并在 <code>draw</code> 方法中指定使用方法及步骤
 *
 * @param <T> 代表返回的结果类型 可在 <code>subtype</code>包中查看返回类型
 * @author Vincent
 * @version 1.0
 */
public abstract class BaseCaptcha<T> {

    /**
     * 配置绘图的基本属性 包括颜色与验证码的外框大小等
     *
     * @param bufferedImage 带缓冲区的图像实例 用于加载至内存中
     * @return
     */
    protected final T beforeDrawCaptcha(BufferedImage bufferedImage) {
        Graphics graphics = bufferedImage.getGraphics();
        Color color = new Color(Constants.COLOR_RED, Constants.COLOR_GREEN, Constants.COLOR_BLUE);
        graphics.setColor(color);
        graphics.fillRect(Constants.RECT_X_AXIS, Constants.RECT_Y_AXIS, Constants.RECT_WIDTH, Constants.RECT_HEIGHT);
        return drawImageInternal(graphics);
    }

    /**
     * 依据不同的验证码类型 将延迟到子类中实现
     *
     * @param graphics 基本的绘图对象 包括形状、颜色、坐标轴等属性
     * @return
     */
    protected abstract T drawImageInternal(Graphics graphics);

    /**
     * 规定绘图的抽象步骤
     *
     * @param bufferedImage 带缓冲区的图像实例 用于加载至内存中
     * @return 返回绘图后的结果实例
     */
    public final T draw(BufferedImage bufferedImage) {
        return this.beforeDrawCaptcha(bufferedImage);
    }

}
