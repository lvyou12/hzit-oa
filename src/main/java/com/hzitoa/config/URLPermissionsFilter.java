package com.hzitoa.config;

/*@Component("urlPermissionsFilter")*/
public class URLPermissionsFilter {} /*extends PermissionsAuthorizationFilter{

	@Autowired
	private IEmployeeInfoService iEmployeeInfoService;

	*/ /**
	 * 过滤资源
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
     * @throws IOException
     *//*
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		String curUrl = getRequestUrl(request);
		Subject subject = SecurityUtils.getSubject();
		if(subject.getPrincipal() == null
				|| StringUtils.endsWithAny(curUrl, ".js",".css",".html")
				|| StringUtils.endsWithAny(curUrl, ".jpg",".png",".gif", ".jpeg",".ico")
				|| StringUtils.endsWithAny(curUrl,".otf?v=4.6.3",".eot?v=4.6.3",".ttf?v=4.6.3",".woff?v=4.6.3",".woff2?v=4.6.3")
				|| StringUtils.endsWithAny(curUrl,".woff",".ttf")
				|| StringUtils.contains(curUrl,"/druid/")
				|| StringUtils.contains(curUrl,"/druid2/")
				|| StringUtils.equals(curUrl,"/account/checkValidate")) {
			if(curUrl.contains("woff")|| curUrl.contains("tff")){
				return true;
			}
			if(curUrl.contains("druid")){
				return true;
			}
			return true;
		}
		//每次访问时做的权限验证
		//获取用户所能访问的资源
	*//*	List<String> urls = null;//;employeeInfoService.findPermissionUrl(subject.getPrincipal().toString());
		if(curUrl.contains("?")){
			curUrl = curUrl.substring(0,curUrl.lastIndexOf("?"));
		}
		boolean ok = urls.contains(curUrl);*//*
		return true;
	}
	
	*//**
	 * 获取当前URL+Parameter
	 * @param request	拦截请求request
	 * @return			返回完整URL
	 *//*
	private String getRequestUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		String queryString = req.getQueryString();
		queryString = StringUtils.isBlank(queryString)?"": "?"+queryString;
		return req.getRequestURI()+queryString;
	}
}
*/