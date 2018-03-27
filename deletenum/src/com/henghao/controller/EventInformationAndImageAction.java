package com.henghao.controller;


import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.henghao.entity.EventImage;
import com.henghao.entity.EventInformation;
import com.henghao.entity.Result;
import com.henghao.service.EventInformationAndImageService;
import com.henghao.util.DateUtils;
import com.henghao.util.Md5Test;

@Controller
@RequestMapping("/event")
public class EventInformationAndImageAction {
	@Autowired
	private EventInformationAndImageService eventInformationAndImageService;

	/**
	 * 保存事件及图片信息    “接口已注销现在用UserEventTrackController类添加和查询”
	 * @param userId
	 * @param eventName
	 * @param eventAddress
	 * @param eventTime
	 * @param eventDate
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addEventInformationAndImage")
	@ResponseBody
	public Result addEventInformationAndImage(String userId,String eventName,String eventAddress,String eventTime,String eventDate,@RequestParam(value = "file", required = false) List<MultipartFile> file,
			HttpServletRequest request, ModelMap model)  {
		try {
			// 生成随机的18位编码
			String words = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String token = "";
			for (int i = 0; i < 18; i++) {
				int rand = (int) (Math.random() * words.length());
				token += words.charAt(rand);
			}
			String tokens = Md5Test.toMD5(token);
			Result result = new Result();
			EventInformation eventInformation = new EventInformation();
			eventInformation.setUSERID(userId);
			eventInformation.setSHIJIAN_EVENT(eventName);
			eventInformation.setSHIJIAN_PLACE(eventAddress);
			eventInformation.setSHIJIAN_TIME(eventTime);
			eventInformation.setTIME(eventDate);
			eventInformation.setTOKEN(tokens);
			eventInformationAndImageService.addEventInformation(eventInformation);
			EventImage eventImage = new EventImage();
			int size = file.size();
			int i=0;
			if (size != 0) {
				for ( i = 0; i < file.size(); i++) {
					//MultipartFile fileNAme = file.get(i);
					// System.out.println("开始上传文件");
					// 获取tomcat保存文件地址
					String path = request.getSession().getServletContext().getRealPath("/")+ "tousu";
					if(!new File(path).exists())   {
					    new File(path).mkdirs();
					  }
					System.out.println(path);
					// 获取文件名称
					String  fileName = file.get(i).getOriginalFilename();
					//System.out.println(fileName);
					// 获取文件类型
					String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
					// 文件重命名
					String file_ture_name = DateUtils.getCurrentDate("yyyyMMddHHmmssms"+i+".") + extensionName;
					//得到long类型当前时间
//				long l = System.currentTimeMillis();
//				//new日期对象
//				Date date = new Date(l);
//				// 获取当前时间
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String time = df.format(date);// 当前时间
					//System.out.println(fileName);
					// 路径分隔符
					String separator = File.separator;
					// 文件路径名
					String pathFileName = path + separator + fileName;
					//System.out.println(pathFileName);
					//FileInputStream in = new FileInputStream(fileNAme);
					File targetFile = new File(path, file_ture_name);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 保存
					try {
						//保存图片到物理磁盘
						file.get(i).transferTo(targetFile);
//					System.out.println(file.get(i));
//					System.out.println(fileName);
						//保存数据库
						eventImage.setSAVE_NAME(file_ture_name);
						eventImage.setFILENAME(pathFileName);
						eventImage.setFILETYPE(extensionName);
						eventImage.setSAVE_TIME(eventDate);
						eventImage.setTOKEN(tokens);
						eventInformationAndImageService.addEventImage(eventImage);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);
					model.addAttribute("downFileUrl", path + "/" + fileName);
				}

			}

			String msg = "保存成功";
			if (eventInformationAndImageService.equals(null)) {
				msg = "保存失败";
			}
			result.setMsg(msg);
			result.setData("token" + ": " + eventImage.getTOKEN());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(0, "查询失败", null);
	}
	
	
	
}
