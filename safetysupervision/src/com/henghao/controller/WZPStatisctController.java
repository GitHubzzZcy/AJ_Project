package com.henghao.controller;

import com.henghao.entity.Result;
import com.henghao.service.IWZPStatisctService;
import com.henghao.util.WZPCaseUtil;
import com.henghao.vo.SelectVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wzp
 * @description: 统计信息控制层
 * @create on 2017/12/5.
 */
@Controller
@RequestMapping("/wzpstatisct")
public class WZPStatisctController {

    // service接口
    @Resource
    private IWZPStatisctService statisctService;


    /**
     * 执法案件统计
     *
     * @return {@link Result}
     * @create by wzp on 2017/12/5
     */
    @ResponseBody
    @RequestMapping(value = "/case", produces = "application/json;charset=utf-8")
    public Result CaseStatisct() {
        Result result;
        try {
            result = statisctService.selectCaseStatisct();
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执法详情 - START
     */


    /**
     * 根据ID查询案件详情
     *
     * @param ID 案件ID
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    @ResponseBody
    @RequestMapping(value = "/detail/caseByID", produces = "application/json;charset=utf-8")
    public Result caseDetailById(String ID, HttpServletResponse response) {
        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectDetailByID(ID);
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 1. 执法详情 - 正办案件
     *
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    @ResponseBody
    @RequestMapping(value = "/detail/inforce", produces = "application/json;charset=utf-8")
    public Result inforceDetail(HttpServletResponse response) {

        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectInforceDetail();
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 2. 执法详情 - 已办案件
     *
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    @ResponseBody
    @RequestMapping(value = "/detail/closure", produces = "application/json;charset=utf-8")
    public Result closureDetail(HttpServletResponse response) {
        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectClosureDetail();
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 3. 执法详情 - 代办案件
     *
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    @ResponseBody
    @RequestMapping(value = "/detail/commission", produces = "application/json;charset=utf-8")
    public Result commissionDetail(HttpServletResponse response) {
        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectCommissionDetail();
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 4. 执法详情 - 预期未结
     *
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    @ResponseBody
    @RequestMapping(value = "/detail/overdue", produces = "application/json;charset=utf-8")
    public Result overdueDetail(HttpServletResponse response) {
        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectOverdueDetail();
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 5. 执法详情 - 期限过半
     *
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    @ResponseBody
    @RequestMapping(value = "/detail/overhalf", produces = "application/json;charset=utf-8")
    public Result overHalfDetail(HttpServletResponse response) {
        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectOverHalfDetail();
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 6. 执法详情 - 按期办结
     *
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    @ResponseBody
    @RequestMapping(value = "/detail/ontime", produces = "application/json;charset=utf-8")
    public Result ontimeDetail(HttpServletResponse response) {
        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectOntimeDetail();
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 7. 执法详情 - 逾期办结
     *
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    @ResponseBody
    @RequestMapping(value = "/detail/overtime", produces = "application/json;charset=utf-8")
    public Result overtimeDetail(HttpServletResponse response) {
        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectOvertimeDetail();
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 执法详情 - END
     */

    /**
     * 统计本月部门执法情况排名
     * @return {@link Result}
     * @create by wzp on 2017/12/6
     */
    @ResponseBody
    @RequestMapping(value = "/rank/month", produces = "application/json;charset=utf-8")
    public Result rankMonth(HttpServletResponse response){
        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectMonthRank();
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 执法 - 统计 - 饼图（包括执法中和已结案）
     * @return {@link Result}
     * @create by wzp on 2017/12/11
     */
    @ResponseBody
    @RequestMapping(value = "/count/law/pie", produces = "application/json;charset=utf-8")
    public Result countLawPie(HttpServletResponse response, SelectVo selectVo){
        // 解决跨域
        WZPCaseUtil.cross(response);
        Result result;
        try {
            result = statisctService.selectCountLawPie(selectVo);
        } catch (Exception e) {
            result = new Result(1, "未知错误", null);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 督责问责-----》权责清单
     */
    @ResponseBody
    @RequestMapping(value="/queryInventory", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
    public Result queryInventory(@RequestParam(defaultValue="2014-01-01 00:00") String start, 
    							@RequestParam(defaultValue="2028-01-01 00:00") String end, 
    							@RequestParam(required=false) String name, 
    							@RequestParam(defaultValue = "1")int page , 
    							@RequestParam(defaultValue = "10")int size) {
    	try {
			Map<String,Object> map = statisctService.queryInventory(start, end, name, page, size);
			return new Result(0, "查询成功", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new Result(1, "查询失败", null);
    }
   
}
