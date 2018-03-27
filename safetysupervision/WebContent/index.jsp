<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>jsp页面</title>
</head>
<body>
<div>
	<h1>对不起不能访问</h1>
      <!--   
    <a href="checking/findKgListByUserId?date1=2017-10-22&date2=2017-10-23">按年月周时间段旷工次数查询</a>
    <a href="attendance/leave?date1=2017-06-24&date2=2017-06-28">按时间段查询迟到</a>
    <a href="attendance/early?date1=2017-06-24&date2=2017-06-28">按时间段查询早退</a>
    <a href="attendance/monthLate?date1=2017-06-24&date2=2017-06-28">按月查询迟到</a>
    <a href="attendance/yearLate?date1=2017-06-24&date2=2017-06-28">按年查询迟到</a>
    <a href="attendance/monthEarly?date1=2017-06-24&date2=2017-06-28">按月查询早退</a>
    <a href="attendance/yearEarly?date1=2017-06-24&date2=2017-06-28">按年查询早退</a>
    <p>
    <a href="integrity/scoreStatistics?deptName=二大队&name=">分数详情</a>
    <a href="integrity/comprehensiveScore?deptName=&name=">人员信息</a>
    <a href="integrity/carNumType">车辆座位类型</a>
    <a href="integrity/useCarType?date1=2017-06-01&date2=2017-06-21">按日期查询用车次数</a>
    <a href="integrity/carInfo?carNo=贵A60957">车辆基本信息</a>
    <a href="integrity/maintainInfo?carNo=贵AC6938">车辆保养信息</a>
    <a href="integrity/carInfomation">所有车辆信息</a>
    <p>
    <a href="efficiency/personleSex">男女人数</a>
    <a href="efficiency/deptPersonleNum">部门人数</a>
    <a href="efficiency/personleNum?date1=2017-06-01&date2=2017-06-28">按时间段查询人数</a>
    <a href="efficiency/ageAll">年龄阶段人数</a>
    <p>
    <a href="integrity/carsState?date1=2017-09-15&date2=2017-09-17">车辆状态</a>
    <a href="integrity/carUseInfo?carNo=贵AC6938">用车信息</a>
    <p>
    <a href="attendance/withoutReason?date1=2017-10-18&date2=2017-10-19">按时间段年月周查询无故外出次数</a>
    <a href="checking/findAbsenteeismNum?date1=2017-10-18&date2=2017-10-19">按年月周时间段旷工人数查询</a>
    <a href="attendance/findLateAndearlyNum?date1=2017-06-21&date2=2017-06-27">按时间段年月周查询迟到早退人数</a>
    <a href="attendance/findwithoutReasonPersonleNum?date1=2017-10-18&date2=2017-10-19">按时间段年月周查询无故外出人数数</a>
    <p>
    <a href="integrity/weekendUseCar?date1=2017-06-13&date2=2017-06-28">按时间段年月周非工作日用车次数</a>
    <a href="integrity/findVacationsUseCar?date1=2017-05-13&date2=2017-06-28">按时间段年月周节假日用车次数</a>
    <a href="integrity/driverByCarNo?date1=2017-05-13&date2=2017-06-28">按时间段年月周驾驶员与车不符次数</a>
    <a href="integrity/useCarByOvertime?date1=2017-05-13&date2=2017-06-28">按时间段年月周用车超时次数</a>
     <a href="integrity/carDeviateType">保存车辆偏离信息</a>
    <a href="integrity/addressInconsistent?date1=2017-09-01&date2=2017-09-21">按年月周时间段查询目的地偏离和公车私用次数</a>
    <a href="integrity/carReturn?date1=2017-07-13&date2=2017-09-28">按时间段年月周查询车回人不回</a>
    <a href="integrity/menAndCarNot?date1=2017-07-13&date2=2017-09-28">按时间段年月周查询人车不符</a>
    <p>
    <a href="meetingInfo/meetSignInfo?date1=2017-05-13&date2=2017-10-28">按时间段年月周查询未签到未参加次数</a>
    <a href="meetingInfo/notUploadMeetMinutes?date1=2017-05-13&date2=2017-09-28">按时间段年月周查询未上传未拍照会议记录次数</a>
    <a href="meetingInfo/meetingType?date1=2017-05-13&date2=2017-09-28">按时间段年月周查询会议类型</a>
    <a href="meetingInfo/meetingLongTime?date1=2017-05-13&date2=2017-09-28">按时间段年月周查询会议时长</a>
    <a href="meetingInfo/deptMeetingStatistics?date1=2017-05-13&date2=2017-09-28">按时间段年月周查询部门会议数</a>
    <p>
    <form action="report/addReportInformation" method="post" enctype="multipart/form-data">
    		举报人姓名         :<input type="text" name="informantName" value="张三"><br>
    		举报人身份证     :<input type="text" name="informantIdCard" value="513427199505064124"><br>
    		举报人电话         :<input type="text" name="informantphone" value="15881017805"><br>
    		举报人政治面貌 :<input type="text" name="informantPoliticalOutlook" value="团员"><br>
    		举报人住址         :<input type="text" name="informantAddress" value="上海"><br>
    		举报人级别         :<input type="text" name="informantLevel" value="管理人员"><br>
    		被举报姓名         :<input type="text" name="informantsName" value="梁龙江"><br>
    		被举报人司         :<input type="text" name="informantsCompany" value="国企"><br>
    		被举报职务         :<input type="text" name="informantsPosition" value="开发"><br>
    		被举报地址         :<input type="text" name="informantsAddress" value="贵阳"><br>
    		被举报级 别        :<input type="text" name="informantsLevel" value="管理"><br>
    		标       题              :<input type="text" name="title" value="考核投诉"><br>
    		问题类型             :<input type="text" name="problemCategories" value="无理取闹"><br>
    		问题细类             :<input type="text" name="problemClassification" value="审批意见"><br>
    		类        容             :<input type="text" name="content" value="胡乱填写"><br>
    		<input type="file" name="file"><br>
    		<input type="reset" name="" value="重置"> 
    		<input type="submit" name="" value="提交"><br>
    </form>
    <a href="report/findReport?phone=&page=1&pageSize=3">查询投诉信息</a>
    <a href="report/findAccountabilityInfo?date1=2017-10-01&date2=2017-10-27&page=1&pageSize=3">问责信息</a>
    <a href="report/findSuperviseInfo?date1=2017-10-01&date2=2017-10-27&page=1&pageSize=1">督责信息</a>
    <a href="report/superAndAccount?date1=2017-09-01&date2=2017-10-27">督责问责类型条数</a>
    <a href="report/accountabilityType?date1=2017-09-01&date2=2017-09-27">问责人数</a>
    <a href="report/superviseType?date1=2017-09-01&date2=2017-09-27">督责人数</a>
    <a href="report/findAccountabilityInfoById?id=1">id查询问责信息</a>
    <a href="report/findSuperviseInfoById?id=1">ID查询督责信息</a>
    <a href="report/complaintHandlingById?code=l07HvAr">案件编码查询投诉办理信息</a>
    <br>
    <a href="report/updateInfo?code=l07HvAr&examinationResult=属实&examinationName=胡正康&examinationDescribe=同意">更新投诉办理信息</a>
    <a href="report/superviseAndAccountabilityNum?date1=2017-09-01&date2=2017-09-27">年月查询督责问责条数</a>
    <a href="report/reportType?date1=2017-09-01&date2=2017-09-27">年月查询投诉类型条数</a>
    <a href="report/reportNum?date1=2017-09-01&date2=2017-09-27">年月查询投诉总条数</a>
    <a href="report/handlingNum?date1=2017-09-01&date2=2017-09-27">年月查询投诉办理条数</a>
    <a href="report/beComplainedPersonleNum?date1=2017-09-01&date2=2017-09-27">年月查询被投诉人数</a>
    <a href="report/isTrueNum?date1=2017-09-01&date2=2017-09-27">年月查询投诉属实件数</a>
    <a href="report/reportTypeInfo?date1=2017-09-01&date2=2017-10-10&type=违法违纪&page=1&pageSize=3">时间段按类型查询投诉信息</a>
    <br>
    <h2>审批</h2>
    <a href="approval/findMyApproval?userId=admin&page=1&pageSize=4">查询个人案件数</a>
    <a href="approval/findApprovalTotal?page=1&pageSize=10">查询所有审批</a>
    <a href="approval/findExamineTotal?page=1&pageSize=10">查询所有审核</a>
    <a href="approval/findMyApprovalNum?userId=admin">用户ID查询未办理件数</a>
    --> 
    <a href="approval/findMajorStatus?date1=2017-10-10&date2=2017-10-29">三重一大预警次数</a>
    <a href="integrity/addressInconsistent?date1=2017-09-01&date2=2017-09-21">按年月周时间段查询目的地偏离和公车私用次数</a>
    <a href="integrity/carDeviateType">保存车辆偏离信息</a>
    <a href="integrity/carReturnAndPerson">保存车回人不回信息</a>
    <a href="integrity/addMenAndCarNot">保存人车不符信息</a>
    </div>
</body>
</html>