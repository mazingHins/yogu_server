function getCookie(name){
    if (document.cookie.length>0){  //先查询cookie是否为空，为空就return ""
      c_start=document.cookie.indexOf(name + "=");  //通过String对象的indexOf()来检查这个cookie是否存在，不存在就为 -1  
      if (c_start!=-1){ 
        c_start=c_start + name.length+1;  //最后这个+1其实就是表示"="号啦，这样就获取到了cookie值的开始位置
        c_end=document.cookie.indexOf(";",c_start);
        if (c_end==-1) c_end=document.cookie.length;
            return unescape(document.cookie.substring(c_start,c_end));
        } 
    }
    return ""
}

function setCookie(name, value, expiredays) {
    var exdate=new Date();
    exdate.setDate(exdate.getDate() + (expiredays ? expiredays : 0));
    var expire = "";
    if (expiredays) {
        expire = ";expires="+exdate.toGMTString();
    }
    document.cookie=name+ "=" + escape(value) + ";path=/;domain=.mazing.com" + expire;
}

