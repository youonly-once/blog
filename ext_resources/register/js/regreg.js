function encodepwd(original) {
	return ""+CryptoJS.AES.encrypt(original,'this is temp before https');
}


