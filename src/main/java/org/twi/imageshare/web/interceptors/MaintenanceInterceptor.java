package org.twi.imageshare.web.interceptors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.twi.imageshare.services.ImageService;

public class MaintenanceInterceptor extends HandlerInterceptorAdapter {

	private static final String RESOURCES_URL = "/imageshare/resources";

	@Resource(name = ImageService.IMAGE_SERVICE_BEAN)
	private ImageService imageService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
        // Ignore static resources
		if (!uri.contains(RESOURCES_URL)) {
			imageService.removeOldImagesIfNecessary();
		}
		return true;
	}

}
