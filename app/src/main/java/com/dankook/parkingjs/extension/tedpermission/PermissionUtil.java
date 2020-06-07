package com.dankook.parkingjs.extension.tedpermission;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class PermissionUtil {

	public static void requestPermission(@NonNull final Context context,
	                                     @Nullable final String... permissions) {
		TedPermission.with(context)
				.setPermissionListener(getPermissionListener(context))
				.setDeniedMessage("만약 권한을 거부 하였다면, 서비스를 이용하실 수 없습니다." +
						"[설정] > [권한]을 들어가 권한을 허용해주세요.")
				.setPermissions(permissions)
				.check();
	}

	private static PermissionListener getPermissionListener(Context context) {
		return new PermissionListener() {
			@Override
			public void onPermissionGranted() {
			}

			@Override
			public void onPermissionDenied(List<String> deniedPermissions) {
				Toast.makeText(context, "다음 권한이 거부되어 실행할 수 없습니다.\n" +
						deniedPermissions.toString(), Toast.LENGTH_LONG).show();
			}
		};
	}
}