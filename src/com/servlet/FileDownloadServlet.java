package com.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获得文件名
        String fileName = req.getParameter("fileName");
        //2.获得文件的绝对路径
        String parentpath = getServletContext().getRealPath("/upload");
        //创建下载文件的文件对象
        File downloadFile = new File(parentpath,fileName);

        //3.设置文件在客户端浏览器的打开方式
        //设置头信息
        //attachment:以附件的形式打开
        //未来解决文件的中文乱码问题，要进行转码
        String newFileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
        resp.setHeader("Content-disposition","attachment;fileName="+newFileName);

        //4.开始真正的文件下载
        //获得输入流
        InputStream inputStream = new FileInputStream(downloadFile);

        //获得输出流：获得响应流
        OutputStream outputStream = resp.getOutputStream();

        //文件的读与
        IOUtils.copy(inputStream,outputStream);

        //关闭流
        inputStream.close();
    }
}
