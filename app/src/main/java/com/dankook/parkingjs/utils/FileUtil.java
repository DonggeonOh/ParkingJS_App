package com.dankook.parkingjs.utils;


import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dankook.parkingjs.App;

import java.io.File;
import java.io.IOException;

public class FileUtil {

	private static final String rootDirName = "/parkingJS";

	@NonNull
	public static File createNewFile() {
		String root = Environment.getExternalStorageDirectory().toString();
		File fileDir = new File(root + rootDirName);

		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		String fileName = DateUtil.getCurrentDateTime() + ".jpg";
		return new File(fileDir, fileName);
	}

	@Nullable
	public static File createCacheFile() {
		try {
			return File.createTempFile("JPEG_", ".jpg", App.instance().getCacheDir());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
