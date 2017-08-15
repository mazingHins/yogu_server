package com.yogu.commons.server.mock;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.server.mock.service.MockManageService;
import com.yogu.commons.utils.StringUtils;

@Provider
// Priorities.USER
@Priority(Priorities.AUTHENTICATION - 100)
public class MockTogetherFilter implements ContainerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(MockTogetherFilter.class);

	private MockManageService mockManageService;

	private MockProvider[] mockProviders;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		MockManageService service = mockManageService();
		MockProvider[] mps;

		// 有适配器，且开启了 mock
		if (service.isAvailable() && null != (mps = mockProviders())) {
			UriInfo uriInfo = requestContext.getUriInfo();
			for (MockProvider provider : mps) {
				String distinguish = provider.distinguish(uriInfo);

				if (StringUtils.isNotBlank(distinguish)) {
					String result = service.result(distinguish);
					if (logger.isDebugEnabled())
						logger.debug("{} the MockProvider that the '{}', get the result is '{}'.", provider.getClass(), distinguish, result);

					if (StringUtils.isNotBlank(result)) {
						Response ok = Response.status(Response.Status.OK).entity(result).build();
						requestContext.abortWith(ok);
						break;
					}
				}
			}
		}
	}

	private MockManageService mockManageService() {
		if (null == mockManageService)
			mockManageService = MainframeBeanFactory.getBean(MockManageService.class);
		return mockManageService;
	}

	private MockProvider[] mockProviders() {
		if (null == mockProviders) {
			Map<String, MockProvider> beansOfType = MainframeBeanFactory.getBeansOfType(MockProvider.class);
			if (null != beansOfType)
				mockProviders = beansOfType.values().toArray(new MockProvider[beansOfType.size()]);
		}
		return mockProviders;
	}

}
