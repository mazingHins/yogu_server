package com.yogu.commons.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.cfg.DesPropertiesEncoder;
import com.yogu.commons.utils.encrypt.DES3;
import com.yogu.commons.utils.encrypt.MD5Util;

/**
 * log 工具类
 * 
 * @author ten 2015/9/16.
 */
public class LogUtil {

	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

	/**
	 * 处理显示的 值,具有保密性的值需要特殊处理，不能直接log到日志文件中
	 * 
	 * @param key 参数名
	 * @param value 参数值
	 * @return 处理后的参数值
	 */
	public static String dealShowValue(String key, String value) {
		String lowerName = key.toLowerCase();

		if (lowerName.indexOf("password") >= 0) {
			// 完全隐藏密码 ten 2015/11/20
			value = "***";
		} else if (lowerName.indexOf("username") >= 0 || lowerName.indexOf("accesskey") >= 0 || lowerName.indexOf("accesssecret") >= 0
				|| lowerName.indexOf("certsecret") >= 0 || lowerName.indexOf("alipay") >= 0 || lowerName.indexOf("wechat") >= 0
				|| lowerName.indexOf("rediscluster") >= 0 || lowerName.indexOf("singleredis") >= 0 || lowerName.indexOf("phone") >= 0
				|| lowerName.indexOf("passport") >= 0 || lowerName.indexOf("mobile") >= 0 || lowerName.indexOf("accountno") >= 0
				|| lowerName.indexOf("accountname") >= 0) {
			// 隐藏部分 值
			value = hidePassport(value);
		}
		return value;
	}

