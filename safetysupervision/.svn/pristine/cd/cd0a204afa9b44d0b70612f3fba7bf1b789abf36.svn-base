package com.henghao.service.impl;

import com.henghao.dao.IWZPUserDao;
import com.henghao.entity.Result;
import com.henghao.entity.wzp.ToHorizonUserEntity;
import com.henghao.entity.wzp.TorHorizonUserDeptEntity;
import com.henghao.service.IWZPUserService;
import com.henghao.util.GsonUtil;
import com.henghao.util.ObjectUtil;
import com.henghao.util.WZPCaseUtil;
import com.henghao.util.WZPUserIdUtil;
import com.henghao.vo.IntegrityVo;
import com.henghao.vo.ScoreVo;
import com.henghao.vo.SelectUserVo;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wzp
 * @description: 用户service实现类
 * @create 2017/11/28.
 */
@Service("userService")
public class WZPUserServiceImpl implements IWZPUserService {

    // dao接口
    @Resource
    IWZPUserDao<ToHorizonUserEntity> userDao;
    // redis
    @Resource
    private RedisService redisService;

    /*
     * 修改人员信息
     * create by wzp on 2017/11/28
     */
    public Result updatePersonal(ToHorizonUserEntity userEntity, TorHorizonUserDeptEntity userDept) {
        // 返回对象
        Result result;

        // 用户输入的包装对象非空
        if (userEntity == null || userDept == null) {
            return new Result(1, "系统繁忙", null);
        }
        // 属性
        String id = userEntity.getId();
        String deptId = userDept.getDeptId();
        // 非空验证
        if (id == null || "".equals(id) || deptId == null || "".equals(deptId)) {
            return new Result(1, "用户ID为空", null);
        }

        // 防止SQL注入
        String eID = StringEscapeUtils.escapeSql(id);
        String eDeptId = StringEscapeUtils.escapeSql(deptId);

        // 根据ID查询数据库
        ToHorizonUserEntity personal = userDao.getById(eID);
        // 返回对象非空
        if (personal == null) {
            result = new Result(1, "查无此人", null);
            return result;
        }
        try {
            // 将数据库信息同步到对象上，再做修改。
            ToHorizonUserEntity user = (ToHorizonUserEntity) ObjectUtil.updateNew(personal, userEntity);
            // 修改用户信息
            this.userDao.update(user);
            if (!(eDeptId == null || "".equals(eDeptId))){
                String sql_dept = "SELECT COUNT(*) FROM to_horizon_dept WHERE ID='"+eDeptId+"'";
                Integer count = this.userDao.getCount(sql_dept);
                if (count > 0){
                    // 修改：用户-部门中间表信息
                    String sql_update = "UPDATE tor_horizon_user_dept SET DEPT_ID = '" + eDeptId + "' WHERE USER_ID = '" + eID + "' ";
                    this.userDao.executeSqlUpdate(sql_update);
                }
            }
            result = new Result(0, "个人资料修改成功", null);
        } catch (Exception e) {   // 修改失败
            e.printStackTrace();
            result = new Result(1, "系统繁忙", null);
        }
        return result;

    }

    /*
    * 根据用户ID查询用户所属部门ID
    *
    * */
    public Result selectDeptIdByUserId(String userId) {

        // 非空验证
        if (userId == null || "".equals(userId)) {
            return new Result(1, "用户ID为空", null);
        }

        // 防止SQL注入
        String eID = StringEscapeUtils.escapeSql(userId);
        // 查询用户-部门中间表
        String sql_select = "SELECT DEPT_ID FROM tor_horizon_user_dept WHERE USER_ID ='" + eID + "' ";
        String deptId;
        try {
            deptId = this.userDao.getString(sql_select);
        } catch (Exception e) {
            return new Result(1, "系统发生错误，请稍后再试", null);
        }
        // 成功返回
        return new Result(0, "查询成功", deptId);
    }

    /**
     * 用户登录成功后，将用户ID传到这里
     *
     * @param uid 成功登录的用户ID
     * @return Result
     * @create create by wzp on 2017/12/1
     */
    public Result getUserId(String uid) {
        // 非空
        if (uid != null && !("".equals(uid))) {
            // 防止SQL注入
            String eID = StringEscapeUtils.escapeSql(uid);
            // 根据ID去数据库查询
            ToHorizonUserEntity user = userDao.getById(eID);
            // 返回对象不为空，
            if (user != null)
                WZPUserIdUtil.setUserId(uid);
            return new Result(0, "成功", null);
        }
        return new Result(1, "失败", null);
    }

