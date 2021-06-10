package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.FileuploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.mysite.vo.UserVo;

@RequestMapping("/gallery")
@Controller
public class GalleryController {

	@Autowired
	private FileuploadService fileuploadService;
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping
	public String index(@AuthUser UserVo authUser, Model model) {
		model.addAttribute("user", authUser);
		model.addAttribute("galleries", galleryService.getImages());
		
		return "gallery/index";
	}
	
	@Auth(role = "ADMIN")
	@PostMapping("/upload")
	public String uploadImage(GalleryVo galleryVo, MultipartFile file) {
		galleryVo.setUrl(fileuploadService.restore(file));
		galleryService.uploadImage(galleryVo);
		return "redirect:/gallery";
	}
	
	@Auth(role = "ADMIN")
	@GetMapping("/delete/{id}")
	public String deleteImage(@PathVariable Long id) {
		galleryService.deleteImage(id);
		return "redirect:/gallery";
	}
}
