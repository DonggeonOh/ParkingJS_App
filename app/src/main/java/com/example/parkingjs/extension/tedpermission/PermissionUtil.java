package com.example.parkingjs.extension.tedpermission;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.gun0912.tedpermission.TedPermissionResult;
import com.tedpark.tedpermission.rx2.TedRx2Permission;

import io.reactivex.Single;

public class PermissionUtil {

	public static Single<TedPermissionResult> requestPermission(@NonNull final Context context,
	                                                            @Nullable final String... permissions) {
		return TedRx2Permission.with(context)
				.setPermissions(permissions)
				.request();
	}

	public static void requestPermission(@NonNull final Context context,
	                                     @NonNull final PermissionListener listener,
	                                     @Nullable final String... permissions) {
		TedPermission.with(context)
				.setPermissionListener(listener)
				.setDeniedMessage("만약 권한을 거부 하였다면, 서비스를 이용하실 수 없습니다." +
						"[설정] > [권한]을 들어가 권한을 허용해주세요.")
				.setPermissions(permissions)
				.check();
	}
}