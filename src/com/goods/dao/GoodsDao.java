package com.goods.dao;

import com.goods.entity.GoodsInfo;
import com.goods.utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao extends BaseDao {
    /**
     * 多条件查询
     */
    public List<GoodsInfo> findByGoods(GoodsInfo gi) {
        Connection conn = null;
        PreparedStatement prs = null;
        ResultSet rs = null;
        List<GoodsInfo> list = new ArrayList();

        //连接数据库
        conn = getConnection();

        //创建sql语句
        StringBuffer sb = new StringBuffer();
        sb.append("select * from goodsinfo where 1=1 ");
        List paramlist = new ArrayList();

        //多条件查询
        if (gi != null) {
            if (gi.getId() > 0) {
                sb.append("and id = ? ");
                paramlist.add(gi.getId());
            }
            if (StringUtils.isNotNull(gi.getGoodsInfoName())) {
                sb.append("and goodsInfoName = ? ");
                paramlist.add(gi.getGoodsInfoName());
            }
            if (StringUtils.isNotNull(gi.getGoodsInfoPic())) {
                sb.append(" and goodsInfoPic = ? ");
                paramlist.add(gi.getGoodsInfoPic());
            }
            if (gi.getGoodsInfoPrice() > 0) {
                sb.append("and goodsInfoPrice = ? ");
                paramlist.add(gi.getGoodsInfoPrice());
            }
            if (StringUtils.isNotNull(gi.getGoodsInfoDescription())) {
                sb.append("and goodsInfoDescription = ? ");
                paramlist.add(gi.getGoodsInfoDescription());
            }
            if (gi.getGoodsInfoStock() > 0) {
                sb.append("and goodsStock = ? ");
                paramlist.add(gi.getGoodsInfoStock());
            }
            if (gi.getFlag() > 0) {
                sb.append("and flag = ? ");
                paramlist.add(gi.getFlag());
            }
            if (StringUtils.isNotNull(gi.getCreated())) {
                sb.append("and created = ? ");
                paramlist.add(gi.getCreated());
            }
//            if (StringUtils.isNotNull(gi.getCreatedDate())) {
//                sb.append("and createdDate = ? ");
//                paramlist.add(gi.getCreatedDate());
//            }
        }
        try {
            //获得执行SQL语句的对象
            prs = conn.prepareStatement(sb.toString());

            //设置占位符的值
            if (paramlist != null && paramlist.size() > 0) {
                for (int i = 0; i < paramlist.size(); i++) {
                    prs.setObject(i + 1, paramlist.get(i));
                }
            }
            //执行sql语句,获得结果集
            rs = prs.executeQuery();
            while (rs.next()) {
                //将结果集传到listGoods里面
                GoodsInfo gi2 = new GoodsInfo();
                System.out.println(gi2);
                System.out.println(list);
                gi2.setId(rs.getInt(1));
                gi2.setGoodsInfoName(rs.getString(2));
                gi2.setGoodsInfoPic(rs.getString(3));
                gi2.setGoodsInfoPrice(rs.getDouble(4));
                gi2.setGoodsInfoDescription(rs.getString(5));
                gi2.setGoodsInfoStock(rs.getInt(6));
                gi2.setFlag(rs.getInt(7));
                gi2.setCreated(rs.getString(8));
                gi2.setCreatedDate(rs.getString(9));
                System.out.println(gi2);
                list.add(gi2);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, prs, rs);
        }
        return null;
    }

    public int addStudent(GoodsInfo gi) {
        String sql = "insert into goodsInfo values(?,?,?,?,?,?,?,?,?)";
        Object[] paramObjs = {
                gi.getId(),
                gi.getGoodsInfoName(),
                gi.getGoodsInfoPic(),
                gi.getGoodsInfoPrice(),
                gi.getGoodsInfoDescription(),
                gi.getGoodsInfoStock(),
                gi.getFlag(),
                gi.getCreated(),
                gi.getCreatedDate()
        };
        return this.executeUpdate(sql, paramObjs);
    }

    //删除
    public int deleteStudent(GoodsInfo gi) {
        String sql = "delete from goodsinfo where  id = ? ";
        Object[] paramObjs = {gi.getId()};
        return this.executeUpdate(sql, paramObjs);
    }

    //修改
    public int updateGoods(GoodsInfo gi) {
        String sql = "update goodsinfo set goodsInfoName=?, goodsInfoPic=?, goodsInfoPrice=?,goodsInfoDescription=?" +
                ", goodsStock=?, flag=?, created=? , createdDate=? where id =?";
        Object[] paramObjs = {
                gi.getGoodsInfoName(),
                gi.getGoodsInfoPic(),
                gi.getGoodsInfoPrice(),
                gi.getGoodsInfoDescription(),
                gi.getGoodsInfoStock(),
                gi.getFlag(),
                gi.getCreated(),
                gi.getCreatedDate(),
                gi.getId()
        };
        return this.executeUpdate(sql, paramObjs);
    }

    //获取数据库已有的id值
//    public List<Integer> getGoodsId() {
//        //获取数据库的已有数据的编号
//        List<Integer> intList = new ArrayList<>();
//        GoodsInfo gi = null;
//        List<GoodsInfo> list = this.findByGoods(gi);
//        for (int i = 0; i < list.size(); i++) {
//            intList.add(list.get(i).getId());
//        }
//        return intList;
//    }

}
