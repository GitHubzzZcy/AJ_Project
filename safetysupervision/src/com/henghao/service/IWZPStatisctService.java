package com.henghao.service;

import java.util.Map;

import com.henghao.entity.Result;
import com.henghao.vo.SelectVo;

/**
 * @author wzp
 * @description: 统计信息Service接口
 * @create on 2017/12/5.
 */
public interface IWZPStatisctService {

    /**
     * 执法案件统计
     * @create by wzp on 2017/12/5
     * @return {@link Result}
     */
    Result selectCaseStatisct();

    /**
     * 1. 执法详情 - 正办案件
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    Result selectInforceDetail();

    /**
     * 根据ID查询案件详情
     * @create by wzp on 2017/12/6
     * @param ID 案件ID
     * @return {@link Result}
     */
    Result selectDetailByID(String ID);

    /**
     * 2. 执法详情 - 已办案件
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    Result selectClosureDetail();

    /**
     * 3. 执法详情 - 代办案件
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    Result selectCommissionDetail();

    /**
     * 4. 执法详情 - 预期未结
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    Result selectOverdueDetail();

    /**
     * 5. 执法详情 - 期限过半
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    Result selectOverHalfDetail();

    /**
     * 6. 执法详情 - 按期办结
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    Result selectOntimeDetail();

    /**
     * 7. 执法详情 - 逾期办结
     * @create by wzp on 2017/12/6
     * @return {@link Result}
     */
    Result selectOvertimeDetail();

    /**
     * 统计本月部门执法情况排名
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    Result selectMonthRank();

    /**
     * 执法 - 统计 - 饼图（包括执法中和已结案）
     * @return {@link Result}
     * @create by wzp on 2017/12/11
     */
    Result selectCountLawPie(SelectVo selectVo);

	Map<String, Object> queryInventory(String start, String end, String name, int page, int size);

//    /**
//     * 远东数据库测试查询
//     * @return {@link Result}
//     * @create by wzp on 2017/12/6
//     */
//    Result selectDataBaseFromYD(String ename);
}
