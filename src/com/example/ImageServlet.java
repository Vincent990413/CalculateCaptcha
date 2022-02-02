package com.example;

import com.image.base.BaseCaptcha;
import com.image.subtype.CalculateCaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

    /**
     * 首先定制化 绘制算术验证码图片
     * 然后将得到的结果 存进 Session域中
     * 最后将这个图片 以流的形式 返回给前端
     *
     * @param request  需要存储算术结果的 request 请求域
     * @param response 需要返回算术验证码图的 response 响应体
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
        BaseCaptcha<Integer> baseCaptcha = new CalculateCaptcha();
        int result = baseCaptcha.draw(bufferedImage);
        request.getSession().setAttribute("result", result);
        ImageIO.write(bufferedImage, "JPG", response.getOutputStream());
    }

}