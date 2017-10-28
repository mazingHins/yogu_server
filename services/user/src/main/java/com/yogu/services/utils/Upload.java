package com.yogu.services.utils;

import com.yogu.remote.config.fs.api.FileCategory;
import com.yogu.remote.config.fs.api.FileStoreFactory;

public class Upload {
	
	public static void main(String[] args) {
		 FileStoreFactory.createFileStore().storeImage("ces", FileCategory.PROFILE, "img".getBytes());
	}

}
