package cn.wolfcode.web.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileDownLoadController {

	@RequestMapping("/download1")
	public void download1(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dir = request.getServletContext().getRealPath("/WEB-INF/down");
		//设置响应头:下载文件
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		//设置建议保存名称
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		Files.copy(Paths.get(dir, fileName), response.getOutputStream());
	}

	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/download2")
	public ResponseEntity<byte[]> download2(String fileName) throws Exception {
		String dir = servletContext.getRealPath("/WEB-INF/down");
		HttpHeaders headers = new HttpHeaders();
		//设置响应头:下载文件
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//设置建议保存名称
		headers.setContentDispositionFormData("attachment", fileName);
		return new ResponseEntity<>(FileUtils.readFileToByteArray(new File(dir, fileName)), headers,
				HttpStatus.CREATED);
	}
}