	/**
	 * 隐藏 通行证 的输出，避免通行证直接显示在日志里
	 * 
	 * @param passport 通行证
	 * @return 返回隐藏了部分字符的带 *** 的字符串
	 */
	public static String hidePassport(String passport) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(passport)) {
			return passport;
		}
		if (passport.length() <= 1) {
			return passport + "***";
		}
		if (passport.length() <= 4) {
			// 和 length=1 时相同，避免直接猜测出来
			return passport.substring(0, 1) + "***";
		}
		if (passport.length() <= 6)
			return passport.substring(0, 2) + "***" + passport.substring(passport.length() - 1);

		return passport.substring(0, 3) + "***" + passport.substring(passport.length() - 3);
	}

	/**
	 * 加密字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		try {
			return DES3.encrypt(str, "!#52c3c373a0d44e2c_*ad0fd2b37be8!cfc8,.G");
		} catch (Exception e) {
			logger.info("加密出错", e);
			return hidePassport(str);
		}
	}

	public static String decrypt(String str) {
		try {
			return DES3.decrypt(str, "!#52c3c373a0d44e2c_*ad0fd2b37be8!cfc8,.G");
		} catch (Exception e) {
			logger.info("解密出错", e);
			return hidePassport(str);
		}
	}

	/**
	 * 隐藏 appKey
	 * 
	 * @param akey appKey
	 * @return 返回非明文的appKey
	 */
	public static String hideAppKey(String akey) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(akey)) {
			return akey;
		}
		return MD5Util.getMD5String(akey);
	}

	public static void main(String[] args) {
		System.out.println(hidePassport("13500001111"));
		System.out.println(hidePassport("461118534@mazing.com"));
		System.out.println(hidePassport("19002222"));
		System.out.println(hidePassport("1900"));
		System.out.println("------------");
		System.out.println(encrypt("76528"));
		System.out.println(decrypt("XVkvZRRXah8="));

		/**
		 * 领取优惠券的手机号提取
		 */
//		List<String> list = new ArrayList<>();
//		String phones = "ormJrMm9BEnsmmaqZiRv9A==,xEwLg0K4erVOiC3kR1VVXw==,153urhF2pZgKlZnuaK9Q0g==,fpbhH5CKbdMz57c4v76Yyw==,2bVjm4oA4Yb2LIys9pPmVw==,HxGFLmluabRHVcUWT5CCXQ==,fpbhH5CKbdMWHVVVmZRF9g==,/r9EaYH8WQoOf8uorKs2qg==,APrejELb9joU37x+PD/xJQ==,KN8vp+eirB3PLgcDNfSMzA==,+U0rG8tjULLb7t1jhqM08A==,YQxNyOg2ApggkvB5UyEoVQ==,Si9HhEwlXWan3XWCsXzAuQ==,6b7woagQ2u8MAwbS8B2LuQ==,PN2pGT6crFYidZg1rsJrlQ==,8qA0/C1gb5FBzwcTBtq8/A==,7YpxzxQ83AyA7mGqIU1b2w==,ZGUgOQKmSlDh6lJBW+X2EA==,Ptce2G5+O2wDjFVINJDT6g==,QWy+3NBBRs4WHVVVmZRF9g==,iNMfGJ7d/w5zo3Ky8IqBdA==,GzMkaegQxq0WxAspqyKy6g==,Nkyb5+py4YYiwlMqW9PvCw==,z68KNkFXIX8cuP+WWkJErg==,sc5phKr99aIU0LVDGjudHQ==,ax7B9/50CNZbKCuFcDAxPA==,cJk7Ig2kJXMcOYP/4EauEw==,y5XN3viHdGPbLJgV+RpTSg==,Vy5zr8vs/euScTJDzjFBCg==,ixaOvfQwrz2cPNVvaYgRyQ==,82g1g0Vup2Na+F3s4UzibQ==,Mstiy3n5l/WYnf/AelK3NA==,YAAN6Hb2pQxM7knhWmd3Bw==,o2G2RMgefVE6JoihAw8VXA==,A8hXZ5ynAdV2K6F+gZXZCg==,tBRe8LP8LpmOgrKCeYmSsg==,P5YNPJdnlp86pjazO/SEkw==,xtxTqHuVZE6zTDQ/T+wz+Q==,FI+fdvAqQhjB80dC2HAZmw==,W27ZmNTOKGzQZew+k/XP8w==,L1EITbvEAVn42ougof/yAQ==,MQfyoBFsLgHQFll+EljhGg==,dIG+FjRsfMZ+lvmkFYBpNA==,7p+CipCtsBj3jtUJKtoBig==,jifkbT29tHzB80dC2HAZmw==,JyVDCNVRlBIO2sRjINBe6g==,7vJ+h/OK8iK2V0KVoZIGXA==,8uewafg2078n3kSGbEXzlQ==,jJztk5IKCI4D9mCinQMPfA==,YXR+/quPfNQ4C4vCcXMVLw==,UyIK0IzBNg5WNHlsgzwj1w==,tx5JC962nI2OkMeP5JaP0Q==,W27ZmNTOKGypVhUnq+mG8w==,Z1ttP3uBrnEvjUwTVK+4AQ==,bYYKKPG1DZhqhKcsaIiTwQ==,zngxXamtsC6V/zIKjWSFyQ==,Ixlm8Hq6zsrAQpfacqntiA==,8OY9Ke19fvE7J6N/lwwVuw==,EPdJBZvlCdiI9A3sgbzs6g==,RTMqYDltXvOXLVRdNS+L3g==,6IKcas8lx/JlfG7X3Q3szQ==,p88fPlLpNaMXn6DHrP3wWg==,lfCI0ufk2k/o7sNixeGoJA==,8glPX9V8j5qHIt6FCgYGmw==,3/QVOvDqqpw3wdCXRawPAg==,kDXxPIpr/Q3hIokPutVoIA==,rict/V+7wMgdryisegF4Mg==,ihzNpD8cW0d9VGk/fam1jg==,OzMYE1np+x3xxf+F2xOgKg==,FD0HDFcKg8kMYYqKYyC+qA==,xEauL2Ci2XCh0dSbD/WOpA==,NTO8sNT8dzx1QRmtmzXvkQ==,vuNuPQuQm9MF4UEXbTar6g==,E0WCjonLUQDRLy8gp5bRog==,cvZz8Xi4MakWV2vRxCStyQ==,5JdMwSBSFTX3jJiABkthjg==,6VfOIglNpRD2YOtB8KKNqQ==,kXNLtsWe6KU0XxjUpR+oqg==,e8ULHgE5hOrmpDQoFpf5Tg==,QJjJ3bV0kJqndjHZP2v7Ew==,ZTFP+dYhXhqsMQoIFY6hcQ==,dYLXF3+Zn6eV++R2toDwQg==,hGnzSEQIUiRpZEUzg7owxQ==,lRKYV8hYwKcCu3ZbZHMbsw==,pV0rkIvrdGfde1SErJ+PIQ==,BI/Uq4akAPAF4UEXbTar6g==,c9MG5HYKVlEa5ZnloOfVKw==,0tDDC75zAurogpJHY0FULg==,F3KHmXA2wHQViHbc/F8TqA==,+GPuxtHh/j/d6EoPvubmdw==,nVGY7zdleXRXowdy+XbsLw==,NIbOcKBOQeDo7sNixeGoJA==,1ECgiDGp2mqdqQHzW2q8HA==,UJZ7M72cO1u9ZnTS+PkKzQ==,u1QEfIAOY/Y+NcBPkH2mCg==,NceKjoiISnXi8zwM1TKxWg==,2xqESKE8yUVAZ5Grn9LNFQ==,qoJpdI1NHnMN41YCq2jgvQ==,BczLz1dbIGxpGIw1La87Lw==,Vexexfc+vMfdYB3kLBlrQQ==,2VHmdH6keC2BBV9BvIltWw==,P9onn52TI0g0XxjUpR+oqg==,D1NmioF/5TKniyq/1YF1mg==,YNyS/snopjmXe2Rx3vlKuw==,2uoncKVhEJK1pchu09dVSw==,/lhgPsYJvckm4BPqnZSJ4w==,+aXEaa/e0Yp527luBn3SBQ==,+PvoTfYWwXt527luBn3SBQ==,qMmEadaPqHqyNgU1tQ35vA==,2cmRsQC022/f3FEmGfWVQw==,DbVuxfLiMlo4C4vCcXMVLw==,aRFe2GTdrqS2OQq4CpFkRA==,pPFJ2D6L6oNKhD0lVaZh4g==,U4vhm9SEKXHiYNtAwIG4dg==,Bl5NfwNuq814MC81qHDWfA==,F0RUzUqcRKqsTQQoyJat8g==,rsL6uWFgZ1uYRlOYi5T3YQ==,Ge55dJGWVGsFuOegKu9dng==,7sJRd/QDMAXFs/6DZwXP5w==,XZTxivro6D7t0U35GuFQBg==,VolSL6EMmv5262jbGniP9Q==,GXrcnRpGJ6f0MBPoiQ801w==,xv/xDKRePdE0XxjUpR+oqg==,xVRGrngzpemIUugsKBrOlA==,Qi6CSpURQNqIUugsKBrOlA==,UozhpbrdvCBfB7t4MMDtKA==,4lFgiJShEq8P3qQ/DqGalQ==,WIvGVL+0Yc3OKoWYt+m/Rg==,BJmmKhxAknMt8Jy4xl7T/Q==,6FfaxHkKuu68hA2ctmv3fw==,X4vPVOQCJnpQZ0F2MYLP/A==,aqRRT25MMrEa5ZnloOfVKw==,XVPslsr4g1zGclv6221BSw==,e/r1Ch6ucHaWgK+WY66giQ==,8x91/w4fyiFZduekwkJxQg==,eoc5Kzxb8h/RTV8QJ4TYuw==,M3Jkw7Th8NOuha4rqY23rA==,FVtMPaWMDQjLBXj8KVtFbg==,rgmEHO9e1pcl+cFLcEWSjg==,dEC2nC7sAWI4wpGQ3kADRA==,WQVbBITgcEfZpvYRYej6sw==,6GhOY2wCszmQ58Tc1drivQ==,hint0nDzSwseyjj5WnSMgQ==,koWsoKKyHDogVaX3hCyTIA==,InoOUQs2zZNBE2z6vSUKjQ==,rHUnEz+Z10G9ZnTS+PkKzQ==,dylNpmjXsQwQH6IiVJX+3w==,KLpLsvmzteqZmuHnhKTkfA==,gnC3QVSaGZpAZ5Grn9LNFQ==,8zygETqwPQH2wJSgF1Sn/Q==,KXdWLLp8+tCEXIXK8mvjIQ==,vK/8h8mRGVhqMYvKTGeEEA==,GYjHsheTboRe3rp3jJGUmQ==,qfZ+ZTjMPrRkxSfglxQgcA==,Mt+lEX7WsTslOTeLwnLraQ==,chpaKziaUskVAfM25UpYOw==,Ki+Q5LG+4pPCLyF50OIzfQ==,W7mWNyOhG9/rOZY8T1848Q==,AD0pPPvBVnAdXC0V7KaZWg==,bZl59M+sdd+EXIXK8mvjIQ==,5qsO9Nt/waGdUN1jS1vdLw==,Jvdoj/Uo6X+bSmDGOmi0nw==,ln/PanoD6FMEpnD+t84b/g==,8WZ48aSH74rAjp1xqzOcvg==,GVHxgM8FdPT8U99eJp2vlQ==,O9IZ7NfrX28I2zTD97umqw==,cSFMMg1cD/QvjUwTVK+4AQ==,5NUH2JzLuBzp9gqPRg77Yg==,hVM+kweF7lr7ZYzdESguJg==,SpkfiNoZ0UvQFll+EljhGg==,4ebzSFT+MRSy5l3UaBBccw==,wjyoamJpx/V+lvmkFYBpNA==,pL/3u1p6yt8a4HEt6BZicw==,yg8QxaWklHrgyy8OF8FQ+Q==,2b4CI1ICPcKE3vOUSKVAzQ==,ql77QOO72x1ZZtEO0hi1bQ==,VwpWQ6F0V+qEvq1oneh7/w==,aUp29AZh3XBaZg9o6R6keQ==,K1rr+TU/6kdnwOZIAbaG4g==,HfhgA0wcF4EobmHzq3ISeA==,LvSwtApbyD0JJlAubO2YJg==,/hemBxpMM5z/ZRD/hnPviA==,ICVgjmvVvVJCok3r+89IUQ==,PLw2sIWK1WpKhD0lVaZh4g==,NpDeU03JqxqDKzgUgcqqWg==,CNXCKZJU9Q1i4jyGOCo+Yw==,F/AYlkqdMwAryQ1goM4NuQ==,D79/53SyeozhIokPutVoIA==,b7F83VisbogFxz5ZLZbczg==,bcs9/0MMJFOTK58p+DljQw==,YX2C9QRCpMFCCL0sex+ZKg==,byTdIPZe53PqXn7Jf4hufA==,lp5n1AU7vcwoegTvS+2BZA==,l/AgKCw1gvf+qSft/8Kd4w==,qQfdqFB3G7efNdGkxIBH3g==,j4X/en0LHpfUFRg1/mELpQ==,RXy2dZ5HN/VQ5ho3FuezuA==,EoLp0hrcH19ECY7jGdok6g==,RAPbMpRV9mYuj8fbsBU7SQ==,AqJrJWce+fNgVXGVDxPf3g==,Vru0tthDEOjQZj2IFKVWJA==,IhTUTUsDqRZhCiR+EOa8Gg==,GgiXpnNRhdXhDjc9nmMzVA==,js4V4EVaR58aSZVZlfjP9w==,A9OFvShAw2GDbVFzUNFdxQ==,LetpbL2VCWB5z/pDCoEvaA==,Wb6OmCnki334zNTzijXiLA==,2waVOx7uSL+VrDbs/0ZZhQ==,l6Dywc6pHulU08Fq94k9uw==,kZahh7lYMj8z57c4v76Yyw==,M/0t8xHHAVjwhQ0ugN1dfw==,1h7UCItGvPI86x4Ru9El6g==,phlRh56O8G1LQxCgGfFZgA==,QU1O5SFQjqP6rscwn7AdBQ==,lBM/6JqnAjnwhQ0ugN1dfw==,2GZb98NbNxKPb+y8QI4cZQ==,koJ6B6DW0JoyZku+U9E3wg==,VAlhc+jZrgZ1taJkB/eCtg==,0QKzaPpR816DKzgUgcqqWg==,1UGnn5N5ymvCLyF50OIzfQ==,Md85hRLj6dTWKJD3cZifvA==,/GDWYupI5noa5ZnloOfVKw==,0bG0EliXHVhM7STj7t6o6A==,chdMA/KOzQ9lfG7X3Q3szQ==,ZJ0+zzOTR2X4RshIne5B/g==,ECsZR1wgyrd1/nWrFC6Nfw==,xTYnV6MMrMuuha4rqY23rA==,SgnGo1KOPRH17ESrMR/6Xw==,aOax8zez4BNm6HDylN4eow==,VzSINNg4An0t8Jy4xl7T/Q==,Y0zy7JfQmSAg3oXR7thptA==,iCuqN/FtJSxM7STj7t6o6A==,3izh7w8sqQSkt0eJmLG/Xw==,B/rRis0sa86gpWACX1ApBw==,OXlEsMjH+INi4jyGOCo+Yw==,WvE2l8NltfZhCiR+EOa8Gg==,cv9c0oVLE7myNgU1tQ35vA==,EAYWB1x4+SxI/TawGcT5og==,6d5mJiM5zN3gXwxkaqYCWA==,a2i425PkrN2W82DcSmFWVA==,8RD2xukzQC3I0WCA5kvm3Q==,E2nknCzU0pfm3DRNqRl3+Q==,NFGt8OdM06yDMKYOriXUUQ==,8p7bnIjfvzHxxRtWBwr0mw==,16W3yaBmMe9/Lw5YWOr1yg==,Ph62i5hRirfUmimPpI/J7g==,4VECdKR//Pkt/cwKwhoV3g==,0oSK4BFG+IGDKzgUgcqqWg==,uw9sVW/N8jgtuQHgAzsg5A==,0r6ueKq5SV7stCuUR4gG4Q==,gK3B5vFMRYu2bZ8861e+DA==,2h69haQppGZHVcUWT5CCXQ==,ivXfCqYo6KtLQxCgGfFZgA==,BQX/ri+i1lPBJBxywcozvA==,a2i+NbRFnhSWgK+WY66giQ==,Ck2C92YrGHyU0DHsxP+6ZQ==,EeWTsRRc4rr17ESrMR/6Xw==,Bl47l9MPkLiz+Fx5YlaK/Q==,mNN1dmQA2dyvmArG18vTqA==,xmEoERIYs5cwvb0wWzSMZw==,8OY9Ke19fvGs2xZ0i1Ldxw==,8OY9Ke19fvH30WvYtPtb4A==,8OY9Ke19fvFWsL7mJZ1Dzw==,8OY9Ke19fvESibnZ9uYmVg==,8OY9Ke19fvF+lvmkFYBpNA==,8OY9Ke19fvFAEgRIqnTfTg==,8OY9Ke19fvH1ZyPUreETnA==,8OY9Ke19fvHBJBxywcozvA==,8OY9Ke19fvGwblK1VonZQA==,8OY9Ke19fvHxzFwsaSDXZw==,ufEk1F8Cz43mTM/0NFHcew==,XSXhimfKMVTgHC4vfWdG8A==,02fgozLQ8PGThcVzEQ0ORg==,iaEgq6fWvRPQFll+EljhGg==,lsR1LGpt68xQyxQ4TYLT+w==,gVnH9jTMX4oKvVJR/SAYfQ==,J6UalfTTFZBk8CnVUM/rcQ==,Pe66g0zzwmRq08VSOtx4ZQ==,rIDzjfZuN/q0sYpltODQTw==,h56bsaxfqnP3ymFCuLgSlw==,FK8o7MUogilX6Tg4osJ4BQ==,NOfvE6FsvyCR6swau9vSTQ==,cQ1a/AUphz2ndjHZP2v7Ew==,z0cGI8y8yLWgSfITOAB5Rg==,gST8G5qBMgLJ2dk0kUlEqA==,gK/OR8ZYqMeuha4rqY23rA==,tmQMrRqvzeplfG7X3Q3szQ==,OZIxOW69Fb0wfmJawlwyRA==,Vr65/WAw/jhpZEUzg7owxQ==,vugq0j+YgOMI2zTD97umqw==,irftfER7g0FmQNsyeF+X8w==,hRmRwb0E6O5U8GPL/dC+EA==,utGkgIvkFvwTnKVARTr88w==,g45crLRVVD2bnOX9GW+VcQ==,xymWvu5DhaQKlZnuaK9Q0g==,DzdfKch05FOgs8I9BOgNFw==,oceXwkQhYcASibnZ9uYmVg==,NURpCt1issas2xZ0i1Ldxw==,fifRRR0HRaAGCYTlwy2T8w==,CFgbvvH3JpITgfwL2KWPgA==,ygN7JFx5oALgyy8OF8FQ+Q==,+Bd4TLMmJzT50XUWxgfhlA==,jj3A5n6hMvFGJ95JElhtcA==,j4tTJHTaecUI2zTD97umqw==,lSjAP/8heyQpzmtdGktkFw==,RzxCgGaZQ8QpzKYqpfrQNA==,lg3g9VUbdQWL8y9Kn7oCxg==,y4vQX7tap5ClMIdoexUcKQ==,MFrQE932cn6j3tS2Yk8HuA==,eagfaOn5UfbqXn7Jf4hufA==,OwbqTGHK7SOYXrb9OqC0Hg==,n5zKWL4JdQzDl84sxaWcyA==,u+n3mjfkdLZGMG+TlSbDzw==,bLZfGI360TNEPNDTuLo4qA==,bVpxNf0rDJ2SkaUbbLPdWA==,9B4o5dmMjzM+NcBPkH2mCg==,4eRmsa3H5ONPOlMTJNRgHw==,EVlM10lA+FY3UtRohRd/rw==,0XO57zfGSqP8j9hzxLCsmg==,ZHKhb/a/6qPXoKAuUMD+BQ==,Lvz+O++PdSpqhKcsaIiTwQ==,TGaEObkLi59T0lhQZN9VwA==,WpLEW8UPhVh6NpzerTf44A==,4K0eOF3i7qKk2bU/0sM0iw==,1HRTcxEFatpoyWAHus8t7g==,w/Habqs9EMIzus5nmcEkhA==,0AwmmrVEqPjssXYnlsfBxQ==,7Ys8dVLbr1Jc+55Iz0YK/A==,pUUpy5T3pu0F8RyxHR9pmw==,Ex4T9axnwHtIESVN36cwig==,f2R/eyRzoxP29bNL1T3X8A==,sm4KlXEQDYiEkiRG12/fKA==,f2jzEPJs+NcP3qQ/DqGalQ==,YRVOiOhTgdTj4jrCdRDGEg==,zq1PC8Fv0C8lOTeLwnLraQ==,3o/z5vQmd9zJN8yudpnWAw==,T+fsudkuLG9Iw84sxHdqGA==,sU90DieRtcgGelYckCN8Fg==,IvLdUPN9VXEc+vi8iKOG6A==,wifjLAWn75zogpJHY0FULg==,EEvE44D7WPMW0tsryl37Ig==,OElzYJm6xua+q/HiZoz7ww==,iRlC7pmBIjiMmchnMecV1Q==,6Of0x8WoGYIa5ZnloOfVKw==,0S6AC48jx3rOiCZE5lxC0A==,DDXKqbV+doD4UnHWfG9C2A==,xotcsjHqTwraj3079ESOjQ==,n9iRm/oPu2ucB25u93xW9w==,vNhPJKBmvbk3x5XyGkqbAQ==,LBpyAPCOJr2USSRSUYxYBw==,g4v8qZgfMuMO2sRjINBe6g==,H8EZbDKtZDayLaAQZ0sTuQ==,bKqUjdRCYgCOkMeP5JaP0Q==,bOFO/Gu1OeTn4Xv/yISQjQ==,65uITEOaDonvfalI/abg0w==,qhlg1nFSkg0vFtszWHSUPg==,hGskU4Gpqd/c7DYRHx/5Gg==,ZXX4mYqrD+RjwoWr1H5dIQ==,iQNSiSDH/5wfm596zZevyg==,39aAtRrixR4RZVg9QvXjSQ==,2ZBglKRfKJBotipqK3rxow==,swfsEcdPNgAks70JiE4wDA==,GXDydb/0yPcI2zTD97umqw==,orWc5f8MyKUMYYqKYyC+qA==,9+Ngh2sXdnY8OzRiK+RP9A==,P7MPYcBOc5Xt0U35GuFQBg==,2RybRNf0Id4ryQ1goM4NuQ==,gie7Mfyq0c4+q0UT9/D1SQ==,LBGm2A9Iiyk5O+N8Wo/erQ==,E17DQFxnj3VsHVK/SF3nzw==,8fau6pxfzB6wxgMaFqAFnw==,ZGI354AwS1lEvN0lqGsxlw==,VVmbGVkIMZ/adOlKvxtPJg==,KKm7CUULGyW7QEqp44qwyg==,Ck7Yd8SgrdflfFDLEqbdmQ==,kP/Tda86yP37n8zSL5SjWw==,9R6vT42fQkQzo1BtmXZz9Q==,PGtUDRQqqMwV1e32wu7GeQ==,+SO5Fro5dZL8U99eJp2vlQ==,z9hFfQF+QMCk2bU/0sM0iw==,NURpCt1issZZ8UziBXTZgQ==,vQar1JXVtrun3XWCsXzAuQ==,EU97CS7oPDiuWIs2/b4g/g==,Num4bt8CFTjDl84sxaWcyA==,57uFBY7IY3fF/bGl+hX62Q==,/Xbgwm6w15VFa+jzwDgWPA==,wZYcy2/X3Y/whQ0ugN1dfw==,6JIOWk7n8Ve4e/MC9yKiZw==,FtnTnMC9FMoS8K8/gb1uGQ==,yRUeuquva+O2aOU6ABNgfQ==,qf6ZEGOpnyPgyy8OF8FQ+Q==,bfTQjeGq44ykyPKFetdzZQ==,m0iR7djSTs8L4Wv1qyuuWw==,lcW0QQeSN+EyIn6akms7jQ==,QBhbKu2jLdRM7knhWmd3Bw==,JKLqR2ZK2UK7Bzy1Vw/UKw==,qcGkrDkxoI55bKJCW0ZZRg==,p5KChQ+fEt+DKzgUgcqqWg==,wTzC6zKLaldIK+sWKl7ckg==,GnI55ZuQhgDRHh/35pyMUw==,gf9MsBIKPWmbnOX9GW+VcQ==,9eYvJXbzcAE/QsY4SyHw2Q==,Y47gxkBONLGO1+2Kbpvf9Q==,0Rqt0R58466YT9A/506i4w==,dxGquUGGn5H638ENSlHvlQ==,0mxn0D0nSU7Fs/6DZwXP5w==,ELd0IROqNwVr+l6UfHscQg==,hbIfEX4tMwCYnf/AelK3NA==,5u/19TIdhGrjqww/IUUFSQ==,SCg83vH3wVQSrQTjs8sZfQ==,V1us43KgXbxzo3Ky8IqBdA==,QHDWM1uJHXxulfBJna08Qw==,9j4vqsyDJkxOiUqnixGRFQ==,aCHkwNxRd6zOoXJ25qB9fw==,zXzzE46WSQ/WKJD3cZifvA==,ZZ265u4QPfkoH7TDuSo0CA==,mSauwB/8qdckFNtNMz6JLw==,w6uULuFWu4CBAavZjXkaww==,8OY9Ke19fvEPH18g7TJJ3Q==,8OY9Ke19fvGvZPbpQuUm/w==,8OY9Ke19fvHVSHodLcBrLA==,GzMkaegQxq1Ig/7lWL+6Tw==,q7R8IXGnk6y4lg1UPnL5Ng==,9Veslof0YruZmuHnhKTkfA==,KTqiEnFOpehsjTKF+sLB1w==,dn/+igeGaW4mZMjA3TKc2w==,m2uV2cBJZrN99gARyzubEQ==,qHGAbmDYlMHp9gqPRg77Yg==,haAlaaNCzERSpJEbbtFBDg==,upakv45hHOzd6EoPvubmdw==,XwHUrt1Qyfj29bNL1T3X8A==,3KjkBgmdWi9Za5T6GtQ/rg==,AnsiVDgJ2DyYcNuwOURmpA==,tpSZtUOlOlK1idTZgFuukw==,5Rr8yjgAKzJ6gcjEJz6U9g==,Qt+8YCVFLFCRkvmitjGVTQ==,p1mYgcmB99V3SnR7K+arJA==,fEaj3vr/h1DqXn7Jf4hufA==,y+OwKz/xmb9tEBa5CtYTfA==,0VsJvl2GgrbpLTca6l7UBQ==,QmAqSehpytjXfoi9Oervsw==,L4WfkZsypxeL8y9Kn7oCxg==,UHGxBEyeCTbvrKkPsvvk3Q==,kRceXdNMwGz30WvYtPtb4A==,XFJRgigbNoMwfmJawlwyRA==,isltxBFQzvqxxBTgghzPHg==,U+WKCaPLtci2kSujBds99g==,FuF51aJJIPXC1DYEwp59lA==,UhNSrbd7qyZAJbqWUyO8Hw==,u47cXkjpImRUuUeiKfYVSg==,GOmoAPA331ebnOX9GW+VcQ==,HLcqfk7RGi/7JIVJPunvUg==,pvmp3w39oRqRRTyoEWj2+g==,4BWSyIdcGMSYqZg+oHmWhg==,SOtPv1samPN9xHBnbLBKLg==,VbG+0SKEqMgB6dt0nDzIpg==,QS+RPvt37GoJ2LAPiBtqLw==,T8s8j5XfEJkryQ1goM4NuQ==,LGdSXfLgcX8W+MnWiTcFfQ==,OP3iaAIHSSm9ZnTS+PkKzQ==,xLap9MdmJ6kdXC0V7KaZWg==,YeszMNyD8+BDpLUrbr3C0w==,Fh2b9zFqVfBl2PSt3sUoxA==,eZgEshH4WR7LBXj8KVtFbg==,oOZlade6PEp08e1RbHWbsA==,jC2fNxoiAdBtZtZANOGqMQ==,bY6dww376WL8j9hzxLCsmg==,T2Ru1OTTCYIOf8uorKs2qg==,/B41UJZcNN+daNnmWZANUQ==,58IJIOqB11R1taJkB/eCtg==,UC2AywPjA8pmQNsyeF+X8w==,RtMN2fflgfhxgQbYLA9oqg==,rqGcxtBW/D+RRTyoEWj2+g==,M5btbh2n0Dki1EdzMRRCNw==,V+y934hHRcelhH/GJLTN6Q==,NIGjdp2aQJxRY837IJUHgg==,/mQOVMrfXdfIqB+Xw5xpfw==,ZXgbSTyHlWZRV6qxI0of7w==,T3N9Q5wk5+Ag3oXR7thptA==,P5eDBnhop0ecQOK5r5IZZw==,WrZzEPmjm9kVq8B5QglqtQ==,L3SVMo/+ZnOV++R2toDwQg==,8v00Jhw1cbzRHh/35pyMUw==,1cc7WXWCg+06JoihAw8VXA==,fDsNvVKBMKJnwOZIAbaG4g==,D6GkTAGpkzoPH18g7TJJ3Q==,Vz7/0R9cjp6g9lXAcv8Lew==,28oANVxDFf4U0LVDGjudHQ==,b0Tlr0SQu2hZS1fSx9SXnQ==,DbMF6gtldvnaYC9EtE/KrQ==,J1MyeK9IvOEW0tsryl37Ig==,ergjDzT7+6O7PaAXmlWmtw==,T4YaLr5ZuFlOC3f4XOwfMQ==,I2uivaclRii80YFKDaNAqA==,bpAerUlFS8Gqw7MOcSoqbA==,i1mmN0OeYivbxK4jv+i5Yg==,NtTvOPv8QA9c7LehZMzLiQ==,gtdeDoG1Md6JIxVf8WtAMQ==,P1NBx/k7oafmHP9iSW3YkQ==,yvgB87xwlyublz8JjBgabw==,ntvA2usY6eVtklAHSHj9GA==,lytF6F7KN/EW+MnWiTcFfQ==,mAStr5fIGkxWNsRdbBElUQ==,YYMCim/2CsHQ9IcHpZcDCw==,MLOIBctNA3+W82DcSmFWVA==,C1vdEmbZLXGdUN1jS1vdLw==,30yvFwNri8+GJ/a0ey6DoQ==,1X8eCXnTM6Zrtt3xwsnZZw==,8OY9Ke19fvE2Ml+KP3U7ow==,jaoV87sqFAqoXs5angRRFg==,8OY9Ke19fvGkyPKFetdzZQ==,8OY9Ke19fvGRPWKw7c/y3A==,8OY9Ke19fvF1QRmtmzXvkQ==,8OY9Ke19fvFxgQbYLA9oqg==,8OY9Ke19fvGcB25u93xW9w==,V6Pp5lILn+y9ZnTS+PkKzQ==,EeMcr1D2PrT6BExBOYn+6w==,Ujn5cPvlv8fiqbyCHyhUGQ==,ZkFsoplqeh7uG5/cSRKKCA==,Qyj2a1xMo5Ai1EdzMRRCNw==,h3eRNsEONdKzTDQ/T+wz+Q==,RPw/Sco2MGLGclv6221BSw==,sX0HPbsYBtiyPDWyZTw8Gw==,ReRtV96qVxFotipqK3rxow==,8OY9Ke19fvGuLmzg1pnAvA==,+sGHmXZjymXGGfnzn86Cfw==,bTxlnbthBiEqjEl/m+p/uQ==,y1JZQRkfIty5lD5eHQXHBQ==,GnZlogHQ50s86x4Ru9El6g==,K+NOsZyH6ETewcuFGXbWrQ==,wTygiZC349nc7DYRHx/5Gg==,6NXbuGpnUjquLmzg1pnAvA==,WjDCBy6IbFN5QMKnPI1I9Q==,RBXRkkITa8HgOIbtsYGLqg==,5NDUaUxjlGr4UnHWfG9C2A==,EN7D0Ds9l88zySceQIFHuA==,PsCphQu51gAg3oXR7thptA==,tQB8FnKfyVxZZI+zLiFJTw==,ht/FibaP8H1Im1EgIE7ifQ==,neVFd7zXl/yyPDWyZTw8Gw==,IIaEmh05UGlr2BWZjfNTMw==,E9skm7a4d+v7wX5mA1oI3Q==,MrQ1B63SQOK4+7ukzOIy7w==,0aJb2grC55mXLvj6/xIPCg==,KY8bdkGWEywiWrtphaKvXA==,JsMEeSw1oG/hIokPutVoIA==,qkYyLw95JNNf9Hcw25Xr4Q==,CAylfK54uE/xjjtlwySO7w==,gy9Cz0zWaUJq08VSOtx4ZQ==,Mok99s6ZkcyqN49tcblgWg==,FZH+mxIDTanxmYC2xCdfPg==,Ix+yzQa1OSX3jJiABkthjg==,daiQ0GCzanJ/bvE31KQkMQ==,rtRPcYK3dYPCFr1gLi8Yhg==,RjE2WkNC+30Fxz5ZLZbczg==,+EHmbYW0wHli4jyGOCo+Yw==,0IjNosEUbBMqPKGB7Lpx3w==,9t2rHVs2ThXxxf+F2xOgKg==,3rrzAE7WvKquha4rqY23rA==,ODA/kAKVmYsKyc+aXLuXRQ==,SH1xt+u5j2IpzmtdGktkFw==,dtczQsEYNNFrtt3xwsnZZw==,JnG0ZED4L3RwvMrL1lOVag==,kRZTHfYomBnj4jrCdRDGEg==,4LCeeQu+tpQ1eIxfuJQEdw==,tUGCyspVYdGane8oN621Og==,0XZEBLin/1317ESrMR/6Xw==,dpA+ug01GhmEvq1oneh7/w==,xhiW1yFyMvRtklAHSHj9GA==,s0AZKUhMgigYv/EK3HlKRA==,jiH8UrhzJkz50XUWxgfhlA==,J0K1peI60ynPrFtS7Wo3yw==,qHGAbmDYlMGz9Ky3Q3lWCg==,Zg9oYQHpzcE2tHC/VGCeLQ==,FaDRNqmKDBNxgQbYLA9oqg==,SY3hhIf2strI0WCA5kvm3Q==,tOF+qYFQnCl262jbGniP9Q==,FXjWmZ9Noy8j01Bl4d9Cvg==,EeZpjb4EUKcyZku+U9E3wg==,qtkE+z+7ExRgbZvsz8cOhA==,bcTR2Ga0XJhybbQ0C0bcyA==,Z8Se/Uif7NjN4LKFPTr3ow==,xOp6GNM4Epm2V0KVoZIGXA==,aZUcr6KsZjxqhKcsaIiTwQ==,yRUeuquva+PmZ+aAs57+fw==,qOrVXb7O0n7nO3p6Z+R5Lw==,aXzjkOa/ZkaThcVzEQ0ORg==,VwcrP0Bcp5mkahzl0lgsYg==,VkDF1+h476g0XxjUpR+oqg==,svVjDDweCCJ/CTwkMq/9Og==,5cPUn81nWr3uG5/cSRKKCA==,pt4hbVaxFH32RlK9UIcbfg==,BLY83LvrjeawZZrocviDvw==,k8jSEgGQa0UU8pZJNGRAjg==,TgEd8kNuOrDg83QbKYBQjQ==,f7oZVb0neS/bxK4jv+i5Yg==,+VuydpoJrg3QZj2IFKVWJA==,n/+8EHtmQhrc2OYIC6tEcA==,JbsCoFvt/80ViHbc/F8TqA==,hVf49JuU9J89esgSzMHE2Q==,Q9aFLl0L9wTVul4j2x32kQ==,17KsSZNcooHF/bGl+hX62Q==,bRsd751guY/KZzV0wxpyNQ==,qBKO68QC1fgXn6DHrP3wWg==,+7KhjnPyUIyxe74YLQdFYQ==,bJCyKoUi/V5HVcUWT5CCXQ==,O10pbe0+dHIHpz2gq/ML2Q==,j5cEAwFmklbh6lJBW+X2EA==,zCNnS4xl66lECY7jGdok6g==,vhIi+A7ntipi0QWYaTjq5Q==,Gme4TggalTXs4X7Q7Qyhmg==,dVkPLnkCY9xvNo0fOzNwHA==,VNmCyOAgMuO0sYpltODQTw==,ULyv42Q7aOQdryisegF4Mg==,tU4V4OPd6/Hxxf+F2xOgKg==,PEBDXHs1x66gihI9RvplhQ==,wQp2/ihedmzXoKAuUMD+BQ==,2CPKeHu4E/QqgqL3xsbeQQ==,1K8dJ83lLnJsHVK/SF3nzw==,GPNV3J9Sla5tI7iiBTh++g==,RRh+2EzmhYTou27Ps+DRHg==,RXHH6mcFLSGkuviJK7Y1tQ==,1Q4b5SDx2+I/mqr5KDAnxA==,CJQVFoGeeu7X4B3s7UkfEA==,d5LskeEfeTfiqbyCHyhUGQ==,hU3S2mMhU/tQ5ho3FuezuA==,uw2gZr/eV/cuf3pjUnjmBA==,byATVCEeLTukt0eJmLG/Xw==,IoGBU92af4+sTQQoyJat8g==,9zKVOgFSHm0+VrS/RaB2zQ==,BfLjLbf04D0San8o8EzkEw==,gyjb88WMquPHx9EgHAlrHA==,BaQbMNGyYmB1taJkB/eCtg==,eL5G0e30Ng9RY837IJUHgg==,ryCHBaVcaZxulfBJna08Qw==,LFATSyz1IlxAJbqWUyO8Hw==,hBtu+1FvCEEWV2vRxCStyQ==,NLaJS86phBoWHVVVmZRF9g==,K6GYLdYkHaXl7Yjp9tUcEQ==,6FkbsE0gGFQCu3ZbZHMbsw==,sk2+EADfT9aRPWKw7c/y3A==,AKdKx3gdDUTmZ+aAs57+fw==,uHUpSNgF2saxe74YLQdFYQ==,BkJBV09oOOL2HUB+4dSBBg==,zagHBGVbteWU0DHsxP+6ZQ==,TUrLjvYZFvfi8zwM1TKxWg==,NfeftE4vfzW450+9BSgIMQ==,wM130oJKVCyUvFO2fWy8Gw==,75PuicPVsVT7ZYzdESguJg==,Nfmznv56906ndjHZP2v7Ew==,JB3fRSHPHJUqgqL3xsbeQQ==,d2tsmTIkqM0n1QH3WBClTA==,x2xlzJxWfZsfm596zZevyg==,teAzyVeD1+F+lvmkFYBpNA==,8eEGmK++lXLGWsXq8f4uOQ==,bWhPbUFmY5DxzFwsaSDXZw==,hixleB3nO0aR6swau9vSTQ==,wqibFQxOHgIvFtszWHSUPg==,mIRkSpiSHjawXPS+2+46kA==,eYwWt499j3BhCU8SiXdTIA==,i0zlKtDPQ2U9esgSzMHE2Q==,yAJovx2kLqs2tHC/VGCeLQ==,g0ZzCWFvxlOATbVPsgEpig==,7j+bqCsE+zLGp/c7eYY3qQ==,A67+rLBnjEOxxBTgghzPHg==,hbwmpjZrsK9t2YGImt1Z6g==,37Jy17p3Kz3UFRg1/mELpQ==,nVOQ/lKwG6Vqm5yNPInBXQ==,4Dja54Ak88en3XWCsXzAuQ==,AFbQ1Emgbl2xal3rxsqRpQ==,N3mRUe195y7i8zwM1TKxWg==,bu1p4tDHLMx6NpzerTf44A==,uwCD6nuPRfmbGl/7eqb42g==,yZyyK5RvRMGXVsti8s/vJQ==,hz8jJzZ8WsTYgMFJ0gkBGg==,J8sQDvdJ90HmtuatSIT8dw==,1P3pDtdX9rd+H7F7mgDsTQ==,7ux8QSXS5HvmtuatSIT8dw==,tlIO/9oSzu5c+55Iz0YK/A==,zxBvx916/OesM5yzstpumw==,2u5lVQsAhypoyWAHus8t7g==,UVqkc+3QAOJbKCuFcDAxPA==,jgxCvg0v7ePlXw90ifqd5g==,llh5F0KhWiY5O+N8Wo/erQ==,ogJfqoYtMJTssXYnlsfBxQ==,GSqHeWiLZCzDl84sxaWcyA==,VbwNbHAgDVsD2a2u1lzTdg==,N/vx0SunjTPxtM/sJHJNDw==,vm8P3qCdvbP5/LiY6GFYxA==,o+Ej35UxXZBZ8UziBXTZgQ==,D53j4Q3BKplTQen5nxAtvw==,ht4QvJ08dr1qhKcsaIiTwQ==,B88AffxAwUoeyjj5WnSMgQ==,PtbMGquCjn4zCXqex7wqSw==,Xo8GOocfIj3gyy8OF8FQ+Q==,flfEVbQg06DJXim4Igj9LA==,BYcdzZjylMg+q0UT9/D1SQ==,Kb85cSn1jQ4Gd5jLsCeD0Q==,L8Q6GiUvY8BThqY1lYrvng==,NXcORod46r3ogpJHY0FULg==,J6H8KQW204rsIIEiHTaYcw==,8VWtJob7+lW4lg1UPnL5Ng==,/U2bYW5LoQ2gpWACX1ApBw==,3NZ9KOreQnkPH18g7TJJ3Q==,NFbB0xvpxFYuJLsI501GxQ==,RW7IpNs6Os350XUWxgfhlA==,pV23BZIwElDQWmG/5SH8Ng==,NS/hkWkIxDvf94MbAKMimw==,okS4cQ0PjUmndjHZP2v7Ew==,3TwtXtQtDmAuf3pjUnjmBA==,APlLWpD0UTLQWmG/5SH8Ng==,0NyqEyqCATLJXim4Igj9LA==,AoN2KQPlkyIRZVg9QvXjSQ==,MrPZbrFYm6XmTM/0NFHcew==,llIkFaXThfQLFz50Jh0KGw==,E4GMb0wh1z7de1SErJ+PIQ==,E8QH5MD+HzxlfG7X3Q3szQ==,8h9QyJDufTD4UnHWfG9C2A==,gepB3eJDqFuMSAMP2nIqpQ==,1D+EhelRxyTWKJD3cZifvA==,rUEASKYJEcMSysEf1Ju+tQ==,SMsOBmIWxke+vwWCCgoArA==,6nvU51ttdHpqg6SfI2NQ2w==,xlW2COf4ACOI4ObkhzLPUA==,FBQDbzLxHEhZ8UziBXTZgQ==,M9LF/tVWn4BrYf4Q7+BjgA==,EtvfCyWNIN7caMNUo/ExWQ==,8eEGmK++lXJ2ALvOKnnWIQ==,oVBusZKiJdcB6dt0nDzIpg==,ONIs4aAnx/Vp+q4/qTlOfA==,0WRZOv6UzTMZCbWHp2W22A==,+DzJzh8subDf94MbAKMimw==,4E2jtJhD+u2YkG32cfljpg==,2qx9PeeRm49bcwwNGFAN4Q==,hN1TAzPBzmiVcJVcXE6iOg==,T8MkWms2/3Suha4rqY23rA==,HW1qmQHuxqmn3XWCsXzAuQ==,M12299J51mYhzVXqIVMHCQ==,tQQOmdAEji1EgXsD4EBmWQ==,7V/pR85G7QuYYqeCM8CJtw==,pcsBaCdrg0TD8V+Q8raPAA==,MHd1PgbWodj+ZQ38gcEh5g==,/vEEJC4qSggyCQGHMgWCgw==,7XyqhjlwN+vohpqL8iZ2TQ==,bveqNDCoIWMfm596zZevyg==,8ygAACrl0LVi4jyGOCo+Yw==,yOuKs1+MAqn17ESrMR/6Xw==,Gk+kT5N+oFHQA4J6eFErhQ==,tJra4EOTAJD2HUB+4dSBBg==,jlgaDNCPiMpkiwj+cJXZFQ==,zCBzLfHk/9oWHVVVmZRF9g==,h6TONf0arByHIt6FCgYGmw==,lP80PR2qQ5fJXim4Igj9LA==,YfBAerf96SdCok3r+89IUQ==,fgZtz6Pk2cC80YFKDaNAqA==,xrtrELqHdBWwxgMaFqAFnw==,0zwnrvHJqQgtRm1syf6Odg==,HKxd7fI4+SwVam/MiOlpfQ==,9tJboZRvRJU5f3iw0175TA==,hwwaUCqnSM86pjazO/SEkw==,Dn1fIIvh0iafnhvJ+4LT+w==,ABUWe+FKUXBfB7t4MMDtKA==,t1pNbSN4AQqdaNnmWZANUQ==,/JfTLlE8cpV3SnR7K+arJA==,usT2nVvcyEeTe8W2yu4ymg==,3CH/LqvwUoYtRm1syf6Odg==,o62gZMCkuTPW0Hl2gjwAPw==,/UEACC03CXrgHC4vfWdG8A==,bJ5rgTSFG+4iitytonkISg==,uDrdqm5tbwgIn2j/JU4juA==,5vrZlzx+E1KSI1u4Ihfw7w==,FG7WqCZhl8UcOYP/4EauEw==,TNAL2YhpBadM7STj7t6o6A==,VmP0jum+Uzsa5ZnloOfVKw==,bODIBpbXaYvogpJHY0FULg==,2xHWvUXWBONIw84sxHdqGA==,OGaZqRlvfLJLB9UIYVeyGg==,CknsMCjh7BhAZ5Grn9LNFQ==,+U82HgbOPh4obmHzq3ISeA==,nlSovEV1rirpTZQSYNbjSw==,wMNpUiAbO/xCOOWZtCqgSA==,XOCyq60KzOQbs57tznwZNA==,4Dv5z0bukC0IqEO2ANbpYQ==,AFDtPD526t5mwPFawEArNA==,1WBSquJJXVHQFll+EljhGg==,EXTWniuFprFtI7iiBTh++g==,WffkPRUJ/yBAZ5Grn9LNFQ==,t9Pq705gYcpCjObXkOoG4g==,uK/l0od7adsHzxJ+Qm11dw==,iv2ZoWEhqpD+ZQ38gcEh5g==,jkp83Uc6jpTGp/c7eYY3qQ==,v2vGqXdAeCVf9Hcw25Xr4Q==,amH4QQuofboz57c4v76Yyw==,xO2YN/98s3FGTfM1XK9wsw==,dPWkjOwRAFlRV6qxI0of7w==,3La3/7ZxHiewxgMaFqAFnw==,pHgXsghooallfG7X3Q3szQ==,a06uxqDqFaMIn2j/JU4juA==,V4HKVe8QcPmThcVzEQ0ORg==,dPWkjOwRAFmEpqTnhbaHNQ==,KEdZyVkiYM5BzwcTBtq8/A==,FXmLCtR9hng/h86ZjFAY6g==,jm98G2+bqoup20VwW9qnIw==,GZguKPUouN3peho7tBdx5g==,IXt7CUkrgHeKbMCR2ifbRQ==,GK8vBWGo+6/GGfnzn86Cfw==,cn419KvkEs2bnOX9GW+VcQ==,UQzi61qbz4WI9A3sgbzs6g==,DBZvcnlTloagrxvH4LbEDQ==,RFgF/CRzIVZ5Zka1C6wHww==,AVdHp5y/7dbO7uiFnLB3TA==,SCXVCcHByFhjcLaO1eBMNg==,WwsKAGIRBcJZpsYfwIidfQ==,NgTwfjXC13FIYC1IgRGNPA==,hobXXX/DPQwSan8o8EzkEw==,jhZDu2M66cKGsvKdq/ukYQ==,JYdZjqeu1Ioi1EdzMRRCNw==,QcDcJeCCbR5ReOUNJ3bd6g==,ucD/GYPWLmgwfmJawlwyRA==,XRkfEtNJUghfB7t4MMDtKA==,uDeTIGYtCLSVZOAQ2EarrA==,yvcKe1JQkH6i275+quEBig==,0/dsHkjz7JPRLy8gp5bRog==,C2MdV9HG8NSQ58Tc1drivQ==,OVckrQiwgWo3wdCXRawPAg==,jQs9Lwq2gtMB6dt0nDzIpg==,JBljtyV2eQ+9ZnTS+PkKzQ==,cSlZUq8gjJlmQNsyeF+X8w==,fGbi1i0f+WbmZ+aAs57+fw==,vM+aqpzhvjPZk0T0X1cVFA==,Up76g+92rrv2wJSgF1Sn/Q==,+57hlyrVQA5OiUqnixGRFQ==,LU3DjDvL4qPh6lJBW+X2EA==,6W2lehMixTK7Bzy1Vw/UKw==,okKfQZoCVqNJj64SV04iDA==,6fiCHRlRlk1R7Dp+gJHg9w==,K7P7dPunN+egpWACX1ApBw==,McG8So+/3MGq/0p6ZQ+SWA==,G+vat/efAuDCb8VH1CTbgA==,JeGLBZWqKNoppcYkOkJjZg==,8JK6qc5lezwSysEf1Ju+tQ==,x4GlXV/vrMcvhQiymhuqtA==,T6n2aASMtnFt2YGImt1Z6g==,yzvVm2aHWi5K4i20H4ckCQ==,oWXwribqgh+R6swau9vSTQ==,YOnkg89Q/M5GTfM1XK9wsw==,++C5nAqRcB03wdCXRawPAg==,Ox1W9yCgQk7+50MYbQCzXg==,pXu0GsIlgyYgVaX3hCyTIA==,EQewZrpqc4iV++R2toDwQg==,9Q5p+anvZS9pZEUzg7owxQ==,Ww8G+Ie/xBMMBF5ib9223g==,skkppev38gxv0jXvBH6igQ==,z1yu2V2oAdWbnOX9GW+VcQ==,giKaUDs7Gjgl2omCk4eiPQ==,HbS44LrWkmqqN49tcblgWg==,KdvHk5MUYNNFa+jzwDgWPA==,Cht8/kE/HAqXLvj6/xIPCg==,KhSyfinFDoLB5EwLxkDOqQ==,TjVNfVlfVc3ZpvYRYej6sw==,ED3HIoq9wRigrxvH4LbEDQ==,xEAq6SaU6ZPgx26bECVYSQ==,F+aS3MFR1Xvo/0hfAbo8Rg==,4H5N0GsDCt1At6G+DS8WFQ==,HZDpvgxTMUoryQ1goM4NuQ==,3C4Sd+rEsiTrOZY8T1848Q==,FLDl9ksNdiazTDQ/T+wz+Q==,PSVaGaKTQ1UHeigRr4HM7g==,rF5BfCX7/f/xmYC2xCdfPg==,2u09TGFJlOHvfalI/abg0w==,sbv/3RfmidAyIn6akms7jQ==,kVXl99cPzjgGelYckCN8Fg==,XwHUrt1QyfikuviJK7Y1tQ==,xx4n5GXI3ysM4WbfGnImcw==,d9ppCHl86TPl7Yjp9tUcEQ==,GG9J+Cf7HAgaLJZtdegGAg==,T2MG8Y998RnnO3p6Z+R5Lw==,uqQicSiYw49JbZuU1jbOog==,TfvTiNXHpQymKWF0YIOjTw==,+L+Grb/kDQ4/QsY4SyHw2Q==,+0jYkJQOjhmV/zIKjWSFyQ==,1Gauwhkbuv4cVClGdn7tNg==,x2pOFQvY+QTxUubz9trcUw==,y1gxiDt3kldt2YGImt1Z6g==,a3oLjYOL5a5lfG7X3Q3szQ==,NrBMa/0Yv1+TXasfb7/Kdw==,YY7KHqquVNvXoKAuUMD+BQ==,21+iHkAq0wqw9Pu9Hs5iFA==,EdgIq326oc0Ai0xTba5gwQ==,grgt8MpFGl0dryisegF4Mg==,VNdzPtmg/I0jwe26HtPTog==,/90YDRMVaaK80YFKDaNAqA==,lxRLK8fBcCYIPtHM8ONFPQ==,hjw1am7OXlr6rscwn7AdBQ==,m+WwD2MvJa3Dmn6Fh5h/Bg==,Yv87YxxtUjY2tHC/VGCeLQ==,6IQ/uObDbRD2LIys9pPmVw==,/tLrU+UiQUjFQc9GOmL8rA==,FJPnC0R0AhDxxRtWBwr0mw==,Fs6KDBl90dHUiBNZ4ocHyg==,0gCRpd0/b6TlXw90ifqd5g==,iAL8D5g811PEVXC5ommIBQ==,iUppCeHsp1RLrdbeGwtoBQ==,PBZycYcmEr+V/zIKjWSFyQ==,ISqgBcbfdPVFa+jzwDgWPA==,C9/SV+/DBDm/eyAELeRy+Q==,ihT1fVikV6Ckahzl0lgsYg==,kiQhJ3b4t293SnR7K+arJA==,CmKXSiG7tHIMYYqKYyC+qA==,dxcm7ORWgvLf3FEmGfWVQw==,B9ADoymElCXovshNVyMRlA==,hQJmusM6yNJUqVo6IIZXsg==,FniIftKQDvTlXw90ifqd5g==,LC1/mXWyZ5CW82DcSmFWVA==,96xK9YqvVTgSan8o8EzkEw==,SzkJPXEiC6v1mGIfF+ILxQ==,x+UcDDYQaInlXw90ifqd5g==,9IeGq1NdfayrFqMCioPVbA==,0XQqJrzcpsL8j9hzxLCsmg==,Yte6gUVIXA2sU3SOHnkZ5A==,ThItkuPQunMa5ZnloOfVKw==,LbMFHBNyt6KgihI9RvplhQ==,mNhNtoGNw8rLokL3Z3oyeQ==,rGd3h3uhf05CCL0sex+ZKg==,D1L3kNLQfvR527luBn3SBQ==,ZqUqyFc5z1v2wJSgF1Sn/Q==,6GGxD/M7eSYAi0xTba5gwQ==,pC9MqLhEqatT0lhQZN9VwA==,Dq1rfQ+J/mu8s8K43/M3Nw==,Gv+Ij0un6aq47emgCpIQ0w==,pses8oHEbnnIqB+Xw5xpfw==,BMkSpnC2unYMYYqKYyC+qA==,Lrc2ZibpWTBgbZvsz8cOhA==,PWihOP854IBotipqK3rxow==,Z905x1/qPOkryQ1goM4NuQ==,c5600KMCQ4QSyMR5H1iNuA==,sjkEAqO44AayBhtC4zxG5w==,WVGXieTRD5z1gftcELrhPA==,Ju4S5ux4zGczo1BtmXZz9Q==,dU1kqAeZd6qVZOAQ2EarrA==,wZ7qGn8pNx+6FbfP8QYEdQ==,jRV4TJz/OQXJXim4Igj9LA==,aSnOhLbmhSbaYC9EtE/KrQ==,2/eRHHbQMF4DIjpOWe4YHQ==,ejEVu/Zj2eti4jyGOCo+Yw==,isdsuLOoTj6qsGe/EbFM7Q==,kQAv6hqPT+3jkrsXUT00Pg==,mNuxr1UEG2mj3tS2Yk8HuA==,c5G/Ww2gW89At6G+DS8WFQ==,VVrciZDvF+TJXim4Igj9LA==,ZjahcSmsZ/ZGTfM1XK9wsw==,cifBEP9wZpQNLMF1ue5Wug==,GfvX7LABFuBAZ5Grn9LNFQ==,4wqs9Kqoc4rUiBNZ4ocHyg==,sozcUdh/2xRjyJGYco6kjQ==,JeDbD5ZguzcFuOegKu9dng==,WguomvfLXa9lr4DMG8YIiQ==,/H0PvF/9lRJIg/7lWL+6Tw==,naqzVWk/wICHAiRflwkvxQ==,vsZZTYBHZiaL8y9Kn7oCxg==,s78xX7uALnpGJ95JElhtcA==,R/k7uqzrcMBqFGgr/qK8CA==,8CdV/YwSY4OcPNVvaYgRyQ==,tMEVOLJhU02E3vOUSKVAzQ==,YWB8qKpkQwf0MBPoiQ801w==,7q5J2m3bsXCHDM9R/UI+SQ==,63iBszb6ZHoF4UEXbTar6g==,EaPuuFSBegQCu3ZbZHMbsw==,qISweElZ89npeho7tBdx5g==,oMicebLzMdVfDefKrfMZdg==,GgtGVF7DoEPsIIEiHTaYcw==,iVxqJWszhgYsb7pUZ8imIA==,/B+Ymy5L8liPB9fk4zoM3A==,eOz4UJHGdiGk2bU/0sM0iw==,F1cO2LF/8opaalltpT6u4g==,wclAdyRaHZtThqY1lYrvng==,PfZIHKP27EfGGfnzn86Cfw==,3STvvC+TRoB72w0fCRuh2g==,iv08Z5MPwgDyvX+1GPQzmQ==,Eo4Buy5mF6MtRm1syf6Odg==,2BuqY1JL1FTbxK4jv+i5Yg==,+bkTpeP61/CmKWF0YIOjTw==,zRD7iPiirFT/27ksbBhstw==,wx5L0A3+rZAppcYkOkJjZg==,7uvOtFlow+rCKojdpXv05Q==,a6o6GD67cKWW82DcSmFWVA==,zLJ4104Tj8dIm1EgIE7ifQ==,VL3Q4xAQcwmxe74YLQdFYQ==,Rv9vYts3j4Sqydzod/MwEQ==,WaHWNxhUzVfic1wFHOAd5A==,jC0s3rU7gFdOiC3kR1VVXw==,/dqOYSQSNciIL5dHxMEL5A==,Y3VNHYjUSQfrOZY8T1848Q==,Zj/heUTz5d01GigB66yVeA==,kziF8h+uoSG0sYpltODQTw==,PmyFxiqDOawoUmZWKF0bXQ==,BYv/03Bmc20Of8uorKs2qg==,e3scWWCF8rHjqww/IUUFSQ==,2XSqVn4BVGfyvNSVz8ixJg==,Xfxp0q6xxwh4MC81qHDWfA==,uHiq5cMcYcW61Z7mi+vjsA==,1ueeEpcUvD8L9qtdX3oNZg==,Q2qwUIo0bkiA7mGqIU1b2w==,oQtp+6KC+U7JXim4Igj9LA==,ktXoDI1F91cwfmJawlwyRA==,JyVDCNVRlBI3wdCXRawPAg==,JkN8mk+/SLiM6bKWPElVxQ==,YVfIomPW+VIjwe26HtPTog==,5royBj8yOBY5O+N8Wo/erQ==,Y5x4xrSUjI6wXPS+2+46kA==,8izLjJwPwRP78PGrlKNDFA==,w7YH8nyLURn0MBPoiQ801w==,W3bIMxmlXGBZa5T6GtQ/rg==,vTGk1/UP69BAZ5Grn9LNFQ==,/YsjqOh1rjz1gftcELrhPA==,z8eADCQkGSDGwdYzFTya5g==,bLZfGI360TOixpmTC90C4Q==,eR2RxrescNJT0lhQZN9VwA==,2GM1G4ffe0L17ESrMR/6Xw==,On/kyOmaPThRV6qxI0of7w==,l64bvIzxSHZgh+t9fHziZg==,kb4wW8UhgENAt6G+DS8WFQ==,A0kpNJYzUAmxe74YLQdFYQ==,RoXKSowIMha8s8K43/M3Nw==,87Nubx0NvNaPb+y8QI4cZQ==,uhfDmH+oqQCV++R2toDwQg==,Cqo6F4e2AO5kiwj+cJXZFQ==,1f+VmzuqCdn3ymFCuLgSlw==,f+5bLipcyXmW82DcSmFWVA==,uCOym4DSxMKw9Pu9Hs5iFA==,aLm/uoTTUYwL4Wv1qyuuWw==,rF2Htb3ACkFaalltpT6u4g==,V0VK5wBoSsCE3vOUSKVAzQ==,cVlD6IP7LdxBzwcTBtq8/A==,Mri3HwauTCwLFz50Jh0KGw==,xbdGUEnDBMG7QEqp44qwyg==,UbesnOQ7xUq9ZnTS+PkKzQ==,ke5WKq7FF5DD4lPx45dH8Q==,+saXRAjwgYFjqgKjU52TBw==,jJ83LgbgJYlbKCuFcDAxPA==";
//		String[] phoneArr = phones.split(",");
//
//		System.out.println("size=" + phoneArr.length);
//		for (String phone : phoneArr) {
//
//			list.add(decrypt(phone));
//		}
//		System.out.println(StringUtils.join(list, ","));
//
//		collisionDecrypt();
	}

	/**
	 * 碰撞解密 经过 前端加密过的、再经过LogUitl加密后的 手机加密串
	 */
	private static void collisionDecrypt() {
		// 前端传过来的mobile,经过LogUtil.encrypt 后的串 kEOP4D7UemPQE2Q8BeMR3WeglB1hSIxPJLOTYtqAdeo
		String externalMobile = decrypt("kEOP4D7UemPQE2Q8BeMR3WeglB1hSIxPJLOTYtqAdeo=");

		System.out.println("externalMobile: " + externalMobile);

		String[] keyArr = new String[] { //
				"1",// Android配送员端
				"2", // Android配送员端
				"3",// IPHONE米星
				"4",// ANDROID米星
				"5",// 颁发给m.mazing.com访问其他域
				"6"// 颁发给admin.mazing.com的web
		};

		DesPropertiesEncoder decoder = new DesPropertiesEncoder();
		for (String key : keyArr) {
			String appSecret = decoder.decode(key);
			String mobile;
			try {
				// 前端传过来的手机号码是经过 对应的appSecret 加密过的
				mobile = DES3.decrypt(externalMobile, appSecret);

				System.out.println("mobile:" + mobile);
			} catch (Exception e) {
				System.out.println("error");
			}

		}
	}
}
