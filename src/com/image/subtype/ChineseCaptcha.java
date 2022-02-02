package com.image.subtype;

import com.image.base.BaseCaptcha;
import com.image.constants.Constants;

import java.awt.*;
import java.util.Random;

/**
 * 抽象类<code>BaseCaptcha</code>的实现类
 * 实现绘制简单的中文汉字验证码
 *
 * @author Vincent
 * @version 1.0
 * @example 使用例子：
 * <p>
 * BufferedImage bufferedImage = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
 * BaseCaptcha<Integer> baseCaptcha = new ChineseCaptcha();
 * int result = baseCaptcha.draw(bufferedImage);
 * request.getSession().setAttribute("result", result);
 * ImageIO.write(bufferedImage, "JPG", response.getOutputStream());
 * </p>
 */
public class ChineseCaptcha extends BaseCaptcha<String> {

    @Override
    protected String drawImageInternal(Graphics graphics) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            String randChar = Constants.COMMON_CHINESE.charAt(random.nextInt(Constants.COMMON_CHINESE.length())) + "";
            graphics.setColor(new Color(random.nextInt(Constants.COLOR_RED), random.nextInt(Constants.COLOR_GREEN), random.nextInt(Constants.COLOR_BLUE)));
            graphics.drawString(randChar, (i * Constants.NUM_X_OFFSET), Constants.NUM_Y_OFFSET);
            stringBuffer.append(randChar);
        }
        return stringBuffer.toString();
    }
}
