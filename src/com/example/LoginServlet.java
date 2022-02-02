package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    /**
     * 将Session域中的存储结果 与用户提交表单的进行比对
     * 注意可能需要进行UTF-8编码
     *
     * @param request  需要存储算术结果的 request 请求域
     * @param response 需要返回算术验证码图的 response 响应体
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sessionCode = (int) request.getSession().getAttribute("result");
        int checkCode = Integer.parseInt(request.getParameter("checkcode"));

        response.setContentType("text/html;charset=UTF-8");//设置本文格式，防止出现乱码
        PrintWriter out = response.getWriter();
        if (sessionCode == checkCode) {
            out.println("验证码输入正确！");
        } else {
            out.print("验证码输入错误！");
        }
        out.flush();
        out.close();
    }

}
