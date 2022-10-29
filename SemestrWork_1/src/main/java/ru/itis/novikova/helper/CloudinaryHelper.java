package ru.itis.novikova.helper;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


public class CloudinaryHelper {
	private static Cloudinary cloudinary;

	public static Cloudinary getInstance() {
		if (cloudinary == null) {
			cloudinary = new Cloudinary(ObjectUtils.asMap(
					"cloud_name", "dsels4xlc",
					"api_key", "252525461342572",
					"api_secret", "QlTKDrBrsnWGmbpYtoah565o1D0"));
		}

		return cloudinary;
	}
}