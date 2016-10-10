package com.dingzhi.miaohui.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dingzhi.miaohui.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;

/**
*Description:头像上传 <br>
*@auther TX <br>
*created at 2016/9/14 18:03
*/
public class SelectPicActivity extends Activity implements OnClickListener {
	protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
	protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

	private AlertDialog mAlertDialog;
	/***
	 * 使用照相机拍照获取图片
	 */
	public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
	/***
	 * 使用相册中的图片
	 */
	public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
	/***
	 * 裁剪图片
	 */
	public static final int ZOOM_OK = 3;
	/***
	 * 裁剪图片
	 */
	public static final int CUT = 4;

	protected int activityCloseEnterAnimation;

	protected int activityCloseExitAnimation;

	/***
	 * 从Intent获取图片路径的KEY
	 */
	public static final String KEY_PHOTO_PATH = "photo_path";

	private LinearLayout dialogLayout;
	private Button takePhotoBtn, pickPhotoBtn, cancelBtn;

	/** 获取到的图片路径 */
	private String picPath;

	private Intent lastIntent;

	private Uri photoUri;

	float aspectX;

	float aspectY;

	private int cropWith = 753;

	private int cropHeight = 420;

	String fileName;
	int data = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_pic_layout);
		initView();

		data = getIntent().getIntExtra("latitude", -1);
		if (data != -1) {
			aspectX = 4;
			aspectY = 3;
		} else {
			cropWith = 420;
			cropHeight = 420;
			aspectX = 1;
			aspectY = 1;
		}

	}

	/**
	 * 初始化加载View
	 */
	private void initView() {
		dialogLayout = (LinearLayout) findViewById(R.id.dialog_layout);
		dialogLayout.setOnClickListener(this);
		takePhotoBtn = (Button) findViewById(R.id.btn_take_photo);
		takePhotoBtn.setOnClickListener(this);
		pickPhotoBtn = (Button) findViewById(R.id.btn_pick_photo);
		pickPhotoBtn.setOnClickListener(this);
		cancelBtn = (Button) findViewById(R.id.btn_cancel);
		cancelBtn.setOnClickListener(this);

		lastIntent = getIntent();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_layout:
			finish();
			break;
		case R.id.btn_take_photo:
			takePhoto();
			break;
		case R.id.btn_pick_photo:
			// pickPhoto();
			pickFromGallery();
			// Crop.pickImage(this);
			break;
		default:
			finish();
			break;
		}
	}

	/**
	 * 拍照权限
	 */
	public void takePhoto() {
		if (Build.VERSION.SDK_INT >= 23) {
			if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
					|| ContextCompat.checkSelfPermission(this,
							Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				// 申请CAMERA权限
				ActivityCompat.requestPermissions(this,
						new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
			} else {
				takePhoto_content();
			}
		} else {
			takePhoto_content();
		}
	}

	/**
	 * 拍照获取图片
	 */
	private void takePhoto_content() {
		// 执行拍照前，应该先判断SD卡是否存在
		String SDState = Environment.getExternalStorageState();
		if (SDState.equals(Environment.MEDIA_MOUNTED)) {

			String savePath = "";
			savePath = getCacheDir().getAbsolutePath();
			// 没有挂载SD卡，无法保存文件
			if (TextUtils.isEmpty(savePath)) {
				Toast.makeText(this, "内存卡不存在", Toast.LENGTH_LONG).show();
				return;
			}

			String fileName = System.currentTimeMillis() + ".jpg";// 照片命名
			// File out = new File(savePath, fileName);
			// photoUri = Uri.fromFile(out);

			ContentValues values = new ContentValues();
			values.put(Media.TITLE, fileName);

			photoUri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);

			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
			// intent.putExtra(MediaStore.Images.Media.DISPLAY_NAME, fileName);
			startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
		} else {
			Toast.makeText(this, "内存卡不存在", Toast.LENGTH_LONG).show();
		}
	}

	/***
	 * 从相册中取图片
	 */
	public void startPhotoZoom(Uri uri, Uri imageUri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		intent.putExtra("outputX", cropWith);
		intent.putExtra("outputY", cropHeight);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("return-data", true);
		intent.putExtra("noFaceDetection", true);
		startActivityForResult(intent, ZOOM_OK);
	}

	/***
	 * 剪切从相册中取图片
	 */
	public void startPhotoZoom(Uri uri) {
		fileName = getCacheDir().getAbsolutePath() + System.currentTimeMillis() + ".jpg";
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", true);
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		intent.putExtra("outputX", cropWith);
		intent.putExtra("outputY", cropHeight);
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(fileName)));
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 返回格式
		intent.putExtra("noFaceDetection", true);
		startActivityForResult(intent, ZOOM_OK);
	}

	/***
	 * 从相册中取图片
	 */
	public void pickPhoto() {
		Intent intent = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
		intent.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
		startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
	}

	/**
	 * Requests given permission. If the permission has been denied previously,
	 * a Dialog will prompt the user to grant the permission, otherwise it is
	 * requested directly.
	 */
	protected void requestPermission(final String permission, String rationale, final int requestCode) {
		if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
			showAlertDialog(getString(R.string.permission_title_rationale), rationale,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							ActivityCompat.requestPermissions(SelectPicActivity.this, new String[] { permission },
									requestCode);
						}
					}, getString(R.string.label_ok), null, getString(R.string.label_cancel));
		} else {
			ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
		}
	}

	/**
	 * This method shows dialog with given title & message. Also there is an
	 * option to pass onClickListener for positive & negative button.
	 *
	 * @param title
	 *            - dialog title
	 * @param message
	 *            - dialog message
	 * @param onPositiveButtonClickListener
	 *            - listener for positive button
	 * @param positiveText
	 *            - positive button text
	 * @param onNegativeButtonClickListener
	 *            - listener for negative button
	 * @param negativeText
	 *            - negative button text
	 */
	protected void showAlertDialog(@Nullable String title, @Nullable String message,
								   @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener, @NonNull String positiveText,
								   @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener, @NonNull String negativeText) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
		builder.setNegativeButton(negativeText, onNegativeButtonClickListener);
		mAlertDialog = builder.show();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return super.onTouchEvent(event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case SELECT_PIC_BY_TACK_PHOTO:
			if (data == null && photoUri == null) {
				return;
			}
			if (data != null) {
				// startPhotoZoom(data.getData());
				startCropActivity(data.getData());
			} else {
				// startPhotoZoom(photoUri);
				startCropActivity(photoUri);
			}
			break;
		case SELECT_PIC_BY_PICK_PHOTO:
			if (data != null) {
				Uri selectedUri = data.getData();
				if (selectedUri != null) {
					startCropActivity(data.getData());
				} else {
					Toast.makeText(SelectPicActivity.this, R.string.toast_cannot_retrieve_selected_image,
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		// case Crop.REQUEST_CROP:
		case UCrop.REQUEST_CROP:
			if (resultCode != 0) {
				handleCropResult(data);
			}
			break;
		case UCrop.RESULT_ERROR:
			handleCropError(data);
			break;
		default:
			break;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private void handleCropResult(@NonNull Intent result) {
		final Uri resultUri = UCrop.getOutput(result);
		if (resultUri != null) {
			picPath = resultUri.getPath();
			if (picPath != null) {
				lastIntent.putExtra(KEY_PHOTO_PATH, picPath);
				setResult(Activity.RESULT_OK, lastIntent);
				finish();
			} else {
				Toast.makeText(this, "选择图片文件不正确", Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(SelectPicActivity.this, R.string.toast_cannot_retrieve_cropped_image, Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void handleCropError(@NonNull Intent result) {
		final Throwable cropError = UCrop.getError(result);
		if (cropError != null) {
			Log.e(this.getClass().getName(), "handleCropError: ", cropError);
			Toast.makeText(SelectPicActivity.this, cropError.getMessage(), Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(SelectPicActivity.this, R.string.toast_unexpected_error, Toast.LENGTH_SHORT).show();
		}
	}

	private void startCropActivity(@NonNull Uri uri) {
		photoUri = Uri.fromFile(new File(getCacheDir(), (System.currentTimeMillis() + ".jpeg")));
		UCrop uCrop = UCrop.of(uri, photoUri);

		uCrop = basisConfig(uCrop);
		uCrop = advancedConfig(uCrop);

		uCrop.start(SelectPicActivity.this, UCrop.REQUEST_CROP);
	}

	@SuppressLint("InlinedApi")
	private void pickFromGallery() {
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN //
		// Permission
		// && ActivityCompat.checkSelfPermission(this,
		// Manifest.permission.READ_EXTERNAL_STORAGE) !=
		// PackageManager.PERMISSION_GRANTED)
		// {
		// requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
		// getString(R.string.permission_read_storage_rationale),
		// REQUEST_STORAGE_READ_ACCESS_PERMISSION);
		// }
		// else
		// {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(Intent.createChooser(intent, getString(R.string.label_select_picture)),
				SELECT_PIC_BY_PICK_PHOTO);
		// }
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.dismiss();
		}
	}

	/**
	 * Callback received when a permissions request has been completed.
	 */
	@SuppressLint("NewApi")
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
			@NonNull int[] grantResults) {
		switch (requestCode) {
		case 1:
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				takePhoto_content();
			}
			break;
		case REQUEST_STORAGE_READ_ACCESS_PERMISSION:
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				pickFromGallery();
			}
			break;
		default:
			super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}

	private UCrop basisConfig(@NonNull UCrop uCrop) {
		// if (data != 1)
		// {
		uCrop = uCrop.withMaxResultSize(cropWith, cropHeight);
		uCrop.withAspectRatio(aspectX, aspectY);
		// }
		// else
		// {
		// uCrop.withAspectRatio(1, 1);
		// // uCrop = uCrop.useSourceImageAspectRatio();
		// }
		return uCrop;
	}

	private UCrop advancedConfig(@NonNull UCrop uCrop) {
		UCrop.Options options = new UCrop.Options();
		options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
		options.setCompressionQuality(80);
		options.setMaxBitmapSize(1300);
		options.setHideBottomControls(false);
		// if (data == -1)
		// {
		// // 设置裁剪窗口是否为椭圆
		// options.setOvalDimmedLayer(true);
		// // 设置是否展示矩形裁剪框
		// options.setShowCropFrame(false);
		//
		// // // 设置竖线的数量
		// options.setCropGridColumnCount(0);
		// // // 设置横线的数量
		// options.setCropGridRowCount(0);
		// }
		// else
		// {
		// 设置裁剪窗口是否为椭圆
		//options.setOvalDimmedLayer(false);
		// 设置是否展示矩形裁剪框
		options.setShowCropFrame(true);
		// 设置裁剪框横竖线的颜色
		options.setCropGridColor(getResources().getColor(android.R.color.white));
		options.setToolbarColor(ActivityCompat.getColor(this, R.color.toolbarcolor));
		//设置状态栏颜色
		options.setStatusBarColor(ActivityCompat.getColor(this, R.color.toolbarcolor));

		// }

		return uCrop.withOptions(options);
	}

	/**
	 * Get a file path from a Uri. This will get the the path for Storage Access
	 * Framework Documents, as well as the _data field for the MediaStore and
	 * other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @author paulburke
	 */
	@SuppressLint("NewApi")
	public String getPath(final Context context, final Uri uri) {

		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				}
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {
			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders.
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int column_index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(column_index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

}
