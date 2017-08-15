package com.mazing.services.store.resourceapi;

import com.yogu.commons.utils.HttpClientUtils;

public class StoreServiceRangeMatchTest {

	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			HttpClientUtils.doGet("http://storeapi.mazing.com/api/store/serviceRange/test/randomMatch");
		}
	}

}
