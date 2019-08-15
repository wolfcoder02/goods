package com.servlet;


import com.goods.dao.GoodsDao;
import com.goods.entity.GoodsInfo;
import com.goods.utils.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class GoodsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、获得操作的类型
        String opr = req.getParameter("opr");
        int rows = 0;
        String errMsg = "";
        if ("add".equals(opr)) {
            addGooods(req, resp, rows, errMsg, GoodsServlet.getLocalDate());
        }

        //删除
        else if ("delete".equals(opr)) {
            deleteGoods(req, resp, rows);
        }

        //修改
        else if ("update".equals(opr)) {
//             getFile(req,resp);
//
            updateGoods(req, resp, rows);
        }

        //查询
        else if ("select".equals(opr)) {
            selectGoods(req, resp);
            System.out.println(req.getParameter("select"));
        }

        //展示单个商品信息
        else if ("view".equals(opr)) {
            viewGoods(req, resp);
        }

        //遍历
        else {
            list(req, resp);
        }

    }

    public void viewGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获得请求的参数(id)
        int id = StringUtils.str2Int(req.getParameter("id"), 0);

        //2.输出id对应的商品信息(多条件查询）
        GoodsInfo gi = new GoodsInfo();
        gi.setId(id);
        GoodsDao gd = new GoodsDao();
        List<GoodsInfo> list = gd.findByGoods(gi);
        GoodsInfo good = list.get(0);

        //3.转发,
        req.setAttribute("good", good);
        req.getRequestDispatcher("good_view.jsp").forward(req, resp);
    }

    public void selectGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数
        GoodsInfo gi = getGoodsParams(req);
        System.out.println(gi);
        //2.传参
        GoodsDao gd = new GoodsDao();
        List<GoodsInfo> list = gd.findByGoods(gi);

        System.out.println(list);

        //3.得到list,转发
        req.setAttribute("list", list);
        req.getRequestDispatcher("good_select.jsp").forward(req, resp);
    }


    private void addGooods(HttpServletRequest req, HttpServletResponse resp, int rows, String errMsg, String localDate) throws IOException {
        try {

            //1.获取参数的值
            GoodsInfo gi = GoodsServlet.getGoodsParams(req);
            gi.setCreated(req.getParameter("created"));
            gi.setCreatedDate(localDate);
            // 2、进行数据的验证
            if (!StringUtils.isNotNull(gi.getGoodsInfoName())) {
//                    req.setAttribute("msg","学生姓名不能为空");
                throw new RuntimeException("商品姓名不能为空");
            }

            if (!StringUtils.isNotNull(gi.getGoodsInfoPic())) {
//                    req.setAttribute("msg","学生年龄不能为空");
                throw new RuntimeException("商品图片不能为空");
            }

            if (gi.getGoodsInfoPrice() <= 0) {
//                    req.setAttribute("msg","学生年龄不能为空");
                throw new RuntimeException("商品价格不能为空");
            }

            if (!StringUtils.isNotNull(gi.getGoodsInfoDescription())) {
//                    req.setAttribute("msg","学生年龄不能为空");
                throw new RuntimeException("商品描述不能为空");
            }

            if (gi.getGoodsInfoStock() <= 0) {
//                    req.setAttribute("msg","学生年龄不能为空");
                throw new RuntimeException("商品库存不能为空");
            }

            if (!StringUtils.isNotNull(gi.getCreated())) {
//                    req.setAttribute("msg","学生年龄不能为空");
                throw new RuntimeException("创始人不能为空");
            }

            if (!StringUtils.isNotNull(gi.getCreatedDate())) {
//                    req.setAttribute("msg","学生年龄不能为空");
                throw new RuntimeException("建立时间不能为空");
            }

            // 3、数据是合法，调用Dao进行数据的保存
            GoodsDao gd = new GoodsDao();
            rows = gd.addStudent(gi);
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }

        PrintWriter out = resp.getWriter();
        //根据返回的受影响行数进行页面的跳转
        if (rows > 0) {
            // 如果成功就跳转到学生的查询列表页面,还有成功的消息提示
            out.println("<script type='text/javascript'>alert('新增成功');location.href='/goodsServlet';</script>");
        } else {
            // 如果失败就回退到新增页面，还有错误的消息提示
            out.println("<script type='text/javascript'>alert('新增失败：" + errMsg + "');history.back();</script>");
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodsDao gd = new GoodsDao();
        GoodsInfo gi = new GoodsInfo(1, null, null, 0, null, 0, 0, null, null);
        gi = null;

        List<GoodsInfo> list = gd.findByGoods(gi);
        req.setAttribute("list", list);

        //转发
        req.getRequestDispatcher("good_list.jsp").forward(req, resp);
    }

    public void deleteGoods(HttpServletRequest req, HttpServletResponse resp, int row) throws IOException {
        // 1、获得请求参数的值
        GoodsInfo gi = new GoodsInfo();
        gi.setId(StringUtils.str2Int(req.getParameter("id"), 0));
        System.out.println("获取的序号：" + gi.getId());
        // 2、进行数据的验证
        GoodsDao gd = new GoodsDao();
        row = gd.deleteStudent(gi);
        System.out.println("row:" + row);
        PrintWriter out = resp.getWriter();
        if (row > 0) {
            //打印流
            //如果成功就跳转到查询页面来
            out.println("<script type='text/javascript'>alert('删除成功');location.href='goodsServlet'</script>");
        }

        //删除失败
        else {
            out.println("<script type='text/javascript'>alert('删除失败');history.back();</script>");
        }

    }

    //修改功能
    public void updateGoods(HttpServletRequest req, HttpServletResponse resp, int row) throws IOException, ServletException {
        //1.获取参数的值
        GoodsInfo gi = new GoodsInfo();
        System.out.println("\t\t"+gi);
//        gi.setGoodsInfoPic(fileName);
//        gi.setId(StringUtils.str2Int(req.getParameter("id"),0));
        List<GoodsInfo> list = new ArrayList<>();
        GoodsDao gd = new GoodsDao();


        // 如果有<form>表单的enctype="multipart/form-data",
        // 如果直接通过req.getParameter("title");将取不到值
        String title = req.getParameter("title");
        String fileName = "";
        String errMsg = "";
        try {
            // 1、判断你的<form>表单的enctype="multipart/form-data",
            boolean isMultipart = ServletFileUpload.isMultipartContent(req);
            // 2、判断是否为上传表单
            if (isMultipart) {
                // 3、创建ServletFileUpload对象
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                // 4、通过ServletFileUpload对象解析请求获得上传的控件的集合
                List<FileItem> fileItemList = upload.parseRequest(req);

                // 5、遍历
                if (fileItemList != null && fileItemList.size() > 0) {
                    for (FileItem item : fileItemList) {
                        if (item.isFormField()) {
                            // 表示普通控件
                            PrintWriter out = resp.getWriter();
                            if ("id".equals(item.getFieldName())) {
                                String id = item.getString("utf-8");
                                gi.setId(StringUtils.str2Int(id,0));
                            }
                            if ("goodsInfoName".equals(item.getFieldName())) {
                                String goodsInfoName = item.getString("utf-8");
                                gi.setGoodsInfoName(goodsInfoName);
                                if (!StringUtils.isNotNull(gi.getGoodsInfoName())) {
                                    out.println("<script>alert('名字不能为空');history.back();</script>");
                                }
                            }
                            if ("goodsInfoPrice".equals(item.getFieldName())) {
                                String goodsInfoPrice = item.getString("utf-8");
                                gi.setGoodsInfoPrice(StringUtils.str2Double(goodsInfoPrice,0.0));
                                if (gi.getGoodsInfoPrice()<0) {
                                    out.println("<script>alert('价格不能为负');history.back();</script>");
                                }
                            }
                            if ("goodsInfoDescription".equals(item.getFieldName())) {
                                String goodsInfoDescription = item.getString("utf-8");
                                gi.setGoodsInfoDescription(goodsInfoDescription);
                                if (!StringUtils.isNotNull(gi.getGoodsInfoDescription())) {
                                    out.println("<script>alert('描述不能为空');history.back();</script>");
                                }
                            }
                            if ("goodsInfoStock".equals(item.getFieldName())) {
                                String goodsInfoStock = item.getString("utf-8");
                                gi.setGoodsInfoStock(StringUtils.str2Int(goodsInfoStock,0));

                            }
                            if ("flag".equals(item.getFieldName())) {
                                String flag = item.getString("utf-8");
                                gi.setFlag(StringUtils.str2Int(flag,0));
                            }
                        }

                        // 表示上传控件
                        else {
                            if (item.getName() != null && !"".equals(item.getName())) {
                                System.out.println(item.getName());
                                // 5.1、获得upload目录在web服务中的绝对路径
                                String parentPath = req.getServletContext().getRealPath("/upload");
                                // 判断目录是否存在，如果不存在就创建出来
                                File parentFile = new File(parentPath);
                                // mkdirs()：创建目录
                                if (!parentFile.exists()) parentFile.mkdirs();

                                // 5.2、写入服务器中的新的文件的文件对象
                                File newFile = new File(parentPath, item.getName());
                                // 5.3、获得上传文件的输入流
                                InputStream inputStream = item.getInputStream();
                                // 5.4、创建输出流
                                OutputStream outputStream = new FileOutputStream(newFile);
                                // 5.5、进入读写操作
                                IOUtils.copy(inputStream, outputStream);

                                fileName = item.getName();

                                if (fileName != null) {
                                     gi.setGoodsInfoPic(fileName);
                                }

                            }
                        }
                    }
                }
                row = gd.updateGoods(gi);
                PrintWriter out = resp.getWriter();
                if (row > 0) {
                out.println("<script>alert('修改成功');location.href='goodsServlet';</script>");
                 }
            //否则失败
                else {
                out.println("<script>alert('修改失败：没有此商品序号');history.back();</script>");
                }
                return;
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //判断是否提交修改的数据，是k为1，表示执行修改功能
//        int k = StringUtils.str2Int(req.getParameter("k"), 0);
//        if (k == 1) {
//            PrintWriter out = resp.getWriter();
//            //检查输入的stuNo是否符合条件
//            System.out.println(gi);

//            System.out.println(row);
//            if (row > 0) {
//                out.println("<script>alert('修改成功');location.href='goodsServlet';</script>");
//            }
//            //否则失败
//            else {
//                out.println("<script>alert('修改失败：没有此商品序号');history.back();</script>");
//            }
//            return;
//        }
//
        list = gd.findByGoods(gi);

        //4.转发
//        req.setAttribute("list", list);
        req.setAttribute("good", list.get(0));
        req.getRequestDispatcher("good_update.jsp").forward(req, resp);
    }





    //获取请求的参数
    public static GoodsInfo getGoodsParams(HttpServletRequest req) {
        GoodsInfo gi = new GoodsInfo();
        gi.setId(StringUtils.str2Int(req.getParameter("id"), 0));
        gi.setGoodsInfoName(req.getParameter("goodsInfoName"));
        gi.setGoodsInfoPrice(StringUtils.str2Double(req.getParameter("goodsInfoPrice"), 0.0));
        gi.setGoodsInfoDescription(req.getParameter("goodsInfoDescription"));
        gi.setGoodsInfoStock(StringUtils.str2Int(req.getParameter("goodsInfoStock"), 0));
        gi.setFlag(StringUtils.str2Int(req.getParameter("flag"), 0));
//        gi.setCreated(req.getParameter("created"));
        gi.setCreatedDate(getLocalDate());
        return gi;
    }

    //获取当前时间
    public static String getLocalDate() {
        String localTime = LocalDate.now().toString() + " " + LocalTime.now().withNano(0).toString();
        return localTime;
    }


}
