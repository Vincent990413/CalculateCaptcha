package com.image.subtype;

import com.image.base.BaseCaptcha;
import com.image.constants.Constants;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 抽象类<code>BaseCaptcha</code>的实现类
 * 实现绘制简单的算术运算验证码
 *
 * @author Vincent
 * @version 1.0
 * @example 使用例子：
 * <p>
 * BufferedImage bufferedImage = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
 * BaseCaptcha<Integer> baseCaptcha = new CalculateCaptcha();
 * int result = baseCaptcha.draw(bufferedImage);
 * request.getSession().setAttribute("result", result);
 * ImageIO.write(bufferedImage, "JPG", response.getOutputStream());
 * </p>
 */
public class CalculateCaptcha extends BaseCaptcha<Integer> {

    @Override
    protected Integer drawImageInternal(Graphics graphics) {
        List<Character> operatorList = Arrays.asList('+', '-', '*');
        Random random = new Random();
        int index, ranNumLen = operatorList.size();
        int firstNum, secondNum;
        firstNum = secondNum = 0;
        char operation = '-';

        //分为操作符与操作数两部分的绘制
        for (int i = 0; i < 4; i++) {
            if (i != 1) index = random.nextInt(Constants.NUM_BOUND);
            else index = random.nextInt(ranNumLen);

            graphics.setColor(new Color(random.nextInt(Constants.COLOR_RED), random.nextInt(Constants.COLOR_GREEN), random.nextInt(Constants.COLOR_BLUE)));
            if (i == 0) {
                graphics.drawString(index + "", (i * Constants.NUM_X_OFFSET), Constants.NUM_Y_OFFSET);
                firstNum = index;
            } else if (i == 2) {
                graphics.drawString(index + "", (i * Constants.NUM_X_OFFSET), Constants.NUM_Y_OFFSET);
                secondNum = index;
            } else if (i == 1) {
                graphics.drawString(operatorList.get(index) + "", (i * Constants.NUM_X_OFFSET), Constants.NUM_Y_OFFSET);
                operation = operatorList.get(index);
            } else {
                graphics.drawString("=", (i * Constants.NUM_X_OFFSET), Constants.NUM_Y_OFFSET);
            }
        }

        return processResult(operation, firstNum, secondNum);
    }

    /**
     * @param operation 当前生成的操作符
     * @param firstNum  随机生成的第一个数字
     * @param secondNum 随机生成的第二个数字
     * @return 返回算术运算的正确结果
     */
    private Integer processResult(char operation, int firstNum, int secondNum) {
        int result = -1;
        if (operation == '+') result = firstNum + secondNum;
        else if (operation == '-') result = firstNum - secondNum;
        else if (operation == '*') result = firstNum * secondNum;
        return result;
    }

}