    /**
     * 查询用户代办数量
     * 个人代办、待阅事宜
     *
     * @return Result
     * @create create by wzp on 2017/12/1
     */
    public Result selectUserCount() {

        // 返回对象
        SelectUserVo selectVo;
        // 获取用户ID
        String userID = WZPUserIdUtil.getUserID();
        // ID非空
        if (userID == null || "".equals(userID)) {
            selectVo = new SelectUserVo(0, 0);
            return new Result(1, "系统繁忙", selectVo);
        }
        // 个人代办数量
        int count_GRDB;
        // 可阅事宜数量
        int count_KYSY;
        StringBuffer sb = new StringBuffer("http://localhost:8082/statisticsInfo/user/myMessages");
//        StringBuffer sb = new StringBuffer("http://222.85.156.33:8082/statisticsInfo/user/myMessages");
        sb.append("?uid="+userID);
        String url = sb.toString();
        try {
            String json = GsonUtil.loadJSON(url);
            selectVo = WZPCaseUtil.selectVoFromJson(json);
            // 正确返回
            return new Result(0, "查询成功", selectVo);
        }catch (Exception e){
            // 错误返回
            selectVo = new SelectUserVo(0,0);
            return new Result(1, "查询成功", selectVo);
        }





        /*
        // 个人代办查询语句
        String sql_GRDB = "SELECT COUNT(*) FROM " +
                "(SELECT  DISTINCT a.ID,a.TITLE,a.FLOWNAME,a.SENDUSERNAME,a.SENDTIME,a.WORKID," +
                "a.TRACKID,a.SUBJECTION_ID,a.SUBJECTION_TYPE " +
                "FROM TW_HZ_WorkList a WHERE (\n a.AUTH_ID = '" + userID + "'\n " +
                " OR a.AGENT_ID = '" + userID + "' )\n " +
                "AND ISACTIVE = '1'\n" +
                "AND STATUS_NO < '200' ORDER BY a.SENDTIME DESC) t1";

        // 可阅事宜查询语句
        String sql_KYSY = "SELECT COUNT(*) FROM (SELECT a.ID,a.TITLE,a.FLOWNAME,a.SENDUSERNAME,a.SENDTIME,\n" +
                "a.WORKID,a.SUBJECTION_TYPE,a.SUBJECTION_ID \n" +
                "FROM TW_HZ_WorkList a  \n" +
                "WHERE `STATUS` = 'Reader' AND \n" +
                "ISACTIVE = '1' AND \n" +
                "(AUTH_ID = '\"+userID+\"' OR \n" +
                "(AUTH_ID = '-null-' AND \n" +
                "SUBJECTION_ID in ( SELECT ID FROM to_horizon_user) AND \n" +
                "SUBJECTION_TYPE <> 'S') OR \n" +
                "(AUTH_ID = '-null-' AND \n" +
                "SUBJECTION_ID =(SELECT DEPT_ID FROM tor_horizon_user_dept WHERE USER_ID='\"+userID+\"') AND \n" +
                "SUBJECTION_TYPE = 'S')) AND \n" +
                "a.WORKID not in \n" +
                "(select WORKID from TW_HZ_WorkList \n" +
                "where `STATUS` = 'Readed' AND \n" +
                "AUTH_ID = '\"+userID+\"') ORDER BY a.SENDTIME DESC) t2";
        try {
            // 返回值
            count_GRDB = userDao.getCount(sql_GRDB);
            // 返回值
            count_KYSY = userDao.getCount(sql_KYSY);
            
            // 封装进对象
            selectVo = new SelectUserVo(count_GRDB, count_KYSY);
            // 返回
            return new Result(0, "查询成功", selectVo);
        } catch (Exception ee) {
            // 查询时报错
        }
        return new Result(1, "查询失败", null);
        */
    }

    /**
     * 根据用户名查询诚信管理信息
     * @param username 用户名
     * @return Result
     */

    public Result selectIntegrityByUserName(String username) {

        // 非空判断
        if ("".equals(username) || username == null)
            return new Result(1, "用户名为空", null);

        // 防止SQL注入
        String eName = StringEscapeUtils.escapeSql(username);
        // 查询用户信息 - Start
        String sql = "SELECT \n" +
                "u.`NAME` AS username,u.SEX, u.POSITION AS duty,u.TELEPHONE AS tel,\n" +
                "h.SAVE_NAME AS fileName,h.EXTENTION AS fileSuffix,dept_Name AS deptName \n" +
                "FROM to_horizon_dept d \n" +
                "INNER JOIN tor_horizon_user_dept ud  \n" +
                "ON ud.DEPT_ID = d.ID \n" +
                "INNER JOIN to_horizon_user u \n" +
                "ON u.ID = ud.USER_ID\n" +
                "LEFT JOIN ta_horizon_info h \n" +
                "ON u.ID = h.OBJECT_ID \n" +
                "WHERE DEPT_NAME <>'管理员' \n" +
                "AND u.`NAME` = '" + eName + "' ";
        // 返回集合
        List<IntegrityVo> voList = userDao.getVoList(sql, IntegrityVo.class);
        // 用户名不存在
        if (voList == null || voList.size() < 1)
            return new Result(1, "用户名不存在", null);
        // 查询用户信息 - End

        // 查询用户的分数 - Start
        for (IntegrityVo integrityVo : voList) {
            // 获取用户名
            String uname = integrityVo.getUsername();
            // 根据用户名查询用户分数及项
            String sql_score = "SELECT h.TITLE AS title ,SUM(FENZHI) AS score \n" +
                    "FROM honesty_enter he,honesty_enterfujian h \n" +
                    "WHERE he.ID = h.PARENTID AND \n" +
                    "NAME like '%" + uname + "%'\n" +
                    "GROUP BY h.TITLE";
            // 返回集合
            List<ScoreVo> scoreVos = userDao.getVoList(sql_score, ScoreVo.class);
            // 设置入IntegrityVo对象
            integrityVo.setScoreVoList(scoreVos);
            // 文件名
            String fileName = integrityVo.getFileName();
            // 后缀名
            String suffix = integrityVo.getFileSuffix();
            // 文件名非空的时候
            if (fileName != null && !"".equals(fileName))
                integrityVo.setImage(fileName+"."+suffix);

        }
        // 查询用户的分数 - End

        return new Result(0, "查询成功", voList);
    }

}
