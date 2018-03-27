package com.henghao.service.impl;

import com.henghao.dao.IWZPStatisctDao;
import com.henghao.entity.Result;
import com.henghao.entity.wzp.AjZfdata;
import com.henghao.entity.wzp.LawEntLatLng;
import com.henghao.entity.wzp.PieEntity;
import com.henghao.service.IWZPStatisctService;
import com.henghao.util.DateUtils;
import com.henghao.util.GsonUtil;
import com.henghao.util.ObjectUtil;
import com.henghao.util.UUidUtil;
import com.henghao.util.WZPCaseUtil;
import com.henghao.vo.CaseDetailVo;
import com.henghao.vo.CaseEntVo;
import com.henghao.vo.CaseFileVo;
import com.henghao.vo.CaseSJZVo;
import com.henghao.vo.CaseUserVo;
import com.henghao.vo.CaseVo;
import com.henghao.vo.FileVo;
import com.henghao.vo.PieVo;
import com.henghao.vo.RankVo;
import com.henghao.vo.SelectVo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.henghao.utils.BaiduUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
/**
 * @author wzp
 * @description: 统计信息业务逻辑实现类
 * @create on 2017/12/5.
 */
@Service("statisctService")
public class WZPStatisctServiceImpl implements IWZPStatisctService {

    @Resource
    private IWZPStatisctDao statisctDao;


