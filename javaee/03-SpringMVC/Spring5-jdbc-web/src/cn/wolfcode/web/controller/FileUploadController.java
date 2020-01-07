package cn.wolfcode.web.controller;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.wolfcode.domain.Employee;

@Controller
public class FileUploadController {

	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/save")
	public ModelAndView save(Employee e, MultipartFile pic) throws Exception {
		System.out.println(e);
		String fileName = pic.getOriginalFilename();
		System.out.println(fileName);
		String saveDir = servletContext.getRealPath("/upload");
		Files.copy(pic.getInputStream(), Paths.get(saveDir, fileName));
		return null;
	}
}