    /**
     * 执法案件统计
     * @create by wzp on 2017/12/5
     * @return {@link Result}
     */
    public Result selectCaseStatisct() {


        /*
         * 1. 正办案件定义：执法结果为“整改中”
         * 2. 已办案件定义：执法结果为“未整改”或者“已整改”
         * 3. 代办案件定义：执法结果为“责令限期整改”，但是没有复查记录
         * 4. 逾期未结定义：首先属于代办范畴，并且，当前时间已超过责令书上面的规定时间
         * 5. 期限过半定义：首先属于逾期未结范畴，并且：
         *                  temp = 责令书中规定的时间-下达责令书的时间;
         *                  当前时间 >= temp
         *
         * ---------已结案件饼图统计---------，执法中
         * 1. 总数数量定义：已办案件数量
         * 2. 正常数量定义：按期办结数量
         * 3. 预警数量定义：期限过半数量
         * 4. 警告数量定义：逾期办结（逾期时间在一周内）
         * 5. 严重警告定义：逾期办结（逾期时间大于一周）
         */

        // 统计 - 正办案件 - 数量
        String sql_zb = "SELECT COUNT(*) FROM aj_zfdata WHERE ZFJG = '整改中'";
        Integer count_zb = statisctDao.getCount(sql_zb);

        // 统计 - 已办案件 - 数量
        String sql_yb = "SELECT COUNT(*) FROM aj_zfdata WHERE ZFJG = '未整改' OR ZFJG = '已整改'";
        Integer count_yb = statisctDao.getCount(sql_yb);

        // 查询代办集合
        List<CaseEntVo> list_db = this.findDBCaseEntVo();
        // 统计 - 代办案件 - 数量
        int count_db = list_db.size();

        // 逾期和期限过半 Start
        int count_yqwj = 0; // 逾期未结 - 数量
        int count_qxgb = 0; // 期限过半 - 数量

        // 代办集合大于0
        if (list_db.size() > 0) {
            // 获取当前时间yyyy-MM-dd HH:mm:ss
            String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
            // 从代办集合中循环，查出责令书下达时间
            for(CaseEntVo caseEntVo:list_db){
                // 查询这个企业的下达时间
                String sql_xdsj = "SELECT `TIME` FROM aj_sjz WHERE PARENTID='"+caseEntVo.getID()+"' AND SM='下达《责令限期整改指令书》'";
                // 查询返回对象
                List<String> times = statisctDao.getStrings(sql_xdsj);
                // 如果返回对象为空，说明没有逾期，也没有期限过半，则跳过这一循环
                if (times == null | times.size() < 1)
                    continue;
                String xdsj;
                if (times.size() == 1)
                    xdsj = times.get(0);
                else
                    xdsj = times.get(times.size()-1);
                // 责令书中的规定时间
                String time_gdsj = caseEntVo.getData();
                try {
                    Date date_xdsj = DateUtils.parseDateFromString(xdsj,"yyyy-MM-dd HH:mm");  // 下达时间
                    Date date_gdsj = DateUtils.parseDateFromString(time_gdsj,"yyyy-MM-dd HH:mm");  // 规定时间
                    Date date_now = DateUtils.parseDateFromString(currentDate); // 当前时间
                    // 计算两个日期之间相差的天数，当前时间减去规定时间
                    int subDays = DateUtils.getSubDays(date_gdsj, date_now);
                    // 如果结果大于0，说明当前时间大于或等于规定时间，以天为单位
                    if (subDays > 0 || subDays == 0){
                        // 逾期数自增
                        count_yqwj++;
                    }
                    // 规定时间减去下达时间
                    int subDays2 = DateUtils.getSubDays(date_xdsj, date_gdsj);
                    // 当前时间减去下达时间
                    int subDays3 = DateUtils.getSubDays(date_xdsj, date_gdsj);
                    // 如果subDays3 > subDays2/2 说明期限过半
                    if (subDays3 > subDays2/2){
                        count_qxgb++;   //期限过半自增
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            // 逾期和期限过半 End
        }
        // 按期办结和逾期办结 Start
        int count_ontime;   // 按期办结
        int count_overtime; // 逾期办结
        // 按期SQL
        String sql_ontime = "SELECT COUNT(*) FROM aj_sjz WHERE SM='复查' AND STR_TO_DATE(`TIME`,'%Y-%m-%d %H:%i') < STR_TO_DATE(XDWSSJ,'%Y-%m-%d %H:%i') AND CLZT <> '整改中'";
        // 逾期SQL
        String sql_overtime = "SELECT COUNT(*) FROM aj_sjz WHERE SM='复查' AND STR_TO_DATE(`TIME`,'%Y-%m-%d %H:%i') > STR_TO_DATE(XDWSSJ,'%Y-%m-%d %H:%i') AND CLZT <> '整改中'";
        // 查询返回值
        count_ontime = statisctDao.getCount(sql_ontime);    // 按期
        count_overtime = statisctDao.getCount(sql_overtime);    // 逾期
        // 按期办结和逾期办结 End
        /*
         * 2017年12月11日10:38:45做出修改
         * 不采用这种统计方式，所以先注释以前的，如果以后都确定不需要了之后，
         * 可以把这个注释快的内容删除掉。
         *
        // 饼图 - 已结案 - 统计 - Start
        int count_warn_closure; // 警告 - 统计数量
        int count_seriousWarn_closure;  // 严重警告 - 统计数量
        String sql_warn = "SELECT COUNT(*) FROM aj_sjz WHERE SM='复查' AND  UNIX_TIMESTAMP(STR_TO_DATE(`TIME`,'%Y-%m-%d %H:%i'))- UNIX_TIMESTAMP(STR_TO_DATE(XDWSSJ,'%Y-%m-%d %H:%i')) < 7*24*60*60 AND CLZT <> '整改中'";
        String sql_seriousWarn = "SELECT COUNT(*) FROM aj_sjz WHERE SM='复查' AND  UNIX_TIMESTAMP(STR_TO_DATE(`TIME`,'%Y-%m-%d %H:%i'))- UNIX_TIMESTAMP(STR_TO_DATE(XDWSSJ,'%Y-%m-%d %H:%i')) > 7*24*60*60 AND CLZT <> '整改中'";
        count_warn_closure = statisctDao.getCount(sql_warn);
        count_seriousWarn_closure = statisctDao.getCount(sql_seriousWarn);
        // 饼图 - 已结案 - 统计 - End*/
        CaseVo caseVo = new CaseVo(count_yb,count_zb,count_db,count_yqwj,count_qxgb,count_ontime,count_overtime);
        return new Result(0,"查询成功",caseVo);
    }



    /**
     * 根据ID查询案件详情
     * @create by wzp on 2017/12/6
     * @param ID 案件ID
     * @return {@link Result}
     */
    public Result selectDetailByID(String ID) {

        // 返回数据对象
        CaseDetailVo caseDetailVo;
        // 非空判断
        if (ID == null || "".equals(ID))
            return new Result(1,"传入参数ID为空",null);

        // 防止SQL注入
        String eID = StringEscapeUtils.escapeSql(ID);
        // 查询执法明细 START
        AjZfdata zfdata = statisctDao.getById(eID);
        // 查询执法明细 END

        // 查询执法人员信息 START

        // 获取这个案件中的执法人员，按逗号分隔
        String[] users = zfdata.getZfry().split("、");
        // 循环查询执法人员信息
        List<CaseUserVo> userVoList = new ArrayList<>();
        for (String username : users) {
            // 根据姓名查询信息

            // 查询人员基础信息
            String sql_user = "SELECT u.TELEPHONE,u.`NAME` AS username,u.POSITION,\n" +
                    "d.DEPT_NAME AS dept, h.SAVE_NAME AS fileName, h.EXTENTION AS fileSuffix \n " +
                    "FROM to_horizon_user u,to_horizon_dept d,tor_horizon_user_dept ud, ta_horizon_info h \n" +
                    "WHERE u.ID=ud.USER_ID \n" +
                    "AND u.ID=h.OBJECT_ID \n" +
                    "AND d.ID=ud.DEPT_ID \n" +
                    "AND u.`NAME`='" + username + "'";
            CaseUserVo userVo = statisctDao.getVo(sql_user, CaseUserVo.class);
            if (userVo != null){
                userVo.setImage(userVo.getFileName()+"."+userVo.getFileSuffix());
                userVoList.add(userVo);
                // 查询人员诚信分数 - Start
                // 获取本月第一天
                String firstDay = DateUtils.month3();
                // 获取当前时间 yyyy-MM-dd
                String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd");
                StringBuffer sb = new StringBuffer("http://localhost:8082/istration/userGrade/querySaveRedisGrade");
                sb.append("?start="+firstDay);
                sb.append("&end="+currentDate);
                sb.append("&name="+username);
                String url = sb.toString();
                // 访问周承耀的诚信接口，该接口根据用户名和时间段查询诚信分数，返回json串
                String json = GsonUtil.loadJSON(url);
                // 解析json串，将分数封装到包装类中
                WZPCaseUtil.userScroFromJson(userVo,json);
                // 查询人员诚信分数 - End
            }

        }
        // 查询执法人员信息 END


        // 查询执法影像（上传的图片） START

        // 根据ID查询企业名称
        String sql_ename = "SELECT QY FROM aj_zfdata WHERE ID='"+eID+"'";
        String ename = statisctDao.getString(sql_ename);
        // 根据企业名字查询所有图片
        String sql_image = "SELECT h.SAVE_NAME AS fileName,h.EXTENTION AS fileSuffix \n" +
                "FROM ta_horizon_info h, aj_zfdata z,aj_sjz s\n" +
                "WHERE h.OBJECT_ID = s.ID\n" +
                "AND z.ID=s.PARENTID\n" +
                "AND z.QY='"+ename+"'";
        List<CaseFileVo> fileVos = statisctDao.getVoList(sql_image, CaseFileVo.class);
        // 文件集合
        List<String> pic = new ArrayList<>();
        List<String> doc = new ArrayList<>();
        List<String> video = new ArrayList<>();
        for(CaseFileVo fileVo:fileVos){
            if (fileVo != null){
                String suffix = fileVo.getFileSuffix();
                String file = fileVo.getFileName()+"."+suffix;
                fileVo.setImage(file);
                if ("doc".equals(suffix) || "docx".equals(suffix)){
//                    fileVo.setType("doc");
                    doc.add(file);
                }else if ("jpg".equals(suffix) || "png".equals(suffix) || "gif".equals(suffix)){
                    fileVo.setType("pic");
                    pic.add(file);
                }else if(" avi mp4 rm rmvb 3gp mpeg mkv".indexOf(suffix) > 0) {
                    fileVo.setType("video");
                    video.add(file);
                }
            }

        }
        FileVo fileVo = new FileVo(pic,doc,video);

        // 查询执法影像（上传的图片） END

        // 时间轴 - START
        String sql_sjz = "SELECT s.SM,s.TIME,s.CLZT\n" +
                "FROM ta_horizon_info h, aj_zfdata z,aj_sjz s\n" +
                "WHERE h.OBJECT_ID = s.ID\n" +
                "AND z.ID=s.PARENTID\n" +
                "AND z.QY='"+ename+"'";
        List<CaseSJZVo> sjzVos = statisctDao.getVoList(sql_sjz, CaseSJZVo.class);


        // 时间轴 - END

        caseDetailVo = new CaseDetailVo(zfdata,userVoList,fileVo,sjzVos);
        return new Result(0,"查询成功",caseDetailVo);
    }

    /**
     * 1. 执法详情 - 正办案件
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    public Result selectInforceDetail() {

        // 从执法数据表中查询查询所有正办案件
        String sql_zb = "SELECT ID,QY AS ent FROM aj_zfdata WHERE ZFJG='整改中'";
        // 返回的数据
        List<CaseEntVo> list = this.findCaseEntlVoListBySql(sql_zb);
        if (list != null)
        return new Result(0, "查询成功", list);
        // 查询出错了
        return new Result(1, "发生未知错误", null);
    }

    /**
     * 2. 执法详情 - 已办案件
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    public Result selectClosureDetail() {
        // 从执法数据表中查询查询所有已办案件
        String sql_yb = "SELECT ID,QY AS ent FROM aj_zfdata WHERE ZFJG='未整改' OR ZFJG='已整改'";
        // 返回的数据
        List<CaseEntVo> list = this.findCaseEntlVoListBySql(sql_yb);

        if (list == null)
            // 查询出错了
            return new Result(1, "发生未知错误", null);
        // 查询成功
        return new Result(0, "查询成功", list);
    }

    /**
     * 3. 执法详情 - 代办案件
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    public Result selectCommissionDetail() {

        // 拿到代办集合
        List<CaseEntVo> list_ent = this.findDBCaseEntVo();
        // 如果集合为空，则直接返回空
        if (list_ent.size() < 1)
            return new Result(0,"代办案件为空",null);
        // 更新 list_ent 集合中企业的经纬度信息
        for(CaseEntVo caseEntVo:list_ent){
            this.updateCaseEntVoLatLng(caseEntVo);
        }
//        WZPCaseUtil.updateCaseEntVoLatLng(list_ent);
        return new Result(0,"查询成功",list_ent);
    }

    /**
     * 4. 执法详情 - 预期未结
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    public Result selectOverdueDetail() {

        // 先拿到代办集合
        List<CaseEntVo> dbCaseEntVo = this.findDBCaseEntVo();
        // 获取代办集合中，属于逾期未结的集合
        List<CaseEntVo> overdueList = this.getOverdueOrOverHalf(dbCaseEntVo, true);
        // 更新 overdueList 集合中企业的经纬度信息
        for(CaseEntVo caseEntVo:overdueList){
            this.updateCaseEntVoLatLng(caseEntVo);
        }
//        WZPCaseUtil.updateCaseEntVoLatLng(overdueList);
        return new Result(0,"查询成功",overdueList);
    }

    /**
     * 5. 执法详情 - 期限过半
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    public Result selectOverHalfDetail() {
        // 先拿到代办集合
        List<CaseEntVo> dbCaseEntVo = this.findDBCaseEntVo();
        // 获取代办集合中，属于期限过半的集合
        List<CaseEntVo> overdueList = this.getOverdueOrOverHalf(dbCaseEntVo, false);
        // 更新 overdueList 集合中企业的经纬度信息
        for(CaseEntVo caseEntVo:overdueList){
            this.updateCaseEntVoLatLng(caseEntVo);
        }
//        WZPCaseUtil.updateCaseEntVoLatLng(overdueList);
        return new Result(0,"查询成功",overdueList);
    }

    /**
     * 6. 执法详情 - 按期办结
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    public Result selectOntimeDetail() {

        String sql_aq = "SELECT ID,QY AS ent FROM aj_zfdata WHERE " +
                "ID IN (SELECT PARENTID FROM aj_sjz WHERE SM='复查' AND " +
                "STR_TO_DATE(`TIME`,'%Y-%m-%d %H:%i') < STR_TO_DATE(XDWSSJ,'%Y-%m-%d %H:%i') " +
                "AND CLZT <> '整改中')";
        // 返回的数据
        List<CaseEntVo> list = this.findCaseEntlVoListBySql(sql_aq);

        if (list == null)
            // 查询出错了
            return new Result(1, "发生未知错误", null);
        // 查询成功
        return new Result(0, "查询成功", list);
    }

    /**
     * 7. 执法详情 - 逾期办结
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    public Result selectOvertimeDetail() {

        // 查询逾期办结的包装类，ID和企业名
        String sql_yq = "SELECT ID,QY AS ent FROM aj_zfdata WHERE " +
                "ID IN (SELECT PARENTID FROM aj_sjz WHERE SM='复查' AND " +
                "STR_TO_DATE(`TIME`,'%Y-%m-%d %H:%i') > STR_TO_DATE(XDWSSJ,'%Y-%m-%d %H:%i') " +
                "AND CLZT <> '整改中')";

        // 返回的数据
        List<CaseEntVo> list = this.findCaseEntlVoListBySql(sql_yq);

        if (list == null)
            // 查询出错了
            return new Result(1, "发生未知错误", null);
        // 查询成功
        return new Result(0, "查询成功", list);

    }

    /**
     * 统计本月部门执法情况排名
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    public Result selectMonthRank() {

        // 返回数据
        List<RankVo> list = new ArrayList<>();
        try {
            // 查询所有部门名称
            String sql_dept = "SELECT DISTINCT DEPT_NAME FROM to_horizon_dept WHERE DEPT_NAME <> '管理员' AND DEPT_NAME <> '部门导航'";
            List<String> depts = statisctDao.getStrings(sql_dept);
            // 获取当前时间
            String currentDate = DateUtils.getCurrentDate("yyyy-MM");
            // 统计每个部门在当月的执法数量
            for(String dept:depts){
                String sql_count = "SELECT COUNT(*) FROM aj_zfdata z,aj_sjz s \n" +
                        "WHERE s.PARENTID=z.ID \n" +
                        "AND z.ZFBM='"+dept+"' \n" +
                        "AND s.TIME LIKE '"+currentDate+"%'";
                Integer count = statisctDao.getCount(sql_count);
                RankVo rankVo = new RankVo(dept,count);
                list.add(rankVo);
            }
            return new Result(0,"查询成功",list);
        }catch (Exception e){
            // 查询出错
            e.printStackTrace();
        }
        return new Result(1,"发生未知错误",null);
    }

    /**
     * 执法 - 统计 - 饼图（包括执法中和已结案）
     * @return {@link Result}
     * @create by wzp on 2017/12/11
     */
    @Override
    public Result selectCountLawPie(SelectVo selectVo) {
        /*
         * 2017年12月11日10:54:13 定义饼图统计（其实是修改之前的定义。。。）
         * 1. 总数量
         *    1.1 执法中：正办案件数量
         *    1.2 已结案：已办案件数量
         * 对于zf_data表中的SFYH（是否存在安全隐患）、YHSM（安全隐患说明）、ZFJG（执法结果）
         * 以及执法影像这4个元素
         * 2. 正常：4个元素全部都有数据
         * 3. 预警：缺少1个元素以内
         * 4. 警告：缺少1-3个元素
         * 5. 严重警告：缺少4元素
         *
         */

        String starttime = selectVo.getSTARTTIME();
        String endtime = selectVo.getENDTIME();

        // 非空
        if (starttime == null || endtime == null || "".equals(starttime) || "".equals(endtime))
            return new Result(1,"查询时间为空",null);
        // 返回对象
        PieEntity pi;

        // 执法中的案件 - Start
        int in_normal = 0;      // 执法中-数量-正常
        int in_previous = 0;    // 执法中-数量-预警
        int in_warn = 0;        // 执法中-数量-警告
        int in_serious = 0;     // 执法中-数量-严重警告

        // 查询执法中案件的SQL
        String sql = "SELECT z.ID AS SID,z.SFYH,z.YHSM,z.ZFJG \n" +
                "FROM aj_zfdata z,aj_sjz s \n" +
                "WHERE z.ID=s.PARENTID \n" +
                "AND z.ZFJG = '整改中' \n" +
                "AND STR_TO_DATE(s.TIME,'%Y-%m-%d %H:%i') " +
                "BETWEEN STR_TO_DATE('"+starttime+"','%Y-%m-%d %H:%i') " +
                "AND STR_TO_DATE('"+endtime+"','%Y-%m-%d %H:%i')";
        List<PieVo> voList = statisctDao.getVoList(sql, PieVo.class);
        if (voList != null && voList.size() > 0){
            for (PieVo pi_in:voList) {
                // 查询第四个元素，即是否有文件（执法影像）
                String e4 = "SELECT COUNT(*) FROM ta_horizon_info WHERE OBJECT_ID='"+pi_in.getSID()+"'";
                pi_in.setFILE(statisctDao.getCount(e4)==0?"":"1");
                // 判断这一个对象，总共有几个元素为空
                int count = ObjectUtil.countNullProperty(pi_in);
                if (count == 0)
                    in_normal++;    // 正常
                else if (count <= 1)
                    in_previous++;  // 预警
                else if (count <= 3)
                    in_warn++;      // 警告
                else in_serious++;  // 严重警告
            }
        }
        // 执法中的案件 - End

        // 已结案 - Start
        int al_normal = 0;      // 已结案-数量-正常
        int al_previous = 0;    // 已结案-数量-预警
        int al_warn = 0;        // 已结案-数量-警告
        int al_serious = 0;     // 已结案-数量-严重警告

        // 查询已结案的SQL
        String sql_al = "SELECT z.ID AS SID,z.SFYH,z.YHSM,z.ZFJG \n" +
                "FROM aj_zfdata z,aj_sjz s \n" +
                "WHERE z.ID=s.PARENTID \n" +
                "AND (z.ZFJG = '未整改' OR z.ZFJG = '已整改') \n" +
                "AND STR_TO_DATE(s.TIME,'%Y-%m-%d %H:%i') " +
                "BETWEEN STR_TO_DATE('"+starttime+"','%Y-%m-%d %H:%i') " +
                "AND STR_TO_DATE('"+endtime+"','%Y-%m-%d %H:%i')";
        List<PieVo> vo_al = statisctDao.getVoList(sql_al, PieVo.class);
        if (vo_al != null && vo_al.size() > 0){
            for (PieVo pi_al:vo_al) {//3.93
                // 查询第四个元素，即是否有文件（执法影像）
                String e4 = "SELECT COUNT(*) FROM ta_horizon_info WHERE OBJECT_ID='"+pi_al.getSID()+"'";
                pi_al.setFILE(statisctDao.getCount(e4)==0?null:"1");
                // 判断这一个对象，总共有几个元素为空
                int count_al = ObjectUtil.countNullProperty(pi_al);
                if (count_al == 0){
                    al_normal++;    // 正常
                }else if (count_al <= 1){
                    al_previous++;  // 预警
                }else if (count_al <= 3){
                    al_warn++;      // 警告
                }
                else al_serious++;  // 严重警告
            }
        }
        // 已结案 - End
        pi = new PieEntity(in_normal, in_previous, in_warn, in_serious, al_normal, al_previous, al_warn, al_serious);
        return new Result(0,"查询成功",pi);
    }

//    /**
//     * 远东数据库测试查询
//     * @return {@link Result}
//     * @create by wzp on 2017/12/6
//     */
    /*
    public Result selectDataBaseFromYD(String ename) {

        String eName = StringEscapeUtils.escapeSql(ename);
//        String sql = "SELECT ENTNAME,LONGITUDE,LATITUDE FROM SM_ENTERPRISE WHERE ENTNAME='"+eName+"'";
        String sql = "SELECT ENTNAME,LONGITUDE,LATITUDE FROM SM_ENTERPRISE WHERE ENTNAME='"+eName+"'";
        List<EntVo> entVoList = ydDao.getEmpVoList(sql, EntVo.class);

        return new Result(0,"",entVoList);
    }
    */


    // 私有化内部方法，封装使用，避免代码重复 - START

    /**
     * 1. 根据SQL语句查询案件企业包装类集合
     * 如果查询出错，则返回null
     * @param sql 查询语句
     * @return List<CaseEntlVo>
     */
    private synchronized List<CaseEntVo> findCaseEntlVoListBySql(String sql){
        List<CaseEntVo> list;
        try {
            list = statisctDao.getVoList(sql,CaseEntVo.class);
        }catch (Exception e){
            // 查询出错
            return null;
        }
        // 完善 CaseEntVo 中企业的经纬度数据
        if (list != null && list.size() > 0){
            for(CaseEntVo caseEntVo:list){
                this.updateCaseEntVoLatLng(caseEntVo);
            }
        }
        // 将信息放入包装类中
        return list;
    }


    /**
     * 2. 查询执法 - 代办 - 包装类List<CaseEntVo> 只有企业ID（ID）、企业名（ent）和责令书下达时间（data）
     * @return 包装类List<CaseEntVo>
     */
    private List<CaseEntVo> findDBCaseEntVo(){
        // 返回值
        List<CaseEntVo> DBList = new ArrayList<>();  // 代办包装类集合

        // 先查询所有执法结果为“限令期限整改”的ID 和 企业名称
        String sql_zlzg = "SELECT ID,QY AS ent,data FROM aj_zfdata WHERE ZFJG = '责令限期整改'";
        // 查询结果 责令整改集合
        List<CaseEntVo> list_zlzg = statisctDao.getVoList(sql_zlzg, CaseEntVo.class);
        // 如果集合为空，则直接返回
        if (list_zlzg == null || list_zlzg.size() < 1) {
            return DBList;
        } else {
            // 否则，循环根据企业名称查询是否已复查
            for (CaseEntVo caseEntVo : list_zlzg) {

                /*
                 * 先查询这个企业在执法数据表中，执法结果字段ZFJG不等于“责令限期整改”的数量，
                 * 如果大于1，再去判断是否已经复查，反之则属于代办
                 */
                // 查询执法结果字段ZFJG不等于“责令限期整改”的SQL
                String sql_not_zlzg = " SELECT COUNT(*) FROM aj_zfdata WHERE QY= '" + caseEntVo.getEnt() + "' AND ZFJG <> '责令限期整改'";
                // 返回数量
                Integer count_not_zlzg = statisctDao.getCount(sql_not_zlzg);
                if (count_not_zlzg > 1) {
                    // 用于控制代办数量自增，因为是根据企业名称循环查询的，只控制在这个for循环里面自增才正确
                    boolean temp = true;
                    // 查询下达责令整改文书的时间，下达时间
                    String sql_xdsj = "SELECT `TIME` FROM aj_sjz WHERE PARENTID='" + caseEntVo.getID() + "' AND SM='下达《责令限期整改指令书》'";
                    // 下达时间集合
                    List<String> times = statisctDao.getStrings(sql_xdsj);
                    // 循环这个时间集合，进行匹配
                    for (String time_xdsj : times) {
                        // 又去执法数据表中查询，执法结果不等于“限令期限整改”，查询ID，
                        String sql_id = "SELECT `ID` FROM aj_zfdata WHERE QY = '" + caseEntVo.getEnt() + "' AND ZFJG <> '责令限期整改' ";
                        List<String> ids = statisctDao.getStrings(sql_id);
                        for (String str_id:ids){
                            // 然后根据ID去时间轴表中查询复查记录，下达文书的时间等于上面的下达时间，并且结果不等于整改中
                            String sql_fc = "SELECT COUNT(*) FROM aj_sjz WHERE PARENTID='" + str_id + "' AND XDWSSJ = '" + time_xdsj + "' AND CLZT <> '整改中'";
                            Integer count_fc = statisctDao.getCount(sql_fc);
                            // 如果数量大于0，说明已复查。
                            if (count_fc > 0) {
                                temp = false;
                                break;
                            }
                        }
                        // 跳出times循环
                        if (!temp) break;
                    }
                    // 如果上面的循环走完，即根据这个企业的名字查询所有的下达时间都没有处于整改中的情况
                    // 说明它没有复查
                    if (temp){
                            DBList.add(caseEntVo);
                    }
                } else {
                    // 返回数量不大于1，则符合代办
                       DBList.add(caseEntVo);
                }
            }
            // 返回
            return DBList;
        }

    }


    /**
     * 3. 查询逾期未结或者期限过半包装类集合
     * @param dbVoList 代办包装类集合只有企业ID（ID）、企业名（ent）和责令书下达时间（data）
     * @param overdue 是否查询逾期未办，true 为查询逾期未结，false 为查询期限过半
     * @return Object
     */
    private List<CaseEntVo> getOverdueOrOverHalf(List<CaseEntVo> dbVoList, boolean overdue) {

        // 返回值
        List<CaseEntVo> overdueListRes = new ArrayList<>(); // 集合 - 逾期未结
        List<CaseEntVo> overHalfListRes = new ArrayList<>(); // 集合 - 期限过半

        // 代办集合大于0
        if (dbVoList.size() > 0) {
            // 获取当前时间yyyy-MM-dd HH:mm:ss
            String currentDate = DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss");
            // 从代办集合中循环，查出责令书下达时间
            for (CaseEntVo caseEntVo : dbVoList) {
                // 查询下达时间
                String sql_xdsj = "SELECT `TIME` FROM aj_sjz WHERE PARENTID='" + caseEntVo.getID() + "' AND SM='下达《责令限期整改指令书》'";
                // 查询返回对象
                String xdsj = statisctDao.getString(sql_xdsj);
                // 如果返回为空，说明没有逾期，也没有期限过半，则跳过这一循环
                if (xdsj == null || "".equals(xdsj))
                    continue;
                // 责令书中的规定时间
                String time_gdsj = caseEntVo.getData();

                try {
                    Date date_xdsj = DateUtils.parseDateFromString(xdsj, "yyyy-MM-dd HH:mm");  // 下达时间
                    Date date_gdsj = DateUtils.parseDateFromString(time_gdsj, "yyyy-MM-dd HH:mm");  // 规定时间
                    Date date_now = DateUtils.parseDateFromString(currentDate); // 当前时间
                    // 计算两个日期之间相差的天数，当前时间减去规定时间
                    int subDays = DateUtils.getSubDays(date_gdsj, date_now);
                    // 如果结果大于0，说明当前时间大于或等于规定时间，以天为单位
                    if (subDays > 0 || subDays == 0) {
                        // 查询包装类集合，并且查询的是预期未结
                        if (overdue) {
                            overdueListRes.add(caseEntVo);  // 添加进逾期未结集合
                        }
                    }
                    // 规定时间减去下达时间
                    int subDays2 = DateUtils.getSubDays(date_xdsj, date_gdsj);
                    // 当前时间减去下达时间
                    int subDays3 = DateUtils.getSubDays(date_xdsj, date_gdsj);
                    // 如果subDays3 > subDays2/2 说明期限过半
                    if (subDays3 > subDays2 / 2) {
                        if (!overdue) {
                            overHalfListRes.add(caseEntVo); // 添加进逾期未结集合
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            // 正确返回
            if (overdue) return overdueListRes;
            return overHalfListRes;
        }
        // 集合小于1返回
        return overHalfListRes;

    }
    // 私有化内部方法，封装使用，避免代码重复 - END

    /**
     * 4. 保存 企业的经纬度 到本地
     * @param entLatLng LawEntLatLng
     */
    private synchronized void saveLawEntLatLng(LawEntLatLng entLatLng){
        try {
            statisctDao.save(entLatLng,LawEntLatLng.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 5. 完善CaseEntVo中企业的经纬度数据
     * @param caseEntVo CaseEntVo 企业名称不能为空
     * @return CaseEntVo
     */
    private synchronized CaseEntVo updateCaseEntVoLatLng(CaseEntVo caseEntVo){
        if (caseEntVo != null) {
            // 企业名称
            String entName = caseEntVo.getEnt();
            // 企业名称为空直接返回
            if(entName == null || "".equals(entName))
                return caseEntVo;
            // 根据企业名称查询经纬度
            String sql_lat = "SELECT entLng AS lng,entLat AS lat FROM sa_law_ent WHERE lawEnt='"+entName+"' ";
            CaseEntVo latLng = statisctDao.getVo(sql_lat, CaseEntVo.class);
            if (latLng == null){
                // 本地数据库中没有经纬度信息，需要调用百度接口查询经纬度
                // 根据企业名称查询经纬度
                JSONObject lngLat = BaiduUtil.getLngLat(entName);
                if (lngLat != null) {
                    // 查询返回状态码
                    Object res = lngLat.get("status");
                    if (res != null) {
                        String status = lngLat.get("status").toString();
                        if ("1".equals(status)) {
                            // 查询成功
                            String baidu_lat = lngLat.get("lat").toString();    // 纬度
                            String baidu_lng = lngLat.get("lng").toString();    // 经度
                            caseEntVo.setLat(baidu_lat);
                            caseEntVo.setLng(baidu_lng);
                            // 将经纬度保存到本地
                            Timestamp ts = new Timestamp(System.currentTimeMillis());
                            LawEntLatLng entLatLng = new LawEntLatLng(UUidUtil.getGSUUid32(),entName,baidu_lng,baidu_lat,ts);
                            this.saveLawEntLatLng(entLatLng);
                        }
                    }
                }
            }else{
                caseEntVo.setLat(latLng.getLat());
                caseEntVo.setLng(latLng.getLng());
            }

        }
        return caseEntVo;
    }



	@Override
	public Map<String, Object> queryInventory(String start, String end, String name, int page, int size) {
		Map<String, Object> map = statisctDao.queryInventory(start, end, name, page, size);
		return map;
	}





}
